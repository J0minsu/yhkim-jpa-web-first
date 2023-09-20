package jpa.web.part.first.component;

import jpa.web.part.first.domain.entity.*;
import jpa.web.part.first.domain.values.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.setting1();
        initService.setting2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void setting1() {

            Member memberA = createMember("userA", "서울","남문로", "21116");
            em.persist(memberA);

            Book bookA = createBook("고릴라 일대기 1", 15000, 10);
            em.persist(bookA);
            Book bookB = createBook("고릴라 일대기 2", 20000, 10);
            em.persist(bookB);

            OrderItem orderItemA = OrderItem.createOrderItem(bookA, 15000, 1);
            OrderItem orderItemB = OrderItem.createOrderItem(bookB, 20000, 2);
            Order order = Order.createOrder(memberA, createDelivery(memberA),
                    orderItemA, orderItemB);
            em.persist(order);

        }

        public void setting2() {

            Member memberC = createMember("userC", "서울","남문로", "21116");
            em.persist(memberC);

            Book bookC = createBook("고릴라 일대기 3", 25000, 10);
            em.persist(bookC);
            Book bookD = createBook("고릴라 일대기 4", 30000, 10);
            em.persist(bookD);

            OrderItem orderItemC = OrderItem.createOrderItem(bookC, 15000, 1);
            OrderItem orderItemD = OrderItem.createOrderItem(bookD, 20000, 2);
            Order order = Order.createOrder(memberC, createDelivery(memberC),
                    orderItemC, orderItemD);
            em.persist(order);

        }

        private Member createMember(String name, String city, String street,
                                    String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
        private Book createBook(String name, int price, int stockQuantity) {
            return Book.of(name, price, stockQuantity, null, null);
        }
        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }

}
