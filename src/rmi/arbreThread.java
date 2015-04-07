package rmi;

import java.io.IOException;

public class arbreThread implements Runnable{

	private String message;
	private SiteItf site;
	private StringBuilder res;

	public arbreThread(SiteItf site,String message,StringBuilder res) {
		this.site = site;
		this.message = message;
		this.res = res;
	}
	public void run() {
		try {
			res.append(site.diffuseMessage(message));
		} catch (IOException e) {
		}
	}
}
