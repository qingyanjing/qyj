package org.wisdom.pms.bp.am.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.Assert;

public abstract class BeanUtils extends org.springframework.beans.BeanUtils
{
	public static <T> List<Map<String, Object>> beanListToMapList(List<T> beanList)
	{
		List<Map<String, Object>> mapList = new ArrayList<>();

		if (beanList != null && beanList.size() > 0)
		{
			for (T bean : beanList)
			{
				mapList.add(beanToMap(bean));
			}
		}

		return mapList;
	}

	public static <T> Map<String, Object> beanToMap(T bean)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if (bean != null)
		{
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet())
			{
				map.put(String.valueOf(key), beanMap.get(key));
			}
		}
		return map;
	}

	public static void copyProperties(Object source, Object target) throws BeansException
	{
		copyProperties(source, target, null, (String[]) null);
	}

	public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException
	{
		copyProperties(source, target, editable, (String[]) null);
	}

	private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties) throws BeansException
	{
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		if (editable != null)
		{
			if (!editable.isInstance(target))
			{
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
			}
		}

		Class<?> sourceClass = source.getClass();

		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		PropertyDescriptor[] sourcePropertyDescriptors = getPropertyDescriptors(sourceClass);
		for (PropertyDescriptor sourcePropertyDescriptor : sourcePropertyDescriptors)
		{
			Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
			if (sourceReadMethod != null && (ignoreList == null || !ignoreList.contains(sourcePropertyDescriptor.getName())))
			{
				try
				{
					if (!Modifier.isPublic(sourceReadMethod.getDeclaringClass().getModifiers()))
					{
						sourceReadMethod.setAccessible(true);
					}
					Object value = sourceReadMethod.invoke(source);
					if (value != null)
					{
						// 按照同名原则复制属性
						PropertyDescriptor targetPropertyDescriptor = getPropertyDescriptor(target.getClass(), sourcePropertyDescriptor.getName());
						if (targetPropertyDescriptor != null)
						{
							Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
							if (targetWriteMethod != null)
							{
								if (!Modifier.isPublic(targetWriteMethod.getDeclaringClass().getModifiers()))
								{
									targetWriteMethod.setAccessible(true);
								}
								targetWriteMethod.invoke(target, value);
							}
						}
						// 将日期类型转换为字符串
						if (value.getClass().getName().equals("java.util.Date"))
						{
							targetPropertyDescriptor = getPropertyDescriptor(target.getClass(), sourcePropertyDescriptor.getName() + "Str");
							if (targetPropertyDescriptor != null)
							{
								Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
								if (targetWriteMethod != null)
								{
									if (!Modifier.isPublic(targetWriteMethod.getDeclaringClass().getModifiers()))
									{
										targetWriteMethod.setAccessible(true);
									}

									SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

									targetWriteMethod.invoke(target, simpleDataFormat.format(value));
								}
							}
						}
						// 将布尔类型转换为字符串
						if (value.getClass().getName().equals("java.lang.Boolean"))
						{
							targetPropertyDescriptor = getPropertyDescriptor(target.getClass(), sourcePropertyDescriptor.getName() + "Str");
							if (targetPropertyDescriptor != null)
							{
								Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
								if (targetWriteMethod != null)
								{
									if (!Modifier.isPublic(targetWriteMethod.getDeclaringClass().getModifiers()))
									{
										targetWriteMethod.setAccessible(true);
									}

									if ((boolean) value)
									{
										targetWriteMethod.invoke(target, "是");
									}
									else
									{
										targetWriteMethod.invoke(target, "否");
									}
								}
							}
						}
					}
				}
				catch (Throwable ex)
				{
					throw new FatalBeanException("Could not copy properties from source to target", ex);
				}
			}
		}
	}

	public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException
	{
		copyProperties(source, target, null, ignoreProperties);
	}

	public static Object getPropertyValue(Object source, String propertyName) throws BeansException
	{
		Object value = null;

		Assert.notNull(source, "Source must not be null");

		Class<?> sourceClass = source.getClass();

		PropertyDescriptor sourcePropertyDescriptor = getPropertyDescriptor(sourceClass, propertyName);
		if (sourcePropertyDescriptor != null)
		{
			Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
			if (sourceReadMethod != null)
			{
				try
				{
					if (!Modifier.isPublic(sourceReadMethod.getDeclaringClass().getModifiers()))
					{
						sourceReadMethod.setAccessible(true);
					}
					value = sourceReadMethod.invoke(source);
				}
				catch (Throwable ex)
				{
					ex.printStackTrace();
				}
			}
		}

		return value;
	}

	public static boolean isChange(Object source, Object target, String... ignoreProperties) throws BeansException
	{
		boolean isChange = false;

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> sourceClass = source.getClass();

		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		PropertyDescriptor[] sourcePropertyDescriptors = getPropertyDescriptors(sourceClass);
		for (PropertyDescriptor sourcePropertyDescriptor : sourcePropertyDescriptors)
		{
			Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
			if (sourceReadMethod != null && (ignoreList == null || !ignoreList.contains(sourcePropertyDescriptor.getName())))
			{
				try
				{
					if (!Modifier.isPublic(sourceReadMethod.getDeclaringClass().getModifiers()))
					{
						sourceReadMethod.setAccessible(true);
					}
					Object sourceValue = sourceReadMethod.invoke(source);

					// 按照同名原则复制属性
					PropertyDescriptor targetPropertyDescriptor = getPropertyDescriptor(target.getClass(), sourcePropertyDescriptor.getName());
					if (targetPropertyDescriptor != null)
					{
						Method targetReadMethod = targetPropertyDescriptor.getReadMethod();
						if (targetReadMethod != null)
						{
							if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers()))
							{
								targetReadMethod.setAccessible(true);
							}
							Object targetValue = targetReadMethod.invoke(target);

							if ((sourceValue == null && targetValue != null) || (sourceValue != null && targetValue == null))
							{
								isChange = true;
								break;
							}
							if (sourceValue != null && targetValue != null)
							{
								if (sourceValue.equals(targetValue) == false)
								{
									isChange = true;
									break;
								}
							}
						}
					}
				}
				catch (Throwable ex)
				{
					throw new FatalBeanException("Could not copy properties from source to target", ex);
				}
			}
		}

		return isChange;
	}

	public static <T> List<T> mapListToBeanList(List<Map<String, Object>> mapList, Class<T> clazz) throws InstantiationException, IllegalAccessException
	{
		List<T> beanList = new ArrayList<>();

		if (mapList != null && mapList.size() > 0)
		{
			for (Map<String, Object> map : mapList)
			{
				beanList.add(mapToBean(map, clazz.newInstance()));
			}
		}

		return beanList;
	}

	public static <T> T mapToBean(Map<String, Object> map, T bean)
	{
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	public static void setPropertyValue(Object source, String propertyName, Object propertyValue) throws BeansException
	{
		Assert.notNull(source, "Source must not be null");

		Class<?> sourceClass = source.getClass();

		PropertyDescriptor sourcePropertyDescriptor = getPropertyDescriptor(sourceClass, propertyName);
		if (sourcePropertyDescriptor != null)
		{
			Method sourceWriteMethod = sourcePropertyDescriptor.getWriteMethod();
			if (sourceWriteMethod != null)
			{
				try
				{
					if (!Modifier.isPublic(sourceWriteMethod.getDeclaringClass().getModifiers()))
					{
						sourceWriteMethod.setAccessible(true);
					}
					sourceWriteMethod.invoke(source, propertyValue);
				}
				catch (Throwable ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}
