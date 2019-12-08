package com.xiaokang.common.xml;
/**
 * 
 * @Description: xml文件中的实体类
 * @author 小康   
 * @version V1.0.0 2019年11月9日 上午11:12:41
 */

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bean {
	private String beanId;
	private String beanClass;
	private Map<String, String> kvMap = new HashMap<String, String>();

}
