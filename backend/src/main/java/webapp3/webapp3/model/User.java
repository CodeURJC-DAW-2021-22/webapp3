package webapp3.webapp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="userTable")
public class User {

    //Interface for monitors columns: id and name
    public interface MonitorBasic{}
    //Interface for monitors columns: id, name, surname, NIF, email, address, PC, phone, activity, description
    public interface MonitorLog{}
    //Interface for members columns: id, name and NIF
    public interface MemberBasic{}
    //Interface for members columns: id, name, surname, NIF, email, address, PC, phone, height, weight, medical info
    public interface MemberLog{}

    //Columns
        //ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonView({MonitorBasic.class, MonitorLog.class, MemberBasic.class, MemberLog.class})
    private long id;

        //name
    @Column(nullable = false)
    @NotNull
    @JsonView({MonitorBasic.class, MonitorLog.class, MemberBasic.class, MemberLog.class})
    private String name = "";

        //surmane
    @Column(nullable = false)
    @NotNull
    @JsonView({MemberLog.class, MonitorLog.class})
    private String surname = "";

        //NIF
    @Column(nullable = false)
    @NotNull
    @JsonView({MemberBasic.class, MemberLog.class, MonitorLog.class})
    private String NIF = "";

        //email
    @Column(nullable = false)
    @NotNull
    @Email
    @JsonView({MemberLog.class, MonitorLog.class})
    private String email = "";

        //password
    @Column(nullable = false)
    @NotNull
    @JsonIgnore
    private String encodedPassword = "";

        //address
    @Column(nullable = false)
    @NotNull
    @JsonView({MemberLog.class, MonitorLog.class})
    private String address = "";

        //PC
    @Column(nullable = false)
    @NotNull
    @JsonView({MemberLog.class, MonitorLog.class})
    private String postalCode = "";

        //birthday
    @Column(nullable = false)
    @JsonIgnore
    @NotNull
    private Date birthday = new Date();

        //phone
    @Column(nullable = false)
    @NotNull
    @JsonView({MemberLog.class, MonitorLog.class})
    private String phone = "";

        //description
    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    @JsonView(MonitorLog.class)
    private String description = "";

    //image
    @Lob
    @JsonIgnore
    private Blob image;

    //type
    @Column(nullable = false)
    @NotNull
    private String userType = "";

    //Member
    //entryDate
    @JsonIgnore
    private Date entryDate;

    //height
    @JsonView(MemberLog.class)
    private int height;

    //weight
    @JsonView(MemberLog.class)
    private int weight;

    //medicalInfo
    @JsonView(MemberLog.class)
    private String medicalInfo;

    //activities
    @JsonView(MonitorLog.class)
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT1;

    @JsonView(MonitorLog.class)
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT2;

    @JsonView(MonitorLog.class)
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT3;

    //exerciseTables
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @JsonIgnore
    private List<UserExerciseTable> exerciseTables = new ArrayList<>();


    //Monitor
    //hiringDate
    @JsonIgnore
    private Date hiringDate;

    public User() {}

    public User(String name, String surname, String NIF, String email, String encodedPassword, String address, String postal_code, org.hibernate.type.DateType birthday, String phone_num, String member, org.hibernate.type.DateType entryDate, int height, int weight, String IBAN, String medicalInfo){
    }

    //Administrator constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode, DateType birthday,
                String phone, String password){
        this.name = name;
        this.surname = surname;
        this.NIF = NIF;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.birthday = birthday.getDate();
        this.phone = phone;
        this.userType = "administrator";
        this.encodedPassword = password;
    }


    //Monitor constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode, String phone,
                DateType birthday, DateType hiring, String description, String password) {
        this.name = name;
        this.surname = surname;
        this.NIF = NIF;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.birthday = birthday.getDate();
        this.phone = phone;
        this.hiringDate = hiring.getDate();
        this.description = description;
        this.userType = "monitor";
        this.encodedPassword = password;
    }


    //Client constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode,
                DateType birthday, String phone, DateType entryDate, int height, int weight, String medicalInfo, String password){
        this.name = name;
        this.surname = surname;
        this.NIF = NIF;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.birthday = birthday.getDate();
        this.phone = phone;
        this.entryDate = entryDate.getDate();
        this.height = height;
        this.weight = weight;
        this.medicalInfo = medicalInfo;
        this.userType = "member";
        this.encodedPassword = password;
    }

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
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

    public DateType getBirthday() {
        return new DateType(birthday);
    }

    public void setBirthday(DateType birthday) {
        this.birthday = birthday.getDate();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public boolean hasImage() { return this.image != null; }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public DateType getEntryDate() {
        return new DateType(entryDate);
    }

    public void setEntryDate(DateType entryDate) {
        this.entryDate = entryDate.getDate();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public DateType getHiringDate() {
        return new DateType(hiringDate);
    }

    public void setHiringDate(DateType hiringDate) {
        this.hiringDate = hiringDate.getDate();
    }

    public List<UserExerciseTable> getExerciseTables() {
        return exerciseTables;
    }

    public void setExerciseTables(List<UserExerciseTable> exerciseTables) {
        this.exerciseTables = exerciseTables;
    }

    public Activity getACT1() {
        return ACT1;
    }

    public void setACT1(Activity ACT1) {
        this.ACT1 = ACT1;
    }

    public Activity getACT2() {
        return ACT2;
    }

    public void setACT2(Activity ACT2) {
        this.ACT2 = ACT2;
    }

    public Activity getACT3() {
        return ACT3;
    }

    public void setACT3(Activity ACT3) {
        this.ACT3 = ACT3;
    }

    public void addExerciseTable(ExerciseTable exTab){
        UserExerciseTable usExTab = new UserExerciseTable(this, exTab);
        exerciseTables.add(usExTab);
        exTab.getUsers().add(usExTab);
    }
}