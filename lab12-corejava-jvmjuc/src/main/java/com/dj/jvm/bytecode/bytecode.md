Java 字节码技术案例
========================

## HelloByteCode 

- 源代码
    ```java
    
    public class HelloByteCode {
        public static void main(String[] args) {
            HelloByteCode byteCode = new HelloByteCode();
        }    
    }
    ```
  - 指定目录下，执行 `javac` 指令 ``javac HelloByteCode.java`` ，进行编译
  
  _`javac` 工具默认开启了优化功能, 生成的字节码中没有局部变量表(`LocalVariableTable`)，相当于局部变量名称被擦除。如果需要这些调试信息, 在编译时请加上 -g 选项。_

  - 指定目录下，执行 `javap` 指令，``javap -c HelloByteCode`` 或 ``javap -c HelloByteCode.class``进行反反汇编, 获取字节码清单：
      ```text
      Compiled from "HelloByteCode.java"
      public class bytecode.com.jvm.HelloByteCode {
        public bytecode.com.jvm.HelloByteCode();
            Code:
            0: aload_0
            1: invokespecial #1                  // Method java/lang/Object."<init>":()V
            4: return
    
        public static void main(java.lang.String[]);
            Code:
            0: new           #2                  // class com/dj/jvm/bytecode/HelloByteCode
            3: dup
            4: invokespecial #3                  // Method "<init>":()V
            7: astore_1
            8: return
      }
      ```
  - `HelloByteCode` 字节码解读
    ```text
      public class bytecode.com.jvm.HelloByteCode {
        // 默认的无参构造方法
        public bytecode.com.jvm.HelloByteCode();
            Code:
            0: aload_0
            // 构造方法调用 super 类（Object）的构造方法进行初始化
            1: invokespecial #1                  // Method java/lang/Object."<init>":()V
            4: return
    
        public static void main(java.lang.String[]);
            Code:
            // new 一个对象
            0: new           #2                  // class com/dj/jvm/bytecode/HelloByteCode
            3: dup
            4: invokespecial #3                  // Method "<init>":()V
            7: astore_1
            8: return
      }
      ```
  - 使用 `javap -v HelloByteCode.class`，输出详细信息，`javap` 更多用法查看 `java -help`
    ```text
        Last modified 2023年9月2日; size 460 bytes
        MD5 checksum 7e595600f53870de0ca4dc3e4e61ebef
        Compiled from "HelloByteCode.java"
        public class bytecode.com.jvm.HelloByteCode
           // jdk版本 55.0 --> 11.0javap
           minor version: 0
           major version: 55
           // 访问标志符，其中 ACC_SUPER 是 jdk1.1引入的，用于解决 invokespecial 指令调用 super 类方法的问题
           flags: (0x0021) ACC_PUBLIC, ACC_SUPER
           this_class: #2                          // com/dj/jvm/bytecode/HelloByteCode
           super_class: #4                         // java/lang/Object
           interfaces: 0, fields: 0, methods: 2, attributes: 1
        Constant pool:
           // #1、 #2等为常量编号，可以在该文件的其他地方引用；“=” ：分隔符；
           // Methodref：这个常量指向的是一个方法，类指向#4，方法指向#19
           #1 = Methodref          #4.#19         // java/lang/Object."<init>":()V
           #2 = Class              #20            // com/dj/jvm/bytecode/HelloByteCode
           #3 = Methodref          #2.#19         // com/dj/jvm/bytecode/HelloByteCode."<init>":()V
           #4 = Class              #21            // java/lang/Object
           #5 = Utf8               <init>
           #6 = Utf8               ()V
           #7 = Utf8               Code
           #8 = Utf8               LineNumberTable
           #9 = Utf8               LocalVariableTable
           #10 = Utf8               this
           #11 = Utf8               Lcom/dj/jvm/bytecode/HelloByteCode;
           #12 = Utf8               main
           #13 = Utf8               ([Ljava/lang/String;)V
           #14 = Utf8               args
           #15 = Utf8               [Ljava/lang/String;
           #16 = Utf8               byteCode
           #17 = Utf8               SourceFile
           #18 = Utf8               HelloByteCode.java
           #19 = NameAndType        #5:#6          // "<init>":()V
           #20 = Utf8               com/dj/jvm/bytecode/HelloByteCode
           #21 = Utf8               java/lang/Object
       {
           // 默认的无参构造方法
           public bytecode.com.jvm.HelloByteCode();
             descriptor: ()V
             flags: (0x0001) ACC_PUBLIC
             Code:
                stack=1, locals=1, args_size=1
                   0: aload_0
                   1: invokespecial #1                  // Method java/lang/Object."<init>":()V
                   4: return
               LineNumberTable:
                   line 3: 0
               LocalVariableTable:
                   Start  Length  Slot  Name   Signature
                    0       5     0     this   Lcom/dj/jvm/bytecode/HelloByteCode;
            
             // main 方法
             public static void main(java.lang.String[]);
               // 小括号内是入参信息，左方括号表述数组，java/lang/String是类名称，V 表示方法的返回值是 void，
               descriptor: ([Ljava/lang/String;)V
               // 方法访问标识 public、static
               flags: (0x0009) ACC_PUBLIC, ACC_STATIC
               Code:
                   stack=2, locals=2, args_size=1
                      0: new           #2                  // class com/dj/jvm/bytecode/HelloByteCode
                      3: dup
                      4: invokespecial #3                  // Method "<init>":()V
                      7: astore_1
                      8: return
                  LineNumberTable:
                      line 6: 0
                      line 8: 8
                  LocalVariableTable:
                      Start  Length  Slot  Name   Signature
                        0       9     0    args   [Ljava/lang/String;
                        8       1     1   byteCode   Lcom/dj/jvm/bytecode/HelloByteCode;
       }  
      ```
一般地，把一个方法的修饰符+名称+参数类型清单+返回值类型，合在一起叫“方法签名”，即这些信息可以完整的表示一个方法。
