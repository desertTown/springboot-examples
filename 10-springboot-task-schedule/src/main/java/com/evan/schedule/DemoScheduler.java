package com.evan.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DemoScheduler {
    // 每天的22点到23点每隔15秒钟执行一次任务
    @Scheduled(cron = "0/15 * 22-23 * * ?")
    public void cronJobSchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        log.info("Java cron job expression scheduler::"+sdf.format(now));
    }

    //  每5秒执行一次
    @Scheduled(fixedRate=5000)
    public void fixedRateSchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        log.info("Java fixedRate scheduler::"+sdf.format(now));
    }

    // 在应用程序启动成功后延迟10秒钟，再以每5秒钟的频率执行任务：
    @Scheduled(fixedDelay = 5000,initialDelay = 10000)
    public void fixedRateAndInitialDelaySchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        log.info("Java fixedRate and initialDelay scheduler::"+sdf.format(now));
    }
}
