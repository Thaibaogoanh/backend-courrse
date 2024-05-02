package com.af.oshaeat.security;

import com.af.oshaeat.responsory.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    UserInterface userInterface;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users users = userInterface.findByUserName(username);
        if(users == null)
        {
            throw new  UsernameNotFoundException("User can't exist");
        }
        return new User(username,users.getPassword(),new ArrayList<>());
    }
}
