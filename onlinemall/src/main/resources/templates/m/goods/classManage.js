$(function(){
	queryGoodsClass();
});

function queryGoodsClass(){
	var goodsId = $("#goodsId").val();
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/class/queryClass?goodsId="+goodsId, // url
		success: function(result) {	
			if(result.status) {
				
				var data = result.data;
				
				var html = "";
				var i = 0;
				for(i=0; i< data.length; i++){
					html = html + '<tr>'
								+'<td>'+data[i].name+'</td>'
								+'<td>';
					
					var details = data[i].details;
					var j =0;
					for(j=0;j<details.length;j++){
						html = html +'	<label class="m_class_detail">'+details[j].name+'</label>';
					}
								
					html = html +'</td>'
								+'<td>'
								+'	<span onclick="classUp(this)" class="m_icon_btn glyphicon glyphicon-arrow-up btn btn-primary btn-lg " title="上移"></span>'
								+'	<span onclick="classDown(this)" class="m_icon_btn glyphicon glyphicon-arrow-down btn btn-primary btn-lg " title="下移"></span>'
								+'	<span onclick="classUpdate(this)" class="m_icon_btn glyphicon glyphicon-pencil btn btn-info btn-lg " title="更改"></span>'
								+'	<span onclick="classRemove(this)" class="m_icon_btn glyphicon glyphicon-remove btn btn-warning btn-lg " title="移除"></span>	'							
								+'</td>'
								+'</tr>';
				}	
				
				
				$("#class_query_tbody").html(html);
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

function addClassModal(id){
	var zm = zeroModal.show({
        title: "商品增加分类",
        url:ms.path +"/m/class/add",
        width:"500px",
        height:"60%",
        max:true, 
        onComplete:function(){
        	$("#classGoodsName").html($("#goodsName").html());
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		var html = getNewTrHtml();
		
		if(html) {
			$("#class_query_tbody").append($('<tr>'+html+'</tr>'));	
			return true;
		}
		
		return false;
	}
}

function getNewTrHtml(){
	var name = $("#manage_form [name='name']").val();	
	var detail = $("#manage_form [name='detail']");
	if(name.isBlank()){
		zeroModal.error("分类名不能为空");
		return false;
	}
	
	var status = false;
	var j =0;
	for(j=0;j<detail.length;j++){
		if(!detail[j].value.isBlank()) {
			status = true;
			break;
		}
	}
	
	if(!status) {
		zeroModal.error("子分类不能为空");
		return false;
	}	
	
	var html = '<td >'+name+'</td>'
				+'<td>';
	var i =0;
	for(i=0;i<detail.length;i++){
		if(!detail[i].value.isBlank()){
			html = html + '<label class="m_class_detail">'+detail[i].value+'</label>';	
		}
		
	}	
	
	html = html +'</td>'
				+'<td>'
				+'	<span onclick="classUp(this)" class="m_icon_btn glyphicon glyphicon-arrow-up btn btn-primary btn-lg " title="上移"></span>'
				+'	<span onclick="classDown(this)" class="m_icon_btn glyphicon glyphicon-arrow-down btn btn-primary btn-lg " title="下移"></span>'
				+'	<span onclick="classUpdate(this)" class="m_icon_btn glyphicon glyphicon-pencil btn btn-info btn-lg " title="更改"></span>'
				+'	<span onclick="classRemove(this)" class="m_icon_btn glyphicon glyphicon-remove btn btn-warning btn-lg " title="移除"></span>	'							
				+'</td>';
	return html;
}

/**
 * 往上移动
 * @param o
 * @returns
 */
function classUp(o){
	var tr = $(o).parent().parent();	
	var i = $("#class_query_tbody tr").index(tr);	
	if(i==0) {
		return;
	}else{
		var beforeTr = tr.prev();
		beforeTr.insertAfter(tr);
	}	
}

/**
 * 往下移动
 * @param o
 * @returns
 */
function classDown(o){
	var tr = $(o).parent().parent();	
	var i = $("#class_query_tbody tr").index(tr);
	var len = $("#class_query_tbody tr").lenth;
	if(i>=len) {
		return;
	}else{
		var nextTr = tr.next();
		nextTr.insertBefore(tr);
	}	
}

function classUpdate(o){
	var otr = $(o).parent();
	var tr = otr.parent();
	var tdName = otr.prev().prev();
	var detail = otr.prev().children();
	zeroModal.show({
        title: "商品增加分类",
        url:ms.path +"/m/class/add",
        width:"500px",
        height:"60%",
        max:true, 
        onComplete:function(){
        	$("#classGoodsName").html($("#goodsName").html());
        	
        	//初始化值
        	$("#manage_form [name='name']").val(tdName.text());
        	if(detail.length!=0){
        		$("#add_detail_btn").prev().remove();
        	}
        	detail.each(function(){
        		$("#add_detail_btn").before('<input type="text" class="form-control" placeholder="子分类名称" name="detail" value="'+$(this).text()+'"> ');
        	});
        	
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		var html = getNewTrHtml();
		
		if(html) {
			$(tr).html($(html));	
			return true;
		}
		
		return false;
	}
}

function classRemove(o){
	$(o).parent().parent().remove();
}

/**
 * 把表单拼接成对象
 * @returns
 */
function getJson(){
	var goodsId = $("#goodsId").val();
	var array = new Array();
	$("#class_query_tbody tr").each(function(){
		var h = "{";
		h=h+ '"goodsId":"'+goodsId+'",';
		
		var td = $(this).children();		
		h=h+'"name":"'+td.eq(0).text()+'", "details":[';
		
		var de = new Array();
		td.eq(1).children().each(function(){
			var t = '{"name":"'+$(this).text()+'"}';
			de.push(t);
		});
		
		h=h+de.join(",")+"]"+"}";
		array.push(h);
	});
	
	json = "["+array.join(",")+"]";
	return json;
}









