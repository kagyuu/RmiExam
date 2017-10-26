/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rmiexam;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.UnknownGroupException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atsushi
 */
public class Main {

    public static void main(String[] args) {
        try {
            // この JVM の activationGroup を作る
            // activationGroup オブジェクトが返ってくるけど、ここからできるのは、このグループで動く RMI アプリケーションの停止だけ
            ActivationGroup activationGroup = createActivationGroup();
            createRmiObj();            
        } catch (ActivationException | RemoteException | AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ActivationGroup createActivationGroup() throws ActivationException, RemoteException {
        Properties prop = new Properties();
        // これいらないかも...どうせ java の引き数で policy 指定すんでしょ
        prop.put("java.security.policy", "/Users/atsushi/java.policy");
        
        ActivationGroupDesc.CommandEnvironment configInfo = null;
        ActivationGroupDesc desc = new ActivationGroupDesc(prop, configInfo);
        
        ActivationGroupID id = (ActivationGroup.getSystem()).registerGroup(desc);
        
        return ActivationGroup.createGroup(id, desc, 0);
    }

    private static void createRmiObj() throws ActivationException, UnknownGroupException, RemoteException, AlreadyBoundException, MalformedURLException {
        // 第二引き数意味アンのかなぁ...どうせ java の引き数で codebase 指定すんでしょ。　ひょっとして第二引き数の意味を間違って解釈している?
        ActivationDesc ad = new ActivationDesc(
                RmiObjImpl.class.getName(),
                "file:///Users/atsushi/NetBeansProjects/RmiExam/target/RmiExam-1.0-SNAPSHOT.jar",
                null);
        
        RmiObj obj = (RmiObj) RmiObjImpl.register(ad);
        Naming.bind("hello", obj);
    }
}
