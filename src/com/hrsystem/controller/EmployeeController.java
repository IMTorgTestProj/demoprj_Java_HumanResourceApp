package com.hrsystem.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.EmployeeDAO;
import com.hrsystem.dao.EmployeeDAOImpl;
import com.hrsystem.model.Employee;
import com.hrsystem.util.HRUtil;

public class EmployeeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADD_PAGE = "/employee/add.html";

	private EmployeeDAO employeeDAO;

	public EmployeeController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageContext = request.getContextPath();
		String page = "";
		String action = request.getParameter("action");
		System.out.println("Operation @ GET : " + action);
		HttpSession session = request.getSession();

		if (action.equals(HRUtil.Action.ADD)) {
			if (session.getAttribute("location") == null) {
				session.setAttribute("location", employeeDAO.loadLocation());
			}

			page = pageContext + ADD_PAGE;
		}

		response.sendRedirect(page);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = request.getContextPath();
		String action = request.getParameter("action");
		System.out.println("Operation @ POST : " + action);

		if (action.equals(HRUtil.Action.ADD)) {

			Employee employee = new Employee();

			employee.setFirstName(request.getParameter("firstname"));
			employee.setLastName(request.getParameter("lastname"));
			employee.setInitMiddle(request.getParameter("initial"));
			employee.setSsn(request.getParameter("ssn"));
			employee.setLocation(request.getParameter("location"));
			employee.setSalaryType(request.getParameter("salarytype"));
			employee.setCreatedDate(new Date());

			System.out.println(employee);
			employeeDAO.addEmployee(employee);

			page += ADD_PAGE;
		}

		response.sendRedirect(page);

	}

}
