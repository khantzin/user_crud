package com.kz.bookingsystem.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
