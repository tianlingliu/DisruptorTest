package com.thinkbit.disruptor.processer;

import com.lmax.disruptor.dsl.Disruptor;
import com.thinkbit.disruptor.event.MyInParkingDataEvent;
import com.thinkbit.disruptor.translator.MyInParkingDataEventTranslator;

import java.util.concurrent.CountDownLatch;

/**
 * 生产者，进入停车场的车辆
 */
public class MyInParkingDataEventPublisher implements Runnable {

    // 用于监听初始化操作，等初始化执行完毕后，通知主线程继续工作
    private CountDownLatch countDownLatch;

    private Disruptor<MyInParkingDataEvent> disruptor;

    // 1,10,100,1000
    private static final Integer NUM = 10;

    public MyInParkingDataEventPublisher(CountDownLatch countDownLatch,
                                         Disruptor<MyInParkingDataEvent> disruptor) {
        this.countDownLatch = countDownLatch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < NUM; i++) {
                MyInParkingDataEventTranslator eventTranslator = new MyInParkingDataEventTranslator();
                disruptor.publishEvent(eventTranslator);
                // 假设一秒钟进一辆车
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 执行完毕后通知 await()方法
            countDownLatch.countDown();
            System.out.println(NUM + "辆车已经全部进入进入停车场！");
        }
    }

}


