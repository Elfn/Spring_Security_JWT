package com.springsec.demo.services;

import com.springsec.demo.entities.AppRole;
import com.springsec.demo.entities.AppUser;

import java.util.Collection;

/**
 * Created by Elimane on Feb, 2018, at 23:03
 */
public interface AccountService {

    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String rolename,String username);
    public AppUser findUserByUsername(String username);
    public Collection<AppUser> findAppUsers();

}
