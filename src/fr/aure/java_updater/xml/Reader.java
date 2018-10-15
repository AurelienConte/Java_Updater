package fr.aure.java_updater.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import fr.aure.java_updater.downloader.ServerConfiguration;

public class Reader {
	
	public Reader(ServerConfiguration ServerInformations) 
	{
		URL url = null;
		InputStream in = null;
		Document doc = null;
	
		try {
			url = new URL(ServerInformations.getProtocol() + "://" + ServerInformations.getHostName() + ServerInformations.getPath() + "xml/download.xml");
			System.out.println(url);
			in = url.openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(in);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		  doc.getDocumentElement ().normalize ();
          System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
	}
}
