package com.nuc.service;

import com.nuc.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuc.pojo.vo.PortalVO;
import com.nuc.utils.Result;

/**
* @author wj183
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-04-08 16:52:37
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVO
     * @return
     */
    Result findNewsPage(PortalVO portalVO);

    /**
     * 根据id查询头条详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条的方法
     *
     * @param token
     * @param headline
     * @return
     */
    Result publish(String token, Headline headline);

    /**
     * 修改头条数据
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}
