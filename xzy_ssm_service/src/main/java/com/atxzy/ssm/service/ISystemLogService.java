package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.SysLog;

import java.util.List;

public interface ISystemLogService {


    void saveLog(SysLog sysLog);

    List<SysLog> findAll();
}
