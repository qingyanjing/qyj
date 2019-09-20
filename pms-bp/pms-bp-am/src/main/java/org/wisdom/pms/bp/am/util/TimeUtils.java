package org.wisdom.pms.bp.am.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class TimeUtils
{
	public static Date createDate()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date FormatCstDate(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		// java.util.Date对象
		Date date = null;
		try
		{
			date = (Date) sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date createDateByDayOffset(Date date, int dayOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// 添加日偏量
		calendar.add(Calendar.DATE, dayOffset);

		return calendar.getTime();
	}

	public static Date createDateByDayOffset(int dayOffset)
	{
		return createDateByDayOffset(new Date(), dayOffset);
	}

	public static String createDateStr()
	{
		return parseString(createDate(), "yyyy-MM-dd");
	}

	public static String createDateStrByDayOffset(Date date, int dayOffset)
	{
		return parseString(createDateByDayOffset(date, dayOffset), "yyyy-MM-dd");
	}

	public static String createDateStrByDayOffset(int dayOffset)
	{
		return parseString(createDateByDayOffset(dayOffset), "yyyy-MM-dd");
	}

	public static Date createTime()
	{
		return new Date();
	}

	public static Date createTimeByDayOffset(Date date, int dayOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 添加日偏量
		calendar.add(Calendar.DATE, dayOffset);

		return calendar.getTime();
	}

	public static Date createTimeByDayOffset(int dayOffset)
	{
		return createTimeByDayOffset(new Date(), dayOffset);
	}

	public static Date createTimeByMinuteOffset(Date date, int minuteOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 添加分钟偏量
		calendar.add(Calendar.MINUTE, minuteOffset);

		return calendar.getTime();
	}

	public static Date createTimeByMonthOffset(Date date, int monthOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 添加月偏量
		calendar.add(Calendar.MONTH, monthOffset);

		return calendar.getTime();
	}

	public static Date createTimeByMonthOffset(int monthOffset)
	{
		return createTimeByMonthOffset(new Date(), monthOffset);
	}

	public static Date createTimeBySecondOffset(Date date, int secondOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 添加秒偏量
		calendar.add(Calendar.SECOND, secondOffset);

		return calendar.getTime();
	}

	public static Date createTimeByYearOffset(Date date, int yearOffset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 添加年偏量
		calendar.add(Calendar.YEAR, yearOffset);

		return calendar.getTime();
	}

	public static Date createTimeByYearOffset(int yearOffset)
	{
		return createTimeByYearOffset(new Date(), yearOffset);
	}

	public static String createTimeStr()
	{
		return parseString(createTime());
	}

	public static String createTimeStrByDayOffset(Date date, int dayOffset, String format)
	{
		return parseString(createTimeByDayOffset(date, dayOffset), format);
	}

	public static String createTimeStrByDayOffset(int dayOffset, String format)
	{
		return parseString(createTimeByDayOffset(dayOffset), format);
	}

	public static String createTimeStrByMinuteOffset(Date date, int minuteOffset, String format)
	{
		return parseString(createTimeByMinuteOffset(date, minuteOffset), format);
	}

	public static String createTimeStrByMonthOffset(Date date, int monthOffset, String format)
	{
		return parseString(createTimeByMonthOffset(date, monthOffset), format);
	}

	public static String createTimeStrByMonthOffset(int monthOffset, String format)
	{
		return parseString(createTimeByMonthOffset(monthOffset), format);
	}

	/**
	 * 取出当前日
	 */
	public static int getCurrentDate()
	{
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取出当前月(int型)
	 */
	public static int getCurrentMonthOfInt()
	{
		Calendar now = Calendar.getInstance();

		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取出当前月(字符串型)
	 */
	public static String getCurrentMonthOfStr()
	{
		Calendar now = Calendar.getInstance();

		int dayInt = now.get(Calendar.MONTH) + 1;

		String dayStr = null;
		if (dayInt < 10)
		{
			dayStr = "0" + String.valueOf(dayInt);
		}
		else
		{
			dayStr = String.valueOf(dayInt);
		}
		return dayStr;
	}

	/**
	 * 取出当前年
	 */
	public static int getCurrentYear()
	{
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	public static String[] getDateArray(String startDate, String endDate)
	{
		List<String> dateList = new ArrayList<String>();

		try
		{
			SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date startTime = simpleDataFormat.parse(startDate);
			Date endTime = simpleDataFormat.parse(endDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);

			while (calendar.getTime().compareTo(endTime) <= 0)
			{
				dateList.add(simpleDataFormat.format(calendar.getTime()));

				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return dateList.toArray(new String[0]);
	}

	/**
	 * 计算某月的天
	 */
	public static String[] getDayArray(String dateStr)
	{
		List<String> dayList = new ArrayList<String>();

		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM");
		String currentDateStr = simpleDataFormat.format(new Date());

		if (currentDateStr.compareTo(dateStr) > 0)
		{
			try
			{
				Date date = simpleDataFormat.parse(dateStr);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int index = 1; index <= dayCount; index++)
				{
					dayList.add(String.format("%1$02d", index));
				}
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Calendar calendar = Calendar.getInstance();

			int currentHour = calendar.get(Calendar.DAY_OF_MONTH);
			for (int index = 1; index <= currentHour; index++)
			{
				dayList.add(String.format("%1$02d", index));
			}
		}

		return dayList.toArray(new String[0]);
	}

	/**
	 * 计算某年某月有多少天
	 */
	public static int getDayCount(String dateStr)
	{
		try
		{
			SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date = simpleDataFormat.parse(dateStr);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 获取当前时间是该月的第几天
	 */
	public static int getDaysOfCurrentMonth()
	{
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间是该年的第几天
	 */
	public static int getDaysOfCurrentYear()
	{
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取某一年有多少天:闰年366、平年365
	 */
	public static int getDaysOfYear(String year)
	{
		int days = 0;
		int yearNum = Integer.parseInt(year);
		if (yearNum % 4 == 0 && yearNum % 100 != 0 || yearNum % 400 == 0)
		{
			days = 366;
		}
		else
		{
			days = 365;
		}
		return days;
	}

	/**
	 * 获取当前时间所在月的第一天（时分秒为0）
	 */
	public static Date getFirstDayOfMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 设置当月的第一天
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的第一天
	 */
	public static Date getFirstDayOfMonth(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		// 设置当年
		calendar.set(Calendar.YEAR, year);
		// 设置当月
		calendar.set(Calendar.MONTH, month);
		// 第一天
		calendar.set(Calendar.DATE, 1);

		return getFirstDayOfMonth(calendar.getTime());
	}

	/**
	 * 返回指定日期的季的第一天
	 */
	public static Date getFirstDayOfQuarter(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的第一天
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter)
	{
		Date firstDay = null;

		if (quarter > 0 && quarter < 5)
		{
			firstDay = getFirstDayOfMonth(year, (quarter - 1) * 3);
		}

		return firstDay;
	}

	/**
	 * 取得当前日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// 设置为一星期的第一天
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

		return calendar.getTime();
	}

	/**
	 * 得到某年某周的第一天
	 */
	public static Date getFirstDayOfWeek(int year, int week)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		// 加周
		calendar.add(Calendar.DATE, (week - 1) * 7);

		return getFirstDayOfWeek(calendar.getTime());
	}

	public static Date getFirstDayOfYear(Date date)
	{
		// Calendar中的Month是从0-11的，0代表1月，11代表12月。
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static String getFirstDayStrOfMonth(Date date, String format)
	{
		return parseString(getFirstDayOfMonth(date), format);
	}

	public static String getFirstDayStrOfYear(Date date, String format)
	{
		return parseString(getFirstDayOfYear(date), format);
	}

	/**
	 * 计算某天的小时
	 */
	public static String[] getHourArray(String dateStr)
	{
		List<String> monthList = new ArrayList<String>();

		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = simpleDataFormat.format(new Date());

		if (currentDateStr.compareTo(dateStr) > 0)
		{
			for (int index = 0; index <= 23; index++)
			{
				monthList.add(String.format("%1$02d", index));
			}
		}
		else
		{
			Calendar calendar = Calendar.getInstance();

			int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
			for (int index = 0; index <= currentHour; index++)
			{
				monthList.add(String.format("%1$02d", index));
			}
		}

		return monthList.toArray(new String[0]);
	}

	/**
	 * 返回指定日期的上个月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Integer year, Integer quarter)
	{
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1)
		{
			month = 12 - 1;
		}
		else if (quarter == 2)
		{
			month = 3 - 1;
		}
		else if (quarter == 3)
		{
			month = 6 - 1;
		}
		else if (quarter == 4)
		{
			month = 9 - 1;
		}
		else
		{
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 取得当前日期所在周的前一周最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastWeek(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
	}

	/**
	 * 返回指定日期的月的最后一天（不含时分秒）
	 */
	public static Date getLastDayOfMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 设置当年
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		// 设置当月
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		// 第一天
		calendar.set(Calendar.DATE, 1);
		// 加一月
		calendar.add(Calendar.MONTH, 1);
		// 减一天
		calendar.add(Calendar.DATE, -1);
		// 设置时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的最后一天
	 */
	public static Date getLastDayOfMonth(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		// 设置当年
		calendar.set(Calendar.YEAR, year);
		// 设置当月
		calendar.set(Calendar.MONTH, month);
		// 第一天
		calendar.set(Calendar.DATE, 1);

		return getLastDayOfMonth(calendar.getTime());
	}

	/**
	 * 返回指定日期的季的最后一天
	 */
	public static Date getLastDayOfQuarter(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的最后一天
	 */
	public static Date getLastDayOfQuarter(int year, int quarter)
	{
		Date lastDay = null;

		if (quarter > 0 && quarter < 5)
		{
			lastDay = getLastDayOfMonth(year, quarter * 3 - 1);
		}

		return lastDay;
	}

	/**
	 * 取得当前日期所在周的最后一天
	 */
	public static Date getLastDayOfWeek(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		// 去掉时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		// 设置为一星期的第一天
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);

		return calendar.getTime();
	}

	/**
	 * 得到某年某周的最后一天
	 */
	public static Date getLastDayOfWeek(int year, int week)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		// 添加周
		calendar.add(Calendar.DATE, week * 7 - 1);

		return getLastDayOfWeek(calendar.getTime());
	}

	/**
	 * 返回指定日期的年的最后一天
	 */
	public static Date getLastDayOfYear(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 设置当年
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		// 设置当月
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		// 第一天
		calendar.set(Calendar.DATE, 1);
		// 加一月
		calendar.add(Calendar.MONTH, 1);
		// 减一天
		calendar.add(Calendar.DATE, -1);
		// 设置时分秒
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	/**
	 * 返回指定日期的月的最后一天
	 */
	public static String getLastDayStrOfMonth(Date date, String format)
	{
		return parseString(getLastDayOfMonth(date), format);
	}

	/**
	 * 返回指定日期的年的最后一天
	 */
	public static String getLastDayStrOfYear(Date date, String format)
	{
		return parseString(getLastDayOfYear(date), format);
	}

	/**
	 * 获取最大值
	 */
	public static Date getMaxValue(Date... values)
	{
		Date maxValue = null;

		if (values.length > 1)
		{
			maxValue = values[0];

			for (int index = 1; index < values.length; index++)
			{
				Date compareTime = values[index];

				if (maxValue == null)
				{
					maxValue = compareTime;
				}
				else
				{
					if (compareTime != null)
					{
						if (maxValue.compareTo(compareTime) <= 0)
						{
							maxValue = compareTime;
						}
					}
				}
			}
		}

		return maxValue;
	}

	/**
	 * 获取最小值
	 */
	public static Date getMinValue(Date... values)
	{
		Date minValue = null;

		if (values.length > 1)
		{
			minValue = values[0];

			for (int index = 1; index < values.length; index++)
			{
				Date compareTime = values[index];

				if (minValue == null)
				{
					minValue = compareTime;
				}
				else
				{
					if (compareTime != null)
					{
						if (minValue.compareTo(compareTime) >= 0)
						{
							minValue = compareTime;
						}
					}
				}
			}
		}

		return minValue;
	}

	/**
	 * 计算某年的月份
	 */
	public static String[] getMonthArray(String year)
	{
		List<String> monthList = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		if (Integer.valueOf(year) == currentYear)
		{
			int currentMonth = calendar.get(Calendar.MONTH) + 1;
			for (int index = 1; index <= currentMonth; index++)
			{
				monthList.add(String.format("%1$02d", index));
			}
		}
		else if (Integer.valueOf(year) < currentYear)
		{
			for (int index = 1; index <= 12; index++)
			{
				monthList.add(String.format("%1$02d", index));
			}
		}

		return monthList.toArray(new String[0]);
	}

	/**
	 * 获取时间月差
	 */
	public static String[] getMonthArray(String startMonthStr, String endMonthStr)
	{
		int startMonth = Integer.valueOf(startMonthStr);
		int endMonth = Integer.valueOf(endMonthStr);

		List<String> monthList = new ArrayList<String>();
		if (startMonth != 0 && endMonth != 0)
		{
			while (startMonth <= endMonth)
			{
				monthList.add(String.valueOf(String.format("%1$02d", startMonth)));
				startMonth++;
			}
		}

		return monthList.toArray(new String[0]);
	}

	/**
	 * 获取时间周差
	 */
	public static String[] getMonthArrayByTime(String startDateStr, String endDateStr)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(TimeUtils.parseDate(startDateStr, "yyyy-MM-dd"));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(TimeUtils.parseDate(endDateStr, "yyyy-MM-dd"));

		List<String> monthList = new ArrayList<String>();
		while (cal1.getTimeInMillis() < cal2.getTimeInMillis())
		{
			monthList.add(cal1.get(Calendar.YEAR) + "年" + (cal1.get(Calendar.MONTH) + 1) + "月");
			cal1.add(Calendar.MONTH, 1);
		}
		// monthList.add(cal2.get(Calendar.YEAR)+"年"+(cal2.get(Calendar.MONTH)+1)+"月");

		return monthList.toArray(new String[0]);
	}

	/**
	 * 获取前一月的此刻
	 */
	public static Date getPreMonthTime()
	{
		Calendar calendar = Calendar.getInstance();
		// 减1月
		calendar.add(Calendar.MONTH, -1);

		return calendar.getTime();
	}

	/**
	 * 获取前一周的此刻
	 */
	public static Date getPreWeekTime()
	{
		Calendar calendar = Calendar.getInstance();
		// 减7天
		calendar.add(Calendar.DAY_OF_MONTH, -7);

		return calendar.getTime();
	}

	/**
	 * 返回指定日期所属的季度
	 */
	public static int getQuarterOfYear(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	public static String[] getRecentYearArray(int yearCount)
	{
		String[] array = null;

		List<String> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		while (yearCount > 0)
		{
			list.add(String.valueOf(currentYear));

			currentYear--;
			yearCount--;
		}
		if (list.size() > 0)
		{
			array = list.toArray(new String[0]);

			Arrays.sort(array);
		}

		return array;
	}

	public static String[] getSeasonArray(String year)
	{
		List<String> seasonList = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		if (Integer.valueOf(year) == currentYear)
		{
			int currentMonth = calendar.get(Calendar.MONTH) + 1;
			if (currentMonth > 0)
			{
				seasonList.add("1");
			}
			if (currentMonth > 3)
			{
				seasonList.add("2");
			}
			if (currentMonth > 6)
			{
				seasonList.add("3");
			}
			if (currentMonth > 9)
			{
				seasonList.add("4");
			}
		}
		else if (Integer.valueOf(year) < currentYear)
		{
			for (int index = 0; index < 4; index++)
			{
				seasonList.add(String.valueOf(index + 1));
			}
		}

		return seasonList.toArray(new String[0]);
	}

	/**
	 * 获取两个日期相隔多少天
	 */
	public static int getSpaceDays(String startDate, String endDate)
	{
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(TimeUtils.parseDate(startDate, "yyyy-MM-dd"));
		// 去掉时分秒
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(TimeUtils.parseDate(endDate, "yyyy-MM-dd"));
		// 去掉时分秒
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		// 得到两个日期相差的天数
		return ((int) (endCalendar.getTime().getTime() / 1000) - (int) (startCalendar.getTime().getTime() / 1000)) / 3600 / 24;
	}

	/**
	 * 获取两个日期相隔多少月
	 */
	public static int getSpaceMonths(String startDate, String endDate)
	{
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(TimeUtils.parseDate(startDate, "yyyy-MM"));

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(TimeUtils.parseDate(endDate, "yyyy-MM"));

		int startYear = startCalendar.get(Calendar.YEAR);
		int startMonth = startCalendar.get(Calendar.MONTH);
		int endYear = endCalendar.get(Calendar.YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH);

		return (endYear - startYear) * 12 + (endMonth - startMonth);
	}

	/**
	 * 获取两个日期相隔多少年
	 */
	public static int getSpaceYears(String startDate, String endDate)
	{
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(TimeUtils.parseDate(startDate, "yyyy"));

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(TimeUtils.parseDate(endDate, "yyyy"));

		int startYear = startCalendar.get(Calendar.YEAR);
		int endYear = endCalendar.get(Calendar.YEAR);

		return endYear - startYear;
	}

	public static String[] getTimePeriodArray(String date)
	{
		List<String> timePeriodList = new ArrayList<String>();

		if (StringUtils.isNotEmpty(date))
		{
			String currentDate = createDateStr();

			if (date.compareTo(currentDate) < 0)
			{
				for (int index = 0; index < 24; index++)
				{
					timePeriodList.add(String.format("%1$02d", index));
				}
			}
			else
			{
				Calendar calendar = Calendar.getInstance();
				int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

				for (int index = 0; index < currentHour + 1; index++)
				{
					timePeriodList.add(String.format("%1$02d", index));
				}
			}
		}

		return timePeriodList.toArray(new String[0]);
	}

	/**
	 * 获取时间周差
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String[] getWeekArray(String startDateStr, String endDateStr)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(TimeUtils.parseDate(startDateStr, "yyyy-MM-dd"));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(TimeUtils.parseDate(endDateStr, "yyyy-MM-dd"));
		List<String> weekList = new ArrayList<String>();
		int i = 0;
		while (cal1.getTimeInMillis() < cal2.getTimeInMillis())
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == 1)
			{
				if (i == 0)
				{
					weekList.add(cal1.get(Calendar.YEAR) + "年" + (cal1.get(Calendar.WEEK_OF_YEAR)) + "周");
				}
			}
			else
			{
				weekList.add(cal1.get(Calendar.YEAR) + "年" + (cal1.get(Calendar.WEEK_OF_YEAR)) + "周");
			}
			cal1.add(Calendar.WEEK_OF_YEAR, 1);
			i++;
		}
		weekList.add(cal2.get(Calendar.YEAR) + "年" + (cal2.get(Calendar.WEEK_OF_YEAR)) + "周");

		return weekList.toArray(new String[0]);
	}

	/**
	 * 获取当前日期是星期几
	 */
	public static String getWeekOfDate(Date date)
	{
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekIndex < 0)
		{
			weekIndex = 0;
		}

		return weeks[weekIndex];
	}

	/**
	 * 获取时间周差
	 */
	public static String getWeekTimeStr(int year, int week)
	{
		String timestr = "";
		try
		{
			String startDate = TimeUtils.parseString(getFirstDayOfWeek(year, week), "yyyy-MM-dd");
			String enDate = TimeUtils.parseString(getLastDayOfWeek(year, week), "yyyy-MM-dd");
			timestr = startDate + "~" + enDate;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return timestr;
	}

	public static int getYear(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static String[] getYearArray(String startYearStr, String endYearStr)
	{
		int startYear = Integer.valueOf(startYearStr);
		int endYear = Integer.valueOf(endYearStr);

		List<String> yearList = new ArrayList<String>();
		if (startYear != 0 && endYear != 0)
		{
			while (startYear <= endYear)
			{
				yearList.add(String.valueOf(startYear));
				startYear++;
			}
		}
		return yearList.toArray(new String[0]);
	}

	public static void main(String[] args)
	{
		System.out.println(parseString(TimeUtils.getFirstDayOfWeek(createTimeByDayOffset(1))));
	}

	/**
	 * 将Date字符串形式转化为Date类型
	 */
	public static Date parseDate(String dateStr)
	{
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date字符串形式转化为Date类型
	 */
	public static Date parseDate(String dateStr, String format)
	{
		Date date = null;

		try
		{
			if (StringUtils.isNotEmpty(dateStr))
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
				date = simpleDateFormat.parse(dateStr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将日期转化为日期字符串
	 */
	public static String parseString(Date date)
	{
		return parseString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将日期转化为指定格式的字符串
	 */
	public static String parseString(Date date, String format)
	{
		String dateString = "";

		try
		{
			if (date != null)
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
				dateString = simpleDateFormat.format(date);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return dateString;
	}

	public static Date trunc(Date date)
	{
		return trunc(new Date(), "yyyy-MM-dd");
	}

	public static Date trunc(Date date, String format)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if ("yyyy".equals(format))
		{
			calendar.set(Calendar.MONTH, 1);
			calendar.set(Calendar.DATE, 1);
			// 去掉时分秒
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		if ("yyyy-MM".equals(format))
		{
			calendar.set(Calendar.DATE, 1);
			// 去掉时分秒
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		if ("yyyy-MM-dd".equals(format))
		{
			// 去掉时分秒
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}

		return calendar.getTime();
	}
}