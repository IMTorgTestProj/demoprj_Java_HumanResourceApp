package com.hrsystem.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HRUtil {

	/* Common Utility Fields */
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public class Action {
		public static final String ADD = "add";
		public static final String LIST = "list";
		public static final String VIEW = "view";
		public static final String EDIT = "edit";
		public static final String UPDATE = "update";
		public static final String DELETE = "delete";
		public static final String REMOVE = "remove";
		public static final String SEARCH = "search";
	}

	public class Ajax {
		public static final String LOAD_EMP_INFO = "showEmployeeInfo";
		public static final String LOAD_JOB_INFO = "showJobInfo";

	}

	public class Status {
		public static final int Active = 1;
		public static final int Inactive = 0;
	}

	/* Common Utility Methods */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat(HRUtil.DATE_FORMAT);

		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}

}
