package cn.zengxiangyu.translate.utils;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtils {
    protected static Log logger = LogFactory.getLog(DateUtils.class);

    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    public static final String SHORT_DATE_FORMAT = "MM-dd";

    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    public static final int SUB_YEAR = Calendar.YEAR;

    public static final int SUB_MONTH = Calendar.MONTH;

    public static final int SUB_DAY = Calendar.DATE;

    public static final int SUB_HOUR = Calendar.HOUR;

    public static final int SUB_MINUTE = Calendar.MINUTE;

    public static final int SUB_SECOND = Calendar.SECOND;

    static final String dayNames[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday" };

    @SuppressWarnings("unused")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtils() {}

    /**
     * Converts a string that matches the date format to a date type
     *
     * @param dateStr
     * @return
     */
    public static java.util.Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }

    /**
     * Converts a string that matches the date format to a date type
     */
    public static java.util.Date stringtoDate(String dateStr, String format,
                                              ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    /**
     * Convert date to string
     *
     * @param date
     * @return
     */
    public static String dateToString(java.util.Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    /**
     * Get the specified format of the current time
     *
     * @param format
     * @return
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

    /**
     *
     * @param dateStr
     * @param amount
     * @return
     */
    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), FORMAT_ONE);
    }

    /**
     * Subtract two dates
     *
     * @param firstTime
     * @param secTime
     * @return Subtracted seconds
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * Get the number of days in a month
     *
     * @param year
     *          int
     * @param month
     *          int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * Get the number of days in a year and month
     *
     * @param year
     *          int
     * @param month
     *          int Month[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get current date
     *
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * Get the current month
     *
     * @return int
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Get the current year
     *
     * @return int
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Day of return date
     *
     * @param date
     *          Date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * Year to return date
     *
     * @param date
     *          Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Month to return date，1-12
     *
     * @param date
     *          Date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Calculate the number of days between two dates,
     * if date2> date1 returns a positive number, otherwise returns a negative number
     *
     * @param date1
     *          Date
     * @param date2
     *          Date
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * Compare the difference between two dates
     *
     * @param befor
     * @param after
     * @return
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * Compare the difference between the specified date and the current date
     *
     * @param befor
     * @param after
     * @return
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }

    /**
     * Compare the difference between the specified date and the current date
     * @param before
     * @return
     */
    public static long dayDiffCurr(String before) {
        Date currDate = DateUtils.stringtoDate(currDay(), LONG_DATE_FORMAT);
        Date beforeDate = stringtoDate(before, LONG_DATE_FORMAT);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;

    }

    /**
     * Get the first week of the month
     * @param year
     * @param month
     * @return
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // Sunday is the first day
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get the last week of every month
     * @param year
     * @param month
     * @return
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // Sunday is the first day
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get current date string, format "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }

    /**
     * Get constellation based on birthday
     *
     * @param birth
     *          YYYY-mm-dd
     * @return
     */
    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1,
                birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "Capricorn Aquarius Pisces Sheep Taurus Gemini Cancer Lion Virgo Libra Scorpio Archer Capricorn";
        int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "Constellation";
    }

    /**
     * Determine whether the date is valid, including leap year
     *
     * @param date
     *          YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * Get the date after months past the specified date (when months is negative, it means before the specified month);
     *
     * @param date
     *          When the date is null, it means the day
     * @param month
     *          Number of months added (subtracted)
     */
    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * Get the date after the specified day passes by day (when day is negative, it means before the date);
     *
     * @param date
     *          When the date is null, it means the day
     * @param month
     *          Number of months added (subtracted)
     */
    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    /**
     * Get the date from today's day
     * @param day
     * @param format
     * @return
     * @author chenyz
     */
    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return dateToString(cal.getTime(), format);
    }

    /**
     * Get the date after the specified day passes the week of
     * the day (when day is negative, it means before the specified month)
     *
     * @param date
     *          When the date is null, it means the day
     */
    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * Get the current date (yyyy-MM-dd)
     */
    public static String currDay() {
        return DateUtils.dateToString(new Date(), DateUtils.LONG_DATE_FORMAT);
    }

    /**
     * Get yesterday's date
     *
     * @return
     */
    public static String befoDay() {
        return befoDay(DateUtils.LONG_DATE_FORMAT);
    }

    /**
     * Get yesterday's date according to time type
     * @param format
     * @return
     * @author chenyz
     */
    public static String befoDay(String format) {
        return DateUtils.dateToString(DateUtils.nextDay(new Date(), -1), format);
    }

    /**
     * Get tomorrow's date
     */
    public static String afterDay() {
        return DateUtils.dateToString(DateUtils.nextDay(new Date(), 1),
                DateUtils.LONG_DATE_FORMAT);
    }

    /**
     * Get the number of days from the current time to 1900/1/1
     *
     * @return
     */
    public static int getDayNum() {
        int daynum = 0;
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
        return daynum;
    }

    /**
     * The inverse method of getDayNum (for processing date format data taken out by Excel, etc.)
     *
     * @param day
     * @return
     */
    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    /** For yyyy-MM-dd HH: mm: ss format, display yyyymmdd */
    public static String getYmdDateCN(String datestr) {
        if (datestr == null)
            return "";
        if (datestr.length() < 10)
            return "";
        StringBuffer buf = new StringBuffer();
        buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7))
                .append(datestr.substring(8, 10));
        return buf.toString();
    }

    /**
     * Get the first day of the month
     *
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return dateToString(cal.getTime(), format);
    }

    /**
     * Get the last day of the month
     *
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return dateToString(cal.getTime(), format);
    }







    /**
     * @desc: Obtain the system Timestamp, such as (2014-12-18 17: 35: 46.651)
     * @return Timestamp
     */
    public static Timestamp getCurrentSysTimestamp(){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        return d;
    }




    public static long getNowTimeStamp()
    {
        long stamp = 0L;
        Date date1 = new Date();
        Date date2 = null;
        try {
            date2 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse("1970/01/01 08:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        stamp = (date1.getTime() - date2.getTime()) / 1000L;
        return stamp;
    }


    public static long getNowTimeStampMs(){
        long stamp = 0L;
        Date date1 = new Date();
        Date date2 = null;
        try {
            date2 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse("1970/01/01 08:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        stamp = (date1.getTime() - date2.getTime());
        return stamp;
    }



    public static long getTimeStampByDate(Date date)
    {
        long stamp = 0L;
        Date date1 = date;
        Date date2 = null;
        try {
            date2 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse("1970/01/01 08:00:00");
            stamp = (date1.getTime() - date2.getTime()) / 1000L;
        } catch (Exception e) {
            stamp = 0L;
        }

        return stamp;
    }


    public static long getTimeStampMsByDate(Date date)
    {
        long stamp = 0L;
        Date date1 = date;
        Date date2 = null;
        try {
            date2 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse("1970/01/01 08:00:00");
            stamp = (date1.getTime() - date2.getTime());
        } catch (Exception e) {
            stamp = 0L;
        }

        return stamp;
    }



    public static String getYYYYByTimeStamp(Long second, String format)
    {
        if(second==null||second==0){
            return "";
        }
        Date da = null;
        try {
            da = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("1970-01-01 08:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(da.getTime() + second * 1000L);
        return (new SimpleDateFormat(format)).format(date);
    }

    public static String getYYYYbyTimeStampMs(Long second, String format)
    {
        if(second==null||second==0){
            return "";
        }
        Date da = null;
        try {
            da = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("1970-01-01 08:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(da.getTime() + second );
        return (new SimpleDateFormat(format)).format(date);
    }





    public static Date getDateByTimeStamp(Long TimeStamp){
        return new Date(TimeStamp*1000);
    }


    public static Date getDateByTimeStampMs(Long TimeStampMs){
        return new Date(TimeStampMs);
    }








}