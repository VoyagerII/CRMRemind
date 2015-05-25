/*
 * html 特殊字符转义。
 * 
 * @param strdata
 */

function convertToHtml(strdata) {
	if (strdata == null)
		return "";
	var sb = "";
	for (i = 0; i < strdata.length; i++) {
		var c = strdata.charAt(i);
		switch (c) {
		case '<':
			sb += ("&lt;");
			continue;
		case '>':
			sb += ("&gt;");
			continue;
		case '&':
			sb += ("&amp;");
			continue;
		case '\'':
			sb += ("&apos;");
			continue;
		case '"':
			sb += ("&quot;");
			continue;
		case '\r':
			sb += ("&#xd;");
			continue;
		case '\n':
			sb += ("&#xa;");
			continue;
		default:
			sb += (c);
			continue;
		}
	}

	return sb;
}

/*
 * 空值判断
 */
function isNotNull(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return true;
	} else {
		return false;
	}
}

/*
 * 判断值是否为空并返回值
 */
function isNotNullReturnValue(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return value;
	} else {
		return "";
	}
}

/*
 * url参数解析
 */
function urlParamParse(key) {
	var paramValue = null;
	if (null != key && undefined != key && "" != key && "null" != key && "undefined" != key
			&& key.length > 0) {
		var param = document.location.search;
		if (null != param && undefined != param && "" != param && "null" != param
				&& "undefined" != param && param.length > 0) {
			param = unescape(param);
			param = param.substring(param.indexOf("?", 0) + 1, param.length);
			for (var i = 0; i < param.split("&").length; i++) {
				if (null != String(param.split("&")[i]) && undefined != String(param.split("&")[i])
						&& "" != String(param.split("&")[i])
						&& "null" != String(param.split("&")[i])
						&& "undefined" != String(param.split("&")[i])
						&& String(param.split("&")[i]).length > 0
						&& String(param.split("&")[i]).split("=").length > 0) {
					var paramkey = String(param.split("&")[i]).split("=")[0];
					//
					if (null != paramkey && undefined != paramkey && "" != paramkey
							&& "null" != paramkey && "undefined" != paramkey && paramkey.length > 0
							&& paramkey.toUpperCase() == key.toUpperCase()) {
						paramValue = String(param.split("&")[i]).split("=")[1];
						break;
					}
				}
			}
		}
	}

	return paramValue;
}

/*
 * 动态加载页面内容
 */
function dynmaicLoadPageContent(url, param, elementId) {
	// 我的大赛——右侧内容显示
	if (isNotNull(url)) {
		$.post(url, param, function(resultData, resultStatus) {
			if (isNotNull(resultStatus) && resultStatus.toUpperCase() == "SUCCESS") {
				$("#" + elementId).html(resultData);
			} else {
				alert('bbbb');
				$("#" + elementId).html(
						"<span style='font-size: 15px; margin: 5px 5px auto auto; "
								+ "color: #FF0000;'>Sorry！加载失败</span>");
			}
		}, "text");
	} else {
		window.location.href = window.location.href.split("?")[0];
	}
}

$.jheartbeat = {
	options : {
		delay : 10000
	},
	beatfunction : function() {
	},
	timeoutobj : {
		id : -1
	},

	set : function(options, onbeatfunction) {
		if (this.timeoutobj.id > -1) {
			clearTimeout(this.timeoutobj);
		}
		if (options) {
			$.extend(this.options, options);
		}
		if (onbeatfunction) {
			this.beatfunction = onbeatfunction;
		}

		this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
	},

	beat : function() {
		this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
		this.beatfunction();
	}
};

/**
 * 每隔一定的时间 执行func
 */
function lanqiaotimer(func, interval) {
	$.jheartbeat.set({
		delay : interval
	}, func);
}

function html_encode(str) {
	var s = "";
	if (str.length == 0)
		return "";
	s = str.replace(/&/g, "&amp;");
	s = s.replace(/</g, "&lt;");
	s = s.replace(/>/g, "&gt;");
	// s = s.replace(/ /g, "&nbsp;");
	s = s.replace(/\'/g, "&#39;");
	s = s.replace(/\"/g, "&quot;");
	// s = s.replace(/\n/g, "<br>");
	return s;
}

function html_decode(str) {
	var s = "";
	if (str.length == 0)
		return "";
	s = str.replace(/&gt;/g, "&");
	s = s.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s = s.replace(/&nbsp;/g, " ");
	s = s.replace(/&#39;/g, "\'");
	s = s.replace(/&quot;/g, "\"");
	s = s.replace(/<br>/g, "\n");
	return s;
}

/**
 * 
 * 类似 java 的hashmap ，
 * 
 */
function HashMap() {

	/** Map 大小 * */
	var size = 0;

	/** 对象 * */
	var entry = new Object();

	/** 存 * */
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
		}
		entry[key] = value;
	}

	/** 取 * */
	this.get = function(key) {
		if (this.containsKey(key)) {
			return entry[key];
		} else {
			return null;
		}
	}

	/** 删除 * */
	this.remove = function(key) {
		if (delete entry[key]) {
			size--;
		}
	}

	/** 是否包含 Key * */
	this.containsKey = function(key) {
		return (key in entry);
	}

	/** 是否包含 Value * */
	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	}

	/** 所有 Value * */
	this.values = function() {
		var values = new Array(size);
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	}

	/** 所有 Key * */
	this.keys = function() {
		var keys = new Array(size);
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	}

	/** Map Size * */
	this.size = function() {
		return size;
	}
};

/**
 * 取出字符串中的空格
 */
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}