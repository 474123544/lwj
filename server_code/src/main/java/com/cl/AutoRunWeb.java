package com.cl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import java.io.IOException;

@Configuration
public class AutoRunWeb {
    // 这里是注入你yml配置的端口号
    @Value("${server.port}")
    private String appPort;

    /**
     * 监听事件（当项目启动后），启动浏览器
     */
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        // 需要启动的url（appPort是端口号， "/springbootil5n0/admin/dist/idnex.html"是项目的具体页面）
        String url1 = "http://localhost:" + appPort + "/"+"cl515882190/client/index.html";
        String url2 = "http://localhost:" + appPort + "/"+"cl515882190/manage/index.html#/login";
        Runtime runtime = Runtime.getRuntime();
        try {
            // rundll32 url.dll,FileProtocolHandler是Windows系统下用来打开默认浏览器并访问指定URL的命令
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url1);
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}