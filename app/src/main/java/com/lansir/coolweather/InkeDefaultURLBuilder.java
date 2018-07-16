package com.lansir.coolweather;

import java.util.LinkedHashMap;
import java.util.Map;

public class InkeDefaultURLBuilder extends BaseInkeURLBuilder {

    @Override
    protected LinkedHashMap<String, String> getAtomHeaderMap(String url) {
        return null;
    }

    @Override
    protected Map<String, String> getAtomParamsMap() {
        return AtomManager.getInstance().getAtomParamsMap();
    }

    @Override
    public byte getReqType() {
        return REQ_TYPE_TEXT;
    }
}
