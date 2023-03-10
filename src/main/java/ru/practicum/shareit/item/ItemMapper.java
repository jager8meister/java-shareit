package ru.practicum.shareit.item;

import lombok.NonNull;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    private ItemMapper() {

    }

    public static ItemDto toItemDto(@NonNull Item item) {
        return new ItemDto(
                item.getId(),
                item.getOwner(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest() : null
        );
    }

    public static Item toItem(@NonNull ItemDto itemDto) {
        return new Item(
                itemDto.getId(),
                itemDto.getOwner(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                itemDto.getRequest()
        );
    }

    public static List<ItemDto> toItemDtoList(@NonNull List<Item> itemList) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemList) {
            result.add(toItemDto(item));
        }
        return result;
    }

}
