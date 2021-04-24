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
    public void setAll(String firstName, String lastName, Date dateOfBirth, String cityOfBirth, int studentId, String address, String phone, ArrayList<StudentCourse> courses) {
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
        StringBuilder coursesString = new StringBuilder("[");
        for (StudentCourse cours : courses) {
            coursesString.append("{\t" + "courseId=").append(cours.courseId).append("\t").append("courseName='").append(cours.courseName).append("\t").append("courseTrack=").append(cours.courseTrack).append("\t").append("courseCredit=").append(cours.courseCredit).append("\t").append("courseScore=").append(cours.courseScore).append("\t").append("courseGPA=").append(cours.courseGPA).append("\t").append("courseSession='").append(cours.courseSession).append("\t").append("courseStatus=").append(cours.courseStatus).append("\t}");
        }
        coursesString.append("]");
        return Arrays.asList(studentId,firstName, lastName, dateOfBirth, cityOfBirth, address, phone, coursesString.toString());
    }

    public String print() {
        StringBuilder coursesString = new StringBuilder("[\n");
        for (StudentCourse cours : courses) {
            coursesString.append("\t{\n" + "\tcourseId=").append(cours.courseId).append(",\n").append("\tcourseName='").append(cours.courseName).append(",\n").append("\tcourseTrack=").append(cours.courseTrack).append(",\n").append("\tcourseCredit=").append(cours.courseCredit).append(",\n").append("\tcourseScore=").append(cours.courseScore).append(",\n").append("\tcourseGPA=").append(cours.courseGPA).append(",\n").append("\tcourseSession='").append(cours.courseSession).append(",\n").append("\tcourseStatus=").append(cours.courseStatus).append("\n").append("\t}\n");
        }
        coursesString.append("]");
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
