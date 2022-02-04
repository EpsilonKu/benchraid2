package com.benchraid.benchraid;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import com.benchraid.benchraid.entities.Task;
import com.benchraid.benchraid.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateTask(){
        Task task = new Task();
        task.setId(null);
        task.setTitle("email");
        task.setDesc("password");

        Task savedTask  = taskRepository.save(task);

        System.out.println(savedTask.getId());
        Task existTask = entityManager.find(Task.class, savedTask.getId());

        assertThat(existTask.getTitle()).isEqualTo(task.getTitle());
    }

//    @Test
//    public void testFindtaskByEmail() {
//        String email  = "nurmukhamedali@gmail.com";
//        task task = taskRepository.findBytaskname(email);
//
//        assertThat(task).isNotNull();
//    }
//
//    @Test
//    public void testFindtaskByName() {
//        String name  = "Hint";
//        List<task> tasks = taskRepository.findByLastnameOrFirstname(name);
//
//        assertThat(tasks).isNotNull();
//    }
}
