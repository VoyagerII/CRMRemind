/**
 * 1.13.0-demo.html页面表单验证
 */
$(function() {
	$("#demoForm").validate({
		submitHandler : function(form) {
			// 表单提交
			// formSubmitFunction(form);
		},
		rules : {
			loginName : {
				required : true,
				minlength : 2,
				maxlength : 200,
				isHttpUrl : true
			},
			loginEmail : {
				required : true,
				minlength : 2,
				maxlength : 200,
				isHttpUrl : true
			}
		},
		messages : {
			loginName : {
				required : "请输入内容",
				minlength : "请输入至少${0}位字符",
				maxlength : "最多可输入${0}位字符",
				remote : "用户名重复"
			},
			loginEmail : {
				required : "请输入内容",
				minlength : "请输入至少${0}位字符",
				maxlength : "最多可输入${0}位字符",
				remote : "用户名重复"
			}
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			var error_msg_label_id = "error_msg_" + String(element.attr("id")) + "_label";
			$("#" + error_msg_label_id).html(error);
			$("#" + error_msg_label_id).removeClass().addClass("error");
		},
		success : function(element) {
			$("#error_msg_" + String(element.attr("id").split("-")[0]) + "_label").html("");
			$("#error_msg_" + String(element.attr("id").split("-")[0]) + "_label").removeClass();
		}
	});
});

// Ajax Form提交方法
function formSubmitFunction() {
	$.ajax({
        data : {
			"param" : "param"
		}
	});
}