import java.util.Arrays;
import java.util.List;

public class Track {
    int trackId;
    String trackName;

    public Track(int trackId, String trackName) {
        this.trackId = trackId;
        this.trackName = trackName;
    }

    public Track() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public void setAll(int trackId, String trackName) {
        this.trackId = trackId;
        this.trackName = trackName;
    }
    public List<Object> getAll(){
        return Arrays.asList(trackId, trackName);
    }
    public String print() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                '}';
    }
}
