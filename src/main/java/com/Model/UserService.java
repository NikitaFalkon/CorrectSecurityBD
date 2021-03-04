package com.Model;

import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public boolean NewUser(User user)
    {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1 == null)
        {
            user.setRoles(Collections.singleton(Role.ADMIN));
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public boolean redactUser(String username, String password, long id)
    {
        User user = userRepository.findById(id).orElseThrow();
        if (user!=null)
        {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public List<User> allUsers()
    {
        return userRepository.findAll();
    }

    public boolean ExistById(long id)
    {
        return userRepository.existsById(id);
    }

    public Optional<User> FindById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
