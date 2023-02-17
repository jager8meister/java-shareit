package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService{
    private final UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.toUserDtoList(userDao.findAll());
    }

    @Override
    public UserDto findById(long id) {
        return UserMapper.toUserDto(userDao.findById(id));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return UserMapper.toUserDto(userDao.save(UserMapper.toUser(userDto)));
    }

    @Override
    public UserDto edit(long userId, UserDto userDto) {
        return UserMapper.toUserDto(userDao.edit(userId, UserMapper.toUser(userDto)));
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }
}
