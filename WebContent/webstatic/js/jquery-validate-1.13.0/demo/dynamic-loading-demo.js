/*
 * 动态引入CSS、JavaScript文件
 * 定义参数
 */
var DynamicLoading = {};

// 调用CSS、Javascript文件
DynamicLoading.LoadingScript = function(param) {
	if (param != null) {
		// jQuery Validate 页面元素提示
		if (param == 'jQuery.Validate.Page.Element.Tip') {
			jQueryValidatePageElementTip();
		}

		// jQuery Validate 页面浮动提示
		else if (param == 'jQuery.Validate.Page.Float.Tip') {

		}
	}
};

// 加载文件
function loadingFile(sourceType, sourceFilePath) {
	if (null != sourceType && undefined != sourceType && "" != sourceType && "null" != sourceType
			&& "undefined" != sourceType && null != sourceFilePath && undefined != sourceFilePath
			&& "" != sourceFilePath && "null" != sourceFilePath && "undefined" != sourceFilePath) {
		// 加载CSS文件
		if (sourceType.toUpperCase() == "css".toUpperCase()) {
			var cssFile = document.createElement("link");
			cssFile.setAttribute("rel", "stylesheet");
			cssFile.setAttribute("type", "text/css");
			cssFile.setAttribute("href", sourceFilePath);
			document.getElementsByTagName("head")[0].appendChild(cssFile);
		}
		// 加载Javascript文件
		else if (sourceType.toUpperCase() == "script".toUpperCase()) {
			var scriptFile = document.createElement("script");
			scriptFile.setAttribute("language", "javascript");
			scriptFile.setAttribute("type", "text/javascript");
			scriptFile.setAttribute("src", sourceFilePath);
			document.getElementsByTagName("head")[0].appendChild(scriptFile);
		}
	}
}

/*
 * jquery-1.8.0
 */
function jQuery_1_8_0() {
	loadingFile("script", "../../jquery-1.8.0/jquery-1.8.0.min.js");
}

/*
 * jQuery Validate 页面元素提示
 */
function jQueryValidatePageElementTip() {
	// css
	loadingFile("css", "../../../css/jquery.validate.tip.css");
	loadingFile("css", "../src/jquery-ui.css");

	// JS
	jQuery_1_8_0();
	loadingFile("script", "../src/jquery.validate.js");
	loadingFile("script", "../src/jquery-ui.min.js");
	loadingFile("script", "../src/jquery.validate.allRules.js");
	loadingFile("script", "1.13.0-demo.html.js");
}