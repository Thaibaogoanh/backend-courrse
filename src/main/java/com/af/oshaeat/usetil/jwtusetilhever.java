package com.af.oshaeat.usetil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class jwtusetilhever {
    @Value("${jwt.privateKey}")
    private String privateKey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(privateKey));
        String jws = Jwts.builder().subject("Joe").signWith(key).compact();

        return jws;
    }

    public boolean verifyToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(privateKey));
            Jwts.parser()            // (1)
                    //.keyLocator(key)        // (2) dynamically lookup verification keys based on each JWS
                    .verifyWith(key)             //     or a static key used to verify all encountered JWSs
                    .build()                       // (3)
                    .parseSignedClaims(token); // (4) or parseSignedContent(jwsString)

            return true;
            // we can safely trust the JWT
        }catch (Exception e){
            return false;
        }
    }
}
