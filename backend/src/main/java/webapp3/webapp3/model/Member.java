package webapp3.webapp3.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "member_tab")
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id = null;

    //General Properties
    private String name;
    private String surname;
    private String usrname;
    private String password;
    private String email;
    private String NIF;
    private DateType birthday;
    private String genre;
    private String height;
    private String weight;
    private String address;
    private String postalCode;
    private String phone;
    private String creditCard;
    private String additionalInfo;


    @Lob
    private Blob imageFile;

    private boolean image;

    //Constructors
    public Member(){
    }

    public Member(String name, String surname, String usrname, String password, String email, String NIF, DateType birthday,
                  String genre, String height, String weight, String address, String postalCode, String phone,
                  String creditCard, String additionalInfo) {
        this.name = name;
        this.surname = surname;
        this.usrname = usrname;
        this.password = password;
        this.email = email;
        this.NIF = NIF;
        this.birthday = birthday;
        this.genre = genre;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.creditCard = creditCard;
        this.additionalInfo = additionalInfo;

    }

    //Getters & setters
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

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateType getBirthday() {
        return birthday;
    }

    public void setBirthday(DateType birthday) {
        this.birthday = birthday;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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