package com.elearning.web.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.bean.UploadFileConfig;

public class UploadFileUtils {

	public static java.util.concurrent.atomic.AtomicLong gllong = new java.util.concurrent.atomic.AtomicLong();

	public static HashMap<String, UploadFileConfig> allowfilelist = new HashMap<String, UploadFileConfig>();

	public void setAllowfilelist(HashMap<String, UploadFileConfig> filelist) {

		UploadFileUtils.allowfilelist = filelist;
	}

	/**
	 * 根据类型 ， 查询配置参数。
	 * 
	 * @param uptype
	 * @return
	 */
	public static UploadFileConfig getConfigByType(String uptype) {

		if (allowfilelist != null) {
			return allowfilelist.get(uptype);
		}

		return null;
	}

	/**
	 * 根据时间产生唯一文件名字。
	 * 
	 * @param ext
	 * @return
	 */
	public static String CreateUniqueName(String ext) {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		long ltemp = gllong.get();

		if (ltemp > 999999)
			gllong.set(1);

		return df.format(new Date()) + ltemp + "." + ext;
	}

	/**
	 * 根据时间产生路径
	 * 
	 * @return
	 */
	public static String getDir() 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(new Date()).replace("/", File.separator);
	}

	
	public static String getDirURL() 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(new Date());
	}
	
	/**
	 * 保存文件公共方法
	 * 
	 * @param imgFile
	 * @param path
	 * @param uptype
	 * @return
	 */
	public static String SaveCommonFile(MultipartFile imgFile, String path, String uptype) {

		String fileName = imgFile.getOriginalFilename();
		String ext = FilenameUtils.getExtension(fileName);

		ext = ext.toLowerCase();
		String extdir = getDir();
		String dirurl =getDirURL();
		File fpath = new File(path + File.separator + uptype + File.separator + extdir);
		if (!fpath.exists())
			fpath.mkdirs();

		String sFile = CreateUniqueName(ext);

		try {
			String toFile = fpath.getAbsolutePath() + File.separator + sFile;
			System.out.println("save File :" + toFile);
			
			imgFile.transferTo(new File(toFile));

			return uptype + "/" + dirurl + "/" + sFile;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 将上传的文件保存到指定的目录， 返回一个保存后的文件名字。 之返回目录之后的部分
	 * 
	 * @param imgFile
	 * @param path
	 *            ： 保存的目录。
	 * @return "" ： 保存失败 。
	 */
	public static String SaveFile(MultipartFile imgFile, String path, String uptype) {

		String fileName = imgFile.getOriginalFilename();
		String ext = FilenameUtils.getExtension(fileName);

		ext = ext.toLowerCase();
			
		return SaveCommonFile(imgFile, path, uptype);
	
	}

	/**
	 * 保存图片
	 * 
	 * @param path
	 * @param imgFile
	 * @param fpath
	 * @param filename
	 * @return
	 */
	private static boolean saveImage(MultipartFile imgFile, File fpath, String filename) {

		try {
			String toFile = fpath.getAbsolutePath() + File.separator + filename;
			// System.out.println ("---------------------"+toFile);
			imgFile.transferTo(new File(toFile));
			return true;
			// 保存上传的文件
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String args[]) {

		// String ext = FilenameUtils.getExtension("aaa");
		// System.out.println ("["+ext+"]");
		File f = new File("d:\\temp\\");
		System.out.println(f.getAbsolutePath());

	}
}
