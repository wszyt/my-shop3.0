package com.zyt.my.shop.web.ui.controller;

import com.zyt.my.shop.web.ui.api.ContentsApi;
import com.zyt.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        //请求幻灯片
        requestContentsPPT (model);
        requestContentsMPPT (model);
        return "index";
    }


    /**
     * 请求幻灯片
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<TbContent> tbContents = ContentsApi.ppt();

        model.addAttribute ("ppt", tbContents);
    }

    private void requestContentsMPPT(Model model) {
        List<TbContent> tbContents = ContentsApi.mppt ();

        model.addAttribute ("mppt", tbContents);
    }

}
