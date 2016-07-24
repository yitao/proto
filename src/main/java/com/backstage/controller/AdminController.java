package com.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by and on 2016/7/24.
 */
@Controller
@RequestMapping("/bstage")
public class AdminController extends BaseAdminController{

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/backstage/index";
    }


}
