package com.guoxiaotian.lifesecretary.model.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.controler.activity.HistoryDetail;
import com.guoxiaotian.lifesecretary.model.Model;
import com.guoxiaotian.lifesecretary.model.bean.TodayEventBean;
import com.guoxiaotian.lifesecretary.model.bean.TodayEventBean2;
import com.guoxiaotian.lifesecretary.model.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public class TodayInHistory extends BasePager {

    private EditText et_date;
    private ImageButton ib_search;
    private ListView today_listview;

    private NetUtils netUtils;

    private List<TodayEventBean.TodayDataEntity> data;

    private List<TodayEventBean2.TodayEventResult> data2;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                //处理数据
                String json= (String) msg.obj;
                processData(json);
            }

        }
    };

    //数据处理
    private void processData(String json) {

         TodayEventBean2 todayEventBean= prase(json);
         //Log.i("TAG","解析成功的数据："+todayEventBean.getresult().get(1).getDes());
         //data=new ArrayList<TodayEventBean.TodayDataEntity>();
         //data=todayEventBean.getresult();

        data2=new ArrayList<TodayEventBean2.TodayEventResult>();
        data2=todayEventBean.getResult();

        today_listview.setAdapter(new MyBaseAdapter());
    }

    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data2.size();
        }

        @Override
        public Object getItem(int position) {
            return data2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewholder=null;
            if (convertView==null){
                //convertView=View.inflate(context,R.layout.today_items,null);
                convertView=View.inflate(context,R.layout.today_items_2,null);
                viewholder=new ViewHolder();

               viewholder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
               viewholder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
//                viewholder.tv_title_todayitems= (TextView) convertView.findViewById(R.id.tv_title_todayitems);
//                viewholder.tv_happenyear= (TextView) convertView.findViewById(R.id.tv_happenyear);
//                viewholder.tv_nongli= (TextView) convertView.findViewById(R.id.tv_nongli);
//                viewholder.tv_dagang= (TextView) convertView.findViewById(R.id.tv_dagang);
//                viewholder.iv_pic_item= (ImageView) convertView.findViewById(R.id.iv_pic_item);

                convertView.setTag(viewholder);
            }else{
                viewholder = (ViewHolder) convertView.getTag();
            }

       /*     viewholder.tv_title_todayitems.setText(data.get(position).getTitle());
            int year=data.get(position).getYear();
            String Onyear=year+"";
            viewholder.tv_happenyear.setText(Onyear);
            viewholder.tv_nongli.setText(data.get(position).getLunar());
            viewholder.tv_dagang.setText(data.get(position).getDes());
            x.image().bind(viewholder.iv_pic_item,data.get(position).getPic());*/

            viewholder.tv_date.setText(data2.get(position).getDate());
            viewholder.tv_title.setText(data2.get(position).getTitle());

            return convertView;
        }

        class ViewHolder{

            TextView tv_date;
            TextView tv_title;


         /*   TextView tv_title_todayitems;
            ImageView iv_pic_item;
            TextView tv_happenyear;
            TextView tv_nongli;
            TextView tv_dagang;*/
        }
    }
    public TodayEventBean2 prase(String json) {
        TodayEventBean2 todayEventBean2 = new Gson().fromJson(json, TodayEventBean2.class);

        return todayEventBean2;
    }

    public TodayInHistory(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.todayinhistorypager,null);
        et_date= (EditText) view.findViewById(R.id.et_date);
        ib_search= (ImageButton) view.findViewById(R.id.ib_search);
        today_listview= (ListView) view.findViewById(R.id.today_listview);

        ib_search.setOnClickListener(new MyOnImageButtonClickListener());

        today_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String e_id = data2.get(position).getE_id();

                Intent intent =new Intent(context, HistoryDetail.class);

                intent.putExtra("id",e_id);

                context.startActivity(intent);
            }
        });

        return view;
    }


    //按钮点击监听
    class MyOnImageButtonClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {

                final String AllDate = et_date.getText().toString().trim();

                if (TextUtils.isEmpty(AllDate)){
                    Toast.makeText(context, "请输入查询日期", Toast.LENGTH_SHORT).show();
                }else{
                    //AllDate：3/7  要把3 7取出来
                    int i = AllDate.indexOf("/");
                    final String month=AllDate.substring(0,i);
                    final String day = AllDate.substring(i + 1, AllDate.length());
                    final int int_month = Integer.parseInt(month);
                    final int int_day = Integer.parseInt(day);

                    if(int_month<=12&&int_month>=1&&int_day<=31&&int_day>=1){
                    //Log.i("TAG","month: "+month+"  day: "+day);
                    netUtils=new NetUtils();

                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {


                                //String ListJson = netUtils.todayList(month, day);
                                //String Json = netUtils.todayList(int_month, int_day);
                                String Json2 = netUtils.todayList2(AllDate);
                            //Log.i("TAG","新的v2.0请求的数据:"+Json2);

                                Message message=Message.obtain();
                                message.obj=Json2;
                                message.what=1;
                                handler.sendMessage(message);
                                // Log.i("TAG", "历史今天数据列表" + Json);
                            }

                    });
                    }else {
                        Toast.makeText(context, "输入的日期不正确呀", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        }
    @Override
    public void initData() {
        super.initData();


    }
}
