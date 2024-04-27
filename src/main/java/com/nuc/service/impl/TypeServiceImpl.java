package com.nuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuc.pojo.Type;
import com.nuc.service.TypeService;
import com.nuc.mapper.TypeMapper;
import com.nuc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wj183
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-04-08 16:52:37
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    /**
     * 查询所有类别数据
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}




