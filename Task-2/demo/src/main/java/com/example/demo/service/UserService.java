package com.example.demo.service;

import com.example.demo.model.request.UserDTO;
import com.example.demo.model.response.BaseResponse;


public interface UserService {

    BaseResponse registerAccount(UserDTO userDTO);
}
