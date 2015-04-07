package server;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import rmi.SiteImpl;
import rmi.SiteItf;
/**
 * class which initialize a registry RMI
 * @author julien and zairi
 *
 */
public class server {

	public static void main(String[] args) {

		List<SiteItf> node = new ArrayList<SiteItf>();
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Registry already exists");
		}
		try {
			for (int i=0; i<6; i++) {
				node.add(new SiteImpl(i));
				System.out.println(node.get(i).getId() + "aaa");
				Naming.rebind("//127.0.0.1/" + node.get(i).getId(), node.get(i));
				
				
			}		
			System.out.println("The server is ready");
		} catch (IOException e) {
		}
	}
}