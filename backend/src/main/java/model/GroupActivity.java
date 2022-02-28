package model;

import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;

@Entity
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

}
