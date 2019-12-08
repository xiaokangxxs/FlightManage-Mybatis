package com.xiaokang.common.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @Description: 时间工具类
 * @author 小康
 * @version V1.0.0 2019年11月11日 下午8:24:00
 */
public class LocalDateTimeUtils {
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Description: 获取默认时间格式化模板
	 * @return DateTimeFormatter
	 * @return
	 */
	public static DateTimeFormatter getPattern() {
		return DateTimeFormatter.ofPattern(PATTERN);
	}

	/**
	 * 
	 * @Description: 获取自定义时间格式化模板
	 * @return DateTimeFormatter
	 * @param formatter
	 * @return
	 */
	public static DateTimeFormatter getPattern(String formatter) {
		return DateTimeFormatter.ofPattern(formatter);
	}

	/**
	 * 
	 * @Description: 获取当前北京时间的默认格式化后的字符串
	 * @return String
	 * @return
	 */
	public static String getStringByLocalDateTime() {
		return LocalDateTime.now(ZoneOffset.of("+8")).format(getPattern());
	}

	/**
	 * 
	 * @Description: 获取当前北京时间的指定格式化的字符串
	 * @return String
	 * @param formatter
	 * @return
	 */
	public static String getStringByLocalDateTime(String formatter) {
		return LocalDateTime.now(ZoneOffset.of("+8")).format(getPattern(formatter));
	}

	/**
	 * 
	 * @Description: 将指定字符串解析成默认格式的LocalDateTime
	 * @return LocalDateTime
	 * @param text
	 * @return
	 */
	public static LocalDateTime getLocalDateTimeByString(String text) {
		return LocalDateTime.parse(text, getPattern());
	}

	/**
	 * 
	 * @Description: 将指定字符串解析成指定格式的LocalDateTime
	 * @return LocalDateTime
	 * @param text
	 * @param formatter
	 * @return
	 */
	public static LocalDateTime getLocalDateTimeByString(String text, String formatter) {
		return LocalDateTime.parse(text, getPattern(formatter));
	}
}
