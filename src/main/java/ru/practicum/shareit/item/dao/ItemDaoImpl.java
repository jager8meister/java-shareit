package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemDaoImpl implements ItemDao {
    private long id = 1;
    private final Map<Long, List<Item>> userItems = new HashMap<>();

    @Override
    public Item add(long userId, Item item) {
        item.setId(id++);
        if (userItems.get(userId) == null) {
            userItems.put(userId, new ArrayList<>());
            List<Item> ids = userItems.get(userId);
            ids.add(item);
        }
        return item;
    }

    @Override
    public Item edit(long userId, long itemId, Item item) {
        Item userItem = findById(itemId);
        Optional.ofNullable(item.getName()).ifPresent(itemOp -> userItem.setName(item.getName()));
        Optional.ofNullable(item.getDescription()).ifPresent(itemOp -> userItem.setDescription(item.getDescription()));
        Optional.ofNullable(item.getAvailable()).ifPresent(itemOp -> userItem.setAvailable(item.getAvailable()));
        Optional.ofNullable(item.getOwner()).ifPresent(itemOp -> userItem.setOwner(item.getOwner()));
        return userItem;
    }

    @Override
    public Item findById(long itemId) {
        List<Item> all = getAllItems();
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

    private List<Item> getAllItems() {
        List<Item> all = new ArrayList<>();
        for (List<Item> list: userItems.values()) {
            all.addAll(list);
        }
        return all;
    }
}
