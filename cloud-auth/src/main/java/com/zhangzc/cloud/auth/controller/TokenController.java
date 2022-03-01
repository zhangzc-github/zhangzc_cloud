package com.zhangzc.cloud.auth.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TokenController
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/1 5:33 下午
 */
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 认证页面
     * @param modelAndView ModelAndView
     * @param error 表单登录失败处理回调的错误信息
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        modelAndView.setViewName("/ftl/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /**
     * 查询token
     * @param params 分页参数
     * @return page
     */
    @PostMapping("/page")
    public R<Page> tokenList(@RequestBody Map<String, Object> params) {
        String key = "auth_to_access:*";
        int current = MapUtil.getInt(params, CommonConstants.CURRENT);
        int size = MapUtil.getInt(params, CommonConstants.SIZE);
        Set<String> keys = redisTemplate.keys(key);
        List<String> pages = keys.stream().skip(current - 1).limit(size).collect(Collectors.toList());
        Page result = new Page<>(current, size);
        result.setRecords(redisTemplate.opsForValue().multiGet(pages));
        result.setTotal(keys.size());
        return R.ok(result);
    }
}
