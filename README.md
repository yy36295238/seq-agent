# seq-agent
### 启动地址
> java -javaagent:D:\\workspace\\java\\my\\seq-agent\\target\\seq-agent.jar="scanPackages=com.agent.demo&mainClass=com.agent.demo.AgentDemoApplication&seqLogFilePath=C:\\\Users\\\admin\\\Desktop\\\my-seq.txt" -jar agent-demo.jar

* -javaagent = `D:\\workspace\\java\\my\\seq-agent\\target\\seq-agent.jar`，Agent文件路径
* scanPackages = `com.agent.demo`，扫描包路径
* mainClass = `com.agent.demo.AgentDemoApplication`,启动类
* seqLogFilePath = `C:\\\Users\\\admin\\\Desktop\\\my-seq.txt`，时序日志文件路径

