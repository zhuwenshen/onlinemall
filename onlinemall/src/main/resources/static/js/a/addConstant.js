
$(function(){	
	
	$('#add_constant_form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
            	kind: {
                    message: '分类(英文)验证失败',
                    validators: {
                        notEmpty: {
                            message: '分类(英文)不能为空'
                        }
                    }
                },
                kind_name: {
                    message: '分类(中文)验证失败',
                    validators: {
                        notEmpty: {
                            message: '分类(中文)不能为空'
                        }
                    }
                },
                name: {
                    message: '变量名称(英文)验证失败',
                    validators: {
                        notEmpty: {
                            message: '变量名称(英文)不能为空'
                        }
                    }
                },
                name_cn: {
                    message: '变量名称(中文)验证失败',
                    validators: {
                        notEmpty: {
                            message: '变量名称(中文)不能为空'
                        }
                    }
                },
                value_1: {
                    message: '变量值验证失败',
                    validators: {
                        notEmpty: {
                            message: '变量值不能为空'
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
	
	
	
	/*$("#login_form_b").click(function(){
		
		
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
                            //alert(result.msg); 
                            //console.log(a);
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
	});*/
	
	
});



