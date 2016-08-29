package com.stest.constant;

/**
 * Created by Limuyang on 2016/8/19.
 * API常量统一管理
 */

public class API {


    /**
     * Banner API:天天动听(阿里星球)
     */
    public static final String BANNER = "http://api.dongting.com/frontpage/frontpage";
    /**
     * 网易云音乐(歌单)最后的ID即在网易云音乐界面网址末尾ID
     */
    public static final String SONGLIST = "http://music.163.com/api/playlist/detail?id=15985919";
    /**
     * {0}=需要搜索的歌曲或歌手
     * {1}=查询的页码数
     * {2}=当前页的返回数量
     * http://so.ard.iyyin.com/s/song_with_out?q={0}&page={1}&size={2}
     * http://so.ard.iyyin.com/s/song_with_out?q=五月天&page=1&size=3
     */
    public static final String SEARCH_MUSIC = "http://so.ard.iyyin.com/s/song_with_out?q=";
    /**
     * 根地址为 http://api.lostg.com
     * 网易歌词 /music/163/lyrics/{id}
     * 网易单曲 /music/163/songs/{id}
     * 网易专辑 /music/163/albums/{id}
     * 网易歌手 /music/163/artists/{id}
     * 网易歌单 /music/163/collections/{id}
     * 虾米专辑歌曲 ID /music/xiami/albums/ids/{id}
     * 虾米歌手歌曲 ID /music/xiami/artists/ids/{id}
     * 虾米精选集歌曲 ID /music/xiami/collections/ids/{id}
     * 虾米歌词 /music/xiami/lyrics/{id}
     * 虾米单曲 /music/xiami/songs/{id}
     */
}


