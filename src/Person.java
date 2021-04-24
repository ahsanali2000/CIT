import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class Person {
    String firstName;
    String lastName;
    Date dateOfBirth;
    String cityOfBirth;

    public Person(String firstName, String lastName, Date dateOfBirth, String cityOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.cityOfBirth = cityOfBirth;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public void setAll(String firstName, String lastName, Date dateOfBirth, String cityOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.cityOfBirth = cityOfBirth;
    }

    public List<Object> getAll(){
        return Arrays.asList(firstName, lastName, dateOfBirth, cityOfBirth);
    }
    public String print() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");
        lines.add("c");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JList list = new JList(lines.toArray());
                list.setSelectionMode(
                        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                int[] select = {19, 20, 22};
                list.setSelectedIndices(select);
                JOptionPane.showMessageDialog(null, new JScrollPane(list));
                System.out.println(list.getSelectionModel().getAnchorSelectionIndex());

            }
        });
    }
}
