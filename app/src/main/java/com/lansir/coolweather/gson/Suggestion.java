package com.lansir.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * "suggestion" : {
 *     "comf" : {
 *         "txt" : "白天天气较热。。。"
 *     },
 *     "cw" : {
 *         "txt" : "不宜洗车。。。"
 *     },
 *     "sport" : {
 *         "txt" : "推荐室内运动。。。"
 *     }
 * }
 */
public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public class Sport {

        @SerializedName("txt")
        public String info;

    }
}
