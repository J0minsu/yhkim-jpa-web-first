package jpa.web.part.first.service;

import jpa.web.part.first.domain.entity.*;
import jpa.web.part.first.domain.req.OrderSearchParam;
import jpa.web.part.first.repository.ItemRepository;
import jpa.web.part.first.repository.MemberRepository;
import jpa.web.part.first.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : jpa.web.part.first.service
 * fileName       : OrderService
 * author         : ms.jo
 * date           : 2023/09/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/14        ms.jo       최초 생성
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //C
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.fineOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    //R
    public List<Order> findOrders(OrderSearchParam orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }


    //U
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 entity 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }

    //D
}
