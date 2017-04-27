package com.example.administrator.dlwxnongxutong.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.AboutUsActivity;
import com.example.administrator.dlwxnongxutong.activitys.JiFenActivity;
import com.example.administrator.dlwxnongxutong.activitys.LoginActivity;
import com.example.administrator.dlwxnongxutong.activitys.MyCollectActivity;
import com.example.administrator.dlwxnongxutong.activitys.MyOrderFromActivity;
import com.example.administrator.dlwxnongxutong.activitys.MyshippingaddressActiVIty;
import com.example.administrator.dlwxnongxutong.activitys.PersonageCenterActivity;
import com.example.administrator.dlwxnongxutong.activitys.SettingActivity;
import com.example.administrator.dlwxnongxutong.activitys.TiXianActivity;
import com.example.administrator.dlwxnongxutong.activitys.YuEActivity;
import com.example.administrator.dlwxnongxutong.adabter.UserListAdapter;
import com.example.administrator.dlwxnongxutong.bean.LoginBean;
import com.example.administrator.dlwxnongxutong.bean.VersionBean;
import com.example.administrator.dlwxnongxutong.utils.DataCleanManager;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;


import com.example.administrator.dlwxnongxutong.views.CircleImageView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.Call;
import okhttp3.Response;

import static android.R.id.message;
import static com.example.administrator.dlwxnongxutong.R.mipmap.icon_wdtouxiang;


/**
 * @作者 wch
 * @create at 2017/1/7 0007 上午 10:49
 * @name 用户信息
 */
public class UserMesFragment extends BaseFragment {
    private int accent = 0;
    private ViewHolder mVh;
    private RelativeLayout rl_sett;
    private UserListAdapter mAdapter;
    private Intent mIntent;
    private ProgressDialog mProgressDialog;
    private SharedPreferences sp;
    private VersionBean.Info info;

    @Override
    public int getLayoutResID() {

        return R.layout.fragment_user_mes;
    }

    @Override
    public void initView(View view) {
        mVh = new ViewHolder(view);
        rl_sett = (RelativeLayout) view.findViewById(R.id.rl_sett);
    }

    @Override
    public void initData() {
        mAdapter = new UserListAdapter(ctx);
        mVh.lv_userlist.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {

        sp = ctx.getSharedPreferences(SPUtils.SP_MODE, Context.MODE_PRIVATE);
        String phone = sp.getString(SPUtils.USER_PHONE, "");
        String photo = sp.getString(SPUtils.USER_PHOTO, "");
        String user_id = sp.getString(SPUtils.USER_ID, "");
        if (!TextUtils.isEmpty(user_id)) {
//            getMessage(user_id);
        if (!TextUtils.isEmpty(phone)) {
            mVh.tv_accounts.setText("帐号：" + phone);
            if (photo.endsWith("jpg")) {
                Picasso.with(ctx).load(photo).into(mVh.imageView);
            }else{
                Picasso.with(ctx).load(R.mipmap.icon_wdtouxiang).into(mVh.imageView);
            }
        }
        }else{
            Picasso.with(ctx).load(R.mipmap.icon_wdtouxiang).into(mVh.imageView);
            mVh.tv_accounts.setText("登录/注册");
        }
        super.onResume();
    }

    @Override
    public void initListener() {
        mVh.iv_setting.setOnClickListener(this);
        mVh.ll_login.setOnClickListener(this);
        mVh.ll_yue.setOnClickListener(this);
        mVh.ll_jifen.setOnClickListener(this);
        mVh.ll_tixian.setOnClickListener(this);
        rl_sett.setOnClickListener(this);
        mVh.lv_userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Intent mIntent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent;
                switch (position) {
                    case 0://我的订单
                        if (!TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                            mIntent = new Intent(ctx, MyOrderFromActivity.class);
                            ctx.startActivity(mIntent);
                        }else{
                            mIntent = new Intent(ctx, LoginActivity.class);
                            startActivity(mIntent);
                        }
                        break;
                    case 1://个人中心
                        if (!TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                            mIntent = new Intent(ctx, PersonageCenterActivity.class);
                            startActivity(mIntent);
                            return;
                        }
                        mIntent = new Intent(ctx, LoginActivity.class);
                        startActivity(mIntent);
                        break;
                    case 2://我的收藏
                        if (!TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                            mIntent = new Intent(ctx, MyCollectActivity.class);
                            startActivity(mIntent);
                            return;
                        }
                        mIntent = new Intent(ctx, LoginActivity.class);
                        startActivity(mIntent);
                        break;
                    case 3://收货地址
                        if (!TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                            mIntent = new Intent(ctx, MyshippingaddressActiVIty.class);
                            mIntent.putExtra("tag",1);
                            startActivity(mIntent);
                            return;
                        }
                        mIntent = new Intent(ctx, LoginActivity.class);
                        startActivity(mIntent);
                        break;
                    case 4://关于我们
                        mIntent = new Intent(ctx, AboutUsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 5://清空缓存
                        wipeCache();
                        break;
                    case 6://检查版本更新
                        updateVersion();
                        break;
                }
            }


        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_sett://设置

                if (!TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                    mIntent = new Intent(ctx, SettingActivity.class);
                    startActivity(mIntent);
                }else{
                    mIntent = new Intent(ctx, LoginActivity.class);
                    startActivity(mIntent);
                }
                break;
            case R.id.ll_login://登录注册
                if (TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                    mIntent = new Intent(ctx, LoginActivity.class);
                    startActivity(mIntent);
                }
                break;

             case R.id.ll_yue: //余额
                 if (TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                     mIntent = new Intent(ctx, LoginActivity.class);
                     startActivity(mIntent);
                 }else {
                     mIntent = new Intent(ctx, YuEActivity.class);
                     startActivity(mIntent);
                 }
                 break;
             case R.id.ll_jifen: //积分
                 if (TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                     mIntent = new Intent(ctx, LoginActivity.class);
                     startActivity(mIntent);
                 }else {
                     mIntent = new Intent(ctx, JiFenActivity.class);
                     startActivity(mIntent);
                 }
                 break;
             case R.id.ll_tixian:
                 if (TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))) {
                     mIntent = new Intent(ctx, LoginActivity.class);
                     startActivity(mIntent);
                 }else {
                     mIntent = new Intent(ctx, TiXianActivity.class);
                     startActivity(mIntent);
                 }
                 break;
        }
    }

//    /**
//     * 获取个人信息
//     * @param user_id
//     */
//    public void getMessage(String user_id) {
//        Log.i("wch",user_id+"");
//        OkGo.post(NetUtils.Get_Message)
//                .params("userid",user_id)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        wch(s);
//                        Gson gson = new Gson();
//                        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
//                        if (loginBean.code == 200) {
//                            LoginBean.Userinfo info = loginBean.info;
//                            sp.edit().putString(SPUtils.USER_name,info.username).commit();
//                            sp.edit().putString(SPUtils.USER_PHONE,info.userphe).commit();
//                            sp.edit().putString(SPUtils.USER_PHOTO,info.user_photo).commit();
//                            sp.edit().putString(SPUtils.USER_ACCOUNT,info.account).commit();
//                        }else{
//                            Toast.makeText(ctx, loginBean.message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


    private static class ViewHolder {
        public View rootView;
        public ImageView iv_setting;
        public LinearLayout ll_login;
        public LinearLayout ll_yue;
        public LinearLayout ll_tixian;
        public LinearLayout ll_jifen;
        public ListView lv_userlist;
        public TextView tv_accounts;
        public CircleImageView imageView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_setting = (ImageView) rootView.findViewById(R.id.iv_setting);//设置
            this.ll_login = (LinearLayout) rootView.findViewById(R.id.ll_login);//登录注册
            this.ll_yue = (LinearLayout) rootView.findViewById(R.id.ll_yue);//余额
            this.ll_tixian = (LinearLayout) rootView.findViewById(R.id.ll_tixian);//提现
            this.ll_jifen = (LinearLayout) rootView.findViewById(R.id.ll_jifen);//积分
            this.lv_userlist = (ListView) rootView.findViewById(R.id.lv_userlist);//功能列表
            this.tv_accounts = (TextView) rootView.findViewById(R.id.tv_accounts);
            this.imageView = (CircleImageView) rootView.findViewById(R.id.imageView);
        }

    }

    /**
     * 清除缓存
     */
    private void wipeCache() {
        mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setProgress(100);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("正在清除缓存");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.create();
        builder.setTitle("是否清楚缓存");
        builder.setMessage("本次操作将会清除您的缓存信息");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataCleanManager.cleanSharedPreference(ctx);
                DataCleanManager.cleanInternalCache(ctx);
                DataCleanManager.cleanInternalCache(ctx);
                DataCleanManager.cleanFiles(ctx);

                mProgressDialog.show();
                opThread();

            }
        });
        builder.show();


    }

    private void opThread() {
        new Thread() {
            @Override
            public void run() {
                accent++;
                for (int i = 0; i <= 100; i++) {
                    try {
                        mProgressDialog.setProgress(i);
                        sleep(50);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        mProgressDialog.cancel();
                    }
                }
                mProgressDialog.cancel();

            }
        }.start();
    }

    /**
     * 检查版本更新
     */
    public void updateVersion() {
        OkGo.get(NetUtils.GET_VERSION_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        parseVersionJson(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
                

    }


    /**
     * 解析版本信息
     *
     * @param response
     */
    protected void parseVersionJson(String response) {

        Gson gson = new Gson();
        VersionBean versionBean = gson.fromJson(response, VersionBean.class);
        if (versionBean.getCode() == 200) {

            info = versionBean.getInfo();

            isUpload();
        }
    }

    boolean isCancel = false;

    // 检查是否需要更新
    private void isUpload() {
        // 获取包的管理器
        PackageManager manager = ctx.getPackageManager();
        PackageInfo packageInfo = null;
        try {// 获取版本的信息
            packageInfo = manager.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        int versionCode = packageInfo.versionCode;
        if (versionCode < info.getEditionNo()) {
            isCancel = false;
            upLoadApp();
        } else {
            Toast.makeText(ctx, "已经是最新版本", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 有新版本 需要更新
     *
     * @param
     */
    private void upLoadApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("有新版本，是否更新？").setMessage("")
                .setNegativeButton("拒绝", null);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                downLoad();
            }
        });
        builder.show();
    }

    // 下载新APP
    protected void downLoad() {

        final ProgressDialog pd; // 进度条对话框

        pd = new ProgressDialog(ctx);

        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // 点击不可以去掉这个进度条
        pd.setCanceledOnTouchOutside(false);

        pd.setMessage("正在下载更新");

        pd.setButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                isCancel = true;
                pd.dismiss();
            }
        });

        pd.show();

        new Thread() {

            @Override
            public void run() {

                try {

                    File file = getFileFromServer(ctx, info.getDownload(), pd);

                    if (file != null) {
                        sleep(3000);

                        installApk(file);

                        pd.dismiss(); // 结束掉进度条对话框
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }
        }.start();
    }

    // 联网获取更新文件存到本地
    public File getFileFromServer(Context ctx, String path, ProgressDialog pd) {

        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                URL url = null;

                url = new URL(path);


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 默认情况下,HttpURLConnection使用gzip方式获取,要取得长度则要求http请求不要gzip压缩;这样就可以得到文件的大小了
                conn.setRequestProperty("Accept-Encoding", "identity");

                // 连接时长
                conn.setConnectTimeout(5000);

                // 读取时长
                conn.setReadTimeout(3000);
                // 获取到文件的大小
                int contentLength = conn.getContentLength();
                String sdSpaceStr = Formatter.formatFileSize(ctx, contentLength);

                InputStream is = conn.getInputStream();

                File file = new File(Environment.getExternalStorageDirectory(),
                        "upLoad.apk");

                FileOutputStream fos = new FileOutputStream(file);

                BufferedInputStream bis = new BufferedInputStream(is);

                byte[] buffer = new byte[1024];

                int len;

                int total = 0;

                while ((len = bis.read(buffer)) != -1) {

                    fos.write(buffer, 0, len);
                    total += len;
                    // 获取当前下载量
                    pd.setProgress((int) (total * 100) / contentLength);
                    if (isCancel) {
                        file = null;
                        break;
                    }
                }

                fos.close();

                bis.close();

                is.close();

                return file;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            return null;

        }
        return null;
    }



    /**
     * 安装app
     *
     * @param file
     */
    protected void installApk(File file) {
        Intent intent = new Intent();

        // 执行动作

        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 执行的数据类型

        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
