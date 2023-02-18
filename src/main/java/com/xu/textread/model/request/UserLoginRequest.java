package com.xu.textread.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aniki
 * @CreteDate 2023/1/27 20:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    private  String userAccount;

    private  String userPassword;



}
