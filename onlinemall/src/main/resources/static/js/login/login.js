$(function(){
	$('#login_form').submit(function(){
		$(this).ajaxSubmit({
			success:function(data){
				if(data.status){
					window.location="index";
				}else{
					$("#error").html(data.msg);
				}
			}
		});
		
		return false;
	});
});