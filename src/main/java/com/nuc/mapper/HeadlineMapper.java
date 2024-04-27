package com.nuc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nuc.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuc.pojo.vo.PortalVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author wj183
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-04-08 16:52:37
* @Entity com.nuc.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {


   IPage<Map> selectMyPage(IPage page,@Param("portalVo") PortalVO portalVO);

    Map queryDetailMap(Integer hid);

}




