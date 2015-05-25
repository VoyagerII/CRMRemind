<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
		实现 翻页页码标签， 
		翻页过程点击后， 会调用某一javascript 函数。 
	参数说明：
	tagid : 页面中多次载入标记，同一页面中, 不可重复, 不可含有特殊字符按变量定义规则。
	pageno : 当前页码。  
	totalpage :  最大页数
	jsfunc : javascript 函数名称。 
	jsparam: 函数回调过程， 可以将部分参数传递过去。 这个必须是对象， 也就是
	这种格式： {name:'dfsfdsfds',id:2222}
	
	 -->
<%
	String pageno= request.getParameter("pageno");
	String totalpage= request.getParameter("totalpage");
	String tagid = request.getParameter("tagid");
	if(tagid ==null) tagid="cc";
	
	//支持 翻页回调 javascript . 
	String jsfunc=request.getParameter("jsfunc");
	String jsparam=request.getParameter("jsparam");
	if(jsparam ==null || "".equals(jsparam))
		jsparam="{}";
		
	
	if(pageno ==null || "".equals(pageno))
		pageno ="1";
	else
		pageno = pageno.trim();
	if(totalpage ==null || "".equals(totalpage))
	totalpage ="1";
	else
		totalpage = totalpage.trim();
	int npageno = Integer.parseInt(pageno);
	int ntotalpageno = Integer.parseInt(totalpage);
%>

<script>
function PageJavascript  (tg , jsfuncname, jsparam)
{
	this.JS_func = jsfuncname;
	this.TagName = tg;
	this.JS_param = jsparam;
	this.isNotNull=  function (value) {
		if (null != value && "null" != value && "" != value && undefined != value
				&& "undefined" != value) {
			return true;
		} else {
			return false;
		}
	};
};
  
 $.PJS<%=tagid%>=new PageJavascript('<%=tagid%>',<%=jsfunc%>, <%=jsparam%>);
 
  </script>
<div style="text-align: center">
	<ul class="conntent_paging_list_g141166">

		<% out.println ("<li><a href=\"javascript:;\"  onclick=\""+jsfunc+"(" + 1 + ","+jsparam+")"+"\" class=\"page-enablenextpage\">首页</a></li>");%>

		<%
 if(npageno >1)
 {
	 String str = jsfunc+"(" + ((npageno -1)) + ","+jsparam+")";
	 
		out.println ("<li><a href=\"javascript:;\"  onclick=\""+str+"\" class=\"page-enablenextpage\">上一页</a></li>");
 }else
	 out.println ("<li><span>上一页</span></li>");
	
 
 
//当前页的前 2 页 。
	int bpageno = npageno -2;
	if((ntotalpageno-npageno)<2){
		bpageno = bpageno - (2 - (ntotalpageno - npageno));
	}
	if(bpageno <1) bpageno =1;
	for(int i=bpageno;i <npageno ;i++)
	{
		String str = jsfunc+"(" + (i) + ","+jsparam+")";
		out.println ("<li><a href=\"javascript:;\" onclick=\""+str+"\">"+i+"</a></li>");
	}
 	out.println ("<li><span class=\"page-current\">"+npageno +"</span></li>" );
 	//当前页的后 2 页 。
 	int epageno = npageno +2;
 	if(npageno<3){
 		epageno = epageno + (5 - epageno);
 	}
 	if(epageno > ntotalpageno) epageno = ntotalpageno;
 	
	for(int i=npageno+1;i<=epageno ;i++)
 	{
		String str = jsfunc+"(" + (i) + ","+jsparam+")";
		out.println ("<li><a href=\"javascript:;\" onclick=\""+str+"\">"+i+"</a></li>");
 	}

 	if(npageno+1 <= ntotalpageno)
 	{
 		 String str = jsfunc+"(" + (npageno +1) + ","+jsparam+")";
		 out.println ("<li><a href=\"javascript:;\" onclick=\""+str+"\" class=\"page-enablenextpage\">下一页</a></li>");
 	}
	else
 		 out.println ("<li><span>下一页</span></li>");
	 
 %>
		<% out.println ("<li><a href=\"javascript:;\"  onclick=\""+jsfunc+"(" + totalpage + ","+jsparam+")"+"\">尾页</a></li>");%>
	</ul>
</div>