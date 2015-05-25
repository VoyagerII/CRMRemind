package com.elearning.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * TODO PDF解析器
 * 
 * @author xinglt
 * @date 2014年8月9日 下午4:42:33
 *
 */
public class PdfParser {

	/**
	 * html静态页面转化为pdf页面
	 * 
	 * @param htmlFile
	 * @param pdfFile
	 * @param withEncrypt
	 * @return
	 */
	public static boolean convertHtmlToPDF(String fontTTCFilePath, String htmlFilePath,
			String pdfFilePath, boolean withEncrypt) {

		if (NullUtil.isNotNull(fontTTCFilePath) && NullUtil.isNotNull(htmlFilePath)
				&& NullUtil.isNotNull(pdfFilePath)) {
			// 不加密
			withEncrypt = false;

			File fontTTCFile = new File(fontTTCFilePath);
			File htmlFile = new File(htmlFilePath);
			File pdfFile = new File(pdfFilePath);

			//
			if (fontTTCFile.exists() && fontTTCFile.isFile() && htmlFile.exists()
					&& htmlFile.isFile()) {

				//
				if (!pdfFile.exists()) {
					pdfFile.getParentFile().mkdirs();
				}

				//
				OutputStream os = null;
				try {
					os = new FileOutputStream(pdfFilePath);
					ITextRenderer renderer = new ITextRenderer();
					if (withEncrypt) {
						PDFEncryption pdfEncryption = new PDFEncryption();
						pdfEncryption.setAllowedPrivileges(PdfWriter.ALLOW_DEGRADED_PRINTING
								| PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS);
						renderer.setPDFEncryption(pdfEncryption);
					}
					ITextFontResolver fontResolver = renderer.getFontResolver();
					fontResolver.addFont(fontTTCFilePath, BaseFont.IDENTITY_H,
							BaseFont.NOT_EMBEDDED);
					String url = new File(htmlFilePath).toURI().toURL().toString();
					renderer.setDocument(url);
					renderer.layout();
					renderer.createPDF(os);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					if (os != null) {
						try {
							os.close();
						} catch (IOException e) {
							return false;
						}
					}
				}
				return true;
			}
		}

		return false;
	}

	/**
	 * PDF批量合并
	 * 
	 * @param pdfFilePathList
	 * @param pdfFilePath
	 * @return
	 */
	public static boolean pdfMerge(List<String> sourceFilePathList, String targetFilePath) {

		if (null != sourceFilePathList && sourceFilePathList.size() > 0
				&& NullUtil.isNotNull(targetFilePath)) {

			//
			File targetFile = new File(targetFilePath);
			if (!targetFile.exists()) {
				targetFile.getParentFile().mkdirs();
			}

			//
			PdfCopyFields copy = null;
			try {
				copy = new PdfCopyFields(new FileOutputStream(targetFile));
				for (int i = 0; i < sourceFilePathList.size(); i++) {
					PdfReader reader = new PdfReader(sourceFilePathList.get(i));
					copy.addDocument(reader);
				}
			} catch (Exception e) {
				return false;
			} finally {
				if (null != copy) {
					try {
						copy.close();
					} catch (Exception e) {
						return false;
					}
				}
			}
			return true;
		}

		return false;
	}

	public static void main(String[] args) {

		boolean ret = PdfParser.convertHtmlToPDF("C:\\Users\\kane\\Desktop\\templates\\simsun.ttc",
				"C:\\Users\\kane\\Desktop\\templates\\CollegePrepareApplyFormTemplate.html", "C:\\Users\\kane\\Desktop\\templates\\CollegePrepareApplyFormTemplate.pdf", false);
		System.out.println(ret);
//		List<String> sourceFilePathList = new ArrayList<String>();
//		sourceFilePathList.add("F:\\1.pdf");
//		sourceFilePathList.add("F:\\2.pdf");
//
//		String targetFilePath = "F:\\t.pdf";
//
//		boolean flag = pdfMerge(sourceFilePathList, targetFilePath);
//
//		System.out.println(flag);
	}
}
