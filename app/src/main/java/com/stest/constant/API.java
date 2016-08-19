package com.stest.constant;

/**
 * Created by Limuyang on 2016/8/19.
 * API常量统一管理
 */

/**
 * # 歌曲榜单地址
 * top_list_all = {
 * 0: ['云音乐新歌榜', '/discover/toplist?id=3779629'],
 * 1: ['云音乐热歌榜', '/discover/toplist?id=3778678'],
 * 2: ['网易原创歌曲榜', '/discover/toplist?id=2884035'],
 * 3: ['云音乐飙升榜', '/discover/toplist?id=19723756'],
 * 4: ['云音乐电音榜', '/discover/toplist?id=10520166'],
 * 5: ['UK排行榜周榜', '/discover/toplist?id=180106'],
 * 6: ['美国Billboard周榜', '/discover/toplist?id=60198'],
 * 7: ['KTV嗨榜', '/discover/toplist?id=21845217'],
 * 8: ['iTunes榜', '/discover/toplist?id=11641012'],
 * 9: ['Hit FM Top榜', '/discover/toplist?id=120001'],
 * 10: ['日本Oricon周榜', '/discover/toplist?id=60131'],
 * 11: ['韩国Melon排行榜周榜', '/discover/toplist?id=3733003'],
 * 12: ['韩国Mnet排行榜周榜', '/discover/toplist?id=60255'],
 * 13: ['韩国Melon原声周榜', '/discover/toplist?id=46772709'],
 * 14: ['中国TOP排行榜(港台榜)', '/discover/toplist?id=112504'],
 * 15: ['中国TOP排行榜(内地榜)', '/discover/toplist?id=64016'],
 * 16: ['香港电台中文歌曲龙虎榜', '/discover/toplist?id=10169002'],
 * 17: ['华语金曲榜', '/discover/toplist?id=4395559'],
 * 18: ['中国嘻哈榜', '/discover/toplist?id=1899724'],
 * 19: ['法国 NRJ EuroHot 30周榜', '/discover/toplist?id=27135204'],
 * 20: ['台湾Hito排行榜', '/discover/toplist?id=112463'],
 * 21: ['Beatport全球电子舞曲榜', '/discover/toplist?id=3812895']
 * }
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
}


