package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteItf extends Remote {


	public String diffuseMessage(String message) throws RemoteException;

	public void addSon(SiteItf son) throws RemoteException;

	public String getId() throws RemoteException;

	public void setFather(SiteItf father) throws RemoteException;

	boolean hasFather() throws RemoteException;

}