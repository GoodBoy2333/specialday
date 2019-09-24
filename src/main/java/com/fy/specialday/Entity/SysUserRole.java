package com.fy.specialday.Entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SysUserRole {
    /**
    * 用户主键
    */
    private BigDecimal userid;

    /**
    * 角色主键
    */
    private BigDecimal roleid;
}