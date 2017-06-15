package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class TodayEventBean2 {

    private String reason;

    private List<TodayEventResult> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<TodayEventResult> getResult() {
        return result;
    }

    public void setResult(List<TodayEventResult> result) {
        this.result = result;
    }

    public static class TodayEventResult{

        private String day;

        private String date;

        private String title;

        private String e_id;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }
    }
}
