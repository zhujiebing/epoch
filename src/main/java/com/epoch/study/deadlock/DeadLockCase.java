package com.epoch.study.deadlock;


import java.util.concurrent.TimeUnit;

/**
 * @description: 死锁编码
 * @author: zjbing
 * @create: 2019-12-17 17:40
 **/
public class DeadLockCase {

    public static void main(String[] args) {
         String lockA = "lockA";

         String lockB = "lockB";

        new Thread(()->{
            try {
                holdLock(lockA,lockB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                holdLock(lockB,lockA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }

    private static void holdLock(String lock1,String lock2) throws InterruptedException {
        //持有锁A后，尝试持有锁B   ***********重点*************
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+"\t持有锁"+lock1+"，尝试获取锁"+lock2);
            TimeUnit.SECONDS.sleep(1);

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"\t尝试获取锁"+lock2);
            }
        }
    }

}


/**
 * Microsoft Windows [版本 10.0.17134.1184]
 * (c) 2018 Microsoft Corporation。保留所有权利。
 *
 * E:\study\mygithub\other\epoch>jps -l
 * 15600 sun.tools.jps.Jps
 * 16256 com.epoch.study.deadlock.DeadLockCase2
 * 4540
 * 8268 org.jetbrains.idea.maven.server.RemoteMavenServer36
 *
 * E:\study\mygithub\other\epoch>jstack 16256
 * 2019-12-18 09:26:27
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.40-b25 mixed mode):
 *
 * "DestroyJavaVM" #13 prio=5 os_prio=0 tid=0x0000000002c63800 nid=0x2bac waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "B" #12 prio=5 os_prio=0 tid=0x000000001d92a000 nid=0x3cfc waiting for monitor entry [0x000000001e4af000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.epoch.study.deadlock.HoldLockThread.run(DeadLockCase2.java:34)
 *         - waiting to lock <0x000000076b508cb0> (a java.lang.String)
 *         - locked <0x000000076b508ce8> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * "A" #11 prio=5 os_prio=0 tid=0x000000001d927800 nid=0x21bc waiting for monitor entry [0x000000001e3ae000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.epoch.study.deadlock.HoldLockThread.run(DeadLockCase2.java:34)
 *         - waiting to lock <0x000000076b508ce8> (a java.lang.String)
 *         - locked <0x000000076b508cb0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * "Service Thread" #10 daemon prio=9 os_prio=0 tid=0x000000001d84c000 nid=0x13ec runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C1 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001d846000 nid=0x1e84 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001d845000 nid=0x3f50 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001d7c6000 nid=0x3e60 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001d75d000 nid=0x3f2c runnable [0x000000001ddae000]
 *    java.lang.Thread.State: RUNNABLE
 *         at java.net.SocketInputStream.socketRead0(Native Method)
 *         at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
 *         at java.net.SocketInputStream.read(SocketInputStream.java:170)
 *         at java.net.SocketInputStream.read(SocketInputStream.java:141)
 *         at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
 *         at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
 *         at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
 *         - locked <0x000000076b616be0> (a java.io.InputStreamReader)
 *         at java.io.InputStreamReader.read(InputStreamReader.java:184)
 *         at java.io.BufferedReader.fill(BufferedReader.java:161)
 *         at java.io.BufferedReader.readLine(BufferedReader.java:324)
 *         - locked <0x000000076b616be0> (a java.io.InputStreamReader)
 *         at java.io.BufferedReader.readLine(BufferedReader.java:389)
 *         at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)
 *
 * "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001c372800 nid=0x4c8 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001c371800 nid=0x2358 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000002d59000 nid=0x3f88 in Object.wait() [0x000000001d6af000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x000000076b206f58> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 *         - locked <0x000000076b206f58> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 *         at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)
 *
 * "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000002d50800 nid=0x2b5c in Object.wait() [0x000000001d5ae000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x000000076b206998> (a java.lang.ref.Reference$Lock)
 *         at java.lang.Object.wait(Object.java:502)
 *         at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:157)
 *         - locked <0x000000076b206998> (a java.lang.ref.Reference$Lock)
 *
 * "VM Thread" os_prio=2 tid=0x000000001c327000 nid=0x3e58 runnable
 *
 * "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000002c78800 nid=0x3c08 runnable
 *
 * "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x0000000002c7a000 nid=0x2bcc runnable
 *
 * "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x0000000002c7c000 nid=0x1bb0 runnable
 *
 * "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x0000000002c7d800 nid=0x2a64 runnable
 *
 * "VM Periodic Task Thread" os_prio=2 tid=0x000000001d891000 nid=0x2a7c waiting on condition
 *
 * JNI global references: 33
 *
 *
 * Found one Java-level deadlock:
 * =============================
 * "B":
 *   waiting to lock monitor 0x0000000002d56488 (object 0x000000076b508cb0, a java.lang.String),
 *   which is held by "A"
 * "A":
 *   waiting to lock monitor 0x0000000002d58dc8 (object 0x000000076b508ce8, a java.lang.String),
 *   which is held by "B"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "B":
 *         at com.epoch.study.deadlock.HoldLockThread.run(DeadLockCase2.java:34)
 *         - waiting to lock <0x000000076b508cb0> (a java.lang.String)
 *         - locked <0x000000076b508ce8> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 * "A":
 *         at com.epoch.study.deadlock.HoldLockThread.run(DeadLockCase2.java:34)
 *         - waiting to lock <0x000000076b508ce8> (a java.lang.String)
 *         - locked <0x000000076b508cb0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * Found 1 deadlock.
 *
 *
 * E:\study\mygithub\other\epoch>jps -l
 * 14752 com.epoch.study.deadlock.DeadLockCase2
 * 15552 sun.tools.jps.Jps
 * 11100 com.epoch.study.deadlock.DeadLockCase
 * 14220 org.jetbrains.jps.cmdline.Launcher
 * 4540
 * 8268 org.jetbrains.idea.maven.server.RemoteMavenServer36
 *
 * E:\study\mygithub\other\epoch>jstack 11100
 * 2019-12-18 09:36:57
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.40-b25 mixed mode):
 *
 * "DestroyJavaVM" #13 prio=5 os_prio=0 tid=0x0000000002ce3800 nid=0x271c waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "B" #12 prio=5 os_prio=0 tid=0x000000001e374800 nid=0x81c waiting for monitor entry [0x000000001ed4f000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.epoch.study.deadlock.DeadLockCase.process(DeadLockCase.java:42)
 *         - waiting to lock <0x000000076b50a628> (a java.lang.String)
 *         - locked <0x000000076b50a660> (a java.lang.String)
 *         at com.epoch.study.deadlock.DeadLockCase.lambda$main$1(DeadLockCase.java:28)
 *         at com.epoch.study.deadlock.DeadLockCase$$Lambda$2/189568618.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * "A" #11 prio=5 os_prio=0 tid=0x000000001e373800 nid=0x18cc waiting for monitor entry [0x000000001ec4f000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.epoch.study.deadlock.DeadLockCase.process(DeadLockCase.java:42)
 *         - waiting to lock <0x000000076b50a660> (a java.lang.String)
 *         - locked <0x000000076b50a628> (a java.lang.String)
 *         at com.epoch.study.deadlock.DeadLockCase.lambda$main$0(DeadLockCase.java:20)
 *         at com.epoch.study.deadlock.DeadLockCase$$Lambda$1/1915910607.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * "Service Thread" #10 daemon prio=9 os_prio=0 tid=0x000000001d8fa800 nid=0x2a4 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C1 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001d88a800 nid=0x408 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001d87e000 nid=0x24ac waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001d87b800 nid=0x3c78 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001d855000 nid=0x6a8 runnable [0x000000001de4e000]
 *    java.lang.Thread.State: RUNNABLE
 *         at java.net.SocketInputStream.socketRead0(Native Method)
 *         at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
 *         at java.net.SocketInputStream.read(SocketInputStream.java:170)
 *         at java.net.SocketInputStream.read(SocketInputStream.java:141)
 *         at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
 *         at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
 *         at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
 *         - locked <0x000000076b6166f8> (a java.io.InputStreamReader)
 *         at java.io.InputStreamReader.read(InputStreamReader.java:184)
 *         at java.io.BufferedReader.fill(BufferedReader.java:161)
 *         at java.io.BufferedReader.readLine(BufferedReader.java:324)
 *         - locked <0x000000076b6166f8> (a java.io.InputStreamReader)
 *         at java.io.BufferedReader.readLine(BufferedReader.java:389)
 *         at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)
 *
 * "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001c3ee000 nid=0x554 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001c3fc800 nid=0x2430 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000002dd8800 nid=0x2c54 in Object.wait() [0x000000001d74f000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x000000076b206f58> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 *         - locked <0x000000076b206f58> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 *         at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)
 *
 * "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000002dd0800 nid=0x2fe4 in Object.wait() [0x000000001d64f000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x000000076b206998> (a java.lang.ref.Reference$Lock)
 *         at java.lang.Object.wait(Object.java:502)
 *         at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:157)
 *         - locked <0x000000076b206998> (a java.lang.ref.Reference$Lock)
 *
 * "VM Thread" os_prio=2 tid=0x000000001c3a7000 nid=0x3e60 runnable
 *
 * "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000002cf8800 nid=0x3dd8 runnable
 *
 * "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x0000000002cfa000 nid=0x2648 runnable
 *
 * "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x0000000002cfc000 nid=0x2614 runnable
 *
 * "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x0000000002cfd800 nid=0x394 runnable
 *
 * "VM Periodic Task Thread" os_prio=2 tid=0x000000001d8fb800 nid=0x3c1c waiting on condition
 *
 * JNI global references: 329
 *
 *
 * Found one Java-level deadlock:
 * =============================
 * "B":
 *   waiting to lock monitor 0x0000000002dd5cf8 (object 0x000000076b50a628, a java.lang.String),
 *   which is held by "A"
 * "A":
 *   waiting to lock monitor 0x000000001e3688a8 (object 0x000000076b50a660, a java.lang.String),
 *   which is held by "B"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "B":
 *         at com.epoch.study.deadlock.DeadLockCase.process(DeadLockCase.java:42)
 *         - waiting to lock <0x000000076b50a628> (a java.lang.String)
 *         - locked <0x000000076b50a660> (a java.lang.String)
 *         at com.epoch.study.deadlock.DeadLockCase.lambda$main$1(DeadLockCase.java:28)
 *         at com.epoch.study.deadlock.DeadLockCase$$Lambda$2/189568618.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:745)
 * "A":
 *         at com.epoch.study.deadlock.DeadLockCase.process(DeadLockCase.java:42)
 *         - waiting to lock <0x000000076b50a660> (a java.lang.String)
 *         - locked <0x000000076b50a628> (a java.lang.String)
 *         at com.epoch.study.deadlock.DeadLockCase.lambda$main$0(DeadLockCase.java:20)
 *         at com.epoch.study.deadlock.DeadLockCase$$Lambda$1/1915910607.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:745)
 **/