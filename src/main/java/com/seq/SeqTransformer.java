package com.seq;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Author yangyu
 * @create 2020/4/23 下午6:29
 */
public class SeqTransformer implements ClassFileTransformer {

    private OpsParam opsParam;

    public SeqTransformer(OpsParam opsParam) {
        this.opsParam = opsParam;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return enhance(className, classfileBuffer);
    }

    private byte[] enhance(String className, byte[] classfileBuffer) {
        className = className.replaceAll("/", ".");
        if (!containsClass(className)) {
            return classfileBuffer;
        }
        ClassPool pool = ClassPool.getDefault();
        try {
            System.err.println(className);
            pool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
            CtClass ctClass = pool.get(className);
            if (ctClass.isInterface() || ctClass.isAnnotation()) {
                return classfileBuffer;
            }

            CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            for (CtMethod ctMethod : declaredMethods) {
                if (!ctMethod.isEmpty()) {
                    ctMethod.insertBefore("com.seq.Seq.req(Thread.currentThread().getStackTrace()[2].getClassName(),Thread.currentThread().getStackTrace()[1].getClassName()" +
                            ",\"" + ctMethod.getName() + "\"" +
                            ",\"" + opsParam.getSeqLogFilePath() + "\"" +
                            ",\"" + opsParam.getExcludeKeyword() + "\"" +
                            ");");
                    ctMethod.insertAfter("com.seq.Seq.res(Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[2].getClassName()" +
                            ",\"" + ctMethod.getName() + "\"" +
                            ",\"" + opsParam.getSeqLogFilePath() + "\"" +
                            ",\"" + opsParam.getExcludeKeyword() + "\"" +
                            ");");
                }
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean containsClass(String className) {

        if (className.contains(opsParam.getMainClass()) || className.contains("$$")) {
            return false;
        }
        for (String scanPackage : opsParam.getScanPackages()) {
            if (className.startsWith(scanPackage)) {
                return true;
            }
        }
        return false;
    }
}
