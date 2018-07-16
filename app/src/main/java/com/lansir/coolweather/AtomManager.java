package com.lansir.coolweather;

import android.provider.Settings;

import com.meelive.ingkee.base.utils.GlobalContext;
import com.meelive.ingkee.base.utils.log.Logger;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AtomManager {
    private final int PROTO = 8; // 之前是7   8是连麦放开的proto

    private AtomManager() {
    }

    public static AtomManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final AtomManager instance = new AtomManager();
    }

    /**
     * 获取原子信息
     */
//    public String getAtom() {
//
//        //        try {
////            sb.append("vv=").append(SDKToolkit.Version()).append("&");
////        } catch (NoClassDefFoundError e) {
////            e.printStackTrace();
////        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
////            unsatisfiedLinkError.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } catch (Throwable e) {
////            e.printStackTrace();
////        }
//
//        return
//                "lc=" + getLicenceId() +
//                "&" + "cv=" + getClientVersion() +
//                "&" + "cc=" + getChannelCode() +
//                "&" + "ua=" + getUaEncoder() +
//                "&" + "uid=" + getUserId() +
//                "&" + "sid=" + getSessionId() +
//                "&" + "devi=" + PhoneInfoConfig.devi +
//                "&" + "imsi=" + PhoneInfoConfig.imsi +
//                "&" + "imei=" + PhoneInfoConfig.imei +
//                "&" + "icc=" + PhoneInfoConfig.iccid +
//                "&" + "conn=" + Network.getNetworkName(PhoneInfoConfig.netType) +
//                "&" + "aid=" + getAndroidId() +
//                "&" + "osversion=" + "android_" + Integer.toString(android.os.Build.VERSION.SDK_INT) +
//                "&" + "mtid=" + paramEncoder(getWifiMtid()) +
//                "&" + "mtxid=" + paramEncoder(getWifiMtxid()) +
//                "&" + "proto=" + PROTO +
//                "&smid=" + paramEncoder(getSmId()) +
//                "&logid=" + paramEncoder(getLogId());
//    }

//    private String getLogId() {
//        return ServiceInfoManager.getInstance().getLogId();
//    }
//
//    private String getChannelCode() {
//        return InkeConfig.getChannelCode();
//    }
//
//    private String getClientVersion() {
//        return InkeConfig.getClientVersion();
//    }
//
//    private String getLicenceId() {
//        return InkeConfig.getLicenceId();
//    }

    /**
     * 获取埋点原子信息
     */
//    public String getLogAtom() {
//
////        try {
////            sb.append("vv=").append(SDKToolkit.Version()).append("&");
////        } catch (NoClassDefFoundError e) {
////            e.printStackTrace();
////        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
////            unsatisfiedLinkError.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } catch (Throwable e) {
////            e.printStackTrace();
////        }
//        return "lc=" + getLicenceId() +
//                "&" + "cv=" + getClientVersion() +
//                "&" + "cc=" + getChannelCode() +
//                "&" + "ua=" + getUaEncoder() +
//                "&" + "uid=" + getUserId() +
//                "&" + "sid=" + getSessionId() +
//                "&" + "devi=" + PhoneInfoConfig.devi +
//                "&" + "imsi=" + PhoneInfoConfig.imsi +
//                "&" + "imei=" + PhoneInfoConfig.imei +
//                "&" + "icc=" + PhoneInfoConfig.iccid +
//                "&" + "conn=" + Network.getNetworkName(PhoneInfoConfig.netType) +
//                "&" + "aid=" + getAndroidId() +
//                "&" + "mtid=" + (paramEncoder(getWifiMtid())) +
//                "&" + "mtxid=" + paramEncoder(getWifiMtxid()) +
//                "&" + "osversion=" + "android_" + android.os.Build.VERSION.SDK_INT +
//                "&" + "proto=" + PROTO +
//                "&smid=" + paramEncoder(getSmId()) +
//                "&" + "tg=" + getAndroidId() +
//                "&logid=" + paramEncoder(getLogId()) +
//                "&" + "cpu=" + PhoneInfoConfig.cpuInfo +
//                "&" + "ram=" + PhoneInfoConfig.totalMemorySize;
//    }

    /**
     * 异步获得原子参数，因为增加参数是在异步进行，所以增加锁
     *
     * @return
     */
//    public synchronized Map<String, String> getAtomParamsMapForNewLog() {
//        HashMap<String, String> httpParams = new HashMap<>();
//
//        httpParams.put("lc", getLicenceId());
//        httpParams.put("cv", getClientVersion());
//        httpParams.put("cc", getChannelCode());
//        httpParams.put("ua", getUa());
//        httpParams.put("uid", getUserId() + "");
//        httpParams.put("sid", getSessionId());
//        httpParams.put("devi", PhoneInfoConfig.devi);
//        httpParams.put("imsi", PhoneInfoConfig.imsi);
//        httpParams.put("imei", PhoneInfoConfig.imei);
//        httpParams.put("icc", PhoneInfoConfig.iccid);
//        httpParams.put("conn", Network.getNetworkName(PhoneInfoConfig.netType));
////        try {
////            httpParams.put("vv", SDKToolkit.Version());
////        } catch (NoClassDefFoundError e) {
////            e.printStackTrace();
////        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
////            unsatisfiedLinkError.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } catch (Throwable e) {
////            e.printStackTrace();
////        }
//        httpParams.put("aid", getAndroidId());
//        httpParams.put("osversion", "android_" + android.os.Build.VERSION.SDK_INT);
//        httpParams.put("proto", PROTO + "");
//        httpParams.put("smid", getSmId());
//        httpParams.put("mtid", getWifiMtid());
//        httpParams.put("mtxid", getWifiMtxid());
//        httpParams.put("logid", getLogId());
//        httpParams.put("cpu", PhoneInfoConfig.cpuInfoWithoutUrlEncode);
//        httpParams.put("ram", String.valueOf(PhoneInfoConfig.totalMemorySize));
//        return httpParams;
//    }


    /**
     * 异步获得原子参数，因为增加参数是在异步进行，所以增加锁
     *
     * @return
     */
    public synchronized Map<String, String> getAtomParamsMap() {
        HashMap<String, String> httpParams = new HashMap<>();

        String androidId = getAndroidId();
//        httpParams.put("lc", getLicenceId());
//        httpParams.put("cv", getClientVersion());
//        httpParams.put("cc", getChannelCode());
        httpParams.put("ua", getUa());
//        httpParams.put("uid", getUserId() + "");
//        httpParams.put("sid", getSessionId());
//        httpParams.put("devi", PhoneInfoConfig.devi);
//        httpParams.put("imsi", PhoneInfoConfig.imsi);
//        httpParams.put("imei", PhoneInfoConfig.imei);
//        httpParams.put("icc", PhoneInfoConfig.iccid);
//        httpParams.put("conn", Network.getNetworkName(PhoneInfoConfig.netType));
//        try {
//            httpParams.put("vv", SDKToolkit.Version());
//        } catch (NoClassDefFoundError e) {
//            e.printStackTrace();
//        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
//            unsatisfiedLinkError.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
        httpParams.put("aid", androidId);
        httpParams.put("osversion", "android_" + android.os.Build.VERSION.SDK_INT);
        httpParams.put("proto", PROTO + "");
//        httpParams.put("smid", getSmId());
//        httpParams.put("mtid", getWifiMtid());
//        httpParams.put("mtxid", getWifiMtxid());
//        httpParams.put("logid", getLogId());
        return httpParams;
    }

    public synchronized String getAtomInJson() {
        return new JSONObject(getAtomParamsMap()).toString();
    }

    private String getUa() {
        String ua = "";
        try {
            ua = PhoneInfoConfig.userAgent.replace(" ", "");
        } catch (Exception e) {
            Logger.e( "getUa Error");
        }
        return ua;
    }

    private String getUaEncoder() {
        String ua = "";
        try {
            ua = URLEncoder.encode(PhoneInfoConfig.userAgent.replace(" ", ""), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.e( "getUaEncoder Error");
        }
        return ua;
    }

    //获取userId
//    private int getUserId() {
//        return UserManager.ins().getUid();
//    }
//
//    //获取sessionId
//    private String getSessionId() {
//        return UserManager.ins().getLoginSessionId();
//    }
//
//    //获取数盟id
//    private String getSmId() {
//        return ShuzilmManager.getImpl().getSmid();
//    }

    private String paramEncoder(String param) {
        String str = param;
        try {
            str = URLEncoder.encode(param, "UTF-8");
        } catch (Exception e) {
            Logger.e( "paramEncoder Error:%s", param);
        }
        return str;
    }

//    private String getWifiMtid() {
//        String ret = "";
//        String[] wifis = getWifiState();
//        if (wifis != null && wifis.length >= 2) {
//            ret = wifis[0];
//        }
//        return ret;
//    }
//
//    private String getWifiMtxid() {
//        String ret = "";
//        String[] wifis = getWifiState();
//        if (wifis != null && wifis.length >= 2) {
//            ret = wifis[1];
//        }
//        return ret;
//    }

    /**
     * 获取 wifi 信息
     *
     * @return mtid, mtxid
     */
//    private static String[] getWifiState() {
//        String mtid = "";
//        String mtxid = "";
//        try {
//
//            WifiInfo wifiInfo = GlobalContext.getWifiManager().getConnectionInfo();
//
//            if (wifiInfo == null) {
//                return new String[]{mtid, mtxid};
//            }
//
//            String ssid = wifiInfo.getSSID();
//            String bssid = wifiInfo.getBSSID();
//            if (!StringUtils.isEmpty(ssid)) {
//                mtid = DigestUtils.md5Hex(ssid.getBytes());
//            }
//            if (!StringUtils.isEmpty(bssid)) {
//                if (bssid.contains(":")) {
//                    mtxid = bssid.replaceAll(":", "");
//                }
//            }
//            if (DEBUG) {
//                Logger.d("getWifiState() ssid: %s, bssid: %s, mtid : %s, mtxid: %s", mtxid, ssid, bssid, mtid);
//            }
//            return new String[]{mtid, mtxid};
//        } catch (Throwable e) {
//            e.printStackTrace();
//            CrashReport.postCatchedException(e);
//        }
//        return new String[]{mtid, mtxid};
//    }

    private static String getAndroidId() {
        return Settings.Secure.getString(GlobalContext.getAppContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

//    public String addAtom(String url) {
//        if (!TextUtils.isEmpty(url)) {
//            if (url.contains("?")) {
//                url = url.concat("&").concat(AtomManager.getInstance().getAtom());
//            }else {
//                url = url.concat("?").concat(AtomManager.getInstance().getAtom());
//            }
//        }
//        return url;
//    }
}

