package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
 
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,30}", message = "영문, 숫자, 특수기호를 조합하여 8자 이상으로 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;
    
    private String companyname;
    
    @NotEmpty(message = "이름은 필수항목입니다.")
    private String username;
    
    @NotEmpty(message = "연락처는 필수항목입니다.")
    private String phonenumber;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

}



