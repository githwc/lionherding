package com.lh.common.constant;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-27
 * @Version: 1.0.0
 */
public interface CacheConstant {

	// /**
	//  * 字典信息缓存
	//  */
    // public static final String SYS_DICT_CACHE = "sys:cache:dict";
	// /**
	//  * 表字典信息缓存
	//  */
    // public static final String SYS_DICT_TABLE_CACHE = "sys:cache:dictTable";
    //
	/**
	 * 数据权限配置缓存
	 */
    public static final String SYS_DATA_PERMISSIONS_CACHE = "sys:cache:permission:datarules";

	/**
	 * 缓存用户信息
	 */
	public static final String SYS_USERS_CACHE = "sys:cache:user";

	/**
	 * 全部部门信息缓存
	 */
	public static final String SYS_DEPARTS_CACHE = "sys:cache:depart:alldata";


	/**
	 * 全部部门ids缓存
	 */
	public static final String SYS_DEPART_IDS_CACHE = "sys:cache:depart:allids";

    //
	// /**
	//  * 测试缓存key
	//  */
	// public static final String TEST_DEMO_CACHE = "test:demo";

}
