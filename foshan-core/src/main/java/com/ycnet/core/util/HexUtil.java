package com.ycnet.core.util;  

/**
 * 十六进制转换工具类
* @desc: foshan-core  
* @author: DZY  
* @createTime: 2018年5月28日 上午11:53:06  
* @history:  
* @version: v1.0
 */
public class HexUtil {
	/**
     * 字节流转成十六进制表示
     */
    public static String encode(String str) {
    	byte[] src = str.getBytes();
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < src.length; n++) {
            strHex = Integer.toHexString(src[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }
    
    /**
     * 字符串转成字节流
     */
    public static String decode(String src) {
        int m = 0, n = 0;
        int byteLen = src.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return new String(ret);
    }
    
    public static void main(String[] args) {
    	String str = "02000001";
        System.out.println("test string : "+str);

        String hexEncode = HexUtil.encode(str);
        System.out.println("HexUtil.encode Result : "+hexEncode);

        String bytes = HexUtil.decode(hexEncode);
        System.out.println("HexUtil.decode Result : "+new String(bytes));
	}
}
  
    