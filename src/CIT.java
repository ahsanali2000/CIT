import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

public class CIT {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Faculty> faculties = new ArrayList<>();
    static ArrayList<Track> tracks = new ArrayList<>();
    public static void saveAllCourcesToFile(String filename){
        StringBuilder addToCSV = new StringBuilder("Course Id,Course Name,Course Track,Course Credit\n");
        for (Course cours : courses) {
            List<Object> currentStudents = cours.getAll();
            for (int j = 0; j < currentStudents.size(); j++) {
                if (j != 3) {
                    addToCSV.append(currentStudents.get(j)).append(",");
                } else {
                    addToCSV.append(currentStudents.get(j));
                }

            }
            addToCSV.append("\n");
        }
        File myObj = new File(filename);
        try {
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(addToCSV.toString());
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void saveAllFacultyToFile(String filename){
        StringBuilder addToCSV = new StringBuilder("First Name,Last Name,DOB,City of Birth,Teaching Courses,Teaching Tracks\n");
        for (Faculty faculty : faculties) {
            List<Object> currentStudents = faculty.getAll();
            for (int j = 0; j < currentStudents.size(); j++) {
                if (j != 5) {
                    addToCSV.append(currentStudents.get(j)).append(",");
                } else {
                    addToCSV.append(currentStudents.get(j));
                }

            }
            addToCSV.append("\n");
        }
        File myObj = new File(filename);
        try {
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(addToCSV.toString());
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void saveAllStudentsToFile(String filename){
        StringBuilder addToCSV = new StringBuilder("Student Id,First Name,Last Name,DOB,City of Birth,Address,Phone,Courses\n");
        for (Student student : students) {
            List<Object> currentStudents = student.getAll();
            for (int j = 0; j < currentStudents.size(); j++) {
                if (j != 7) {
                    addToCSV.append(currentStudents.get(j)).append(",");
                } else {
                    addToCSV.append(currentStudents.get(j));
                }

            }
            addToCSV.append("\n");
        }
        File myObj = new File(filename);
        try {
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(addToCSV.toString());
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        tracks.add(new Track(1,"Dummy Track 1"));
        tracks.add(new Track(2,"Dummy Track 2"));
        tracks.add(new Track(3,"Dummy Track 3"));
        tracks.add(new Track(4,"Dummy Track 4"));

        try {
            ArrayList<String[]> coursesList = new ArrayList<>();
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
                ArrayList<StudentCourse> stuCourses = new ArrayList<>();
                String[] courseIds= all[7].split("_");
                for (String courseId : courseIds) {
                    for (String[] strings : coursesList) {
                        if (Integer.parseInt(courseId) == Integer.parseInt(strings[0])) {
                            StudentCourse tempStuCourseObj = new StudentCourse(
                                    Integer.parseInt(strings[0]),
                                    strings[1],
                                    new Track(Integer.parseInt(strings[2].split("-")[0]), strings[2].split("-")[1]),
                                    Integer.parseInt(strings[3]),
                                    Integer.parseInt(strings[4]),
                                    Float.parseFloat(strings[5]),
                                    strings[6],
                                    courseStatus.valueOf(strings[7])
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
                ArrayList<Course> facCourses = new ArrayList<>();
                String[] courseIds= all[4].split("_");
                for (String courseId : courseIds) {
                    for (Course cours : courses) {
                        if (Integer.parseInt(courseId) == cours.courseId) {
                            facCourses.add(cours);
                        }
                    }
                }
                ArrayList<Track> facTracks = new ArrayList<>();
                String[] currentTrackList= all[5].split("_");
                for (String s : currentTrackList) {
                    facTracks.add(new Track(Integer.parseInt(s.split("-")[0]), s.split("-")[1]));
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
        saveToFile.addActionListener(e -> {
            JFrame saveToFileFrame= new JFrame("CIT");

            JLabel text1 = new JLabel("Save to File");
            text1.setFont(new Font("Arial", Font.BOLD, 50));
            text1.setForeground(Color.WHITE);
            text1.setBounds(140,30,350,50);
            saveToFileFrame.add(text1);

            JButton saveStudentsButton = new JButton("Save Students");
            saveStudentsButton.setBounds(150,150,250,50);
            saveStudentsButton.setFont(new Font("Roboto", Font.BOLD, 20));
            saveStudentsButton.setBackground(Color.WHITE);
            saveToFileFrame.add(saveStudentsButton);
            saveStudentsButton.addActionListener(e12 -> {
                saveAllStudentsToFile("all students.csv");
                saveToFileFrame.dispose();
            });


            JButton saveFacultyButton = new JButton("Save Faculty");
            saveFacultyButton.setBounds(150,225,250,50);
            saveFacultyButton.setFont(new Font("Roboto", Font.BOLD, 20));
            saveFacultyButton.setBackground(Color.WHITE);
            saveToFileFrame.add(saveFacultyButton);
            saveFacultyButton.addActionListener(e1 -> {
                saveAllFacultyToFile("all faculty.csv");
                saveToFileFrame.dispose();

            });


            JButton saveCoursesButton = new JButton("Save Courses");
            saveCoursesButton.setBounds(150,300,250,50);
            saveCoursesButton.setFont(new Font("Roboto", Font.BOLD, 20));
            saveCoursesButton.setBackground(Color.WHITE);
            saveToFileFrame.add(saveCoursesButton);
            saveCoursesButton.addActionListener(e13 -> {
                saveAllCourcesToFile("all courses.csv");
                saveToFileFrame.dispose();
            });
            saveToFileFrame.setResizable(false);
            saveToFileFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            saveToFileFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            saveToFileFrame.setSize(576,554);
            saveToFileFrame.setVisible(true);
        });

        JButton saveDetails = new JButton("Save Details to File");
        saveDetails.setBounds(675,100,325,50);
        saveDetails.setFont(new Font("Roboto", Font.BOLD, 20));
        saveDetails.setBackground(Color.WHITE);
        mainFrame.add(saveDetails);
        saveDetails.addActionListener(e -> {
            JFrame saveDetailsFrame= new JFrame("CIT");

            JLabel Title = new JLabel("Save to File");
            Title.setFont(new Font("Algerian", Font.BOLD, 40));
            Title.setForeground(Color.WHITE);
            Title.setBounds(120,10,400,50);
            saveDetailsFrame.add(Title);

            String[] allStudents = new String[students.size()];
            for (int i = 0; i < students.size(); i++) {
                allStudents[i]=students.get(i).firstName+" "+students.get(i).lastName;
            }
            JComboBox studentsDrop = new JComboBox(allStudents);
            studentsDrop.setBounds(30,150,100,40);
            saveDetailsFrame.add(studentsDrop);

            JButton saveselectedStudent = new JButton("Save Selected Student");
            saveselectedStudent.setBounds(150,150,200,40);
            saveselectedStudent.setFont(new Font("Roboto", Font.BOLD, 13));
            saveselectedStudent.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveselectedStudent);
            saveselectedStudent.addActionListener(event -> {
                StringBuilder addToCSV = new StringBuilder("Student Id,First Name,Last Name,DOB,City of Birth,Address,Phone,Courses\n");
                ArrayList<Student> requiredStudents = new ArrayList<>();
                for(Student student:students){
                    if ((student.firstName+" "+student.lastName).equals(studentsDrop.getSelectedItem())){
                        requiredStudents.add(student);
                    }
                }
                for (Student student : requiredStudents) {
                    List<Object> currentStudents = student.getAll();
                    for (int j = 0; j < currentStudents.size(); j++) {
                        if (j != 7) {
                            addToCSV.append(currentStudents.get(j)).append(",");
                        } else {
                            addToCSV.append(currentStudents.get(j));
                        }

                    }
                    addToCSV.append("\n");
                }
                File myObj = new File("Selected Students Details.csv");
                try {
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("Selected Students Details.csv");
                    myWriter.write(addToCSV.toString());
                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                saveDetailsFrame.dispose();
            });
            JButton saveAllStudentsButton = new JButton("Save All Students");
            saveAllStudentsButton.setBounds(360,150,180,40);
            saveAllStudentsButton.setFont(new Font("Roboto", Font.BOLD, 13));
            saveAllStudentsButton.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveAllStudentsButton);
            saveAllStudentsButton.addActionListener(event -> {
                saveAllStudentsToFile("Selected Students Details.csv");
                saveDetailsFrame.dispose();
            });


            String[] allFaculty = new String[faculties.size()];
            for (int i = 0; i < faculties.size(); i++) {
                allFaculty[i]=faculties.get(i).firstName+" "+faculties.get(i).lastName;
            }
            JComboBox facultyDrop = new JComboBox(allFaculty);
            facultyDrop.setBounds(30,210,100,40);
            saveDetailsFrame.add(facultyDrop);

            JButton saveselectedFaculty = new JButton("Save Selected Faculty");
            saveselectedFaculty.setBounds(150,210,200,40);
            saveselectedFaculty.setFont(new Font("Roboto", Font.BOLD, 13));
            saveselectedFaculty.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveselectedFaculty);
            saveselectedFaculty.addActionListener(event -> {
                StringBuilder addToCSV = new StringBuilder("First Name,Last Name,DOB,City of Birth,Teaching Courses,Teaching Tracks\n");
                ArrayList<Faculty> requiredFaculty = new ArrayList<>();
                for (Faculty faculty:faculties){
                    if ((faculty.firstName+" "+faculty.lastName).equals(facultyDrop.getSelectedItem())){
                        requiredFaculty.add(faculty);
                    }
                }
                for (Faculty faculty : requiredFaculty) {
                    List<Object> currentStudents = faculty.getAll();
                    for (int j = 0; j < currentStudents.size(); j++) {
                        if (j != 5) {
                            addToCSV.append(currentStudents.get(j)).append(",");
                        } else {
                            addToCSV.append(currentStudents.get(j));
                        }

                    }
                    addToCSV.append("\n");
                }
                File myObj = new File("Selected Faculty Details.csv");
                try {
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("Selected Faculty Details.csv");
                    myWriter.write(addToCSV.toString());
                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                saveDetailsFrame.dispose();
            });

            JButton saveAllFacultyButton = new JButton("Save All Faculty");
            saveAllFacultyButton.setBounds(360,210,180,40);
            saveAllFacultyButton.setFont(new Font("Roboto", Font.BOLD, 13));
            saveAllFacultyButton.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveAllFacultyButton);
            saveAllFacultyButton.addActionListener(event -> {
                saveAllFacultyToFile("Selected Faculty Details.csv");
                saveDetailsFrame.dispose();
            });




            String[] allCources = new String[courses.size()];
            for (int i = 0; i < courses.size(); i++) {
                allCources[i]=courses.get(i).courseId+" "+courses.get(i).courseName;
            }
            JComboBox courcesDrop = new JComboBox(allCources);
            courcesDrop.setBounds(30,270,100,40);
            saveDetailsFrame.add(courcesDrop);

            JButton saveSelectedCourses = new JButton("Save Selected Student");
            saveSelectedCourses.setBounds(150,270,200,40);
            saveSelectedCourses.setFont(new Font("Roboto", Font.BOLD, 13));
            saveSelectedCourses.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveSelectedCourses);
            saveSelectedCourses.addActionListener(event -> {
                StringBuilder addToCSV = new StringBuilder("Course Id,Course Name,Course Track,Course Credit\n");
                ArrayList<Course> requiredCources = new ArrayList<>();
                for (Course course: courses){
                    if ((course.courseId+" "+course.courseName).equals(courcesDrop.getSelectedItem())){
                        requiredCources.add(course);
                    }
                }
                for (Course cours : requiredCources) {
                    List<Object> currentStudents = cours.getAll();
                    for (int j = 0; j < currentStudents.size(); j++) {
                        if (j != 3) {
                            addToCSV.append(currentStudents.get(j)).append(",");
                        } else {
                            addToCSV.append(currentStudents.get(j));
                        }

                    }
                    addToCSV.append("\n");
                }
                File myObj = new File("Selected Courses Details.csv");
                try {
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("Selected Courses Details.csv");
                    myWriter.write(addToCSV.toString());
                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                saveDetailsFrame.dispose();
            });


            JButton saveAllCoursesButton = new JButton("Save All Cources");
            saveAllCoursesButton.setBounds(360,270,180,40);
            saveAllCoursesButton.setFont(new Font("Roboto", Font.BOLD, 13));
            saveAllCoursesButton.setBackground(Color.WHITE);
            saveDetailsFrame.add(saveAllCoursesButton);
            saveAllCoursesButton.addActionListener(event -> {
                saveAllCourcesToFile("Selected Courses Details.csv");
                saveDetailsFrame.dispose();
            });

            saveDetailsFrame.setResizable(false);
            saveDetailsFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            saveDetailsFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            saveDetailsFrame.setSize(576,554);
            saveDetailsFrame.setVisible(true);

        });



        JButton addStudent = new JButton("Add Student");
        addStudent.setBounds(300,200,200,50);
        addStudent.setFont(new Font("Roboto", Font.BOLD, 20));
        addStudent.setBackground(Color.WHITE);
        mainFrame.add(addStudent);
        addStudent.addActionListener(event -> {
            JFrame addStudentFrame= new JFrame("CIT");


            JLabel text1 = new JLabel("Add Student");
            text1.setFont(new Font("Arial", Font.BOLD, 20));
            text1.setForeground(Color.WHITE);
            text1.setBounds(240,10,350,20);
            addStudentFrame.add(text1);


            JLabel StudentIdLebel = new JLabel("Student Id: ");
            StudentIdLebel.setForeground(Color.WHITE);
            StudentIdLebel.setBounds(100,50,100,40);
            addStudentFrame.add(StudentIdLebel);

            NumberFormat longFormat = NumberFormat.getIntegerInstance();
            NumberFormatter numberFormatter = new NumberFormatter(longFormat);
            numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
            numberFormatter.setAllowsInvalid(false); //this is the key!!
            numberFormatter.setMinimum(0l); //Optional
            JFormattedTextField field = new JFormattedTextField(numberFormatter);
            field.setBounds(200,50,200,30);
            addStudentFrame.add(field);

            JLabel userLabel = new JLabel("First Name: ");
            userLabel.setForeground(Color.WHITE);
            userLabel.setBounds(100,100,100,40);
            addStudentFrame.add(userLabel);
            JTextField firstName = new JTextField();
            firstName.setBounds(200,100,200,30);
            addStudentFrame.add(firstName);

            JLabel lastNameLabel = new JLabel("Last Name: ");
            lastNameLabel.setForeground(Color.WHITE);
            lastNameLabel.setBounds(100,150,100,40);
            addStudentFrame.add(lastNameLabel);
            JTextField lastName = new JTextField();
            lastName.setBounds(200,150,200,30);
            addStudentFrame.add(lastName);


            JLabel DOBLabel = new JLabel("Enter DOB: ");
            DOBLabel.setForeground(Color.WHITE);
            DOBLabel.setBounds(100,200,100,40);
            addStudentFrame.add(DOBLabel);
            MaskFormatter mask1 = null;//the # is for numeric values
            try {
                mask1 = new MaskFormatter("##-##-####");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mask1.setPlaceholderCharacter('#');
            JFormattedTextField txtDate = new JFormattedTextField(mask1);
            txtDate.setBounds(200,200,200,30);
            addStudentFrame.add(txtDate);
            JLabel dateName = new JLabel("Date(MM-DD-YYYY)");
            dateName.setForeground(Color.WHITE);
            dateName.setBounds(410,200,200,30);
            addStudentFrame.add(dateName);

            txtDate.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid");
                        e.consume();
                    }
                }
            });


            JLabel Citylabel = new JLabel("City of Birth: ");
            Citylabel.setForeground(Color.WHITE);
            Citylabel.setBounds(100,250,100,40);
            addStudentFrame.add(Citylabel);
            JTextField cityName = new JTextField();
            cityName.setBounds(200,250,200,30);
            addStudentFrame.add(cityName);

            JLabel addresslabel = new JLabel("Address: ");
            addresslabel.setForeground(Color.WHITE);
            addresslabel.setBounds(100,300,100,40);
            addStudentFrame.add(addresslabel);
            JTextField addressEntry = new JTextField();
            addressEntry.setBounds(200,300,200,30);
            addStudentFrame.add(addressEntry);

            JLabel phoneLabel = new JLabel("Phone: ");
            phoneLabel.setForeground(Color.WHITE);
            phoneLabel.setBounds(100,350,100,40);
            addStudentFrame.add(phoneLabel);
            JFormattedTextField phone = new JFormattedTextField(numberFormatter);
            phone.setBounds(200,350,200,30);
            addStudentFrame.add(phone);

            JLabel allCources = new JLabel("Select Courses: ");
            allCources.setForeground(Color.WHITE);
            allCources.setBounds(100,400,100,40);
            addStudentFrame.add(allCources);
            JButton selectCourseButton = new JButton("Select Courses");
            selectCourseButton.setBounds(200,400,200,30);
            selectCourseButton.setFont(new Font("Roboto", Font.BOLD, 20));
            selectCourseButton.setBackground(Color.BLACK);
            selectCourseButton.setBackground(Color.WHITE);
            addStudentFrame.add(selectCourseButton);

            int[][] selectedCoursesIndex = {new int[0]};
            String[] allCoursesList = new String[courses.size()];
            for (int i = 0; i < courses.size(); i++) {
                allCoursesList[i] = courses.get(i).courseId+" "+courses.get(i).courseName;
            }

            selectCourseButton.addActionListener(e -> {
                JList<String> items = new JList<String>(allCoursesList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));
                selectedCoursesIndex[0] = items.getSelectedIndices();
            });





            JButton addStudentButton = new JButton("Add Student");
            addStudentButton.setBounds(150,450,300,50);
            addStudentButton.setFont(new Font("Roboto", Font.BOLD, 25));
            addStudentButton.setBackground(Color.BLACK);
            addStudentButton.setBackground(Color.WHITE);
            addStudentFrame.add(addStudentButton);
            addStudentButton.addActionListener(e -> {
                int givenStudentId = Integer.parseInt(field.getText());
                String givenFirstName = firstName.getText();
                String givenLastName = lastName.getText();
                Date givenDOB = null;
                try {
                    givenDOB= new SimpleDateFormat("MM-DD-YYYY").parse(txtDate.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                String givenCity = cityName.getText();
                String givenAddress = addressEntry.getText();
                String givenPhone = phone.getText();
                ArrayList<StudentCourse> givenCourses = new ArrayList<>();
                for(int index: selectedCoursesIndex[0]){
                    Course temp = courses.get(index);
                    givenCourses.add(new StudentCourse(temp.courseId,temp.courseName, temp.courseTrack, temp.courseCredit, 0,0,"Spring 2021",courseStatus.inProgress));
                }
                students.add(new Student(givenStudentId,givenFirstName,givenLastName,givenDOB,givenCity,givenAddress,givenPhone,givenCourses));
                addStudentFrame.dispose();

            });

            addStudentFrame.setResizable(false);
            addStudentFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            addStudentFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            addStudentFrame.setSize(576,554);
            addStudentFrame.setVisible(true);
        });

        JButton addCourse = new JButton("Add Course");
        addCourse.setBounds(550,200,200,50);
        addCourse.setFont(new Font("Roboto", Font.BOLD, 20));
        addCourse.setBackground(Color.WHITE);
        mainFrame.add(addCourse);
        addCourse.addActionListener(event -> {
            JFrame addCourseFrame= new JFrame("CIT");

            JLabel text1 = new JLabel("Add Course");
            text1.setFont(new Font("Arial", Font.BOLD, 20));
            text1.setForeground(Color.WHITE);
            text1.setBounds(240,10,350,20);
            addCourseFrame.add(text1);

            JLabel StudentIdLebel = new JLabel("Course Id: ");
            StudentIdLebel.setForeground(Color.WHITE);
            StudentIdLebel.setBounds(100,100,100,40);
            addCourseFrame.add(StudentIdLebel);

            NumberFormat longFormat = NumberFormat.getIntegerInstance();
            NumberFormatter numberFormatter = new NumberFormatter(longFormat);
            numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
            numberFormatter.setAllowsInvalid(false); //this is the key!!
            numberFormatter.setMinimum(0l); //Optional
            JFormattedTextField field = new JFormattedTextField(numberFormatter);
            field.setBounds(200,100,200,30);
            addCourseFrame.add(field);



            JLabel userLabel = new JLabel("Course Name: ");
            userLabel.setForeground(Color.WHITE);
            userLabel.setBounds(100,150,100,40);
            addCourseFrame.add(userLabel);
            JTextField courseName = new JTextField();
            courseName.setBounds(200,150,200,30);
            addCourseFrame.add(courseName);

            JLabel courseCreditLabel = new JLabel("Course Credit: ");
            courseCreditLabel.setForeground(Color.WHITE);
            courseCreditLabel.setBounds(100,200,100,40);
            addCourseFrame.add(courseCreditLabel);
            JFormattedTextField courseCredit = new JFormattedTextField(numberFormatter);
            courseCredit.setBounds(200,200,200,30);
            addCourseFrame.add(courseCredit);



            JLabel allTracks = new JLabel("Select Tracks: ");
            allTracks.setForeground(Color.WHITE);
            allTracks.setBounds(100,250,100,40);
            addCourseFrame.add(allTracks);

            String[] allTracksList = new String[tracks.size()];
            for (int i = 0; i < tracks.size(); i++) {
                allTracksList[i] = tracks.get(i).trackId+" "+tracks.get(i).trackName;
            }
            JComboBox tracksDrop = new JComboBox(allTracksList);
            tracksDrop.setBounds(200,250,200,30);
            addCourseFrame.add(tracksDrop);


            JButton addCourseButton = new JButton("Add Course");
            addCourseButton.setBounds(150,400,300,50);
            addCourseButton.setFont(new Font("Roboto", Font.BOLD, 25));
            addCourseButton.setBackground(Color.BLACK);
            addCourseButton.setBackground(Color.WHITE);
            addCourseFrame.add(addCourseButton);
            addCourseButton.addActionListener(e -> {
                String givenName = courseName.getText();
                int givenId = Integer.parseInt(field.getText());
                int givenCourseCredit = Integer.parseInt(courseCredit.getText());
                Track givenTrack = null;
                for(Track track:tracks){
                    if ((track.trackId+" "+track.trackName).equals(tracksDrop.getSelectedItem())){
                        givenTrack = track;
                    }
                }

                courses.add(new Course(givenId,givenName,givenTrack,givenCourseCredit));
                System.out.println(courses.get(2).print());
                addCourseFrame.dispose();

            });

            addCourseFrame.setResizable(false);
            addCourseFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            addCourseFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            addCourseFrame.setSize(576,554);
            addCourseFrame.setVisible(true);
        });


        JButton addFaculty = new JButton("Add Faculty");
        addFaculty.setBounds(800,200,200,50);
        addFaculty.setFont(new Font("Roboto", Font.BOLD, 20));
        addFaculty.setBackground(Color.WHITE);
        mainFrame.add(addFaculty);
        addFaculty.addActionListener(event -> {
            JFrame addFacultyFrame= new JFrame("CIT");


            JLabel text1 = new JLabel("Add Faculty");
            text1.setFont(new Font("Arial", Font.BOLD, 20));
            text1.setForeground(Color.WHITE);
            text1.setBounds(240,10,350,20);
            addFacultyFrame.add(text1);




            JLabel userLabel = new JLabel("First Name: ");
            userLabel.setForeground(Color.WHITE);
            userLabel.setBounds(100,100,100,40);
            addFacultyFrame.add(userLabel);
            JTextField firstName = new JTextField();
            firstName.setBounds(200,100,200,30);
            addFacultyFrame.add(firstName);

            JLabel lastNameLabel = new JLabel("Last Name: ");
            lastNameLabel.setForeground(Color.WHITE);
            lastNameLabel.setBounds(100,150,100,40);
            addFacultyFrame.add(lastNameLabel);
            JTextField lastName = new JTextField();
            lastName.setBounds(200,150,200,30);
            addFacultyFrame.add(lastName);


            JLabel DOBLabel = new JLabel("Enter DOB: ");
            DOBLabel.setForeground(Color.WHITE);
            DOBLabel.setBounds(100,200,100,40);
            addFacultyFrame.add(DOBLabel);
            MaskFormatter mask1 = null;//the # is for numeric values
            try {
                mask1 = new MaskFormatter("##-##-####");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mask1.setPlaceholderCharacter('#');
            JFormattedTextField txtDate = new JFormattedTextField(mask1);
            txtDate.setBounds(200,200,200,30);
            addFacultyFrame.add(txtDate);
            JLabel dateName = new JLabel("Date(MM-DD-YYYY)");
            dateName.setForeground(Color.WHITE);
            dateName.setBounds(410,200,200,30);
            addFacultyFrame.add(dateName);

            txtDate.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid");
                        e.consume();
                    }
                }
            });


            JLabel Citylabel = new JLabel("City of Birth: ");
            Citylabel.setForeground(Color.WHITE);
            Citylabel.setBounds(100,250,100,40);
            addFacultyFrame.add(Citylabel);
            JTextField cityName = new JTextField();
            cityName.setBounds(200,250,200,30);
            addFacultyFrame.add(cityName);


            JLabel allCources = new JLabel("Select Courses: ");
            allCources.setForeground(Color.WHITE);
            allCources.setBounds(100,300,100,40);
            addFacultyFrame.add(allCources);
            JButton selectCourseButton = new JButton("Select Courses");
            selectCourseButton.setBounds(200,300,200,30);
            selectCourseButton.setFont(new Font("Roboto", Font.BOLD, 20));
            selectCourseButton.setBackground(Color.BLACK);
            selectCourseButton.setBackground(Color.WHITE);
            addFacultyFrame.add(selectCourseButton);

            int[][] selectedCoursesIndex = {new int[0]};
            String[] allCoursesList = new String[courses.size()];
            for (int i = 0; i < courses.size(); i++) {
                allCoursesList[i] = courses.get(i).courseId+" "+courses.get(i).courseName;
            }

            selectCourseButton.addActionListener(e -> {
                JList<String> items = new JList<String>(allCoursesList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));
                selectedCoursesIndex[0] = items.getSelectedIndices();
            });

            JLabel allTracks = new JLabel("Select Tracks: ");
            allTracks.setForeground(Color.WHITE);
            allTracks.setBounds(100,350,100,40);
            addFacultyFrame.add(allTracks);
            JButton selectTrackButton = new JButton("Select Tracks");
            selectTrackButton.setBounds(200,350,200,30);
            selectTrackButton.setFont(new Font("Roboto", Font.BOLD, 20));
            selectTrackButton.setBackground(Color.BLACK);
            selectTrackButton.setBackground(Color.WHITE);
            addFacultyFrame.add(selectTrackButton);

            int[][] selectedTracksIndex = {new int[0]};
            String[] allTracksList = new String[tracks.size()];
            for (int i = 0; i < tracks.size(); i++) {
                allTracksList[i] = tracks.get(i).trackId+" "+tracks.get(i).trackName;
            }

            selectTrackButton.addActionListener(e -> {
                JList<String> items = new JList<String>(allTracksList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));
                selectedTracksIndex[0] = items.getSelectedIndices();
            });


            JButton addFacultyButton = new JButton("Add Faculty");
            addFacultyButton.setBounds(150,400,300,50);
            addFacultyButton.setFont(new Font("Roboto", Font.BOLD, 25));
            addFacultyButton.setBackground(Color.BLACK);
            addFacultyButton.setBackground(Color.WHITE);
            addFacultyFrame.add(addFacultyButton);
            addFacultyButton.addActionListener(e -> {
                String givenFirstName = firstName.getText();
                String givenLastName = lastName.getText();
                Date givenDOB = null;
                try {
                    givenDOB= new SimpleDateFormat("MM-dd-yyyy").parse(txtDate.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                String givenCity = cityName.getText();
                ArrayList<Course> givenCourses = new ArrayList<>();
                for(int index: selectedCoursesIndex[0]){
                    givenCourses.add(courses.get(index));
                }
                ArrayList<Track> givenTracks = new ArrayList<>();
                for (int index:selectedTracksIndex[0]){
                    givenTracks.add(tracks.get(index));
                }


                faculties.add(new Faculty(givenFirstName,givenLastName,givenDOB,givenCity,givenCourses, givenTracks));
                addFacultyFrame.dispose();

            });

            addFacultyFrame.setResizable(false);
            addFacultyFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            addFacultyFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            addFacultyFrame.setSize(576,554);
            addFacultyFrame.setVisible(true);
        });


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
        search.addActionListener(e -> {
            JFrame searchFrame = new JFrame("CIT");

            JLabel text1 = new JLabel("Search");
            text1.setFont(new Font("Arial", Font.BOLD, 40));
            text1.setForeground(Color.WHITE);
            text1.setBounds(200,10,350,40);
            searchFrame.add(text1);

            JLabel userLabel = new JLabel("Student Id or Name: ");
            userLabel.setForeground(Color.WHITE);
            userLabel.setBounds(20,100,150,40);
            searchFrame.add(userLabel);
            JTextField searchStudent = new JTextField();
            searchStudent.setBounds(170,100,200,30);
            searchFrame.add(searchStudent);

            JButton searchStudentButton = new JButton("Search Student");
            searchStudentButton.setBounds(390,100,150,30);
            searchStudentButton.setFont(new Font("Roboto", Font.BOLD, 12));
            searchStudentButton.setBackground(Color.WHITE);
            searchFrame.add(searchStudentButton);
            searchStudentButton.addActionListener(event->{
                ArrayList<String> selectedStudents = new ArrayList<>();
                for(Student student:students){
                    if(String.valueOf(student.studentId).equals(searchStudent.getText()) || student.firstName.equals(searchStudent.getText()) || student.lastName.equals(searchStudent.getText())){
                        selectedStudents.add(student.print());
                    }
                }
                String[] selectedStudentsList = null;
                if (selectedStudents.size()>0){
                    selectedStudentsList = new String[selectedStudents.size()];
                    for (int i = 0; i < selectedStudents.size(); i++) {
                        selectedStudentsList[i] = selectedStudents.get(i);
                    }
                }else{
                    selectedStudentsList = new String[]{"No result Found!!!"};
                }
                JList<String> items = new JList<String>(selectedStudentsList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));
            });


            JLabel searchFacultyLabel = new JLabel("Faculty Name: ");
            searchFacultyLabel.setForeground(Color.WHITE);
            searchFacultyLabel.setBounds(20,200,150,40);
            searchFrame.add(searchFacultyLabel);
            JTextField facultyName = new JTextField();
            facultyName.setBounds(170,200,200,30);
            searchFrame.add(facultyName);

            JButton searchFacultyButton = new JButton("Search Faculty");
            searchFacultyButton.setBounds(390,200,150,30);
            searchFacultyButton.setFont(new Font("Roboto", Font.BOLD, 12));
            searchFacultyButton.setBackground(Color.WHITE);
            searchFrame.add(searchFacultyButton);
            searchFacultyButton.addActionListener(even->
            {
                ArrayList<String> selectedFaculty = new ArrayList<>();
                for(Faculty faculty:faculties){
                    if(faculty.firstName.equals(facultyName.getText()) || faculty.lastName.equals(facultyName.getText())){
                        selectedFaculty.add(faculty.print());
                    }
                }
                String[] selectedStudentsList = null;
                if (selectedFaculty.size()>0){
                    selectedStudentsList = new String[selectedFaculty.size()];
                    for (int i = 0; i < selectedFaculty.size(); i++) {
                        selectedStudentsList[i] = selectedFaculty.get(i);
                    }
                }else{
                    selectedStudentsList = new String[]{"No result Found!!!"};
                }
                JList<String> items = new JList<String>(selectedStudentsList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));

            });


            JLabel searchCourseLabel = new JLabel("Course Id or Name: ");
            searchCourseLabel.setForeground(Color.WHITE);
            searchCourseLabel.setBounds(20,300,150,40);
            searchFrame.add(searchCourseLabel);
            JTextField searchCourse = new JTextField();
            searchCourse.setBounds(170,300,200,30);
            searchFrame.add(searchCourse);


            JButton searchCourseButton = new JButton("Search Course");
            searchCourseButton.setBounds(390,300,150,30);
            searchCourseButton.setFont(new Font("Roboto", Font.BOLD, 12));
            searchCourseButton.setBackground(Color.WHITE);
            searchFrame.add(searchCourseButton);
            searchCourseButton.addActionListener(event->{
                ArrayList<String> selectedCourses = new ArrayList<>();
                for(Course course:courses){
                    if(String.valueOf(course.courseId).equals(searchCourse.getText()) || course.courseName.equals(searchCourse.getText())){
                        selectedCourses.add(course.print());
                    }
                }
                String[] selectedStudentsList = null;
                if (selectedCourses.size()>0){
                    selectedStudentsList = new String[selectedCourses.size()];
                    for (int i = 0; i < selectedCourses.size(); i++) {
                        selectedStudentsList[i] = selectedCourses.get(i);
                    }
                }else{
                    selectedStudentsList = new String[]{"No result Found!!!"};
                }
                JList<String> items = new JList<String>(selectedStudentsList);
                JOptionPane.showMessageDialog(null, new JScrollPane(items));
            });


            searchFrame.setResizable(false);
            searchFrame.setLayout(new BorderLayout());
            JLabel background_min=new JLabel(new ImageIcon("src/assets/bg-min.jpg"));
            background_min.setBounds(0,0,576,554);
            searchFrame.add(background_min);
            background_min.setLayout(new FlowLayout());
            searchFrame.setSize(576,554);
            searchFrame.setVisible(true);
        });

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
