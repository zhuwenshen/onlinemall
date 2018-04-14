$(function(){	
	
	//初始化datetime表单
	$('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
	});
	
	
	$("#add_label_btn").click(function(){
		$(this).before('<input class="form-control" placeholder="商品标签" name="label" >');
	});
	
	$("#descriptionImg1Url").click(addDescriptionImgUrl);
	$("#update_descriptionImg1Url").click(addDescriptionImgUrl);
	
	$("#add_detailImgUrl").click(addDetailImgUrl);
	
	$("#reset_btn").click(function(){
		$("input[type='hidden']").val("");
		$("input[type='text']").val("");
		$("#descriptionImg1Url").attr("src","/onlinemall/static/img/add.png");
		$("input[name='detailImgUrl']").parent().remove();
		$("#add_detailImgUrl").css("display","block");
	});
	
		
	$("#add_form").submit(function(event){
		//阻止表单提交
	    event.preventDefault();
	    if(!$("#descriptionImg1Url_input").val()){
	    	zeroModal.error("商品图片不能为空");
	    	return;
	    }
	    if(!$("input[name='detailImgUrl']").length) {
	    	zeroModal.error("商品详情图片不能为空");
	    	return;
	    }

	    saveGoods();
	  });
	
	queryChangedRecently();
});

//商品主要图片
function addDescriptionImgUrl(){
	ms.uploadImgModal(function(){
		$("#descriptionImg1Url").attr("src",ms.picIp+$("#imgurl").val());	
		$("#descriptionImg1Url_input").val($("#imgurl").val());		
	});
}

/**
 * 添加商品详情图片
 * @returns
 */
function addDetailImgUrl(){

	if($("input[name='detailImgUrl']").length <10) {
			ms.uploadImgModal(function(){	
			if($("input[name='detailImgUrl']").length == 9) {
				$("#add_detailImgUrl").css("display","none");
			}
			
			var html = '<div class="m_img_margin_bottom">'
						+ '<img src="'+(ms.picIp+$("#imgurl").val())+'" class="img-responsive img-rounded" alt="无法加载图片">'
						+'<input value="'+$("#imgurl").val()+'"  name="detailImgUrl" type="hidden" />'
						+ '<button type="button" class="btn btn-primary" onclick=updateDetailImgUrl(this)>更换</button>'
						+ '<button type="button" class="btn btn-primary"onclick=deleteDetailImgUrl(this)>删除</button>'
						+ '</div>';		
			
			$("#add_detailImgUrl").before(html);
			
			
			
			
		});
	}else {
		zeroModal.error("商品详情图片不能超过10张");
		$("#add_detailImgUrl").css("display","none");
	}
	
}

function updateDetailImgUrl(obj){
	ms.uploadImgModal(function(){		
		$(obj).parent().children("input").val($("#imgurl").val());
		$(obj).parent().children("img").attr("src",(ms.picIp+$("#imgurl").val()));
		
	});
}

function deleteDetailImgUrl(obj){
	$(obj).parent().remove();
	$("#add_detailImgUrl").css("display","block");
}

/**
 * 保存商品
 * @returns
 */
function saveGoods(){
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/goods/addGoods", // url
		data: $("#add_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {
				
				zeroModal.success(result.msg);
				zeroModal.close(loadingUnique);
				$("#reset_btn").click();
				
				//更新最近添加内容
				queryChangedRecently();
			} else {
				zeroModal.close(loadingUnique);
				zeroModal.error(result.msg);

			}
		},
		error: function() {	
			zeroModal.close(loadingUnique);
			zeroModal.error("服务器异常！");

		}
	});
}

function queryChangedRecently(){
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/goods/queryChangedRecently", // url
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + ms.tlEmpty(list[i].name) + "</td>" +
						'<td > <img    src="'+ms.tlEmpty(list[i].descriptionImg1Url)+'" class="img-responsive img-rounded m_img_sdes"  alt="点击上传图片">  </td>' +
						"<td>" + ms.tlEmpty(list[i].shelveTime)  + "</td>" ;					
					
					tbodyHtml = tbodyHtml +						
							"</tr>";
				}

				$("#recently_tbody").html(tbodyHtml);				
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}