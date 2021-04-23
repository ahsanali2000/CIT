import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Student extends Person{
    int studentId;
    String address;
    String phone;
    ArrayList<StudentCourse> courses;

    public Student(int studentId,String firstName, String lastName, Date dateOfBirth, String cityOfBirth, String address, String phone, ArrayList<StudentCourse> courses) {
        super(firstName, lastName, dateOfBirth, cityOfBirth);
        this.studentId = studentId;
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

    public ArrayList<StudentCourse> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<StudentCourse> courses) {
        this.courses = courses;
    }
    public void setAll(String firstName, String lastName, Date dateOfBirth, String cityOfBirth, int studentId, Person person, String address, String phone, ArrayList<StudentCourse> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityOfBirth = cityOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.studentId = studentId;
        this.address = address;
        this.phone = phone;
        this.courses = courses;
    }
    public List<Object> getAll(){
        String coursesString = "[";
        for (int i = 0; i < courses.size(); i++) {
            coursesString+="{\t" +
                    "courseId=" + courses.get(i).courseId+ "\t" +
                    "courseName='" + courses.get(i).courseName + "\t" +
                    "courseTrack=" + courses.get(i).courseTrack + "\t" +
                    "courseCredit=" + courses.get(i).courseCredit + "\t" +
                    "courseScore=" + courses.get(i).courseScore + "\t" +
                    "courseGPA=" + courses.get(i).courseGPA + "\t" +
                    "courseSession='" + courses.get(i).courseSession + "\t" +
                    "courseStatus=" + courses.get(i).courseStatus+
                    "\t}";
        }
        coursesString+="]";
        return Arrays.asList(studentId,firstName, lastName, dateOfBirth, cityOfBirth, address, phone, coursesString);
    }

    public String print() {
        String coursesString = "[\n";
        for (int i = 0; i < courses.size(); i++) {
            coursesString+="\t{\n" +
                    "\tcourseId=" + courses.get(i).courseId+ ",\n" +
                    "\tcourseName='" + courses.get(i).courseName + ",\n" +
                    "\tcourseTrack=" + courses.get(i).courseTrack + ",\n" +
                    "\tcourseCredit=" + courses.get(i).courseCredit + ",\n" +
                    "\tcourseScore=" + courses.get(i).courseScore + ",\n" +
                    "\tcourseGPA=" + courses.get(i).courseGPA + ",\n" +
                    "\tcourseSession='" + courses.get(i).courseSession + ",\n" +
                    "\tcourseStatus=" + courses.get(i).courseStatus+ "\n" +
                    "\t}\n";
        }
        coursesString+="]";
        return "Student {\n" +
                "firstName='" + firstName + ",\n" +
                "lastName='" + lastName + ",\n" +
                "dateOfBirth=" + dateOfBirth + ",\n" +
                "cityOfBirth='" + cityOfBirth + ",\n" +
                "studentId=" + studentId + ",\n" +
                "address='" + address + ",\n" +
                "phone='" + phone + ",\n" +
                "courses = " + coursesString + "\n" +
                '}';
    }
}
