package com.commodity.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BytetoImg {
	public static void  bytetoImg(byte[] bytes,String com_no ) throws FileNotFoundException {
		File myDir = new File("readImgFromDB");
			
//		一次抓單張圖檔的寫法 
		File file = new File("WebContent\\readImgFromDB", com_no + ".jpg");

		OutputStream out = new FileOutputStream(file);

		try {
			out.write(bytes);
			out.flush();
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
//=====================================================================		
		
		
		
		
			
//				for(int i = 1; i <=3 ; i++){
//					OutputStream out = new FileOutputStream(file+"-"+i+".jpg");
//					try {
//						
//						out.write(bytes);
//						out.flush();
//						out.close();
//						System.out.println("以印出第"+i+"個圖片");
//					} catch (IOException e) {
//						e.printStackTrace();
//						System.out.println("列印完成");
//					}
//				}
//				
				
			
	}
}

//
// } catch (FileNotFoundException fe) {
//
// fe.printStackTrace();
// }
// catch (IOException ie) {
//
// ie.printStackTrace();
// }
//
// }else{
// myDir.mkdir();
// }
//
//
//
//
//
//
// }
