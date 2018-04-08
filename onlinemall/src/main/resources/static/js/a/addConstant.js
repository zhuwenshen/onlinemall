$(function() {

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
			kindName: {
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
			nameCn: {
				message: '变量名称(中文)验证失败',
				validators: {
					notEmpty: {
						message: '变量名称(中文)不能为空'
					}
				}
			},
			value1: {
				message: '变量值验证失败',
				validators: {
					notEmpty: {
						message: '变量值不能为空'
					}
				}
			}
		}
	});

	/*function checkKind(){
		var kVal = $("#kind").val();
		$.ajax({
			type: "GET", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/c/getKindNameByKind", // url
			data: {
				kind: kVal
			},
			success: function(result) {
				console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					console.log(result.data);
					$("#kindName").attr("value", result.data);
					//$("#kindName").attr("disabled", "disabled");
					v.validateField('kindName');;
					//$("#name").focus();
				} else {
					// alert(result.msg);
					$("#kindName").removeAttr("disabled");
					v.validate();
					
				}
			},
			error: function() {
				alert("服务器异常！");
				$("#kindName").removeAttr("disabled");
			}
		});
	}*/
	
	/*$("#kind").blur(function() {
		v.validate();
		checkKind();
	});
	$("#kind").focusin(function(){
		checkKind();
	});	*/
	

	$("#add_constant_form_b").click(function() {

		v.validate();
		if(v.isValid()) {
			$("#add_constant_form_b").attr('disabled', "true");
			$.ajax({				
				type: "POST", // 方法类型
				dataType: "json", // 预期服务器返回的数据类型
				url: ms.path + "/a/addConstant", // url
				data: $('#add_constant_form').serialize(),
				success: function(result) {
					console.log(result); // 打印服务端返回的数据(调试用)
					if(result.status) {
						//alert(result.msg);
						ms.append_alert("success",true,result.msg,"msg_alert_div");
						// console.log(a);
						// window.location.href=result.data.uri+"?t="+result.data.t;
						$("#add_constant_form_b").attr("disabled");
						$("#add_constant_form_next").removeAttr("disabled");
						getConstantChangedLately();
					} else {
						//alert(result.msg);
						ms.append_alert("danger",true,result.msg,"msg_alert_div");
						$("#add_constant_form_b").removeAttr("disabled");
					}
				},
				error: function() {
					//alert("服务器异常！");
					ms.append_alert("danger",true,"服务器异常！","msg_alert_div");
					$("#add_constant_form_b").removeAttr("disabled");
				}
			});
		}
	});

	function getConstantChangedLately() {		
		$.ajax({			
			type: "GET", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/a/getConstantChangedLately", // url    
			success: function(result) {
				console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					var htm = " ";
					var list = result.data;
					var i = 0;
					for(i = 0; i < list.length; i++) {
						htm = htm + "<tr>" +
							" <td>" + list[i].kind + "</td>" +
							" <td>" + list[i].kindName + "</td>" +
							" <td>" + list[i].name + "</td>" +
							" <td>" + list[i].nameCn + "</td>" +
							"<td>" + list[i].value1 + "</td>" +
							"<td>" + ms.translateIcon(list[i].useful) + "</td>" +
							"</tr>";
						$("#constant_tbody").html(htm);
					}

				} else {
					//alert(result.msg);
					ms.append_alert("danger",true,result.msg,"msg_alert_div");

				}
			},
			error: function() {
				//alert("服务器异常！");
				ms.append_alert("danger",true,"服务器异常！","msg_alert_div");
				$("#add_constant_form_b").removeAttr("disabled");
			}
		});
	}

	$("#add_constant_form_next").click(function(){
		$("#name").val("");
		$("#nameCn").val("");
		$("#value1").val("");
		$("#remake").val("");
		$("#add_constant_form_b").removeAttr("disabled");
		$("#add_constant_form_next").attr("disabled");
		checkForm();
	});	
	
	$("#reset_b").click(function(){
		v.resetForm();		
	});
	
	var v = $("#add_constant_form").data('bootstrapValidator');
	function checkForm(){
		$("#kind").blur();
		v.resetForm();
		v.validate();
	}
	
	
	checkForm();
	getConstantChangedLately();
});