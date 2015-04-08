package rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**class which allow to create graphe
 * 
 * @author julien
 *
 */
public class Graphe {

	public static void main(String[] args) {

		final List<SiteItf> node = new ArrayList<SiteItf>();

		try {
			for (int i = 0; i < 6; i++) {
				try {
					node.add((SiteItf) Naming.lookup("//127.0.0.1/" + i));
				} catch (NotBoundException e) {
				}
			}
			
			// le graphe:
			System.out.println("			0			");
			System.out.println("		/		");
			System.out.println("	1				4	");
			System.out.println("   / ");
			System.out.println("3	  	2			|");
			System.out.println("|");
			System.out.println("5--------------------");
			
			node.get(0).addSon(node.get(1));
			node.get(0).addSon(node.get(4));
			node.get(1).addSon(node.get(2));
			node.get(1).addSon(node.get(3));
			node.get(5).addSon(node.get(4));
			node.get(3).addSon(node.get(5));

						
			
			
			Scanner enterUser = new Scanner(System.in); 
			int choice = enterUser.nextInt(); 
			System.out.println(node.get(choice).diffuseMessage("My message!"));
		} catch (final IOException e) {
		}

	}

}
