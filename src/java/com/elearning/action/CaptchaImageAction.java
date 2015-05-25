package com.elearning.action;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

/**
 * 
 * TODO 图片验证码
 * 
 * @author guocy
 * @date 2014年8月1日 下午8:08:47
 *
 */
public class CaptchaImageAction extends MultiActionController {

	private Properties Params = new Properties();

	private Producer kaptchaProducer = null;

	private String sessionKeyValue = null;

	private String sessionKeyDateValue = null;

	public String getSessionKeyValue() {

		return sessionKeyValue;
	}

	public String getSessionKeyDateValue() {

		return sessionKeyDateValue;
	}

	public void setParams(Properties prop) {

		Params = prop;
		Config config = new Config(Params);
		kaptchaProducer = config.getProducerImpl();
		sessionKeyValue = config.getSessionKey();
		sessionKeyDateValue = config.getSessionDate();
	}

	public ModelAndView getCaptchaImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, Exception {

		HttpServletResponse resp = response;
		HttpServletRequest req = request;

		// Set to expire far in the past.
		resp.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "max-age=0");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");

		// return a jpeg
		resp.setContentType("image/jpeg");

		// create the text for the image
		String capText = this.kaptchaProducer.createText();

		// store the text in the session
		req.getSession().setAttribute(this.sessionKeyValue, capText);

		// store the date in the session so that it can be compared
		// against to make sure someone hasn't taken too long to enter
		// their kaptcha
		req.getSession().setAttribute(this.sessionKeyDateValue, new Date());

		// create the image with the text
		BufferedImage bi = this.kaptchaProducer.createImage(capText);

		ServletOutputStream out = resp.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);

		out.flush();

		return null;

	}
}
