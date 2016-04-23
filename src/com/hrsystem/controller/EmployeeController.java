package com.hrsystem.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
	private static final String LIST_PAGE = "/employee/list.html";
	private static final String VIEW_PAGE = "/employee/view.html";
	private static final String EDIT_PAGE = "/employee/edit.html";
	private static final String DELETE_PAGE = "/employee/delete.html";

	private EmployeeDAO employeeDAO;

	public EmployeeController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		} else if (action.equals(HRUtil.Action.LIST)) {
			request.setAttribute("employees", employeeDAO.getAllEmployees());
			page = LIST_PAGE;

		} else if (action.equals(HRUtil.Action.VIEW)) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("employee", employeeDAO.getEmployeeById(employeeId));
			page = VIEW_PAGE;
		} else if (action.equals(HRUtil.Action.EDIT)) {
			if (session.getAttribute("location") == null) {
				session.setAttribute("location", employeeDAO.loadLocation());
			}

			int employeeId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("employee", employeeDAO.getEmployeeById(employeeId));
			page = EDIT_PAGE;
		} else if (action.equals(HRUtil.Action.DELETE)) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("employee", employeeDAO.getEmployeeById(employeeId));
			page = DELETE_PAGE;
		} else if (action.equals(HRUtil.Action.REMOVE)) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			employeeDAO.deleteEmployee(employeeId);
			page = "Controller?action=list";
		} else if (action.equals(HRUtil.Action.SEARCH)) {
			String searchKey = request.getParameter("searchemployee");

			if (searchKey.trim().length() > 0)
				request.setAttribute("employees", employeeDAO.searchEmployees(searchKey));

			page = "search.html";

		}

		if (action.equals(HRUtil.Action.ADD) || action.equals(HRUtil.Action.REMOVE)) {
			response.sendRedirect(page);
		} else {
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

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

			// System.out.println(employee);
			employeeDAO.addEmployee(employee);

			page += ADD_PAGE;
		} else if (action.equals(HRUtil.Action.UPDATE)) {
			Employee employee = new Employee();

			employee.setEmployeeId(Integer.parseInt(request.getParameter("id")));

			employee.setFirstName(request.getParameter("firstname"));
			employee.setLastName(request.getParameter("lastname"));
			employee.setInitMiddle(request.getParameter("initial"));
			employee.setSsn(request.getParameter("ssn"));
			employee.setLocation(request.getParameter("location"));
			employee.setSalaryType(request.getParameter("salarytype"));
			employee.setUpdatedDate(new Date());

			System.out.println(employee);
			employeeDAO.updateEmployee(employee);

			page = "Controller?action=list";
		}

		response.sendRedirect(page);

	}

}
