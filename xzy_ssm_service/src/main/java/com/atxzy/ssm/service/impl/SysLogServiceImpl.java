package com.atxzy.ssm.service.impl;

import com.atxzy.ssm.dao.ISysLogDao;
import com.atxzy.ssm.domain.SysLog;
import com.atxzy.ssm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("systemLogService")
@Transactional
public class SysLogServiceImpl implements ISystemLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        List<SysLog> sysLogs = sysLogDao.findAll();
        return sysLogs;
    }
}
