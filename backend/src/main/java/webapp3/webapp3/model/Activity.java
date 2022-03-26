package webapp3.webapp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @JsonView(User.MonitorLog.class)
    private String name = "";

    @Column(nullable = false)
    @NotNull
    private String room = "";

    @Column(nullable = false)
    @NotNull
    private int capacity;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description = "";

    @Column(nullable = false)
    @NotNull
    private int price;

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    private String monitorName;

    @Lob
    @JsonIgnore
    private Blob image;

    public Activity(){
    }

    public Activity(String name, int price, String description, String room, int capacity, String monday,
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
  
    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }
}
