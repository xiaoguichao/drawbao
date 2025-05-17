package com.windpicker.drawbao.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserAccount {
    private Long id;
    private String wxid;
    private Integer chances;
    private BigDecimal rewards;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
