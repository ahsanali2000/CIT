import java.util.Arrays;
import java.util.List;

public class Course {
    int courseId;
    int courseScore;
    int courseGPA;
    String courseSession;

    public Course(int courseId, int courseScore, int courseGPA, String courseSession) {
        this.courseId = courseId;
        this.courseScore = courseScore;
        this.courseGPA = courseGPA;
        this.courseSession = courseSession;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(int courseScore) {
        this.courseScore = courseScore;
    }

    public int getCourseGPA() {
        return courseGPA;
    }

    public void setCourseGPA(int courseGPA) {
        this.courseGPA = courseGPA;
    }

    public String getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(String courseSession) {
        this.courseSession = courseSession;
    }

    public void setAll(int courseId, int courseScore, int courseGPA, String courseSession) {
        this.courseId = courseId;
        this.courseScore = courseScore;
        this.courseGPA = courseGPA;
        this.courseSession = courseSession;
    }
    public List<Object> getAll(){
        return Arrays.asList(courseId, courseScore, courseGPA, courseSession);
    }

    public String print() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseScore=" + courseScore +
                ", courseGPA=" + courseGPA +
                ", courseSession='" + courseSession + '\'' +
                '}';
    }
}
enum courseStatus {
    done,
    inProgress,
    failed,
    dropped
}