$(document).ready(function() {
	
	$('input[name="addassignmentbtn"],input[name="updateassignmentbtn"]').click(function() {
		var employeeId = $('select[name="emp_ssn"]').val();
		var jobId = $('select[name="sel_jobnumber"]').val();
		
		//alert("Click Update : "+employeeId+"\t"+jobId);
		var validation = true;
		
		if(employeeId == 0){
			alert("Select Employee", "Alert");
			validation = false;
		}else if(jobId == 0){
			alert("Select Job", "Alert");
			validation = false;
		}
		
		if(validation){
			$('form').submit();
		}
	});
	
	$('input[name="deleteassignmentbtn"]').click(function(){
		if (confirm("Are you sure that you want to delete Job Assignment", "Alert")) {
			$('form').submit();
		}
	});
	

	$('#emp_ssn').on('change', function() {
		var id = $(this).val();
		var ssn = $(this).find('option:selected').text();
		//alert('SSN changed : ' + id +"\t:"+ssn);
		
		if(id == 0){
			$('input[name="clear"]').trigger('click');
		}
		
		var dataString = "id="+id+"&ssn="+ssn+"&action=showEmployeeInfo";
		
		$.ajax({
			type: "GET",
			url:"Controller",
			data: dataString,
			dataType: "json",
			success: function(data, textStatus, jqXHR){
				
				if(data.success){
					console.log("REQUEST SUCCESS");
					//alert("Request Success...");
					//$('input[name="clear"]').trigger('click');
					

					$('input[name="firstname"]').val(data.EmployeeInfo.firstName);
					$('input[name="lastname"]').val(data.EmployeeInfo.lastName);
					$('input[name="mi"]').val(data.EmployeeInfo.initMiddle);
					$('input[name="location"]').val(data.EmployeeInfo.location);
					$('input[name="salarytype"]').val(data.EmployeeInfo.salaryType);	
					
					
					//Clear Job field set
					$('input[name="jobname"]').val('');
					$('textarea[name="description"]').val('')
					
					$('select[name="sel_jobnumber"] option').remove();
					$('select[name="sel_jobnumber"]').append($('<option></option>').attr('value',0).text("--Select--"));
					
					var ava_jobs = data.availableJobs;
					$.each(ava_jobs, function( index, job ) {
						console.log( index + ": " + job.jobNumber );
						$('select[name="sel_jobnumber"]').append($('<option></option>').attr('value',job.jobId).text(job.jobNumber));
					});
					

					
				}else{
					console.log("REQUEST FAIL AT SERVER. PLEASE CEHCK WITH ADMINISTRATION.");
					//alert("Request Fail...");
				}
			},
			error:  function(jqXHR, textStatus, error){
				console.log("Server ERROR : "+textStatus);
				alert("SERVER ERROR : "+textStatus);
			}
		});
		
	});
	
	$('select[name="sel_jobnumber"]').on('change', function() {
		var id = $(this).val();
		var jobnumber = $(this).find('option:selected').text();
		//alert('SSN changed : ' + id +"\t:"+jobnumber);
		
		if(id == 0){
			$('input[name="jobname"]').val('');
			$('textarea[name="description"]').val('')			
		}
		
		var dataString = "id="+id+"&jobnumber="+jobnumber+"&action=showJobInfo";
		
		$.ajax({
			type: "GET",
			url:"Controller",
			data: dataString,
			dataType: "json",
			success: function(data, textStatus, jqXHR){
				
				if(data.success){
					console.log("REQUEST SUCCESS");
					//alert("Request Success...");
					//$('input[name="clear"]').trigger('click');
					
					$('input[name="jobname"]').val(data.JobInfo.jobName);
					$('textarea[name="description"]').val(data.JobInfo.description);
										
				}else{
					console.log("REQUEST FAIL AT SERVER. PLEASE CEHCK WITH ADMINISTRATION.");
					//alert("Request Fail...");
				}
			},
			error:  function(jqXHR, textStatus, error){
				console.log("Server ERROR : "+textStatus);
				alert("SERVER ERROR : "+textStatus);
			}
		});

		
	});

	$('input[name="clear"]').click(function() {
		//alert('clear button clicked..');
		$('select[name="emp_ssn"]').val(0);
		
		$('input[name="firstname"]').val('');
		$('input[name="lastname"]').val('');
		$('input[name="mi"]').val('');
		$('input[name="location"]').val('');
		$('input[name="salarytype"]').val('');
		
		$('select[name="sel_jobnumber"] option').remove();
		$('select[name="sel_jobnumber"]').append($('<option></option>').attr('value',0).text("--Select--"));
		
		$('input[name="jobname"]').val('');
		$('textarea[name="description"]').val('')

	});
	
	$('form').bind('submit', function() {
		$(this).find(':input').removeAttr('disabled');
	});


});