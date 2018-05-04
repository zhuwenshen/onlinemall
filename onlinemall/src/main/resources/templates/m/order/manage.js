$(function(){
	queryOrder(1);
	
	$("#search_btn").click(function(){
		queryOrder(1);
	});
})

function goPage(num){	
	queryOrder(num);
}

function refreshPage(){
	goPage($("#pageNum").val());
}


function queryOrder(pageNum){
	if(!pageNum){
		pageNum = 1;
	}
	
	//获取已经展开的id
	
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/order/query", // url
		data: $("#query_form").serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var data = result.data;
				var list = data.list;
				//console.log(data)
				var html = "";				
				
				if(list){
					var name = "";
					for(var i=0;i<list.length;i++){
						name = "";
						var inforList = list[i].orderInforList;
						if(inforList.length>1) {
							name = inforList[0].goodsName+"等共"+inforList.length+"件商品";
						}else{
							name = inforList[0].goodsName;
						}
						
						var op = "";
						switch (list[i].orderStatus) {
						case 1:
							op = op +'<input type="button" class="btn btn-info btn-xs m-td-btn disabled" value="等待支付" /> ';
							break;
						case 2:
							op = op +'<input type="button" class="btn btn-primary btn-xs m-td-btn" onclick=confirmOrder("'+list[i].id+'") value="确认订单" /> '
									+'<input type="button" class="btn btn-warning btn-xs m-td-btn" onclick=cancelOrder("'+list[i].id+'") value="取消订单" /> ';
							break;
						case 3:
							op = op +'<input type="button" class="btn btn-primary btn-xs m-td-btn" onclick=deliverOrder("'+list[i].id+'") value="发货" /> ';
							break;
						case 4:
							op = op +'<input type="button" class="btn btn-primary btn-xs m-td-btn disabled" value="等待签收" /> ';
							break;
						case 5:
							op = op +'<input type="button" class="btn btn-primary btn-xs m-td-btn disabled" value="等待确认收货" /> ';
							break;
						/*case 6:
							op = op +'<input type="button" class="btn btn-primary btn-xs m-td-btn disabled" value="等待确认收货" /> ';
							break;*/
						default:
							break;
						}
						html = html + '<tr>'
									+'<td onclick=showOrderInfor(this,"'+list[i].id+'") style="text-align: center;width: 10px;"><span class="glyphicon glyphicon-plus-sign" style="color: green;cursor: pointer;"></span></td>'
									+ '<td>'+name+'</td>'
									+ '<td>'+list[i].createTime+'</td>'
									+ '<td>'+list[i].orderStatusTL+'</td>'
									+ '<td>'+toDecimal2(list[i].actualAmount)+'</td>'
									+ '<td>'+toDecimal2(list[i].paidActualAmount)+'</td>'
									+ '<td>'+ms.translateNullText(list[i].expressNumber)+'</td>'
									+ '<td>'+ms.translateNullText(list[i].expressCompany)+'</td>'
									+ '<td>'+ms.translateNullText(list[i].remake)+'</td>'
									+ '<td>'+op+'</td>'
									+ '</tr>';
						
						html = html + '<tr style="display: none;" class="m-tr-details">'
									+ '<td colspan="10">'
									+ '<div class="col-sm-12" style="margin-bottom: 10px;" id="order_'+list[i].id+'">'
																						
									+ '</div>'
									+ '<div class="col-sm-12">'
									+ '<div class="table-responsive ">'
									+ '	<table class=" m-tr-details table table-bordered">'
									+ '	<thead>'
									+ '	<tr>'
									+ '		<th>图片</th>'
									+ '		<th>名称</th>'
									+ '			<th>具体分类信息</th>	'																
									+ '			<th>单价</th>'
									+ '			<th>数量</th>'
									+ '			<th>金额</th>'
									/*+ '			<th>操作</th>		'*/									
									+ '		</tr>'
									+ '	</thead>'
									+ '		<tbody id="query_infor_tbody_'+list[i].id+'">'
												
									+ '		</tbody>'
									+ '	</table>'
									+ '</div>'
									+ '</div>'
									+ '</td>'													
									+ '</tr>';
						
					}
				}
				
				
				$("#query_tbody").html(html);
				$("#pageNum").val(result.data.pageNum);
				
				ms.navPage(result.data.pages, result.data.pageNum, "m_nav_ul");
				zeroModal.close(loadingUnique);
				
			} else {
				zeroModal.error(result.msg);
				zeroModal.close(loadingUnique);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");
			zeroModal.close(loadingUnique);

		}
	});
}

var hiddenSpan = '<span class="glyphicon glyphicon-plus-sign" style="color: green;cursor: pointer;">';
var showSpan = '<span class="glyphicon glyphicon-minus-sign" style="color: red;cursor: pointer;"></span>';
function showOrderInfor(e,orderId){
	
	var tr = $(e).parent().next();
	var display = $(e).parent().next().css("display");
	if(display == 'none'){	
		$(e).html(showSpan);
		$(e).parent().addClass("m-tr-checked");
		getOrderInfor(tr,orderId);	
	}else{
		$(e).html(hiddenSpan);		
		$(e).parent().removeClass("m-tr-checked");
		tr.toggle();
	}
}

function getOrderInfor(tr,orderId) {
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/order/getOrderInfor", // url
		data: {id:orderId},
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var data = result.data;
				//console.log(data)
				var html = "";
				
				var btnHtml ='';
				if(data&&data.length>0){					
					var order = data[0];
					
					var orderHtml = '<span>收件人：'+ms.translateNullText(order.addressConsignee)+'</span>&nbsp;&nbsp;&nbsp; <span>电话：'+ms.translateNullText(order.addressPhone)+'</span>'
									+'<br>'
									+'<span>收货地址：'+ms.translateNullText(order.addressDetail)+'</span>	'													
									+'</div>';
					$("#order_"+orderId).html(orderHtml);
					
					var list = order.orderInforList;
					var i=0;
					for(i=0;i<list.length;i++){						
						
						
						html = html +'<tr >'
									+'<td class="m-trolley-img-td"><img  src="'+list[i].goodsImg+'"></td>'
									+'<td>'+list[i].goodsName+'</td>'
									+'<td >'+ms.translateNullText(list[i].classDetails)+'</td>'									
									+'<td >￥ <span>'+toDecimal2(list[i].price)+'</span></td>'
									+'<td >'+list[i].num+'</td>'	
									+'<td>￥ <span>'+toDecimal2(list[i].price*list[i].num)+'</span></td>'
									/*+'<td></td>'*/
								+'</tr>';
					}
					
					
					$("#query_infor_tbody_"+orderId).html(html);
					tr.toggle();
					zeroModal.close(loadingUnique);
				}
				
				
			} else {
				zeroModal.error(result.msg);
				zeroModal.close(loadingUnique);
			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");
			zeroModal.close(loadingUnique);
		}
	});
}

function confirmOrder(orderId){
	zeroModal.confirm("确认该订单？", function(){
		//加载模态
		var loadingUnique = zeroModal.loading(3);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/m/order/confirmOrder", // url
			data: {orderId:orderId},
			success: function(result) {
				//console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					zeroModal.success(result.msg);
					zeroModal.close(loadingUnique);
					refreshPage();					
				} else {
					zeroModal.error(result.msg);
					zeroModal.close(loadingUnique);
				}
			},
			error: function() {				
				zeroModal.error("服务器异常！");
				zeroModal.close(loadingUnique);
			}
		});
	});
}

function cancelOrder(orderId){
	var zm = zeroModal.show({
        title: "取消订单",
        url:ms.path +"/m/order/cancel",
        width:"400px",
        height:"200px",
        max:true,
        onComplete:function(){
        	$("#orderId").val(orderId);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "确认取消",
        okFn:okFn
    });
	
	function okFn(){
		$("#modal_id").val(zm);
		$("#modal_form").submit();
		return false;
	}
}

function deliverOrder(orderId) {
	var zm = zeroModal.show({
        title: "发货",
        url:ms.path +"/m/order/deliver",
        width:"400px",
        height:"200px",
        max:true,
        onComplete:function(){
        	$("#orderId").val(orderId);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "发货",
        okFn:okFn
    });
	
	function okFn(){
		$("#modal_id").val(zm);
		$("#modal_form").submit();
		return false;
	}
}
