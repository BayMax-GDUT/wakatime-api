package com.cvaiedu.template.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
@Component
public class FileUtils {

	@Value("${file.uploadPath}")
	private String filePploadPath;

	/**
	 * 删除文件
	 *
	 * @param filePath
	 *            文件
	 * @return
	 */
	public boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePploadPath + filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
