package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/26 - 12:52
 * 日志业务层
 */
public interface SysLogService {

    /**
     * 保存日志
     * @param sysLog
     */
    void saveLog(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    List<SysLog> findAllLog(int page, int size);
}
