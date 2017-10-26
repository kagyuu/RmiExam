/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rmiexam;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;

/**
 *
 * @author atsushi
 */
public class RmiObjImpl extends Activatable implements RmiObj {

    public RmiObjImpl(ActivationID id, MarshalledObject data)
            throws RemoteException {
        /*
	 * Export the activatable object on an anonymous port.
         */
        super(id, 0);
    }

    @Override
    public String hello(String name) throws RemoteException {
        return String.format("Hello %s!", name);
    }
}
