package com.xiaokang.constant;
/**
 * 
 * @Description: 固定常量
 * @author 小康   
 * @version V1.0.0 2019年11月27日 下午4:17:16
 */

import lombok.Getter;

@Getter
public enum StringPath {

	textPath("D:\\jee_workspace\\FlightManage-Mybatis\\src\\main\\resources\\flights.txt");

	private String value;

	private StringPath(String value) {
		this.value = value;
	}
}
