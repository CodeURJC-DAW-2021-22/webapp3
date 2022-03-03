package model;
import org.hibernate.type.DateType;

import java.sql.Blob;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "User_Table")
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
    private Demarcacion  userType;

    private DateType entryDate;
    private int height;
    private int weight;
    private int IBAN;
    private String medicalInfo;
    private LocalDate sickLeave;
    private DateType hiringDate;


    /*relacion*/
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name ="GroupActivity_id")
    private GroupActivity groupActivity;

    public User(){}

    public User(String name, String surname, String NIF, String email,
                String password, String address, String postalCode, DateType birthday, String phone,
                DateType entryDate, int height,int weight, int IBAN, String medicalInfo,LocalDate sickLeave,DateType hiringDate){
        super();
        this.name= name;
        this.surname= surname;
        this.NIF=NIF;
        this.email=email;
        this.password=password;
        this.address=address;
        this.postalCode=postalCode;
        this.birthday=birthday;
        this.phone=phone;
        this.entryDate=entryDate;
        this.height=height;
        this.weight=weight;
        this.IBAN=IBAN;
        this.medicalInfo=medicalInfo;
        this.sickLeave=sickLeave;
        this.hiringDate=hiringDate;


    }

    public void setName(String name){
        this.name= name;
    }
    public String getName(){
        return name;
    }

    public LocalDate getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(LocalDate sickLeave) {
        this.sickLeave = sickLeave;
    }

    public DateType getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(DateType hiringDate) {
        this.hiringDate = hiringDate;
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
        return birthday;
    }

    public void setBirthday(DateType birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Demarcacion getUserType() {
        return userType;
    }

    public void setUserType(Demarcacion userType) {
        this.userType = userType;
    }

    public DateType getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(DateType entryDate) {
        this.entryDate = entryDate;
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

    public int getIBAN() {
        return IBAN;
    }

    public void setIBAN(int IBAN) {
        this.IBAN = IBAN;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GroupActivity getGroupActivity() {
        return groupActivity;
    }

    public void setGroupActivity(GroupActivity groupActivity) {
        this.groupActivity = groupActivity;
    }

}
