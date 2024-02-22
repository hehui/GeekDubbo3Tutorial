package com.hmilyylimh.cloud.compiler.asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * <h1>生成class直接的工具类。</h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2021-10-24
 */
public class AsmProxyUtils implements Opcodes {

    /**
     * <h2>创建代理对象。</h2>
     *
     * @param originClass：样例：UserBean.class
     * @return
     */
    public static Object newProxyInstance(Class originClass) throws Exception{
        String newClzNameSuffix = "Handler";
        byte[] classBytes = generateByteCode(originClass, newClzNameSuffix);

        // 可以想办法将 classBytes 存储为一个文件
        String filePath = AsmProxyUtils.class.getResource("/").getPath()
                + AsmProxyUtils.class.getPackage().toString().substring("package ".length()).replaceAll("\\.", "/");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath,
                originClass.getSimpleName() + newClzNameSuffix + ".class"));
        fileOutputStream.write(classBytes);
        fileOutputStream.close();

        // 还得把 classBytes 加载到 JVM 内存中去
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> loaderClass = Class.forName("java.lang.ClassLoader");
        Method defineClassMethod = loaderClass.getDeclaredMethod("defineClass",
                String.class,
                byte[].class,
                int.class,
                int.class);
        defineClassMethod.setAccessible(true);
        Object respObject = defineClassMethod.invoke(loader, new Object[]{
                originClass.getName() + newClzNameSuffix,
                classBytes,
                0,
                classBytes.length
        });

        // 实例化对象
        return ((Class)respObject).newInstance();
    }

    /**
     * <h2>生成字节码的核心。</h2><br/>
     *
     * <li><h2>注意：接下来的重点就是如何用asm来动态产生一个 UserBeanHandler 类。</h2></li>
     *
     * @param originClass：样例：UserBean.class
     * @param newClzNameSuffix： 样例：Handler
     * @return
     */
    private static byte[] generateByteCode(Class originClass, String newClzNameSuffix) {
        String newClassSimpleNameAndSuffix = originClass.getSimpleName() + newClzNameSuffix + ".java";

        /**********************************************************************/
        // 利用 ASM 编写创建类文件头的相关信息
        /**********************************************************************/
        ClassWriter classWriter = new ClassWriter(0);
        /////////////////////////////////////////////////////////
        // class version 50.0 (50)
        // access flags 0x21
        // signature Ljava/lang/Object;Lcom/hmilyylimh/cloud/compiler/asm/Handler<Lcom/hmilyylimh/cloud/compiler/asm/UserBean;>;
        // declaration: com/hmilyylimh/cloud/compiler/asm/UserBeanHandler implements com.hmilyylimh.cloud.compiler.asm.UserBean<com.hmilyylimh.cloud.compiler.asm.UserBean>
        // public class com/hmilyylimh/cloud/compiler/asm/UserBeanHandler extends Ljava/lang/Object; implements com/hmilyylimh/cloud/compiler/asm/Handler {
        /////////////////////////////////////////////////////////
        classWriter.visit(
                V1_6,
                ACC_PUBLIC + ACC_SUPER,
                Type.getInternalName(originClass) + newClzNameSuffix,
                Type.getDescriptor(Object.class)+Type.getDescriptor(Handler.class).replace(";","")+"<"+Type.getDescriptor(originClass)+">;",
                Type.getDescriptor(Object.class),
                new String[]{ Type.getInternalName(Handler.class) }
        );
        /////////////////////////////////////////////////////////
        // UserBeanHandler.java
        /////////////////////////////////////////////////////////
        classWriter.visitSource(newClassSimpleNameAndSuffix, null);

        /**********************************************************************/
        // 创建构造方法
        /**********************************************************************/
        /////////////////////////////////////////////////////////
        // compiled from: UserBeanHandler.java
        // access flags 0x1
        // public <init>()V
        /////////////////////////////////////////////////////////
        MethodVisitor initMethodVisitor = classWriter.visitMethod(
                ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null
        );
        initMethodVisitor.visitCode();
        /////////////////////////////////////////////////////////
        // ALOAD 0
        // INVOKESPECIAL java/lang/Object.<init> ()V
        // RETURN
        /////////////////////////////////////////////////////////
        initMethodVisitor.visitVarInsn(ALOAD, 0);
        initMethodVisitor.visitMethodInsn(INVOKESPECIAL,
                Type.getInternalName(Object.class),
                "<init>",
                "()V"
                );
        initMethodVisitor.visitInsn(RETURN);
        /////////////////////////////////////////////////////////
        // MAXSTACK = 1
        // MAXLOCALS = 1
        /////////////////////////////////////////////////////////
        initMethodVisitor.visitMaxs(1, 1);
        initMethodVisitor.visitEnd();


        /**********************************************************************/
        // 创建 addBefore 方法
        /**********************************************************************/
        /////////////////////////////////////////////////////////
        // access flags 0x1
        // public addBefore(Lcom/hmilyylimh/cloud/compiler/asm/UserBean;)V
        /////////////////////////////////////////////////////////
        MethodVisitor addBeforeMethodVisitor = classWriter.visitMethod(
                ACC_PUBLIC,
                "addBefore",
                "(" + Type.getDescriptor(originClass) + ")V",
                null,
                null
        );
        addBeforeMethodVisitor.visitCode();

        /////////////////////////////////////////////////////////
        // ALOAD 1
        // INVOKEVIRTUAL com/hmilyylimh/cloud/compiler/asm/UserBean.getName ()Ljava/lang/String;
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitVarInsn(ALOAD, 1);
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(originClass),
                "getName",
                "()" + Type.getDescriptor(String.class));

        /////////////////////////////////////////////////////////
        // IFNULL L0
        // ALOAD 1
        // INVOKEVIRTUAL com/hmilyylimh/cloud/compiler/asm/UserBean.getName ()Ljava/lang/String;
        // INVOKEVIRTUAL java/lang/String.length ()I
        // IFLE L0
        /////////////////////////////////////////////////////////
        Label L0 = new Label();
        addBeforeMethodVisitor.visitJumpInsn(IFNULL, L0);
        addBeforeMethodVisitor.visitVarInsn(ALOAD, 1);
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(originClass),
                "getName",
                "()" + Type.getDescriptor(String.class));
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(String.class),
                "length",
                "()I");
        addBeforeMethodVisitor.visitJumpInsn(IFLE, L0);

        /**********************************************************************/
        // 接下来要干的事情就是：u.setName("#BEFORE#" + u.getName());
        /**********************************************************************/
        /////////////////////////////////////////////////////////
        // ALOAD 1
        // NEW java/lang/StringBuilder
        // DUP
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitVarInsn(ALOAD, 1);
        addBeforeMethodVisitor.visitTypeInsn(NEW, Type.getInternalName(StringBuilder.class));
        addBeforeMethodVisitor.visitInsn(DUP);

        /////////////////////////////////////////////////////////
        // INVOKESPECIAL java/lang/StringBuilder.<init> ()V
        // LDC "#BEFORE#"
        // INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitMethodInsn(INVOKESPECIAL,
                Type.getInternalName(StringBuilder.class),
                "<init>",
                "()V");
        addBeforeMethodVisitor.visitLdcInsn("#BEFORE#");
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(StringBuilder.class),
                "append",
                "("+ Type.getDescriptor(String.class) + ")" + Type.getDescriptor(StringBuilder.class));
        /////////////////////////////////////////////////////////
        // ALOAD 1
        // INVOKEVIRTUAL com/hmilyylimh/cloud/compiler/asm/UserBean.getName ()Ljava/lang/String;
        // INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
        // NVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
        // INVOKEVIRTUAL com/hmilyylimh/cloud/compiler/asm/UserBean.setName (Ljava/lang/String;)V
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitVarInsn(ALOAD, 1);
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(originClass),
                "getName",
                "()" + Type.getDescriptor(String.class));
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(StringBuilder.class),
                "append",
                "("+ Type.getDescriptor(String.class) + ")" + Type.getDescriptor(StringBuilder.class));
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(StringBuilder.class),
                "toString",
                "()" + Type.getDescriptor(String.class));
        addBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(originClass),
                "setName",
                "(" + Type.getDescriptor(String.class)+")V");

        /////////////////////////////////////////////////////////
        // L0
        // FRAME SAME
        // RETURN
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitLabel(L0);
        addBeforeMethodVisitor.visitFrame(F_SAME, 0, null, 0, null);
        addBeforeMethodVisitor.visitInsn(RETURN);
        /////////////////////////////////////////////////////////
        // LMAXSTACK = 3
        // MAXLOCALS = 2
        /////////////////////////////////////////////////////////
        addBeforeMethodVisitor.visitMaxs(3, 2);
        addBeforeMethodVisitor.visitEnd();

        /**********************************************************************/
        // 创建桥接 addBefore 方法
        /**********************************************************************/
        /////////////////////////////////////////////////////////
        // access flags 0x1041
        // public synthetic bridge addBefore(Ljava/lang/Object;)V
        /////////////////////////////////////////////////////////
        MethodVisitor bridgeAddBeforeMethodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_SYNTHETIC + ACC_BRIDGE,
                "addBefore",
                "(" + Type.getDescriptor(Object.class) + ")V",
                null,
                null
        );
        bridgeAddBeforeMethodVisitor.visitCode();
        /////////////////////////////////////////////////////////
        // ALOAD 0
        // ALOAD 1
        /////////////////////////////////////////////////////////
        bridgeAddBeforeMethodVisitor.visitVarInsn(ALOAD, 0);
        bridgeAddBeforeMethodVisitor.visitVarInsn(ALOAD, 1);

        /////////////////////////////////////////////////////////
        // CHECKCAST com/hmilyylimh/cloud/compiler/asm/UserBean
        // INVOKEVIRTUAL com/hmilyylimh/cloud/compiler/asm/UserBeanHandler.addBefore (Lcom/hmilyylimh/cloud/compiler/asm/UserBean;)V
        // RETURN
        /////////////////////////////////////////////////////////
        bridgeAddBeforeMethodVisitor.visitTypeInsn(CHECKCAST, Type.getInternalName(originClass));
        bridgeAddBeforeMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                Type.getInternalName(originClass) + newClzNameSuffix,
                "addBefore",
                "(" + Type.getDescriptor(originClass) + ")V");
        bridgeAddBeforeMethodVisitor.visitInsn(RETURN);
        /////////////////////////////////////////////////////////
        // MAXSTACK = 2
        // MAXLOCALS = 2
        /////////////////////////////////////////////////////////
        bridgeAddBeforeMethodVisitor.visitMaxs(2, 2);
        bridgeAddBeforeMethodVisitor.visitEnd();

        /**********************************************************************/
        // 创建结束
        /**********************************************************************/
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}