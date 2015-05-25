/**
 * jQuery表单验证
 * 
 * 2014-08-14 09:35
 */

/*
 * 纯数字
 * 
 * isFigure : true or false
 */
$.validator.addMethod("isFigure", function(value, element, param) {
	var pattern = /^[0-9]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持数字");

/*
 * 整数或含两位小数
 * 
 * isDecimal : true or false
 */
$.validator.addMethod("isDecimal", function(value, element, param) {
	var pattern = /^\d+(\.\d{1,2})?$/;
	return this.optional(element) || pattern.test(value);
}, "只支持整数或包含两位小数");

/*
 * 纯英文
 * 
 * isEnglish : true or false
 */
$.validator.addMethod("isEnglish", function(value, element, param) {
	var pattern = /^[A-Za-z]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持英文");

/*
 * 纯小写英文
 * 
 * isLowerCaseEnglish : true or false
 */
$.validator.addMethod("isLowerCaseEnglish", function(value, element, param) {
	var pattern = /^[a-z]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持小写英文");

/*
 * 纯大写英文
 * 
 * isUpperCaseEnglish : true or false
 */
$.validator.addMethod("isUpperCaseEnglish", function(value, element, param) {
	var pattern = /^[A-Z]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持大写英文");

/*
 * 数字英文和下划线
 * 
 * isFigureEnglishSymbol : true or false
 */
$.validator.addMethod("isFigureEnglishSymbol", function(value, element, param) {
	var pattern = /^\w+$/;
	return this.optional(element) || pattern.test(value);
}, "只支持数字、英文和下划线");

/*
 * 纯中文
 * 
 * isChinese : true or false
 */
$.validator.addMethod("isChinese", function(value, element, param) {
	var pattern = /^[\u4E00-\u9FA5]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持中文");

/*
 * 中文或英文
 * 
 * isChineseEnglish : true or false
 */
$.validator.addMethod("isChineseEnglish", function(value, element, param) {
	var pattern = /^[\u4E00-\u9FA5.A-Za-z]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持中文或英文（少数民族中文含有特殊符号使用数字键.代替）");

/*
 * Login Name
 * 
 * isLoginName : true or false
 */
$.validator.addMethod("isLoginName", function(value, element, param) {
	var pattern = /^[0-9a-zA-Z_]*$/;
	return this.optional(element) || pattern.test(value);
}, "只支持数字、字母和下划线");

/*
 * Login Password
 * 
 * isLoginPassword : true or false
 */
$.validator.addMethod("isLoginPassword", function(value, element, param) {
	var pattern = /^[\S]+$/;
	return this.optional(element) || pattern.test(value);
}, "支持数字、大小写字母和标点字符（空格除外）");

/*
 * Email
 * 
 * isEmail : true or false
 */
$.validator.addMethod("isEmail", function(value, element, param) {
	var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return this.optional(element) || pattern.test(value);
}, "正确的Email地址格式为：xxx@xxx.xxx");

/*
 * 电话
 * 
 * isTelephone : true or false
 */
$.validator.addMethod("isTelephone", function(value, element, param) {
	var pattern = /^\d{3,4}-\d{7,8}(-\d{1,5})?$/;
	return this.optional(element) || pattern.test(value);
}, "正确的电话格式为：xxx-xxxxxxxx(-xxx)，区号必填，分机号非必填");

/*
 * 中国大陆手机
 * 
 * isChinaMainlandMobile : true or false
 */
$.validator.addMethod("isChinaMainlandMobile", function(value, element, param) {
	var pattern = /^(1[0-9][0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
	return this.optional(element) || pattern.test(value);
}, "正确的手机格式为：1xxxxxxxxxx（只支持中国大陆手机号码）");

/*
 * 中国大陆身份证号
 * 
 * isChinaMainlandMobile : true or false
 */
$.validator.addMethod("isChinaMainlandIdentity", function(value, element, param) {
	return this.optional(element) || identityCardNo(value);
}, "只支持中国大陆18位身份证号码");

/*
 * 中国大陆身份证号校验
 */
function identityCardNo(value) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = value.length;
	var idNumber = value.toUpperCase();
	// initialize
	if ((intStrLen != 18)) {
		// error = "输入身份证号码长度不对！";
		// alert(error);
		// frmAddUser.txtIDCard.focus();
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			// error = "错误的身份证号码！";
			// alert(error);
			// frmAddUser.txtIDCard.focus();
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6, 14);
		if (checkDate(date8) == false) {
			// error = "身份证中日期信息不正确！.";
			// alert(error);
			return false;
		}
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = 12 - lngProduct % 11;
		switch (intCheckDigit) {
		case 10:
			intCheckDigit = 'X';
			break;
		case 11:
			intCheckDigit = 0;
			break;
		case 12:
			intCheckDigit = 1;
			break;
		}
		// check last digit
		if (varArray[17].toUpperCase() != intCheckDigit) {
			// error = "身份证效验位错误!...正确为： " + intCheckDigit + ".";
			// alert(error);
			return false;
		}
	} else {
		return false;
	}
	return true;
}

/*
 * 中国大陆身份证号-日期校验
 */
function checkDate(date) {
	return true;
}


$.validator.addMethod("isadminLoginon", function(value, element, param) {
	if ($(element).val() == "请输入用户名") {
		return false;
	}else{
		return true;
	}
},"请输入用户名");

$.validator.addMethod("isadminPasson", function(value, element, param) {
	if ($(element).val() == "请输入密码") {
		return false;
	}else{
		return true;
	}
},"请输入密码");

$.validator.addMethod("isauthcodeon", function(value, element, param) {
	if ($(element).val() == "请输入验证码") {
		return false;
	}else{
		return true;
	}
},"请输入验证码");

/**
 * 
 * 判断是否勾选协议
 *  
 */
$.validator.addMethod("ischeckboxtrue", function(value, element, param) {
	if ($(element).attr("checked")) {
		return true;
	}
	return false;
}, "请勾选协议");

/*
 * Internet URL
 * 
 * isHttpUrl : true or false
 */
$.validator
		.addMethod(
				"isHttpUrl",
				function(value, element, param) {
					var httpRegex = /^((https|http|ftp|rtsp|mms)?:\/\/)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/;
					var pattern = new RegExp(httpRegex);
					return this.optional(element) || pattern.test(value);
				}, "正确的网址格式为：http://xxx.xxx.xxx");

/*
 * IPV4
 * 
 * isIPv4 : true or false
 */
$.validator.addMethod("isIPv4", function(value, element, param) {
	var pattern = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	return this.optional(element) || pattern.test(value);
}, "正确的IPV4格式为：xxx.xxx.xxx.xxx");

/*
 * IPV6
 * 
 * isIPv6 : true or false
 */
$.validator
		.addMethod(
				"isIPv6",
				function(value, element, param) {
					var pattern = /^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/;
					return this.optional(element) || pattern.test(value);
				}, "正确的IPv6格式为：xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");