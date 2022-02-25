package webapp3.webapp3.model;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "monitors")
public class Monitor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id = null;

    //General Properties
    private String name;
    private String surname;
    private String NIF;
    private String email;
    private String password;
    private String address;
    private String postalCode;
    private String phone;
    private DateType birthday;

    //Monitor properties
    private boolean sickLeave;
    private DateType hiringDate;

    //Editable
    private String description;
    private String activityName;

    @Lob
    private Blob imageFile;

    private boolean image;

    //Constructors
    public Monitor(){

    }

    public Monitor(String name, String surname, String NIF, String email, String password, String address,
                   String postalCode, String phone, DateType birthday, boolean sickLeave,
                   DateType hiringDate, String activityName, String description) {
        this.name = name;
        this.surname = surname;
        this.NIF = NIF;
        this.email = email;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.birthday = birthday;
        this.sickLeave = sickLeave;
        this.hiringDate = hiringDate;
        this.activityName = activityName;
        this.description = description;
    }

    //Getters & setters
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public DateType getBirthday() {
        return birthday;
    }

    public void setBirthday(DateType birthday) {
        this.birthday = birthday;
    }

    public boolean isSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(boolean sickLeave) {
        this.sickLeave = sickLeave;
    }

    public DateType getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(DateType hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
