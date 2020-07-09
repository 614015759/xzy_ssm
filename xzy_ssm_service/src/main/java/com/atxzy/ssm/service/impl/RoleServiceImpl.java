package com.atxzy.ssm.service.impl;

import com.atxzy.ssm.dao.IRoleDao;
import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.domain.Role;
import com.atxzy.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        Role role = roleDao.findById(roleId);
        return role;
    }

    @Override
    public List<Permission> findOtherPermissions(String id) {
        return roleDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }
}
