package ua.skillsup.practice.restworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.skillsup.practice.restworkshop.models.Task;
import ua.skillsup.practice.restworkshop.repositories.entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByFinishTimeBetween(LocalDateTime finishTime, LocalDateTime finishTime2);
    List<TaskEntity> findByFinishTimeAfter(LocalDateTime finishTime);
    List<TaskEntity> findByFinishTimeBefore(LocalDateTime finishTime);

}
