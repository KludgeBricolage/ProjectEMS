/*
 * Taken from: http://thecodeplayer.com/walkthrough/jquery-multi-step-form-with-progress-bar
 * */

//jQuery time
 $(document).ready(function(){
	/*==============
	* Error Handling
	* ==============*/
	$("#next1").on( 'click', function() {
		var isValid = true;
		$('#errorFs1').parent().addClass('hidden');
		document.getElementById("errorFs1").innerHTML = "";
		if($('#dateNeeded').val() == "") {isValid = errorFs1("Select a date"); }		
		if($('#room').val() == "") { isValid = errorFs1("Select a room"); }		
		if(isValid == true) { next($(this)); }
	});
	$("#next2").on( 'click', function() {	
		var isValid = true;
		$('#errorFs2').parent().addClass('hidden');
		document.getElementById("errorFs2").innerHTML = "";
		IDs = $("#cart a[id]").map(function() { return this.id; }).get();
		if(IDs == "") {	isValid = errorFs2("Add items"); }	
		if(isValid == true) { next($(this)); }			
	});
	function errorFs1(message) { //... easy dynamic function won't work ._. 
		$("#errorFs1").parent().removeClass('hidden');
		document.getElementById("errorFs1").innerHTML += message + "<br/>";	
		return false;
	}
	function errorFs2(message) {  
		$("#errorFs2").parent().removeClass('hidden');
		document.getElementById("errorFs2").innerHTML += message + "<br/>";	
		return false;
	}
	
	
	var current_fs, next_fs, previous_fs; //fieldsets
	var left, opacity, scale; //fieldset properties which we will animate
	var animating; //flag to prevent quick multi-click glitches
	/*==============
	* Next Function
	* ==============*/
	function next(dis) {
		if(animating) return false;
		animating = true;
		
		current_fs = dis.closest('fieldset');
		next_fs = dis.closest('fieldset').next();
		//activate next step on progressbar using the index of next_fs
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
		//show the next fieldset
		next_fs.show(); 
		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now, mx) {
				//as the opacity of current_fs reduces to 0 - stored in "now"
				//1. scale current_fs down to 80%
				scale = 1 - (1 - now) * 0.2;
				//2. bring next_fs from the right(50%)
				left = (now * 50)+"%";
				//3. increase opacity of next_fs to 1 as it moves in
				opacity = 1 - now;
				current_fs.css({'transform': 'scale('+scale+')'});
				next_fs.css({'left': left, 'opacity': opacity});
			}, 
			duration: 800, 
			complete: function(){
				current_fs.hide();
				animating = false;
			}, 
			//this comes from the custom easing plugin
			easing: 'easeInOutBack'
		});
	}
	
	
	 $("input[name = 'previous']").click(function(){
		if(animating) return false;
		animating = true;
		
		current_fs = $(this).closest('fieldset');
		previous_fs = $(this).closest('fieldset').prev();
		//de-activate current step on progressbar
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
		//show the previous fieldset
		previous_fs.show(); 
		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now, mx) {
				//as the opacity of current_fs reduces to 0 - stored in "now"
				//1. scale previous_fs from 80% to 100%
				scale = 0.8 + (1 - now) * 0.2;
				//2. take current_fs to the right(50%) - from 0%
				left = ((1-now) * 50)+"%";
				//3. increase opacity of previous_fs to 1 as it moves in
				opacity = 1 - now;
				current_fs.css({'left': left});
				previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
			}, 
			duration: 800, 
			complete: function(){
				current_fs.hide();
				animating = false;
			}, 
			//this comes from the custom easing plugin
			easing: 'easeInOutBack'
		});
	});
});