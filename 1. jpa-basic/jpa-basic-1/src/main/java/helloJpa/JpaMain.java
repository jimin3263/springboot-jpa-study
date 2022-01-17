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

            Book book = new Book();
            book.setAuthor("하하하");
            book.setIsbn("카카");
            em.persist(book);

            //팀 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            //멤버 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");
            member.changeTeam(team); //팀 저장
            em.persist(member); //저장

            //find를 사용하면 1차 캐시에서 가져오므로
            em.flush(); //영속성 컨텍스트에 있는 것을 DB에 날림
            em.clear(); //영속성 컨텍스트 초기화

            //팀에 속한 멤버들 조회
            Team team1 = em.find(Team.class, team.getId());
            List<Member> members = team1.getMembers();

            for (Member member1 : members) {
                System.out.println(member1.getName());
            }


            em.flush();
            em.clear();
            tx.commit();


//            List<Member> members = em.createQuery("select m from Member as m", Member.class).getResultList();
//            for (Member member1 : members) {
//                System.out.println("member = " + member1.getName());
//            }

        } catch (Exception var8) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
