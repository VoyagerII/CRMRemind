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
	
	//创建表格
	function getManageSubjectList(){
		$.ajax({
    		async : false,
    		url: "${pageContext.request.contextPath}/subjectManage/getmanagesubjects.do",
            type : "post",
            data : {
            	pageIndex : page
            },
			dataType:"json",
            success: function (text) {
            	if(text.Resultcode == "000"){
            	}else{
            		alert(text.Resultmsg);
            		return ;
            	}
            	var trStr = "<tr class=\"tr_course_head_s50415\">"
         	       +"<td class=\"course_width_60_s50415\">序号</td>"
         	       +"<td class=\"course_width_98_s50415\">发布时间</td>"
         	       +"<td class=\"course_width_149_s50415\">课程名称</td>"
         	       +"<td class=\"course_width_98_s50415\">发布部门</td>"
         	       +"<td class=\"course_width_90_s50415\">发布人</td>"
         	       +"<td class=\"course_width_187_s50415\">操作</td>"
         	       +"</tr>";
            	$(text.subjectManageListBean).each(function(i,val) {
            		trStr = trStr +"<tr class=\"tr_one_head_s50415\">";
            		trStr = trStr +"<td>"+(i+1)+"</td>";
            		var date = new Date(val.createTime);
            		trStr = trStr +"<td>"+date.getFullYear()+"."+date.getMonth()+"."+date.getDate()+"</td>";
            		trStr = trStr +"<td>"+val.subjectName+"</td>";
            		trStr = trStr +"<td>"+val.departmentName+"</td>";
            		trStr = trStr +"<td>"+val.adminName+"</td>";
            		if(val.subjectStatus == 0){
            			trStr = trStr +"<td><img id=\"img"+val.subjectId+"\" src=\"${applicationScope.staticurl}img/guan_s50415.jpg\" width=\"106\" height=\"22\" alt=\"开关按钮占位图\" onClick=\"changeStatusOpen("+val.subjectId+")\"></td>";
            		}else{
            			trStr = trStr +"<td><img id=\"img"+val.subjectId+"\" src=\"${applicationScope.staticurl}img/open.jpg\" width=\"106\" height=\"22\" alt=\"开关按钮占位图\" onClick=\"changeStatusClose("+val.subjectId+")\"></td>";
            		}
            		trStr = trStr +"</tr>";
            		$("#table").html(trStr);
            	});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("加载数据失败");
            }
        });
	}
	
	//分页
	function getdata(){
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subjectManage/getmanagesubjects.do",
			data : {
				pageIndex : page
			},
			dataType : "json",
			success : function(data) {
				if(data.Resultcode == "000"){
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
					alert(data.Resultmsg);
					return ;
				}
			}
		});
		getManageSubjectList();
	}
	function relists(p, param) {
		page = p;
		getdata();
	}
	
	//将课程的状态置为开启
	function changeStatusOpen(e){
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subjectManage/updatesubjectstatus.do",
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
	//将课程的状态置为关闭
	function changeStatusClose(e){
		$.ajax({
			async : false,
			type : "post",
			url : "${pageContext.request.contextPath}/subjectManage/updatesubjectstatus.do",
			data : {
				subjectId : e,
				subjectStatus : 0
			},
			dataType : "json",
			success : function(text){
				if(text.resultcode == "000"){
					$("#img"+e).attr("src","${applicationScope.staticurl}img/guan_s50415.jpg");
					$("#img"+e).attr("onClick","changeStatusOpen("+e+")");
				}else{
					alert(text.resultmsg);
				}
			}
		});
	}
</script>
<title>课程管理</title>
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
      <div class="right_vireo_title_s50407"><h3>课程管理</h3></div>
      
    <div>
    
    <div class="screening_interval_s50415">
     <p>
      时间区间<span><select name="" class="select_50_s50415"><option value=""></select></span> 
             年
             <span><select name=""class="select_50_s50415"><option value=""></select></span> 
             月
             <span><select name=""class="select_50_s50415" ><option value=""></select></span>
             日
             <span>至</span>
             <span><select name="" class="select_50_s50415"><option value=""></select></span> 
             年
             <span><select name=""class="select_50_s50415"><option value=""></select></span> 
             月
             <span><select name=""class="select_50_s50415" ><option value=""></select></span>
             日</p>
    
     <p><span><select name=""class="select_167x26_s50415" ><option value="">请筛选部门</select></span>
        <span><select name=""class="select_167x26_s50415" ><option value="">请筛发布人</select></span>
       <span><input class="text_178x26_s50415" type="text" value="请输入课程名称关键字"/></span>
         <span><input class="button_green_95x26_s50331" type="button" value="查　询"/></span>

    </div>

    <table id="table" class="table_course_s50415" border="0">
    </table>
    <br>
    <div id="jspageshtml"></div>
    </div>
      </div>
    <!--最近学习结束-->     
   </div>
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>