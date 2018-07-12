package com.lansir.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * "now" : {
 *     "tmp" : "25",
 *     "cond" : {
 *         "txt" : "阵雨"
 *     }
 * }
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }

}
