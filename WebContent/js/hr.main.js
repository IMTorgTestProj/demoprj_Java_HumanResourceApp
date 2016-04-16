function deleteItem() {
	if (confirm("Are you sure that you want to delete this job?", "Alert")) {
	}
	return false;
}

function showJobList() {
	window.location.assign(getContextPath() + "/job/Controller?action=list");
}

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
