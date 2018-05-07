$(function(){	
	
	$("#add_label_btn").click(function(){
		$(this).before('<input class="form-control" placeholder="商品标签" name="label" >');
	});
	
	$("#descriptionImg1Url").click(addDescriptionImgUrl);
	$("#update_descriptionImg1Url").click(addDescriptionImgUrl);
	
	$("#add_detailImgUrl").click(addDetailImgUrl);	
	
	$("#add_form").submit(function(event){
		//阻止表单提交
	    event.preventDefault();
	    if(!$("#descriptionImg1Url_input").val()){
	    	zeroModal.error("商品图片不能为空");
	    	return false;
	    }
	    if(!$("input[name='detailImgUrl']").length) {
	    	zeroModal.error("商品详情图片不能为空");
	    	return false;
	    }

	     return saveGoods();
	});	
	
	initGoods();
	
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
		url: ms.path + "/m/goods/updateGoods", // url
		data: $("#add_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {
				
				zeroModal.success(result.msg);
				zeroModal.close(loadingUnique);				
				zeroModal.close($("#modelId").val());		
				refreshPage();
				
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

function initGoods(){
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	//var id = ;
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/goods/getUpdateGoods?id="+$("#goodsId").val(), // url
		success: function(result) {
			var data = result.data;	
			console.log(data);
			if(result.status) {
				
				//赋值
				$("#goodsName").val(data.name);
				$("#goodsDescription").val(data.description);
				
				if(data.descriptionImg1Url) {
					$("#descriptionImg1Url").attr("src", data.descriptionImg1Url);	
					$("#descriptionImg1Url_input").val( ms.cutUrlIp(data.descriptionImg1Url));	
				}
				
				var i = 0;
				var label = data.labelList; 
				if(!label.length){
					$("#add_label_btn").before('<div class="form-inline">'
	           		+'<input class="form-control" placeholder="商品标签" required="required" onclick="updateGoodsLabel(this);">  '                                         
	           		+'<input type="hidden" name="label" >'
	           		+'<input class="btn btn-danger btn-xs" type="button" onclick="deleteLabel(this);" value="删除" >' 
	           		+'</div>');
				}
				
				var labelHtml = "";
				for(i = 0;i<label.length;i++) {
					labelHtml= labelHtml+ '<div class="form-inline">'
	           		+'<input class="form-control" placeholder="商品标签" value="'+label[i].allName+'" required="required" onclick="updateGoodsLabel(this);">  '                                         
	           		+'<input type="hidden" name="label" value="'+label[i].id+'">'
	           		+'<input class="btn btn-danger btn-xs" type="button" onclick="deleteLabel(this);" value="删除" >' 
	           		+'</div>';
					//labelHtml= labelHtml+ '<input class="form-control" placeholder="商品标签" name="label" value="'+label[i].allName+'" >';
				}
				$("#add_label_btn").before(labelHtml);
				
				if(data.shelveTime) {
					//初始化datetime表单
					$('#shelveTime_datetime').datetimepicker({
				        language:  'zh-CN',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
				        showMeridian: 1,
				        initialDate:data.shelveTime
					});
					$("#shelveTime_datetime input").val(data.shelveTime);
					$("#shelveTime").val(data.shelveTime);
				}
				
				$("#unshelveTime_datetime input").val(" ");
				$("#unshelveTime").val(" ");
				
				if(data.unshelveTime) {
					$('#unshelveTime_datetime').datetimepicker({
				        language:  'zh-CN',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
				        showMeridian: 1,
				        initialDate:data.unshelveTime
					});
					$("#unshelveTime_datetime input").val(data.unshelveTime);
					$("#unshelveTime").val(data.unshelveTime);
				}
				
				if(data.producer){
					$("#goodsProducer").val(data.producer);
				}
				
				if(data.specification){
					$("#goodsSpecification").val(data.specification);
				}
				//图片详情
				var imgUrl = data.detailImgUrl;
				var j=0;
				var imgHtml = "";
				for(j=0; j<imgUrl.length; j++){
					imgHtml = imgHtml+ '<div class="m_img_margin_bottom">'
								+ '<img src="'+imgUrl[j]+'" class="img-responsive img-rounded" alt="无法加载图片">'
								+'<input value="'+(ms.cutUrlIp(imgUrl[j]))+'"  name="detailImgUrl" type="hidden" />'
								+ '<button type="button" class="btn btn-primary" onclick=updateDetailImgUrl(this)>更换</button>'
								+ '<button type="button" class="btn btn-primary"onclick=deleteDetailImgUrl(this)>删除</button>'
								+ '</div>';							
					
				}
				if(imgUrl.length >= 10) {
					$("#add_detailImgUrl").css("display","none");
				}
				$("#add_detailImgUrl").before(imgHtml);
				
				
				
				zeroModal.close(loadingUnique);				
				
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

function updateGoodsLabel(e){
	
	var zm = zeroModal.show({
        title: "修改标签",
        url:ms.path +"/m/goods/updateGoodsLabel",
        width:"500px",
        height:"80%",
        max:true,
        onComplete:function(){
        	$("#update_labelId").val($(e).next().val());
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		$("#label_modelId").val(zm);
		submitUpdateLabelForml(e);
		return false;
	}
}

function deleteLabel(e){
	$(e).parent().remove();	
}