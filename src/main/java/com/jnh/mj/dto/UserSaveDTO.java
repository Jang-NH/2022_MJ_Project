package com.jnh.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {
    private String userEmail;
    private String userPassword;
    private String userNickname;
}
