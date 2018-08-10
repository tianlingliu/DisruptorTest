package com.thinkbit.disruptor.translator;

import com.lmax.disruptor.EventTranslator;
import com.thinkbit.disruptor.event.MyInParkingDataEvent;

public class MyInParkingDataEventTranslator implements EventTranslator<MyInParkingDataEvent> {

    @Override
    public void translateTo(MyInParkingDataEvent myInParkingDataEvent, long sequence) {
        this.generateData(myInParkingDataEvent);
    }

    private MyInParkingDataEvent generateData(MyInParkingDataEvent myInParkingDataEvent) {
        // 随机生成一个车牌号
        myInParkingDataEvent.setCarLicense("车牌号： 京A-" + (int) (Math.random() * 100000));
        System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
        return myInParkingDataEvent;
    }

}