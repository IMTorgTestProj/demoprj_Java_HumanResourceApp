function deleteItem() {
	if (confirm("Are you sure that you want to delete this job?", "Alert")) {
	}
	return false;
}

function showJobList() {
	window.location.assign("JobList.html")
}

function showEmployeeList() {
	window.location.assign("employeelist.html")
}

function showJobAssignmentList() {
	window.location.assign("jobassignmentlist.html")
}
