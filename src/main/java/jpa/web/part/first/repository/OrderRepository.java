package jpa.web.part.first.repository;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.req.OrderSearchParam;
import jpa.web.part.first.domain.res.FindOrderRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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
}
