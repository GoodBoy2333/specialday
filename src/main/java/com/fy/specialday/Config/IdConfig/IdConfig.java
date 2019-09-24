package com.fy.specialday.Config.IdConfig;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 雪花主键生成器
 * </p>
 */
@Configuration
public class IdConfig {
    /**
     * 雪花生成器
     */
    @Bean
    public Snowflake snowflake() {
        //参数1为终端ID
        //参数2为数据中心ID
        return IdUtil.createSnowflake(1, 1);
    }

}
