/**
 * 
 */
package jadeutils.web.cdn;

import jadeutils.web.http.upload.UploadTools;
import jadeutils.web.http.upload.UploadToolsCfg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SHAN013
 * 
 *         文件上传<br/>
 *         具体步骤： <br/>
 *         1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 <br/>
 *         2） 利用 req 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 <br/>
 *         3）对 DiskFileItemFactory 对象设置一些 属性 <br/>
 *         4）高水平的API文件上传处理 ServletFileUpload uploader = new
 *         ServletFileUpload(factory); <br/>
 *         目的是调用 parseRequest（req）方法 获得 FileItem 集合list ， <br/>
 * 
 *         5）在 FileItem 对象中 获取信息， 遍历， 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 <br/>
 *         6） <br/>
 *         第一种. 用第三方 提供的 item.write( new File(path,filename) ); 直接写到磁盘上 <br/>
 *         第二种. 手动处理 <br/>
 */
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String characterEncoding = "utf-8";
		String uploadFilePath = "/opt/upload/";
		String tmpFilePath = "/opt/uploadtmp/";

		int singleFileSize = 66 * 500 * 1024;
		int allFileSize = 2 * 1024 * 1024;
		boolean isCached = true;

		UploadTools uploader = new UploadTools();
		// uploader.uploadSingleFile(req, characterEncoding, uploadFilePath,
		// tmpFilePath, singleFileSize, isCached, "file1");
		uploader.uploadManyFiles(req, characterEncoding, uploadFilePath,
				tmpFilePath, singleFileSize, allFileSize, isCached,
				new UploadToolsCfg() {
					@Override
					public boolean isMatchFieldName(String fieldName) {
						return true;
					}
				});

		req.getRequestDispatcher("/file-upload/upload-result.jsp").forward(req,
				resp);
	}
}
