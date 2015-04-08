package rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * class which allow to create a Site - graphe mode
 * @author julien and ziad
 *
 */
public  class SiteImplGraphe extends UnicastRemoteObject implements SiteItf {

	public boolean already = false;
	
	public List<SiteItf> nodes;

	public Integer id;

	/**
	 * this is a default constructor
	 * @param i
	 * @throws RemoteException
	 */
	public SiteImplGraphe(int i) throws RemoteException {
		super();
		nodes = new LinkedList<SiteItf>();
		id = new Integer(i);
	}

	
	/**
	 * Create a Son of the current Site
	 * @param the SiteItf
	 * @throws RemoteException
	 */
	public void addSon(final SiteItf node) throws RemoteException {
		if (node != null) {
			nodes.add(node);
		}
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
		if (!already) {
			already = true;
		} else {
			already = false;
			return "";
		}
		StringBuilder res = new StringBuilder();		
		res.append(getId() + ":" + message + "Graphe" + "\n");
		List<Thread> threads = new ArrayList<Thread>();
		for (SiteItf node : nodes) {
			ArbreThread aT = new ArbreThread(node, message, res);
			Thread thread = new Thread(aT);
			threads.add(thread);
			thread.start();
		}
		for (Thread tr : threads) {
			try {
				tr.join();
			} catch (InterruptedException e) {
			}
		}
		already = false;
		return res.toString();
	}
	
	/**
	 * display the  father of the current site
	 * @param father
	 * @throws RemoteException
	 */
	public void setFather(SiteItf father) throws RemoteException {
	}
	
	public boolean hasFather() {
		return false;
	}
}



