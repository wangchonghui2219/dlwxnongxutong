package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/13.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name ClassGoodsBean$
 */
public class ClassGoodsBean {


    /**
     * code : 200
     * message : 获取成功
     * info : [{"gcid":"1","gcname":"家畜饲料","pid":"1","level":"1","lower":[{"gcid":"2","gcname":"教槽料","pid":"1","level":"2","lower":[{"gcid":"3","gcname":"粉状料","pid":"2","level":"3"},{"gcid":"4","gcname":"粉加粒","pid":"2","level":"3"}]},{"gcid":"5","gcname":"保育料","pid":"1","level":"2","lower":[{"gcid":"6","gcname":"40%","pid":"5","level":"3"},{"gcid":"7","gcname":"30%","pid":"5","level":"3"}]},{"gcid":"8","gcname":"小猪料","pid":"1","level":"2","lower":[{"gcid":"9","gcname":"4%","pid":"8","level":"3"},{"gcid":"10","gcname":"8%","pid":"8","level":"3"},{"gcid":"11","gcname":"10%","pid":"8","level":"3"}]},{"gcid":"12","gcname":"中大猪","pid":"1","level":"2","lower":[{"gcid":"13","gcname":"4%","pid":"12","level":"3"},{"gcid":"14","gcname":"8%","pid":"12","level":"3"},{"gcid":"15","gcname":"10%","pid":"12","level":"3"}]}]},{"gcid":"16","gcname":"家禽饲料","pid":"16","level":"1","lower":[{"gcid":"17","gcname":"开口料","pid":"16","level":"2","lower":[{"gcid":"18","gcname":"颗粒(小破碎)","pid":"17","level":"3"}]},{"gcid":"19","gcname":"雏鸡料","pid":"16","level":"2","lower":[{"gcid":"20","gcname":"5%预混料","pid":"19","level":"3"}]},{"gcid":"21","gcname":"青年料","pid":"16","level":"2","lower":[{"gcid":"22","gcname":"5%预混料","pid":"21","level":"3"}]},{"gcid":"23","gcname":"蛋高峰","pid":"16","level":"2","lower":[{"gcid":"24","gcname":"5%预混料","pid":"23","level":"3"}]}]},{"gcid":"25","gcname":"兽药产品","pid":"25","level":"1"},{"gcid":"26","gcname":"宠物粮","pid":"26","level":"1"},{"gcid":"27","gcname":"动物疫苗","pid":"27","level":"1"}]
     */

    private int code;
    private String message;
    private List<InfoBean> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * gcid : 1
         * gcname : 家畜饲料
         * pid : 1
         * level : 1
         * lower : [{"gcid":"2","gcname":"教槽料","pid":"1","level":"2","lower":[{"gcid":"3","gcname":"粉状料","pid":"2","level":"3"},{"gcid":"4","gcname":"粉加粒","pid":"2","level":"3"}]},{"gcid":"5","gcname":"保育料","pid":"1","level":"2","lower":[{"gcid":"6","gcname":"40%","pid":"5","level":"3"},{"gcid":"7","gcname":"30%","pid":"5","level":"3"}]},{"gcid":"8","gcname":"小猪料","pid":"1","level":"2","lower":[{"gcid":"9","gcname":"4%","pid":"8","level":"3"},{"gcid":"10","gcname":"8%","pid":"8","level":"3"},{"gcid":"11","gcname":"10%","pid":"8","level":"3"}]},{"gcid":"12","gcname":"中大猪","pid":"1","level":"2","lower":[{"gcid":"13","gcname":"4%","pid":"12","level":"3"},{"gcid":"14","gcname":"8%","pid":"12","level":"3"},{"gcid":"15","gcname":"10%","pid":"12","level":"3"}]}]
         */

        private String gcid;
        private String gcname;
        private String pid;
        private String level;
        private List<LowerBeanX> lower;

        public String getGcid() {
            return gcid;
        }

        public void setGcid(String gcid) {
            this.gcid = gcid;
        }

        public String getGcname() {
            return gcname;
        }

        public void setGcname(String gcname) {
            this.gcname = gcname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<LowerBeanX> getLower() {
            return lower;
        }

        public void setLower(List<LowerBeanX> lower) {
            this.lower = lower;
        }

        public static class LowerBeanX {
            /**
             * gcid : 2
             * gcname : 教槽料
             * pid : 1
             * level : 2
             * lower : [{"gcid":"3","gcname":"粉状料","pid":"2","level":"3"},{"gcid":"4","gcname":"粉加粒","pid":"2","level":"3"}]
             */

            private String gcid;
            private String gcname;
            private String pid;
            private String level;
            private List<LowerBean> lower;

            public String getGcid() {
                return gcid;
            }

            public void setGcid(String gcid) {
                this.gcid = gcid;
            }

            public String getGcname() {
                return gcname;
            }

            public void setGcname(String gcname) {
                this.gcname = gcname;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<LowerBean> getLower() {
                return lower;
            }

            public void setLower(List<LowerBean> lower) {
                this.lower = lower;
            }

            public static class LowerBean {
                /**
                 * gcid : 3
                 * gcname : 粉状料
                 * pid : 2
                 * level : 3
                 */

                private String gcid;
                private String gcname;
                private String pid;
                private String level;

                public String getGcid() {
                    return gcid;
                }

                public void setGcid(String gcid) {
                    this.gcid = gcid;
                }

                public String getGcname() {
                    return gcname;
                }

                public void setGcname(String gcname) {
                    this.gcname = gcname;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }
            }
        }
    }
}
