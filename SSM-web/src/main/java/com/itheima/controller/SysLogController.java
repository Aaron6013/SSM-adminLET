package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/26 - 18:21
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAllLog(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAllLog(page,size);
        PageInfo logInfo = new PageInfo(sysLogs);
        modelAndView.addObject("logInfo",logInfo);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
