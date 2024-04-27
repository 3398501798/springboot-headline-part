package com.nuc.service;

import com.nuc.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuc.utils.Result;

/**
* @author wj183
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-04-08 16:52:37
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有类别数据
     * @return
     */
    Result findAllTypes();

}
