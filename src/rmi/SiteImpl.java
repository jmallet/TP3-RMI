package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public  class SiteImpl extends UnicastRemoteObject implements SiteItf {

	public List<SiteItf> sons;

	public SiteItf father;

	public Integer id;

	
	public SiteImpl(int i) throws RemoteException {
		super();
		father = null;
		sons = new LinkedList<SiteItf>();
		id = new Integer(i);
	}


	@Override
	public void addSon(final SiteItf son) throws RemoteException {
		if (son != null) {
			
		if (!son.hasFather()) {
			sons.add(son);
			son.setFather(this);
		}
		}
	}
	
	@Override
	public boolean hasFather() throws RemoteException {
		return father != null;
	}


	@Override
	public String getId() throws RemoteException {
		return id.toString();
	}

	
	@Override
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
	
	@Override
	public void setFather(SiteItf father) throws RemoteException {
		if (father != null) {
			this.father = father;
		}
	}
}



