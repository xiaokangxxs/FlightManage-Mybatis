package com.xiaokang.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.xiaokang.common.mybatis.SqlSessionFactoryUtils;
import com.xiaokang.common.time.DateUtils;
import com.xiaokang.common.time.LocalDateTimeUtils;
import com.xiaokang.constant.StringPath;
import com.xiaokang.dao.FlightMapper;
import com.xiaokang.pojo.Plane;
import com.xiaokang.service.FlightService;

/**
 * 
 * @Description: 业务层
 * @author 小康
 * @version V1.0.0 2019年11月27日 下午6:20:34
 */
public class FlightServiceImpl implements FlightService {
	private Logger logger = Logger.getLogger(FlightServiceImpl.class);
	SqlSession sqlSession;
	FlightMapper flightMapper;

	public FlightServiceImpl() {
		sqlSession = SqlSessionFactoryUtils.openSqlSession();
		flightMapper = sqlSession.getMapper(FlightMapper.class);
	}

	@Override
	public List<Plane> selectAllFlight() {
		List<Plane> planes = flightMapper.selectAllFlight();
		if (!planes.isEmpty()) {
			logger.info("查询所有航班信息成功！" + LocalDateTimeUtils.getStringByLocalDateTime());
		} else {
			logger.info("航班信息为空！" + LocalDateTimeUtils.getStringByLocalDateTime());
		}
		return planes;
	}

	@Override
	public List<Plane> queryFlight(String departureCity, String arrivalCity) {
		Plane plane = new Plane();
		plane.setDepartureCity(departureCity);
		plane.setArrivalCity(arrivalCity);
		List<Plane> queryFlights = flightMapper.queryFlight(plane);
		if (queryFlights.isEmpty()) {
			logger.info("没有找到符合条件的航班");
			return null;
		}
		return queryFlights;
	}

	@Override
	public String addFight(String flightNo, String departureCity, String departureTime, String arrivalCity,
			String arrivalTime) {
		String message = "";
		Plane plane = new Plane(flightNo, departureCity, arrivalCity, DateUtils.parseStringToDate(departureTime),
				DateUtils.parseStringToDate(arrivalTime));
		Plane queryFlight = flightMapper.queryFlightByFlightno(flightNo);
		if (queryFlight != null) {
			message = "添加失败，航班号已存在！";
			logger.info("添加失败，航班号已存在！" + LocalDateTimeUtils.getStringByLocalDateTime());
		} else {
			int isLegal = DateUtils.parseStringToDate(departureTime)
					.compareTo(DateUtils.parseStringToDate(arrivalTime));
			if (isLegal > 0) {
				message = "添加失败，起飞时间不合法！";
				logger.info("添加失败，起飞时间不合法！" + LocalDateTimeUtils.getStringByLocalDateTime());
			} else {
				int affectRows = flightMapper.addFlight(plane);
				sqlSession.commit();
				message = affectRows > 0 ? "添加成功" : "添加失败";
			}
		}
		return message;
	}

	@Override
	public String deleteFlight(String delFlightNo) {
		String message = "";
		Plane queryFlight = flightMapper.queryFlightByFlightno(delFlightNo);
		if (queryFlight == null) {
			message = "您要删除的航班编号不存在，删除失败！";
			logger.info("您要删除的航班编号不存在，删除失败！" + LocalDateTimeUtils.getStringByLocalDateTime());
		} else {
			int affectRows = flightMapper.deleteFlight(delFlightNo);
			sqlSession.commit();
			message = affectRows > 0 ? "删除成功" : "删除失败";
		}
		return message;
	}

	@Override
	public String exportToTxt(List<Plane> flights) {
		String txtPath = StringPath.textPath.getValue();
		flights.forEach((perFlight) -> {
			String flightInfo = String.valueOf(perFlight.getFlightno() + "  起飞城市：" + perFlight.getDepartureCity()
					+ "  起飞时间：" + com.xiaokang.common.time.DateUtils.parseDateToString(perFlight.getDepartureTime())
					+ "  到达城市：" + perFlight.getArrivalCity() + "  到达时间："
					+ DateUtils.parseDateToString(perFlight.getArrivalTime()) + "\n");
			File file = new File(txtPath);
			FileWriter fw = null;
			try {
				fw = new FileWriter(file, true);
				fw.write(flightInfo);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		return txtPath;
	}

	@Override
	public void closeSqlSession() {
		sqlSession.close();
	}

}
