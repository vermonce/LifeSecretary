package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.controler.activity.Jiemeng;
import com.guoxiaotian.lifesecretary.controler.activity.Xingshi;
import com.guoxiaotian.lifesecretary.controler.activity.Xingzuo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class SettingPager extends BasePager {

    private GridView setting_gv;

    //private String[]itemNames=new String[]{"星座","解梦","姓氏"};

    private List<String> itemNames=new ArrayList<String>();

    private List<Integer> itemPics =new ArrayList<Integer>();
    //private int[] itemPics=new int[]{R.drawable.xingzuo,R.drawable.zhougongjiemeng,R.drawable.baijiaxing};

    public SettingPager(Context context) {
        super(context);
        itemNames.add("星座");
        itemNames.add("解梦");
        itemNames.add("姓氏");
        itemNames.add("行情");
        itemPics.add(R.drawable.xingzuo);
        itemPics.add(R.drawable.zhougongjiemeng);
        itemPics.add(R.drawable.baijiaxing);
        itemPics.add(R.drawable.company);
    }

    @Override
    public View initView() {

        View view=View.inflate(context, R.layout.settingpager,null);
        setting_gv= (GridView) view.findViewById(R.id.setting_gv);

        setting_gv.setOnItemClickListener(new MyGridviewOnItemClickListener());

        return view;
    }

    class MyGridviewOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent=null;
            switch (position) {
                case 0 :
                    intent=new Intent(context,Xingzuo.class);
                    context.startActivity(intent);
                    break;
                case 1 :
                    intent=new Intent(context,Jiemeng.class);
                    context.startActivity(intent);
                    break;
                case 2 :
                    intent=new Intent(context,Xingshi.class);
                    context.startActivity(intent);
                    break;
                case 3:
                    //intent=new Intent(context,Company.class);
                    //context.startActivity(intent);
                    Toast.makeText(context, "该功能在2.0版本会实现哟", Toast.LENGTH_SHORT).show();
                    break;
            }





        }
    }

    @Override
    public void initData() {
        super.initData();

        setting_gv.setAdapter(new MyGridViewAdapter());


    }

    class MyGridViewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return itemNames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView=new TextView(context);
            ImageView imageView=new ImageView(context);
            if (convertView==null){
                convertView=View.inflate(context,R.layout.setting_grid_items,null);
                textView= (TextView) convertView.findViewById(R.id.tv_gv_item);
                imageView= (ImageView) convertView.findViewById(R.id.iv_gv_item);
            }

            textView.setText(itemNames.get(position));
            imageView.setImageResource(itemPics.get(position));


            return convertView;
        }
    }
}
