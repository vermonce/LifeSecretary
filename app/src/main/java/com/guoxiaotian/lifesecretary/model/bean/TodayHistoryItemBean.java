package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class TodayHistoryItemBean {

    private String reason;

    private List<HistoryItemResult> result;

    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<HistoryItemResult> getResult() {
        return result;
    }

    public void setResult(List<HistoryItemResult> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class HistoryItemResult{

        private String e_id;

        private String title;

        private String content;

        private String picNo;

        private List<PicUrl> picUrl;

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }

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

        public String getPicNo() {
            return picNo;
        }

        public void setPicNo(String picNo) {
            this.picNo = picNo;
        }

        public List<PicUrl> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrl> picUrl) {
            this.picUrl = picUrl;
        }

        public static class PicUrl{

            private String pic_title;

            private String id;

            private String url;

            public String getPic_title() {
                return pic_title;
            }

            public void setPic_title(String pic_title) {
                this.pic_title = pic_title;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }

}

