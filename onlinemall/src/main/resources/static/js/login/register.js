$(function(){	
	
	$(".m_1").css("height",$(window).height()); 
	
	
	$('#register_form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                phone: {
                    message: '手机验证失败',
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        /*
						 * stringLength: { min: 6, max: 30, message:
						 * '用户名长度必须在6到30之间' },
						 */
                        threshold :  11 , // 有11字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，11字符以上才开始）
                        remote: {// ajax验证。server result:{"valid",true or
									// false} 向服务发送当前input
									// name值，获得一个json数据。例表示正确：{"valid",true}
                            url: 'register/valid/phone',// 验证地址
                            message: '手机号已经注册',// 提示消息
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
                },                
                repassword: {
                	 message: '密码无效',
                     validators: {
                         notEmpty: {
                             message: '重复密码不能为空'
                         },
                         identical: {// 相同
                             field: 'password',
                             message: '两次密码不一致'
                         }/*
							 * , different: {//不能和用户名相同 field: 'username',
							 * message: '不能和用户名相同' }, regexp: {//匹配规则 regexp:
							 * /^[a-zA-Z0-9_\.]+$/, message: 'The username can
							 * only consist of alphabetical, number, dot and
							 * underscore' }
							 */
                     }
                }
            }
        });
	
	$("#register_form_b").click(function(){
		
		var v = $("#register_form").data('bootstrapValidator');
		v.validate();
    	if(v.isValid()) {
    		 $("#register_form_b").attr('disabled',"true");
    		$.ajax({
                // 几个参数需要注意一下
                    type: "POST",// 方法类型
                    dataType: "json",// 预期服务器返回的数据类型
                    url: "register" ,// url
                    data: $('#register_form').serialize(),
                    success: function (result) {
                        //console.log(result);// 打印服务端返回的数据(调试用)                       
                       if (result.status) {
                            alert(result.msg);
                            window.location.href="login";
                        }else {
                        	alert(result.msg);
                        }
                    },
                    error : function() {
                        alert("服务器异常！");
                        $("#register_form_b").removeAttr("disabled");
                    }
                });
    		
    	}
	});
});


