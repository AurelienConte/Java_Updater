package fr.aure.java_updater.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.ConnectionPendingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.aure.java_updater.ErrorHandler;
import fr.aure.java_updater.downloader.ServerConfiguration;

public class Reader {

	ArrayList<ListFile> DL = null;
	
	public Reader(ServerConfiguration ServerInformations) 
	{
		URL url = null;
		InputStream in = null;
		Document doc = null;
		
		try {
			url = new URL(ServerInformations.getProtocol() + "://" + ServerInformations.getHostName() + ServerInformations.getPath() + "xml/" + ServerInformations.getXML_File());
			System.out.println("XML Download file path : " + url);
			in = url.openStream();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(in);
		} catch (SAXException|ParserConfigurationException|IOException e) { new ErrorHandler(e); }
		  doc.getDocumentElement ().normalize ();
		  ExctractConfig(doc, ServerInformations);
          DL = ExtractList(doc);
	}
	
	public void ExctractConfig(Document doc, ServerConfiguration Server)
	{
		NodeList nList = doc.getElementsByTagName("configuration");
		Node nNode = nList.item(0);
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			String folder = eElement.getElementsByTagName("folder").item(0).getTextContent();
			Server.FILES_FOLDER = folder;
		}
	}
	
	public ArrayList<ListFile> getDL()
	{
		return this.DL;
	}
	
	public ArrayList<ListFile> ExtractList(Document doc)
	{
		ArrayList<ListFile> items_list = new ArrayList<ListFile>();
		
		NodeList nList = doc.getElementsByTagName("file");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				String md5 = eElement.getElementsByTagName("md5").item(0).getTextContent();
				items_list.add(new ListFile(name, md5));
			}
		}
		
		return items_list;
	}
}
