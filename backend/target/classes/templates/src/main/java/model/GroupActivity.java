package model;

import java.sql.Blob;
import javax.persistence.*;

@Entity
@Table(name = "GroupActivity_Table")
public class GroupActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String room;
    private int capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int price;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    @Lob
    private Blob imageFile;
    private boolean image;

    public GroupActivity(){}

    public GroupActivity(String name, String room, int capacity,String description,
                         int price, String monday, String tuesday, String wednesday,
                         String thursday, String friday){
            super();
            this.name= name;
            this.room = room;
            this.capacity= capacity;
            this.description=description;
            this.price=price;
            this.monday=monday;
            this.tuesday=tuesday;
            this.wednesday=wednesday;
            this.thursday=thursday;
            this.friday=friday;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
