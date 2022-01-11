package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //멤버 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");
            em.persist(member); //저장
            tx.commit();


            List<Member> members = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member1 : members) {
                System.out.println("member = " + member1.getName());
            }

        } catch (Exception var8) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
