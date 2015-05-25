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
		var subjectId = <%=request.getParameter("subjectId") %>;
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subject/getsubjectvideosbysubjectid.do",
			dataType : "json",
			data : {
				subjectId : subjectId
			},
			success : function(data) {
				if(data.resultcode == "000"){
					$("#subjectyName").html("课程名称："+data.subjects.subjectName);
					var createdate = new Date(data.subjects.createTime);
					$("#createTime").html("发布时间："+createdate.getFullYear()+"."+createdate.getMonth()+"."+createdate.getDate());
					$("#subjectDesc").text(data.subjects.subjectDesc);
					drawVideoList(data.videoList);
					$("#chapter").text("第"+(chapter+1)+"章");
					$("#subjectNameInput").attr("value","请输入第"+(chapter+1)+"章名称");
					//if(data.subjects.subjectPublic == 0){
						$("#checkSubjectPublic").attr("checked","none");
					//}
				}
				
			}
		});
	});
	
	//创建视频列表
	var chapter = 0;//记录要添加的那章的章节号
	function drawVideoList(text){
       	var trStr = "";
       	$(text).each(function(i,val) {
       		trStr = trStr +"<li><p>第"+val.videoChapter+"章<b>"+val.videoName+"</b></p>";
       		var createdate = new Date(val.createTime);
       		trStr = trStr +"<p>发布时间："+createdate.getFullYear()+"."+createdate.getMonth()+"."+createdate.getDate()+"</p>";
       		trStr = trStr +"<div class=\"save_button1_s50415\"><input  class=\"button_green_104x30_s50413\" type=\"button\" value=\"点击查看\"/></div></li>";
       		chapter = i+1;
       	});
       	$("#ul").html(trStr);
	}
	
	
	
</script>
<title>已发布课程</title>
</head>
<body>
<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp" />
	<!--头部结束-->

	<!--左侧开始-->
	<jsp:include page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="8" />
	</jsp:include>
	<!--左侧结束-->
	
	<!--右侧开始-->

	<div class="right_content_s50407 fl">   
     <!--发布新课程-->
     <div class="edit_homework_s50415">
        <h1>发布新课程</h1>
      
    <div class="enter_course_s50415"> 
      <h3>课程基本信息</h3>   
    <p id="subjectyName"></p>
    <p id="createTime"></p>
    <p><span id="subjectDesc">主要针对是即将毕业的撰写论文的在校学生而编写的，针对在校生写论文常出现的问题，以实操形式带领学生按步骤修改论文。主要针对是即将毕业的撰写论文的在校学生而编写的，针对在校生写论文常出现的问题，以实操形式带领学生按步骤修改论文。主要针对是即将毕业的撰写论文的在校学生而编写的，</span></p>
    
    
    <p>共享设置<b> <input id="checkSubjectPublic" type="checkbox" name="checkbox" vlaue="" checked />　不共享给其他部门</b></p>
    
    <ul class="upload_img_s50415">
    <li>
    <img src="${applicationScope.staticurl}img/video_zwt_s50408.jpg" width="208" height="130" alt="封面"/></li>
    <li class="mt100"><input class="button_green_104x30_s50413" type="button" value="浏览上传"/></li>
    </ul>
    </div>
    
      <!--第一章开始-->
    <div class="data_upload_s50415">
        <h3>课程章节</h3>
     <div class="chapter_content_s50415">
       <ul id="ul">
       </ul>
    </div>  
    </div>

   <div class="data_upload3_s50415">

  <div class="data_click_s50415">
   <p><i id="chapter"></i></p>
   <p><input id="subjectNameInput" class="text_421x23_s50415" type="text" value="请输入第三章名称"/></p>
   <p><i>发布时间</i></p>
   <p><i>视频内容</i><input class="button_green_107x21_s50331" type="button" value="点击上传视频"/>
   <span>注：支持H.264 ( .mp4, .mov, .f4v )、FLV ( .flv ) 、3GPP ( .3gp, .3g2 )、OGG Theora ( .ogv )和WebM ( .webm )视频格式。上传视频文件大小不得超过1G</span></p>
  
    <p><i>学习资料</i><input class="button_green_107x21_s50331" type="button" value="点击上传"/>
    
   <span>注：请上传例如讲义、课件等ppt、word、pdf格式的文件包，方便学生下载学习</span></p>
    
    <p><i>课堂作业</i><input class="button_green_124x21_s50331" type="button" value="点击编辑课堂作业"/></p>
    
    
      <p><i>课程进程</i><b><input type="checkbox" name="checkbox" vlaue="" checked />　未完待续</b>
           <b> <input type="checkbox" name="checkbox"/>　所有章节上传已完结</b></p>
           
            <div class="save_button2_s50415"><input class="button_orange_131x25_s50413" type="button" value="添加下一章"/></div>
               <div class="save_button_s50415 pb20"><input  class="button_green_104x30_s50413" type="button" value="保存并退出"/></div>
    </div> 
    </div>
  </div>
  </div>
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>