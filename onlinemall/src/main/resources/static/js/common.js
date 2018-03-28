var ms = {
		path : "/onlinemall"
}

//对boolean翻译，true->是，false->否
ms.translate = function(status){
	if(status) return "<span class='glyphicon glyphicon-ok'><span>";
	else return "<span class='glyphicon glyphicon-remove'><span>";
}