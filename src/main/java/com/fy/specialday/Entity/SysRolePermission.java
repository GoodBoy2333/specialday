package com.fy.specialday.Entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SysRolePermission {
    /**
    * 角色主键
    */
    private BigDecimal roleid;

    /**
    * 权限主键
    */
    private BigDecimal permissionid;
}