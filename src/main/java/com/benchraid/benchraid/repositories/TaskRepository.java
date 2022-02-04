package com.benchraid.benchraid.repositories;


import com.benchraid.benchraid.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {
//    @Query("SELECT t FROM Task t WHERE t.author = ?1")
//    List<Task> findByAuthor (String author);
//    @Query("SELECT t FROM Task t where t.title = ?1 or t.desc = ?1")
//    List<Task> findByTitleOrDesc (String text);
}
