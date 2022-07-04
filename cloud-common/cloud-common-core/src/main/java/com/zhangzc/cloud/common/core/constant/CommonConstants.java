package com.zhangzc.cloud.common.core.constant;

public interface CommonConstants {
    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";
    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 菜单树根节点
     */
    Long MENU_TREE_ROOT_ID = -1L;

    /**
     * 当前页
     */
    String CURRENT = "current";

    /**
     * size
     */
    String SIZE = "size";

    /**
     * 滑块验证码
     */
    String IMAGE_CODE_TYPE = "blockPuzzle";

    /**
     * header 中租户ID
     */
    String TENANT_ID = "TENANT-ID";

    /**
     * 租户ID
     */
    Integer TENANT_ID_1 = 1;

    /**
     * 密码传输是否加密
     */
    String ENC_FLAG = "enc_flag";

    /**
     * 验证码开关
     */
    String CAPTCHA_FLAG = "captcha_flag";
}
