package fr.aure.java_updater.downloader;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerConfiguration {
	
	private String protocol = null;
	private String hostname = null;
	private Integer PortNumber = null;
	private String Path = null;
	private String ClientFolder = null;
	private String XML_File = null;
	public String FILES_FOLDER = null;
	
	public ServerConfiguration(String URL_PATH, String FOLDER_PATH, String XML_File) throws MalformedURLException {
		if(!URL_PATH.endsWith("/"))
			URL_PATH = URL_PATH + "/";
		URL aURL = new URL(URL_PATH);
		
		this.protocol = aURL.getProtocol();		
		this.hostname = aURL.getHost();
		this.PortNumber = aURL.getPort();
		this.Path = aURL.getPath();
		
		if(!FOLDER_PATH.endsWith("/"))
			FOLDER_PATH = FOLDER_PATH + "/";
		this.ClientFolder = FOLDER_PATH;
		if(!XML_File.endsWith(".xml"))
			XML_File = XML_File + ".xml";
		this.XML_File = XML_File;
	}
	
	public String getProtocol()
	{
		return this.protocol;
	}
	
	public String getHostName()
	{
		return this.hostname;
	}
	
	public Integer getPortNumber()
	{
		return this.PortNumber;
	}
	
	public String getPath()
	{
		return this.Path;
	}
	
	public String getClientFolder()
	{
		return this.ClientFolder;
	}
	
	public String getXML_File()
	{
		return this.XML_File;
	}

}
