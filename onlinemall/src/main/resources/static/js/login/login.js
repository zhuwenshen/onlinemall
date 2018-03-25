
$(function(){	
	
	$('#login_form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                login_id: {
                    message: '账号或手机号验证失败',
                    validators: {
                        notEmpty: {
                            message: '账号或手机号不能为空'
                        },
                        /*
						 * stringLength: { min: 6, max: 30, message:
						 * '用户名长度必须在6到30之间' },
						 */
                        threshold :  11 , // 有11字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，11字符以上才开始）
                        remote: {// ajax验证。server result:{"valid",true or
									// false} 向服务发送当前input
									// name值，获得一个json数据。例表示正确：{"valid",true}
                            url: 'login/valid/login_id',// 验证地址
                            message: '账号或手机号还没注册',// 提示消息
                            delay :  200,// 每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'// 请求方式
                             
                        }
                    }
                },
                password: {
                	message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码长度必须在6到30之间'
                        }
                    }
                }
            }
        });
	
	var form =  $("#login_form").data('bootstrapValidator');
	$("#login_id").focusout(function(){
		form.validateField("login_id");
	});
	$("#password").focus(function(){
		form.validate();
	});
	
	/*function a(value){
		alert(value)
	}
	
	function b(fun , value) {
		fun(value);
	}*/
	
	
	
	$("#login_form_b").click(function(){
		
		
		var v = $("#login_form").data('bootstrapValidator');
		var a = v.validate();
    	if(v.isValid()) {
    		 $("#login_form_b").attr('disabled',"true");
    		$.ajax({
                // 几个参数需要注意一下
                    type: "POST",// 方法类型
                    dataType: "json",// 预期服务器返回的数据类型
                    url: "login" ,// url
                    data: $('#login_form').serialize(),
                    success: function (result) {
                        //console.log(result);// 打印服务端返回的数据(调试用)                       
                       if (result.status) {
                            alert(result.msg); 
                            console.log(a);
                            window.location.href=result.data.uri+"?t="+result.data.t;
                        }else {
                        	alert(result.msg);
                        	 $("#login_form_b").removeAttr("disabled");
                        }
                    },
                    error : function() {
                        alert("服务器异常！");
                        $("#login_form_b").removeAttr("disabled");
                    }
                });    		
    	}
	});
	
	
});



