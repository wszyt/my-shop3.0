package com.zyt.my.shop.web.ui.api;

import com.zyt.my.shop.commons.utils.HttpClientUtils;
import com.zyt.my.shop.commons.utils.MapperUtils;
import com.zyt.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {
//    public static List<TbContent> findContentsByCategoryId(String id) {
//        String result = HttpClientUtils.doGet (API.API_CONTENTS + id);
//
//        try {
//            List<TbContent> tbContents = MapperUtils.json2list (result, "data", TbContent.class);
//        } catch (Exception e) {
//            e.printStackTrace ();
//        }
//
//        return tbContents;
//    }

    public static List<TbContent> ppt(){
        String result = HttpClientUtils.doGet (API.API_CONTENTS);

        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2list (result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return tbContents;
    }

    public static List<TbContent> mppt() {
        String result = HttpClientUtils.doGet (API.API_CONTENTS_MPPT);

        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2list (result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return  tbContents;
    }
}
