package com.example.administrator.dlwxnongxutong.activitys;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.PhotoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.example.administrator.dlwxnongxutong.utils.Utils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 个人中心
 */
public class PersonageCenterActivity extends BaseActiVity {


    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.iv_toxuaing)
    ImageView mIvToxuaing;
    @BindView(R.id.rl_touxiang)
    RelativeLayout mRlTouxiang;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rl_nicheng)
    RelativeLayout mRlNicheng;
    @BindView(R.id.tv_phonenumber)
    TextView mTvPhonenumber;
    @BindView(R.id.rl_phone)
    RelativeLayout mRlPhone;
    @BindView(R.id.activity_personage_center)
    LinearLayout mActivityPersonageCenter;

    LinearLayout mLlPhotoalbum;

    LinearLayout mLlPhotograph;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private SharedPreferences sp;


    @Override
    public void initView() {
        setContentView(R.layout.activity_personage_center);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTitle.setText("个人中心");
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);


    }

    @Override
    protected void onResume() {
        String photo = sp.getString(SPUtils.USER_PHOTO, "");
        if (photo.endsWith("jpg")){                                     //判断头像是否以jpg结尾
            Picasso.with(ctx).load(photo).into(mIvToxuaing);
        }//判断用户昵称是否为on个
            mTvName.setText(sp.getString(SPUtils.USER_name,""));
       //判断手机号是否为空
        mTvPhonenumber.setText(sp.getString(SPUtils.USER_PHONE,""));
        super.onResume();
    }

    @Override
    public void initListener() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                break;

        }

        return true;

    }

    @OnClick({R.id.rl_touxiang, R.id.rl_nicheng, R.id.rl_phone})
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.rl_touxiang:
                showDia();

                break;
            case R.id.rl_nicheng:
                mIntent = new Intent(ctx, SetNickNameActivity.class);
                mIntent.putExtra("Name", "昵称");
                mIntent.putExtra("hint", "请输入昵称");
                mIntent.putExtra("content",sp.getString(SPUtils.USER_name,""));
                startActivity(mIntent);
                break;
            case R.id.rl_phone:
                mIntent = new Intent(ctx, SetNickNameActivity.class);
                mIntent.putExtra("Name", "手机号");
                mIntent.putExtra("hint", "请输入手机号");
                mIntent.putExtra("content",sp.getString(SPUtils.USER_PHONE,""));
                startActivity(mIntent);
                break;

        }
    }
    protected static Uri tempUri;
    /**
     * 显示Dialog
     */
    private void showDia() {
        builder = new AlertDialog.Builder(ctx);
        builder.create();
        View view = View.inflate(ctx, R.layout.item_dialog, null);
        builder.setView(view);
        mLlPhotoalbum = (LinearLayout) view.findViewById(R.id.ll_photoalbum);
        mLlPhotograph = (LinearLayout) view.findViewById(R.id.ll_photograph);
        mLlPhotoalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent();
                intent1.setType("image/*");
				/* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent1.setAction(Intent.ACTION_GET_CONTENT);
				/* 取得相片后返回本画面 */
                startActivityForResult(intent1, 1);
            }
        });
        mLlPhotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                tempUri = Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "image.jpg"));
                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                startActivityForResult(openCameraIntent, 2);

            }
        });
        dialog = builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            dialog.dismiss();
        if (resultCode ==PersonageCenterActivity.this.RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case 2:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case 1:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case 3:
                    if (data != null) {
                        Log.i("sssssssss",  "ssssssssss");
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
            /**
             * 裁剪图片方法实现
             *
             * @param uri
             */
            protected void startPhotoZoom(Uri uri) {
                if (uri == null) {
                    Log.i("tag", "The uri is not exist.");
                }
                tempUri = uri;
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                // 设置裁剪
                intent.putExtra("crop", "true");
                // aspectX aspectY 是宽高的比例
                intent.putExtra("aspectX", 2);
                intent.putExtra("aspectY", 2);
                // outputX outputY 是裁剪图片宽高
                intent.putExtra("outputX", 150);
                intent.putExtra("outputY", 150);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 3);
            }
    private Bitmap photo = null;
    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        Log.i("sssssssss", extras + "ssssssssss");
        if (extras != null) {
            photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            mIvToxuaing.setImageBitmap(photo);

            wch("ssssssssssss");

            uploadPic(photo);
        }
    }
    private void uploadPic(Bitmap bitmap) {

        // 上传至服务器
        // 拿着imagePath上传了
        byte[] bitmapByte = bitmapToByte(bitmap);
        // 给图片转码为base64
        final String photo = Base64.encodeToString(bitmapByte, 0,
                bitmapByte.length, Base64.DEFAULT);
            //TODO
            upPhoto(photo);
    }

    /**
     *上传头像到服务器
     */
    private void upPhoto(String photo) {
        OkGo.post(NetUtils.UP_PHOTO)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("pho",photo)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        PhotoBean photoBean = gson.fromJson(s, PhotoBean.class);
                        if (photoBean.code == 200) {
                            toast(photoBean.message);
                            sp.edit().putString(SPUtils.USER_PHOTO,photoBean.info.uhead).commit();
                            Picasso.with(ctx).load(photoBean.info.uhead).into(mIvToxuaing);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });


    }

    private byte[] bitmapToByte(Bitmap bitmap) {

        // 将Bitmap转换成字符串
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

        byte[] bytes = bStream.toByteArray();
        return bytes;
    }
    public Bitmap stringtoBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
