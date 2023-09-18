package jpa.web.part.first.controller;

import jpa.web.part.first.domain.entity.Book;
import jpa.web.part.first.domain.req.BookFormReq;
import jpa.web.part.first.domain.res.BookListRes;
import jpa.web.part.first.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : jpa.web.part.first.controller
 * fileName       : ItemController
 * author         : ms.jo
 * date           : 2023/09/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/18        ms.jo       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String listPage(Model model) {

        List<BookListRes> items = itemService.findItems().stream().map(item ->
                        new BookListRes(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity()))
                .collect(Collectors.toList());

        model.addAttribute("items", items);

        return "page/items/itemList";
    }

    @GetMapping("/new")
    public String registPage(Model model) {
        model.addAttribute("bookFormReq", new BookFormReq());

        return "page/items/createItemForm";
    }

    @PostMapping("/new")
    public String regist(BookFormReq form) {
        Book book = Book.of(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/{itemId}/edit")
    public String updatePage(@PathVariable("itemId") Long itemId, Model model) {

        Book item = (Book) itemService.findOne(itemId);

        BookFormReq bookFormReq = new BookFormReq();
        bookFormReq.setId(item.getId());
        bookFormReq.setName(item.getName());
        bookFormReq.setPrice(item.getPrice());
        bookFormReq.setStockQuantity(item.getStockQuantity());
        bookFormReq.setAuthor(item.getAuthor());
        bookFormReq.setIsbn(item.getIsbn());

        model.addAttribute("bookFormReq", bookFormReq);

        return "page/items/updateItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable("itemId") Long itemId, @ModelAttribute(name = "bookFormReq") BookFormReq bookFormReq) {

        /**
         * 현업에서는 소유자와 수정자가 일치하는지 체크
         */

        if(itemId == bookFormReq.getId()) {
//            throw new
        }

        /*
        Book book = Book.of(bookFormReq.getId(), bookFormReq.getName(), bookFormReq.getPrice(), bookFormReq.getStockQuantity(), bookFormReq.getAuthor(), bookFormReq.getIsbn());

        itemService.saveItem(book);
        */

        itemService.updateItem(itemId, bookFormReq.getName(), bookFormReq.getPrice(), bookFormReq.getStockQuantity());

        return "redirect:/items";

    }
}
