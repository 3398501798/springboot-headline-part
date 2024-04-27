package com.nuc.controller;

import com.nuc.pojo.Headline;
import com.nuc.service.HeadlineService;
import com.nuc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token ,@RequestBody Headline headline){
        Result result= headlineService.publish(token,headline);
        return result;
    }


    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Headline headline = headlineService.getById(hid);
        Map data=new HashMap<>();
        data.put("headline",headline);
        return Result.ok(data);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result=headlineService.updateData(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }


}
