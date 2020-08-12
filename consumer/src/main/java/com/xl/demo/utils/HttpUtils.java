package com.xl.demo.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Random;
import java.util.concurrent.*;

public class HttpUtils {
    private static ExecutorService ex = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

    public static void main(String[] args) throws Exception{
        while (true){
            Random random = new Random();
            int id = random.nextInt(100);
            System.out.println(id);
            Thread.sleep(id * 100);
            ex.execute(()->{
                run(id);
            });
        }
    }

    public static void run(int id){
        try {
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建http POST请求
            HttpPost httpPost = new HttpPost("http://localhost:8081/order/"+id);
            //伪装浏览器请求
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    //内容写入文件
                    System.out.println("内容长度："+content.length());
                }
            } finally {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
