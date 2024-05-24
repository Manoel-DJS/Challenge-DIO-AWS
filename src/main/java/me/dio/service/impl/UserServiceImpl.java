package me.dio.service.impl;

import me.dio.Controller.dto.UpdateUserDto;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    public void updateById(Long id,
                           UpdateUserDto updateUserDto){
        findById(id); // Reaproveitar para chamar as exceptions
        var userEntity = userRepository.findById(id);
        if (userEntity.isPresent()){
            var user = userEntity.get();

            if(updateUserDto.username() != null){
                user.setName(updateUserDto.username());
                // Trocando para novo nome
            }

            userRepository.save(user);
        }
    }

    public void deleteById(Long id){
        findById(id);
        userRepository.deleteById(id);
    }

}