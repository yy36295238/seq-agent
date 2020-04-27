package com.seq;

/**
 * @Author yangyu
 * @create 2020/4/23 下午5:21
 */
public class Chain {

    private String preName;
    private String nextName;
    private String method;

    public Chain() {
    }

    public Chain(String preName, String nextName, String method) {
        this.preName = preName;
        this.nextName = nextName;
        this.method = method;
    }

    public static Chain put(String preName, String nextName, String method) {
        return new Chain(preName, nextName, method);
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }

    public String preShortName() {
        return StringUtil.last(this.preName);
    }

    public String nextShortName() {
        return StringUtil.last(this.nextName);
    }

    public String getMethod() {
        return method;
    }

    public String formatReq() {
        return String.format("%s->%s: %s()", this.preShortName(), this.nextShortName(), this.getMethod());
    }

    public String formatRes() {
        return String.format("%s-->%s: response", this.preShortName(), this.nextShortName());
    }

    public boolean isSame() {
        return this.preShortName().equals(this.nextShortName());
    }
}
