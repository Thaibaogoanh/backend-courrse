package com.af.oshaeat.Service;

import com.af.oshaeat.Service.imp.LoginserviceImp;
import com.af.oshaeat.dto.UsersDto;
import com.af.oshaeat.payload.request.SignUpRequest;
import com.af.oshaeat.responsory.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoginService implements LoginserviceImp {
    @Autowired
    UserInterface userInterface;

    @Autowired
    PasswordEncoder passwordEncoder;
    public List<UsersDto> getAllUser(){
        List<Users> listUsers = userInterface.findAll();
        List<UsersDto> usersDtoList = new ArrayList<>();
        for(Users users:listUsers){
            UsersDto usersDto= new UsersDto();
            usersDto.setId(users.getId());
            usersDto.setUserName(users.getUserName());
            usersDto.setFullname(users.getFullname());
            usersDto.setPassword(users.getPassword());

            usersDtoList.add(usersDto);
        }
        return usersDtoList;
    }
    @Override
    public boolean checklogin(String username, String password){
        Users users = userInterface.findByUserName(username);
        return passwordEncoder.matches(password,users.getPassword());
    }
    @Override
    public boolean addUser(SignUpRequest signUpRequest){

        Roles roles = new Roles();
        roles.setId(signUpRequest.getRoleid());
        Users users = new Users();
        users.setFullname(signUpRequest.getFullname());
        users.setUserName(signUpRequest.getEmail());
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try {
            userInterface.save(users);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
