$(document).ready(function() {

	$('input[name="jobnumber"').focus();
	
	$('input[name="addjobbtn"]').click(function() {

		var validation = true;
		var jobnumber = $('input[name="jobnumber"]').val();
		var jobname = $('input[name="jobname"]').val();

		if (jobnumber.trim() == "") {
			alert("Enter Job Number", "Alert");
			$('input[name="jobnumber"]').val('');
			$('input[name="jobnumber"]').focus();
			validation = false;
		} else if (jobname.trim() == "") {
			alert("Enter Job Name", "Alert");
			$('input[name="jobname"]').val('');
			$('input[name="jobname"]').focus();
			validation = false;
		}

		if (validation) {
			$('form').submit();
		}

	});

	$('input[name="clear"]').click(function() {
		$('input[name="jobnumber"').val('');
		$('input[name="jobname"').val('');
		$('textarea[name="description"').val('');
		$('input[name="jobnumber"').focus();
	});

	$('form').bind('submit', function() {
		$(this).find(':input').removeAttr('disabled');
	});

});
