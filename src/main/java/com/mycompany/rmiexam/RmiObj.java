/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rmiexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author atsushi
 */
public interface RmiObj extends Remote {
    public String hello(String name) throws RemoteException;
}
