package com.xiaokang.common.xml;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @Description: 单例工厂（饿汉模式）
 * @author 小康
 * @version V1.0.0 2019年11月9日 上午11:06:23
 */
public class SingleBeanFactory {
	private static SingleBeanFactory INSTANCE = new SingleBeanFactory();
	private Map<String, Object> objMap = new ConcurrentHashMap<String, Object>(0);

	private SingleBeanFactory() {
	}

	public static SingleBeanFactory getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public <E> E newInstance(String key) {
		E obj = null;
		if (objMap.containsKey(key)) {
			return (E) objMap.get(key);
		}
		try {
			// 拿到反射类
			Class<?> cls = Class.forName(BaseXML.getBean(key).getBeanClass());
			// 通过反射创建一个实例
			obj = (E) cls.newInstance();
			// 通过BaseXML的getBean方法拿到Bean实体
			Bean bean = BaseXML.getBean(key);
			// 通过bean再拿到property元素中的name、ref的属性值
			Map<String, String> propertyMap = bean.getKvMap();
			for (String propertyName : propertyMap.keySet()) {
				// 获取指定字段
				Field field = cls.getDeclaredField(propertyName);
				field.setAccessible(true);
				field.set(obj, newInstance(propertyMap.get(propertyName)));
			}
			objMap.put(key, obj);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException
				| SecurityException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
