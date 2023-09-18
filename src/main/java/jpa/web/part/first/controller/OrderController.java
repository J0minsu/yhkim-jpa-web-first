package jpa.web.part.first.controller;

import jpa.web.part.first.domain.entity.Item;
import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.req.OrderSearchParam;
import jpa.web.part.first.service.ItemService;
import jpa.web.part.first.service.MemberService;
import jpa.web.part.first.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : jpa.web.part.first.controller
 * fileName       : OrderController
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
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/form")
    public String orderPage(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "page/order/orderForm";

    }

    @GetMapping
    public String orderList(@ModelAttribute("orderSearch") OrderSearchParam orderSearch, Model model) {

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "page/order/orderList";

    }

    @PostMapping
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") Long id) {

        orderService.cancelOrder(id);

        return "redirect:/orders";
    }
}
