package com.benchraid.benchraid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import com.benchraid.benchraid.entities.Task;
import com.benchraid.benchraid.entities.User;
import com.benchraid.benchraid.enums.Role;
import com.benchraid.benchraid.repositories.TaskRepository;
import com.benchraid.benchraid.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setId(null);
        user.setUsername("email");
        user.setPassword("password");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.EMPLOYEE));
        user.setCreatedDate(null);
        user.setUpdateDate(null);
        user.setRemoveDate(null);
        System.out.println("HELLO?");
        User savedUser  = userRepository.save(user);

        Task task = new Task();
        task.setId(null);
        task.setTitle("null");
        task.setDesc("null");

        System.out.println("HELLO?");
        Task savedTask  = taskRepository.save(task);


        System.out.println(savedUser.getId());
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());


        System.out.println(savedTask.getId());
        Task existTask = entityManager.find(Task.class, savedTask.getId());

        assertThat(existTask.getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    public void testFindUserByEmail() {
        String email  = "nurmukhamedali@gmail.com";
        User user = userRepository.findByUsername(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testFindUserByName() {
        String name  = "Hint";
        List<User> users = userRepository.findByLastnameOrFirstname(name);

        assertThat(users).isNotNull();
    }
}
