package com.osf.test.io;

import java.io.File;

public class ShowFileList {
	public static void main(String[] args) {
		File tFile = new File("d:\\test");
		File[] files = tFile.listFiles();
		for (File fil : files) {
			if (fil.isDirectory()) {
				File[] subFiles = fil.listFiles();
				for (File fil2 : subFiles) {
					System.out.println(fil2);
				}
			} else {
				System.out.println(fil);
			}
		}
	}
}
