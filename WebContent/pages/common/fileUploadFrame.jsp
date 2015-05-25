<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<!-- CSS -->
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<link href="${applicationScope.staticurl}js/jquery-loading/jquery.loading.css" rel="stylesheet" type="text/css" />
<script src="${applicationScope.staticurl}js/jquery-loading/jquery.loading.js"></script>
<script src="${applicationScope.staticurl}js/ajaxfileupload.js"></script>
<script type="text/javascript">
	//上传文件
	function ajaxFileUploadFileOnChange() {
		getLoadLayer(parent.$("body"));
		$.ajaxFileUpload({
			url : "${pageContext.request.contextPath}/file/upload.do",
			// 是否启用安全提交
			secureuri : false,
			data : {
				imagetype : $("#ajaxFileUploadFormImagetype").val()
			},
			dataType : "json",
			// 表示文件域ID
			fileElementId : 'headUpload',
			success : function(resultData, resultStatus) {
				removeLoadLayer(parent.$("body"));
				parent.ajaxFileUploadCallBackFun(resultData, resultStatus, $(
						"#ajaxFileUploadCallBackFunType").val());
			}
		});
	}
</script>
</head>
<body>
	<form id="ajaxFileUploadForm" name="ajaxFileUploadForm">
		<input type="file" id="headUpload" name="headUpload" onchange="ajaxFileUploadFileOnChange()" /> <input type="hidden"
			id="ajaxFileUploadFormImagetype" name="ajaxFileUploadFormImagetype" value="" /> <input type="hidden" id="ajaxFileUploadCallBackFunType"
			name="ajaxFileUploadCallBackFunType" value="" />
	</form>
</body>
</html>