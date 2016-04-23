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
	
	$('input[name="deleteemployeebtn"]').click(function() {
		var lastname = $('input[name="lastname"').val();
		var firstname = $('input[name="firstname"').val();
		var fullname = lastname.trim() + " " + firstname.trim();
		if (confirm("Are you sure that you want to delete Employee: " + fullname, "Alert")) {
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
	
	$('input[name="searchclear"]').click(function(){		
		$('input[name="searchemployee"]').val('');
		
		$('table>tbody tr').remove();
		
	});
	
	$('form').bind('submit', function() {
		$(this).find(':input').removeAttr('disabled');
	});


});
