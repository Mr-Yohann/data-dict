package com.yohann.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yohann.dict.common.exception.DictException;
import com.yohann.dict.common.result.ResultType;
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
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(DataDict dict) {
        if (dict.getId() == null) {
            saveDict(dict);
        }else {
            updateDict(dict);
        }
    }

    /**
     * 添加字典
     * @param dict 字典信息
     */
    private void saveDict(DataDict dict) {
        QueryWrapper<DataDict> queryWrapper = getDictCheckQueryWrapper(dict);

        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new DictException(ResultType.ERROR.getCode(), "The dict already exists!");
        }

        save(dict);
    }

    /**
    * 更新字典
    *
    * @param dict 字典信息
    */
    private void updateDict(DataDict dict) {
        QueryWrapper<DataDict> queryWrapper = getDictCheckQueryWrapper(dict);

        queryWrapper.lambda().ne(DataDict::getId, dict.getId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new DictException(ResultType.ERROR.getCode(), "The dict already exists!");
        }

        updateById(dict);
    }

    /**
    * 获取校验重复筛选条件
    *
    * @param dict 字典信息
    * @return 查询条件
    */
    private QueryWrapper<DataDict> getDictCheckQueryWrapper(DataDict dict) {
        QueryWrapper<DataDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DataDict::getParentId, dict.getParentId());

        // 校验是否重复
        if (dict.getParentId().equals(0L)) {
            queryWrapper.lambda().eq(DataDict::getCode, dict.getCode());
        } else {
            queryWrapper.lambda().eq(DataDict::getDictKey, dict.getDictKey());
        }

        return queryWrapper;
    }

    /**
     * 删除字典
     *
     * @param id 字典id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    /**
     * 禁用字典
     *
     * @param id 字典id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Long id) {
        DataDict dataDict = new DataDict();
        dataDict.setId(id).setStatus(false);

        baseMapper.updateById(dataDict);
    }
}
