package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 城市三级联动
 */
public class SanJiLianDBean {
    private List<CityList> citylist;

    public List<CityList> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CityList> citylist) {
        this.citylist = citylist;
    }

    public class CityList{

        private String p;
        private List<CBean> c;//市集合

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public List<CBean> getC() {
            return c;
        }

        public void setC(List<CBean> c) {
            this.c = c;
        }

        public class CBean{
                private String n;
            private List<aBean> a;//县区集合
            public String getN() { //市名
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public List<aBean> getA() {
                return a;
            }

            public void setA(List<aBean> a) {
                this.a = a;
            }

            public class aBean{
                private String s;//县区名字

                public String getS() {
                    return s;
                }

                public void setS(String s) {
                    this.s = s;
                }
            }
        }
    }



}
