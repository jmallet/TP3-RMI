RMI - Data transfert
Julien Mallet & Zairi Ziad
09/04/2015


Introduction
---------------------------
This application allows the user to transfer data using RMI according to a tree topology and thereafter as a graph.
Each node of the tree or graph spreads the data to his son .


To start the application :

In the directory scripts :

-------------- Simple tree ------------------
to start the server :
./serveurArbre.sh

in a other terminal:
./arbre.sh

-------------- tree with thread -------------------
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
	-Arbre : class which allow to create a tree
	-ArbreThread : class which allow to spreads datas using threads
	-Graphe : class which allow to create a graphe
	-SiteImpl : class which allow to create a Site
	-SiteImplGraphe : class which allow to create a Site - graphe mode
	-SiteImplThread : class which allow to create a Site and diffuse messages with a thread
	-SiteItf : Interface to implement Site

- Package server
	-Server : class which initialize a registry RMI

- Package test
	-rmi : class test




Exceptions
----------------------------------------

Examples of exceptions in this application :

	try {
		...
		...		
	} catch (IOException e) {
	}

	try {
		thread.join();
	} catch (InterruptedException e) {
	}

	try {
		LocateRegistry.createRegistry(1099);
	} catch (RemoteException e) {
		System.out.println("Registry already exists");
	}

	public void myFunction() throws RemoteException {
	...
	}






Code Samples
---------------------------



This method allows to spreads a message into the tree :

public String diffuseMessage(String message) throws RemoteException {
		//If the current node has sons
		if (!sons.isEmpty()) {
		//The variable that will contain all the messages
		StringBuilder res = new StringBuilder();
		//The id of the node and the message are added to the variable
		res.append(getId() + ":" + message + "\n");
		//For each son of the node
		for (final SiteItf son : sons) {
			try {
				//Add to the variable the messages that will be received in the sub-tree
				res.append(son.diffuseMessage(message));
			} catch (final RemoteException e) {
				return "";
			}
		}
		return res.toString();
		}
		//If the node don't have sons, just return the message+id
		else return getId() + ":" +message+"\n";
	}


This method allows to spreads a message into the tree with threads:

	public String diffuseMessage(String message) throws RemoteException {
		//If the current node has sons
		if (!sons.isEmpty()) {
		//The variable that will contain all the messages
		StringBuilder res = new StringBuilder();
		//The id of the node and the message are added to the variable
		res.append(getId() + ":" + message + " Thread" + "\n");
		//The list of threads that we will use
		final List<Thread> lThread = new ArrayList<Thread>();
		//For each son of the node
		for (SiteItf son : sons) {
			//Initialize a type that will exectute res.append(son.diffuseMessage(mess))
			ArbreThread aT = new ArbreThread(son, message, res);
			//Add and start a new thread
			Thread thread = new Thread(aT);
			lThread.add(thread);
			thread.start();
		}
		//For each thread
		for (Thread thread : lThread) {
				try {
					//Wait until the current thread finish
					thread.join();
				} catch (InterruptedException e) {
				}
		}
		
		return res.toString();
		}
		//If the node don't have sons
		else return getId() + ":" +message+ " Thread" + "\n";
	}

Method that allows to add a son to a node  :

	public void addSon(SiteItf son) throws RemoteException {
		//If not null
		if (son != null) {
		//If the son don't have a father already
		if (!son.hasFather()) {
			//Add the son to the list of sons of the node
			sons.add(son);
			//Set the father of the son
			son.setFather(this);
		}
		}
	}

Method that allows to add a son to a node of a graph : 

public void addSon(final SiteItf node) throws RemoteException {
		//Just check if the node is not null. A node can have differents fathers
		if (node != null) {
			nodes.add(node);
		}
	}

Method in the class ArbreThread, to spreads the message to the sons of the site :
public void run() {
		try {
			//spreads the message to the sons of the site and keep all the messages into res
			res.append(site.diffuseMessage(message));
		} catch (IOException e) {
		}
	}



