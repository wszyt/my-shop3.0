package com.zyt.my.shop.web.admin.web.controller;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.dto.PageInfo;
import com.zyt.my.shop.domain.TbContent;
import com.zyt.my.shop.domain.TbContentCategory;
import com.zyt.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zyt.my.shop.web.admin.service.TbContentCategoryService;
import com.zyt.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent, TbContentService> {

//    @Autowired
//    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent;

        //id不为空，则从数据库获取
        if(id != null) {
            tbContent = service.getById (id);
        }

        else {
            tbContent = new TbContent ();
        }

        return tbContent;
    }


    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value="list", method = RequestMethod.GET)
    public String list () {
        return "content_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value="form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }



    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save (tbContent);

        if(baseResult.getStatus () == 200) {
            redirectAttributes.addFlashAttribute ("baseResult", baseResult);
            return "redirect:/content/list";
        }

        else {
            model.addAttribute ("baseResult", baseResult);
            return "content_form";
        }
    }

    /**
     * 删除信息
     * @param ids
     * @param model
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids, Model model) {
        BaseResult baseResult = null;

        if (StringUtils.isNotBlank (ids)) {
            String [] idArray = ids.split (",");
            service.deleteMulti (idArray);
            baseResult = BaseResult.success ("删除内容成功");

        }

        else {
            baseResult = BaseResult.fail ("删除内容失败");
        }

        model.addAttribute ("deleteMulti", baseResult);

        return baseResult;
    }


//    /**
//     * 分页查询
//     * @param request
//     * @return
//     */
//    @Override
//    @ResponseBody
//    @RequestMapping(value = "page", method = RequestMethod.GET)
//    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent) {
//        String strDraw = request.getParameter ("draw");
//        String strStart = request.getParameter ("start");
//        String strLength = request.getParameter ("length");
//
//        int draw = strDraw == null ?  0 : Integer.parseInt (strDraw);
//        int start = strStart == null ? 0: Integer.parseInt (strStart);
//        int length = strLength == null ? 10 : Integer.parseInt (strLength);
//
//        //封装DataTables需要的结果
//        PageInfo<TbContent> pageInfo = service.page (start, length, draw, tbContent);
//
//        List<TbContent> tbContents = pageInfo.getData ();
//
//        return pageInfo;
//    }

    /**
     * 显示详情
     * @param
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "content_detail";
    }

}
