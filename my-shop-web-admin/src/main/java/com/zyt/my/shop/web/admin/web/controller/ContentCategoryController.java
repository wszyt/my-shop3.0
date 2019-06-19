package com.zyt.my.shop.web.admin.web.controller;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.dto.PageInfo;
import com.zyt.my.shop.commons.persistence.BaseEntity;
import com.zyt.my.shop.domain.TbContent;
import com.zyt.my.shop.domain.TbContentCategory;
import com.zyt.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.zyt.my.shop.web.admin.service.TbContentCategoryService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */

@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

//
//    @Autowired
//    private TbContentCategoryService tbContentCategoryService;
//
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<> ();
        List<TbContentCategory> sourceList = service.selectAll ();

        //排序
        sortList (sourceList, targetList, 0L);

        model.addAttribute ("tbContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 树形结构
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if(id == null) {
            id = 0L;
        }

        return service.selectByPid (id);
    }

//
//    /**
//     * 排序
//     * @param sourceList 数据源集合
//     * @param targetList 排序后的集合
//     * @param parentId 父节点的ID
//     */
//    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
//        for (TbContentCategory tbContentCategory : sourceList) {
//            if (tbContentCategory.getParent ().getId ().equals (parentId)) {
//                targetList.add (tbContentCategory);
//
//                //判断有没有子节点，如果有则继续追加
//                if (tbContentCategory.getIsParent ()) {
//                    for (TbContentCategory contentCategory : sourceList) {
//                        if (contentCategory.getParent ().getId ().equals (tbContentCategory.getId ())) {
//                            sortList (sourceList, targetList, tbContentCategory.getId ());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }


    //保存
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save (tbContentCategory);

        if (baseResult.getStatus () == 200) {
            redirectAttributes.addFlashAttribute ("baseResult", baseResult);
            return "redirect:/content/category/list";
        }

        else {
            model.addAttribute ("baseResult", baseResult);
            return "content_category_form";
        }
    }

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        //id不为空，则从数据库获取
        if(id != null) {
             tbContentCategory = service.getById (id);
        }

        else {
            tbContentCategory = new TbContentCategory ();
        }
        return tbContentCategory;
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @RequestMapping(value="form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }

    /**
     * 删除信息
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;

        if (StringUtils.isNotBlank (ids)) {
            service.delete (Long.parseLong (ids));
            baseResult = BaseResult.success ("分类和子类及其内容已全部删除");

        }

        else {
            baseResult = BaseResult.fail ("删除内容失败");
        }

        return baseResult;
    }
}
