package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import rmi.SiteImpl;
import rmi.SiteImplGraphe;
import rmi.SiteImplThread;
import rmi.SiteItf;
import rmi.arbreThread;

public class rmi {

	@Test
	public void hasFatherKO() throws RemoteException {
		SiteImpl si = new SiteImpl(1);
		SiteImplThread sit = new SiteImplThread(1);
		assertFalse(si.hasFather());
		assertFalse(sit.hasFather());
	}

	@Test
	public void hasFatherOK() throws RemoteException {
		SiteImpl si = new SiteImpl(1);
		SiteImpl si2 = new SiteImpl(2);
		SiteImplThread sit = new SiteImplThread(1);
		SiteImplThread sit2 = new SiteImplThread(2);
		si.addSon(si2);
		sit.addSon(sit2);
		assertTrue(si2.hasFather());
		assertTrue(sit2.hasFather());
	}
	
	@Test
	public void TestGetId() throws RemoteException {
		SiteImpl si = new SiteImpl(1);
		SiteImplThread sit = new SiteImplThread(1);
		SiteImplGraphe sig = new SiteImplGraphe(1);
		assertEquals ("1",si.getId());
		assertEquals ("1",sit.getId());
		assertEquals ("1",sig.getId());
	}
	
	@Test 
	public void arbreThreadTest() throws RemoteException {
		SiteImpl si = new SiteImpl(1);
		StringBuilder res = new StringBuilder();
		arbreThread at = new arbreThread (si,"message test",res);
		at.run();
		assertEquals("1:message test\n",res.toString());
	}
	
	
	@Test 
	public void addSonTest() throws RemoteException{
		SiteImpl si = new SiteImpl(1);
		SiteImpl si2 = new SiteImpl(2);
		SiteImplThread sit = new SiteImplThread(1);
		SiteImplThread sit2 = new SiteImplThread(2);
		si.addSon(si2);
		sit.addSon(sit2);
		assertEquals(si2,si.sons.get(0));
		assertEquals(sit2,sit.sons.get(0));
	}
	
	@Test 
	public void addSonGrapheTest() throws RemoteException{
		SiteImplGraphe sig = new SiteImplGraphe(1);
		SiteImplGraphe sig2 = new SiteImplGraphe(2);
		sig.addSon(sig2);
		assertEquals(sig2,sig.nodes.get(0));
	}

	@Test 
	public void diffuseMessageGrapheTest() throws RemoteException{
		SiteImplGraphe si = new SiteImplGraphe(1);
		SiteImplGraphe si2 = new SiteImplGraphe(2);
		SiteImplGraphe si3 = new SiteImplGraphe(3);
		si.addSon(si2);
		si2.addSon(si3);
		assertEquals("1:message testGraphe\n2:message testGraphe\n3:message testGraphe\n", si.diffuseMessage("message test"));
	}
	
	@Test 
	public void diffuseMessageThreadTest() throws RemoteException{
		SiteImplThread si = new SiteImplThread(1);
		SiteImplThread si2 = new SiteImplThread(2);
		SiteImplThread si3 = new SiteImplThread(3);
		si.addSon(si2);
		si2.addSon(si3);
		assertEquals("1:message test Thread\n2:message test Thread\n3:message test Thread\n", si.diffuseMessage("message test"));
	}
	
	
	@Test 
	public void diffuseMessageArbreTest() throws RemoteException{
		SiteImpl si = new SiteImpl(1);
		SiteImpl si2 = new SiteImpl(2);
		SiteImpl si3 = new SiteImpl(3);
		si.addSon(si2);
		si2.addSon(si3);
		assertEquals("1:message test\n2:message test\n3:message test\n", si.diffuseMessage("message test"));
	}
	
	
	
}
