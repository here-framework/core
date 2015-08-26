/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
**/

package org.csource.common;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import org.csource.common.*;

/**
* ini file reader / parser
* @author Happy Fish / YuQing
* @version Version 1.0
*/
public class IniFileReader
{
	private Hashtable paramTable;
	
/**
* @param conf_filename config filename
*/
	public IniFileReader(String conf_filename) throws FileNotFoundException, IOException
	{
		loadFromFile(conf_filename);
	}
	
	public IniFileReader(InputStream conf_in) throws FileNotFoundException, IOException
	{
		loadFromFile(conf_in);
	}
	
/**
* get string value from config file
* @param name item name in config file
* @return string value
*/
	public String getStrValue(String name)
	{
		Object obj;
		obj = this.paramTable.get(name);
		if (obj == null)
		{
			return null;
		}
		
		if (obj instanceof String)
		{
			return (String)obj;
		}
		
		return (String)((ArrayList)obj).get(0);
	}

/**
* get int value from config file
* @param name item name in config file
* @param default_value the default value
* @return int value
*/
	public int getIntValue(String name, int default_value)
	{
		String szValue = this.getStrValue(name);
		if (szValue == null)
		{
			return default_value;
		}
		
		return Integer.parseInt(szValue);
	}

/**
* get boolean value from config file
* @param name item name in config file
* @param default_value the default value
* @return boolean value
*/
	public boolean getBoolValue(String name, boolean default_value)
	{
		String szValue = this.getStrValue(name);
		if (szValue == null)
		{
			return default_value;
		}
		
		return szValue.equalsIgnoreCase("yes") || szValue.equalsIgnoreCase("on") || 
					 szValue.equalsIgnoreCase("true") || szValue.equals("1");
	}
	
/**
* get all values from config file
* @param name item name in config file
* @return string values (array)
*/
	public String[] getValues(String name)
	{
		Object obj;
		String[] values;
		
		obj = this.paramTable.get(name);
		if (obj == null)
		{
			return null;
		}
		
		if (obj instanceof String)
		{
			values = new String[1];
			values[0] = (String)obj;
			return values;
		}
		
		Object[] objs = ((ArrayList)obj).toArray();
		values = new String[objs.length];
		System.arraycopy(objs, 0, values, 0, objs.length);
		return values;
	}
	private void loadFromFile(String conf_filename) throws FileNotFoundException, IOException{
		this.loadFromFile(new FileReader(conf_filename));
	}
	private void loadFromFile(InputStream conf_in) throws FileNotFoundException, IOException{
		
		this.loadFromFile(new InputStreamReader(conf_in,"utf8"));
	}
	
	private void loadFromFile(Reader conf_reader) throws FileNotFoundException, IOException
	{
		FileReader fReader;
		BufferedReader buffReader;
		String line;
		String[] parts;
		String name;
		String value;
		Object obj;
		ArrayList valueList;
	  buffReader = new BufferedReader(conf_reader);
	  this.paramTable = new Hashtable();
	  
	  try
	  {
	  	while ((line=buffReader.readLine()) != null)
	  	{
	  		line = line.trim();
	  		if (line.length() == 0 || line.charAt(0) == '#')
	  		{
	  			continue;
	  		}
	  		
	  		parts = line.split("=", 2);
	  		if (parts.length != 2)
	  		{
	  			continue;
	  		}
	  	
	  		name = parts[0].trim();
	  		value = parts[1].trim();
	  		
	  		obj = this.paramTable.get(name);
	  		if (obj == null)
	  		{
	  			this.paramTable.put(name, value);
	  		}
	  		else if (obj instanceof String)
	  		{
	  			valueList = new ArrayList();
	  			valueList.add(obj);
	  			valueList.add(value);
	  			this.paramTable.put(name, valueList);
	  		}
	  		else
	  		{
	  			valueList = (ArrayList)obj;
	  			valueList.add(value);
	  		}
	  	}
	  }
	  finally
	  {
		  buffReader.close();
	  }
  }  
}
