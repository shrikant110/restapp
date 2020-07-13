package com.cardreader.config.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss a";
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DEFAULT_DATE_TIME_FULL_FORMAT = "yyyy-MM-dd hh:ss:mm.SSS";
	public static final String DEFAULT_MOODY_DATE_FORMAT= "YYYYMMDD";
	public	static Locale locale = new Locale("en","US","");

	
	public static String getDefaultDate() {
		final DateFormat format = new SimpleDateFormat("ddMMYYYY");
		final String dateStr = format.format(new Date());
		return dateStr;
	}
	
	public static String getFormatedDate(Date d, String dateFomate) {
		final DateFormat format = new SimpleDateFormat(dateFomate);
		final String dateStr = format.format(d);
		return dateStr;
	}
	
	public static Date getBackDate(int days) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -days);
		Date daysAgo = cal.getTime();
		return daysAgo;
	}
	
	
	/**
	 * Converts the String date to calender format
	 * @param s source date
	 * @return Calender date object
	 * @throws ParseException
	 */
	public static Calendar stringToCalendar(String s) throws ParseException {
		return stringToCalendar(s, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Converts calendar date to string
	 * @param cal 
	 * @return String date
	 */
	public static String calendarToString(Calendar cal){
		return calendarToString(cal, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Converts the calender date to string
	 * @param cal source date 
	 * @param format source date format in which date need to convert
	 * @return String date
	 */
	public static String calendarToString (Calendar cal, String format){
		if (null != cal){
			SimpleDateFormat df=new SimpleDateFormat(format,locale);
			return df.format(cal.getTime());
		}
		return "";
	}

	/**
	 * Get the current month
	 * @return Month name
	 */
	public static String getTodayMonth(){
		SimpleDateFormat df=new SimpleDateFormat("MMM",locale);
		return df.format(Calendar.getInstance());
	}
	/**
	 * Converts the string date to calander format
	 * @param s contains the date string
	 * @param dateFormat date format
	 * @return Calender object
	 * @throws ParseException
	 */
	public static Calendar stringToCalendar(String s, String dateFormat) throws ParseException {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df=new SimpleDateFormat(dateFormat,locale);
		Date d1=df.parse(s);
		cal.setTime(d1);

		return cal;
	}

	/**
	 * Checks if the provided date string is a valid date or not
	 * @param s contains the date string
	 * @param dateFormat date format in which date need to check
	 * @return true or false
	 */
	public static boolean isValidDate(String s, String dateFormat)  {
		SimpleDateFormat df=new SimpleDateFormat(dateFormat,locale);
		try{
			df.setLenient(false);
			df.parse(s);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/**
	 * get Calendar Start Date
	 * @param cal
	 * @return calender object
	 */
	public static Calendar getCalendarStartDate(Calendar cal) {
		if (cal != null){
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		return cal;
	}

	/**
	 * get Calender end date
	 * @param cal
	 * @return Calender object
	 */
	public static Calendar getCalendarEndDate(Calendar cal) {
		if (cal != null){
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
		}
		return cal;
	}

	/**
	 * convert a date from on format to another format
	 * @param date
	 * @param currentFormat
	 * @param toFormat
	 * @return formatted date string
	 * @throws ParseException
	 */
	public static String formatStringDate(String date, String currentFormat, String toFormat) throws ParseException{
		return new SimpleDateFormat(toFormat,locale).format(stringToCalendar(date, currentFormat).getTime());
	}

	/**
	 * convert date to string
	 * @param date value
	 * @param format date format in which date need to check
	 * @return String date
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf=new SimpleDateFormat(format,locale);
		return sdf.format(date);
	}

	

}
