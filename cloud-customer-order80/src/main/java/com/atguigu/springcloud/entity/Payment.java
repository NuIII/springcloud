package com.atguigu.springcloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author QiCheng.Wang
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Payment implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String serial;


    public static final String ID = "id";

    public static final String SERIAL = "serial";

}
