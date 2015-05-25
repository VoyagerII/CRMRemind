package com.elearning.web.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * 
 * TODO UDP指令控制客户端
 * 
 * @author xinglt
 * @date 2014年11月6日 上午10:18:58
 *
 */
public class UDPCommandClient {

	String castip = "192.168.200.255";

	// server 监听的端口
	int m_port = 7690;

	public String getCastip() {

		return castip;
	}

	public void setCastip(String castip) {

		this.castip = castip;
	}

	public int getM_port() {

		return m_port;
	}

	public void setM_port(int m_port) {

		this.m_port = m_port;
	}

	/**
	 * 载入所有的字典数据到tomcat
	 * 
	 * @return :true: 可能成功, false;必然失败.
	 * 
	 * @throws Exception
	 */
	public boolean SendCmdReloadDictionary() throws Exception {

		boolean b = isSuccData(SendCmd("reloadalldic"));
		return b;
	}

	/**
	 * 判断返回的结果是否正错, 如果有tomcat 没启动, 无法处理这种情况.
	 * 
	 * @param aldata
	 * @return
	 */
	public boolean isSuccData(ArrayList<DatagramPacket> aldata) throws Exception {

		boolean bret = true;

		for (DatagramPacket p : aldata) {
			String s = new String(p.getData(), "UTF-8");
			if (!s.startsWith("1")) {
				bret = false;
				break;
			}
		}
		return bret;
	}

	/**
	 * 广播发送指令.
	 * 
	 * @param sdata
	 * @throws Exception
	 */
	public ArrayList<DatagramPacket> SendCmd(String sdata) throws Exception {

		DatagramSocket client = new DatagramSocket();
		ArrayList<DatagramPacket> aldata = new ArrayList<DatagramPacket>();

		try {
			byte[] buf = sdata.getBytes("UTF-8");
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			dp.setSocketAddress(new InetSocketAddress(castip, m_port));
			client.send(dp);

			System.out.println("send data:" + sdata);
			client.setSoTimeout(2000);
			while (true) {
				DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
				client.receive(p);
				aldata.add(p);

				System.out.println("Receive:" + new String(p.getData(), "UTF-8"));
			}

		} catch (java.net.SocketTimeoutException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aldata;

	}

	public static void main(String[] args) throws Exception {

		UDPCommandClient client = new UDPCommandClient();
		// client.SendCmd("reload,9090");
		client.SendCmdReloadDictionary();

	}
}
