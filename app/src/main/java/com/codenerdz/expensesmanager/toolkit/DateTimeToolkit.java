package com.codenerdz.expensesmanager.toolkit;

import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeToolkit
{
    private static class SingeltonHolder
    {
        public static DateTimeToolkit instance = new DateTimeToolkit();
    }

    private DateTimeToolkit()
    {

    }

    public static DateTimeToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    /**
     * Convert given string DATE value to long value.
     * @param date string date value
     * @return
     */
    public long convertDateStringToLong(String date)
    {
        SimpleDateFormat f = new SimpleDateFormat(EMConstantToolkit.DATE_FORMAT_PATTERN_1);
        return dateStringConversion(date,f);
    }

    /**
     * This method convert given date time value to long value.
     * @param date dateTime String value
     * @return
     */
    public long convertDateTimeStringTOLong(String date)
    {
        SimpleDateFormat f = new SimpleDateFormat(EMConstantToolkit.DATE_TIME_FORMAT_2);
        return dateStringConversion(date, f);
    }

    /**
     * Will be converged given string date/datetime value to long value based on the format.
     * @param date string date/datetime value.
     * @param format format for the given string date value.
     * @return
     */
    public long dateStringConversion(String date, SimpleDateFormat format)
    {
        long milliseconds = 0;
        try {
            Date d = format.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    /**
     * will be returned minimum milliseconds value and maximum milliseconds value for given date.
     * @param date string DATE value.
     * @return minimum milliseconds (midnight 12.00)
     * maximum milliseconds (midnight 11.59)
     */
    public long[] getMinMaxMillisecondsForGivenDate(String date)
    {
        long[] minMaxMilliseconds = new long[2];
        minMaxMilliseconds[0] = convertDateTimeStringTOLong(date+" 00:00:00");
        minMaxMilliseconds[1] = convertDateTimeStringTOLong(date+" 23:59:59");
        return minMaxMilliseconds;
    }

    /**
     * @return will be return current DATE
     */
    public String getCurrentDate()
    {
        SimpleDateFormat dateFormat = getDateFormatter(EMConstantToolkit.DATE_FORMAT_PATTERN_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    /**
     * To get previous date string for given date.
     * @param dateStringValue
     * @return
     * @throws ParseException
     */
    public String getPreviousDateForGivenDate(String dateStringValue) throws ParseException
    {
        SimpleDateFormat dateFormat = getDateFormatter(EMConstantToolkit.DATE_FORMAT_PATTERN_1);
        Calendar calendar = getCalendarObjectForGivenDate(dateStringValue,dateFormat);
        calendar.add(Calendar.DATE,-1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * To get next date string for given date.
     * @param dateStringValue
     * @return
     * @throws ParseException
     */
    public String getNextDateForGivenDate(String dateStringValue) throws ParseException
    {
        SimpleDateFormat dateFormat = getDateFormatter(EMConstantToolkit.DATE_FORMAT_PATTERN_1);
        Calendar calendar = getCalendarObjectForGivenDate(dateStringValue,dateFormat);
        calendar.add(Calendar.DATE,+1);
        return dateFormat.format(calendar.getTime());
    }

    private Calendar getCalendarObjectForGivenDate(String dateStringValue, SimpleDateFormat dateFormat)  throws ParseException
    {
        Date date = dateFormat.parse(dateStringValue);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * To get SimpleDateFormat for given pattern
     * @param pattern
     * @return
     */
    public SimpleDateFormat getDateFormatter(String pattern)
    {
        return new SimpleDateFormat(pattern);
    }
}
