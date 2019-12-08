package com.xiaokang.common.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @Description: SqlSessionFactoryUtils工具类（单例模式）
 * @author 小康
 * @version V1.0.0 2019年11月27日 下午3:48:33
 */
public class SqlSessionFactoryUtils {
	private static SqlSessionFactory sqlSessionFactory;
	private static InputStream is;
	// 静态代码块中读取mybatis-config.xml文件
	static {
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 获得SqlSessionFactory工厂实例
	 * @return SqlSessionFactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		}
		return sqlSessionFactory;
	}

	/**
	 * 
	 * @Description: 获得SqlSession实例,来操作数据库
	 * @return SqlSession
	 * @return
	 */
	public static SqlSession openSqlSession() {
		return getSqlSessionFactory().openSession();
	}

}
