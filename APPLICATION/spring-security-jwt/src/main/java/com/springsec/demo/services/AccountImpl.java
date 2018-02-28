package com.springsec.demo.services;

import com.springsec.demo.dao.RoleRepository;
import com.springsec.demo.dao.UserRepository;
import com.springsec.demo.entities.AppRole;
import com.springsec.demo.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Elimane on Feb, 2018, at 23:06
 */
@Service
@Transactional//To say that each method of this class is a transaction with database
public class AccountImpl implements AccountService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashedPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPwd);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String rolename, String username) {

        AppUser currentUser = findUserByUsername(username);
        AppRole currentRole = roleRepository.findByRolename(rolename);

        currentUser.getAppRoles().add(currentRole);

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<AppUser> findAppUsers() {
        return userRepository.findAll();
    }
}
