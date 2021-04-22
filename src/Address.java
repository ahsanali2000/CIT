import java.util.Arrays;
import java.util.List;

public class Address {
    int homeNumber;
    String street;
    String city;

    public Address(int homeNumber, String street, String city) {
        this.homeNumber = homeNumber;
        this.street = street;
        this.city = city;
    }

    public Address() {
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public int getHomeNumber() {
        return homeNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void setAll(int homeNumber, String street, String city) {
        this.homeNumber = homeNumber;
        this.street = street;
        this.city = city;
    }
    public List<Object> getAll(){
        return Arrays.asList(homeNumber, street, city);
    }

    public String print() {
        return "Address{" +
                "homeNumber=" + homeNumber +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
