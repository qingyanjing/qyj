package org.wisdom.pms.bp.am.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils
{
	/**
	 * 获取交集
	 */
	private static List<String> getIntersectionList(List<String> value0List, List<String> value1List)
	{
		List<String> intersectionList = new ArrayList<String>();

		if ((value0List != null && value0List.size() > 0) && (value1List != null && value1List.size() > 0))
		{
			for (String value1 : value0List)
			{
				if (value1List.contains(value1))
				{
					intersectionList.add(value1);
				}
			}
		}

		return intersectionList;
	}

	/**
	 * 获取最大值
	 */
	public static String getMaxValue(List<String> valueList)
	{
		String maxValue = "";

		if (valueList != null && valueList.size() > 0)
		{
			maxValue = valueList.get(0);

			for (int index = 1; index < valueList.size(); index++)
			{
				if (maxValue.compareTo(valueList.get(index)) < 0)
				{
					maxValue = valueList.get(index);
				}
			}
		}

		return maxValue;
	}

	/**
	 * 获取最小值
	 */
	public static String getMinValue(List<String> valueList)
	{
		String minValue = "";

		if (valueList != null && valueList.size() > 0)
		{
			minValue = valueList.get(0);

			for (int index = 1; index < valueList.size(); index++)
			{
				if (minValue.compareTo(valueList.get(index)) > 0)
				{
					minValue = valueList.get(index);
				}
			}
		}

		return minValue;
	}

	/**
	 * 判断是否有交集
	 */
	public static boolean isIntersection(List<String> value0List, List<String> value1List)
	{
		boolean isIntersection = false;

		List<String> intersectionList = getIntersectionList(value0List, value1List);
		if (intersectionList != null && intersectionList.size() > 0)
		{
			isIntersection = true;
		}

		return isIntersection;
	}

	/**
	 * 判断是否有交集
	 */
	public static boolean isIntersection(String... values)
	{
		boolean result = false;

		if (values.length > 1)
		{
			List<String> intersectionList = getIntersectionList(parseList(values[0]), parseList(values[1]));
			if (intersectionList.size() > 0)
			{
				for (int index = 2; index < values.length; index++)
				{
					intersectionList = getIntersectionList(intersectionList, parseList(values[index]));
					if (intersectionList.size() == 0)
					{
						break;
					}
				}
				result = true;
			}
		}

		return result;
	}

	/**
	 * 判断是否有交集
	 */
	public static boolean isIntersection(String[] value0Array, String[] value1Array)
	{
		return isIntersection(parseList(value0Array), parseList(value1Array));
	}

	/**
	 * 将列表值连接为字符串
	 */
	public static String join(List<String> valueList)
	{
		return join(parseArray(valueList));
	}

	/**
	 * 将列表值用分隔符连接为字符串
	 */
	public static String join(List<String> valueList, String separator)
	{
		return StringUtils.join(parseArray(valueList), separator);
	}

	/**
	 * 将列表值连接为字符串（默认逗号连接）
	 */
	public static String joinDefault(List<String> valueList)
	{
		return join(parseArray(valueList), ",");
	}

	/**
	 * 将数组值连接为字符串（默认逗号连接）
	 */
	public static String joinDefault(String[] valueArray)
	{
		return join(valueArray, ",");
	}

	/**
	 * 将排序后的列表值连接为字符串
	 */
	public static String joinSort(List<String> valueList)
	{
		String values = "";

		if (valueList != null && valueList.size() > 0)
		{
			// 转换为数组
			String[] valueArray = valueList.toArray(new String[0]);
			// 排序
			Arrays.sort(valueArray);
			// 连接
			values = join(valueArray);
		}

		return values;
	}

	/**
	 * 将排序后的列表值用分隔符连接为字符串
	 */
	public static String joinSort(List<String> valueList, String separator)
	{
		String values = "";

		if (valueList != null && valueList.size() > 0)
		{
			// 转换为数组
			String[] valueArray = valueList.toArray(new String[0]);
			// 排序
			Arrays.sort(valueArray);
			// 连接
			values = join(valueArray, separator);
		}

		return values;
	}

	/**
	 * 将排序后的数组值连接为字符串
	 */
	public static String joinSort(String[] valueArray)
	{
		String values = "";

		if (valueArray != null && valueArray.length > 0)
		{
			// 排序
			Arrays.sort(valueArray);
			// 连接
			values = join(valueArray);
		}

		return values;
	}

	/**
	 * 将排序后的数组值用分隔符连接为字符串
	 */
	public static String joinSort(String[] valueArray, String separator)
	{
		String values = "";

		if (valueArray != null && valueArray.length > 0)
		{
			// 排序
			Arrays.sort(valueArray);
			// 连接
			values = join(valueArray, separator);
		}

		return values;
	}

	/**
	 * 将排序后的列表值连接为字符串（默认逗号连接）
	 */
	public static String joinSortDefault(List<String> valueList)
	{
		return joinSort(parseArray(valueList), ",");
	}

	/**
	 * 将排序后的数组值连接为字符串（默认逗号连接）
	 */
	public static String joinSortDefault(String[] valueArray)
	{
		return joinSort(valueArray, ",");
	}

	/**
	 * 转换为数组
	 */
	public static String[] parseArray(List<String> valueList)
	{
		String[] valueArray = null;

		if (valueList != null && valueList.size() > 0)
		{
			valueArray = valueList.toArray(new String[0]);
		}

		return valueArray;
	}

	/**
	 * 转换为数组
	 */
	public static String[] parseArray(String values)
	{
		String[] valueArray = null;

		if (isNotEmpty(values))
		{
			valueArray = values.split(",");
		}

		return valueArray;
	}

	/**
	 * 转换为列表
	 */
	public static List<String> parseList(String values)
	{
		return parseList(parseArray(values));
	}

	/**
	 * 转换为列表
	 */
	public static List<String> parseList(String[] valueArray)
	{
		List<String> valueList = null;

		if (valueArray != null && valueArray.length > 0)
		{
			valueList = Arrays.asList(valueArray);
		}

		return valueList;
	}

	/**
	 * 转换为百分数（默认保留的2位小数）
	 */
	public static String parsePercent(double decimal)
	{
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);

		return nt.format(decimal);
	}

	/**
	 * 转换为百分数（指定保留的小数位数）
	 */
	public static String parsePercent(double decimal, int fractionDigits)
	{
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(fractionDigits);

		return nt.format(decimal);
	}

	/**
	 * 将列表转换为值排序后的数组
	 */
	public static String[] parseSortArray(List<String> valueList)
	{
		String[] valueArray = parseArray(valueList);

		if (valueArray != null && valueArray.length > 0)
		{
			Arrays.sort(valueArray);
		}

		return valueArray;
	}

	/**
	 * 将首字母转为小写
	 */
	public static String toLowerCaseFirstLetter(String value)
	{
		if (StringUtils.isNotEmpty(value))
		{
			char[] charArray = new char[1];

			charArray[0] = value.charAt(0);
			String temp = new String(charArray);
			if (charArray[0] >= 'A' && charArray[0] <= 'Z')
			{
				return value.replaceFirst(temp, temp.toLowerCase());
			}
		}

		return value;
	}
}