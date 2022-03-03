package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:29 上午
 */
public interface SysFileService extends IService<SysFile> {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	R uploadFile(MultipartFile file);

	/**
	 * 读取文件
	 * @param bucket 桶名称
	 * @param fileName 文件名称
	 * @param response 输出流
	 */
	void getFile(String bucket, String fileName, HttpServletResponse response);

	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	Boolean deleteFile(Long id);

}
