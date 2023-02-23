package ru.practicum.shareit.user;

import lombok.NonNull;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private UserMapper() {

    }

    public static UserDto toUserDto(@NonNull User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDto> toUserDtoList(@NonNull List<User> userList) {
        List<UserDto> result = new ArrayList<>();
        for (User user : userList) {
            result.add(toUserDto(user));
        }
        return result;
    }

    public static User toUser(@NonNull UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail()
        );
    }
}
