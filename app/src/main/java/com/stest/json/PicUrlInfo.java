package com.stest.json;

/**
 * Created by Limuyang on 2016/8/22.
 */
public class PicUrlInfo {

    /**
     * id : 3800
     * name : 环球音乐地图
     * desc : 披头士的伦敦足迹
     * picUrl : http://pic.xiami.net/images/trade/ams_homepage/60/57b92bc9b9e4d_3015029_1471753161.jpg
     * action : {"type":1,"value":"http://alimusic.xiami.com/markets/xiami/globalmusicmapvol5_planet"}
     */

    private int id;
    private String name;
    private String desc;
    private String picUrl;
    /**
     * type : 1
     * value : http://alimusic.xiami.com/markets/xiami/globalmusicmapvol5_planet
     */

    private ActionBean action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public static class ActionBean {
        private int type;
        private String value;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
