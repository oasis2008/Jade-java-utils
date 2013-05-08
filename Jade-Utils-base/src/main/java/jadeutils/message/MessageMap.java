package jadeutils.message;

/**
 * 
 */

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author morgan
 * 
 */
public class MessageMap {
	ResourceBundle resourceBundle = null;

	public MessageMap(String fileName) {
		this.resourceBundle = ResourceBundle.getBundle(fileName);
	}

	/**
	 * <p>
	 * 从properties文件中取得消息。
	 * </p>
	 * 
	 * @param msgKey
	 *            消息key值
	 * @return properties文件中对应的消息
	 */
	public String getMessage(String msgKey) {
		String strErrorMsg = "Err load msg properties";
		try {
			strErrorMsg = this.resourceBundle.getString(msgKey);
		} catch (Exception e) {
			// do nothing
		}
		return strErrorMsg;
	}

	/**
	 * <p>
	 * 从properties文件中取得消息。(Log相关)
	 * </p>
	 * 
	 * @param msgKey
	 *            消息key值
	 * @param params
	 *            消息参数key值
	 * @return properties文件中对应的消息(Log相关)
	 */
	public String getMessage(String msgKey, String[] params) {
		Object obj[] = params;
		String strErrorMsg = this.getMessage(msgKey);
		try {
			strErrorMsg = MessageFormat.format(strErrorMsg, obj);
		} catch (Exception e) {
			// do nothing
		}
		return strErrorMsg;
	}
}
