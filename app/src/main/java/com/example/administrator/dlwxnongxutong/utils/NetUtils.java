package com.example.administrator.dlwxnongxutong.utils;

import java.net.FileNameMap;

/**
 * 
 *
 * @作者 wch
 *
 * @create at 2017/1/7 0007 下午 2:13
 * 
 * @name 接口地址
 */
public class NetUtils {
//    public static final String IP = "http://192.168.3.186/nmt";
    public static final String IP = "http://www.nongmut.com/index.php";
    /**
     * 登录
     */
    public static final String LOGIN = IP+"/AppLogin/ULogin";
    /**
     * 注册
     */
    public static final String REGISTER =IP+"/Register/UserRegister";
    /**
     * 获取个人信息
     */
    public static final String Get_Message = IP+"/AppLogin/GetUserInfo";
    /**
     * 获取验证码
     */
    public static final String AUTE_CODE = IP+"/SendMessage/SendMsg";
    /**
     * 找回密码
     */
    public static final String Find_PWD = IP+"/AppLogin/FindUPwd";
    /**
     * 修改密码
     */
    public static final String CHANGE_PWD = IP +"/AppLogin/UpUserPwd";
    /**
     * 上传头像
     */
    public  static final String UP_PHOTO = IP+"/AppLogin/UploadUserPhoto";
    /**
     * 修改支付密码
     */
    public static final String CHANGE_PAY_PWD = IP +"/DoorWay/Home/DoorWay/UpdateUserPayPas";
    /**
     * 修改昵称
     */
    public static final String CHANGE_NICKNAME = IP +"/AppLogin/UpdateUserNick";
    /**
     * 修改手机号
     */
//    public static final String CHANGE_NICKNAME = IP +"/AppLogin/UpdateUserNick";
    /**
     * 全部商品
     */
    public static final String ALL_GOODS = IP+ "/Goods/GetGoods";
    /**
     * 新品推荐
     */
    public static final String NEW_PRODUCT = IP +"/Goods/GetNewTJGoods";
    /**
     * 热品推荐
     */
    public static final String ReviewGoods = IP +"/Goods/GetHotGoods";
    /**
     * 商品分类
     */
    public static final String Class_GOODS = IP+"/Classify/GetClassify";
    /**
     * 判断商品是否被收藏
     */
    public static final String IS_collect = IP+"/Goods/GetGoodsHouse";
    /**
     * 收藏该商品
     */
    public static final String Collect_GOODS = IP+"/Goods/SetGoodsHouse";
    /**
     * 我的收藏
     */
    public static final String MY_COLLECT = IP +"/Goods/MyHouse";
    /**
     * 首页分类
     */
    public static final String HOME_CLASS = IP +"/Classify/GetHomeClassify";
    /**
     * 首页轮播图
     */
    public static final String Home_LUNBO = IP+"/Api/GetCarousel";
    /**
     * 新增用户收货地址
     */
    public static final String New_ADDRESS = IP +"/Address/NewlyAdded";
    /**
     * 收货地址列表数据接口
     */
    public static final String Address_list = IP +"/Address/GetAddress";
    /**
     * 收货地址删除接口
     */
    public static final String Delete_ADDress = IP +"/Address/DelAddress";
    /**
     * 收货地址修改接口
     */
    public static final  String RenewAddress = IP +"/Address/RenewAddress";
    /**
     * 设置默认地址（收获列表页）
     */
    public static final String SetDefault = IP +"/Address/SetDefault";
    /**
     * 加入购物车
     */
    public static final String Join_Cat = IP +"/Cart/JoinCart";
    /**
     * 获得商品全部评价
     */
    public static final String Get_ALLevaluate = IP +"/Goods/GetRate";
    /**
     * 获取购物车列表
     */
    public static final String ShoppingCat_list = IP +"/Cart/CartList";
    /**
     * 获取配送方式
     */
    public static final String DIS_TYPE = IP+"/Order/Deliver";
    /**
     * 获取商家信息

     */
    public static  final String SHANGJIA = IP+"/Seller/GetBusiness";
    /**
     * 商品的立即购买和购物车结算
     */
    public static final String ORDER_UP= IP+"/Order/PlaceOrder";
    /**
     * 支付方式获取
     */
    public static final String PAY_TYPE = IP +"/Pay/GetPay";
    /**
     * 修改购物车信息
     */
    public static final String ModifyData = IP+"/Cart/AlterCart";
    /**
     * 购物车商品信息删除
     */
    public static final String Delete_Cart = IP +"/Cart/CartDel";
    /**
     * 添加银行卡
     */
    public static final String Add_bank = IP+"/Bank/BankAdd";
    /**
     * 获取银行卡列表
     */
    public static final String Bank_List = IP+"/Bank/BankList";
    /**
     * 添加商品订单评价
     */
    public static final String Order_evaluate = IP +"/Goods/RateAdd";
    /**
     * 返利纪录获取
     */
    public static final String Redate_List= IP+"/Rebate/GetRebate";
    /**
     * 提现记录获取
     */
    public static final String Draw_money = IP+"/Rebate/GetUserWithdraw";
    /**
     * 积分记录获取
     */
    public static final  String GetUserIntl = IP+"/Rebate/GetUserIntl";
    /**
     * 提现申请提交接口
     */
    public static final String UserWithDraw = IP+"/Rebate/UserWithDraw";
    /**
     * 我的订单
     */
    public static final String MyOrderList = IP+"/Order/MyOrderList";
    /**
     * 删除订单
     */
    public static final String Delete_Order= IP +"/Order/DelOrder";
    /**
     * 获取单个商品
     */
    public static final String Goods_message = IP+"/Goods/GetGoodsInfo";
    /**
     * 获取管理员列表
     */
    public static final String AddminIdList = IP+"/Seller/GetManager";
    /**
     * 上传管理员id
     */
    public static final String UpAdminId = IP+"/Seller/BindAdmin";
    public static final String PAY_URL  = "";

    public static final String CHECKRSASIGN_URL  = "";
    /**
     * 关于我们
     */
    public static final String About_ME = IP+"/admin.php?c=Index&a=About";
    /**
     * 用户协议
     */
    public static final String User_Prodo = IP+"/admin.php?c=Index&a=About";
    /**
     * 更新版本
     */
    public static final String GET_VERSION_URL = IP+"/Api/GetAppEditionInfo";
}



