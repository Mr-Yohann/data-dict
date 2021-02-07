package com.yohann.consumer.contorller;

import com.yohann.common.utils.DictHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yohann
 * @since 2021/2/7 19:10
 */
@RestController
public class TestController {
    @RequestMapping("/test/{code}")
    public Object test(@PathVariable String code) {
        return DictHolder.getInstance().getListMap(code);
    }
}