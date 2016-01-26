$(document).ready(function(){  
	//Date picker ;; http://www.daterangepicker.com/
    $(function() {
        $('input[name="date"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true
        }, 
        function() {
        	dateNeeded = $("#dateNeeded").val();
        	arrayDate = dateNeeded.split('/');
        	dateDeadline = arrayDate[0] + "/" + (+arrayDate[1] + 1) + "/" + arrayDate[2];
        	alert("Return deadline is 24 hours: " + dateDeadline);
        });
    });
    
    
    
    
});