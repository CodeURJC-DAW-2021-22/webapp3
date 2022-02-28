package model;
import org.hibernate.type.DateType;

import java.sql.Blob;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String NIF;
    private String email;
    private String password;
    private String address;
    private String postalCode;
    private DateType birthday;
    private String phone;
    @Lob
    private Blob imageFile;
    private boolean image;

    private enum Demarcacion {member,admministrator,monitor}
    @ManyToMany
    private Demarcacion  userType;

    private DateType entryDate;
    private int height;
    private int weight;
    private int IBAN;
    private String medicalInfo;
    private LocalDate sickLeave;
    private DateType hiringDate;






}
