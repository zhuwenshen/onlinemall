$(function() {	
	$("#imgPicker").on("change",function(){
		// 当没选中图片时，清除预览
		if (this.files.length === 0) {
			$("#preview").attr("src", "");
			return;
		}else{
			// 判断图片格式
			var filepath = $("#imgPicker").val();		
	        var extStart = filepath.lastIndexOf(".");
	        var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	        if (ext != ".BMP" && ext != ".PNG"&& ext != ".JPG" && ext != ".JPEG") {
	          zeroModal.error("图片限于bmp,png,jpeg,jpg格式");          
	          return;
	        } else{
	        	ms.uploadPortrait("uploadImg_form" , review);
	        }
		}
		
		
		
		
	});
});

function review(imgurl){
	$("#imgurl").val(imgurl);
	$("#preview").attr("src", ms.picIp+ imgurl);
}