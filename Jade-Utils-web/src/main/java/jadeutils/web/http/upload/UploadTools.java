package jadeutils.web.http.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadTools {

	/**
	 * 上传单个文件
	 * 
	 * @param req
	 *            http请求
	 * @param characterEncoding
	 *            编码
	 * @param uploadFilePath
	 *            上传路径
	 * @param tmpFilePath
	 *            临时文件路径
	 * @param singleFileSize
	 *            单个文件大小
	 * @param isCached
	 *            是否使用缓存
	 * @param fileName
	 *            文件所在的字段名
	 * @return 操作信息
	 * @throws IOException
	 */
	public String uploadSingleFile(HttpServletRequest req,
			String characterEncoding, String uploadFilePath,
			String tmpFilePath, int singleFileSize, boolean isCached,
			final String fileName) throws IOException {
		return this.uploadManyFiles(req, characterEncoding, uploadFilePath,
				tmpFilePath, singleFileSize, singleFileSize, isCached,
				new UploadToolsCfg() {
					@Override
					public boolean isMatchFieldName(String fieldName) {
						return fieldName.equals(fileName);
					}
				});
	}

	/**
	 * 上传多个文件
	 * 
	 * @param req
	 *            http请求
	 * @param characterEncoding
	 *            编码
	 * @param uploadFilePath
	 *            上传路径
	 * @param tmpFilePath
	 *            临时文件路径
	 * @param singleFileSize
	 *            单个文件大小
	 * @param allFileSize
	 *            所有文件大小
	 * @param isCached
	 *            是否使用缓存
	 * @param cfg
	 *            配置类
	 * @return 操作信息
	 * @throws IOException
	 */
	public String uploadManyFiles(HttpServletRequest req,
			String characterEncoding, String uploadFilePath,
			String tmpFilePath, int singleFileSize, int allFileSize,
			boolean isCached, UploadToolsCfg cfg) throws IOException //
	{
		req.setCharacterEncoding(characterEncoding);
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		if (isCached) {
			// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
			// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
			// 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
			// 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
			// 格式的 然后再将其真正写到 对应目录的硬盘上
			factory.setRepository(new File(tmpFilePath));
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(1024 * 1024);
		}

		// 高水平的API文件上传处理
		ServletFileUpload uploader = new ServletFileUpload(factory);
		// 将页面请求传递信息最大值设置为50M
		uploader.setSizeMax(allFileSize);
		// 将单个上传文件信息最大值设置为6M
		uploader.setFileSizeMax(singleFileSize);

		try {
			/* 遍历所有表单成员 */
			List<FileItem> list = (List<FileItem>) uploader.parseRequest(req);
			for (FileItem item : list) {
				String fieldName = item.getFieldName();// 表单字段名
				if (cfg.isMatchFieldName(fieldName)) {
					this.doUpload(req, item, uploadFilePath, fieldName);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 执行上传文件保存操作
	 * 
	 * @param req
	 * @param item
	 * @param uploadFilePath
	 * @param fieldName
	 * @throws Exception
	 */
	private void doUpload(HttpServletRequest req, FileItem item,
			String uploadFilePath, String fieldName) throws Exception {
		if (item.isFormField()) {
			/* 字符串类型字段处理 */
			String fieldValue = item.getString();
			req.setAttribute(fieldName, fieldValue);
		} else {
			/* 二进制流文件处理 */
			String filename = this.getUploadFileName(item.getName());
			req.setAttribute(fieldName, filename);
			// 这是第三方提供的写入磁盘方法，这里先注释掉不用
			if (Integer.parseInt("2") > 6666) {// 这里的代码永远执行不到
				this.autoWriteFile(item, uploadFilePath, filename);
			}
			// 自己定义的写入磁盘的方法
			writeFile(item, uploadFilePath, filename);
		}

	}

	/**
	 * 把文件写入磁盘
	 * 
	 * @param item
	 * @param filename
	 * @throws IOException
	 */
	private void writeFile(FileItem item, String path, String filename)
			throws IOException {
		// 手动写的
		File file = new File(path, filename);
		OutputStream out = new FileOutputStream(file);

		InputStream in = item.getInputStream();
		int length = 0;
		byte[] buf = new byte[1024];
		// System.out.println("获取上传文件的总共的容量：" + item.getSize());
		// 每次读到的数据存放在 buf 数组中
		while ((length = in.read(buf)) != -1) {
			// 数据 写到 （输出流）磁盘上
			out.write(buf, 0, length);
		}
		in.close();
		out.close();
	}

	/**
	 * 由第三提供的方法自动写入文件
	 * 
	 * @param item
	 * @param filename
	 * @throws Exception
	 */
	private void autoWriteFile(FileItem item, String path, String filename)
			throws Exception {
		item.write(new File(path, filename));
	}

	/**
	 * 取得上传的文件名
	 * 
	 * @return
	 */
	private String getUploadFileName(String fieldValue) {
		String fileName = fieldValue;
		String[] tmp = fileName.split("/");
		int tailIdx = tmp.length - 1;
		fileName = tmp[tailIdx];
		tmp = fileName.split("\\\\");
		tailIdx = tmp.length - 1;
		fileName = tmp[tailIdx];
		return fileName;
	}
}
