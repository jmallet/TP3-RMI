RMI - Date transfert
Julien Mallet & Zairi Ziad
09/04/2015


Introduction
---------------------------
This application allows you to transfer data in the RMI to a set of objects organized according to a tree topology and thereafter as a graph.
Each node of the tree or graph s execute in a different virtual machine and spreads the data has his son .


to start the application :

in scripts do :

-------------- Arbre simple ------------------
to start the server :
./serveurArbre.sh

in a other terminal:
./arbre.sh

-------------- Arbre thread -------------------
to start the server :
./serveurThread.sh

in a other terminal:
./arbre.sh

--------------- Graphe -------------------
to start the server :
./serveurGraphe.sh

in a other terminal::
./graphe.sh



design
---------------------------
- Package rmi
	-Arbre : class which allow to create tree
	-ArbreThread : class which allow to create ArbreThread
	-Graphe : class which allow to create graphe
	-SiteImpl : class which allow to create a Site
	-SiteImplGraphe : class which allow to create a Site - graphe mode
	-SiteImplThread : class which allow to create a Site and diffuse messages with a thread
	-SiteItf : Interface to implement Site

- Package server
	-Server : class which initialize a registry RMI

- Package test
	-rmi : class test




Exception
----------------------------------------


Server: 

	else if (choix == 2){
		try {
			for (int i=0; i<6; i++) {
				node.add(new SiteImplGraphe(i));
				System.out.println(node.get(i).getId() + "aaa");
				Naming.rebind("//127.0.0.1/" + node.get(i).getId(), node.get(i));
			}		
			System.out.println("The server is ready (Graphe)");
		} catch (IOException e) {
		}
	}



SiteImplThread :

		for (Thread thread : lThread) {
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
		}






Server:

		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Registry already exists");
		}





Code Samples
---------------------------


SiteImpl:

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



SiteImplThread:

	public String diffuseMessage(final String message) throws RemoteException {
		if (!sons.isEmpty()) {
		StringBuilder res = new StringBuilder();
		res.append(getId() + ":" + message + " Thread" + "\n");
		final List<Thread> lThread = new ArrayList<Thread>();
		for (SiteItf son : sons) {
			ArbreThread aT = new ArbreThread(son, message, res);
			Thread thread = new Thread(aT);
			lThread.add(thread);
			thread.start();
		}
		for (Thread thread : lThread) {
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
		}
		
		return res.toString();
		}
		else return getId() + ":" +message+ " Thread" + "\n";
	}

SiteImplGraphe:

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


SiteImpl:

	public void addSon(final SiteItf son) throws RemoteException {
		if (son != null) {
			
		if (!son.hasFather()) {
			sons.add(son);
			son.setFather(this);
		}
		}
	}




	public void setFather(SiteItf father) throws RemoteException {
		if (father != null) {
			this.father = father;
		}
	}


