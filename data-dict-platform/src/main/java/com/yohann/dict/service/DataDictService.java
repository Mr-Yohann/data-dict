package com.yohann.dict.service;

import com.yohann.common.holder.DictHolder;
import com.yohann.dict.entity.DataDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author Yohann
 * @since 2021-02-06
 */
public interface DataDictService extends IService<DataDict> {
    /**
     * 获取所有字典
     */
    DictHolder getAllDict();

    /**
     * 添加或修改字典
     */
    void addOrUpdate(DataDict dict);

    /**
     * 删除字典
     */
    void delete(Long id);

    /**
     * 禁用字典
     */
    void disable(Long id);
}
