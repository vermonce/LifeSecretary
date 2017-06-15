package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 *
 * "_id":"10850101",
 "title":"司马光主持编撰的《资治通鉴》成书",
 "pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200911/30/0F02714956.jpg",
 "year":1085,
 "month":1,
 "day":1,
 "des":"在932年前的今天，1085年1月1日 (农历腊月初三)，司马光主持编撰的《资治通鉴》成书。",
 "lunar":"甲子年腊月初三"
 */
public class TodayEventBean  {

    private int error_code;

    private String reason;



    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

        private List<TodayDataEntity> result;

        public List<TodayDataEntity> getresult() {
            return result;
        }

        public void setData(List<TodayDataEntity> result) {
            this.result = result;
        }

        public static class TodayDataEntity{

            private int day;

            private String des;

            private String _id;

            private String lunar;

            private int month;

            private String pic;

            private String title;

            private int year;

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getLunar() {
                return lunar;
            }

            public void setLunar(String lunar) {
                this.lunar = lunar;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }


