package com.af.oshaeat.controllers;

import com.af.oshaeat.Service.imp.LoginserviceImp;
import com.af.oshaeat.payload.ResponseData;
import com.af.oshaeat.payload.request.SignUpRequest;
import com.af.oshaeat.usetil.jwtusetilhever;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.beans.Encoder;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginCotroller {

    @Autowired
    LoginserviceImp loginserviceImp;

    @Autowired
    jwtusetilhever jwtusetilhever;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String encrypted = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(encrypted);

        if(loginserviceImp.checklogin(username, password)){
            String token = jwtusetilhever.generateToken(username);
            responseData.setData(token);
            responseData.setSuccess(true);
        }
        else {
            responseData.setData("");
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginserviceImp.addUser(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
