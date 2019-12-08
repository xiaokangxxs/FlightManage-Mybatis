package com.xiaokang.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description: 时间字符串转换工具
 * @author 小康
 * @version V1.0.0 2019年11月16日 上午9:32:31
 */
public class DateUtils {
	private static final String PATTERN_STRING = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Description: 按照默认格式将String转为Date类型
	 * @return Date
	 * @param dateStr
	 * @return
	 */
	public static Date parseStringToDate(String dateStr) {
		return parseStringToDate(dateStr, PATTERN_STRING);
	}

	/**
	 * 
	 * @Description: 按照指定格式将String转为Date类型
	 * @return Date
	 * @param dateStr
	 * @param formatter
	 * @return
	 */
	public static Date parseStringToDate(String dateStr, String formatter) {
		SimpleDateFormat format = new SimpleDateFormat(formatter);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Description: 按照默认时间格式将Date类型转为String
	 * @return String
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Date date) {
		return parseDateToString(date, PATTERN_STRING);
	}

	/**
	 * 
	 * @Description: 按照指定时间格式将Date类型转为String
	 * @return String
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String parseDateToString(Date date, String formatter) {
		SimpleDateFormat format = new SimpleDateFormat(formatter);
		return format.format(date);
	}

}
