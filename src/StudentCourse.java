import java.util.Arrays;
import java.util.List;

public class StudentCourse extends Course{
    int courseScore;
    float courseGPA;
    String courseSession;
    courseStatus courseStatus;

    public StudentCourse(int courseId, String courseName, Track courseTrack, int courseCredit, int courseScore, float courseGPA, String courseSession, courseStatus courseStatus) {
        super(courseId, courseName, courseTrack, courseCredit);
        this.courseScore = courseScore;
        this.courseGPA = courseGPA;
        this.courseSession = courseSession;
        this.courseStatus = courseStatus;
    }

    public StudentCourse() {
    }

    public courseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(courseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(int courseScore) {
        this.courseScore = courseScore;
    }

    public float getCourseGPA() {
        return courseGPA;
    }

    public void setCourseGPA(float courseGPA) {
        this.courseGPA = courseGPA;
    }

    public String getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(String courseSession) {
        this.courseSession = courseSession;
    }

    public void setAll(int courseScore, int courseGPA, String courseSession, courseStatus courseStatus) {
        this.courseScore = courseScore;
        this.courseGPA = courseGPA;
        this.courseSession = courseSession;
        this.courseStatus = courseStatus;
    }
    public List<Object> getAll(){
        return Arrays.asList(courseId,courseName,courseTrack,courseCredit,courseScore, courseGPA, courseSession);
    }

    public String print() {
        return "StudentCourse{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseTrack=" + courseTrack +
                ", courseCredit=" + courseCredit +
                ", courseScore=" + courseScore +
                ", courseGPA=" + courseGPA +
                ", courseSession='" + courseSession + '\'' +
                ", courseStatus=" + courseStatus +
                '}';
    }
}
enum courseStatus {
    done,
    inProgress,
    failed,
    dropped
}