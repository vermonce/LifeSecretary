package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoxiaotian.lifesecretary.R;
import com.guoxiaotian.lifesecretary.model.Model;
import com.guoxiaotian.lifesecretary.model.bean.JieMengBean;
import com.guoxiaotian.lifesecretary.model.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class Jiemeng extends Activity {


  /*
  *     String   mytext   =   java.net.URLEncoder.encode("中国",   "utf-8");
        String   mytext2   =   java.net.URLDecoder.decode(mytext,   "utf-8");

         这两条语句在同一个页面中的话,得到的结果是:
        mytext:   %E4%B8%AD%E5%9B%BD
        mytex2:   中国

        String   zhongguo=new      String(request.getParameter("zhongguo").getBytes("iso8859_1"));
        zhongguo=java.net.URLDecoder.decode(zhongguo,"utf-8");
  * */


    private EditText et_content;
    private ImageButton ib_search;
   /* private TextView tv_meng_name;
    private TextView tv_meng_type;
    private TextView tv_meng_content;
*/
    private ListView lv_jiemeng;

    private NetUtils netUtils;

    private List<JieMengBean.JieMengResult> data;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                String json= (String) msg.obj;

                if (TextUtils.isEmpty(json)){
                    Toast.makeText(Jiemeng.this, "没有数据哦", Toast.LENGTH_SHORT).show();
                }else{

                    processData(json);
                }
            }
        }
    };

    private void processData(String json) {

        JieMengBean jieMengBean=prase(json);

        data=new ArrayList<JieMengBean.JieMengResult>();
        data=jieMengBean.getResult();

        lv_jiemeng.setAdapter(new MyAdapter());

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder=null;
            if (convertView==null){

                convertView=View.inflate(Jiemeng.this,R.layout.jiemeng_item,null);
                viewHolder=new ViewHolder();

                viewHolder.tv_meng_content= (TextView) convertView.findViewById(R.id.tv_meng_content);
                viewHolder.tv_meng_type= (TextView) convertView.findViewById(R.id.tv_meng_type);
                //viewHolder.tv_meng_name= (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }

            String content_Org=data.get(position).getContent();
            String content=content_Org.replaceAll("<div>|</div>|<br/>","");//去除里面特定的字符串

            viewHolder.tv_meng_content.setText(content);
            //viewHolder.tv_meng_name.setText(data.get(position).getTitle());
            viewHolder.tv_meng_type.setText(data.get(position).getType());

            return convertView;
        }


    }

    class ViewHolder{

         TextView tv_meng_name;
         TextView tv_meng_type;
         TextView tv_meng_content;
    }
    private JieMengBean prase(String json) {

        JieMengBean jieMengBean2=new Gson().fromJson(json,JieMengBean.class);

        return jieMengBean2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_jiemeng);

        initView();
    }

    private void initView() {

        et_content = (EditText)findViewById(R.id.et_content);
        ib_search = (ImageButton)findViewById(R.id.ib_search);
        lv_jiemeng = (ListView)findViewById(R.id.lv_jiemeng);

     /*   tv_meng_name = (TextView)findViewById(R.id.tv_meng_name);
        tv_meng_type = (TextView)findViewById(R.id.tv_meng_type);
        tv_meng_content = (TextView)findViewById(R.id.tv_meng_content);*/


        ib_search.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            final String JieMengContent=et_content.getText().toString().trim();

           /* final String JieMengContent2="梦见黄金";*/

            if (TextUtils.isEmpty(JieMengContent)){
                Toast.makeText(Jiemeng.this, "输入内容吧", Toast.LENGTH_SHORT).show();
            }else{

                netUtils=new NetUtils();

                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {

                        String jieMengJson = netUtils.getJieMeng(JieMengContent);

                        Message message=Message.obtain();
                        message.obj=jieMengJson;
                        message.what=1;

                        handler.sendMessage(message);
                    }
                });
            }

        }
    }


}
