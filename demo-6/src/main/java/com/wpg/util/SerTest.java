package com.wpg.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.wpg.pojo.OperationDetails;

public class SerTest {

		public static void change(OperationDetails op) {
			OperationDetails o=new OperationDetails();
			o.setDetails(op.getDetails());
			try {
				FileOutputStream fileout=new FileOutputStream("E:/all.log");
				ObjectOutputStream out=new ObjectOutputStream(fileout);
				out.writeObject(o);
				out.close();
				fileout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
