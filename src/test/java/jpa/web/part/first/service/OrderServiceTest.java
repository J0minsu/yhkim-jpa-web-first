package jpa.web.part.first.service;

import jpa.web.part.first.domain.entity.Book;
import jpa.web.part.first.domain.entity.Item;
import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.enums.OrderStatus;
import jpa.web.part.first.domain.exception.NotEnoughStockException;
import jpa.web.part.first.domain.values.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


/**
 * packageName    : jpa.web.part.first.service
 * fileName       : OrderServiceTest
 * author         : ms.jo
 * date           : 2023/09/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/14        ms.jo       최초 생성
 */

@SpringBootTest
//@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
@Slf4j
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;

    @Test
    public void 상품주문() throws Exception {

        //given
        Member member = getMember();

        Book book = getBook("JPA BOOK", 14000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order findOrder = orderService.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, findOrder.getStatus());
        Assertions.assertEquals(1, findOrder.getOrderItems().size());
        Assertions.assertEquals(book.getPrice() * orderCount, findOrder.getTotalPrice());
        Assertions.assertEquals(8, book.getStockQuantity());

    }


    @Test
    public void 상품주문_재고수량초과() throws Exception {

        //given
        Member member = getMember();
        Item item = getBook("JPA BOOK", 14000, 10);

        int orderCount = 11;
        //when
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });

    }

    @Test
    public void 주문취소() throws Exception {

        //given
        Member member = getMember();
        Book book = getBook("JPA BOOK", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order findOrder = orderService.findOne(orderId);

        Assertions.assertEquals(OrderStatus.CANCEL, findOrder.getStatus());
        Assertions.assertEquals(10, book.getStockQuantity());

    }

    private Book getBook(String name, int price, int stockQuantity) {
        Book book = Book.of(name, price, stockQuantity, null, null);
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("MemberA");
        member.setAddress(new Address("Seoul", "River", "11234"));
        em.persist(member);
        return member;
    }
}
