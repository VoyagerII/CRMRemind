/*
 * 显示遮档层
 */
function getLoadLayer(obj) {
	var wrap = obj;
	$("<div class=\"jquery-loading-css-datagrid-mask\" style=\"z-index:100000000000000\"></div>")
			.css({
				display : "block",
				width : wrap.width(),
				height : wrap.height(),
				left : 0,
				top : 0
			}).appendTo(wrap);
	$(
			"<div id=\"loadingDivId\" class=\"jquery-loading-css-datagrid-mask-msg\" style=\"z-index:100000000000000\"></div>")
			.appendTo(wrap).css(
					{
						display : "block",
						left : (wrap.width() - $("div.jquery-loading-css-datagrid-mask-msg", wrap)
								.outerWidth()) / 2,
						top : (wrap.height() - $("div.jquery-loading-css-datagrid-mask-msg", wrap)
								.outerHeight()) / 2
					});
}

/*
 * 移除遮档层
 */
function removeLoadLayer(obj) {
	var _rll = obj;
	_rll.children("div.jquery-loading-css-datagrid-mask-msg").remove();
	_rll.children("div.jquery-loading-css-datagrid-mask").remove();
}