package ua.skillsup.practice.restworkshop.repositories.entity;


import javax.persistence.*;

@Entity
public class SubTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "poryadok")
    private int order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubTaskEntity entity = (SubTaskEntity) o;

        if (order != entity.order) return false;
        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        if (title != null ? !title.equals(entity.title) : entity.title != null) return false;
        return task != null ? task.equals(entity.task) : entity.task == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + order;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubTaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
