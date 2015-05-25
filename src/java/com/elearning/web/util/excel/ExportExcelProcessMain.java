package com.elearning.web.util.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.ServletRequestUtils;

import com.elearning.web.util.ConstantsUtil;
import com.elearning.web.util.NullUtil;

/**
 * 
 * TODO 导出Excel
 * 
 * @author xinglt
 * @date 2014年12月11日 下午6:50:44
 *
 */
public class ExportExcelProcessMain {

	/**
	 * 获取下载文件Path
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	public static String getDownloadFilePath(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> paramMap) {

		/**
		 * templateName：模板名称
		 * 
		 * paramRowNumber：参数所在行，默认为Excel第三行
		 */
		String templateName = ServletRequestUtils.getStringParameter(request, "templateName", null);
		Long paramRowNumber = ServletRequestUtils.getLongParameter(request, "paramRowNumber", 3);

		//
		if (null != paramMap && paramMap.size() > 0 && null != paramRowNumber
				&& NullUtil.isNotNull(templateName)) {

			/**
			 * TITLE_ROW_NUMBER：Excel模板标题参赛行。
			 * 
			 * downloadData：下载Excel数据，支持（List<Map<Object, Object>>格式数据）
			 */
			paramMap.put("TITLE_ROW_NUMBER", paramRowNumber);

			/**
			 * downloadFileName：下载文件自定义名称
			 */
			String downloadFileName = ServletRequestUtils.getStringParameter(request,
					"downloadFileName", null);
			String fileName = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator
					+ "dd").format(new Date())
					+ File.separator
					+ (NullUtil.isNotNull(downloadFileName) ? downloadFileName + "."
							+ FilenameUtils.getExtension(templateName) : System.currentTimeMillis()
							+ ".xls");
			/**
			 * templateName：下载文件模板文件名称
			 */
			String templateFilePath = ConstantsUtil.templatepath + templateName;

			// 下载文件存储路径
			String sourceFilePath = ConstantsUtil.temppath + fileName;

			//
			if (ExcelParseMain.excelParseByApachePOIAndExportData(templateFilePath, sourceFilePath,
					paramMap)) {

				// 判断文件是否存在
				File sourceFile = new File(sourceFilePath);
				if (sourceFile.isFile() && sourceFile.exists()) {

					return sourceFilePath;
				}
			}
		}

		return null;
	}

	/**
	 * 获取下载文件URL
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	public static String getDownloadFileUrl(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> paramMap) {

		/**
		 * templateName：模板名称
		 * 
		 * paramRowNumber：参数所在行，默认为Excel第三行
		 */
		String templateName = ServletRequestUtils.getStringParameter(request, "templateName", null);
		Long paramRowNumber = ServletRequestUtils.getLongParameter(request, "paramRowNumber", 3);

		//
		if (null != paramMap && paramMap.size() > 0 && null != paramRowNumber
				&& NullUtil.isNotNull(templateName)) {

			/**
			 * TITLE_ROW_NUMBER：Excel模板标题参赛行。
			 * 
			 * downloadData：下载Excel数据，支持（List<Map<Object, Object>>格式数据）
			 */
			paramMap.put("TITLE_ROW_NUMBER", paramRowNumber);

			/**
			 * downloadFileName：下载文件自定义名称
			 */
			String downloadFileName = ServletRequestUtils.getStringParameter(request,
					"downloadFileName", null);
			String fileName = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator
					+ "dd").format(new Date())
					+ File.separator
					+ (NullUtil.isNotNull(downloadFileName) ? downloadFileName + "."
							+ FilenameUtils.getExtension(templateName) : System.currentTimeMillis()
							+ ".xls");
			/**
			 * templateName：下载文件模板文件名称
			 */
			String templateFilePath = ConstantsUtil.templatepath + File.separator + templateName;

			// 下载文件存储路径
			String sourceFilePath = ConstantsUtil.temppath + File.separator + fileName;

			//
			if (ExcelParseMain.excelParseByApachePOIAndExportData(templateFilePath, sourceFilePath,
					paramMap)) {

				// 判断文件是否存在
				File sourceFile = new File(sourceFilePath);
				if (sourceFile.isFile() && sourceFile.exists()) {

					return ConstantsUtil.tempurl + fileName;
				}
			}
		}

		return null;
	}
}
