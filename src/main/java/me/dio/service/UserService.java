package me.dio.service;

import me.dio.Controller.dto.UpdateUserDto;
import me.dio.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    void updateById(Long id, UpdateUserDto updateUserDto);
    void deleteById(Long id);
}