package com.nuc.controller;

import com.nuc.pojo.vo.PortalVO;
import com.nuc.service.HeadlineService;
import com.nuc.service.TypeService;
import com.nuc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class ProtalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
      Result result=typeService.findAllTypes();
      return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVO portalVO){
        Result result=headlineService.findNewsPage(portalVO);
        return result;
    }


    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }

}
