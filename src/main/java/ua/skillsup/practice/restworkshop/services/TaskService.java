package ua.skillsup.practice.restworkshop.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.practice.restworkshop.exception.NoSuchTaskException;
import ua.skillsup.practice.restworkshop.models.SubTask;
import ua.skillsup.practice.restworkshop.models.Task;
import ua.skillsup.practice.restworkshop.repositories.SubTaskRepository;
import ua.skillsup.practice.restworkshop.repositories.TaskRepository;
import ua.skillsup.practice.restworkshop.repositories.entity.SubTaskEntity;
import ua.skillsup.practice.restworkshop.repositories.entity.TaskEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepositoriy;
    private final SubTaskRepository subTaskRepository;

    public TaskService(TaskRepository taskRepositoriy, SubTaskRepository subTaskRepository) {
        this.taskRepositoriy = taskRepositoriy;
        this.subTaskRepository = subTaskRepository;
    }

    public List<Task> getAll(LocalDate from, LocalDate to) {
        List<TaskEntity> entities;
        if (from == null && to == null) {
            entities = taskRepositoriy.findAll();
        } else if (from == null) {
            entities = taskRepositoriy.findByFinishTimeBefore(convert(to));
        } else if (to == null) {
            entities = taskRepositoriy.findByFinishTimeAfter(convert(from));
        } else {
            entities = taskRepositoriy.findByFinishTimeBetween(convert(from), convert(to));
        }
        return taskRepositoriy.findAll().
                stream()
                .map(TaskService::convertFromEntity)
                .collect(Collectors.toList());
    }

    private static LocalDateTime convert(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }

    private static Task convertFromEntity(TaskEntity entity) {
        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setFinishTime(entity.getFinishTime());
        task.setSubTasks(entity.getSubTasks().stream().map(TaskService::convertFromEntity).collect(Collectors.toList()));
        return task;
    }

    private static SubTask convertFromEntity(SubTaskEntity entity) {
        SubTask subTask = new SubTask();
        subTask.setOrder(entity.getOrder());
        subTask.setTitle(entity.getTitle());
        return subTask;
    }

    @Transactional
    public void store(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setFinishTime(task.getFinishTime());
        taskRepositoriy.save(entity);
    }

    @Transactional
    public void addSubtask(long taskId, SubTask subTask) {
        Optional<TaskEntity> task = taskRepositoriy.findById(taskId);
        if (!task.isPresent()) {
            throw new NoSuchTaskException("No task with ID " + taskId);
        }
        SubTaskEntity entity = new SubTaskEntity();
        entity.setTitle(subTask.getTitle());
        entity.setOrder(subTask.getOrder());
        entity.setTask(task.get());
        subTaskRepository.save(entity);
    }
}
