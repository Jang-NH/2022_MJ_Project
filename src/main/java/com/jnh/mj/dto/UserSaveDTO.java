package com.jnh.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {

    @NotBlank(message = "이메일은 필수 입력입니다.")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String userPassword;

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    private String userNickname;
}
