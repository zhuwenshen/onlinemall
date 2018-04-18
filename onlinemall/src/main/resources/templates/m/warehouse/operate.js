var typeName = '进货'; 
var operationType = '1';

$(function(){
	
	initAll();
	
	$("#add_form").submit(function(event){
		
		
		//阻止表单提交
	    event.preventDefault();
	  	if(operationType==1){
	  		if(!$("#purchasePriceU").val()){
		    	zeroModal.error(typeName+"价不能为空");
	    		return false;
		    }else if(!($("#purchasePriceU").val()>0)){
		    	zeroModal.error(typeName+"价必须为合法数且大于0");
	    		return false;
		    }
	  	}else if(operationType==2){
	  		if(!$("#purchasePriceU").val()){
		    	zeroModal.error(typeName+"价不能为空");
	    		return false;
		    }else if(!($("#purchasePriceU").val()>=0)){
		    	zeroModal.error(typeName+"价必须为合法数且大于或等于0");
	    		return false;
		    }
	  	}  	  	
	  	
	    if(operationType == 3){
	    	if(!$("#priceU").val()){
		    	zeroModal.error("售价不能为空");
	    		return false;
		    }else if(!($("#priceU").val()>0)){
		    	zeroModal.error("售格必须为合法数且大于0");
	    		return false;
		    }
	    }
	    	    
	    if(operationType == 1){
	    	if($("#numU").val().isBlank()){
		    	zeroModal.error(typeName+"数量不为空");
	    		return false;
		    }
	    }	   	
	    
		switch (operationType) {
		case '1':
			addFormSubmit(ms.path + "/m/warehouse/stockGoodsPrice");
			break;
		case '2':
			addFormSubmit(ms.path + "/m/warehouse/salesReturnGoodsPrice");
			break;
		case '3':
			addFormSubmit(ms.path + "/m/warehouse/updateGoodsPrice");
			break;

		default:
			break;
		}
	  
	    
	});
	
	
})


function initAll(){
	operationType = $("#operationType").val();
	
	switch (operationType) {
	case '1':
		initData(initStock);
		break;
	case '2':
		initData(initSalesReturn);
		typeName = "退货";
		break;
	case '3':
		initData(initUpdate);
		break;

	default:
		break;
	}
}
//进货
function initStock(data){
	initCommon(data);
	$("#priceU").attr("disabled","disabled");
}

//退货
function initSalesReturn(data){
	initCommon(data);
	$("#priceU").attr("disabled","disabled");
	$("#numU_label").html(typeName+'数量<font color="red">*</font>');
	$("#purchasePriceU_label").html(typeName+'价￥<font color="red">*</font>');
}

//修改售价
function initUpdate(data){
	initCommon(data);
	$("#numU_label").parent().remove();
	$("#purchasePriceU_label").parent().remove();
	$("#supplyU_label").parent().remove();
}



function initCommon(data){
	$("#goodsIdU").val(data.goodsName);
	
	var html = "";
	var detailList = data.detailList;
	if(detailList && detailList.length) {
		for(var k=0; k<detailList.length; k++){
			html = html+ '<div class="form-group">'
						+'<label class="col-sm-3 control-label">'+detailList[k].className+'</label>'
						+'<div class="col-sm-9"><input class="form-control"   value="'+detailList[k].name+'" disabled="disabled"></div>'
						+'</div>';
		}
	}
	$("#nameU_div").after(html);
	
	$("#numU").val(0);
	$("#purchasePriceU").val(0);
	$("#priceU").val(data.price);
	$("#restMunU").val(data.num);
	$("#allNumU").val(data.allNum);
}

function initData(callback){	
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/getPriceInfo", // url
		data:{priceId:$("#goodsPriceId").val()},
		success: function(result) {	
			if(result.status) {				
				//console.log(result.data);				
				callback(result.data);				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}

function addFormSubmit(url){
	//alert("提交表单");
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: url,  // url
		data: $("#add_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {
				
				zeroModal.success(result.msg);
				zeroModal.close(loadingUnique);	
				//selectNowGoodsPrice();
				zeroModal.close($("#modalId").val());
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
