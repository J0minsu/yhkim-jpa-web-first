package jpa.web.part.first.controller.api;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.req.OrderSearchParam;
import jpa.web.part.first.domain.res.FindOrderRes;
import jpa.web.part.first.domain.res.Result;
import jpa.web.part.first.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * nToOne
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public Result ordersV1() {

        List<Order> orders = orderRepository.findAll(new OrderSearchParam());

        List<FindOrderRes> list = orders.stream()
                .map(FindOrderRes::new)
                .toList();

        return Result.of(list);

    }

    @GetMapping("api/v2/simple-orders")
    public Result ordersV2() {

        return Result.of(orderRepository.findAllByFetch().stream().map(FindOrderRes::new).toList());

    }

    @GetMapping("api/v3/simple-orders")
    public Result ordersV3() {

        return Result.of(orderRepository.findAllByFetch().stream().map(FindOrderRes::new).toList());

    }
}
