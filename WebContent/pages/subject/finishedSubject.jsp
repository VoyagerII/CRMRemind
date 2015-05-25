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
	$(document).ready(function() {
		getdata();
	});
	var page = 1;
	
	//创建视频列表
	function getSubjectList(text){
       	if(text.resultcode == "000"){
       	}else{
       		alert(text.resultmsg);
       		return ;
       	}
       	var trStr = "";
       	$(text.subjectsBeanList).each(function(i,val) {
       		trStr = trStr +"<li class=\"ml43\">";
       		trStr = trStr +"<img src=\"${applicationScope.staticurl}img/video_zwt_s50408.jpg\" width=\"208\" height=\"130\" alt=\"视频占位图\"/>";
       		trStr = trStr +"<ul class=\"video_introduce_s50408\">";
       		trStr = trStr +"<li><a href=\"#\">课程名称："+val.subjectName+"</a></li>";
       		var createdate = new Date(val.createTime);
       		trStr = trStr +"<li><a href=\"#\">发布时间："+createdate.getFullYear()+"."+createdate.getMonth()+"."+createdate.getDate()+"</a></li>";
       		var lastdate = new Date(val.lastTime);
       		trStr = trStr +"<li><a href=\"#\">最近更新时间："+lastdate.getFullYear()+"."+lastdate.getMonth()+"."+lastdate.getDate()+"</a></li>";
       		trStr = trStr +"<li>";
       		trStr = trStr +"<div class=\"stars_play_s50408\">";
       		trStr = trStr +"<ul>";
       		trStr = trStr +drawStar(val.subjectScore);
       		trStr = trStr +"<li class=\"stars_playli_s50408\"><img src=\"${applicationScope.staticurl}img/play_s50408.png\" width=\"13\" height=\"9\" alt=\"播放\"/><b>"+val.subjectPlay+"次</b></li>";
       		trStr = trStr +"</ul></div></li></ul>";
       		if(val.subjectStatus == 0){
       			trStr = trStr +"<div class=\"offine_s50416\"><img src=\"${applicationScope.staticurl}img/offline_s50416.png\" width=\"208\" height=\"264\" alt=\"强制下线\"></div>";
       		}
       		trStr = trStr +"</li>";
       	});
       	$("#ul").html(trStr);
	}
	
	//画星星
	function drawStar(score){
		var returnStr = "<li>";
		var zeroStarStr = "<span><img src=\"${applicationScope.staticurl}img/stars_gary_s50408.png\" width=\"12\" height=\"12\" alt=\"灰色的星星\"/></span>";
		var halfStarStr = "<span><img src=\"${applicationScope.staticurl}img/stars_ban_s50408.png\" width=\"12\" height=\"12\" alt=\"半颗的星星\"/></span>";
		var ontStarStr = "<span><img src=\"${applicationScope.staticurl}img/stars_h_s50408.png\" width=\"12\" height=\"12\" alt=\"点亮的星星\"/></span>";
		if(score == 0){
			returnStr = returnStr + zeroStarStr + zeroStarStr + zeroStarStr + zeroStarStr + zeroStarStr;
		}else if(score < 2){
			returnStr = returnStr + halfStarStr + zeroStarStr + zeroStarStr + zeroStarStr + zeroStarStr;
		}else if(score == 2){
			returnStr = returnStr + ontStarStr + zeroStarStr + zeroStarStr + zeroStarStr + zeroStarStr;
		}else if(score < 4){
			returnStr = returnStr + ontStarStr + halfStarStr + zeroStarStr + zeroStarStr + zeroStarStr;
		}else if(score == 4){
			returnStr = returnStr + ontStarStr + ontStarStr + zeroStarStr + zeroStarStr + zeroStarStr;
		}else if(score < 6){
			returnStr = returnStr + ontStarStr + ontStarStr + halfStarStr + zeroStarStr + zeroStarStr;
		}else if(score == 6){
			returnStr = returnStr + ontStarStr + ontStarStr + ontStarStr + zeroStarStr + zeroStarStr;
		}else if(score < 8){
			returnStr = returnStr + ontStarStr + ontStarStr + ontStarStr + halfStarStr + zeroStarStr;
		}else if(score == 8){
			returnStr = returnStr + ontStarStr + ontStarStr + ontStarStr + ontStarStr + zeroStarStr;
		}else if(score < 10){
			returnStr = returnStr + ontStarStr + ontStarStr + ontStarStr + ontStarStr + halfStarStr;
		}else if(score == 10){
			returnStr = returnStr + ontStarStr + ontStarStr + ontStarStr + ontStarStr + ontStarStr;
		}
		returnStr = returnStr + "</li>";
		return returnStr;
	}
	//分页
	function getdata(){
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subject/getfinishedsubjects.do",
			data : {
				pageIndex : page
			},
			dataType : "json",
			success : function(data) {
				if(data.resultcode == "000"){
					$.ajax({
						async : false,
						type : "post",
						url : "${applicationScope.webhost}pages/common/pageno_javascript.jsp",
						dataType : "html",
						data : {
							pageno : page,
							totalpage : data.totalPages,
							jsfunc : "relists",
							tagid : "pages1",
							jsparam : "{}"
						},
						success : function(data, status) {
							$("#jspageshtml").html(data);
						}
					});
				}else{
					alert(data.resultmsg);
					return ;
				}
				$("#subjectNum").html(data.total);
				getSubjectList(data);
			}
		});
	}
	function relists(p, param) {
		page = p;
		getdata();
	}
	
	//
	function changeStatusOpen(e){
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subjectManage/.do",
			data : {
				subjectId : e,
				subjectStatus : 1
			},
			dataType : "json",
			success : function(text){
				if(text.resultcode == "000"){
					$("#img"+e).attr("src","${applicationScope.staticurl}img/open.jpg");
					$("#img"+e).attr("onClick","changeStatusClose("+e+")");
				}else{
					alert(text.resultmsg);
				}
			}
		});
		
	}
</script>
<title>已完结课程</title>
</head>
<body>
<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp" />
	<!--头部结束-->

	<!--左侧开始-->
	<jsp:include page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="17" />
	</jsp:include>
	<!--左侧结束-->
	
	<!--右侧开始-->
	
	<div class="right_content_s50407 fl">
    <!--最近学习-->     
     <div class="right_video_s50407">
      <div class="right_vireo_title_s50407"><h3>完成课程<b id="subjectNum"></b></h3></div>
        <div class="video_course_s50408">
          <ul id="ul">
          </ul>
        </div>
      </div>     
   <!--最近学习结束--> 
 
    </div>
    <br>
    <div id="jspageshtml"></div>
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>