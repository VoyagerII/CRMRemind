$(function () {
	$("#formID").validationEngine({
		// 浮动提示位置
		promptPosition : "centerRight",
		// 逐条显示
		showOneMessage:true,
		onValidationComplete : function(form, formIsValid) {
			// Form表单验证通过之后提交ajax方法
			if(formIsValid) {
				$.ajax({
					type : $("#formID").attr("method"),
					url : $("#formID").attr("action"),
					data : {
						loginname : $("#loginname").val(),
						pwd : $("#pwd").val(),
						usertype:"1",
						authcode:$("#authcode").val()
					},
					dataType : 'json',
					success : function(data, status) {
						// 返回数据及状态 data.status
					}
				});
			}
		},
		// 验证成功提交form表单
		success : false,
		// 验证失败提交form表单
		failure : function() {
			alert("失败");
		}
	});
});