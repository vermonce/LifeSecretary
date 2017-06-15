package com.guoxiaotian.lifesecretary.model.utils;

import com.guoxiaotian.lifesecretary.Constants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 *
 *     聚合官网上联网请求的代码demo，取一部分，变成联网的utils类
 */
public class NetUtils {

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public NetUtils() {
    }

    // 星座运势的联网请求帮助类
    public String getXingZuoYunShi(String cosName,String type) {
        String result = null;
        String url = "http://api.avatardata.cn/Constellation/Query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", Constants.XINGZUOYUNSHI_APPKEY);//您申请的key
        params.put("consName", cosName);//
        params.put("type", type);//

        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //1.最新笑话
    public String getNewLaughText(){
        String result =null;
        String url ="http://japi.juhe.cn/joke/content/text.from";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("page","1");//当前页数,默认1
        params.put("pagesize","20");//每次返回条数,默认1,最大20
        params.put("key", Constants.LAUGH_APPKEY);//您申请的key

        try {
            result =net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
     /*   try {
            result =net(url, params, "GET");
           // JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();*/
        }

    //2.历史上今天的事件列表
    public String todayList(int month,int day) {
        String result = null;
        String url = "http://api.juheapi.com/japi/toh";//请求接口地址
        Map params = new HashMap();//请求参数

        params.put("v", "1.0");//版本
        params.put("month", month);//查的月
        params.put("day", day);//查的日期
        params.put("key", Constants.TODAY_IN_HISTORY_APPKEY);//您申请的key


        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
       /* try {
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    //2.历史上今天的事件列表
    public String todayList2(String date) {
        String result = null;
        String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php";//请求接口地址
        Map params = new HashMap();//请求参数

    /*    params.put("v", "1.0");//版本
        params.put("month", month);//查的月
        params.put("day", day);//查的日期*/
        params.put("key", Constants.TODAY_IN_HISTORY_APPKEY);//您申请的key
        params.put("date",date);

        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //3.百家姓列表
    public String getBaiJiaXing(String name) {
        String result = null;
        String url = "http://api.avatardata.cn/XingShiQiYuan/LookUp";//请求接口地址
        Map params = new HashMap();//请求参数

        params.put("key", Constants.XINGSHIQIYUAN_APPKEY);//您申请的key
        params.put("xingshi", name);//查的日期


        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //4、周公解梦
    public String getJieMeng(String keyword) {
        String result = null;
        String url = "http://api.avatardata.cn/ZhouGongJieMeng/LookUp";//请求接口地址
        Map params = new HashMap();//请求参数

        params.put("key", Constants.ZHOUGONGJIEMENG_APPKEY);//您申请的key
        params.put("keyword", keyword);//


        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //5、上市公司咨询
    public String getCompanyInformation(String date) {
        String result = null;
        String url = "http://api.avatardata.cn/CompanyInfo/LookUp";//请求接口地址
        Map params = new HashMap();//请求参数

        params.put("key", Constants.COMPANY_APPKEY);//您申请的key
        params.put("date", date);//


        try {
            result = net(url, params, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public  String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    }


