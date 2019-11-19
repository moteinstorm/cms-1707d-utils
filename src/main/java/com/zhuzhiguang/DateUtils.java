package com.zhuzhiguang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期类型的工具类
 * @author zhuzg
 *
 */
public class DateUtils {
	
	public static int YEAR  = Calendar.YEAR;
	public static int MONTH  = Calendar.MONTH;
	public static int DATE  = Calendar.DATE;
	public static int HOUR  = Calendar.HOUR;
	public static int MINUTE  = Calendar.MINUTE;
	public static int SECOND  = Calendar.SECOND;
	public static int MILLISECOND  = Calendar.MILLISECOND;
	

	/**
	 * 一天的毫秒数
	 */
	static final long millionSecondsPerDay = 1000 * 60 * 60 * 24;

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1, Date date2) {

		if (date1 == null || date2 == null)
			throw new RuntimeException("日期不能为空");

		return date1.compareTo(date2);
	}
	
	/**
	 * 在一个原有的日期基础上增减  天数 月数 秒数等
	 * @param field
	 * @param num
	 * @param srcDate
	 * @return
	 */
	public static synchronized Date addField(int field,int num,Date srcDate) {
		
		
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(srcDate);
		canlendar.add(field, num);
		return canlendar.getTime();
		
	}

	/**
	 * 计算年龄
	 * @param birthday
	 * @return
	 */
	public static synchronized int calculateAge(Date birthday) {

		if (birthday.compareTo(new Date()) > 0) {
			throw new RuntimeException("生日不能大于当前日期" + birthday);
		}

		//获取日历类
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(birthday);

		int bdYear = canlendar.get(Calendar.YEAR);// 获取生日的年
		int bdMonth = canlendar.get(Calendar.MONTH);//获取生日的月
		int bdDay = canlendar.get(Calendar.DAY_OF_MONTH);// 获取生日的天

		System.out.println(" bdYear: " + bdYear + " bdMonth:" + bdMonth + " bdDay: " + bdDay);

		// 设置当前的日
		canlendar.setTime(new Date());
		int currentYear = canlendar.get(Calendar.YEAR);// 获取当前年
		int currentMonth = canlendar.get(Calendar.MONTH);//获取当前月
		int currentDay = canlendar.get(Calendar.DAY_OF_MONTH);// 获取当前日

		System.out.println(
				" currentYear: " + currentYear + " currentMonth:" + currentMonth + " currentDay: " + currentDay);

		int age = currentYear - bdYear;
		if (currentMonth < bdMonth) {
			age--;
		} else if (currentMonth == bdMonth && currentDay < bdDay) {
			age--;
		}

		return age;
	}

	/**
	 *  计算到将来某一个日期剩余的天数
	 * 
	 * @param futureDate  未来的日期
	 *          
	 * @return
	 * @throws CmsException
	 */
	public static int remainDays(Date futureDate) throws CmsException {
		/**
		 * 日期必须大于当前的日期
		 */
		if (futureDate.compareTo(new Date()) < 0) {
			throw new CmsException("日期不能小于当前的日期" + futureDate);
		}

		// 时间戳相减 除以 一天的毫秒数量
		int days = (int) ((futureDate.getTime() - new Date().getTime()) / millionSecondsPerDay);

		return days;

	}

	/**
	 * 判断是否为今天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 格式化参数日期
		String dateStr = sdf.format(date);

		// 格式化当前日期
		String todayStr = sdf.format(new Date());

		return (dateStr.equals(todayStr));

	}

	/**
	 * 判断是否为本周
	 * @param date
	 * @return
	 */
	public static  synchronized boolean isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day + 1 + 1);// �����+1����Ϊ�����տ�ʼ

		// ����һ������

		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7 - day + 1);

		

		System.out.println(format.format(lastDayOfWeek.getTime()));

		return (date.getTime() < lastDayOfWeek.getTime().getTime()
				&& date.getTime() > firstDayOfWeek.getTime().getTime());

	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized boolean  isThisMonth(Date date) {
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(date);
		// 判断是否为本月
		int pYear = canlendar.get(Calendar.YEAR);// 获取参数的年
		int pMonth = canlendar.get(Calendar.MONTH);//获取参数的月

		canlendar.setTime(new Date());
		// 判断是否为本月
		int curYear = canlendar.get(Calendar.YEAR);// 获取参数的年
		int curMonth = canlendar.get(Calendar.MONTH);//获取参数的月
		
		
		return (pYear==curYear && pMonth == curMonth);
	}
	
	
	
	/**
	 *  计算月初的时间
	 *    BOM  begin of the month 
	 * @param date
	 * @return
	 * 
	 */
	public static synchronized Date getBOM(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
		
	}
	
	/**
	 *  计算本月月末的时间
	 *   
	 * @param date
	 * @return
	 */
	public static synchronized Date getEOM(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);

		return calendar.getTime();
		
	}
	

}
