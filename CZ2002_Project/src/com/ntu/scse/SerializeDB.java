package com.ntu.scse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeDB {

	List info;
	FileInputStream fis;
	ObjectInputStream in;
	FileOutputStream fos;
	ObjectOutputStream out;


	public SerializeDB() {
		info = null;
		fis = null;
		in = null;
		fos = null;
		out = null;
	}

	public List readSerializedObject(String filename) {

		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			info = (ArrayList) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		// System.out.println(" Details Size: " + pDetails.size());
		// System.out.println();
		return info;
	}

	public void writeSerializedObject(String filename, List list) {

		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			fos.close();
			// System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
