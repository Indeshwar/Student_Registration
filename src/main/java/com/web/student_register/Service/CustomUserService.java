package com.web.student_register.Service;

import com.web.student_register.Dto.UserDto;
import com.web.student_register.entity.Role;
import com.web.student_register.entity.User;
import com.web.student_register.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException(username + " does not exist!");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRoleToAuthority(user.getRoles())
        );
    }

    //Map to Role to Authority
    public Collection<? extends GrantedAuthority> mapRoleToAuthority(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }



    public User registerUser(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        log.info("UserName: ---->", userDto.getUserName());
        log.info("Password: ---->", userDto.getPassword());
        //add Role to the user
        Role role = new Role();
        role.setRoleName(userDto.getRoleName());
        user.getRoles().add(role);

        return userRepo.save(user);

    }
}
