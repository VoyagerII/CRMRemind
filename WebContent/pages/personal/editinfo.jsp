<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${applicationScope.staticurl}css/public.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/public_button.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/style.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/header.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/footer.css" rel="stylesheet" type="text/css" />
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${applicationScope.staticurl}js/personal/bjzl_tab_control.js"></script>
<title>编辑信息</title>
</head>
<body>
	<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp" />
	<!--头部结束-->
	<!--左侧开始-->
	<jsp:include page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="0" />
	</jsp:include>
	<!--左侧结束-->


	<!--右侧开始-->
	<div class="right_content_s50407 fl">
		<div class="editor_content_s50408">
			<ul class="editor_title_s50408">
				<li id="editor_tab1" class="editor_hover">个人信息</li>
				<li class="editor_line_s50408">|</li>
				<li id="editor_tab2">安全设置</li>
			</ul>
			<!--个人信息开始-->
			<div id="editor_t1" class="editor_tab_left" style="display: block;">
				<ul class="basic_information_s50407">
					<li>姓名：</li>
					<li>部门：</li>
				</ul>
				<div class="editor_image_s50408">
					<ul class="editor_image_left_s50408 fl">
						<li><img src="${applicationScope.staticurl}img/zwt_122x122_s50408.jpg" width="122" height="122" alt="头像占位图"/></li>
						<li><input class="editor_button_107x29_s50408" type="button" value="保存图片" /></li>
					</ul>
					<ul class="editor_image_middle_s50408 fl">
						<li><input class="editor_button_107x29_s50408" type="button" value="上传图片" /> <!--上传图片占位按钮--></li>
						<li>仅支持jpg、png格式，且文件小于2M。</li>
						<li>(使用高质量图片，可以生成高清头像)</li>
					</ul>
					<ul class="editor_image_right_s50408 fl">
						<li>
							<ul class="editor_right_70_s50408">
								<li><img src="${applicationScope.staticurl}img/big_zwt_s50408.png" width="70" height="70" alt="大头像占位图" /></li>
								<li>
									<p>大尺寸头像</p>
									<p>70*70像素(自动生成)</p>
								</li>
							</ul>
						</li>
						<li>
							<ul class="editor_right_36_s50408">
								<li><img src="${applicationScope.staticurl}img/small_zwt_s50408.png" width="36" height="36" alt="小头像占位图" /></li>
								<li>
									<p>小尺寸头像</p>
									<p>36*36像素(自动生成)</p>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="cls"></div>
			</div>
			<!--个人信息结束-->
			<!--安全设置开始-->
			<div id="editor_t2" class="editor_tab_right" style="display: none;">
				<ul class="editor_input_s50408">
					<li><input class="editor_text_s50408" type="text" value="请输入原始密码" /></li>
					<li><input class="editor_text_s50408" type="text" value="请输入新密码" /></li>
					<li><input class="editor_text_s50408" type="text" value="重复输入新密码" /></li>
				</ul>
				<p>
					<input class="editor_button_107x29_s50408" type="button" value="确认修改">
				</p>
			</div>

			<!--安全设置结束-->

		</div>
	</div>
	<!--右侧结束-->
	<div class="cls"></div>
	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部结束-->
</body>
</html>
