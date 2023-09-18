package jpa.web.part.first.repository;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.req.OrderSearchParam;
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
}
