package online.riedler.Helena;


import java.util.Objects;
import java.util.StringJoiner;

public class Guest {

    private Long id;
    private String name;
    private String email;
    private String checkIn;
    private String checkOut;
    private String raum;
    private String preis;
    private String gezahlt;
    private String sonstiges;

    public Guest() {
    }

    public Guest(Long id, String name, String email, String checkIn, String checkOut, String sonstiges, String raum,
                 String preis, String gezahlt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.sonstiges = sonstiges;
        this.raum = raum;
        this.preis = preis;
        this.gezahlt = gezahlt;
    }

    public String getGezahlt() {
        return gezahlt;
    }

    public String getPreis() {
        return preis;
    }

    public String getRaum() {
        return raum;
    }

    public void setGezahlt(String gezahlt) {
        this.gezahlt = gezahlt;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public void setRaum(String raum) {
        this.raum = raum;
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

    public String getCheckIn() {
        return checkIn;
    }

    public String getEmail() {
        return email;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public String getSonstiges() {
        return sonstiges;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setSonstiges(String sonstiges) {
        this.sonstiges = sonstiges;
    }

}