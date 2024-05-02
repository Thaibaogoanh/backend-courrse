package com.af.oshaeat.responsory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterface extends JpaRepository<Users, Integer> {
    List<Users> findByUserNameAndPassword(String UserName, String Password);
    Users findByUserName(String userName);
}
