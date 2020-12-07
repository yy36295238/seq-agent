package test;

import java.text.MessageFormat;

/**
 * @Author yangyu
 * @create 2020/4/27 上午11:30
 */
public class JavaAgentPathTest {

    public static void main(String[] args) {
        make();
//        mybatis();
    }

    public static void make() {
        String cmd = "-javaagent:%s=scanPackages=%s&mainClass=%s&seqLogFilePath=%s";
        String agentPath = "/Users/yangyu/workspace/yyself/seq-agent/target/seq-agent.jar";
        String scanPackages = "com.agent.demo";
        String mainClass = "AgentDemoApplication";
        String logPath = "/Users/yangyu/Desktop/seq.txt";
        System.err.println(String.format(cmd, agentPath, scanPackages, mainClass, logPath));
    }

    public static void mybatis() {
        String cmd = "-javaagent:%s=scanPackages=%s&mainClass=%s&seqLogFilePath=%s";
        String agentPath = "/Users/yangyu/workspace/yyself/seq-agent/target/seq-agent.jar";
        String scanPackages = "com.kot.kotmybatis.biz.mysql.biz";
        String mainClass = "KotMybatisApplication";
        String logPath = "/Users/yangyu/Desktop/seq.text";
        System.err.println(String.format(cmd, agentPath, scanPackages, mainClass, logPath));
    }

}
