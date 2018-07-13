1. 写VerifierTest这个类。
2. 运行javac，得到class文件，用javap -c VerifierTest得到：
```
Warning: Binary file VerifierTest contains com.kkdz.test.changeByteCode.VerifierTest
Compiled from "VerifierTest.java"
public class com.kkdz.test.changeByteCode.VerifierTest {
  public com.kkdz.test.changeByteCode.VerifierTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  static int fun();
    Code:
       0: iconst_1
       1: istore_0
       2: iconst_2
       3: istore_1
       4: iload_0
       5: iload_1
       6: iadd
       7: istore_2
       8: iload_2
       9: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: invokestatic  #3                  // Method fun:()I
       6: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
       9: return
}

```
3. 用16进制编辑器(HxD)，找开字节码文件。
4. 找到04 3B 05 3C 1A 1B 60 3D 1C AC
	
这是编译器编译后的文件对应的fun()方法里的程序，其对应关系：

```
0 iconst_1 04
1 istore_0 3B
2 iconst_2 05
3 istore_1 3C
4 iload_0  1A
5 iload_1  1B
6 iadd     60
7 istore_2 3D
8 iload_2  1C
9 ireturn  AC
```

5. 用16进制编辑器，把第3步改成第1步一样的，3B。这样，相当于对m初始化了两次。使用javap -c VerifierTest得到：
```
Warning: Binary file VerifierTest contains com.kkdz.test.changeByteCode.Verifier                                                                                                                                                                                               Test
Compiled from "VerifierTest.java"
public class com.kkdz.test.changeByteCode.VerifierTest {
  public com.kkdz.test.changeByteCode.VerifierTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":                                                                                                                                                                                               ()V
       4: return

  static int fun();
    Code:
       0: iconst_1
       1: istore_0
       2: iconst_2
       3: istore_0
       4: iload_0
       5: iload_1
       6: iadd
       7: istore_2
       8: iload_2
       9: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/                                                                                                                                                                                               io/PrintStream;
       3: invokestatic  #3                  // Method fun:()I
       6: invokevirtual #4                  // Method java/io/PrintStream.printl                                                                                                                                                                                               n:(I)V
       9: return
}

```
6. 用java ....VerifierTest来运行，可以看到如下结果：
```
java.lang.VerifyError: Bad local variable type
Exception Details:
  Location:
    com/kkdz/test/changeByteCode/VerifierTest.fun()I @5: iload_1
  Reason:
    Type top (current frame, locals[1]) is not assignable to integer
  Current Frame:
    bci: @5
    flags: { }
    locals: { integer }
    stack: { integer }
  Bytecode:
    0x0000000: 043b 053b 1a1b 603d 1cac

        at java.lang.Class.getDeclaredMethods0(Native Method)
        at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
        at java.lang.Class.privateGetMethodRecursive(Class.java:3048)
        at java.lang.Class.getMethod0(Class.java:3018)
        at java.lang.Class.getMethod(Class.java:1784)
        at sun.launcher.LauncherHelper.validateMainClass(LauncherHelper.java:544                                                                                                                                                                                               )
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:526)
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main"
```
这就说明有问题。
7. 使用java -noverify ....VerifierTest来运行，可以看到如下结果：
```
$ java -noverify com.kkdz.test.changeByteCode.VerifierTest
2
```