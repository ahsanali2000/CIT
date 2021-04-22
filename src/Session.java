import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class Session {
    String name;
    Year sessionYear;

    public Session(String name, Year sessionYear) {
        this.name = name;
        this.sessionYear = sessionYear;
    }

    public Session() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getSessionYear() {
        return sessionYear;
    }

    public void setSessionYear(Year sessionYear) {
        this.sessionYear = sessionYear;
    }

    public void  setAll(String name, Year sessionYear) {
        this.name = name;
        this.sessionYear = sessionYear;
    }
    public List<Object> getAll(){
        return Arrays.asList(name, sessionYear);
    }

    public String print() {
        return "Session{" +
                "name='" + name + '\'' +
                ", sessionYear=" + sessionYear +
                '}';
    }
}
