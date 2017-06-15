package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 *
 *  趣图的数据模型类
 */
public class LaughPicBean {

    private int error_code;

    private String reason;

    private ResultPic result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultPic getResult() {
        return result;
    }

    public void setResult(ResultPic result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultPic{

        List<PicDataEntity> data;

        public List<PicDataEntity> getData() {
            return data;
        }

        public void setData(List<PicDataEntity> data) {
            this.data = data;
        }

        public static class PicDataEntity{

            private String content;

            private String hashId;

            private int unixtime;

            private String updatetime;

            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
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
