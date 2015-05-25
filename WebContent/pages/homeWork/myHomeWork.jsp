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
	var myVideoHomeWorkNum = null;
	$(document).ready(function() {
		myVideoHomeWorkNum = $("#myVideoHomeWork").clone();
		$("#myVideoHomeWork").empty();
		getSubjectListByMyAnswer(1);
	});
	function getSubjectListByMyAnswer(page){
		$("#myVideoHomeWork").empty();
		$.ajax({
    		async : false,
    		url: "${pageContext.request.contextPath}/myhomework/subjectlistbymyanswer.do",
            type : "post",
            data : {
            	pageIndex : page
            },
			dataType:"json",
            success: function (data) {
              //记得改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	           	if(data.resultcode == "201"){
	           		$.ajax({
						async : false,
						type : "post",
						url : "${applicationScope.webhost}pages/common/pageno_javascript.jsp",
						dataType : "html",
						data : {
							pageno : page,
							totalpage : data.totalpage,
							jsfunc : "relists",
							tagid : "pages1",
							jsparam : "{}"
						},
						success : function(data, status) {
							$("#jspageshtml").html(data);
						}
					});
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
    	$("#myVideoHomeWorkCount").html(data.videoBeanList.length);
		$(data.videoBeanList).each(function(i, n) {
					var temp = myVideoHomeWorkNum.clone();
					temp.find("#videoName").html("视频名称："+n.videoName);
					temp.find("#countNum").html("作业数量："+n.countNum);
					temp.find("#lastTime").html("修改时间："+n.lastTime);
					$("#myVideoHomeWork").append(temp.html());
				});
	}
    
    //分页?
    function relists(p, param) {
		getSubjectListByMyAnswer(p);
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
	<!--我的作业-->
		<div class="right_video_s50407" style="border-color: red">
			<div class="right_vireo_title_s50407">
				<h3>
					我的作业共关联：<b id="myVideoHomeWorkCount"></b>&nbsp;&nbsp;&nbsp;&nbsp;节视频
				</h3>
			</div>
			<div class="video_course_s50408" id="myVideoHomeWork" style=" height:600px;">
				<ul>
				    <li class="ml43" id="myVideoHomeWorkNum">
			            <div class="video_content_s50408"> 
			              <img id="subjectPicture" src="${applicationScope.staticurl}img/video_zwt_s50408.jpg" width="208" height="130" alt="视频占位图"/>
			            </div>
			            <ul class="video_introduce_s50408">
			                 <li><a href="#" id="videoName"></span></a></li>
			                 <li><a href="${applicationScope.webhost}pages/homeWork/myHomeWorkAnswer.jsp" id="countNum"><span id="subjectName"></span></a></li>
			                 <li><a id="lastTime"></a></li>
				             <li></li>
			            </ul>
		            </li>
				</ul>
			</div>
		  <div id="jspageshtml" style="margin:0 auto;"></div>
		</div>
	<!--我的作业-->
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>