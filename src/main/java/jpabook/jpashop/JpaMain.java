package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);

            // 방법 1 (양방향 사용)
            // Order order = new Order();
            // order.addOrderItem(new OrderItem()); // order 객체를 만들어 order에 딸린 item인 orderItem을 쭈욱 추가할 수 있음

            // 방법 2 (양방향 사용 X, 단방향 O)
            // Order order = new Order();
            // em.persist(order);
            //
            // OrderItem orderItem = new OrderItem();
            // orderItem.setOrder(order);
            //
            // em.persist(orderItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
