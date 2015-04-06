package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface to implement Site
 * @author julien and Zairi
 *
 */
public interface SiteItf extends Remote {

	/**
	 * broadcasts a message to a Site
	 * @param message is a message to broadcast
	 * @return the broadcast message
	 * @throws RemoteException
	 */
	public String diffuseMessage(String message) throws RemoteException;

	/**
	 * Create a Son of the current Site
	 * @param the SiteItf
	 * @throws RemoteException
	 */
	public void addSon(SiteItf son) throws RemoteException;

	/**
	 * allow to have identifier of the current Site
	 * @return the identifier
	 * @throws RemoteException
	 */
	public String getId() throws RemoteException;
	
	/**
	 * display the  father of the current site
	 * @param father
	 * @throws RemoteException
	 */
	public void setFather(SiteItf father) throws RemoteException;

	/**
	 * allow to verify if the son has a father
	 * @return a boolean true or false
	 * @throws RemoteException
	 */
	boolean hasFather() throws RemoteException;

}