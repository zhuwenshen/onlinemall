$(function(){
	initLabel();
	queryGoods(1);
	
	$("#search_btn").click(function(){
		queryGoods(1);
	});	
})

function initLabel(){
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/goods/goodsLabel", // url
		success: function(result) {	
			if(result.status) {
				var html = '<option value=""></option>';
				var list = result.data;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					html = html + '<option value="'+list[i].name+'">'+list[i].name+'</option>';
				}

				$("#label").html(html);				
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}

var goodsList;
function queryGoods(pageNum){
	var list;
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/goods/queryGoods?pageNum="+pageNum, // url
		data: $('#query_form').serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				list = result.data.list;
				goodsList = list;
				var i = 0;
				var j = 0;
				var labelList;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + ms.tlEmpty(list[i].name) + "</td>" +
						'<td > <img    src="'+ms.tlEmpty(list[i].descriptionImg1Url)+'" class="img-responsive img-rounded m_img_sdes"  alt="点击上传图片">  </td>' +
						"<td>" + ms.translateText(list[i].unshelve)  + "</td>" ;
					tbodyHtml = tbodyHtml + '<td>';
					if(list[i].labelList || list[i].labelList.length) {
						labelList = list[i].labelList;
						for(j=0; j<labelList.length ; j++) {
							if(labelList[j]){
								tbodyHtml = tbodyHtml +'<label class="m_label">'+labelList[j].name+'</label>';
							}							
						}						
					}
					tbodyHtml = tbodyHtml + '</td>';
					tbodyHtml = tbodyHtml + 
						"<td>" + ms.tlEmpty(list[i].shelveTime)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].unshelveTime)  + "</td>" ;
					
					tbodyHtml = tbodyHtml +	"<td>" + 
					
					" <input type='button' value='修改' class='btn btn-sm btn-info'" +
					"onclick=updateGoods("+"'"+ list[i].id + "'"+") />";
					
					if(!list[i].unshelve){
						tbodyHtml = tbodyHtml + " <input type='button' value='下架' class='btn btn-sm btn-warning'" +
						"onclick=unshelve("+"'"+ list[i].id + "'"+") />";
					}					
					
					tbodyHtml = tbodyHtml +
					" <input type='button' value='更改下架时间' class='btn btn-sm btn-info'" +
					"onclick=updateUnshelveTime("+"'"+ list[i].id + "'"+","+i+') />'+
					
					" <input type='button' value='分类管理' class='btn btn-sm btn-info'" +
					"onclick=classManage("+"'"+ list[i].id + "'"+") />";
					
					" <input type='button' value='删除' class='btn btn-sm btn-warning'" +
					"onclick=deleteGoods("+"'"+ list[i].id + "'"+") />"
					+ "</td>" ;
					
					tbodyHtml = tbodyHtml +						
							"</tr>";
				}

				$("#query_tbody").html(tbodyHtml);
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

function goPage(p){	
	queryGoods(p);	
}

function refreshPage(){
	//alert(1136466);
	queryGoods($("#pageNum").val());
}

function updateGoods(id){
	var zm = zeroModal.show({
        title: "更改商品信息",
        url:ms.path +"/m/goods/updateGoods?id="+id,
        width:"500px",
        height:"80%",
        max:true,
        onComplete:function(){
        	$("#goodsId").val(id);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		$("#modelId").val(zm);
		$("#add_form").submit();
		return false;
	}
}

/**
 * 下架商品
 * @param id
 * @returns
 */
function unshelve(id){
	zeroModal.confirm("确认要下架该商品吗！", function(){
		var loadingUnique = zeroModal.loading(3);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/m/goods/unshelve?id="+id, // url
			success: function(result) {	
				if(result.status) {
								
					zeroModal.close(loadingUnique);
					zeroModal.success(result.msg);
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

function updateUnshelveTime(id, index){
	var html = '<div class="form-group row">'
 	+'<label class="col-sm-3 control-label">下架时间</label>  '
 	+'<div class="col-sm-9">'
 	+'<div class="input-group date form_datetime"  data-date-format="yyyy-mm-dd hh:ii:ss" id="update_unshelveTime_datetime" data-link-field="updateUnshelveTime">'
 	+'  <input class="form-control" size="16" type="text" value=""  >'
 	+'     <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>'
 	+'		<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>'
 	+'    </div>'
 	+'	<input type="hidden" id="updateUnshelveTime" name="unshelveTime" value="" /> '
 	+'</div>   '                                                                                                                         
 	+' </div>';
	var unshelveTime = goodsList[index].unshelveTime;
	
	zeroModal.show({
        title: "更改下架时间",
        content:html,
        width:"400px",
       // height:"80%",
        max:true,
        onComplete:function(){
        	$('#update_unshelveTime_datetime').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
		        showMeridian: 1,
		        initialDate:unshelveTime
			});
			$("#update_unshelveTime_datetime input").val(unshelveTime);
			$("#updateUnshelveTime").val(unshelveTime);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:function(){
    		
    		var loadingUnique = zeroModal.loading(3);
    		$.ajax({
    			type: "POST", // 方法类型
    			dataType: "json", // 预期服务器返回的数据类型
    			url: ms.path + "/m/goods/updateUnshelveTime", // url
    			data:{id:id,unshelveTime:$("#updateUnshelveTime").val()},
    			success: function(result) {	
    				if(result.status) {
    								
    					zeroModal.close(loadingUnique);
    					zeroModal.success(result.msg);
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
    	}
    });	
}

function deleteGoods(id){
	zeroModal.confirm("确认要下架该商品吗！", function(){
		var loadingUnique = zeroModal.loading(3);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/m/goods/deleteGoods?id="+id, // url
			success: function(result) {	
				if(result.status) {
					
					zeroModal.close(loadingUnique);
					zeroModal.success(result.msg);					
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

function classManage(id){
	var zm = zeroModal.show({
        title: "商品分类管理",
        url:ms.path +"/m/class/manage?id="+id,
        width:"800px",
        height:"80%",
        max:true,       
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		var json = getJson();
		//console.log(json);
		
		var loadingUnique = zeroModal.loading(3);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/m/class/update", // url
			data:{data:json},
			success: function(result) {	
				if(result.status) {
					
					zeroModal.close(loadingUnique);
					zeroModal.success(result.msg);	
					return true;
					
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
		
		
		return false;
	}
}

