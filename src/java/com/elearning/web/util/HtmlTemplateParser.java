package com.elearning.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * 
 * TODO HTML模板解析器
 * 
 * @author xinglt
 * @date 2014年8月11日 上午9:10:05
 *
 */
public class HtmlTemplateParser {

	private static final String HTML_SUFFIX = "html";

	/**
	 * 生成html
	 * 
	 * @param templateFilePath
	 * @param targetFilePath
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static boolean htmlBuilder(String templateFilePath, String targetFilePath,
			Map<String, Object> paramMap) throws Exception {

		if (NullUtil.isNotNull(templateFilePath)
				&& NullUtil.isNotNull(targetFilePath)
				&& NullUtil
						.isNotNullResultStringValue(FilenameUtils.getExtension(templateFilePath))
						.toUpperCase().equals(HTML_SUFFIX.toUpperCase())
				&& NullUtil.isNotNullResultStringValue(FilenameUtils.getExtension(targetFilePath))
						.toUpperCase().equals(HTML_SUFFIX.toUpperCase()) && null != paramMap
				&& paramMap.size() > 0) {

			// Template File
			File templateFile = new File(templateFilePath);

			// Target File
			File targetFile = new File(targetFilePath);
			if (!targetFile.exists()) {
				targetFile.getParentFile().mkdirs();
			}

			// 判断html模板文件是否存在
			if (templateFile.exists() && templateFile.isFile()) {
				//
				Properties properties = new Properties();
				properties.setProperty("file.resource.loader.path", templateFile.getParent());

				//
				Velocity.init(properties);

				//
				Template template = Velocity.getTemplate(templateFile.getName(), "UTF-8");

				//
				VelocityContext context = new VelocityContext();
				for (Iterator mapIterator = paramMap.entrySet().iterator(); mapIterator.hasNext();) {
					Map.Entry ME = (Entry) mapIterator.next();
					if (null != ME && NullUtil.isNotNull(ME.getKey())) {
						context.put(NullUtil.isNotNullResultStringValue(ME.getKey()), ME.getValue());
					}
				}

				//
				PrintWriter writer = new PrintWriter(targetFile.getPath(), "UTF-8");

				//
				template.merge(context, writer);

				//
				writer.close();
			}

			// 判断目标文件是否已存在
			if (!(targetFile.exists() && targetFile.isFile())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 读取指定路径的html内容生成String
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public static String getHtmlString(String path) throws IOException{
		BufferedReader br= null;
		String fileContent = "";
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			String line="";
			StringBuffer  buffer = new StringBuffer();
			while((line=br.readLine())!=null){
			buffer.append(line);
			}
			fileContent = buffer.toString();
		}
		finally{
			if(br!=null)
				br.close();
		}
		
		return fileContent;
	}
}
