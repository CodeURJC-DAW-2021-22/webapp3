package webapp3.webapp3;

import javax.persistence.*;
import java.awt.*;
import java.sql.Blob;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String price;
    private String description;
    private String room;
    private int capacity;
    private String schedule;

    @Lob
    private Blob imageFile;

    private boolean image;

    public Activity(){

    }

    public Activity(String name, String price, String description, String room, int capacity, String schedule) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.room = room;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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
}
