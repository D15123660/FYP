function msgtemp(msg,className) {
	return '<div class="alert '+className+' alert-dismissible fade in" role="alert">' +
		'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
		'<span aria-hidden="true">&times;</span></button>' + msg +
		'</div>';
};
(function($){
	$.fn.extend({
		/* Reset verification code send button */
		rewire: function (time){
			var $this = $(this);
			var time = time || 60;
			time -= 1;
			if (time == 0) {
				$this.removeAttr("disabled");
				$this.html("get verification code");
			} else {
				$this.prop("disabled", true);
				$this.html("Resend（{0}）".format(time));
				setTimeout(function() { $this.rewire(time) }, 1000);
			}
		},
		/*
		 * Verify mobile phone number
		 * 
		 * @return 0,1,2,3
		 *		0: The verification is successful; 1: The content is empty; 2 The length is not 11 bits; 3: The format is incorrect.
		 */
		validatemobile: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1;
			} 
			
			/*else if (num.length != 11) {
				$(this)[0].focus();
				return 2;
			} else {
				var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				if(!reg.test(num)) {
					$(this)[0].focus();
					return 3;
				} else {
					return 0;
				}
			}*/
		},
		/*
		 * Verify password $phone The input box jQ object to be verified
		 * Letter + number, letter + special character, number + special character, at least 6 digits
		 * @return 0,1,2,3
		 *		0: The verification is successful; 1: The content is empty; 2: The length is too short; 3: The format is incorrect.
		 */
		validatepwd: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1
			} 
			
			/*else if (num.length < 6) {
				$(this)[0].focus();
				return 2
			} else {
				var  reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
				if(!reg.test(num)) {
					$(this)[0].focus();
					return 3;
				} else {
					return 0;
				}
			}*/
		},

	});
})(jQuery);
$(document).ready(function() {
	// Hide/show password switch
	$('.pwd-toggle').on('click',function() {
		var icon = $(this).find('.glyphicon');
		if (icon.hasClass('glyphicon-eye-open')) {
			$(this).attr("title", "Hide password").siblings('input').prop('type', 'text');
			icon.removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');
		} else if (icon.hasClass('glyphicon-eye-close')) {
			$(this).attr("title", "Show password").siblings('input').prop('type', 'password');
			icon.removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');
		}
	})
	$('#register').click(function() {$('.login').fadeOut(150,function() {$('.register').fadeIn(150)})});
	$('#resetpwd').click(function() {$('.login').fadeOut(150,function() {$('.resetpwd').fadeIn(150)})});
	$('#reglogin').click(function() {$('.register').fadeOut(150,function() {$('.login').fadeIn(150)})});
	$('#pwdlogin').click(function() {$('.resetpwd').fadeOut(150,function() {$('.login').fadeIn(150)})});
});