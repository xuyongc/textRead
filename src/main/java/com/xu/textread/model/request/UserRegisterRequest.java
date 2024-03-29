package com.xu.textread.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xyc
 * @CreteDate 2023/1/27 20:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String userAccount;

    private String userPassword;

    private String userCheckPassword;
}
