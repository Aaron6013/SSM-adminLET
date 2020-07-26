package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/26 - 12:53
 * 日志持久层
 */
public interface SysLogDao {

    /**
     * 保存日志
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    @Select("select * from syslog")
    List<SysLog> findAllLog();
}
