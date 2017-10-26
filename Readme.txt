古代の RMI プログラムの移行調査

○ Java7からJDK標準の policy によって、1024 より上の socket 通信ができなくなった

  → /Library/Java/JavaVirtualMachines/jdk1.8.0_92.jdk/Contents/Home/jre/lib/security/java.policy
  の修正が必要 (macOSの場合, windows は Program Files 下かな)
        // allows anyone to listen on dynamic ports
        // permission java.net.SocketPermission "localhost:0", "listen";
        permission java.net.SocketPermission "*", "listen, connect,accept,resolve";
   とか
　 → 引き数に設定した java.policy では上書きできない?

grant {
   permission java.security.AllPermission;
   permission java.net.SocketPermission "*", "listen,accept,connect,resolve";
};

○ 起動 とりあえず bind までできた。この引数がいいのか知らんけど

$ rmiregistry \
-J-Djava.security.policy=java.policy \
-J-Djava.rmi.server.codebase=file:///Users/atsushi/NetBeansProjects/RmiExam/target/classes/

$ rmid \
-J-Djava.security.policy=java.policy \
-C-Djava.security.policy=java.policy \
-J-Djava.rmi.server.codebase=file:///Users/atsushi/NetBeansProjects/RmiExam/target/classes/ \
-C-Djava.rmi.server.codebase=file:///Users/atsushi/NetBeansProjects/RmiExam/target/classes/

$ java com.mycompany.rmiexam.Main \
-Djava.security.policy=java.policy \
-Djava.rmi.server.codebase=file:///Users/atsushi/NetBeansProjects/RmiExam/target/classes/

$ java com.mycompany.rmiexam.Client \
-Djava.security.policy=java.policy \
Hello Matt!

おーとりあえずうごいとる。ここからいらないパラメータ削れば良かんね
