package com.guoxiaotian.lifesecretary.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class LaughTextBean {


/**
 * result : {"data":[{"unixtime":1418815439,"updatetime":"2014-12-17 19:23:59","hashId":"607ce18b4bed0d7b0012b66ed201fb08","content":"女生分手的原因有两个，\r\n一个是：闺蜜看不上。另一个是：闺蜜看上了。"},{"unixtime":1418814837,"updatetime":"2014-12-17 19:13:57","hashId":"20670bc096a2448b5d78c66746c930b6","content":"老师讲完课后，问道\r\n\u201c同学们，你们还有什么问题要问吗？\u201d\r\n这时，班上一男同学举手，\r\n\u201c老师，这节什么课？\u201d"}]}
 * reason : Success
 * error_code : 0
 */

    private int error_code;

    private String reason;

    private FuntextResult result;

    public FuntextResult getResult() {
        return result;
    }

    public void setResult(FuntextResult result) {
        this.result = result;
    }

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

    public static class FuntextResult{

        private List<DataEntity> data;

        public List<DataEntity> getData() {
            return data;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

       public static class  DataEntity{

            private String content;

            private String hashId;

            private int unixtime;

            private String updatetime;

           public DataEntity(String content, String hashId, int unixtime, String updatetime) {
               this.content = content;
               this.hashId = hashId;
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
    }

}
