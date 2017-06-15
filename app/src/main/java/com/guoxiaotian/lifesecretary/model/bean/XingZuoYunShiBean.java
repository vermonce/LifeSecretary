package com.guoxiaotian.lifesecretary.model.bean;

/**
 * Created by Administrator on 2017/3/14.
 */
public class XingZuoYunShiBean {


    /**
     * result : {"date":"20151126","all":"60%","love":"40%","summary":"无论是有无对象的狮子，今天的恋爱运都不是很好。单身的人虽有满腔的热血，但是落花有意流水无情，付出的感情也不太容易得到回应；而有伴侣的人，与对方的感情开始出现考验，情海生变的机率大增，不可不慎！","color":"银色","work":"60%","health":"80%","QFriend":"天秤座","number":"9","datetime":"2015年11月26日","money":"60%","name":"狮子座","error_code":"0","resultcode":"200"}
     * reason : Succes
     * error_code : 0
     */
    private ResultEntity result1;
    private String reason;
    private int error_code;

    public void setResult(ResultEntity result) {
        this.result1 = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultEntity getResult() {
        return result1;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public class ResultEntity {
        /**
         * date : 20151126
         * all : 60%
         * love : 40%
         * summary : 无论是有无对象的狮子，今天的恋爱运都不是很好。单身的人虽有满腔的热血，但是落花有意流水无情，付出的感情也不太容易得到回应；而有伴侣的人，与对方的感情开始出现考验，情海生变的机率大增，不可不慎！
         * color : 银色
         * work : 60%
         * health : 80%
         * QFriend : 天秤座
         * number : 9
         * datetime : 2015年11月26日
         * money : 60%
         * name : 狮子座
         * error_code : 0
         * resultcode : 200
         */
        private String date;
        private String all;
        private String love;
        private String summary;
        private String color;
        private String work;
        private String health;
        private String QFriend;
        private String number;
        private String datetime;
        private String money;
        private String name;
        private String error_code;
        private String resultcode;

        public void setDate(String date) {
            this.date = date;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public void setQFriend(String QFriend) {
            this.QFriend = QFriend;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public void setResultcode(String resultcode) {
            this.resultcode = resultcode;
        }

        public String getDate() {
            return date;
        }

        public String getAll() {
            return all;
        }

        public String getLove() {
            return love;
        }

        public String getSummary() {
            return summary;
        }

        public String getColor() {
            return color;
        }

        public String getWork() {
            return work;
        }

        public String getHealth() {
            return health;
        }

        public String getQFriend() {
            return QFriend;
        }

        public String getNumber() {
            return number;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getMoney() {
            return money;
        }

        public String getName() {
            return name;
        }

        public String getError_code() {
            return error_code;
        }

        public String getResultcode() {
            return resultcode;
        }
    }
}
