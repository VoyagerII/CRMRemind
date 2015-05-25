package com.elearning.web.util;

import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
public class HtmlParserForKingEditor 
{
	String htmldata = "";
	public HtmlParserForKingEditor(String strdata )
	{
		this.htmldata = strdata;
		
	}
	
	/**
	 * 分析html 得到第1 个img 地址. 
	 * @return
	 */
	public String getFirstImage()
	{
		String sret ="";
		 HtmlCleaner cleaner = new  HtmlCleaner(); 
		 TagNode tagnode  = cleaner.clean(htmldata);
		 TagNode findnode =  tagnode.findElementByName("img",true);
		 if(findnode !=null)
			 sret = findnode.getAttributeByName("src");
		 
		 return sret;
		 
	}
	
	private boolean isExists(String s , ArrayList<String> al)
	{
		boolean b =false;
		for(String src : al)
		{
			if(src.equals(s))
			{
				b =true;
				break; 
			}
			
		}
		
		return b; 
		
		
	}
	
	/**
	 * 得到html 中所有 文件url
	 * @return
	 */
	public String [] getAllFiles()
	{
		ArrayList <String >al = new ArrayList<String>();
		 HtmlCleaner cleaner = new  HtmlCleaner(); 
		 TagNode tagnode  = cleaner.clean(htmldata);
		 List listnode =  tagnode.getElementListByName("a",true);
		
		 for(Object o : listnode )
		 {
			 TagNode findnode = (TagNode)o;
			 
			 if(findnode !=null && "ke-insertfile".equals(findnode.getAttributeByName("class")))
			 {
				 String shref = findnode.getAttributeByName("href");
				 if(shref !=null && !"".equals(shref) && !isExists(shref,al))
				 {
					 al.add(shref);
				 }
			 }
		 }
		return al.toArray(new String[al.size()]);
	}
	
	
	
	public static void main(String args [] )
	{
		String strdata ="<a class=\"ke-insertfile\" href=\"http://localhost/editor/image/20140920/201409201359050.jpg\" target=\"_blank\">http://localhost/editor/image/20140920/201409201359050.jpg</a><a class=\"ke-insertfile\" href=\"http://localhost/editor/image/20140920/201409201359050.jpg\" target=\"_blank\">http://localhost/editor/image/20140920/201409201359050.jpg</a><img src=\"http://localhost/editor/image/20140920/201409201356520.jpg\" alt=\"\" />KindEditor<a class=\"ke-insertfile\" href=\"http://localhost/editor/image/20140920/201409201359050.jpg\" target=\"_blank\">http://localhost/editor/image/20140920/201409201359050.jpg</a>";
		HtmlParserForKingEditor parser = new HtmlParserForKingEditor(strdata);
		String simg = parser.getFirstImage();
		System.out.println ("image : " + simg );
		String sdata[] = parser.getAllFiles();
		for(String s : sdata)
		{
			System.out.println (s);
		}
		
	}
	
	
}
