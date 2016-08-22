package com.stest.constant;

/**
 * Created by Limuyang on 2016/8/19.
 * API常量统一管理
 */

public class API {

    /**
     * 网易音乐搜索API
     * http://s.music.163.com/search/get/
     * 获取方式：GET
     * 参数：
     * src: lofter //可为空
     * type: 1
     * filterDj: true|false //可为空
     * s: //关键词
     * limit: 10 //限制返回结果数
     * offset: 0 //偏移
     * callback: //为空时返回json，反之返回jsonp callback
     */
    public static final String SEARCH_MUSIC_PREFIX = "http://s.music.163.com/search/get/?";

    /**
     * Banner API:天天动听(阿里星球)
     */
    public static final String BANNER = "http://api.dongting.com/frontpage/frontpage";
}


