 /**
 * written by: CHIAJO LIN
 */

$('.form').find('input, text').on('keyup', function (e){ 
	var $this = $(this),
    label = $this.prev('label');
	
	if (e.type == 'keyup') {
		if ($this.val() == '') {
			label.removeClass('active highlight'); //active: label fly down
		} 
		else {
			label.addClass('active highlight');
		}
	} 
});

$('.tab').find('a').on('click', function (e){
	e.preventDefault();
  
	$(this).parent().addClass('active');
	$(this).parent().siblings().removeClass('active');
  
	target = $(this).attr('href');
	$('.tab-content > div').not(target).hide();  
	$(target).fadeIn(650);
  
});