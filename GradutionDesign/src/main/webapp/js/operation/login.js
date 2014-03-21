$(function(){
	 $('.carousel').carousel({
		 pause:"click"
	 });
                
        $('#myCarousel').carousel('next');
        
        $('#username').change(function(){
                $("#username").popover('hide');
        });
        $('#login_button').click(function(){
                if($('#username').val()==''){
                        $("#username").popover('toggle');
                        $("#username").focus();
                        return false;
                }
                else if($('#password').val()==''){
                        $("#password").popover('toggle');
                        $("#password").focus();
                        return false;
                }
                $('#login_info').submit();
        });
});