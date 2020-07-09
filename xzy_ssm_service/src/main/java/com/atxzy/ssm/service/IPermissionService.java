package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll();

    void save(Permission permission);
}
