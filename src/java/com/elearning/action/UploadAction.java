package com.elearning.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UploadFileConfig;
import com.elearning.web.util.JSONUtil;
import com.elearning.web.util.UploadFileUtils;

/**
 * 
 * TODO 所有上传文件控制层
 * 
 * @author xinglt
 * @date 2014年8月12日 上午10:43:50
 *
 */
public class UploadAction extends MultiActionController {

	private static final Log log = org.apache.commons.logging.LogFactory.getLog(UploadAction.class);

	public ModelAndView UploadFileByUptype(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		String requestType = request.getContentType();
		if (requestType != null && requestType.indexOf("multipart/form-data") != -1) {
			try {
				String clen = request.getHeader("Content-Length");
				log.info("Content-Length:" + clen);
				int nlen = Integer.parseInt(clen);
				if (nlen > 1024 * 100000) {
					log.info("上传文件太大了");
					returnJsonResultError(response, "上传文件太大了。");
					return null;
				}
				if (nlen <= 0) {
					log.info("上传文件不能为空");
					returnJsonResultError(response, "上传文件不能为空");
					return null;
				}

				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

				// 转换成多部分request
				MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
				// 上传的文件类型。
				String uptype = multipartRequest.getParameter("dir");
				MultipartFile f = multipartRequest.getFile("fileToUpload");
				String localfile = f.getOriginalFilename();

				if (f == null || StringUtils.isEmpty(localfile)) {
					log.info("上传文件imgFile 不能为空");

					returnJsonResultError(response, "上传文件imgFile 不能为空");
					return null;
				}

				UploadFileConfig conf = UploadFileUtils.allowfilelist.get(uptype);
				if (conf == null) {
					log.info("上传文件dir 类型不允许");
					returnJsonResultError(response, "上传文件dir 类型不允许");
					return null;
				}

				String ext = FilenameUtils.getExtension(localfile);
				ext = ext.toLowerCase();

				if (conf.getExt().indexOf(ext) == -1) {
					log.info("上传文件类型不允许,支持：" + conf.getExt());
					returnJsonResultError(response, "上传文件类型不允许 " + conf.getExt());
					return null;
				}

				String upfilename = UploadFileUtils.SaveFile(f, conf.getImgsrc(), uptype);

				String rep = File.separator;
				if (File.separator.equals("\\"))
					rep = "\\\\";
				String stemp = upfilename.replaceAll(rep, "/");

				String surl = conf.getHttpurl() + stemp;
				returnJsonResultOk(response, surl, stemp);
				log.info(surl);
			} catch (Exception se) {
				se.printStackTrace();
				returnJsonResultError(response, se.toString());

			}
		}

		return null;

	}

	public void returnJsonResultError(HttpServletResponse response, String err) {

		try {
			HashMap retmp = new HashMap();
			retmp.put("error", 1);
			retmp.put("message", err);
			JSONObject jobj = JSONObject.fromObject(retmp);
			response.setContentType("text/html;charset=UTF-8");
			String s = jobj.toString();
			log.info(s);
			response.getOutputStream().write(s.getBytes("utf-8"));
			response.getOutputStream().flush();

		} catch (Exception se) {

		}

	}

	/**
	 * 接收上传文件成功。
	 * 
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void returnJsonResultOk(HttpServletResponse response, String url, String dburl) throws ServletException, IOException {

		HashMap retmp = new HashMap();
		retmp.put("error", 0);
		retmp.put("url", url);
		retmp.put("dburl", dburl);

		JSONObject jobj = JSONObject.fromObject(retmp);
		response.setContentType("text/html;charset=UTF-8");
		String s = jobj.toString();
		log.info(s);
		response.getOutputStream().write(s.getBytes("utf-8"));
		response.getOutputStream().flush();
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public ModelAndView UploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {

		Map<String, String> ret = new HashMap<String, String>();
		String status = "N"; // Y：成功 N：失败
		String msg = "无消息"; // 返回消息
		response.setContentType("text/html; charset=UTF-8");
		String requestType = request.getContentType();

		if (requestType != null && requestType.indexOf("multipart/form-data") != -1) {
			try {
				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
				// 转换成多部分request
				MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
				// 上传的文件类型。
				String uptype = multipartRequest.getParameter("imagetype"); // 图片类型
				UploadFileConfig conf = UploadFileUtils.allowfilelist.get(uptype); // 根据图片类型获取图片的各项信息。包括：大小，类型，保存地址等
				if (null == conf) {
					ret.put("status", status);
					ret.put("msg", "上传文件类型不允许。");
					response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
					return null;
				}

				int nlen = Integer.parseInt(request.getHeader("Content-Length"));

				// 判断文件大小是否超过规定
				if (nlen > conf.getMaxsize()) {
					ret.put("status", status);
					ret.put("msg", "上传文件太大了。");
					response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
					return null;
				}
				if (nlen <= 0) {
					ret.put("status", status);
					ret.put("msg", "上传文件不能为空。");
					response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
					return null;
				}

				MultipartFile f = multipartRequest.getFile("headUpload");
				String localfile = f.getOriginalFilename();

				if (f == null || StringUtils.isEmpty(localfile)) {
					ret.put("status", status);
					ret.put("msg", "上传文件不能为空。");
					response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
					return null;
				}

				String ext = FilenameUtils.getExtension(localfile).toLowerCase();

				if (conf.getExt().indexOf(ext) <= -1) {
					ret.put("status", status);
					ret.put("msg", "上传文件类型不允许" + ext + "，允许上传的文件类型" + conf.getExt());
					response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
					return null;
				}

				String upfilename = UploadFileUtils.SaveFile(f, conf.getImgsrc(), uptype);

				String rep = File.separator;
				if (File.separator.equals("\\")){
					rep = "\\\\";
				}
				upfilename = upfilename.replaceAll(rep, "/");
				status = "Y";
				ret.put("status", status);
				ret.put("msg", upfilename);
				response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
			} catch (Exception se) {
				ret.put("status", status);
				ret.put("msg", se.toString());
				response.getOutputStream().print(new String(JSONUtil.getJSON(ret).getBytes("UTF-8"), "ISO-8859-1"));
				return null;
			}
		}
		return null;
	}
}
