import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CIT {
    static ArrayList<Student> students = new ArrayList<Student>();
    static ArrayList<Course> courses = new ArrayList<Course>();
    static ArrayList<Faculty> faculties = new ArrayList<Faculty>();
    
    public static void main(String[] args) {
        try {
            ArrayList<String[]> coursesList = new ArrayList<String []>();
            File myObj = new File("src/input/courses.in");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] all = data.split(",");
                coursesList.add(all);
                Course tempObj = new Course(
                        Integer.parseInt(all[0]),
                        all[1],
                        new Track(Integer.parseInt(all[2].split("-")[0]), all[2].split("-")[1]),
                        Integer.parseInt(all[3])
                );
                courses.add(tempObj);

            }
            myReader.close();


            myObj = new File("src/input/students.in");
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] all = data.split(",");
                ArrayList<StudentCourse> stuCourses = new ArrayList<StudentCourse>();
                String[] courseIds= all[7].split("_");
                for (int i = 0; i < courseIds.length; i++) {
                    for (int j = 0; j < coursesList.size(); j++) {
                        if(Integer.parseInt(courseIds[i])==Integer.parseInt(coursesList.get(j)[0])){
                            StudentCourse tempStuCourseObj = new StudentCourse(
                                    Integer.parseInt(coursesList.get(j)[0]),
                                    coursesList.get(j)[1],
                                    new Track(Integer.parseInt(coursesList.get(j)[2].split("-")[0]),coursesList.get(j)[2].split("-")[1]),
                                    Integer.parseInt(coursesList.get(j)[3]),
                                    Integer.parseInt(coursesList.get(j)[4]),
                                    Float.parseFloat(coursesList.get(j)[5]),
                                    coursesList.get(j)[6],
                                    courseStatus.valueOf(coursesList.get(j)[7])
                                    );
                            stuCourses.add(tempStuCourseObj);
                        }

                    }

                }
                Student tempObj = new Student(
                        Integer.parseInt(all[0]),
                        all[1],
                        all[2],
                        new Date(String.valueOf(all[3])),
                        all[4],
                        all[5],
                        all[6],
                        stuCourses
                );
                students.add(tempObj) ;
            }
            myReader.close();


            myObj = new File("src/input/faculty.in");
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] all = data.split(",");
                ArrayList<Course> facCourses = new ArrayList<Course>();
                String[] courseIds= all[4].split("_");
                for (int i = 0; i < courseIds.length; i++) {
                    for (int j = 0; j < courses.size(); j++) {
                        if(Integer.parseInt(courseIds[i])==courses.get(j).courseId){
                            facCourses.add(courses.get(j));
                        }
                    }
                }
                ArrayList<Track> facTracks = new ArrayList<Track>();
                String[] currentTrackList= all[5].split("_");
                for (int i = 0; i < currentTrackList.length; i++) {
                    facTracks.add(new Track(Integer.parseInt(currentTrackList[i].split("-")[0]),currentTrackList[i].split("-")[1]));
                }
                Faculty tempObj = new Faculty(
                        all[0],
                        all[1],
                        new Date(String.valueOf(all[2])),
                        all[3],
                        facCourses,
                        facTracks
                );
                faculties.add(tempObj) ;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }










        JFrame mainFrame= new JFrame("CIT");

        JLabel text = new JLabel("CIT");
        text.setFont(new Font("Algerian", Font.BOLD, 80));
        text.setForeground(Color.WHITE);
        text.setBounds(50,30,150,60);
        mainFrame.add(text);

        JButton saveToFile = new JButton("Save Lists to File");
        saveToFile.setBounds(300,100,325,50);
        saveToFile.setFont(new Font("Roboto", Font.BOLD, 20));
        saveToFile.setBackground(Color.WHITE);
        mainFrame.add(saveToFile);
        saveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame saveToFileFrame= new JFrame("CIT");

                JLabel text = new JLabel("Save to File");
                text.setFont(new Font("Arial", Font.BOLD, 50));
                text.setForeground(Color.WHITE);
                text.setBounds(140,30,350,50);
                saveToFileFrame.add(text);

                JButton saveStudentsButton = new JButton("Save Students");
                saveStudentsButton.setBounds(150,150,250,50);
                saveStudentsButton.setFont(new Font("Roboto", Font.BOLD, 20));
                saveStudentsButton.setBackground(Color.WHITE);
                saveToFileFrame.add(saveStudentsButton);
                saveStudentsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String addToCSV = "Student Id,First Name,Last Name,DOB,City of Birth,Address,Phone,Courses\n";
                        for (int i = 0; i < students.size(); i++) {
                            List<Object> currentStudents =  students.get(i).getAll();
                            for (int j = 0; j < currentStudents.size(); j++) {
                                if (j!=7) {
                                    addToCSV += currentStudents.get(j) + ",";
                                }
                                else{
                                    addToCSV += currentStudents.get(j);
                                }

                            }
                            addToCSV+="\n";
                        }
                        File myObj = new File("all students.csv");
                        try {
                            myObj.createNewFile();
                            FileWriter myWriter = new FileWriter("all students.csv");
                            myWriter.write(addToCSV);
                            myWriter.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        saveToFileFrame.dispose();
                    }
                });


                JButton saveFacultyButton = new JButton("Save Faculty");
                saveFacultyButton.setBounds(150,225,250,50);
                saveFacultyButton.setFont(new Font("Roboto", Font.BOLD, 20));
                saveFacultyButton.setBackground(Color.WHITE);
                saveToFileFrame.add(saveFacultyButton);
                saveFacultyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String addToCSV = "First Name,Last Name,DOB,City of Birth,Teaching Courses,Teaching Tracks\n";
                        for (int i = 0; i < faculties.size(); i++) {
                            List<Object> currentStudents =  faculties.get(i).getAll();
                            for (int j = 0; j < currentStudents.size(); j++) {
                                if (j!=5) {
                                    addToCSV += currentStudents.get(j) + ",";
                                }
                                else{
                                    addToCSV += currentStudents.get(j);
                                }

                            }
                            addToCSV+="\n";
                        }
                        File myObj = new File("all faculty.csv");
                        try {
                            myObj.createNewFile();
                            FileWriter myWriter = new FileWriter("all faculty.csv");
                            myWriter.write(addToCSV);
                            myWriter.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        saveToFileFrame.dispose();

                    }
                });


                JButton saveCoursesButton = new JButton("Save Courses");
                saveCoursesButton.setBounds(150,300,250,50);
                saveCoursesButton.setFont(new Font("Roboto", Font.BOLD, 20));
                saveCoursesButton.setBackground(Color.WHITE);
                saveToFileFrame.add(saveCoursesButton);
                saveCoursesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String addToCSV = "Course Id,Course Name,Course Track,Course Credit\n";
                        for (int i = 0; i < faculties.size(); i++) {
                            List<Object> currentStudents =  faculties.get(i).getAll();
                            for (int j = 0; j < currentStudents.size(); j++) {
                                if (j!=5) {
                                    addToCSV += currentStudents.get(j) + ",";
                                }
                                else{
                                    addToCSV += currentStudents.get(j);
                                }

                            }
                            addToCSV+="\n";
                        }
                        File myObj = new File("all faculty.csv");
                        try {
                            myObj.createNewFile();
                            FileWriter myWriter = new FileWriter("all faculty.csv");
                            myWriter.write(addToCSV);
                            myWriter.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        saveToFileFrame.dispose();
                    }

                });
                saveToFileFrame.setResizable(false);
                saveToFileFrame.setLayout(new BorderLayout());
                JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
                background_min.setBounds(0,0,576,554);
                saveToFileFrame.add(background_min);
                background_min.setLayout(new FlowLayout());
                saveToFileFrame.setSize(576,554);
                saveToFileFrame.setVisible(true);
            }
        });

        JButton saveDetails = new JButton("Save Details to File");
        saveDetails.setBounds(675,100,325,50);
        saveDetails.setFont(new Font("Roboto", Font.BOLD, 20));
        saveDetails.setBackground(Color.WHITE);
        mainFrame.add(saveDetails);



        JButton addStudent = new JButton("Add Student");
        addStudent.setBounds(300,200,200,50);
        addStudent.setFont(new Font("Roboto", Font.BOLD, 20));
        addStudent.setBackground(Color.WHITE);
        mainFrame.add(addStudent);

        JButton addCourse = new JButton("Add Course");
        addCourse.setBounds(550,200,200,50);
        addCourse.setFont(new Font("Roboto", Font.BOLD, 20));
        addCourse.setBackground(Color.WHITE);
        mainFrame.add(addCourse);

        JButton addFaculty = new JButton("Add Faculty");
        addFaculty.setBounds(800,200,200,50);
        addFaculty.setFont(new Font("Roboto", Font.BOLD, 20));
        addFaculty.setBackground(Color.WHITE);
        mainFrame.add(addFaculty);


        JButton edit = new JButton("Edit");
        edit.setBounds(300,300,150,50);
        edit.setFont(new Font("Roboto", Font.BOLD, 20));
        edit.setBackground(Color.WHITE);
        mainFrame.add(edit);

        JButton search = new JButton("Search");
        search.setBounds(475,300,150,50);
        search.setFont(new Font("Roboto", Font.BOLD, 20));
        search.setBackground(Color.WHITE);
        mainFrame.add(search);

        JButton delete = new JButton("Delete");
        delete.setBounds(650,300,150,50);
        delete.setFont(new Font("Roboto", Font.BOLD, 20));
        delete.setBackground(Color.WHITE);
        mainFrame.add(delete);

        JButton display = new JButton("Display");
        display.setBounds(825,300,175,50);
        display.setFont(new Font("Roboto", Font.BOLD, 20));
        display.setBackground(Color.WHITE);
        mainFrame.add(display);

        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg.jpg"));
        background.setBounds(0,0,1300,650);
        mainFrame.add(background);
        background.setLayout(new FlowLayout());
        mainFrame.setSize(1300,650);
        mainFrame.setVisible(true);
    }
}
