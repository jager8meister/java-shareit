package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto add(@RequestHeader("X-Sharer-User-Id") long userId,
                       @Valid @RequestBody ItemDto itemDto) {
        return itemService.add(userId, itemDto);
    }

    @PatchMapping("/{id}")
    public ItemDto edit(@RequestHeader("X-Sharer-User-Id") long userId,
                        @PathVariable long id,
                        @RequestBody ItemDto itemDto) {
        return itemService.edit(userId, id, itemDto);
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable long id) {
        return itemService.findById(id);
    }

    @GetMapping
    public List<ItemDto> findAll(@RequestHeader("X-Sharer-User-Id") long id) {
        return itemService.findAll(id);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        if (StringUtils.isBlank(text)) {
            return List.of();
        }
        return itemService.search(text);
    }
}
