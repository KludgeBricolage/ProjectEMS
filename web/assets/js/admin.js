$(document).ready(function() {
	//Semantic
	$('.menu .item').tab();
	//Functions
	$('#btnSubmit').on( 'click', function () {
		var category = $('#category').val();
		var brand = $('#brand').val();
		var serialNo = $('#serialNo').val();
		var propertyNo = $('#propertyNo').val();
		
		var sendData = category + "," + brand + "," + serialNo + "," + propertyNo;
		$('#sendData').val(sendData);
	});
	
	$(function() {
		var count = $('#reqCount').val();

		var tru = "true";
		var fal = "false";
		
		for(cnt = 0; cnt <= count; cnt++) {
			var decline = $('#decline' + cnt).text().trim();
			
			if(decline === tru){
				$('#decline' + cnt).html("<button type='submit' name='response' class='ui small disabled button' value='D" + $('#request' + cnt).text().trim() +"'>Decline</button>");				
			}
			if(decline === fal){
				$('#decline' + cnt).html("<button type='submit' name='response' class='ui small button' value='D" + $('#request' + cnt).text().trim() +"'>Decline</button>");
				
				var allow = $('#allow' + cnt).text().trim();
				
				if(allow === fal){
					$('#allow' + cnt).html("<button type='submit' name='response' class='ui small button' value='A" + $('#request' + cnt).text().trim() +"'>Allow</button>");
					$('#return' + cnt).html("<button type='submit' name='response' class='ui small disabled button' value='R" + $('#request' + cnt).text().trim() +"'>Return</button>");
				}
				
				if(allow === tru){
					$('#allow' + cnt).html("<button type='submit' name='response' class='ui small disabled button' value='A" + $('#request' + cnt).text().trim() +"'>Allow</button>");
					$('#decline' + cnt).html("<button type='submit' name='response' class='ui small disabled button' value='D" + $('#request' + cnt).text().trim() +"'>Decline</button>");
					$('#return' + cnt).html("<button type='submit' name='response' class='ui small button' value='R" + $('#request' + cnt).text().trim() +"'>Return</button>");
				}
			}		
		}
		
		var itemCount = $('#itemCount').val();
		for(cnt = 0; cnt <= itemCount; cnt++) {
			var available = $('#available' + cnt).text().trim();
			if(available == tru) {
				$('#available' + cnt).html("<button type='submit' name='available' class='ui small button' value='T" + cnt +"'>Available</button>");
			}
			if(available == fal) {
				$('#available' + cnt).html("<button type='submit' name='available' class='ui small button' value='F" + cnt +"'>Not Available</button>");
			}
			
		}
	})
});