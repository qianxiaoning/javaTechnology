package com.qxn.longConnection.webSocket2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Component
public class Test implements Runnable{

    // 项目启动后，不断向id为1的WebSocket发送
    @PostConstruct
    public static void main() {
        Thread thread = new Thread(new Test());
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                LocalTime localTime = LocalTime.now().withNano(0);
                System.out.println(localTime);
                WebSocketServerTest.sendInfo(localTime.toString(), "1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
