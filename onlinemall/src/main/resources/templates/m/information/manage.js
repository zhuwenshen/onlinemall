$(function() {
	
	showMerchantInformation();
	$("#update_merchantPortraitUrl_btn").click(uploadMerchantPortraitUrl);
	
	$("#updateImformation_btn").click(updateMerchantImformation);
		

});


var imgLen = 0;

function showMerchantInformation(){
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/information/showMerchantInformation", // url
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			var data = result.data;
			if(result.status) {
				//赋值
				if(data.merchantPortraitUrl) {
					$("#merchantPortraitUrl").attr("src",data.merchantPortraitUrl);
				}
				
				if(data.num) {
					$("#num").val(data.num);
				}
				
				if(data.nameCn) {
					$("#nameCn").val(data.nameCn);
				}
				
				if(data.phone) {
					$("#phone").val(data.phone);
				}
				
				$("#fund").val(ms.translateZero(data.fund));
				
				$("#integral").val(ms.translateZero(data.integral));
				
				$("#allIntegral").val(ms.translateZero(data.allIntegral));
				
				
				if(data.certificationLevelTL) {
					$("#certificationLevel").val(data.certificationLevelTL);
				}
				
				if(data.detailedAddress) {
					$("#detailedAddress").val(data.detailedAddress);
				}
				
				initImgDes(data);
				
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

function uploadMerchantPortraitUrl(){
	zeroModal.show({
        title: '上传头像',
        url:ms.path +"/uploadPortrait",
        width:"300px",
        height:"300px",
        max:true,
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle:"确认更换",
        okFn:updateMerchantPortraitUrl
        
    });
}

function updateMerchantPortraitUrl(){
	var imgurl = $("#imgurl").val();
	
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/information/updateMerchantPortrait", // url
		data: {imgurl : imgurl},
		success: function(result) {
			var data = result.data;
			if(result.status) {
				//赋值
				if(data) {
					$("#merchantPortraitUrl").attr("src",data);
				}
				zeroModal.success(result.msg);
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

function updateMerchantImformation(){
	
	$("#phone").removeAttr("disabled");	
	
	$("#detailedAddress").removeAttr("disabled");
	
	$("#updateImformation_btn").attr("hidden");
	
	$("#sumbit_updateImformation_btn").removeAttr("hidden");
}

function sumbitUpdateImformation(){
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/information/updateMerchantImformation", // url
		data: $("#infor_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {				
				
				zeroModal.success(result.msg);
				zeroModal.close(loadingUnique);
				showMerchantInformation();
				
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

function initImgDes(data){
	if(data){		
		var imgHtml = "";
		imgLen = 0;
		//imgHtml =  imgHtml + addImgDes("http://39.106.107.122/img/4328/6684/2018041111528D6HWC0ZC0.png");
		
		if(data.describingImg1Url){
			imgLen++;
			imgHtml =  imgHtml + addImgHtml(data.describingImg1Url);
		}
		
		if(data.describingImg2Url){
			imgLen++;
			imgHtml =  imgHtml + addImgHtml(data.describingImg2Url);
		}
		
		if(data.describingImg3Url){
			imgLen++;
			imgHtml =  imgHtml + addImgHtml(data.describingImg3Url);
		}
		
		if(data.describingImg4Url){
			imgLen++;
			imgHtml =  imgHtml + addImgHtml(data.describingImg4Url);
		}		
		imgHtml =  imgHtml + checkCanAddImgDes();
		$("#img_des_form").html(imgHtml);
		
	}
}

function addImgHtml(imgurl){
	if(imgurl){
		var htm = "";
		htm= htm 
		+ '<div class="col-lg-3 col-sm-6 m_add_margin m_panel_bodder_right">'
		+	'<img src="'+imgurl+'" class="img-responsive img-rounded" alt="无法加载图片">'
		+	'<input type="hidden" name="imgDes" value="'+ms.cutUrlIp(imgurl)+'" >'
		+	'<button type="button" class="btn btn-primary updateImg" onclick=updateImgDes(this)>更换</button>'
		+	'<button type="button" class="btn btn-primary deleteImg" onclick=deleteImgDes(this)>删除</button>'
		 +'</div>';
		
		return htm;
		
	}else{
		return "";
	}
}

function checkCanAddImgDes(){
	if(imgLen<4) {
		var htm = "";
		htm= htm 
		+'<div class="col-lg-3 col-sm-6 m_add_margin m_panel_bodder_right ">'
		+'<img src="/onlinemall/static/img/add.png" onclick=addImgDes() class="img-responsive img-rounded" alt="无法加载图片">'
		
		+'</div>';
		
		return htm;
	}else {
		return "";
	}
}

function addImgDes(){
	ms.uploadImgModal(addImgSuccess);
}

function addImgSuccess(){	
	$("#img_des_form").append('<input type="hidden" name="imgDes" value="'+$("#imgurl").val()+'" >');	
	sumbitImgDes();
}

function deleteImgDes(obj){
	$(obj).parent().remove();
	sumbitImgDes();	
}

function updateImgDes(obj) {
	ms.uploadImgModal(function(){
		$(obj).parent().children("input").val($("#imgurl").val());
		sumbitImgDes();
	});
}

function sumbitImgDes(){
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/information/updateImgDes", // url
		data: $("#img_des_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {				
				
				zeroModal.close(loadingUnique);
				zeroModal.success(result.msg);				
				showMerchantInformation();
				
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

