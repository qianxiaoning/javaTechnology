package com.qxn.longConnection.webSocket2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

/**
 * 版权：(C) 版权所有 2000-2021 上海天好电子商务股份有限公司苏州分公司
 * <简述>
 * <详细描述> A
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
@Component
public class Test implements Runnable{

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
