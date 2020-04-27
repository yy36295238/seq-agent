package com.seq;

/**
 * @Author yangyu
 * @create 2020/4/23 下午5:49
 */
public class Seq {


    public static void req(String preName, String nextName, String method, String seqLogFilePath, String excludeKeyword) {
        Chain chain = Chain.put(preName, nextName, method);

        boolean isAppend = true;
        if (excludeKeyword != null) {
            for (String keyword : excludeKeyword.split(",")) {
                if (preName.contains(keyword) || nextName.contains(keyword) || method.contains(keyword) || method.contains("$")) {
                    isAppend = false;
                    break;
                }
            }
        }
        if (isAppend) {
            TextUtils.writeAppend(seqLogFilePath, chain.formatReq());
        }
    }

    public static void res(String preName, String nextName, String method, String seqLogFilePath, String excludeKeyword) {
        Chain chain = Chain.put(preName, nextName, method);

        boolean isAppend = true;
        if (excludeKeyword != null) {
            for (String keyword : excludeKeyword.split(",")) {
                if (preName.contains(keyword) || nextName.contains(keyword) || method.contains(keyword) || method.contains("$")) {
                    isAppend = false;
                    break;
                }
            }
        }
        if (isAppend && !chain.isSame()) {
            TextUtils.writeAppend(seqLogFilePath, chain.formatRes());
        }
    }


}
