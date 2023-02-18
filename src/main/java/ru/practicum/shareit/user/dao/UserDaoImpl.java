package ru.practicum.shareit.user.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private long id;
    private Map<Long, User> userMap = new HashMap<>();
    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(long id) {
        invalidUserIdCheck(id);
        return userMap.get(id);
    }

    @Override
    public User save(User user) {
        valid(user);
        user.setId(++id);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User edit(long id, User user) {
        invalidUserIdCheck(id);
        User userFromMap = userMap.get(id);
        if (user.getEmail() != null && !user.getEmail().equals(userFromMap.getEmail())) {
            checkEmailUsage(user);
            userFromMap.setEmail(user.getEmail());
        }
        if (user.getName() != null) {
            userFromMap.setName(user.getName());
        }
        userMap.put(id, userFromMap);
        return userFromMap;
    }

    private void invalidUserIdCheck(long id) {
        if (!userMap.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id");
        }
    }

    @Override
    public void deleteUser(Long id) {
        invalidUserIdCheck(id);
        userMap.remove(id);
    }

    private void valid(User user) {
        checkEmailUsage(user);
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "invalid input in user email");
        }
    }

    private void checkEmailUsage(User user) {
        for (User userCheck : userMap.values()) {
            if (user.getEmail().equals(userCheck.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "email is already in use");
            }
        }
    }
}
