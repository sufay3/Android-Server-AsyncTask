package com.jl.app.utils;

import android.sax.*;
import android.util.Log;

import java.io.ByteArrayInputStream;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.sax2.Driver;

public class XMLParser {
	
	public static Element build(String xml){
		try{
			XMLReader xmlReader = new Driver();
			RootElement root = new RootElement("philiosopher");
			root.setStartElementListener(new StartElementListener(){
				public void start(Attributes attributes){
					Log.v("XMLrmtParser", attributes.getValue("", "name"));
				}
			});
			xmlReader.setContentHandler(root.getContentHandler());
			
			InputSource is = new InputSource();
			is.setByteStream(new ByteArrayInputStream(xml.getBytes()));

			xmlReader.parse(is);
			
			return null;
		}catch(Exception e){
			return null;
		}
		
	}

}
