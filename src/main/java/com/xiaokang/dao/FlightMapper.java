package com.xiaokang.dao;

import java.util.List;

import com.xiaokang.pojo.Plane;

public interface FlightMapper {
	/**
	 * 
	 * @Description: 添加航班
	 * @return int
	 * @param plane
	 * @return
	 */
	int addFlight(Plane plane);

	/**
	 * 
	 * @Description: 删除航班
	 * @return int
	 * @param delFlightNo
	 * @return
	 */
	int deleteFlight(String delFlightNo);

	/**
	 * 
	 * @Description: 根据起飞城市和到达城市查询指定航班
	 * @return List<Plane>
	 * @param plane
	 * @return
	 */
	List<Plane> queryFlight(Plane plane);

	/**
	 * 
	 * @Description: 根据航班号查询指定航班
	 * @return Plane
	 * @param queryFlightno
	 * @return
	 */
	Plane queryFlightByFlightno(String queryFlightno);

	/**
	 * 
	 * @Description: 查询所有航班
	 * @return List<Plane>
	 * @return
	 */
	List<Plane> selectAllFlight();
}
