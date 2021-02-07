package com.yohann.dict.service.impl;

import com.yohann.dict.entity.DataDict;
import com.yohann.dict.mapper.DataDictMapper;
import com.yohann.dict.service.DataDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Yohann
 * @since 2021-02-06
 */
@Service
public class DataDictServiceImpl extends ServiceImpl<DataDictMapper, DataDict> implements DataDictService {
    /**
     * 获取所有Map字典
     *
     * @return DictHolder
     */
    @Override
    public Map<String, Map<String, String>> getAllMapDict() {
        Map<String, Map<String, String>> map = new HashMap<>();
        //查询Map一级字典
        List<DataDict> mapCodeList = baseMapper.getDictMapCode();

        //查询map叶子节点
        for (DataDict dict : mapCodeList) {
            map.put(dict.getDictKey(), baseMapper.getDictMap(dict.getId()).stream().collect(Collectors.toMap(DataDict::getDictKey, DataDict::getDictValue)));
        }

        return map;
    }

    /**
     * 获取所有List字典
     *
     * @return DictHolder
     */
    @Override
    public Map<String, List<String>> getAllListDict() {
        Map<String, List<String>> map = new HashMap<>();
        //查询List一级字典
        List<DataDict> listCodeList = baseMapper.getDictListCode();

        //查询list叶子节点
        for (DataDict dict : listCodeList) {
            map.put(dict.getDictKey(), baseMapper.getDictList(dict.getId()));
        }

        return map;
    }

    /**
     * 新增或修改字典
     *
     * @param dict 字典信息
     */
    @Override
    @Transactional
    public void addOrUpdate(DataDict dict) {
        saveOrUpdate(dict);
    }

    /**
     * 删除字典
     *
     * @param id 字典id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    /**
     * 禁用字典
     *
     * @param id 字典id
     */
    @Override
    @Transactional
    public void disable(Long id) {
        DataDict dataDict = new DataDict();
        dataDict.setId(id).setStatus(false);

        baseMapper.updateById(dataDict);
    }
}
