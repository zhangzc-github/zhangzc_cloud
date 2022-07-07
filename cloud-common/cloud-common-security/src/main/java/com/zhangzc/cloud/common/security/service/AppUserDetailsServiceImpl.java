package com.zhangzc.cloud.common.security.service;

import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 */
@Slf4j
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl implements CloudUserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 手机号登录
	 * @param phone 手机号
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String phone) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(phone) != null) {
			return (CloudUser) cache.get(phone).get();
		}

		R<UserInfo> result = remoteUserService.infoByMobile(phone, SecurityConstants.FROM_IN);

		UserDetails userDetails = getUserDetails(result);
		if (cache != null) {
			cache.put(phone, userDetails);
		}
		return userDetails;
	}

	/**
	 * check-token 使用
	 * @param cloudUser user
	 * @return
	 */
	@Override
	public UserDetails loadUserByUser(CloudUser cloudUser) {
		return this.loadUserByUsername(cloudUser.getPhone());
	}

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	@Override
	public boolean support(String clientId, String grantType) {
		return SecurityConstants.APP.equals(grantType);
	}

}
