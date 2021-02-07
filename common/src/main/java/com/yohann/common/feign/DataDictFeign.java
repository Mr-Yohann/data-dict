package com.yohann.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Yohann
 * @since 2021/2/6 17:47
 */
@Component
@FeignClient("data-dict-service")
public interface DataDictFeign {
    @GetMapping("/dict/getAllListDict")
    public Map<String, List<String>> getAllListDict();

    @GetMapping("/dict/getAllMapDict")
    public Map<String, Map<String, String>> getAllMapDict();
}