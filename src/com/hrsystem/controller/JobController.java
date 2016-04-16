package com.hrsystem.controller;

import java.io.IOException;
import java.util.Date;

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

	private JobDAO jobDAO;

	public JobController() {
		super();
		this.jobDAO = new JobDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			response.sendRedirect(page);
		}

	}

}
