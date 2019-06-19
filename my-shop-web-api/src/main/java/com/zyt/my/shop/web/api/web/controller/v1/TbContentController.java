package com.zyt.my.shop.web.api.web.controller.v1;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.domain.TbContent;
import com.zyt.my.shop.web.api.service.TbContentService;
import com.zyt.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        if (id == null) {
            tbContent = new TbContent ();
        }

        return tbContent;
    }

    /**
     * 根据类别ID查询内容列表
     * @return
     */
    @RequestMapping(value = "ppt", method = RequestMethod.GET)
    public BaseResult findPPT() {
        return findContents (89L);
    }

    @RequestMapping(value = "mppt", method = RequestMethod.GET)
    public BaseResult findMPPT() {
        return  findContents (97L);
    }

    private BaseResult findContents(Long id) {
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId (id);

        if (tbContents != null && tbContents.size () > 0) {
            tbContentDTOS = new ArrayList<> ();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO ();
                BeanUtils.copyProperties (tbContent, dto);
                tbContentDTOS.add (dto);
            }
        }
        return BaseResult.success ("成功", tbContentDTOS);
    }
}
