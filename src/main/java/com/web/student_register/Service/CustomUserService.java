package com.web.student_register.Service;

import com.web.student_register.Dto.UserDto;
import com.web.student_register.config.JWTTokenHelper;
import com.web.student_register.entity.Permission;
import com.web.student_register.entity.Role;
import com.web.student_register.entity.User;
import com.web.student_register.repository.PermissionRepo;
import com.web.student_register.repository.RoleRePo;
import com.web.student_register.repository.UserRepo;
import com.web.student_register.response.LogInResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CustomUserService implements UserDetailsService {
    private UserRepo userRepo;
    private AuthenticationManager authenticationManager;
    private JWTTokenHelper jwtTokenHelper;
    private PasswordEncoder passwordEncoder;
    private RoleRePo roleRePo;
    private PermissionRepo permissionRepo;

    @Lazy
    @Autowired
    public CustomUserService(UserRepo userRepo, AuthenticationManager authenticationManager, JWTTokenHelper jwtTokenHelper, PasswordEncoder passwordEncoder, RoleRePo roleRePo, PermissionRepo permissionRepo) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
        this.passwordEncoder = passwordEncoder;
        this.roleRePo = roleRePo;
        this.permissionRepo = permissionRepo;
    }


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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //add Role to the user
        Role role = roleRePo.getByRoleName(userDto.getRoleName());
        role.setRoleName(userDto.getRoleName());
        user.setRoles(Arrays.asList(role));

        return userRepo.save(user);

    }

    public LogInResponse userLogIn(UserDto userDto){

        //Authenticate the user
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));

        //Update authenticated user details in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token;
        LogInResponse response;
        try{
            response = new LogInResponse();
            token = jwtTokenHelper.generateToken(userDetails.getUsername());
            response.setToken(token);

        }catch(Exception e){
            token = null;
            response = null;

        }

        return response;
    }

    public Role createRole(String roleName, Set<Permission> permissions){
        Role role = roleRePo.getByRoleName(roleName);
        if(role == null){
            role = new Role();
            role.setRoleName(roleName);
            role.setPermissions(permissions);
            roleRePo.save(role);
        }
        return role;
    }

    public Permission createPermission(String permissionName){
        Permission permission = permissionRepo.getBypermissionName(permissionName);
        if(permission == null){
            permission = new Permission();
            permission.setPermissionName(permissionName);
            permissionRepo.save(permission);
        }
        return permission;
    }
}
