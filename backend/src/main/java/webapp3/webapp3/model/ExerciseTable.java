package webapp3.webapp3.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExerciseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name = "";

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description = "";

    @Lob
    private Blob image;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "exerciseTable")
    private List<UserExerciseTable> users = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Exercise> exercises = new ArrayList<>();

    public ExerciseTable(){

    }

    public ExerciseTable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public List<UserExerciseTable> getUsers() {
        return users;
    }

    public void setUsers(List<UserExerciseTable> exerciseTables) {
        this.users = users;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
