package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;
    private final UserService userService;

    @Override
    public ItemDto add(long userId, ItemDto itemDto) {
        User user = UserMapper.toUser(userService.findById(userId));
        itemDto.setOwner(user);
        Item item = itemDao.add(userId, ItemMapper.toItem(itemDto));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto edit(long userId, long itemId, ItemDto itemDto) {
        if (!itemDao.findById(itemId).getOwner().getId().equals(userId)) {
            log.error("Invalid user id " + userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id");
        }
        return ItemMapper.toItemDto(itemDao.edit(userId, itemId, ItemMapper.toItem(itemDto)));
    }

    @Override
    public ItemDto findById(long itemId) {
        return ItemMapper.toItemDto(itemDao.findById(itemId));
    }

    @Override
    public List<ItemDto> findAll(long userId) {
        return ItemMapper.toItemDtoList(itemDao.findAll(userId));
    }

    @Override
    public List<ItemDto> search(String text) {
        return ItemMapper.toItemDtoList(itemDao.search(text));
    }
}
