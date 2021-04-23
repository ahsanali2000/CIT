import java.util.Arrays;
import java.util.List;

public class Course {
    int courseId;
    String courseName;
    Track courseTrack;
    int courseCredit;

    public Course(int courseId, String courseName, Track courseTrack, int courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTrack = courseTrack;
        this.courseCredit = courseCredit;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Track getCourseTrack() {
        return courseTrack;
    }

    public void setCourseTrack(Track courseTrack) {
        this.courseTrack = courseTrack;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public void setAll(int courseId, String courseName, Track courseTrack, int courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTrack = courseTrack;
        this.courseCredit = courseCredit;
    }
    public List<Object> getAll(){
        return Arrays.asList(courseId, courseName, courseTrack, courseCredit);
    }



    public String print() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseTrack=" + courseTrack.print() +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
