package com.yohann.common.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * <p>
 * 字典定期任务
 * </p>
 *
 * @author Yohann
 * @since 2021/2/7 17:49
 */
@Configuration
@EnableScheduling
public class DictSyncSchedule {
    @Resource
    private DictSyncRunner runner;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncDict() {
        runner.syncDict();
    }
}