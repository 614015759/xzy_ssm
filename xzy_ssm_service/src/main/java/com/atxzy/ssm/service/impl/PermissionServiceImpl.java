package com.atxzy.ssm.service.impl;

import com.atxzy.ssm.dao.IPermissionDao;
import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(){
        List<Permission> permissions = permissionDao.findAll();
        return permissions;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

}
