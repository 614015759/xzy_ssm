package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.domain.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IRoleService {
    public List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermissions(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
