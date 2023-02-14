package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@AllArgsConstructor
@Setter
@Getter
public class Item {
    private Long id;
    private User owner;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
