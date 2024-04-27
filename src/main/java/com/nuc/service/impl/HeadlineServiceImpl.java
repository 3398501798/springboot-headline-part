package com.nuc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuc.pojo.Headline;
import com.nuc.pojo.vo.PortalVO;
import com.nuc.service.HeadlineService;
import com.nuc.mapper.HeadlineMapper;
import com.nuc.utils.JwtHelper;
import com.nuc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author wj183
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-04-08 16:52:37
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 首页数据查询
     * 1.进行分页数据查询
     * 2.分页数据，拼接到result即可
     *
     * 查询需要自定义语句，自定义mapper方法，携带分页
     * 返回结果List<Map>
     * @param portalVO
     * @return
     */
    @Override
    public Result findNewsPage(PortalVO portalVO) {
        IPage<Map> page=new Page<>(portalVO.getPageNum(),portalVO.getPageSize());
        headlineMapper.selectMyPage(page,portalVO);
        List<Map> records = page.getRecords();

        Map data=new HashMap();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());

        Map pageInfo=new HashMap<>();
        pageInfo.put("pageInfo",data);

        return Result.ok(pageInfo);
    }

    /**
     * 根据id查询头条详情
     *
     * 2.查询对应的数据即可
     * 1.修改阅读量+1
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {

        //1.实现根据id的查询(多表
        Map headLineDetail = headlineMapper.queryDetailMap(hid);
        //2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("headline",headLineDetail);
        return Result.ok(pageInfoMap);
    }

    /**
     * 发布头条的方法
     * <p>
     * 1.补全数据
     *
     * @param token
     * @param headline
     * @return
     */
    @Override
    public Result publish(String token, Headline headline) {

        //根据token查询用户id
        int userId = jwtHelper.getUserId(token).intValue();
        //数据装配
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    /**
     * 修改头条数据
     *
     *1.hid查询数据的最新version
     * 2.修改数据的修改时间为当前节点
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);//乐观锁
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }
}




