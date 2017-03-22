package com.example.tuion.mywallpaperwithokhttp;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by tuionf on 2017/3/9.
 */

public class ImageAdapter extends PagerAdapter {

    private List<View> mViews;
    private Context mContext;
    private WallpaperManager mWallManager;

    public ImageAdapter(Context context,List<View> mViews) {
        this.mViews = mViews;
        this.mContext = context;
        mWallManager=WallpaperManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(mViews.get(position),0);
        ImageView iv = (ImageView) mViews.get(position);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag","dianjishijian"+position);
                showDialog(mContext,v);
            }
        });

        return mViews.get(position);
    }

    private void showDialog(Context context, final View v) {
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setTitle("Tips");
        alertDialog.setMessage("设为壁纸？");
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
                v.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false);

                try {
                    mWallManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        alertDialog.show();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));//删除页卡
    }
}
