import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student extends Person{
    int studentId;
    Person person;
    String address;
    String phone;
    ArrayList<Course> courses;

    public Student(String firstName, String lastName, Date dateOfBirth, String cityOfBirth, int studentId, Person person, String address, String phone, ArrayList<Course> courses) {
        super(firstName, lastName, dateOfBirth, cityOfBirth);
        this.studentId = studentId;
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.courses = courses;
    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public void setAll(String firstName, String lastName, Date dateOfBirth, String cityOfBirth, int studentId, Person person, String address, String phone, ArrayList<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityOfBirth = cityOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.studentId = studentId;
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.courses = courses;
    }
    public List<Object> getAll(){
        return Arrays.asList(firstName, lastName, dateOfBirth, cityOfBirth, studentId, person , address, phone, courses);
    }

    public String print() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                ", studentId=" + studentId +
                ", person=" + person +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", courses=" + courses +
                '}';
    }
}
