<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${applicationScope.staticurl}css/login.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/public.css" rel="stylesheet" type="text/css" />
<link href="${applicationScope.staticurl}css/public_button.css" rel="stylesheet" type="text/css" />
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<link rel='stylesheet' type='text/css' href='${applicationScope.staticurl}js/jquery-validate-1.13.0/css/jquery.validate.tip.css' />
<link rel='stylesheet' type='text/css' href='${applicationScope.staticurl}js/jquery-validate-1.13.0/css/jquery-ui.css' />
<script type='text/javascript' src='${applicationScope.staticurl}js/jquery-validate-1.13.0/src/jquery.validate.js'></script>
<script type='text/javascript' src='${applicationScope.staticurl}js/jquery-validate-1.13.0/src/jquery-ui.min.js'></script>
<script type='text/javascript' src='${applicationScope.staticurl}js/jquery-validate-1.13.0/jquery.validate.allRules.js'></script>
<script src="${applicationScope.staticurl}js/login/login.js"></script>
</head>
<script>
</script>
<title>登录入口</title>
</head>
<body>
	<div class="login_bg_s50407">
		<div class="login_content_s50407">
			<div class="login_logo_s50407">
				<img src="${applicationScope.staticurl}img/logo.png" alt="logo" width="245" height="75" />
			</div>
			<div class="login_middle_s50407">
				<div class="login_personal_s50407">
					<form id="loginForm" method="post">
						<ul>
							<li><input class="login_text_s50407" id="adminLogin" name="adminLogin" onFocus="if(this.value==defaultValue){this.value=''}"
								onBlur="if(!value){value=defaultValue;}" type="text" value="voyager" /></li>
							<li><span><label id="adminLoginError"></label></span></li>
							<li><input class="login_text_s50407" id="adminPass" name="adminPass" onFocus="if(this.value==defaultValue){this.value=''}"
								onBlur="if(!value){value=defaultValue;}" type="password" value="123123123" /></li>
							<li><span><label id="adminPassError"></label></span></li>
							<li>
								<div class="login_personal_yam_s50407">
									<ul>
										<li><input class="login_text_yzm_s50407" id="authcode" onFocus="if(this.value==defaultValue){this.value=''}"
											onBlur="if(!value){value=defaultValue;}" name="authcode" type="text" value="111" /></li>
										<li><img src="" alt="验证码" id="authcodeimg" width="84" height="36" onclick="loginchangeAuthcode();" /></li>
										<li><i onclick="loginchangeAuthcode();">看不清，换一张</i></li>
									</ul>
									<div class="cls"></div>
								</div>
							</li>
							<li><span><label id="authcodeError"></label></span></li>
							<li><input type="submit" value="登　录" class="button_bule_333x36_s50331" /></li>
						</ul>
					</form>
				</div>
				<div class="login_company_s50407">
					<p>XXXXXXX有限公司</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>