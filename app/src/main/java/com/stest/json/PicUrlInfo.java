package com.stest.json;

import java.util.List;

/**
 * Created by Limuyang on 2016/8/22.
 */
public class PicUrlInfo {


    /**
     * type : 0
     */

    private ActionBean action;
    /**
     * data : [{"albumRightKey":{"price":0,"dmsg":"","count":-1,"buy":false,"quality":null,"vipFree":null,"active":null,"buyFlag":false},"id":3804,"isExclusive":0,"picUrl":"http://pic.xiami.net/images/trade/ams_homepage/180/57bbb33156805_9009940_1471918897.jpg","desc":"丁当","status":1,"name":"时间告诉我的事","action":{"value":"2403882","type":19}},{"id":3803,"author":"阿里音乐","picUrl":"http://pic.xiami.net/images/trade/ams_homepage/169/57bb956c98ef6_8452522_1471911276.jpg","desc":"中国说唱女神都在这里 - 中国摇民系列之五","name":"中国说唱女神都在这里 - 中国摇民系列之五","action":{"value":"309531168","type":5},"listenCount":5,"comments":0},{"id":3802,"author":"阿里音乐","picUrl":"http://pic.xiami.net/images/trade/ams_homepage/3/57ba5ee79f078_193919_1471831783.jpg","desc":"阿里音乐","name":"这些狮子座歌手，很会唱歌","action":{"value":"309499047","type":5},"listenCount":39,"comments":0},{"id":3801,"author":"阿里音乐","picUrl":"http://pic.xiami.net/images/trade/ams_homepage/169/57ba5ec1242bb_8459088_1471831745.jpg","desc":"你曾和他和她 在雨天教室外的楼道上 分享同一首歌\n少年的你们因为 一句歌词写得好难过 所以觉得好美\n少年的你们，还没有受过爱情的伤\n\n今年这天，和这些曾经伤情歌不期而遇\n可会想起那个少年\n和那种曾经迷恋过的\u201c非君不可\u201d的恋爱的感觉","name":"情歌还是伤的好","action":{"value":"309516723","type":5},"listenCount":41,"comments":0},{"action":{"value":"http://alimusic.xiami.com/markets/xiami/globalmusicmapvol5_planet","type":1},"id":3800,"picUrl":"http://pic.xiami.net/images/trade/ams_homepage/60/57b92bc9b9e4d_3015029_1471753161.jpg","desc":"披头士的伦敦足迹","name":"环球音乐地图"}]
     * action : {"type":0}
     * id : 2881
     * desc : 海报
     * isNameDisplay : 0
     * style : 8
     * name : 海报（阿里星球）
     */

    private int id;
    private String desc;
    private int isNameDisplay;
    private int style;
    private String name;
    /**
     * albumRightKey : {"price":0,"dmsg":"","count":-1,"buy":false,"quality":null,"vipFree":null,"active":null,"buyFlag":false}
     * id : 3804
     * isExclusive : 0
     * picUrl : http://pic.xiami.net/images/trade/ams_homepage/180/57bbb33156805_9009940_1471918897.jpg
     * desc : 丁当
     * status : 1
     * name : 时间告诉我的事
     * action : {"value":"2403882","type":19}
     */

    private List<DataBean> data;

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsNameDisplay() {
        return isNameDisplay;
    }

    public void setIsNameDisplay(int isNameDisplay) {
        this.isNameDisplay = isNameDisplay;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ActionBean {
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class DataBean {
        /**
         * price : 0
         * dmsg :
         * count : -1
         * buy : false
         * quality : null
         * vipFree : null
         * active : null
         * buyFlag : false
         */

        private AlbumRightKeyBean albumRightKey;
        private int id;
        private int isExclusive;
        private String picUrl;
        private String desc;
        private int status;
        private String name;
        /**
         * value : 2403882
         * type : 19
         */

        private ActionBean action;

        public AlbumRightKeyBean getAlbumRightKey() {
            return albumRightKey;
        }

        public void setAlbumRightKey(AlbumRightKeyBean albumRightKey) {
            this.albumRightKey = albumRightKey;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsExclusive() {
            return isExclusive;
        }

        public void setIsExclusive(int isExclusive) {
            this.isExclusive = isExclusive;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ActionBean getAction() {
            return action;
        }

        public void setAction(ActionBean action) {
            this.action = action;
        }

        public static class AlbumRightKeyBean {
            private int price;
            private String dmsg;
            private int count;
            private boolean buy;
            private Object quality;
            private Object vipFree;
            private Object active;
            private boolean buyFlag;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getDmsg() {
                return dmsg;
            }

            public void setDmsg(String dmsg) {
                this.dmsg = dmsg;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public boolean isBuy() {
                return buy;
            }

            public void setBuy(boolean buy) {
                this.buy = buy;
            }

            public Object getQuality() {
                return quality;
            }

            public void setQuality(Object quality) {
                this.quality = quality;
            }

            public Object getVipFree() {
                return vipFree;
            }

            public void setVipFree(Object vipFree) {
                this.vipFree = vipFree;
            }

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public boolean isBuyFlag() {
                return buyFlag;
            }

            public void setBuyFlag(boolean buyFlag) {
                this.buyFlag = buyFlag;
            }
        }

        public static class ActionBean {
            private String value;
            private int type;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
