package com.guoxiaotian.lifesecretary.model.bean;

/**
 * Created by Administrator on 2017/3/8.
 */
public class XingShiQiYuanBean {


    /**
     * result : {"xing":"百里","intro":"姓氏起源<BR>&nbsp;&nbsp;&nbsp; 出自姬姓，以封地为姓。周朝时，有姬姓虞国人，入秦后，授予百里作采邑，其后代子孙就以封地名为姓，称百里氏。<BR>　　以人名为姓，是春秋时秦国大夫百里奚的后代。周初，周武王封周太王古公亶父的二儿子虞仲的子孙在虞国（在今山西平陆县北）。春秋时，虞仲有个后人叫奚，因住在百里乡，又称百里奚，他在虞国任大夫。公元前655年，虞国被晋国所灭，百里奚和虞君都当了晋国的俘虏，成了奴隶。这时，秦穆公向晋献公求亲，晋献公就把女儿嫁给他，同时把百里奚也作为陪嫁的奴仆之一送往秦国。百里奚不甘心做奴隶，就在半路上逃跑了，可不久又被楚人捉去，成了楚国的奴隶。秦穆公是个有雄心壮志的国君，一直在收罗人才，他听说百里奚是个有才干的人之后，决心把他追回来。他怕用重金去赎会引起楚国对百里奚的重视，就按照当时奴隶的身价，用五张羊皮把他作为逃奴赎回来秦穆公同百里奚交谈后，对他大加赞赏，封他为大夫。百里奚的后代子孙就以他的名字命姓，称百里氏。&nbsp;<BR>历史名人<BR>&nbsp;&nbsp;&nbsp; 百里嵩：汉代徐州刺史。相传，当时天旱，百里嵩行仗所过之处，便有雨水降下，号刺史雨。"}
     * reason : Succes
     * error_code : 0
     */
    private ResultEntity result;
    private String reason;
    private int error_code;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public class ResultEntity {
        /**
         * xing : 百里
         * intro : 姓氏起源<BR>&nbsp;&nbsp;&nbsp; 出自姬姓，以封地为姓。周朝时，有姬姓虞国人，入秦后，授予百里作采邑，其后代子孙就以封地名为姓，称百里氏。<BR>　　以人名为姓，是春秋时秦国大夫百里奚的后代。周初，周武王封周太王古公亶父的二儿子虞仲的子孙在虞国（在今山西平陆县北）。春秋时，虞仲有个后人叫奚，因住在百里乡，又称百里奚，他在虞国任大夫。公元前655年，虞国被晋国所灭，百里奚和虞君都当了晋国的俘虏，成了奴隶。这时，秦穆公向晋献公求亲，晋献公就把女儿嫁给他，同时把百里奚也作为陪嫁的奴仆之一送往秦国。百里奚不甘心做奴隶，就在半路上逃跑了，可不久又被楚人捉去，成了楚国的奴隶。秦穆公是个有雄心壮志的国君，一直在收罗人才，他听说百里奚是个有才干的人之后，决心把他追回来。他怕用重金去赎会引起楚国对百里奚的重视，就按照当时奴隶的身价，用五张羊皮把他作为逃奴赎回来秦穆公同百里奚交谈后，对他大加赞赏，封他为大夫。百里奚的后代子孙就以他的名字命姓，称百里氏。&nbsp;<BR>历史名人<BR>&nbsp;&nbsp;&nbsp; 百里嵩：汉代徐州刺史。相传，当时天旱，百里嵩行仗所过之处，便有雨水降下，号刺史雨。
         */
        private String xing;
        private String intro;

        public void setXing(String xing) {
            this.xing = xing;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getXing() {
            return xing;
        }

        public String getIntro() {
            return intro;
        }
    }
}
