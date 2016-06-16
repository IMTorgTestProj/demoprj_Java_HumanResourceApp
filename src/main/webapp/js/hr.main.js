/**    
* pulvrtag:{ "Func": "Add_Job" } 
*/
function deleteItem() {
	if (confirm("Are you sure that you want to delete this job?", "Alert")) {
	}
	return false;
}
/**    
* pulvrtag:{ "Func": "Add_Job" } 
*/
function showJobList() {
	window.location.assign(getContextPath() + "/job/Controller?action=list");
}

function showEmployeeList() {
	window.location.assign(getContextPath() + "/employee/Controller?action=list");
}

function showAssignmentList() {
	window.location.assign(getContextPath() + "/assignment/Controller?action=list");
}

/**    
* pulvrtag:{ "Func": "Add_Job" } 
*/
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
