package org.framework.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：JAVA日期时间函数包
 * 日期数据获取与转换、
 * 关于时间的相关方法
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-09 19:04
 */
public class DateTimeUtil {

    /**
     * @Description: 按指定格式格式化当前时间
     * @Date: 19:12 2019/5/9
     * @Param: format: "yyyy-MM-dd HH:mm:ss"
     * @Return: 格式化后的当前时间（String）
     */
    public static String formatDate(String format) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            e.printStackTrace();
            return "Illegal date";
        }
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * @Description:按标准格式格式化指定时间
     * @Date: 19:14 2019/5/9
     * @Param: date:java.util.date; format:时间格式："yyyy-MM-dd HH:mm:ss"
     * @Return: 指定时间字符串
     */
    public static String formatDate(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = null;
            try {
                sdf = new SimpleDateFormat(format);
            } catch (Exception e) {
                e.printStackTrace();
                return "Illegal date";
            }
            return sdf.format(date);
        }
        return "";
    }

    /**
     * @Description:格式化时间中阿拉伯数字为中文数字
     * @Date: 19:22 2019/5/9
     * @Param date :     java.util.Date
     * @Param format :   时间格式
     * @Return:时间字符串
     */
    public static String toZhCNString(Date date, String format) {
        String strDate = "";
        if (date != null) {
            strDate = formatDate(date, format);
            String strUp[] = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"},
                    strTen[] = {"", "十", "二十", "三十"};
            String str = "";
            int nPos = 0;
            if (!"".equals(strDate)) {
                for (int i = 0; i < 10; i++) {
                    if (i < 4) {
                        str += strUp[Integer.parseInt(strDate.substring(i, i + 1))];
                    } else if (i > 4 && i != 7) {
                        nPos = Integer.parseInt(strDate.substring(i, i + 1));
                        str += strTen[nPos];
                        i++;
                        nPos = Integer.parseInt(strDate.substring(i, i + 1));
                        if (nPos > 0) str += strUp[nPos];
                    } else str += i == 4 ? "年" : "月";
                }
                strDate = str + "日";
            }
        }
        return strDate;
    }

    /**
     * @Description:解析时间字符串输出日期
     * @Date: 19:33 2019/5/9
     * @Param strDate: 时间字符串
     * @Param format: 时间格式
     * @Return:java.util.Date
     */
    public static Date parse(String strDate, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Description:取得日期 例如：2019-05-04
     * @Date: 19:38 2019/5/9
     * @Param:
     * @Return:时间字符串（类型: String）
     */
    public static String getDate() {
        return formatDate("yyyy-MM-dd");
    }

    /**
     * @Description:取得时间 例如：09:27:12
     * @Date: 19:40 2019/5/9
     * @Param:
     * @Return:时间字符串（类型: String）
     */
    public static String getTime() {
        return formatDate("HH:mm:ss");
    }

    /**
     * @Description:取得当前日期时间 例如：2019-05-09 09:27:12
     * @Date: 19:40 2019/5/9
     * @Param:
     * @Return:
     */
    public static String getDateTime() {
        return formatDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @Description:取得指定日期的星期几 例如：星期四
     * @Date: 19:41 2019/5/9
     * @Param date: 指定日期
     * @Return: 星期字符串
     */
    public static String getWeek(Date date) {
        String strDate = formatDate(date, "yyyy-MM-dd HH:mm:ss"), weekinfo = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(strDate.substring(0, 4)),
                Integer.parseInt(strDate.substring(5, 7)) - 1,
                Integer.parseInt(strDate.substring(8, 10)));
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                weekinfo = "星期日";
                break;
            case 2:
                weekinfo = "星期一";
                break;
            case 3:
                weekinfo = "星期二";
                break;
            case 4:
                weekinfo = "星期三";
                break;
            case 5:
                weekinfo = "星期四";
                break;
            case 6:
                weekinfo = "星期五";
                break;
            case 7:
                weekinfo = "星期六";
                break;
            default:
                weekinfo = "";
        }
        return weekinfo;
    }

    /**
     * @param field: Calendar对象中的字段类型
     * @Description:日期 加减操作，可操作
     * 年(Calendar.YEAR)、月(Calendar.MONTH)、周(WEEK_OF_YEAR)、日(Calendar.DAY_OF_YEAR)
     * @Date: 19:46 2019/5/9
     * @Param date:java.util.Date
     * @Param amount:相对Calendar对象指定类型的变化量
     * @Return:java.util.Date
     */
    public static Date calculate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * @param strDate : 操作的日期
     * @param format  : 日期格式字符串
     * @param field   : Calendar对象中的字段类型
     * @param amount  : 相对Calendar对象指定类型的变化量
     * @return java.lang.String
     * @Description:日期 加减操作，可操作
     * 年(Calendar.YEAR)、月(Calendar.MONTH)、日(Calendar.DAY_OF_YEAR)
     */
    public static String calculate(String strDate, String format, int field, int amount) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date current = sdf.parse(strDate);
            Date date = calculate(current, field, amount);
            result = sdf.format(date);
        } catch (Exception e) {
            result = strDate;
        }
        return result;
    }

    /**
     * @param date   : java.util.Date
     * @param amount : 年份变化的量
     * @Description:取得变化几年的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:07 2019/5/9
     * @Return:java.util.Date
     */
    public static Date yearDiff(Date date, int amount) {
        return calculate(date, Calendar.YEAR, amount);
    }

    /**
     * @param strDate : java.lang.String
     * @param format  : 日期格式字符串
     * @param amount  : 年份变化的量
     * @return Date
     * @Description:取得变化几年的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:07 2019/5/9
     */
    public static Date yearDiff(String strDate, String format, int amount) {
        return calculate(parse(strDate, format), Calendar.YEAR, amount);
    }

    /**
     * @param date   : java.util.Date
     * @param format : 日期格式字符串
     * @param amount : 年份变化的量
     * @return String Of Date
     * @Description:取得变化几年的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:08 2019/5/9
     */
    public static String yearDiffString(Date date, String format, int amount) {
        return formatDate(calculate(date, Calendar.YEAR, amount), format);
    }

    /**
     * @param strDate : java.lang.String
     * @param format  : 日期格式字符串
     * @param amount  : 年份变化的量
     * @return String Of Date
     * @Description:取得变化几年的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:10 2019/5/9
     */
    public static String yearDiffString(String strDate, String format, int amount) {
        return calculate(strDate, format, Calendar.YEAR, amount);
    }

    /**
     * @param date   : java.util.Date
     * @param amount : 月份 变化的量
     * @return Date
     * @Description:取得变化几月的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static Date monthDiff(Date date, int amount) {
        return calculate(date, Calendar.MONTH, amount);
    }

    /**
     * @param strDate : java.lang.String
     * @param format  : 日期格式字符串
     * @param amount  : 月份 变化的量
     * @return Date
     * @Description:取得变化几月的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static Date monthDiff(String strDate, String format, int amount) {
        return calculate(parse(strDate, format), Calendar.MONTH, amount);
    }

    /**
     * @param date   : java.util.Date
     * @param format : 日期格式字符串
     * @param amount : 月份 变化的量
     * @return String Of Date
     * @Description:取得变化几月的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static String monthDiffString(Date date, String format, int amount) {
        return formatDate(calculate(date, Calendar.MONTH, amount), format);
    }

    /**
     * @param strDate : java.lang.String
     * @param format  : 日期格式字符串
     * @param amount  : 月份 变化的量
     * @return String Of Date
     * @Description:取得变化几月的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static String monthDiffString(String strDate, String format, int amount) {
        return calculate(strDate, format, Calendar.MONTH, amount);
    }

    /**
     * @param date   : java.util.Date
     * @param amount : 日期变化的量
     * @return Date
     * @Description:取得变化几日的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static Date dayDiff(Date date, int amount) {
        return calculate(date, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * @param strDate : java.lang.String
     * @param format  : 日期格式字符串
     * @param amount  : 日期变化的量
     * @return Date
     * @Description:取得变化几日的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static Date dayDiff(String strDate, String format, int amount) {
        return calculate(parse(strDate, format), Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * @param date   : java.util.Date
     * @param format : 日期格式字符串
     * @param amount : 日期变化的量
     * @return String Of Date
     * @Description:取得变化几日的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static String dayDiffString(Date date, String format, int amount) {
        return formatDate(calculate(date, Calendar.DAY_OF_YEAR, amount), format);
    }

    /**
     * @param strDate : java.util.Date
     * @param format  : 日期格式字符串
     * @param amount  : 日期变化的量
     * @return String Of Date
     * @Description:取得变化几日的日期，例如date: 2007-01-30
     * 如果amount: 1 返回值2008-01-30，如果var: -1 返回值2006-01-31
     * @Date: 20:11 2019/5/9
     */
    public static String dayDiffString(String strDate, String format, int amount) {
        return calculate(strDate, format, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * @param startTime : 起始时间（java.util.date）
     * @param endTime   : 结束时间（java.util.date）
     * @return 时间差（单位: 秒）
     * @Description:计算时间差
     * @Date: 20:11 2019/5/9
     */
    public static long timeDiff(Date startTime, Date endTime) {
        return (startTime != null && endTime != null) ? (endTime.getTime() - startTime.getTime()) / 1000 : 0;
    }

    /**
     * @param beforeDate 格式:2019-06-20
     * @param afterDate  格式:2019-06-21
     * @Description:计算两个日期相隔的日子
     * @Date: 11:30 2019/5/10
     * @Return:
     */
    public static int dayDiff(String beforeDate, String afterDate) {
        int result = 0;
        try {
            String[] tt = beforeDate.split("-");
            Date firstDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));

            tt = afterDate.split("-");
            Date nextDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
            result = (int) (nextDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:当前时间所在一周的周一和周日时间
     * @Date: 20:32 2019/5/9
     * @Param:
     * @Return:
     */
    public static Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        map.put("mondayDate", sdf.format(cal.getTime()));//当前时间所在周星期一的日期

        cal.add(Calendar.DATE, 6);
        map.put("sundayDate", sdf.format(cal.getTime()));//当前时间所在周星期天的日期
        return map;
    }

    /**
     * @Description:获取当前月份
     * @Date: 11:37 2019/5/10
     * @Param:
     * @Return:返回字符串 格式：两位数
     */
    public static String getCurrentMonth() {
        String strmonth = null;
        try {
            Calendar cld = Calendar.getInstance();
            java.util.Date date = new Date();
            cld.setTime(date);
            int intMon = cld.get(Calendar.MONTH) + 1;
            if (intMon < 10) {
                strmonth = "0" + String.valueOf(intMon);
            } else {
                strmonth = String.valueOf(intMon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strmonth;
    }

    /**
     * @Description:获取本月第一天
     * @Date: 20:37 2019/5/9
     * @Param:
     * @Return:
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat format = null;
        String result = "";
        try {
            format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            result = format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:获取本月最后一天
     * @Date: 20:47 2019/5/9
     * @Param:
     * @Return:
     */
    public static String getMonthLastDay() {
        SimpleDateFormat format = null;
        String result = "";
        try {
            format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            result = format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:获取当前时间是一周中的第几天
     * @Date: 20:53 2019/5/9
     * @Param:
     * @Return:
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return w;
    }

    /**
     * @Description:获取当前时间是本年第几周
     * @Date: 20:53 2019/5/9
     * @Param:
     * @Return:
     */
    public static int getWeekOfYear() {
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return i;
    }

    /**
     * @param date2:当前时间
     * @Description:比较两个时间大小(只比较时分秒)
     * @Date: 20:54 2019/5/9
     * @Param date1: 指定时间
     * @Param format:指定时间格式：yyyy-MM-dd HH:mm:ss
     * @Return:
     */
    public static int compareDate(String date1, Date date2, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String now = sdf.format(date2);
        String now1 = now.split(" ")[1];
        return date1.compareTo(now1);
    }

    /**
     * @Description:获取当前日期所在周的每一天
     * @Date: 20:58 2019/5/9
     * @Param:
     * @Return:
     */
    public static String[] getDaysArrAtWeek(String date) throws ParseException {
        String[] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 1);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        for (int i = 0; i < 7; i++) {
            String nowDay = sdf.format(cal.getTime());
            arr[i] = nowDay;
            cal.add(Calendar.DATE, 1);
        }
        return arr;
    }

    /**
     * @Description:获取指定日期所在月的每一天
     * @Date: 21:00 2019/5/9
     * @Param:
     * @Return:
     */
    public static String[] getDaysArrAtMonth(String date) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int year = c.get(Calendar.YEAR);
        int month = Integer.parseInt(date.replace("-", "").substring(4, 6));
        c.set(year, month, 0);
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        String arr[] = new String[lastDay + 1];
        for (int x = 1; x <= lastDay; x++) {
            if (x < 10) {
                arr[x] = /*year+"-"+*/(date.substring(5, 7)) + "-0" + x;
            } else {
                arr[x] = /*year+"-"+*/(date.substring(5, 7)) + "-" + x;
            }
        }
        return arr;
    }

}
