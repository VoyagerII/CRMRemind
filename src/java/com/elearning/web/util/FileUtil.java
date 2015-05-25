package com.elearning.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package cn.com.dekn.cms.util:
//            StrUtil

public class FileUtil {

	public FileUtil() {

	}

	public static String readTxt(String i1111IIl1Il1Il1Ill, String i1ll1I1l11l) throws IOException {

		i1ll1I1l11l = i1ll1I1l11l.trim();
		StringBuffer i11lI1 = new StringBuffer("");
		String i1Ill = "";
		try {
			FileInputStream iI11I = new FileInputStream(i1111IIl1Il1Il1Ill);
			InputStreamReader i1llI1;
			if (i1ll1I1l11l.equals("")) {
				i1llI1 = new InputStreamReader(iI11I);
			} else {
				i1llI1 = new InputStreamReader(iI11I, i1ll1I1l11l);
			}
			BufferedReader ill11 = new BufferedReader(i1llI1);
			try {
				for (String iIllll1 = ""; (iIllll1 = ill11.readLine()) != null;) {
					i11lI1.append(iIllll1 + " ");
				}

			} catch (Exception illl) {
				i11lI1.append(illl.toString());
			}
			i1Ill = i11lI1.toString();
		} catch (IOException iI111) {
			i1Ill = "";
		}
		return i1Ill;
	}

	public static String createFolder(String ilIIIlI1IIlll) {

		String i1lIll = ilIIIlI1IIlll;
		try {
			File iIl11IIlII111 = new File(i1lIll);
			i1lIll = ilIIIlI1IIlll;
			if (!iIl11IIlII111.exists()) {
				iIl11IIlII111.mkdir();
			}
		} catch (Exception illl) {
			illl.printStackTrace();
		}
		return i1lIll;
	}

	public static String createFolders(String ilIIIlI1IIlll, String ill1IIlI) {

		String iI11l11 = ilIIIlI1IIlll;
		try {
			iI11l11 = ilIIIlI1IIlll;
			StringTokenizer i1Ill = new StringTokenizer(ill1IIlI, "|");
			for (int i11I = 0; i1Ill.hasMoreTokens(); i11I++) {
				String i1lIll = i1Ill.nextToken().trim();
				if (iI11l11.lastIndexOf(File.separator) != -1) {
					iI11l11 = createFolder(iI11l11 + i1lIll);
				} else {
					iI11l11 = createFolder(iI11l11 + i1lIll + File.separator);
				}
			}

		} catch (Exception illl) {
			illl.printStackTrace();
		}
		return iI11l11;
	}

	public static void createFile(String i1111IIl1Il1Il1Ill, String il1IlIllII1lII) {

		try {
			String i1llIIl1ll1 = i1111IIl1Il1Il1Ill;
			i1llIIl1ll1 = i1llIIl1ll1.toString();
			File iIl11IIlII111 = new File(i1llIIl1ll1);
			if (!iIl11IIlII111.exists()) {
				iIl11IIlII111.createNewFile();
			}
			FileWriter il1111llll1ll = new FileWriter(iIl11IIlII111);
			PrintWriter ill1l1lll = new PrintWriter(il1111llll1ll);
			String il111llI1IIII = il1IlIllII1lII;
			ill1l1lll.println(il111llI1IIII);
			ill1l1lll.close();
			il1111llll1ll.close();
		} catch (Exception illl) {
			illl.printStackTrace();
		}
	}

	public static boolean delFile(String i1111IIl1Il1Il1Ill) {

		boolean i1lIII = false;
		try {
			String i1llIIl1ll1 = i1111IIl1Il1Il1Ill;
			File i1I1l1Il1111 = new File(i1llIIl1ll1);
			if (i1I1l1Il1111.exists()) {
				i1I1l1Il1111.delete();
				i1lIII = true;
			} else {
				i1lIII = false;
			}
		} catch (Exception illl) {
			illl.printStackTrace();
		}
		return i1lIII;
	}

	public static void delFolder(String ilIIIlI1IIlll) {

		try {
			delAllFile(ilIIIlI1IIlll);
			String i1llIIl1ll1 = ilIIIlI1IIlll;
			i1llIIl1ll1 = i1llIIl1ll1.toString();
			File iIl11IIlII111 = new File(i1llIIl1ll1);
			iIl11IIlII111.delete();
		} catch (Exception illl) {
			illl.printStackTrace();
		}
	}

	public static boolean delAllFile(String i11Ill1) {

		boolean i1lIII = false;
		File i1lIIl1 = new File(i11Ill1);
		if (!i1lIIl1.exists()) {
			return i1lIII;
		}
		if (!i1lIIl1.isDirectory()) {
			return i1lIII;
		}
		String iI1l1lII111[] = i1lIIl1.list();
		File iII11I1 = null;
		for (int i11I = 0; i11I < iI1l1lII111.length; i11I++) {
			if (i11Ill1.endsWith(File.separator)) {
				iII11I1 = new File(i11Ill1 + iI1l1lII111[i11I]);
			} else {
				iII11I1 = new File(i11Ill1 + File.separator + iI1l1lII111[i11I]);
			}
			if (iII11I1.isFile()) {
				iII11I1.delete();
			}
			if (iII11I1.isDirectory()) {
				delAllFile(i11Ill1 + File.separator + iI1l1lII111[i11I]);
				delFolder(i11Ill1 + File.separator + iI1l1lII111[i11I]);
				i1lIII = true;
			}
		}

		return i1lIII;
	}

	public static void copyFile(String i1llII1lIllI11, String i1l1lIllI1I11I) {

		try {
			int il1I1IIIlI = 0;
			int iIIlI1l1l11 = 0;
			File i1l111111l = new File(i1llII1lIllI11);
			if (i1l111111l.exists()) {
				InputStream iII1lIIIII1 = new FileInputStream(i1llII1lIllI11);
				FileOutputStream iI11I = new FileOutputStream(i1l1lIllI1I11I);
				byte buffer[] = new byte[1444];
				while ((iIIlI1l1l11 = iII1lIIIII1.read(buffer)) != -1) {
					il1I1IIIlI += iIIlI1l1l11;
					System.out.println(il1I1IIIlI);
					iI11I.write(buffer, 0, iIIlI1l1l11);
				}
				iII1lIIIII1.close();
			}
		} catch (Exception illl) {
			illl.printStackTrace();
		}
	}

	public static void copyFolder(String ilIIIIl1ll, String illl111ll1) {

		try {
			(new File(illl111ll1)).mkdirs();
			File il11 = new File(ilIIIIl1ll);
			String i1lIIl1[] = il11.list();
			File iII11I1 = null;
			for (int i11I = 0; i11I < i1lIIl1.length; i11I++) {
				if (i1lIIl1[i11I].equals(".") || i1lIIl1[i11I].equals(".."))
					continue;

				if (ilIIIIl1ll.endsWith(File.separator)) {
					iII11I1 = new File(ilIIIIl1ll + i1lIIl1[i11I]);
				} else {
					iII11I1 = new File(ilIIIIl1ll + File.separator + i1lIIl1[i11I]);
				}
				if (iII11I1.isFile()) {
					FileInputStream i1III1II = new FileInputStream(iII11I1);
					FileOutputStream ilIl111l1 = new FileOutputStream(illl111ll1 + File.separator
							+ iII11I1.getName().toString());
					byte b[] = new byte[5120];
					int iIIIIl;
					while ((iIIIIl = i1III1II.read(b)) != -1) {
						ilIl111l1.write(b, 0, iIIIIl);
					}

					ilIl111l1.flush();
					ilIl111l1.close();
					i1III1II.close();
					System.out.println("copyfile : " + illl111ll1 + File.separator
							+ iII11I1.getName().toString());
				}
				if (iII11I1.isDirectory()) {
					copyFolder(ilIIIIl1ll + File.separator + i1lIIl1[i11I], illl111ll1
							+ File.separator + i1lIIl1[i11I]);
				}
			}

		} catch (Exception illl) {
			illl.printStackTrace();
		}
	}

	public static void moveFile(String ilIIIIl1ll, String illl111ll1) {

		copyFile(ilIIIIl1ll, illl111ll1);
		delFile(ilIIIIl1ll);
	}

	public static void moveFolder(String ilIIIIl1ll, String illl111ll1) {

		copyFolder(ilIIIIl1ll, illl111ll1);
		delFolder(ilIIIIl1ll);
	}

	public static String getUploadFileRealPath(HttpServletRequest il1lIlIII1, String i1I111Il1l) {

		String iI1IIlIlI1l = "";
		// FileUploadConfigVO i1lll1II1 = FileUploadConfigVO.getInstance();
		// String il11lII1llIl = i1lll1II1.getFILE_UPLOAD_DIR();
		// SimpleDateFormat iI1llll = new SimpleDateFormat("yyMMdd");
		// String ilIIll1lI = iI1llll.format(new Date());
		// String ill1IllIlII1IIlI = il1lIlIII1.getRealPath(il11lII1llIl) +
		// File.separator + ilIIll1lI;
		// File illlII1lIl = new File(ill1IllIlII1IIlI);
		// if(!illlII1lIl.exists())
		// {
		// illlII1lIl.mkdirs();
		// }
		// SimpleDateFormat iIl1IlII1I1 = new
		// SimpleDateFormat("yyMMddHHmmssSSS");
		// String il1Il11lIll = "CMS4J_" + iIl1IlII1I1.format(new Date()) + "0."
		// + i1I111Il1l;
		// iI1IIlIlI1l = illlII1lIl + File.separator + il1Il11lIll;
		return iI1IIlIlI1l;
	}

	public static boolean download(String url, String savePath) {

		String i1II1Il1I1 = url;
		String i1l1lIlIII1 = savePath;
		String filetype = "";
		if (url == null)
			return false;

		if (url.indexOf(".") > -1 && url.lastIndexOf(".") < url.length() - 1)
			filetype = url.substring(url.lastIndexOf(".") + 1);

		try {
			URL iI1I11 = new URL(i1II1Il1I1);
			URLConnection iIII1l1 = iI1I11.openConnection();
			iIII1l1.connect();
			HttpURLConnection iIlIl11l1I1 = (HttpURLConnection) iIII1l1;
			int i1l1l1lIllII1IIII = iIlIl11l1I1.getResponseCode();
			if (i1l1l1lIllII1IIII != 200) {
				System.out.println("\u8FDE\u63A5\u5730\u5740: " + i1II1Il1I1
						+ " \u5931\u8D25,\u9519\u8BEF\u7801:" + i1l1l1lIllII1IIII);
				return false;
			}
			int iI1lll1lII = iIII1l1.getContentLength();
			InputStream i1l1l = iIII1l1.getInputStream();
			byte tmpbuf[] = new byte[1024];
			File iII1Illl1l1 = new File(i1l1lIlIII1);
			if (iII1Illl1l1.exists()) {
				if (iII1Illl1l1.isDirectory()) {
					i1l1lIlIII1 = i1l1lIlIII1 + File.separator + "" + System.currentTimeMillis()
							+ "." + filetype;
					iII1Illl1l1 = new File(i1l1lIlIII1);
				}
			} else
				iII1Illl1l1.createNewFile();

			FileOutputStream i1II1l = new FileOutputStream(iII1Illl1l1);
			int i11I1llI1l = 0;
			if (iI1lll1lII < 0) {
				while (i11I1llI1l > -1) {
					i11I1llI1l = i1l1l.read(tmpbuf);
					if (i11I1llI1l > 0) {
						i1II1l.write(tmpbuf, 0, i11I1llI1l);
					}
				}
			} else {
				int illI1ll11IIl;
				for (illI1ll11IIl = 0; illI1ll11IIl < iI1lll1lII && i11I1llI1l != -1;) {
					i11I1llI1l = i1l1l.read(tmpbuf);
					if (i11I1llI1l > 0) {
						i1II1l.write(tmpbuf, 0, i11I1llI1l);
						illI1ll11IIl += i11I1llI1l;
					}
				}

				if (illI1ll11IIl < iI1lll1lII) {
					System.out.println("\u4E0B\u8F7D\u7B2C\u4E09\u65B9\u8D44\u6E90\u9519\u8BEF");
					i1l1l.close();
					i1II1l.close();
					iII1Illl1l1.delete();
					return false;
				}
			}
			i1II1l.flush();
			i1II1l.close();
			i1l1l.close();
		} catch (Exception illl) {
			illl.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]) {

		// FileUtil.download("http://www.baidu.com/img/logo-yy.gif",
		// "D:\\Program Files\\tomcat\\webapps\\");

		FileUtil.copyFolder("d:\\temp\\u", "d:\\temp\\uback");
	}

}
