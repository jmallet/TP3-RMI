package rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class which allow to create tree
 * @author julien and ziad
 *
 */
public class Arbre {

	public static void main(String[] args) throws NotBoundException {

		final List<SiteItf> tree = new ArrayList<SiteItf>();
		
		try {
			
			for(int i=0; i<6; i++) {	
				
				tree.add((SiteItf) Naming.lookup("//127.0.0.1/"+i));
				
			}
			
			tree.get(0).addSon(tree.get(1));
			tree.get(0).addSon(tree.get(2));
			tree.get(1).addSon(tree.get(4));
			tree.get(1).addSon(tree.get(3));
			tree.get(4).addSon(tree.get(5));
			
			// l'arbre:
			System.out.println("			0			");
			System.out.println("		/		");
			System.out.println("	1				2	");
			System.out.println("   / ");
			System.out.println("4	  	3			");
			System.out.println("|");
			System.out.println("5");
						
			
			
			// demande à l'utilisateur de choisir à partir de quel fils il veut diffuser le message
			Scanner enterUser = new Scanner(System.in); 
			int choice = enterUser.nextInt(); 
			
			
			
			//System.out.println("First");
			System.out.println(tree.get(choice).diffuseMessage("My message!"));
			
		/*	System.out.println("Second");
			SiteItf second = (SiteItf) Naming.lookup("127.0.0.1/1");
			System.out.println(second.diffuseMessage("My message!"));
			*/
		} catch (IOException e) {
			System.out.print("Error encountred");

		}
	}
}