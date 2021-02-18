package com.yohann.common.utils;

import com.yohann.common.feign.DataDictFeign;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 启动任务
 * </p>
 *
 * @author Yohann
 * @since 2021/2/7 19:06
 */
@Component
public class DictSyncRunner implements CommandLineRunner {
    @Resource
    private DataDictFeign dataDictFeign;

    void syncDict() {
        System.out.println("<\t\t\t====== 正在同步字典 ======>");

        try {
            DictHolder.getInstance().setListMap(dataDictFeign.getAllListDict());
            DictHolder.getInstance().setDictMap(dataDictFeign.getAllMapDict());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\t\t\t<====== 同步出错 ======>");
        }

        System.out.println("\t\t\t<====== 同步完成 ======>");
    }

    @Override
    public void run(String... args) {
        syncDict();
    }
}