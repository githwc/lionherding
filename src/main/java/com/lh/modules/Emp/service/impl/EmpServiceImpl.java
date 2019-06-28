package com.lh.modules.Emp.service.impl;

import com.lh.modules.Emp.entity.Emp;
import com.lh.modules.Emp.mapper.EmpMapper;
import com.lh.modules.Emp.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 牧狮&&紫色年华
 * @date 2019-06-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

}
