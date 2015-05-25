<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(function() {
  
});
</script>
<!--左侧开始-->
<div class="middle_bg_s50407">
	<div class="middle_s50407">
		<div class="left_content_s50407 fl">
			<div class="left_portrait_s50407">
				<a href="#"><img src="${applicationScope.staticurl}img/big_head_s50407.png" alt="大logo" width="122" height="122"></a>
				<c:if test="${not empty sessionScope.elearn_User_Info}">
					<h1>${sessionScope.elearn_User_Info.admin.adminName}</h1>
					<h2>${sessionScope.elearn_User_Info.departmentname}</h2>
				</c:if>
			</div>
			<div class="left_course_s50407">
				<ul class="course_common_s50407">
					<li><span>学习课程</span></li>
					<li><b><c:if test="${not empty studySubjectCount}">${studySubjectCount}</c:if></b></li>
				</ul>
				<ul class="course_middle_s50407">
					<li><span>收藏课程</span></li>
					<li><b><c:if test="${not empty favoriteSubjectCount}">${favoriteSubjectCount}</c:if></b></li>
				</ul>
				<ul class="course_common_s50407">
					<li><span>完成课程</span></li>
					<li><b><c:if test="${not empty completeSubjectCount}">${completeSubjectCount}</c:if></b></li>
				</ul>

			</div>
			<div class="left_situation_s50407">
				<ul class="left_title_s50407">
					<li>我的首页</li>
					<li class="selected_s50407">我的课程</li>
				</ul>
				<ul class="left_menu_s50407">
					
					<li <c:if test="${leftIndex=='1'}">xz_menu_s50407</c:if>><b>最近学习</b> <c:if test="${not empty studySubjectCount}">
							<span><i>${studySubjectCount}</i></span>
						</c:if></li>
					<li><b>完成课程</b> <c:if test="${not empty completeSubjectCount}">
							<span><i>${completeSubjectCount}</i></span>
						</c:if></li>
					<li><b>我的收藏</b> <c:if test="${not empty favoriteSubjectCount}">
							<span><i>${favoriteSubjectCount}</i></span>
						</c:if></li>
					<li class=""><b>我的作业</b> <c:if test="${not empty favoriteSubjectCount}">
							<span><i>${favoriteSubjectCount}</i></span>
						</c:if></li>
				</ul>
			</div>

			<div class="left_situation_s50407">
				<ul class="left_title_s50407">
					<li>发布课程</li>
				</ul>
				<ul class="left_menu_s50407">
					<li><b>更新发布课程</b> <c:if test="${not empty adminUploadSubjectCount}">
							<span><i>${adminUploadSubjectCount}</i></span>
						</c:if></li>
					<li><b>已完结课程</b> <c:if test="${not empty adminUploadCompleteSubjectCount}">
							<span><i>${adminUploadCompleteSubjectCount}</i></span>
						</c:if></li>
					<li><b>查看作业</b><span></span></li>
				</ul>
			</div>


			<div class="left_situation_s50407">
				<ul class="left_title_s50407">
					<li>统计分析</li>
				</ul>
				<ul class="left_menu_s50407">
					<li><b>本部门课程统计</b><span></span></li>
					<li><b>本部门学习统计</b><span></span></li>
					<li><b>各部门课程统计</b><span></span></li>
					<li><b>各部门学习统计</b><span></span></li>
				</ul>
			</div>

			<div class="left_situation_s50407">
				<ul class="left_title_s50407">
					<li>我是管理员</li>
				</ul>
				<ul class="left_menu_s50407">
					<li><b>用户管理</b><span></span></li>
					<li><b>课程管理</b><span></span></li>
					<li><b>上传LOGO</b><span></span></li>
				</ul>
			</div>

		</div>

		<!--左侧结束-->