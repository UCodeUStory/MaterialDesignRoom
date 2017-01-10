package com.example.qiyue.materialdesignadvance.demo2.json;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class JsonBean {
    /**
     * status : 2000
     * msg : Successful!
     * data : [{"details":[{"distance":2847,"nextLat":39.994076,"nextLong":116.47764,"nexti":"MeloDev","status":4}],"distance":2847,"imageUrl":"","overview":"长期原创Android博客","source":"http://www.jianshu.com/users/f5909165c1e8/latest_articles","status":"SUCCESSFUL"},{"details":[{"distance":2769,"nextLat":39.97691,"nextLong":116.46019,"nexti":"MeloDev","status":4}],"distance":2769,"imageUrl":"","overview":"喜欢请加关注","source":"http://www.jianshu.com/users/f5909165c1e8/latest_articles","status":"SUCCESSFUL"}]
     */

    public String status;
    public String msg;

    @Override
    public String toString() {
        return "JsonBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * details : [{"distance":2847,"nextLat":39.994076,"nextLong":116.47764,"nexti":"MeloDev","status":4}]
     * distance : 2847
     * imageUrl :
     * overview : 长期原创Android博客
     * source : http://www.jianshu.com/users/f5909165c1e8/latest_articles
     * status : SUCCESSFUL
     */

    public List<DataBean> data;

    public static class DataBean {
        public int distance;
        public String imageUrl;
        public String overview;
        public String source;
        public String status;

        @Override
        public String toString() {
            return "DataBean{" +
                    "distance=" + distance +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", overview='" + overview + '\'' +
                    ", source='" + source + '\'' +
                    ", status='" + status + '\'' +
                    ", details=" + details +
                    '}';
        }

        /**
         * distance : 2847
         * nextLat : 39.994076
         * nextLong : 116.47764
         * nexti : MeloDev
         * status : 4
         */

        public List<DetailsBean> details;

        public static class DetailsBean {
            public int distance;
            public double nextLat;

            @Override
            public String toString() {
                return "DetailsBean{" +
                        "distance=" + distance +
                        ", nextLat=" + nextLat +
                        ", nextLong=" + nextLong +
                        ", nexti='" + nexti + '\'' +
                        ", status=" + status +
                        '}';
            }

            public double nextLong;
            public String nexti;
            public int status;
        }
    }
}
