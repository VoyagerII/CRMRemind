/*
 * 动态引入CSS、JavaScript文件
 * 
 * 定义参数
 * 
 * staticUrl : http://xxx.xxx.xx/
 * 
 * type : css|script|library
 * 
 * param : xxx
 */
var DynamicLoading = {};

// 调用CSS、Javascript文件
DynamicLoading.LoadingScript = function(param) {
	//
	if (null != param && undefined != param && "" != param && "null" != param
			&& "undefined" != param) {
		//
		var staticUrl = String(param.split(",")[0]);
		var type = String(param.split(",")[1]);
		var param = String(param.split(",")[2]);

		// 其它CSS
		if (null != staticUrl && null != type && type.toUpperCase() == "css".toUpperCase()) {
			otherCss(staticUrl, param);
		}
		// 其它JS
		else if (null != staticUrl && null != type && type.toUpperCase() == "script".toUpperCase()) {
			otherScript(staticUrl, param);
		}
		// Library JS
		else if (null != staticUrl && null != type && type.toUpperCase() == "library".toUpperCase()) {
			// jQuery Validate
			if (param == "jQuery.Validate.Library") {
				jQueryValidateLibrary(staticUrl);
			}
			// jQuery Validation Engine
			else if (param == "jQuery.Validation.Engine.Library") {
				jQueryValidationEngineLibrary(staticUrl);
			}
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
			scriptFile.setAttribute("type", "text/javascript");
			scriptFile.setAttribute("src", sourceFilePath);
			document.getElementsByTagName("head")[0].appendChild(scriptFile);
		}
	}
}

/*
 * jQuery 1.8.0 Library
 */
function jQueryLibrary(staticUrl) {
	loadingFile("script", staticUrl + "webstatic/js/jquery-1.8.0/jquery-1.8.0.min.js");
}

/*
 * jQuery Validate 1.13.0 Library
 */
function jQueryValidateLibrary(staticUrl) {
	// jQuery Validate CSS
	loadingFile("css", staticUrl
			+ "webstatic/js/jquery-validate-1.13.0/css/jquery.validate.tip.css");
	loadingFile("css", staticUrl + "webstatic/js/jquery-validate-1.13.0/css/jquery-ui.css");

	// jQuery Library
	loadingFile("script", staticUrl + "webstatic/js/jquery-1.8.0/jquery-1.8.0.min.js");

	// jQuery Validate JS
	loadingFile("script", staticUrl + "webstatic/js/jquery-validate-1.13.0/src/jquery.validate.js");
	loadingFile("script", staticUrl + "webstatic/js/jquery-validate-1.13.0/src/jquery-ui.min.js");
	loadingFile("script", staticUrl
			+ "webstatic/js/jquery-validate-1.13.0/jquery.validate.allRules.js");
}

/*
 * jQuery Validation Engine 2.6.4 Library
 */
function jQueryValidationEngineLibrary(staticUrl) {
	// jQuery Validation Engine CSS
	loadingFile("css", staticUrl
			+ "webstatic/js/jquery-validation-engine-2.6.4/css/validationEngine.jquery.css");

	// jQuery Library
	loadingFile("script", staticUrl + "webstatic/js/jquery-1.8.0/jquery-1.8.0.min.js");

	// jQuery Validation Engine JS
	loadingFile("script", staticUrl
			+ "webstatic/js/jquery-validation-engine-2.6.4/src/jquery.validationEngine.js");
	loadingFile(
			"script",
			staticUrl
					+ "webstatic/js/jquery-validation-engine-2.6.4/languages/jquery.validationEngine-zh_CN.js");
	loadingFile("script", staticUrl
			+ "webstatic/js/jquery-validation-engine-2.6.4/jquery.validationEngine.allRules.js");
}

/*
 * 其它CSS
 */
function otherCss(staticUrl, cssPath) {
	
}

/*
 * 其它CSS
 */
function otherScript(staticUrl, scriptPath) {
	if(scriptPath == "a.js") {
		loadingFile("script", staticUrl + "webstatic/js/a.js");
	}
}