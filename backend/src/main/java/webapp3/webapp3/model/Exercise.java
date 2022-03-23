package webapp3.webapp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name = "";

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description = "";

    @Column(nullable = false)
    @NotNull
    private String material = "";

    @Lob
    @JsonIgnore
    private Blob image;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "exercises")
    @JsonIgnore
    private List<ExerciseTable> exerciseTables = new ArrayList<>();
    //JSON VIEWS BAELDUNG

    public Exercise(){

    }

    public Exercise(String name, String description, String material) {
        this.name = name;
        this.description = description;
        this.material = material;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public List<ExerciseTable> getExerciseTables() {
        return exerciseTables;
    }

    public void setExercisesTables(List<ExerciseTable> exerciseTables) {
        this.exerciseTables = exerciseTables;
    }

}
