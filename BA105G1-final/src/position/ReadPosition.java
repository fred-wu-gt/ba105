package position;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadPosition {
	public static void main(String[] args) {
		File file = new File("fruitPosition.ser");
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		int count=0;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			System.out.println(file.getName() + "檔案內容如下: ");
			System.out.println("--------------------");
			
			while (true) {
				PositionVO positionVO = (PositionVO) ois.readObject();
				System.out.println("地點: "+positionVO.getPosition());
				System.out.println("緯度: "+positionVO.getLat());
				System.out.println("經度: "+positionVO.getLng());
				System.out.println("--------------------");
				count++;
			}

			
		} catch (EOFException e) {
			System.out.println("資料讀取完畢！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("共"+count+"個地點");
		
		
	}
}
