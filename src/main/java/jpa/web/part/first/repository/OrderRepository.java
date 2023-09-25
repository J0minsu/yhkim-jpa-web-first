package jpa.web.part.first.repository;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.req.OrderSearchParam;
import jpa.web.part.first.domain.res.FindOrderRes;
import jpa.web.part.first.domain.res.OrderFlatRes;
import jpa.web.part.first.domain.res.OrderItemQueryRes;
import jpa.web.part.first.domain.res.OrderQueryRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * packageName    : jpa.web.part.first.repository
 * fileName       : OrderRepository
 * author         : ms.jo
 * date           : 2023/09/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/14        ms.jo       최초 생성
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByUsername(String username) {
        return em.createQuery("SELECT o FROM Order o JOIN FETCH o.member m WHERE m.name = :username")
                        .setParameter("username", username)
                        .getResultList();
    }

    public List<Order> findAll(OrderSearchParam param) {
        log.info("param :: {}", param);
        String query = "SELECT o FROM Order o JOIN o.member m WHERE m.name LIKE CONCAT('%', :name, '%')" + (param.getOrderStatus() != null ? " AND o.status = :status" : "");
        log.info("query :: {}", query);
        Query baseQuery = em.createQuery(query).setParameter("name", param.getKeyword());

        if(param.getOrderStatus() != null) {
            baseQuery.setParameter("status", param.getOrderStatus());
        }

        List<Order> resultList = baseQuery.getResultList();
        return resultList;
    }

    public List<Order> findAllByFetch() {

        return em.createQuery("""
                SELECT o
                  FROM Order o
                    JOIN FETCH o.member m
                    JOIN FETCH o.delivery d
                    JOIN FETCH o.orderItems oi""", Order.class)
                .getResultList();

    }

    public List<FindOrderRes> findAllByJpql() {

        return em.createQuery("""
                SELECT new jpa.web.part.first.domain.res.FindOrderRes(o.id, m.name, SUM(oi.orderPrice * oi.count), SUM(oi.count), o.orderDate, o.status, d.address)
                  FROM Order o
                    JOIN o.member m
                    JOIN o.delivery d
                    JOIN o.orderItems oi
                 GROUP BY o.id""", FindOrderRes.class)
                .getResultList();

    }

    public List<Order> findAllByJpqlMany() {
        return em.createQuery("""
                SELECT DISTINCT o FROM Order o
                  JOIN FETCH o.member m
                  JOIN FETCH o.delivery d
                  JOIN FETCH o.orderItems oi
                  JOIN FETCH oi.item i""", Order.class)
                .getResultList();
    }

    public List<Order> findAllByJpqlManyWithPage(int offset, int limit) {
        return em.createQuery("""
                SELECT DISTINCT o FROM Order o
                  JOIN FETCH o.member m
                  JOIN FETCH o.delivery d""", Order.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
    }


    /**
     * Using Dto
     * @return
     */

    public List<OrderQueryRes> findAllByJpqlManyUsingDto() {

        List<OrderQueryRes> orders = findOrders();

        orders.forEach(order -> {
            List<OrderItemQueryRes> items = findOrderItems(order.getOrderId());
            order.setItems(items);
        });

        return orders;

    }

    private List<OrderItemQueryRes> findOrderItems(Long orderId) {
        return em.createQuery("""
                    SELECT new jpa.web.part.first.domain.res.OrderItemQueryRes(oi.id, i.name, oi.orderPrice, oi.count)
                      FROM OrderItem oi
                        JOIN oi.item i
                     WHERE oi.order.id = :orderId
                """, OrderItemQueryRes.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryRes> findOrders() {
        return em.createQuery("""
                        SELECT new jpa.web.part.first.domain.res.OrderQueryRes(o.id, m.name, o.orderDate, o.status, d.address)
                          FROM Order o
                           JOIN o.member m
                           JOIN o.delivery d""", OrderQueryRes.class)
                .getResultList();
    }


    /**
     * 1차 최적화 ( pre call order items by order item that formatting Map )
     * @return
     */

    public List<OrderQueryRes> findAllByDtoUsingOptimization() {

        List<OrderQueryRes> orders = findOrders();

        List<Long> idLists = findOrderIds(orders);

        Map<Long, List<OrderItemQueryRes>> orderItemMap = findOrderItemMap(idLists);

        orders.forEach(item -> item.setItems(orderItemMap.get(item.getOrderId())));

        return orders;
    }

    private Map<Long, List<OrderItemQueryRes>> findOrderItemMap(List<Long> idLists) {
        List<OrderItemQueryRes> orderItems = em.createQuery("""
                        SELECT new jpa.web.part.first.domain.res.OrderItemQueryRes(oi.order.id, i.name, oi.orderPrice, oi.count)
                                              FROM OrderItem oi
                                                JOIN oi.item i
                                             WHERE oi.order.id IN :orderIds""", OrderItemQueryRes.class)
                .setParameter("orderIds", idLists)
                .getResultList();

        Map<Long, List<OrderItemQueryRes>> orderItemMap = orderItems.stream().collect(Collectors.groupingBy(oi -> oi.getId()));
        return orderItemMap;
    }

    private static List<Long> findOrderIds(List<OrderQueryRes> orders) {
        List<Long> idLists = orders.stream().map(order -> order.getOrderId()).toList();
        return idLists;
    }


    public List<OrderFlatRes> findAllByDtoUsingFlat() {

        return em.createQuery("""
                        SELECT new jpa.web.part.first.domain.res.OrderFlatRes(o.id, m.name, o.orderDate, o.status, d.address, oi.id, i.name, oi.orderPrice, oi.count)
                          FROM Order o
                            JOIN o.member m
                            JOIN o.delivery d
                            JOIN o.orderItems oi
                            JOIN oi.item i""", OrderFlatRes.class).getResultList();

    }
}
