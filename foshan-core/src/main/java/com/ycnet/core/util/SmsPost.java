package com.ycnet.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;




@Service
public class SmsPost {

    private SmsPost() {

    }

    /**
     * 短信发送
     * 
     * @param sms
     *            接口帮助类
     * @return
     * @throws IOException
     */
    public static boolean sendPost(Sms sms) throws IOException {
        // 调用zyer.cn的短信接口
        if (sms.getHttpUrl().indexOf("zyer") != -1) {
            return zyerSend(sms);
        } else {
            // 使用美圣
            return meriSSend(sms);
        }
    }

    /**
     * zyer send
     * 
     * @param sms
     * @return
     * @throws IOException
     */
    private static boolean meriSSend(Sms sms) throws IOException {
        /**
         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using java.net.URL and //java.net.URLConnection http://sdk.zyer.cn/SmsService/SmsService.asmx/SendEx
         */
        URL url;
        url = new URL(sms.getHttpUrl());
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),  "utf-8");
        String param = "";
        param += "username=" 	+ sms.getLoginName();
        param += "&scode=" 		+ sms.getPassword();
        param += "&content="	+ sms.getMsgContext();
        param += "&msgid=" 		+ sms.getExpSmsId();
        param += "&mobile=" 	+ sms.getSendSim();
        // post的关键所在！
        out.write(param);
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type: application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        StringBuilder sTotalString = new StringBuilder();
        sCurrentLine = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        // 传说中的三层包装阿！
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString.append(sCurrentLine);
            // sTotalString.append("\r\n");
        }

        if (Pattern.compile("^ 0#[0-9]*#[0-9]*$").matcher(sTotalString.toString()).find()) {
            return true;
        }
        return false;
    }

    /**
     * meisheng send
     * 
     * @param sms
     * @return
     * @throws IOException
     */
    public static boolean zyerSend(Sms sms) throws IOException {
        /**
         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using java.net.URL and //java.net.URLConnection http://sdk.zyer.cn/SmsService/SmsService.asmx/SendEx
         */
        URL url = new URL(sms.getHttpUrl());
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GB2312");
        String param = "";
        param += "LoginName=" + sms.getLoginName();
        param += "&Password=" + sms.getPassword();
        param += "&SendSim=" + sms.getSendSim();
        param += "&SmsKind=" + sms.getSmsKind();
        param += "&ExpSmsId=";
        param += "&MsgContext=" + "您好," + "name"+ "提醒您,您本次的验证码是" + sms.getMsgContext() + "。【宁派科技】";
        // post的关键所在！
        out.write(param);
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type: application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        StringBuilder sTotalString = new StringBuilder();
        sCurrentLine = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        // 传说中的三层包装阿！
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString.append(sCurrentLine);
            sTotalString.append("\r\n");
        }
        String[] s = sTotalString.toString().split("<Code>0</Code>");
        if (s.length > 1) {
            return true;
        }
        return false;
    }

    /**
     * @param sms
     * @return
     */
    public static boolean sendPostAA(Sms sms) throws IOException {

        String param = sms.getHttpUrl();
        param += "?name=" + sms.getLoginName();
        param += "&pwd=" + sms.getPassword();
        param += "&content=" + "这是" + "hudadw " + "给您发送的校验码：" + sms.getMsgContext() + "，打死不能告诉别人哦！【宁派科技】";
        param += "&pid=" + 52;
        param += "&dest=" + sms.getSendSim();
        PostMethod getMethod = new PostMethod(param);
        HttpClient client = new HttpClient();
        client.executeMethod(getMethod);
        return false;
    }
    
    
	public boolean sendPost(String receiveMobile,String msgContent) {
		
	    Sms sms = new Sms();
        if (sms == null) {
            return false;
        }
        
        sms.setSendSim(receiveMobile);	        
        sms.setMsgContext(msgContent);
        
        try {
            if (SmsPost.sendPost(sms)) {
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
	}

}
