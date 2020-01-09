package com.joe.oauth.util;

import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2019-12-31 15:44
 **/
public class HttpUitl {

    public static String sendHttpRequest(String httpUrl, String method, Map<String,String> params) throws Exception{
        //建立URL链接对象
        URL url = new URL(httpUrl);
        //创立链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求的方式(需要大写)
        conn.setRequestMethod(method);
        //设置需要输出
        conn.setDoOutput(true);
        //判断是否有参数
        if (params!=null&&params.size()>0){
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String,String> entry:params.entrySet()){
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            //sb.substring(1)去掉最前面的&
            conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
        }
        //发送请求到服务器
        conn.connect();
        //接收对方响应的信息
        String responseStr = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("UTF-8"));
        //注销链接
        conn.disconnect();
        return responseStr;
    }

}
