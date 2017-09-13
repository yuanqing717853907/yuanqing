package com.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期工具类
 * 
 * @author xxx
 * 
 */
public class VeDate {

    private static final Log log = LogFactory.getLog(VeDate.class);
    
    private static String[] m_nMonth = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

    /** 
     * 得到现在小时
     * 
     * @return 小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 两个时间之间的天数
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return 天数
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 两个时间之间的天数
     * @param date1
     *            时间1 yyyy-MM-dd HH:mm:ss
     * @param date2
     *            时间2 yyyy-MM-dd HH:mm:ss
     * @return 天数
     */
    public static long get2Days(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }
    
    /**
     * 获取两个时间间隔的小时数
     * <功能详细描述>
     * @param startDate
     * @param enDate
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int get2Hour(Date startDate,Date enDate ){
        if(startDate == null || enDate == null){
            return 0;
        }
        return (int)(startDate.getTime() - enDate.getTime())/(1000*60*60);
    }
    
    /**
     * 返回日期的星期
     * 
     * @param sdate
     *            日期
     * @return 星期
     */
    public static String getWeekXq(String sdate) {
        int w = getWeekNum(sdate);
        String s = "";
        switch (w) {
        case 0:
            s = "星期天";
            break;
        case 1:
            s = "星期一";
            break;
        case 2:
            s = "星期二";
            break;
        case 3:
            s = "星期三";
            break;
        case 4:
            s = "星期四";
            break;
        case 5:
            s = "星期五";
            break;
        case 6:
            s = "星期六";
            break;

        }
        return s;
    }

    /**
     * 返回日期的周数
     * 
     * @param sdate
     *            日期
     * @return 周数
     */
    public static String getWeekZhou(String sdate) {
        int w = getWeekNum(sdate);
        String s = "";
        switch (w) {
        case 0:
            s = "周日";
            break;
        case 1:
            s = "周一";
            break;
        case 2:
            s = "周二";
            break;
        case 3:
            s = "周三";
            break;
        case 4:
            s = "周四";
            break;
        case 5:
            s = "周五";
            break;
        case 6:
            s = "周六";
            break;

        }
        return s;
    }

    /**
     * 返回数字星期 星期天是0 星期一是1
     * 
     * @param sdate
     *            日期
     * @return 星期
     */
    public static int getWeekNum(String sdate) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(strToDate(sdate));
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     * 
     * @param nowdate
     *            现在日期
     * @param delay
     *            delay为前移或后延的天数
     * @return 日期
     */
    public static String getNextDay(String nowdate, String delay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = "";
        Date d = null;
        if (nowdate == null || "".equals(nowdate)) {
            d = new Date();
        } else {
            d = strToDate(nowdate);
        }

        long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        return mdate;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     * 
     * @param nowdate
     *            现在日期
     * @param delay
     *            为前移或后延的天数
     * @return 日期
     */
    public static String getNextDay(String nowdate, int delay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = "";
        Date d = strToDate(nowdate);
        long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        return mdate;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     *            现在日期
     * @param hour
     *            为前移或后延的天数
     * @return 日期
     */
    public static String getNextHour(String nowdate, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String mdate = "";
        Date d = strToDateLong(nowdate);
        long myTime = (d.getTime() / 1000) + hour* 60 * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        return mdate;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     *            日期
     * @return 日期
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 指定多少秒前或后的时间
     * 
     * @param sjzl
     *            秒数
     * @return 时间
     */
    public static String getNextDayTime(int sjzl) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        long myTime = (currentTime.getTime() / 1000) + sjzl;
        currentTime.setTime(myTime * 1000);
        return format.format(currentTime);
    }

    /**
     * 指定时间之后或者之前的多少秒的时间
     * 
     * @param cutt
     *            时间
     * @param sjzl
     *            大于0是往后多少秒，小于0是往前多少秒
     * @return 时间
     */
    public static Date getNextDayTime(Date cutt, int sjzl) {

        Date currentTime = cutt == null ? new Date() : cutt;
        long myTime = (currentTime.getTime() / 1000) + sjzl;
        currentTime.setTime(myTime * 1000);
        return currentTime;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     *            时间
     * @return 时间
     */
    public static Date strToDateLong(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将指定格式的字符串转为时间
     * 
     * @param strDate
     *            时间
     * @param format
     *            格式
     * @return 时间
     */
    public static Date strToDateLong(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     * 
     * @return String 时间
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如yyyyMMdd，注意字母y不能大写。
     * 
     * @param sformat
     *            yyyyMMddHHmmss
     * @return 时间
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 转为中文格式显示
     * 
     * @param strDate
     *            时间
     * @return String 时间
     */
    public static String getStrDateFormat(String strDate) {
        Date date = strToDateLong(strDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 相差天数
     * 
     * @param sDate1
     *            时间
     * @param sDate2
     *            时间
     * @return int 天数
     */
    public static int DateDiff(String sDate1, String sDate2) { // sDate1和sDate2是2002-12-18格式
        String[] aDate;
        Integer iDays;
        aDate = sDate1.split("-");
        Integer jsrq = Integer.valueOf(aDate[0]) * 31 * 24 + Integer.valueOf(aDate[1]) * 31 + Integer.valueOf(aDate[2]); // 转换为12-18-2002格式
        aDate = sDate2.split("-");
        Integer ksrq = Integer.valueOf(aDate[0]) * 31 * 24 + Integer.valueOf(aDate[1]) * 31 + Integer.valueOf(aDate[2]);
        iDays = jsrq - ksrq; // 把相差天数
        return iDays;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     * 
     * 表示是取几位随机数，可以自己定
     */

    private static String randString = "";

    /**
     * 保证一次产生10000个随机数内无重复 平均一秒钟能产生250个随机数
     * 
     * @param k
     *            k
     * @return String String
     */
    public synchronized static String getNo(int k) {
        if (randString.length() > 20000) {
            randString = "";
            System.out.println("清理randString");
        }
        String rno = getNoNo(k);
        while (randString.indexOf(rno + ",") >= 0) {
            rno = getNoNo(k);
        }
        randString += rno + ",";
        return rno;
    }

    /**
     * @param k
     *            k
     * @return String String
     */
    private static String getNoNo(int k) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getUserDate("yyMMddHHmmss") + RandomStringUtils.randomNumeric(k);
    }

    /**
     * 返回一个随机数
     * 
     * @param i
     *            i
     * @return string
     */
    public static String getRandom(int i) {
        Random jjj = new Random();

        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     * 
     * @param length
     *            int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 得到二个日期间的间隔天数
     * 
     * @param sj1
     *            sj1
     * @param sj2
     *            sj2
     * @return String String
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 得到二个日期间的间隔分钟
     * 
     * @param sj1
     *            sj1
     * @param sj2
     *            sj2
     * @return String String
     */
    public static String getTwoMil(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 得到二个日期间的间隔秒
     * 
     * @param sj1
     *            sj1
     * @param sj2
     *            sj2
     * @return String String
     */
    public static String getTwoSecond(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     * 
     * @param sj1
     *            sj1
     * @param jj
     *            jj
     * @return String String
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 获取一个月的最后一天
     * 
     * @param dat
     *            dat
     * @return String String
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 7);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "-31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "-30";
        } else {
            if (isLeapYear(dat)) {
                str += "-29";
            } else {
                str += "-28";
            }
        }
        return str;
    }
    
    /**
     * @category 返回指定日期的第一天 日期为yyyy-MM-dd格式
     * @param dat 日期为yyyy-MM-dd格式
     * @return date yyyy-MM-dd格式
     */
    public static String getFirstDateOfMonth(String dat){
        if(StringUtils.isBlank(dat)){
            return null;
        }
        if(dat.length() < 7){
            return null;
        }
        String str = dat.substring(0, 7);
        return str + "-01";
    }
    
    /**
     * @category 返回指定日期所在月份的某天，包括大小月判断，如果指定月的天数小于day，则返回最后一天 日期为yyyy-MM-dd格式
     * @param dat 日期为yyyy-MM-dd格式
     * @return date yyyy-MM-dd格式
     */
    public static String getTheDateOfMonth(String dat,int day){
        if(StringUtils.isBlank(dat)){
            return null;
        }
        if(dat.length() < 7){
            return null;
        }
        String str = dat.substring(0, 7);
        String month = dat.substring(5, 7);
        int maxDay = 31;
        int mon = Integer.parseInt(month);
         if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            maxDay = 30;
        } else {
            if (isLeapYear(dat)) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        }
        if(day < 1){
            day = 1;
        }
        if(day > maxDay){
            day = maxDay;
        }
        if(String.valueOf(day).length() == 1){
            return str + "-0" + day;
        }else{
            return str + "-" + day;
        }
    }
    
    /**
     * 判断是否润年
     * 
     * @param ddate
     *            ddate
     * @return boolean boolean
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 取得指定月份的第一天
     * 
     * @param dat
     *            dat
     * @return String String
     */
    public static String getMonthBegin(String dat) {
        String str = dat.substring(0, 8);
        return str + "01";
    }

    /**
     * 时间前推或后推秒钟,其中JJ表示秒钟.
     * 
     * @param sj1
     *            sj1
     * @param jj
     *            jj
     * @return String String
     */
    public static String getPreTimesec(String sj1, int jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + jj;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 日期转字符串
     * 
     * @param d
     *            日期
     * @param format
     *            转换格式 "yyyy-MM-dd HH:mm:ss"
     * @return 日期
     */
    public static String Date2Str(Date d, String format) {
        if (null == d) {
            // d = new Date();
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (d == null) {
            d = new Date();
        }
        java.sql.Date sDate = new java.sql.Date(d.getTime());
        String strDate = df.format(sDate);
        return strDate;
    }

    /**
     * 把一位月日时分秒格式化为2位
     * 
     * @param date
     *            date
     * @return String String
     */
    public static String fmtDate(String date) {
        if (StringUtils.isBlank(date)) {
            return "";
        }
        String str = "";
        String[] yyhh = date.split(" ");
        if (yyhh.length > 0) {// yyyy-mm-dd
            String[] yymmddsz = yyhh[0].split("-");
            for (String s : yymmddsz) {
                if (s.length() == 1) {
                    s = "0" + s;
                }
                if ("".equals(str)) {
                    str = s;
                } else {
                    str += "-" + s;
                }
            }
        }

        if (yyhh.length > 1) {// hh:mm:ss
            str += " ";
            String[] hhmmsssz = yyhh[1].split(":");
            for (int i = 0; i < hhmmsssz.length; i++) {
                String s = hhmmsssz[i];
                if (s.length() == 1) {
                    s = "0" + s;
                }
                if (i == 0) {
                    str += s;
                } else {
                    str += ":" + s;
                }
            }
        }
        return str;
    }

    /**
     * 酒店日期处理
     * 
     * @param string
     *            string
     * @param oldString
     *            oldString
     * @param newString
     *            newString
     * @return String String
     */
    public static String hotelDate(String string, String oldString, String newString) {
        String strString = "";
        if (StringUtils.isNotBlank(string)) {
            strString = string.replace(oldString, newString);
            if ("1900-01-01 00:00:00".equals(strString) || "0001-01-01 00:00:00".equals(strString)) {
                return "";
            }
        }
        return strString;
    }

    /**
     * @category 补齐开始日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
     * @param date
     *            要转换的值
     * @return String String
     */
    public static Date startDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * @category 去掉毫秒.使其格式为YYYY-MM-DD HH24:MI:SS 
     * @param date
     *            要转换的值
     * @return date 
     */
    public static Date delHmTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }
    
    /**
     * @category 补齐结束日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
     * @param date
     *            要转换的值
     * @return String
     */
    public static Date endDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 补齐日期的到秒
     * 
     * @param date
     *            date
     * @param hour
     *            小时
     * @param minute
     *            分钟
     * @param second
     *            秒
     * @param millisecond
     *            毫秒
     * @return Date Date
     */
    public static Date dateTime(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * @category 返回sqlDate日期格式
     * @param date
     *            date
     * @param isLDate
     *            true时返回日期格式是带有时分秒的
     * @return Date Date
     */
    public static Date convert(Date date, boolean isLDate) {

        if (date == null) {
            return null;
        }
        if (isLDate) {
            return new Timestamp(date.getTime());
        } else {
            return new java.sql.Date(date.getTime());
        }
    }

    /**
     * @category 返回sqlDate日期格式
     * @param strdate
     *            strdate
     * @param isLDate
     *            true时返回日期格式是带有时分秒的
     * @return Date Date
     */
    public static Date convert(String strdate, boolean isLDate) {
        Date date = strToDate(strdate);
        return convert(date, isLDate);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param pattern
     *            pattern
     * @param date
     *            date
     * @return String String
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 获取现在时间
     * 
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
//      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      String dateString = formatter.format(currentTime);
//      ParsePosition pos = new ParsePosition(8);//这个8不对
//      Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime;
    }

    /**
     * 获取现在时间
     * 
     * @return yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 得到当前日期00:00:00 返回格式 yyyy-MM-dd 00:00:00
     * 
     * @return Date date
     * @see [类、类#方法、类#成员]
     */
    public static Date getNowDateLong() {
        String currentDate = VeDate.getStringDateShort();
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(currentDate + " 00:00:00", pos);
    }

    /**
     * 验证日期格式 yyyy-mm-dd
     * 
     * @param strDate
     *            strDate
     * @return boolean [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isDate(String strDate) {
        return strDate.matches("((((19|20)\\d{2})-(0?[13578]|1[02])-"
                + "(0?[1-9]|[12]\\d|3[01]))|(((19|20)\\d{2})-(0?[469]|11)-"
                + "(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-9])))");

    }
    /**
     * 验证日期时间格式
     * 
     * @param datetime
     *            yyyy-mm-dd hh:mm:ss
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws
     *                [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isDatetime(String datetime) {
        return datetime.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?"
                + "((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|"
                + "(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|"
                + "(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]"
                + "((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])"
                + "((\\s)|(\\:([0-5]?[0-9])))))$");
    }
    /**
     * 
     * @param sql
     *            sql
     * @param params
     *            params
     * @param start
     *            start
     * @param count
     *            count
     * @return String String
     */
    @SuppressWarnings("unchecked")
    public static String debugSql(String sql, Collection params, int start, int count) {
        if (params != null) {
            Iterator iter = params.iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                String p = "";
                if (key instanceof java.lang.String) {
                    p = "'" + key.toString() + "'";
                } else if (key instanceof Date) {
                    p = "to_date('" + key.toString() + "','yyyy-mm-dd hh24:mi:ss')";
                } else if (key instanceof Timestamp) {
                    p = "to_date('" + key.toString() + "','yyyy-mm-dd hh24:mi:ss')";
                } else {
                    p = key == null ? "null" : key.toString();
                }
                sql = StringUtils.replaceOnce(sql, "?", p);
            }
        }
        if (count > 0) {
            StringBuffer sb = new StringBuffer("select b_table.* from (select a_table.*,rownum as linenum from");
            sb.append("(").append(sql).append(" ) a_table where rownum <= ").append(start + count)
                    .append(") b_table where linenum >").append(start);
            sql = sb.toString();
        }

        System.out.println(sql);
        return sql;
    }

    /**
     * 得到指定时间和当前时间的间隔分钟
     * 
     * @param date
     *            date
     * @return String String
     */
    public static String getMilForDate(Date date) {
        long day = 0;
        try {
            Date dt = new Date();
            day = (date.getTime() - dt.getTime()) / (60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * Date时间前推或后推分钟,其中JJ表示分钟.
     * 
     * @param date
     *            date
     * @param jj
     *            jj
     * @return Date Date
     */
    public static Date getPreTimeForDate(Date date, String jj) {
        try {
            long Time = (date.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date.setTime(Time * 1000);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 判断一个给定的日期字符串是否符合给定的格式，并且是有效的日期
     * 
     * @param date
     *            给定的日期字符串
     * @param format
     *            要求的日期格式
     * @return boolean 如果符合格式并且是有效的日期返回true 否则返回false
     * @see [类、类#方法、类#成员]
     */
    public static boolean isRightDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            if (sdf.format(sdf.parse(date)).equalsIgnoreCase(date))
                return true;
            else
                return false;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 格式化日期
     * 
     * @param dateStr
     *            需要格式化日期
     * @param formatStr
     *            格式，例如：yyyy-MM-dd
     * @return String String
     */
    public static String dateFormat(String dateStr, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String date = "";
        Date d = null;
        try {
            d = strToDate(dateStr);
            date = format.format(d);
        } catch (Exception e) {
            date = "";
        }
        if (StringUtils.isEmpty(date) && dateStr.indexOf("-") == -1 && dateStr.indexOf("/") == -1
                && dateStr.length() >= 8) {
            String year = dateStr.substring(0, 4);
            String monty = dateStr.substring(4, 6);
            String day = dateStr.substring(6, 8);
            dateStr = year + "-" + monty + "-" + day;
            d = strToDate(dateStr);
            date = format.format(d);
        }
        return date;
    }

    /**
     * 用于返回指定日期格式的日期增加指定天数的日期
     * 
     * @param appDate
     *            指定日期
     * @param format
     *            指定日期格式yyyy-MM-dd
     * @param days
     *            指定天数
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static String getFutureDay(String appDate, String format, int days) {
        String future = "";
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        } catch (Exception e) {
        }
        return future;
    }

    /**
     * 返回英文星期
     * 
     * @param sdate
     *            sdate
     * @return String String
     */
    public static String getWeekEn(String sdate) {
        Date date = VeDate.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return new SimpleDateFormat("EEE", Locale.ENGLISH).format(c.getTime());
    }

    /**
     * 
     * @param sdate
     *            sdate
     * @return Date Date
     */
    public static Date parseDate(String sdate) {
        try {
            return DateUtils.parseDate(sdate, new String[] { "yyyy-MM-dd HH:mm:ss" });
        } catch (ParseException e) {
            log.error(e, e);
        }
        return null;
    }

    /**
     * 获取当前时间
     * 
     * @return String [HH:mm]
     */
    public static String getNowTime() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     * 
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 取传入日期的零时零分零秒
     * 
     * @param date
     *            为空时表时当前时间
     * 
     * @return Date [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getZeroByDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 取传入日期的23:59:59时间 
     * 
     * @param date
     *            为空时表时当前时间
     * 
     * @return Date [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getDateBeforeNext(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 
     * @param time
     *            time
     * @return Date Date
     */
    public static Date timeToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String d = format.format(time);

        try {
            Date date = format.parse(d);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @category 获得传入日期的本周星期一的日期
     * @param date
     *            传入日期
     * @return 返回日期
     */
    public static String getMondayDate(String date) {
        String monday = "";
        String strFormat = "yyyy-MM-dd";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.CHINA);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTimeInMillis(strToDateLong(date, strFormat).getTime());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            monday = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monday;
    }

    /**
     * @category 获取传入日期的 前几个月/后几个月 yyyy-MM-dd
     * @param date
     *            传入日期
     * @param i
     *            相隔月数 正数:后几个月 负数:前几个月
     * @return 返回日期yyyy-MM-dd
     */
    public static String getNextMonthDate(String date, int i) {
        String preMonthDate = "";
        String strFormat = "yyyy-MM-dd";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.CHINA);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTimeInMillis(strToDateLong(date, strFormat).getTime());
            cal.add(Calendar.MONTH, i);
            preMonthDate = sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preMonthDate;
    }

    /**
     * Date转Calendar
     * 
     * @param date Date
     * 
     * @return Calendar Calendar
     * @see [类、类#方法、类#成员]
     */
    public static Calendar date2Calendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
    /** 给定两个时间相差的月数,String版* */
    public static int monthsBetween(String pLatterStr, String pFormerStr) {
        GregorianCalendar vFormer = parse2Cal(pFormerStr);
        GregorianCalendar vLatter = parse2Cal(pLatterStr);
        return monthsBetween(vLatter, vFormer);
    }
    /**
     * 取得指定日期的下一个月
     * 
     *
     *            指定日期。
     * @return 指定日期的下几个月
     */
    public static String getNextMonth(String dates, int count) {
        Date date = strToDate(dates);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MONTH, count);

        return dateToStr(gc.getTime());
    }
    public static int monthsBetween(GregorianCalendar pLatter, GregorianCalendar pFormer) {
        GregorianCalendar vFormer = pFormer, vLatter = pLatter;
        boolean vPositive = true;
        if (pFormer.before(pLatter)) {
            vFormer = pFormer;
            vLatter = pLatter;
        } else {
            vFormer = pLatter;
            vLatter = pFormer;
            vPositive = false;
        }

        int vCounter = 0;
        while (vFormer.get(Calendar.YEAR) != vLatter.get(Calendar.YEAR)
                || vFormer.get(Calendar.MONTH) != vLatter.get(Calendar.MONTH)) {
            vFormer.add(Calendar.MONTH, 1);
            vCounter++;
        }
        if (vPositive)
            return vCounter;
        else
            return -vCounter;
    }

    /** 将字符串格式的日期转换为Calender* */
    public static GregorianCalendar parse2Cal(String pDateStr) {
        StringTokenizer sToken;
        sToken = new StringTokenizer(pDateStr, "-");
        int vYear = Integer.parseInt(sToken.nextToken());
        // GregorianCalendar的月份是从0开始算起的，变态！！
        int vMonth = Integer.parseInt(sToken.nextToken()) - 1;
        int vDayOfMonth = Integer.parseInt(sToken.nextToken());
        return new GregorianCalendar(vYear, vMonth, vDayOfMonth);
    }
    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     *
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串
     *
     * @param dateDate
     *
     * @return
     */
    public static String dateToStr(Date dateDate,String type) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static int getAge(String birthday) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        Date csrq = VeDate.strToDate(birthday);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(csrq);

        return calendar.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
    }
    
    /**
     * 转成指令日期格式
     * @param date 13SEP
     * @param year 15
     * @return 2015-09-13
     */
    public static String commandDate(String date, String year) {
        String month = date.substring(2);
        String day = date.substring(0, 2);
        if (month.equals("JAN")) {
            month = "01";
        } else if (month.equals("FEB")) {
            month = "02";
        } else if (month.equals("MAR")) {
            month = "03";
        } else if (month.equals("APR")) {
            month = "04";
        } else if (month.equals("MAY")) {
            month = "05";
        } else if (month.equals("JUN")) {
            month = "06";
        } else if (month.equals("JUL")) {
            month = "07";
        } else if (month.equals("AUG")) {
            month = "08";
        } else if (month.equals("SEP")) {
            month = "09";
        } else if (month.equals("OCT")) {
            month = "10";
        } else if (month.equals("NOV")) {
            month = "11";
        } else if (month.equals("DEC")) {
            month = "12";
        }
        String y = VeDate.getStringDateShort();

        if (StringUtils.isBlank(year)) {
            return y.substring(0, 4) + "-" + month + "-" + day;
        } else {
            y = y.substring(0, 2);
            return y + year + "-" + month + "-" + day;
        }
    }

    public static String getId(){
        String str = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        String str2[] = str.split(",");//将字符串以,分割
        Random rand = new Random();//创建Random类的对象rand
        int index = 0;
        String randStr = "";//创建内容为空字符串对象randStr
        for (int i=0; i<4; ++i){
            index = rand.nextInt(str2.length-1);//在0到str2.length-1生成一个伪随机数赋值给index
            randStr += str2[index];//将对应索引的数组与randStr的变量值相连接
        }
        String sj = VeDate.getStringDateShort().replace("-","");
        String id = sj+randStr;
        return id;
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为M）
     * 参数为String类型
     * @param lat1Str 用户经度
     * @param lng1Str 用户纬度
     * @param lat2Str 商家经度
     * @param lng2Str 商家纬度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (lng1 - lng2) * Math.PI / 180.0;
        String d="";
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2*R* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)* Math.cos(lat2) * sb2 * sb2))+"";
        d = d.substring(0, d.indexOf("."));
        return d;
    }

    /**
     * 计算两点之间距离
     *
     * @param _lat1 - start纬度
     * @param _lon1 - start经度
     * @param _lat2 - end纬度
     * @param _lon2 - end经度
     * @return km(四舍五入)
     */
    public static double distance(double _lat1, double _lon1, double _lat2, double _lon2) {
        //转化为弧度
        double lat1 = (Math.PI / 180) * _lat1;
        double lon1 = (Math.PI / 180) * _lon1;
        double lat2 = (Math.PI / 180) * _lat2;
        double lon2 = (Math.PI / 180) * _lon2;
        double R = 6378.1;//地球半径
        double d = 0.0;
        if (lon2 != lon1){
            d= Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        }
        return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     *比较两个时间的大小
     * @param date1
     * @param date2
     * @param type
     * @return
     */
    public static int compareDate(String date1, String date2,String type) {
        DateFormat df = new SimpleDateFormat(type);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                //dt1比dt2大
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //dt1比dt2小
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回指定格式的日期字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String formatDate(Date date,String formatStr){
    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
    	 return simpleDateFormat.format(date);
    }
    /**
     * 获取两个时间的时间差，返回包括：天，小时，分钟，秒
     * 相差值为正数，不区分时间前后
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static long[] getTwoDateDifference(Date beforeDate,Date afterDate){
         long day  = 0;
         long hour = 0;
         long min  = 0;
         long sec  = 0;
         try {
             long time1 = beforeDate.getTime();
             long time2 = afterDate.getTime();
             long diff ;
             if(time1<time2) {
                 diff = time2 - time1;
             } else {
                 diff = time1 - time2;
             }
             day = diff / (24 * 60 * 60 * 1000);
             hour = (diff / (60 * 60 * 1000) - day * 24);
             min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
             sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
         } catch (Exception e) {
             e.printStackTrace();
         }
         long[] times = {day, hour, min, sec};
         return times;
    }
    
    
    
	/**
	 * zz 2012-09-19 将yyyy-MM-dd日期转换成
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return 19SEP12
	 */
	public static String dateCommandTime(String date) {

		if (date == null || date.length() != 10 || !VeDate.isRightDate(date, "yyyy-MM-dd")) {
			return null;
		}
		String nmonth = date.substring(5, 7); // 获取月份：09
		String nDate = date.substring(8, 10); // 获取日期：13
		String nyear = date.substring(0, 4); // 获取年份
		if (StringUtils.isNotBlank(nyear)) {
			if (nyear.length() == 4)
				nyear = nyear.substring(2, 4); // 获取年份的后两位:07
			// 将月和日期转换成13SEP:13是日期,SEP是月份,m_nMonth是个的数组包含了日期字符串，
			// 该类的最上面
			String EnMonth = m_nMonth[Integer.parseInt(nmonth) - 1];
			return nDate + EnMonth + nyear;
		}

		return null;
	}

    /**
     * 当前时间是否是当天凌晨0-6点之间
     * @param rzrq
     * @return
     */
    public static boolean inTodayMidNight(){
        try{
            String currHourStr = VeDate.getHour();
            int currHour = StringUtils.isNotBlank(currHourStr) ? Integer.parseInt(currHourStr) : -1;
            if(currHour>=0 && currHour<6){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
	    long t =VeDate.getDays("2017-07-29",VeDate.getStringDateShort());
	    System.out.println(t);
    }


}