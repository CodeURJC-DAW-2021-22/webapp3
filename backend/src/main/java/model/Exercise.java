package model;

import java.sql.Blob;
import javax.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String name;
    private String material;

    @Lob
    private Blob imageFile;
    private boolean image;












}
