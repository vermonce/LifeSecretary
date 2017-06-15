package com.guoxiaotian.lifesecretary.model.utils;

import android.widget.ImageView;

import com.guoxiaotian.lifesecretary.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/28.
 *
 *      加载gif的工具类，在原有xutils基础上进行修改
 */
public class XUtilsImageUtils {

    /**
     * 显示图片（默认情况）
     *
     * @param imageView 图像控件
     * @param iconUrl   图片地址
     */
    public static void display(ImageView imageView, String iconUrl) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(R.drawable.ic_action_halt)
                .setLoadingDrawableId(R.drawable.ic_action_more)
                .build();
        x.image().bind(imageView, iconUrl,imageOptions);
    }
}
