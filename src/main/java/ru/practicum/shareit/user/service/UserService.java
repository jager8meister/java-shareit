package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto findById(long id);

    UserDto addUser(UserDto userDto);

    UserDto edit(long userId, UserDto userDto);

    void deleteUser(Long userId);
}
