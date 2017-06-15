package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class CompanyInfoBean {

    private List<CompanyInfoResult> result;

    private int error_code;

    private String reason;

    public List<CompanyInfoResult> getResult() {
        return result;
    }

    public void setResult(List<CompanyInfoResult> result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class CompanyInfoResult{

        private String company_code;

        private String company_name;

        private String ispositive;

        private String title;

        private String yq_date;

        private String yq_value;

        public String getCompany_code() {
            return company_code;
        }

        public void setCompany_code(String company_code) {
            this.company_code = company_code;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getIspositive() {
            return ispositive;
        }

        public void setIspositive(String ispositive) {
            this.ispositive = ispositive;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYq_date() {
            return yq_date;
        }

        public void setYq_date(String yq_date) {
            this.yq_date = yq_date;
        }

        public String getYq_value() {
            return yq_value;
        }

        public void setYq_value(String yq_value) {
            this.yq_value = yq_value;
        }
    }
}
