package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetUserDto;
import com.example.aftas.core.dao.model.dto.Store.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);

    List<GetUserDto> getAllUsers();

    GetUserDto getUserByNum(Integer num);

}
