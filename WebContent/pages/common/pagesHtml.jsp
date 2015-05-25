<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	实现 翻页页码标签， 
	翻页过程只能是 页面跳转方式 通过gourl 传递过来。 
	
	参数说明：
		tagid : 页面中多次载入标记，同一页面中, 不可重复, 不可含有特殊字符按变量定义规则。
	
	  gourl : 翻页后， 跳转的地址。 
	  pageno : 当前页码。 
	  totalpage : 最大页码。 
	  
	 -->
<%
	String pageno= request.getParameter("pageno");
	String totalpage= request.getParameter("totalpage");
	String tagid= request.getParameter("tagid");
	String params = request.getParameter("params");
		
	String gourl ="";
	gourl= request.getParameter("gourl");
	
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
	if(0 == ntotalpageno){
		ntotalpageno = 1;
	}
	if(npageno > ntotalpageno){
		npageno = ntotalpageno;
	}
	if(1 > npageno){
		npageno = 1;
	}
%>


<div style="text-align: center">
	<ul class="conntent_paging_list_g141166">


		<% out.println("<li><a href=\""+linkURL(gourl,"pageno","1")+"\">首页</a></li>");%>

		<%
 if(npageno >1)
 {
		out.println ("<li><a href=\""+linkURL(gourl,"pageno",""+(npageno -1))+"\">上一页</a></li>");
 }else{
	 out.println ("<li><span>上一页</span></li>");
 }
 
 	//当前页的前 2 页 。
 	int bpageno = npageno -2;
 	if((ntotalpageno-npageno)<2){
 		bpageno = bpageno - (2 - (ntotalpageno - npageno));
 	}
 	if(bpageno <1) bpageno =1;
 	for(int i=bpageno;i <npageno ;i++)
 	{
 		out.println ("<li><a href=\""+linkURL(gourl,"pageno",""+i)+"\">"+i+"</a></li>");
 	}
 	out.println ("<li><span>"+npageno +"</span></li>");
 	//当前页的后 2 页 。
 	int epageno = npageno +2;
 	if(npageno<3){
 		epageno = epageno + (5 - epageno);
 	}
 	if(epageno > ntotalpageno) epageno = ntotalpageno;
 	
	for(int i=npageno+1;i<=epageno ;i++)
 	{
 		out.println ("<li><a href=\""+linkURL(gourl,"pageno",""+i)+"\">"+i+"</a></li>");
 	}
 	if(npageno+1 <= ntotalpageno)
	out.println ("<li><a href=\""+linkURL(gourl,"pageno",""+(npageno +1))+"\" class=\"page-enablenextpage\">下一页</a></li>");
 	else
 		 out.println ("<li><span>下一页</span></li>");
	 
 %>
		<% out.println("<li><a href=\""+linkURL(gourl,"pageno",""+ntotalpageno)+"\">尾页</a></li>");%>

	</ul>
</div>
<%!
 

	private static  String linkURL(String srcurl, String strname , String strval)
	{
		String returl =srcurl;
		try
		{
		if(returl.indexOf("?") !=-1)
		{
			returl +="&"+strname +"=" + java.net.URLEncoder.encode(strval,"utf-8");
		}else
			returl +="?"+strname +"=" + java.net.URLEncoder.encode(strval,"utf-8");
		}catch(Exception se)
		{
			se.printStackTrace();
		}
		return returl;
		
	}
	
	
 %>