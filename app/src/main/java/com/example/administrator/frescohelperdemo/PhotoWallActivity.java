package com.example.administrator.frescohelperdemo;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.fresco.helper.photoview.PhotoX;
import com.facebook.fresco.helper.photoview.entity.PhotoInfo;

import java.util.ArrayList;

/**
 * Created by android_ls on 16/11/2.
 */

public class PhotoWallActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView, mRecyclerView2, mRecyclerView3;
    private PhotoWallAdapter mPhotoWallAdapter;
    private GridLayoutManager mLayoutManager;
    private LinearLayout titleLinearLayout, title2LinearLayout, title3LinearLayout, rootLinearLayout;
    private TextView titleTextView, title2TextView, title3TextView;
    private int displayWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);

        /** 取得屏幕的實際寬度 */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        displayWidth = dm.widthPixels;

        /** 對LinearLayout 個別設定比例的寬高 */
        rootLinearLayout = findViewById(R.id.rootLinearLayout);
        rootLinearLayout.setVisibility(View.VISIBLE);
        titleLinearLayout = findViewById(R.id.titleLinearLayout);
        int height = displayWidth / 6;
        int width = displayWidth;
        titleLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        title2LinearLayout = findViewById(R.id.title2LinearLayout);
        title2LinearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        title3LinearLayout = findViewById(R.id.title3LinearLayout);
        title3LinearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        /** 設定字體可以自動縮放 添加下划線的效果 */
        titleTextView = findViewById(R.id.titleTextView);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(titleTextView, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(titleTextView, 5, 100, 1, TypedValue.COMPLEX_UNIT_SP);
        titleTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);                            // 下划线
        titleTextView.getPaint().setAntiAlias(true);                                                // 抗锯齿
        title2TextView = findViewById(R.id.title2TextView);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(title2TextView, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(title2TextView, 5, 100, 1, TypedValue.COMPLEX_UNIT_SP);
        title2TextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);                           // 下划线
        title2TextView.getPaint().setAntiAlias(true);                                               // 抗锯齿
        title3TextView = findViewById(R.id.title3TextView);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(title3TextView, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(title3TextView, 5, 100, 1, TypedValue.COMPLEX_UNIT_SP);
        title3TextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);                           // 下划线
        title3TextView.getPaint().setAntiAlias(true);                                               // 抗锯齿

        /** 初始化圖片資料 */
        String[] images = {
                "http://www.61tours.com/files/images/travel/chujing/meizhou/%E5%A4%8F%E5%A8%81%E5%A4%B7-%E5%A8%81%E5%9F%BA%E5%9F%BA%E6%B5%B7%E6%BB%A9_1.jpg",
                "http://pic.pimg.tw/hawaiitw2015/1426491480-1675833484_l.jpg",
                "https://r.bstatic.com/images/hotel/max1024x768/124/124454269.jpg",
                "https://q.bstatic.com/images/hotel/max1024x768/213/21376742.jpg",
                "http://www.neeu.com/uploads/images/2015/9/11/1441945115165.jpg",
                "http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/img_jinghua_cn/c8877232758eecdbd1a0b15858fe203c.jpg"
        };
        String[] images2 = {
                "https://exp.cdn-hotels.com/hotels/1000000/530000/523600/523534/420cecc4_z.jpg",
                "https://www.ambatel.com/RES/PRODUCT/201801/Grand%20Ballroom_ver2_027_%ED%92%80%EB%A7%8C_20180122184453.jpg",
                "https://www.hanchao.com/varimg/photo_hotel/HT-20121010-000210/low_big_95564.jpg"
        };
        String[] images3 = {
                "https://i.ytimg.com/vi/eY-BXd8cCAE/maxresdefault.jpg",
                "https://pic.pimg.tw/haruhii/1432782696-2381453875.jpg",
                "http://hobiholidays.com/wp-content/uploads/2017/07/rock.jpg",
                "https://6.share.photo.xuite.net/amano0312/164d233/6356388/1066992271_l.jpg",
                "https://www.loveholidays.com/blog/wp-content/uploads/2018/01/41SolBeachHouseIbiza-Pool.jpg",
                "http://s3.fxtrip.cn/4b84699a04561856346af4c500f664b9.jpg",
                "https://pix10.agoda.net/hotelImages/865/8650/8650_15071016310032057797.jpg?s=1024x768"
        };
        ArrayList<PhotoInfo> data = new ArrayList<>();
        for (String image : images) {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.originalUrl = image;
            photoInfo.thumbnailUrl = image;
            data.add(photoInfo);
        }
        ArrayList<PhotoInfo> data2 = new ArrayList<>();
        for (String image : images2) {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.originalUrl = image;
            photoInfo.thumbnailUrl = image;
            data2.add(photoInfo);
        }
        ArrayList<PhotoInfo> data3 = new ArrayList<>();
        for (String image : images3) {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.originalUrl = image;
            photoInfo.thumbnailUrl = image;
            data3.add(photoInfo);
        }

        /** 初始化RecyclerView相關 */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_photo_wall);
        mLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoWallAdapter = new PhotoWallAdapter(data, new OnItemClickListener<PhotoInfo>() {
            @Override
            public void onItemClick(View view, ArrayList<PhotoInfo> photos, int position) {
                PhotoX.with(PhotoWallActivity.this, PhotoBrowseActivity.class)
                        .setLayoutManager(mLayoutManager)
                        .setPhotoList(photos)
                        .setCurrentPosition(position)
                        .enabledAnimation(true)
                        .toggleLongClick(false)
                        .start();
            }
        });
        mRecyclerView.setAdapter(mPhotoWallAdapter);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.rv_photo_wall2);
        mLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView2.setLayoutManager(mLayoutManager);
        mPhotoWallAdapter = new PhotoWallAdapter(data2, new OnItemClickListener<PhotoInfo>() {
            @Override
            public void onItemClick(View view, ArrayList<PhotoInfo> photos, int position) {
                PhotoX.with(PhotoWallActivity.this, PhotoBrowseActivity.class)
                        .setLayoutManager(mLayoutManager)
                        .setPhotoList(photos)
                        .setCurrentPosition(position)
                        .enabledAnimation(true)
                        .toggleLongClick(false)
                        .start();
            }
        });
        mRecyclerView2.setAdapter(mPhotoWallAdapter);
        mRecyclerView3 = (RecyclerView) findViewById(R.id.rv_photo_wall3);
        mLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView3.setLayoutManager(mLayoutManager);
        mPhotoWallAdapter = new PhotoWallAdapter(data3, new OnItemClickListener<PhotoInfo>() {
            @Override
            public void onItemClick(View view, ArrayList<PhotoInfo> photos, int position) {
                PhotoX.with(PhotoWallActivity.this, PhotoBrowseActivity.class)
                        .setLayoutManager(mLayoutManager)
                        .setPhotoList(photos)
                        .setCurrentPosition(position)
                        .enabledAnimation(true)
                        .toggleLongClick(false)
                        .start();
            }
        });
        mRecyclerView3.setAdapter(mPhotoWallAdapter);
    }
}
