package com.commodity.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Imgtobyte {

	public static byte[] imgtobyte(String file, String picName) {
		byte[] buffer = new byte[1024 * 8];
		File pic = new File(file, picName);
		try {
			buffer = Files.readAllBytes(pic.toPath());
		} catch (IOException e) {

			e.printStackTrace();
		} // Java 7 : java.nio.file

		return buffer;

	}

}
