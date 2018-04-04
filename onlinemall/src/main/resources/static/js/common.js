var ms = {
		path : "/onlinemall"
}

//对boolean翻译，true->是，false->否
ms.translate = function(status){
	if(status) return "<span class='glyphicon glyphicon-ok ' style='color:#5cb85c;'><span>";
	else return "<span class='glyphicon glyphicon-remove' style='color:#FF0000;' ><span>";
}

/**
 * 总页数，当前页码，ul的id
 */
ms.navPage = function (totalP, nowP, ulId) {	
	var navHtml = " ";
	var totalP = totalP;
	var nowP= nowP;
	var startP = 0;
	var endP = 0;
	var disa ="";
	var page1 = 1;
	var onclickH = " ";
	
	//上一页
	if(nowP>1) {
		disa=' ';
		onclickH=" onclick=goPage("+(nowP-1)+") ";
	}else {
		disa=' class="disabled"';
		onclickH = " ";
	}
	navHtml= navHtml+" <li "+disa+">"
						+"	<a  aria-label='Previous' "+onclickH+">"
						+"					<span aria-hidden='true'>&laquo;</span>"
						+"				</a>"
						+"			</li>";
	
	
	// 页码大于等于4的时候，添加第一个页码元素
	if(nowP>4) {
		navHtml= navHtml+'<li >'
						+"	<a  onclick=goPage("+(page1)+")>1</a>"
						+"			</li>";
	}					
	
	/* 当前页码>4, 并且<=总页码，总页码>5，添加“···”*/
	if(nowP>4&& nowP<=totalP && totalP>5) {
		navHtml= navHtml+'<li class="disabled">'
						+"	<a  >...</a>"
						+"			</li>";
	}
	
	/* 当前页码的前两页 */
	startP = nowP-2;
	/* 当前页码的后两页 */
	endP = nowP+2; 
	
	if((startP>1 && nowP<4) || nowP==1) {
    	endP++;
	}
    if(nowP>totalP-4 && nowP>=totalP) {
        startP--;
    }
	
	var activeP = " ";
	
	//添加中间码
	for(; startP<=endP&&startP<=totalP; startP++) {
        if(startP<=totalP && startP>=1) {
        	if(startP==nowP) {
        		activeP = ' class="active" ';
        		onclickH = " ";
        	}else {
        		activeP = " ";
        		onclickH=" onclick=goPage("+(startP)+") ";
        	}
      		navHtml= navHtml+'<li '+activeP+'>'
						+"	<a "+onclickH+" >"+startP+"</a>"
						+"			</li>";
        }
    }
	
	//添加后...
	if(nowP+2<totalP-1 && nowP>=1 && totalP>5) {
        navHtml= navHtml+'<li class="disabled">'
						+"	<a  >...</a>"
						+"			</li>";
    }
	//添加最后数字
	if(nowP!=totalP && nowP<totalP-2 && totalP!=4) {
    	navHtml= navHtml+'<li >'
						+"	<a  onclick=goPage("+(totalP)+")>"+totalP+"</a>"
						+"			</li>";
	}
	
	//添加下一页
	if(nowP<totalP) {
		disa=' ';
		onclickH=" onclick=goPage("+(nowP+1)+") ";
	}else {
		disa=" class='disabled'";
		onclickH = " ";
	}
	navHtml= navHtml+" <li "+disa+">"
						+"	<a aria-label='Next' "+onclickH+">"
						+"					<span aria-hidden='true'>&raquo;</span>"
						+"				</a>"
						+"			</li>";
	
	
	
	$("#"+ulId).html(navHtml);
}

/**
 * 类型（即颜色）：success info warning danger
 * 是否可关闭：true ，false
 * 信息内容
 * 加载div的id
 */
ms.append_alert = function(type, closed, msg, divId) {
	var ch = " ";
	if(closed) {
		ch = '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'
	}
	
	var ms_htm = '<div class="alert alert-'+type+' alert-dismissable">'
	    +ch
	    +msg
   +' </div>';	
	
	$("#"+divId).append(ms_htm);
}

ms.tlEmpty = function(val){
	if(val) {
		return val;
	}else{
		return "";
	}
}
//初始化下拉框
ms.initSelect = function (id,kind,val){
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/kindConstant?kind="+kind, // url		
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				console.log(result.data);
				var selectHtml = "";
				var list = result.data;
				var i = 0;
				var selected = ""
				
				selectHtml = selectHtml + "<option value=''></option>";
				for(i = 0; i < list.length; i++) {
					if(val == list[i].value){
						selected = "selected='selected'";
					}else {
						selected ="";
					}
					selectHtml = selectHtml + 
					"<option value='"+list[i].value+"'  "+selected+" >"+list[i].nameCn+"</option>" 
					
				}

				$("#"+id).html(selectHtml);				
				
			} else {
				//alert(result.msg);
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});
}
