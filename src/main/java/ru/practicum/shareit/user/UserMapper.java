package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private UserMapper() {}

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDto> toUserDtoList(List<User> userList) {
        List<UserDto> result = new ArrayList<>();
        for (User user : userList) {
            result.add(toUserDto(user));
        }
        return result;
    }

    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail()
        );
    }
}
