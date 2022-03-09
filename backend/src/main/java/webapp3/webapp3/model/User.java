package webapp3.webapp3.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name = "";

    @Column(nullable = false)
    @NotNull
    private String surname = "";

    @Column(nullable = false)
    @NotNull
    private String NIF = "";

    @Column(nullable = false)
    @NotNull
    private String email = "";

    @Column(nullable = false)
    @NotNull
    private String password = "";

    @Column(nullable = false)
    @NotNull
    private String address = "";

    @Column(nullable = false)
    @NotNull
    private String postalCode = "";

    @Column(nullable = false)
    @NotNull
    private Date birthday = new Date();

    @Column(nullable = false)
    @NotNull
    private String phone = "";

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description = "";
    @Lob
    private Blob image;

    @Column(nullable = false)
    @NotNull
    private String userType = "";

    //Member
    private Date entryDate;
    private int height;
    private int weight;
    private String IBAN;
    private String medicalInfo;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT1;
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT2;
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Activity ACT3;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<UserExerciseTable> exerciseTables = new ArrayList<>();


    //Monitor
    private Date hiringDate;


    public User(){

    }


    //Administrator constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode, DateType birthday,
                String phone){
        this.name = name;
        this.surname = surname;
        this.NIF = NIF;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.birthday = birthday.getDate();
        this.phone = phone;
        this.userType = "administrator";
        this.password = "password";
    }


    //Monitor constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode, String phone,
                DateType birthday, DateType hiring, String description) {
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
        this.password = NIF;
    }


    //Client constructor
    public User(String name, String surname, String NIF, String email, String address, String postalCode,
                DateType birthday, String phone, DateType entryDate, int height, int weight, String IBAN, String medicalInfo){
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
        this.IBAN = IBAN;
        this.medicalInfo = medicalInfo;
        this.userType = "member";
        this.password = "password";
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
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
