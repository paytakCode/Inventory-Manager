package com.paytakcode.inventorymanager.api.v1.util;

/**
 *
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-25 오후 12:15
 */

import java.lang.reflect.Field;

public class PasswordMaskingUtil {

	public static String maskedToString(Object object) {
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();

		StringBuilder stringBuilder = new StringBuilder(clazz.getName()).append("(");
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getName().toLowerCase().contains("password")) {
					String value = (String)field.get(object);
					if (value != null) {
						stringBuilder.append(field.getName())
							.append("=")
							.append("*".repeat(value.length()))
							.append(", ");
					}
				} else {
					stringBuilder.append(field.getName()).append("=").append(field.get(object)).append(", ");
				}
			}
			if (stringBuilder.length() > 1) {
				stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return stringBuilder.append(")").toString();
	}

	private PasswordMaskingUtil() {
	}
}

