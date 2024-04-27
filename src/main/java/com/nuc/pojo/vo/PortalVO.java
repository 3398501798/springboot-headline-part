package com.nuc.pojo.vo;

import lombok.Data;

@Data
public class PortalVO {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize =10;
}
