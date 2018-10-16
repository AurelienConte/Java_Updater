package fr.aure.java_updater;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class ErrorHandler {

	private boolean DEBUG = false;
	
	public ErrorHandler(Exception e) {
		if(e instanceof ConnectException)
			System.out.println("Cannot connect to remote host exiting...");
		else if(e instanceof MalformedURLException)
			System.out.println("URL malformed exiting...");
		else if(e instanceof InterruptedException)
			System.out.println("Problem detected with threads.... exiting...");
		else if(e instanceof NoSuchAlgorithmException)
			System.out.println("Bad algorithm... exiting....");
		else if(e instanceof IOException)
			System.out.println("Problem IO Exception... exiting...");
		else
			System.out.println("Error not specified....");
		
		if(DEBUG)
			e.printStackTrace();
		ProcessExitError();
	}
	
	
	public void ProcessExitError()
	{
		try {
			if(DEBUG)
				TimeUnit.MINUTES.sleep(1);
			else
				TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) { new ErrorHandler(e1); }
		System.exit(1);
	}
}
