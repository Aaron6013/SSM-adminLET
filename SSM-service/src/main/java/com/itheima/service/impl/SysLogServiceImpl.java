package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/26 - 12:52
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAllLog(int page, int size) {
        PageHelper.startPage(page, size);
        return sysLogDao.findAllLog();
    }
}
