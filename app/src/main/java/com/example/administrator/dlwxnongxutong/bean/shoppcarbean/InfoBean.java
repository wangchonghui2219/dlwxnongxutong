package com.example.administrator.dlwxnongxutong.bean.shoppcarbean;

import java.util.List;

/**
 * Created by scy on 2017/2/20.
 */

public class InfoBean extends ShoppCatListBean{
    /**
     * cart : [{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11919a9e0e.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a1191ce7ef8.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11922dc8b4.jpg"}],"gcid":"1","gcname":"家畜饲料","gctid":"5","gid":"7","gname":"中华大书房","gnum":"1","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=7","gp":"199.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-13/58a118f8439ae.jpg","intl":"0","intlpay":"0","isintpay":"1","pid":"1","sales":"0","sc":[{"gprice":"200.00","gsdesc":"紫色","gsid":"17","gstock":"200"},{"gprice":"250.00","gsdesc":"红色","gsid":"18","gstock":"100"}]},{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f2b44b78.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"}],"gcid":"1","gcname":"家畜饲料","gctid":"4","gid":"5","gname":"洛阳疫苗","gnum":"1","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=5","gp":"3000.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-11/589e6f2391838.JPG","intl":"0","intlpay":"0","isintpay":"1","pid":"1","sales":"0","sc":[{"gprice":"0.00","gsdesc":"全自动","gsid":"13","gstock":"200"},{"gprice":"0.00","gsdesc":"手动","gsid":"14","gstock":"200"}]},{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e70c6176d9.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e70cb4bbf3.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e70d1da2c4.JPG"}],"gcid":"1","gcname":"家畜饲料","gctid":"3","gid":"6","gname":"平顶山疫苗","gnum":"7","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=6","gp":"3000.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-11/589e70bac363b.JPG","intl":"0","intlpay":"0","isintpay":"1","pid":"1","sales":"0","sc":[{"gprice":"0.00","gsdesc":"大红凹面","gsid":"15","gstock":"100"},{"gprice":"0.00","gsdesc":"大红条面","gsid":"16","gstock":"100"}]},{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f2b44b78.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"}],"gcid":"2","gcname":"教槽料","gctid":"2","gid":"4","gname":"新乡消毒剂","gnum":"2","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=4","gp":"3000.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-11/589e6f2391838.JPG","intl":"0","intlpay":"0","isintpay":"1","pid":"1","sales":"0","sc":[{"gprice":"0.00","gsdesc":"全自动","gsid":"11","gstock":"200"},{"gprice":"0.00","gsdesc":"手动","gsid":"12","gstock":"200"}]},{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11aed00226.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11af567c48.JPG"}],"gcid":"2","gcname":"教槽料","gctid":"1","gid":"8","gname":"测试数据","gnum":"10","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=8","gp":"199.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-13/58a11ae680764.JPG","intl":"0.5","intlpay":"0","isintpay":"1","pid":"1","sales":"0","sc":[{"gprice":"199.00","gsdesc":"黑色","gsid":"19","gstock":"200"},{"gprice":"199.00","gsdesc":"褐色","gsid":"20","gstock":"50"}]}]
     * seller : 中华小当家
     * sellerid : 1
     */

    private String seller;
    private String sellerid;
    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    private List<CartBean> cart;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }
}
