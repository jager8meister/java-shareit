package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemDaoImpl implements ItemDao {
    private long id = 1;
    private final Map<Long, Set<Item>> userItems = new HashMap<>();

    @Override
    public Item add(long userId, Item item) {
        item.setId(id++);
        if (userItems.get(userId) == null) {
            userItems.put(userId, new HashSet<>());
            Set<Item> ids = userItems.get(userId);
            ids.add(item);
        }
        return item;
    }

    @Override
    public Item edit(long userId, long itemId, Item item) {
        Item userItem = findById(itemId);
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
        return userItem;
    }

    @Override
    public Item findById(long itemId) {
        Set<Item> all = getAllItems();
        for (Item item : all) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<Item> findAll(long userId) {
        if (userItems.containsKey(userId)) {
            List<Item> result = new ArrayList<>(userItems.get(userId));
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> search(String text) {
        List<Item> result = new ArrayList<>();
        for (Item item: getAllItems()) {
            if (item.getAvailable() && (item.getName().toLowerCase().contains(text.toLowerCase())
            || item.getDescription().toLowerCase().contains(text.toLowerCase()))) {
                result.add(item);
            }
        }
        return result;
    }

    private Set<Item> getAllItems() {
        Set<Item> all = new HashSet<>();
        for (Set<Item> set: userItems.values()) {
            all.addAll(set);
        }
        return all;
    }
}
