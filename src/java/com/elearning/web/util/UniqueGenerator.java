package com.elearning.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * TODO What the class does
 * 
 * @author 邢立庭
 * @date 2012-8-7-下午02:59:52
 * @description 唯一键值
 * 
 */
public class UniqueGenerator {
	private static SimpleDateFormat sdfOrder = new SimpleDateFormat("yyyyMMddHHmm");

    private static SimpleDateFormat sdfInvoice = new SimpleDateFormat("yyyyMMddHHmm");

    private static int lastOrderCode = 10101;

    private static int lastInvoinceCode = 12121;

    private static int last16;

    synchronized static public String getUnique() {
        long id = 0;
        long dt = System.currentTimeMillis();
        id = dt + last16;
        if (last16 > 9999) {
            last16 = 0;
        }
        last16++;
        return id + "";
    }

    /**
     * 获取随机数
     * 
     * @param len
     * @return
     */
    synchronized static public String getUnique(int len) {

        return StringUtils.right(StringUtils.rightPad(getUnique(), len, '0'), len);
    }

    /**
     * 生成长度一定的随机密码
     * 
     * @param len
     * @return
     */
    synchronized static public String getRandomPasswd(int len) {
        final char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        int size = chars.length;
        Random rand = new Random();

        StringBuffer passwd = new StringBuffer();
        for (int i = 0; i < len; i++) {
            passwd.append(chars[rand.nextInt(size)]);
        }

        return passwd.toString();
    }

    /**
     * 生成订单号
     * 
     * @return
     */
    synchronized static public Long getOrderCode() {
        if (lastOrderCode >= 99999) {
            lastOrderCode = 10101;
        }

        String orderCode = sdfOrder.format(new Date()) + (lastOrderCode + 1);

        lastOrderCode++;

        return Long.parseLong(orderCode);
    }

    /**
     * 生成发票号
     * 
     * @return
     */
    synchronized static public Long getInvoiceCode() {
        if (lastInvoinceCode >= 99999) {
            lastInvoinceCode = 10101;
        }

        String invoiceId = sdfInvoice.format(new Date()) + (lastInvoinceCode + 1);

        lastInvoinceCode++;

        return Long.parseLong(invoiceId);
    }
}
