package com.xiaokang.action;

import java.util.List;
import java.util.Scanner;

import com.xiaokang.pojo.Plane;
import com.xiaokang.service.FlightService;

/**
 * 
 * @Description: 视图层
 * @author 小康
 * @version V1.0.0 2019年11月27日 下午6:19:19
 */
public class FlightAction {
	private FlightService flightService;
	Scanner sc = new Scanner(System.in);

	/**
	 * 功能选择菜单
	 */
	public void function() {
		System.out.println("1.显示所有航班信息(起飞时间升序显示)\n2.查询航班(起飞时间升序显示)\n3.添加航班\n4.删除航班\n0.退出系统");
		System.out.print("请选择功能>>>");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			showAllFlightsInfo();
			break;
		case 2:
			queryFlight();
			break;
		case 3:
			addFight();
			break;
		case 4:
			deleteFlight();
			break;
		case 0:
			if (exit()) {
				return;
			}
			break;
		default:
			System.out.println("小康正在开发中，敬请期待...");
			break;
		}
		function();
	}

	/**
	 * 
	 * @Description: 显示所有航班信息，起飞时间升序
	 * @return void
	 */
	public void showAllFlightsInfo() {
		List<Plane> planes = flightService.selectAllFlight();
		if (!planes.isEmpty() && planes.size() > 0) {
			planes.forEach((perPlane) -> {
				System.out.println(perPlane);
			});
		} else {
			System.out.println("暂无航班信息！");
		}
	}

	/**
	 * 
	 * @Description: 查询指定航班信息
	 * @return void
	 */
	@SuppressWarnings("unused")
	public void queryFlight() {
		System.out.print("请输入起飞城市>>>");
		String departureCity = sc.next();
		System.out.print("请输入到达城市>>>");
		String arrivalCity = sc.next();
		List<Plane> flights = flightService.queryFlight(departureCity, arrivalCity);
		if (flights != null) {
			flights.forEach((perPlane) -> {
				System.out.println(perPlane);
			});
			System.out.print("按任意键继续...");
			String forward = sc.next();
			System.out.print("请问是否要将查询结果导出到txt文件？y-保存  任意键不保存");
			String isSaveTxt = sc.next();
			if (isSaveTxt.equalsIgnoreCase("y")) {
				String txtPath = flightService.exportToTxt(flights);
				System.out.println("导出成功，文件在" + txtPath);
			}
		} else {
			System.out.println("您要查找的航班信息不存在！");
		}
	}

	/**
	 * 
	 * @Description: 添加航班
	 * @return void
	 */
	@SuppressWarnings("resource")
	public void addFight() {
		System.out.print("请输入航班编号>>>");
		String flightNo = sc.next();
		System.out.print("请输入起飞城市>>>");
		String departureCity = sc.next();
		Scanner scd = new Scanner(System.in);
		System.out.print("请输入起飞时间（格式为：2019-10-27 19:15:24）>>>");
		String departureTime = scd.nextLine();
		System.out.print("请输入到达城市>>>");
		String arrivalCity = sc.next();
		Scanner sca = new Scanner(System.in);
		System.out.print("请输入到达时间（格式为：2019-10-27 19:15:24）>>>");
		String arrivalTime = sca.nextLine();
		String message = flightService.addFight(flightNo, departureCity, departureTime, arrivalCity, arrivalTime);
		System.out.println(message);
	}

	/**
	 * 
	 * @Description: 删除航班
	 * @return void
	 */
	public void deleteFlight() {
		showAllFlightsInfo();
		System.out.print("请输入要删除的航班编号>>>");
		String delFlightNo = sc.next();
		String message = flightService.deleteFlight(delFlightNo);
		System.out.println(message);
	}

	/**
	 * 退出
	 */
	@SuppressWarnings("resource")
	public boolean exit() {
		System.out.println("确定退出吗?y-退出程序  任意键继承执行程序");
		Scanner sc1 = new Scanner(System.in);
		String s = sc1.next();
		if (s.equalsIgnoreCase("y")) {
			flightService.closeSqlSession();
			System.out.println("程序退出成功！");
			return true;
		} else {
			System.out.println("取消了退出，程序继续运行！");
			return false;
		}
	}
}
