package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/7/25 - 9:42
 * 日期工具类
 */
public class DateUtils {

    /**
     * 日期转为字符串
     * @param date
     * @param patter 格式
     * @return
     */
    public static String date2String(Date date, String patter){
        SimpleDateFormat dateFormat = new SimpleDateFormat(patter);
        return  dateFormat.format(date);
    }

    /**
     * 字符串转为日期
     * @param str
     * @param pattern
     * @return
     */
    public static Date string2Date(String str, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
