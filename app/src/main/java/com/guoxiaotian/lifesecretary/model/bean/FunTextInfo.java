package com.guoxiaotian.lifesecretary.model.bean;

/**
 * Created by Administrator on 2017/3/4.
 */
public class FunTextInfo {

    private String content;

    private String hashId;

    private int unixtime;

    private String updatetime;

    public FunTextInfo(String hashId, String content, int unixtime, String updatetime) {
        this.hashId = hashId;
        this.content = content;
        this.unixtime = unixtime;
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }
}
