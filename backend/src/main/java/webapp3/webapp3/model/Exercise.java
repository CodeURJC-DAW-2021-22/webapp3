package webapp3.webapp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;



@Entity
@Table(name = "Exercise_tab")
public class Exercise {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id = null;

    private String name;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    private boolean image;

    public Exercise(){

    }

    public Exercise(String name) {
        super();
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getImage(){
        return this.image;
    }

    public void setImage(boolean image){
        this.image = image;
    }

    public void setImageFile(Blob image) {
        this.imageFile = image;
    }

    public Blob getImageFile() {
        return imageFile;
    }
}