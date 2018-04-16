$(function(){	
	$("#add_detail_btn").click(function(){
		$(this).before('<input type="text" class="form-control" placeholder="子分类名称" name="detail">');
	});
})
