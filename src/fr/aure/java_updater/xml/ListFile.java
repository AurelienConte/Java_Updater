package fr.aure.java_updater.xml;

public class ListFile {

	private String name = null;
	private String md5 = null;
	
	public ListFile(String name, String md5)
	{
		this.name = name;
		this.md5 = md5;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getMd5()
	{
		return this.md5;
	}
	
}
