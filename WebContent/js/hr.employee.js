$(document).ready(function() {

	$('input[name="firstname"').focus();

	$('input[name="addemployeebtn"],input[name="updateemployeebtn"]').click(function() {

		var validation = true;
		var firstName = $('input[name="firstname"]').val();
		var lastName = $('input[name="lastname"]').val();
		var ssn = $('input[name="ssn"]').val();

		if (firstName.trim() == "") {
			alert("Enter First Name", "Alert");
			$('input[name="firstname"]').val('');
			$('input[name="firstname"]').focus();
			validation = false;
		} else if (lastName.trim() == "") {
			alert("Enter Last Name", "Alert");
			$('input[name="lastname"]').val('');
			$('input[name="lastname"]').focus();
			validation = false;
		} else if (ssn.trim() == "") {
			alert("Enter SSN", "Alert");
			$('input[name="ssn"]').val('');
			$('input[name="ssn"]').focus();
			validation = false;
		}

		if (validation) {
			$('form').submit();
		}

	});

	$('input[name="clear"]').click(function() {
		$('input[name="firstname"').val('');
		$('input[name="lastname"').val('');
		$('input[name="initial"').val('');
		$('input[name="ssn"').val('');
		$('select[name="location"').find('option:eq(0)').prop('selected', true);
		$('input:radio[name=salarytype][value=Hourly]').click();

		$('input[name="firstname"').focus();
	});

});