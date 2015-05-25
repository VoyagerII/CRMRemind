<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${applicationScope.staticurl}css/public_paging.css" rel="stylesheet" type="text/css" />
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<script src="${applicationScope.staticurl}js/common/commontools.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
var page = 1;
$(document).ready(function() {
	getdata();
});

function getdata(){
	$.ajax({
		type : "post",
		url : "${applicationScope.webhost}/login/ajaxpagesdemo.do",
		data : {
			pageno : page
		},
		dataType : "json",
		success : function(data) {
			if(data.Resultcode == "000"){
				$.ajax({
					type : "post",
					url : "${applicationScope.webhost}pages/common/pageno_javascript.jsp",
					dataType : "html",
					data : {
						pageno : page,
						totalpage : ${totalPages},
						jsfunc : "relists",
						tagid : "pages1",
						jsparam : "{}"
					},
					success : function(data, status) {
						$("#jspageshtml").html(data);
					}
				});
				$("#data").html(data.data);
			}
		}
	});
}
function relists(p, param) {
	page = p;
	getdata();
}
</script>
</head>
<body>
	<c:forEach items="${demodata1}" var="data">
		${data}
	</c:forEach>
	<p></p>
	<c:forEach items="${demodata2}" var="data">
		${data}
	</c:forEach>


	<p>++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++jsp分页</p>

	<jsp:include page="common/pagesHtml.jsp">
		<jsp:param name="pageno" value="${pageno}" />
		<jsp:param name="gourl" value="${pageContext.request.contextPath}/login/modelandviewdemo.do" />
		<jsp:param name="totalpage" value="${totalPages}" />
	</jsp:include>
	${demodata3}




	<p>++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++JS分页</p>
	<div id="data"></div>
	<div id="jspageshtml"></div>

	<p>++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++上传</p>


	<script type="text/javascript">
	function b_uplogo(){
		$("#fileUploadFrame").contents().find("#ajaxFileUploadFormImagetype").val("testuploadpath");
		$("#fileUploadFrame").contents().find("#ajaxFileUploadCallBackFunType").val("testupload");
		$("#fileUploadFrame").contents().find("#headUpload").click();
	}
	function ajaxFileUploadCallBackFun(resultData, resultStatus, resultType) {
		if (isNotNull(resultData) && resultData.status.toUpperCase() == "Y".toUpperCase()
				&& isNotNull(resultStatus) && resultStatus.toUpperCase() == "SUCCESS".toUpperCase()
				&& isNotNull(resultType)) {
			if (resultType.toUpperCase() == "testupload".toUpperCase()) {
				$("#demoimg").attr("src", "${applicationScope.uploadurl}"+resultData.msg);
			}else {
				alert("上传失败原因：" + resultData.msg);
			}
		} else {
			alert("上传失败原因：" + resultData.msg);
		}
	}
	</script>
	<iframe id="fileUploadFrame" name="fileUploadFrame" src="${pageContext.request.contextPath}/pages/common/fileUploadFrame.jsp" frameborder="0"
		marginwidth="0" marginheight="0" style="display: none; width: 0; height: 0; padding: 0; margin: 0;"></iframe>
	<img id="demoimg" />
	<input type="button" value="上传" onclick="b_uplogo();" />
</body>
</html>