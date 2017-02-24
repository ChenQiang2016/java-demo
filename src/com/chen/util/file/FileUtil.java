package com.chen.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

	public static String upoad(File file) {
		String url = "/file/";
		try {
			String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			String name = rename(suffix);
			url = url + name;
			InputStream input = new FileInputStream(file);
			OutputStream out = new FileOutputStream(url);

			byte[] buffer = new byte[1024];
			int count = 0;

			while ((count = input.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			out.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("上传文件失败");
		}
		return url;
	}
	
	public static String rename(String suffix) {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "." + suffix;
	}
}