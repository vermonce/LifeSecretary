package com.guoxiaotian.lifesecretary.controler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
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
import com.guoxiaotian.lifesecretary.model.bean.CompanyInfoBean;
import com.guoxiaotian.lifesecretary.model.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class Company extends Activity {

    private EditText et_time;
    private ImageButton ib_search;
    private ListView lv_company;

    private NetUtils netUtils;

    private List<CompanyInfoBean.CompanyInfoResult> data;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                String json = (String) msg.obj;
                processData(json);
            }
        }
    };

    private void processData(String json) {

        CompanyInfoBean companyInfoBean=prase(json);

        data=new ArrayList<CompanyInfoBean.CompanyInfoResult>();

        data=companyInfoBean.getResult();

        Log.i("TAG","data的数据："+data.get(1).getCompany_name());

        lv_company.setAdapter(new MyAdapter());

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

            ViewHolder viewHolder = null;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=View.inflate(Company.this,R.layout.company_item,null);
                viewHolder.tv_code= (TextView) convertView.findViewById(R.id.tv_code);
                viewHolder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(viewHolder);
            }else{

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_code.setText(data.get(position).getCompany_code());
            viewHolder.tv_time.setText(data.get(position).getYq_date());
            viewHolder.tv_title.setText(data.get(position).getTitle());
            viewHolder.tv_name.setText(data.get(position).getCompany_name());

            return convertView;
        }
    }

    class ViewHolder{
        TextView tv_time;
        TextView tv_title;
        TextView tv_code;
        TextView tv_name;
    }

    private CompanyInfoBean prase(String json) {

        CompanyInfoBean companyInfoBean2=new Gson().fromJson(json,CompanyInfoBean.class);
        return companyInfoBean2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        setContentView(R.layout.activity_company);

        initView();

    }

    private void initView() {

        et_time = (EditText)findViewById(R.id.et_time);
        ib_search = (ImageButton)findViewById(R.id.ib_search);
        lv_company = (ListView)findViewById(R.id.lv_company);

        ib_search.setOnClickListener(new MyOnClickListener());


    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            final String date = et_time.getText().toString().trim();

            netUtils=new NetUtils();

            if (TextUtils.isEmpty(date)){
                Toast.makeText(Company.this, "请输入日期", Toast.LENGTH_SHORT).show();
            }else{

                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {

                        String Json = netUtils.getCompanyInformation(date);

                        Log.i("TAG","联网请求的的数据："+Json);

                        Message message=Message.obtain();
                        message.what=1;
                        message.obj=Json;

                        handler.sendMessage(message);

                    }
                });

            }

        }
    }

}
