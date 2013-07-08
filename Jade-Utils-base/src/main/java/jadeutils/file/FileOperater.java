package jadeutils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileOperater {

	/**
	 * 保存文件
	 * 
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param file
	 *            文件内容
	 * @throws Exception
	 * @throws Exception
	 */
	public static void writeFile(String path, String fileName, File file)
			throws IOException {
		/* 如果目录不存在则创建目录 */
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		/* 文件写入服务器 */
		writeToStream(path + fileName, new FileInputStream(file));
	}

	/**
	 * 保存文件
	 * 
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param inputStream
	 *            输入流
	 * @throws IOException
	 */
	public static void writeFile(String path, String fileName,
			InputStream inputStream) throws IOException {
		/* 如果目录不存在则创建目录 */
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		/* 文件写入服务器 */
		writeToStream(path + fileName, inputStream);
	}

	/**
	 * 写入到磁盘
	 * 
	 * @param fileName
	 * @param fileData
	 * @throws IOException
	 */
	private static void writeToStream(String fileName, InputStream inputStream)
			throws IOException {
		// 开启IO流保存上传的文件
		FileOutputStream fos = new FileOutputStream(fileName);
		// FileInputStream fis = new FileInputStream(fileData);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		inputStream.close();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 */
	public void deleteDirectory(String dir) throws IOException {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			// 如果dir对应的文件不存在，或者不是一个目录，则退出
		} else {
			// 删除文件夹下的所有文件(包括子目录)
			File[] files = dirFile.listFiles();
			for (int i = 0; i < files.length; i++) {

				if (files[i].isFile()) {
					// 删除子文件
					deleteFile(files[i].getAbsolutePath());
				} else {
					// 删除子目录
					deleteDirectory(files[i].getAbsolutePath());
				}
			}
		}
	}

}
