import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Faculty extends Person{
    ArrayList<Course> teachingCourses;
    ArrayList<Track> teachingTracks;

    public Faculty(String firstName, String lastName, Date dateOfBirth, String cityOfBirth, ArrayList<Course> teachingCourses, ArrayList<Track> teachingTracks) {
        super(firstName, lastName, dateOfBirth, cityOfBirth);
        this.teachingCourses = teachingCourses;
        this.teachingTracks = teachingTracks;
    }

    public Faculty() {
    }

    public ArrayList<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(ArrayList<Course> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    public ArrayList<Track> getTeachingTracks() {
        return teachingTracks;
    }

    public void setTeachingTracks(ArrayList<Track> teachingTracks) {
        this.teachingTracks = teachingTracks;
    }
    public  void setAll(ArrayList<Course> teachingCourses, ArrayList<Track> teachingTracks,String firstName, String lastName, String cityOfBirth, Date dateOfBirth ) {
        this.teachingCourses = teachingCourses;
        this.teachingTracks = teachingTracks;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityOfBirth = cityOfBirth;
        this.dateOfBirth = dateOfBirth;
    }
    public List<Object> getAll(){
        return Arrays.asList(firstName, lastName, dateOfBirth, cityOfBirth, teachingCourses, teachingTracks);
    }

    public String print() {
        return "Faculty{" +
                "teachingCourses=" + teachingCourses +
                ", teachingTracks=" + teachingTracks +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                '}';
    }
}
