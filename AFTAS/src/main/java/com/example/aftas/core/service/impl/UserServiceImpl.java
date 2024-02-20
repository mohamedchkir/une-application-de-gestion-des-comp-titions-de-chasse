package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Get.GetUserDto;
import com.example.aftas.core.dao.model.dto.Store.UserDto;
import com.example.aftas.core.dao.model.entity.User;
import com.example.aftas.core.dao.repository.UserRepository;
import com.example.aftas.core.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto storeUserDto) {
        // Check if user with the same num already exists
        Optional<User> optional = userRepository.findById(storeUserDto.getNum());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("User with the same num already exists");
        }

        // Map DTO to entity
        User user = modelMapper.map(storeUserDto, User.class);
        user.setAccessionDate(LocalDate.now());
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<GetUserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, GetUserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetUserDto getUserByNum(Integer num) {
        Optional<User> optionalUser = userRepository.findByNum(num);

        return optionalUser.map(user -> modelMapper.map(user, GetUserDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
