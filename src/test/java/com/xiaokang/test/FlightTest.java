package com.xiaokang.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.xiaokang.action.FlightAction;
import com.xiaokang.common.mybatis.SqlSessionFactoryUtils;
import com.xiaokang.common.time.DateUtils;
import com.xiaokang.common.xml.SingleBeanFactory;
import com.xiaokang.dao.FlightMapper;
import com.xiaokang.pojo.Plane;

/**
 * 
 * @Description: 测试类
 * @author 小康
 * @version V1.0.0 2019年11月27日 下午4:05:50
 */
public class FlightTest {
	private Logger logger = Logger.getLogger(FlightTest.class);

	@Test
	public void start() {
		FlightAction action = SingleBeanFactory.getInstance().newInstance(FlightAction.class.getSimpleName());
		action.function();
	}

	@Test
	public void testMybatisAdd() {

		SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
		FlightMapper flightDao = sqlSession.getMapper(FlightMapper.class);
		Plane plane = new Plane("XK123459", "北京", "上海", DateUtils.parseStringToDate("2019-06-06 20:30:00"),
				DateUtils.parseStringToDate("2019-06-06 20:30:00"));

		int result = flightDao.addFlight(plane);
		sqlSession.commit();
		if (result > 0) {
			logger.info("添加成功！");
		}
	}

	@Test
	public void testMybatisAll() {

		SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
		FlightMapper flightDao = sqlSession.getMapper(FlightMapper.class);
		List<Plane> selectAllFlight = flightDao.selectAllFlight();
		selectAllFlight.forEach((perPlane) -> {
			System.out.println(perPlane);
		});
	}

	@Test
	public void testMybatisDel() {

		SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
		FlightMapper flightDao = sqlSession.getMapper(FlightMapper.class);
		int result = flightDao.deleteFlight("XK123459");
		sqlSession.commit();
		if (result > 0) {
			logger.info("删除成功！");
		}
	}

	@Test
	public void testMybatisDepartureAndArrival() {

		SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
		FlightMapper flightDao = sqlSession.getMapper(FlightMapper.class);
		Plane plane = new Plane();
		plane.setDepartureCity("北京");
		plane.setArrivalCity("上海");
		List<Plane> queryFlight = flightDao.queryFlight(plane);
		queryFlight.forEach((perPlane) -> {
			System.out.println(perPlane);
		});

	}

	@Test
	public void testMybatisQueryFlightByFlightno() {

		SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
		FlightMapper flightDao = sqlSession.getMapper(FlightMapper.class);
		Plane perPlane = flightDao.queryFlightByFlightno("XK123458");
		System.out.println(perPlane);
	}

}
