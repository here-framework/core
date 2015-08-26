package com.here.framework.core.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.nustaq.serialization.FSTConfiguration;

/**
 * 对象序列化
 * @author koujp
 *
 */
public class HObjectSerializable {
	private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException 
	 */
	public static byte[] serializable(Object object) throws IOException{
		return serializableFST(object);
	}
	/**
	 * 反序列化
	 * @param object
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object deserializable(byte[] bytes) throws ClassNotFoundException, IOException{
		return deserializableFST(bytes);
	}
	
	public static byte[] serializableFST(Object object) throws IOException{
		
		return conf.asByteArray(object);
		
	}
	/**
	 * 反序列化
	 * @param object
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object deserializableFST(byte[] bytes) throws ClassNotFoundException, IOException{
		
		return conf.asObject(bytes);
		
	}
	
	
	public static byte[] serializableJDK(Object object) throws IOException{
		ByteArrayOutputStream o=new ByteArrayOutputStream();
		
		ObjectOutputStream stream=new ObjectOutputStream(o);
		stream.writeObject(object);
		stream.close();
		
		return o.toByteArray();
	}
	
	public static Object deserializableJDK(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream input=new ByteArrayInputStream(bytes);
		ObjectInputStream in=new ObjectInputStream(input);
		Object obj=in.readObject();
		in.close();
		return obj;
	}
	
	
}
