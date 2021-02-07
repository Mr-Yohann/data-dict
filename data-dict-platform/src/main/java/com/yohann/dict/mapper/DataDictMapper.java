package com.yohann.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yohann.dict.entity.DataDict;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Yohann
 * @since 2021-02-06
 */
public interface DataDictMapper extends BaseMapper<DataDict> {

    List<DataDict> getDictMapCode();

    List<DataDict> getDictListCode();

    List<DataDict> getDictMap(Long id);

    List<String> getDictList(Long id);
}
