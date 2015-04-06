package rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;


public class arbre {

	public static void main(String[] args) throws NotBoundException {

		final List<SiteItf> tree = new ArrayList<SiteItf>();

		try {
			for(int i=0; i<6; i++) {	
				tree.add((SiteItf) Naming.lookup("127.0.0.1/"+i));
			}
			
			tree.get(0).addSon(tree.get(1));
			tree.get(0).addSon(tree.get(2));
			tree.get(1).addSon(tree.get(4));
			tree.get(1).addSon(tree.get(3));
			tree.get(4).addSon(tree.get(5));
			
			//System.out.println("First");
			System.out.println(tree.get(0).diffuseMessage("My message!"));
			
		/*	System.out.println("Second");
			SiteItf second = (SiteItf) Naming.lookup("127.0.0.1/1");
			System.out.println(second.diffuseMessage("My message!"));
			*/
		} catch (IOException e) {
			System.out.print("Error encountred");

		}
	}
}