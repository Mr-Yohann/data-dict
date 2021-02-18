package com.yohann.consumer.service.impl;

import com.yohann.consumer.entity.Student;
import com.yohann.consumer.mapper.StudentMapper;
import com.yohann.consumer.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yohann
 * @since 2021-02-18
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
