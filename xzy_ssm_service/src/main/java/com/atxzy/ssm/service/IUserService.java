package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.Role;
import com.atxzy.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String userId);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] roleIds);
}
