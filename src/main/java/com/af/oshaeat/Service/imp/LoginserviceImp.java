package com.af.oshaeat.Service.imp;

import com.af.oshaeat.dto.UsersDto;
import com.af.oshaeat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginserviceImp {
    List<UsersDto> getAllUser();
    boolean checklogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);
}
