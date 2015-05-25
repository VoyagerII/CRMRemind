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
 * 空值返回
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
 * 空值返回0
 */
function isNotNullReturnZero(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return value;
	} else {
		return "0";
	}
}

/*
 * boolean返回0/1
 */
function isTrueReturnLong(value) {
	if (value) {
		return 1;
	} else {
		return 0;
	}
}

/*
 * 链接空值返回
 */
function linkIsNotNullReturnValue(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return value;
	} else {
		return "javascript:void(0)";
	}
}

/*
 * 链接空值返回
 */
function linkIsNotNullReturnValue(http, value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return http + value;
	} else {
		return "javascript:void(0)";
	}
}

/*
 * 0-9返回00-09
 */
function isNotNullReturnTimeValue(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		if (value < 10) {
			return "0" + value;
		} else {
			return value;
		}
	} else {
		return "";
	}
}