package com.yohann.dict.controller;

import com.yohann.dict.common.result.Result;
import com.yohann.dict.entity.DataDict;
import com.yohann.dict.service.DataDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Yohann
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "Dict Service API")
public class DataDictController {
    private final DataDictService dictService;

    public DataDictController(DataDictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping("getAllListDict")
    @ApiOperation("获取所有List字典")
    public Map<String, List<String>> getAllListDict() {
        return dictService.getAllListDict();
    }

    @GetMapping("getAllMapDict")
    @ApiOperation("获取所有Map字典")
    public Map<String, Map<String, String>> getAllMapDict() {
        return dictService.getAllMapDict();
    }

    @PostMapping("addOrUpdate")
    @ApiOperation("添加或修改字典")
    public Result addOrUpdate(@RequestBody DataDict dict) {
        dictService.addOrUpdate(dict);
        return Result.success();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("删除字典")
    public Result delete(@PathVariable @ApiParam("字典id") Long id) {
        dictService.delete(id);
        return Result.success();
    }

    @GetMapping("disable/{id}")
    @ApiOperation("禁用字典")
    public Result disable(@PathVariable @ApiParam("字典id") Long id) {
        dictService.disable(id);
        return Result.success();
    }
}

