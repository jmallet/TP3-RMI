package rmi;

import java.io.IOException;

/**
 * class which allow to create tree Tread
 * @author julien
 *
 */
public class ArbreThread implements Runnable{

	private String message;
	private SiteItf site;
	private StringBuilder res;

	public ArbreThread(SiteItf site,String message,StringBuilder res) {
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
