<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${applicationScope.staticurl}css/public.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/public_button.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/style.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/header.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/footer.css" rel="stylesheet" type="text/css" />
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<script src="${applicationScope.staticurl}js/layer/layer.js"></script>
<script>
	var returnIndex = "${applicationScope.webhost}";
	var loadWin;
	
	function showLoginOut() {
		//捕获页
		layer.open({
			type : 1,
			shade : false,
			area : [ 'auto', '202px' ],
			closeBtn : false,
			title : false, //不显示标题
			content : $("#loginoutdiv").show(), //捕获的元素
			cancel : function(index) {
				layer.close(index);
				this.content.hide();
			}
		});
	}

	function dologinCancel() {
		$("#loginoutdiv").css('display','none');  
	}
	function dologinOut() {
		$.ajax({
			url : '${pageContext.request.contextPath}/login/loginout.do',
			dataType : 'json',
			timeout : 5000,
			data : {
				type : 'json'
			},
			success : function(data, textStatus) {
				layer.msg('感谢您的使用！', {
					time : 3000
				});
				if (data.result == 1) {
					document.location.href = returnIndex;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error" + textStatus);
			}
		});

	}
</script>
<!--头部开始-->
<div class="head_bg_s50407">
	<div class="head_s50407">
		<div class="head_left_s50407 fl">
			<a href="#"><img src="${applicationScope.staticurl}img/logo.png" width="245" height="75" /></a>
		</div>
		<div class="head_right_s50407 fl">
			<div class="head_portrait_s50407 fr">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.elearn_User_Info}">
							<li><a href="${pageContext.request.contextPath}/">登录</a></li>
						</c:when>
						<c:otherwise>
							<li><img src="${applicationScope.staticurl}img/small_head_s50407.png" alt="小头像" width="36" height="36" /></li>
							<li><a href="javascript:showLoginOut();">安全退出</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="search_bg_s50407 ">
				<input type="text" value="" class="search_text_s50407" /> <input type="image"
					src="${applicationScope.staticurl}img/search_right_s50407.png" width="22" height="22" class="search_go_s50407" alt="search"
					title="search" />
			</div>
			<div class="cls"></div>
		</div>
		<div class="cls"></div>
	</div>
</div>
<!--头部结束-->

<!--是否要退出本次登录？开始-->
<div id="loginoutdiv" style="display: none; width: 500px; height: 200px;">
	<div class="popup_page_s50413">
		<p>是否要退出本次登录？</p>
		<div class="public_button_location_clear">
			<ul>
				<li class="ml100"><input class="button_green_85x29_s50410" type="button" value="确　定" onclick="dologinOut()" /></li>
				<li class="ml135"><input class="button_green1_85x29_s50410" type="button" value="取　消" onclick="dologinCancel()" /></li>
			</ul>
		</div>
	</div>
</div>
<!--是否要退出本次登录？结束-->