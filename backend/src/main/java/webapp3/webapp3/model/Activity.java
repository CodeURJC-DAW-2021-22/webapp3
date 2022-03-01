package webapp3.webapp3.model;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name = "activities_tab")
public class Activity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id = null;

    private String name;
    private String price;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String room;
    private int capacity;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    @Lob
    private Blob imageFile;

    private boolean image;

    public Activity(){

    }

    public Activity(String name){
        this.name = name;
    }

    public Activity(String name, String price, String description, String room, int capacity, String monday,
                    String tuesday, String wednesday, String thursday, String friday) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.room = room;
        this.capacity = capacity;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
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