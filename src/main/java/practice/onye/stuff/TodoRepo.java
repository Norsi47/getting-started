package practice.onye.stuff;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TodoRepo extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByDone(boolean done);
//    public static void main(String[] args) {
//        TestTestBox testTestBox = new TestTestBox();
//        testTestBox.setDe
//    }

}
