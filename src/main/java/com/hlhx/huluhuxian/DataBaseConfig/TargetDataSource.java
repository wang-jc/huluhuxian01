package com.hlhx.huluhuxian.DataBaseConfig;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/3/26
 * @Version: 1.0
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
