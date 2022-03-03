package com.zhangzc.cloud.upms.api.vo;

import com.zhangzc.cloud.upms.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:31 上午
 */
@Data
public class LogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private SysLog sysLog;

	private String username;

}
