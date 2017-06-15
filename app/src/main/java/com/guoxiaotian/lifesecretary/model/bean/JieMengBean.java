package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
public class JieMengBean {

    private List<JieMengResult> result;

    public List<JieMengResult> getResult() {
        return result;
    }

    public void setResult(List<JieMengResult> result) {
        this.result = result;
    }

    public  class JieMengResult{

        private String title;

        private String content;

        private String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
