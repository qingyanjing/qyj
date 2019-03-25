package org.wisdom.pms.bp.am.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils
{
	public static <T> Method getGetMethod(Class<T> clazz, Field filed)
	{
		Method getMethod = null;

		try
		{
			String fieldName = filed.getName();
			String getMethodName = "";
			// System.out.println("filed.getType()=="+filed.getType());
			if (filed.getType() == boolean.class || filed.getType() == Boolean.class)
			{
				getMethodName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			}
			else
			{
				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			}
			getMethod = clazz.getMethod(getMethodName);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}

		return getMethod;
	}

	public static <T> Method getSetMethod(Class<T> clazz, Field filed)
	{
		Method setMethod = null;

		try
		{
			String fieldName = filed.getName();

			String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

			setMethod = clazz.getMethod(setMethodName, new Class[] { filed.getType() });
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}

		return setMethod;
	}
}
