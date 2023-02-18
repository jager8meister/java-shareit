package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemDaoImpl implements ItemDao{
    private long id = 1;
    private final Map<Long, Item> items = new HashMap<>();
    private final Map<Long, Set<Long>> userItems = new HashMap<>();

    @Override
    public Item add(long userId, Item item) {
        item.setId(id++);
        items.put(item.getId(), item);
        if (userItems.get(userId) == null) {
            userItems.put(userId, new HashSet<>());
            Set<Long> ids = userItems.get(userId);
            ids.add(item.getId());
        }
        Set<Long> itemIds = userItems.get(userId);
        itemIds.add(item.getId());
        userItems.put(userId, itemIds);
        return item;
    }

    @Override
    public Item edit(long userId, long itemId, Item item) {
        Item userItem = items.get(itemId);
        if (item.getName() != null) {
            userItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            userItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            userItem.setAvailable(item.getAvailable());
        }
        if (item.getOwner() != null) {
            userItem.setOwner(item.getOwner());
        }
        items.put(itemId, userItem);
        return userItem;
    }

    @Override
    public Item findById(long itemId) {
        return items.get(itemId);
    }

    @Override
    public List<Item> findAll(long userId) {
        if (userItems.containsKey(userId)) {
            List<Item> result = new ArrayList<>();
            for (Long itemId: userItems.get(userId)) {
                result.add(items.get(itemId));
            }
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> search(String text) {
        List<Item> result = new ArrayList<>();
        for (Item item: items.values()) {
            if (item.getAvailable() && (item.getName().toLowerCase().contains(text.toLowerCase())
            || item.getDescription().toLowerCase().contains(text.toLowerCase()))) {
                result.add(item);
            }
        }
        return result;
    }
}
