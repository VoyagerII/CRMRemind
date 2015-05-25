$(function() {
	loginchangeAuthcode();
	loginvalidate();
});

function loginvalidate() {
	$("#loginForm").validate({
		submitHandler : function(form) {
			// 表单提交
			var param = $("#loginForm").serialize();
			$.ajax({
				type : "post",
				dataType : "json",
				url : "login/login.do",
				data :param,
				success : function(data) {
					if(data.resultcode==000){
						alert("登陆成功！");
					}else{
						alert(data.resultmsg);
						$("#authcode").val("");
						loginchangeAuthcode();
					}
					
				}
			});
		},
		rules : {
			adminLogin : {
				required : true,
				minlength : 5,
				maxlength : 20,
				isadminLoginon : true
			},
			adminPass : {
				required : true,
				minlength : 5,
				maxlength : 20,
				isadminPasson : true
			},
			authcode : {
				required : true,
				isauthcodeon : true
			}
		},
		messages : {
			adminLogin : {
				required : "请输入用户名",
				minlength : "请输入至少${0}位字符",
				maxlength : "最多可输入${0}位字符",
			},
			adminPass : {
				required : "请输入密码",
				minlength : "请输入至少${0}位字符",
				maxlength : "最多可输入${0}位字符",
			},
			authcode : {
				required : "请输入验证码"
			}
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			var error_msg_label_id = String(element.attr("id")) + "Error";
			$("#" + error_msg_label_id).html(error);
		},
		success : function(element) {
			var error_msg_label_id = String(element.attr("id")) + "Error";
			$("#" + error_msg_label_id).html("");
		}
	});
}

function loginchangeAuthcode() {
	document.getElementById('authcodeimg').src = 'captchaImage.do?' + Math.random();
}