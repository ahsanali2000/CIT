import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

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
        String tracksString = "[";
        for (int i = 0; i < teachingTracks.size(); i++) {
            tracksString+="{\t" +
                    "trackId="+teachingTracks.get(i).trackId+"\t" +
                    "trackName="+teachingTracks.get(i).trackName+"" +
                    "\t}";
        }
        tracksString+="]";
        String coursesString = "[";
        for (int i = 0; i < teachingCourses.size(); i++) {
            coursesString+="{\t" +
                    "courseId=" + teachingCourses.get(i).courseId+ "\t" +
                    "courseName=" + teachingCourses.get(i).courseName + "\t" +
                    "courseTrack={TrackId= " + teachingCourses.get(i).courseTrack.trackId + " TrackName= "+teachingCourses.get(i).courseTrack.trackName +"}\t"+
                    "courseCredit=" + teachingCourses.get(i).courseCredit + ""+
                    "\t}";
        }
        coursesString+="]";
        return Arrays.asList(firstName, lastName, dateOfBirth, cityOfBirth, coursesString.replace(",","\t"), tracksString);
    }

    public String print() {
        String tracksString = "[\n";
        for (int i = 0; i < teachingTracks.size(); i++) {
             tracksString+="\t{\n" +
                     "\ttrackId="+teachingTracks.get(i).trackId+",\n" +
                     "\ttrackName="+teachingTracks.get(i).trackName+",\n" +
                     "\t}\n";
        }
        tracksString+="]";
        String coursesString = "[\n";
        for (int i = 0; i < teachingCourses.size(); i++) {
            coursesString+="\t{\n" +
                    "\tcourseId=" + teachingCourses.get(i).courseId+ ",\n" +
                    "\tcourseName='" + teachingCourses.get(i).courseName + ",\n" +
                    "\tcourseTrack=" + teachingCourses.get(i).courseTrack.print() + ",\n" +
                    "\tcourseCredit=" + teachingCourses.get(i).courseCredit + ",\n"+
                    "\t}\n";
        }
        coursesString+="]";
        return "Faculty{\n" +
                "firstName='" + firstName + ",\n" +
                "lastName='" + lastName + ",\n" +
                "dateOfBirth='" + dateOfBirth + ",\n" +
                "cityOfBirth='" + cityOfBirth + ",\n" +
                "teachingCourses=" + coursesString + ",\n" +
                "teachingTracks=" + tracksString + ",\n" +
                '}';
    }
}
