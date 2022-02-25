package webapp3.webapp3;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class ExerciseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    private String name;

    @Lob
    private Blob imageFile;

    private String muscleGroup;

    public ExerciseTable() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
