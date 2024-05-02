package com.af.oshaeat.Service;

import com.af.oshaeat.Service.imp.UserServiceimp;
import com.af.oshaeat.dto.UsersDto;
import com.af.oshaeat.responsory.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceimp {
    @Autowired
    UserInterface userInterface;
    @Override
    public List<UsersDto> getAllUser() {
        List<Users> listUsers = userInterface.findAll();
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : listUsers) {
            UsersDto usersDto = new UsersDto();
            usersDto.setId(users.getId());
            usersDto.setUserName(users.getUserName());
            usersDto.setFullname(users.getFullname());
            usersDto.setPassword(users.getPassword());

            usersDtoList.add(usersDto);
        }

        return usersDtoList;
    }
}
