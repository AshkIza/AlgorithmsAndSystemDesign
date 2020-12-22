package javeCorePractice;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class outputStream {
	
	//Serializable is a marker interface 
	public static class Object implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String name;
		private String dob;
		public Object(String name, String dob) {
			this.name = name;
			this.dob = dob;
		}
	}

	public static void main(String[] args) {
		// java example of decorator pattern inputStream outputStream
		String relativePath = "/Users/ashkanizadpanah/eclipse-workspace/CodePractice/src/DesignPatterns/Decorator/";
		File outputFile = new File(relativePath + "outputstream.txt");
		File outputFileWriter = new File(relativePath + "outputstreamWriter.txt");

		try {
			//FileOutputStream writes bytes into a file 

			DataOutputStream datastream = 
					new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile))); 
			//THIS daisy chaining is done by decorating OutputStream
			datastream.writeInt(1362);
			datastream.writeBoolean(false);
			datastream.writeChars("OutputStream write as byte arrays");
			datastream.close();
			
			//ByteArrayOutputStream writes bytes into a byte Array 
			ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteArrayStream);
			objectStream.writeObject(new Object("Bob Taylor", "1977"));
			byte[] bytes = byteArrayStream.toByteArray();
			
			
			

			OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(outputFileWriter), "UTF-8");
			streamWriter.write(" writers write as string");
			streamWriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
