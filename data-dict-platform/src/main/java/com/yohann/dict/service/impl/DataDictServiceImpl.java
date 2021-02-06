package com.yohann.dict.service.impl;

import com.yohann.common.dto.DictDTO;
import com.yohann.common.holder.DictHolder;
import com.yohann.dict.entity.DataDict;
import com.yohann.dict.mapper.DataDictMapper;
import com.yohann.dict.service.DataDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
     * 获取所有字典
     *
     * @return DictHolder
     */
    @Override
    public DictHolder getAllDict() {
        DictHolder dictHolder = new DictHolder(new HashMap<>(), new HashMap<>());

        //查询Map一级字典
        List<DictDTO> mapCodeList = baseMapper.getDictMapCode();
        //查询List一级字典
        List<DictDTO> listCodeList = baseMapper.getDictListCode();

        //查询map叶子节点
        for (DictDTO dict : mapCodeList) {
            dictHolder.getDictMap().put(dict.getDictKey(), baseMapper.getDictMap(dict.getId()).stream().collect(Collectors.toMap(DictDTO::getDictKey, DictDTO::getDictValue)));
        }
        //查询list叶子节点
        for (DictDTO dict : listCodeList) {
            dictHolder.getListMap().put(dict.getDictKey(), baseMapper.getDictList(dict.getId()));
        }

        return dictHolder;
    }

    /**
     * 新增或修改字典
     *
     * @param dict 字典信息
     */
    @Override
    public void addOrUpdate(DataDict dict) {
        saveOrUpdate(dict);
    }

    /**
     * 删除字典
     *
     * @param id 字典id
     */
    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    /**
     * 禁用字典
     *
     * @param id 字典id
     */
    @Override
    public void disable(Long id) {
        DataDict dataDict = new DataDict();
        dataDict.setId(id).setStatus(false);

        baseMapper.updateById(dataDict);
    }
}
