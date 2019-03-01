/**
 * Copyright(C) 2019 Hangzhou Differsoft Co., Ltd. All rights reserved.
 *
 */
package com.xiaogang.springboot.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogang.springboot.common.ResultPackage;
import com.xiaogang.springboot.common.base.EntityBase;
import com.xiaogang.springboot.common.base.ServiceResult;

/**
 * @since 2019年2月28日 下午7:48:32
 * @author xiaog
 *
 */
public class Util {

	/**
	 * 
	 */
	public final static String IS_NUMBER_PATTERN = "^[-\\+]?[\\d]*$";
	private static final Logger LOG = LoggerFactory.getLogger(Util.class);

	/**
	 * 判断字符串是否为空
	 * 
	 * @param message
	 *            需检测的字符串
	 * @return
	 */
	public static boolean isString(String message) {
		if (null == message || "".equals(message.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断字符串是否数字
	 * 
	 * @param message
	 *            需检测的字符串
	 * @return
	 */
	public static boolean isNumber(String message) {
		Pattern pattern = Pattern.compile(IS_NUMBER_PATTERN);
		return pattern.matcher(message).matches();
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}

		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}

		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	/**
	 * 将ServiceResult<实体类 > result 转化成ServiceResult<EntityBase>类型
	 * 
	 * @param result
	 * @return
	 */
	public static <T> ServiceResult<EntityBase> resultToBaseEntity(ServiceResult<T> result) {

		if (result == null || result.getResult().getData() == null) {
			// return new ServiceResult<>(result.getCode(), result.getMsg(), result.getSubCode());
		}

		T t = result.getResult().getData();

		if (!(t instanceof EntityBase)) {
			throw new RuntimeException("类型转换异常");
		}
		// 充填数据
		ServiceResult<EntityBase> serviceResult = new ServiceResult<>();
		serviceResult.setCode(result.getCode());
		serviceResult.setMsg(result.getMsg());
		serviceResult.setSubCode(result.getSubCode());
		serviceResult.setResult(new ResultPackage<EntityBase>((EntityBase) t));

		return serviceResult;
	}

	/**
	 * 将ServiceResult<List<实体类>> result 转化成ServiceResult<List<EntityBase>>类型
	 * 
	 * @param result
	 * @return
	 */
	public static <T> ServiceResult<List<EntityBase>> resultToBaseEntityList(ServiceResult<List<T>> result) {
		if (result == null || result.getResult() == null || result.getResult().getData() == null) {
			// return new ServiceResult<>(result.getCode(), result.getMsg(), result.getSubCode());
		}
		List<T> templist = result.getResult().getData();

		// 环境准备
		List<EntityBase> list = new ArrayList<EntityBase>();
		int flag = 0;

		for (T e : templist) {
			// 判断实体类是否继承EntityBase,且只用判断一次
			if (flag == 0) {
				if (!(e instanceof EntityBase)) {
					return null;
				}
				flag++;
			}
			// 类型转换
			list.add((EntityBase) e);
		}

		// 充填数据
		ServiceResult<List<EntityBase>> serviceResult = new ServiceResult<>();
		serviceResult.setCode(result.getCode());
		serviceResult.setMsg(result.getMsg());
		serviceResult.setSubCode(result.getSubCode());
		serviceResult.setResult(new ResultPackage<List<EntityBase>>(list, result.getResult().getPageInfo()));
		return serviceResult;
	}

	/**
	 * 将List<Long>转为数组Long[]
	 * 
	 * @param list
	 * @return
	 */
	public static Long[] listToArray(List<Long> list) {
		if (list == null || list.size() == 0) {
			return new Long[0];
		}
		Long[] arr = new Long[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static String[] listToArrayString(List<String> list) {
		if (list == null || list.size() == 0) {
			return new String[0];
		}
		String[] arr = new String[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static Integer[] listToArrayInteger(List<Integer> list) {
		if (list == null || list.size() == 0) {
			return new Integer[0];
		}
		Integer[] arr = new Integer[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	/**
	 * 将 Long 数组 转为 String 数组
	 * 
	 * @param longArray
	 * @return
	 */
	public static String[] longArrayToStringArray(Long[] longArray) {

		if (longArray == null || longArray.length == 0) {
			return new String[0];
		}

		String[] arr = new String[longArray.length];
		for (int i = 0; i < longArray.length; i++) {
			arr[i] = String.valueOf(longArray[i]);
		}
		return arr;
	}

	/**
	 * 获取字符串的SHA1值
	 * 
	 * @param message
	 * @return
	 */
	public static String sha1(String message) {
		if (message == null || message.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(message.getBytes("UTF-8"));

			byte[] byteArray = messageDigest.digest();
			int j = byteArray.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byteValue = byteArray[i];
				buf[k++] = hexDigits[byteValue >>> 4 & 0xf];
				buf[k++] = hexDigits[byteValue & 0xf];
			}
			return new String(buf);

		} catch (Exception e) {
			LOG.error("获取SH1失败：", e);
			return null;
		}
	}

	/**
	 * 将单个对象转换成数组
	 * 
	 * @param t
	 * @return
	 */
	public static <T> T[] objToArray(T t) {
		if (t == null) {
			return null;
		}
		Class<? extends Object> clazz = t.getClass();
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, 1);
		array[0] = t;
		return array;
	}

	/**
	 * 将任意集合类型转换成数组类型返回
	 * 
	 * @param collect
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] collectToArray(Collection<T> collect) {
		if (collect == null || collect.size() < 1) {
			return (T[]) Array.newInstance(Object.class, 1);
		}
		List<T> list = new ArrayList<T>(collect);
		T t = list.get(0);
		Class<? extends Object> clazz = t.getClass();
		T[] array = (T[]) Array.newInstance(clazz, list.size());
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * 将Long[]数组以,分组拼成字符串
	 * 
	 * @param nums
	 * @return
	 */
	public static String arrLongToString(Long[] nums) {
		if (Util.isNullOrEmpty(nums)) {
			return null;
		}

		String str = StringUtils.join(nums, ",");

		return str;
	}

	/**
	 * 截取字符串,中文算2个字符
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str, Integer length) {
		if (str == null) {
			return null;
		}

		if (str.length() < length / 2) {
			return str;
		}
		try {
			int count = 0;
			StringBuffer sb = new StringBuffer();
			String[] stringArr = str.split("");
			for (int i = 0; i < stringArr.length; i++) {
				count += stringArr[i].getBytes("GBK").length > 1 ? 2 : 1;
				if (count > length) {
					break;
				}
				sb.append(stringArr[i]);
			}

			return (sb.toString().length() < str.length()) ? sb.append("...").toString() : str;

		} catch (Exception ex) {

			LOG.info("字符串截取失败：" + ex.toString());

			return str;
		}
	}

	/**
	 * 访问对象成员属性值值
	 * 
	 * @param obj
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static final Object getFieldValue(Object obj, String field) {
		Object result = null;
		if (obj instanceof Map) {
			return ((Map) obj).get(field);
		}

		if (obj == null) {
			return null;
		}

		Method getterMethod = null;
		try {
			getterMethod = obj.getClass().getMethod("get" + StringUtils.capitalize(field));
		} catch (Exception e) {
		}
		if (getterMethod == null) {
			try {
				getterMethod = obj.getClass().getMethod("is" + StringUtils.capitalize(field));
			} catch (Exception e) {
			}
		}
		if (getterMethod == null) {
			Field privateField;
			try {
				privateField = obj.getClass().getDeclaredField(field);
				privateField.setAccessible(true);
				result = privateField.get(obj);
			} catch (Exception e) {
				return null;
			}
		} else {
			try {
				result = getterMethod.invoke(obj);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * 
	 * BigDecimal的加法运算封装
	 * 
	 * 
	 * @param b1
	 * 
	 * @param bn
	 * 
	 * @return
	 * 
	 */

	public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {

		if (null == b1) {

			b1 = BigDecimal.ZERO;

		}

		if (null != bn) {

			for (BigDecimal b : bn) {

				b1 = b1.add(null == b ? BigDecimal.ZERO : b);

			}

		}

		return b1;

	}

	/**
	 * 
	 * BigDecimal的安全减法运算
	 * 
	 * 
	 * @param isZero
	 *            减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果
	 * 
	 * @param b1
	 *            被减数
	 * 
	 * @param bn
	 *            需要减的减数数组
	 * 
	 * @return
	 * 
	 */

	public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {

		if (null == b1) {
			b1 = BigDecimal.ZERO;
		}

		BigDecimal r = b1;

		if (null != bn) {
			for (BigDecimal b : bn) {
				r = r.subtract((null == b ? BigDecimal.ZERO : b));
			}
		}

		return isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : r) : r;

	}

	/**
	 * 判断num1是否小于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1小于num2返回true
	 */
	public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == -1;
	}

	/**
	 * 判断num1是否小于等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1小于或者等于num2返回true
	 */
	public static boolean lessEqual(BigDecimal num1, BigDecimal num2) {
		return (num1.compareTo(num2) == -1) || (num1.compareTo(num2) == 0);
	}

	/**
	 * 判断num1是否大于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1大于num2返回true
	 */
	public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == 1;
	}

	/**
	 * 判断num1是否大于等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1大于或者等于num2返回true
	 */
	public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
		return (num1.compareTo(num2) == 1) || (num1.compareTo(num2) == 0);
	}

	/**
	 * 判断num1是否等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1等于num2返回true
	 */
	public static boolean equal(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == 0;
	}

	/**
	 * 精确6位小数后判断 BigDecimal大小
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int bigDecimalCompare(BigDecimal num1, BigDecimal num2) {
		return num1.setScale(6, BigDecimal.ROUND_HALF_UP).compareTo(num2.setScale(6, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值 默认返回小数位后6位，用于金额计算 2017年3月23日下午4:59:29
	 * 
	 * @param b1
	 * @param b2
	 * @param defaultValue
	 * @return
	 */
	public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
		if (null == b1 || null == b2) {
			return defaultValue;
		}
		try {
			return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 6, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * BigDecimal的乘法运算封装
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {
		if (null == b1 || null == b2) {
			return BigDecimal.ZERO;
		}
		return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
