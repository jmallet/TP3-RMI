package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * class which allow to create a Site
 * @author julien and ziad
 *
 */
public  class SiteImpl extends UnicastRemoteObject implements SiteItf {

	public List<SiteItf> sons;

	public SiteItf father;

	public Integer id;

	/**
	 * this is a default constructor
	 * @param i
	 * @throws RemoteException
	 */
	public SiteImpl(int i) throws RemoteException {
		super();
		father = null;
		sons = new LinkedList<SiteItf>();
		id = new Integer(i);
	}

	
	/**
	 * Create a Son of the current Site
	 * @param the SiteItf
	 * @throws RemoteException
	 */
	public void addSon(final SiteItf son) throws RemoteException {
		if (son != null) {
			
		if (!son.hasFather()) {
			sons.add(son);
			son.setFather(this);
		}
		}
	}
	
	/**
	 * allow to verify if the son has a father
	 * @return a boolean true or false
	 * @throws RemoteException
	 */
	public boolean hasFather() throws RemoteException {
		return father != null;
	}


	/**
	 * allow to have identifier of the current Site
	 * @return the identifier
	 * @throws RemoteException
	 */
	public String getId() throws RemoteException {
		return id.toString();
	}

	
	/**
	 * broadcasts a message to a Site
	 * @param message is a message to broadcast
	 * @return the broadcast message
	 * @throws RemoteException
	 */
	public String diffuseMessage(final String message) throws RemoteException {
		if (!sons.isEmpty()) {
		StringBuilder res = new StringBuilder();
		res.append(getId() + ":" + message + "\n");
		for (final SiteItf son : sons) {
			try {
				res.append(son.diffuseMessage(message));
			} catch (final RemoteException e) {
				return "";
			}
		}
		return res.toString();
		}
		else return getId() + ":" +message+"\n";
	}
	
	/**
	 * display the  father of the current site
	 * @param father
	 * @throws RemoteException
	 */
	public void setFather(SiteItf father) throws RemoteException {
		if (father != null) {
			this.father = father;
		}
	}
}



