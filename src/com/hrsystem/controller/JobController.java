package com.hrsystem.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.JobDAO;
import com.hrsystem.dao.JobDAOImpl;
import com.hrsystem.model.Job;
import com.hrsystem.util.HRUtil;

public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADD_PAGE = "/job/add.html";
	private static final String LIST_PAGE = "/job/list.html";
	private static final String VIEW_PAGE = "/job/view.html";
	private static final String EDIT_PAGE = "/job/edit.html";
	private static final String DELETE_PAGE = "/job/delete.html";

	private JobDAO jobDAO;

	public JobController() {
		super();
		this.jobDAO = new JobDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageContext = request.getContextPath();
		String page = "";
		String action = request.getParameter("action");

		System.out.println("Operation @ GET : " + action);

		if (action.equals(HRUtil.Action.LIST)) {
			request.setAttribute("jobs", jobDAO.getAllJobs());
			page = LIST_PAGE;
		} else if (action.equals(HRUtil.Action.VIEW)) {
			int jobId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("job", jobDAO.getJobById(jobId));
			page = VIEW_PAGE;
		} else if (action.equals(HRUtil.Action.EDIT)) {
			int jobId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("job", jobDAO.getJobById(jobId));
			page = EDIT_PAGE;
		} else if (action.equals(HRUtil.Action.DELETE)) {
			int jobId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("job", jobDAO.getJobById(jobId));
			page = DELETE_PAGE;
		} else if (action.equals(HRUtil.Action.REMOVE)) {
			int jobId = Integer.parseInt(request.getParameter("id"));
			jobDAO.deleteJob(jobId);
			// request.setAttribute("jobs", jobDAO.getAllJobs());
			page = "Controller?action=list";
		} else if (action.equals(HRUtil.Action.SEARCH)) {
			String searchKey = request.getParameter("searchjob");

			if (searchKey.trim().length() > 0)
				request.setAttribute("jobs", jobDAO.searchJobs(searchKey));

			request.setAttribute("searchKey", searchKey);
			page = "search.html";

		}

		if (action.equals(HRUtil.Action.REMOVE)) {
			response.sendRedirect("Controller?action=list");
		} else {
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
			// view.include(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getContextPath();
		String action = request.getParameter("action");
		System.out.println("Operation @ POST : " + action);

		if (action.equals(HRUtil.Action.ADD)) {

			Job job = new Job();

			job.setJobNumber(request.getParameter("jobnumber"));
			job.setJobName(request.getParameter("jobname"));
			job.setDescription(request.getParameter("description"));
			job.setCreatedDate(new Date());

			jobDAO.addJob(job);

			// RequestDispatcher view =
			// request.getRequestDispatcher("/job/add.html");
			// view.forward(request, response);

			page += ADD_PAGE;
			// response.sendRedirect(page);
		} else if (action.equals(HRUtil.Action.UPDATE)) {
			Job job = new Job();

			job.setJobId(Integer.parseInt(request.getParameter("id")));
			job.setJobNumber(request.getParameter("jobnumber"));
			job.setJobName(request.getParameter("jobname"));
			job.setDescription(request.getParameter("description"));
			job.setUpdatedDate(new Date());

			System.out.println(job);
			jobDAO.updateJob(job);

			// request.setAttribute("jobs", jobDAO.getAllJobs());
			page = "Controller?action=list";
			// RequestDispatcher view = request.getRequestDispatcher(page);
			// view.forward(request, response);
		}
		response.sendRedirect(page);

	}

}
