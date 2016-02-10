package todo.domain.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import todo.domain.model.Todo;

// (1)
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT COUNT(t) FROM Todo t WHERE t.finished = :finished") // (2)
    long countByFinished(@Param("finished") boolean finished); // (3)

}

