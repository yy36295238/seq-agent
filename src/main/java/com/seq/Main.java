package com.seq;

import java.lang.instrument.Instrumentation;

/**
 * @Author yangyu
 * @create 2020/4/23 下午5:37
 */
public class Main {

    public static void premain(String agentOps, Instrumentation inst) {
        System.err.println("====Seq Agent Premain====");
        System.err.println("agentOps:" + agentOps);
        inst.addTransformer(new SeqTransformer(OpsParam.parse(agentOps)));
    }

}
