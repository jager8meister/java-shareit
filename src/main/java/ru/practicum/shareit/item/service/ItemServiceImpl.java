package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Override
    public ItemDto add(long userId, ItemDto itemDto) {
        return null;
    }

    @Override
    public ItemDto edit(long userId, long itemId, ItemDto itemDto) {
        return null;
    }

    @Override
    public ItemDto findById(long itemId) {
        return null;
    }

    @Override
    public List<ItemDto> findAll(long userId) {
        return null;
    }

    @Override
    public List<ItemDto> search(String text) {
        return null;
    }
}
