package com.seq;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yangyu
 * @create 2020/4/24 下午1:45
 */
public class OpsParam {

    private List<String> scanPackages;
    private String mainClass;
    private String excludeKeyword;
    private String seqLogFilePath;

    public static OpsParam parse(String ops) {
        return new OpsParam().parseParam(ops);
    }

    public OpsParam parseParam(String ops) {
        String[] params = ops.split("&");
        for (String param : params) {
            String[] kv = param.split("=");
            String key = kv[0];
            String val = kv[1];
            if ("scanPackages".equals(key)) {
                this.scanPackages = Arrays.asList(val.split(","));
            }
            if ("mainClass".equals(key)) {
                this.mainClass = val;
            }
            if ("seqLogFilePath".equals(key)) {
                this.seqLogFilePath = val;
            }
            if ("excludeKeyword".equals(key)) {
                this.excludeKeyword = val;
            }
        }
        System.err.println(this);
        return this;
    }

    public List<String> getScanPackages() {
        return scanPackages;
    }


    public String getMainClass() {
        return mainClass;
    }


    public String getSeqLogFilePath() {
        return seqLogFilePath;
    }

    public String getExcludeKeyword() {
        return excludeKeyword;
    }

    @Override
    public String toString() {
        return "OpsParam{" +
                "scanPackages=" + scanPackages +
                ", mainClass='" + mainClass + '\'' +
                ", excludeKeyword=" + excludeKeyword +
                ", seqLogFilePath='" + seqLogFilePath + '\'' +
                '}';
    }
}
