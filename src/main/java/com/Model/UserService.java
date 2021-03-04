package com.Model;

import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public boolean NewUser(User user)
    {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1 == null)
        {
            user.setRoles(Collections.singleton(Role.ADMIN));
            user.setActive(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public boolean FindUser(User user)
    {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1!=null)
        {
            return true;
        }
        return false;
    }

}
