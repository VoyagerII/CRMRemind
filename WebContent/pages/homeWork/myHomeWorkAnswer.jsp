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
<script src="${applicationScope.staticurl}js/My97DatePicker/WdatePicker.js"></script>
<script src="${applicationScope.staticurl}js/raphael-min.js"></script>
<script src="${applicationScope.staticurl}js/morris.min.js"></script>
<script>

	//初始化
	var myVideoHomeWorkAnswerAndQuestion = null;
	$(document).ready(function() {
		myVideoHomeWorkAnswerAndQuestion = $("#myVideoHomeWorkAnswerAndQuestion").clone();
		$("#myVideoHomeWorkAnswerAndQuestion").empty();
		getVideoHomeWorkAnswerAndQuestion(1);
	});
	function getVideoHomeWorkAnswerAndQuestion(videoId){
		$("#myVideoHomeWorkAnswerAndQuestion").empty();
		$.ajax({
    		async : false,
    		url: "${pageContext.request.contextPath}/myhomework/answerbyvideoid.do",
            type : "post",
            data : {
            	videoId : videoId
            },
			dataType:"json",
            success: function (data) {
              //记得改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	           	if(data.resultcode == "201"){
            		insertInfo(data);
            	}else{
            		alert(data.resultmsg);
            		return ;
            	}
            },
			error : function(jqXHR, textStatus,errorThrown) {
				alert("操作有误，请联系管理员");
				return ;
			}
        });
	}
	
    function insertInfo(data){
    	$("#videoName").html(data.adminAnswerBeanList[0].videoName);
    	$("#adminAvatar").html("<img style='height:120px;width:80px;' src='${applicationScope.uploadstuurl}"+data.adminAnswerBeanList[0].adminAvatar+"' alt='个人头像'/>");
    	$("#videoDesc").html(data.adminAnswerBeanList[0].videoDesc);
    	$("#adminName").html(data.adminAnswerBeanList[0].adminName);
		$(data.adminAnswerBeanList).each(function(i, n) {
					var temp = myVideoHomeWorkAnswerAndQuestion.clone();
					temp.find("#videoName").html("视频名称："+n.videoName);
					temp.find("#adminAvatar").html("作业数量："+n.countNum);
					temp.find("#videoDesc").html("修改时间："+n.lastTime);
					temp.find("#videoDesc").html("修改时间："+n.lastTime);
					$("#myVideoHomeWorkAnswerAndQuestion").append(temp.html());
				});
	}
	
</script>
<title>我的作业</title>
</head>
<body>
<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp" />
	<!--头部结束-->

	<!--左侧开始-->
	<jsp:include page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="5" />
	</jsp:include>
	<!--左侧结束-->
	
	<!--右侧开始-->
	<!--我的作业详情-->
	<div class="right_video_s50407" style="border-color: red">
		<div class="right_vireo_title_s50407">
			<h3>作业概况：</h3> 
			<div align="center">
			<table cellpadding="0" cellspacing="0" border="0"
					style="width: 800px; height: 8px; text-align: left;"> 
					<tr>
						<td style="width: 40px; text-align: right;">视频名称：</td>
						<td style="width: 150px; text-align: left;"><span id="videoName"></span></td> 
						<td style="width: 40px; text-align: right;">发布人：</td>
						<td style="width: 150px; text-align: left;"><span id="adminAvatar"></span></td>
					</tr>
					<tr>
						<td style="width: 40px; text-align: right;">视频简介：</td>
						<td style="width: 150px; text-align: left;"><span id="videoDesc"></span></td>
						<td style="width: 40px; text-align: right;">姓名：</td>
						<td style="width: 150px; text-align: left;"><span id="adminName"></span></td>
					</tr>
			</table></div>
		</div>
			<div class="video_course_s50408" id="myVideoHomeWorkAnswerAndQuestion" style=" height:600px;">
				<div align="center">
					<table cellpadding="0" cellspacing="0" border="3"
						style="width: 800px; height: 100px; text-align: left;">  
						<tr>
							<td style="width: 800px; margin-top: 20px; text-align: left;"><span id="homeworkContent"></span></td> 
						</tr>  
						<tr>
							<td style="width: 800px; margin-top: 20px; text-align: left;"><span id="answerContent"></span></td> 
						</tr>  
					</table>
				</div>
			</div>
	</div>
	<!--我的作业详情-->
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>