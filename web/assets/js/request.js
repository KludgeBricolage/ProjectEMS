$(document).ready(function() {
	//Semantic
    $('.message .close').on('click', function() { $(this).closest('.message').transition('fade'); });
    $('.ui.dropdown').dropdown();
    $('.sortable.table').tablesort();
	$('#catSearch').dropdown();
    
    $(function() {
		var count = $('#catCount').val();
		for(cnt = 0; cnt < count; cnt++) {
			$('#catSearch').append("<option value=" + $("#catItem" + cnt).val() + ">" + $("#catItem" + cnt).val() + "</option>"); 
	    }
		
		$('#catSearch').on("change", function() {
	        $('#tableItems tr').each(function() {
	        	var secondColumn = $(this).find("td").first().next();
	    	    var row = secondColumn.parent();
	    	    
	    	    if($('#catSearch').val() == "All") {
	    	    	row.show();
	    	    }
	    	    else if(secondColumn.text() != $('#catSearch').val()){
	    	        row.hide();
	    	    } else {
	    	    	row.show();
	    	    }
	    	});
		});
	});
	//Date picker ;; http://www.daterangepicker.com/
    var dateNeeded, arrayDate, dateDeadline, deadlineDuration;
    $(function() {
        $('input[name="date"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true,	
        	minDate: new Date()
        }, 
        function() {
        	dateNeeded = $("#dateNeeded").val();
        	dateNeeded = $("#dateNeeded").val();
        	deadlineDuration = $("#deadlineDuration").val();
    		arrayDate = dateNeeded.split('/');
//    		dateDeadline = arrayDate[0] + "/" + (+ + +deadlineDuration) + "/" + ;
    		dateDeadline = new Date(arrayDate[2], arrayDate[0], arrayDate[1]);
    		dateDeadline.setDate(dateDeadline.getDate() + +deadlineDuration);
    		dateDeadline = String(('0' + dateDeadline.getMonth()).slice(-2) + "/" + ('0' + dateDeadline.getDate()).slice(-2) + "/" + dateDeadline.getFullYear());
        });
    });    
    //Step 2
    $('#tableItems tbody').on( 'click', 'tr', function () {
	 	  var itemId = $(this).find('#itemId').text();
	 	  var item = $(this).find('#item').text();
	
		  $('#cart').append("<a class='item' id=" + itemId+ " name=" + item + ">" + item + " (" + itemId + ")</a>");
			
		  $('#tableItems tr').each(function() {
			  var secondColumn = $(this).find("td").first();
  	      
			  if(secondColumn.text() == itemId){
  			  //Source: http://www.safnet.com/writing/tech/2012/07/manipulating-table-rows-with-jquery.html#
  			  var row = $(this).closest("tr");
				  row.detach();
				  $("#tableDead").append(row);
			  }
		  });
    });
   
    $('#cart').on( 'click', 'a', function () {
    	var itemId = $(this).attr("id");
		$(this).remove();
		$('#tableDead').find('tr').each(function (i, el) {
			//Source: http://stackoverflow.com/a/17120711
			var tds = $(this).find('td'),
			firstColumn = tds.eq(0).text();
			if(firstColumn == itemId) {
				var row = $(this).closest("tr");
				row.detach();
				$("#tableItems").append(row);
			}
		});
    });
    //Step 3
    
    var room, IDs, ids, names, user;
    
    $('#next2').on( 'click', function(){
	    
		user = $('#user').val();
		
		room = $("#room").val();
		IDs = $("#cart a[id]").map(function() { return this.id; }).get();	
		ids = "";
		IDs.forEach(function(line) { ids += line + ";";	} );
		
		names = $("#cart a[name]").map(function() { return this.name; }).get();
		
		function items() {
			var item = "";
			for(cnt = 0; cnt < IDs.length; cnt++) {
				item += "" + names[cnt] + '(' + IDs[cnt] + ')<br/>';
			}
			return item;	
		}
		$row = $('<tr>'
			   + '	<td>User</td>'
			   + '	<td>' + user + '</td>'
			   + '</tr>'
			   + '<tr>'
			   + '	<td>Room</td>'
			   + '	<td>' + room + '</td>'
			   + '</tr>'
			   + '<tr>'
			   + '	<td>Date Needed</td>'
			   + '	<td>' + dateNeeded + '</td>'
			   + '</tr>'
			   + '<tr>'
			   + '	<td>Deadline Date</td>'
			   + '	<td>' + dateDeadline + '</td>'
			   + '</tr>'
			   + '<tr>'
			   + '	<td>Requested Items</td>'
			   + '	<td>' + items() + '</td>'
			   + '</tr>');
		$("#reviewTable").empty();
		$("#reviewTable").append($row);
    });
	
	$("#subBtn").click(function(e){
		var data = user + "," + dateNeeded + "," + dateDeadline + "," + room + "," + ids;
		$("#data").val(data);
    });
});