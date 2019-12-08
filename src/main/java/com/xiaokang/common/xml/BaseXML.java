package com.xiaokang.common.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @Description: dom4j解析xml
 * @author 小康
 * @version V1.0.0 2019年11月16日 上午9:07:46
 */
public class BaseXML {
	private static Map<String, Bean> kvMap = new ConcurrentHashMap<String, Bean>(0);

	static {
		InputStream is = BaseXML.class.getClassLoader().getResourceAsStream("ApplicationContext.xml");
		SAXReader reader = new SAXReader();
		// 获得xml文档
		Document document = null;
		try {
			document = reader.read(is);
			// 获得根元素beans
			Element rootElement = document.getRootElement();
			List<Element> beansList = rootElement.elements();
			for (Element perBean : beansList) {
				String beanId = perBean.attributeValue("id");
				String beanClass = perBean.attributeValue("class");

				List<Element> properList = perBean.elements();
				if (properList != null) {
					Map<String, String> propertyMap = new HashMap<String, String>(0);
					for (Element perProperty : properList) {
						String propertyName = perProperty.attributeValue("name");
						String propertyRef = perProperty.attributeValue("ref");
						propertyMap.put(propertyName, propertyRef);
					}
					// 将解析好的数据放入Bean实体中
					Bean bean = new Bean(beanId, beanClass, propertyMap);
					// 将实体信息存入map中
					kvMap.put(beanId, bean);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static Bean getBean(String beanId) {
		return kvMap.get(beanId);
	}
}
