import java.io.*;
import java.util.*;

class Admin {
    static final String USERNAME = "admin";
    static final String PASSWORD = "admin123";
}

class Student implements Serializable {

    String college = "Sainath High School and Jr College";
    int rollNo, studentClass;
    String name;

    int subjectCount;
    String[] subjects;
    int[] internalMarks;
    int[] externalMarks;
    int[] totalMarks;

    boolean hasInternal;

    Student(int subjectCount, boolean hasInternal) {
        this.subjectCount = subjectCount;
        this.hasInternal = hasInternal;

        subjects = new String[subjectCount];
        internalMarks = new int[subjectCount];
        externalMarks = new int[subjectCount];
        totalMarks = new int[subjectCount];
    }

    void calculateTotals() {
        for (int i = 0; i < subjectCount; i++) {
            totalMarks[i] = internalMarks[i] + externalMarks[i];
        }
    }

    int totalObtained() {
        int sum = 0;
        for (int t : totalMarks) sum += t;
        return sum;
    }

    void displayReport() {
        calculateTotals();

        System.out.println("\n========================================================");
        System.out.println("        " + college.toUpperCase());
        System.out.println("                   REPORT CARD");
        System.out.println("========================================================");
        System.out.printf(" Roll No : %-10d Class : %d\n", rollNo, studentClass);
        System.out.println(" Name    : " + name);
        System.out.println("--------------------------------------------------------");

        if (hasInternal) {
            System.out.printf("%-15s %-10s %-10s %-10s\n",
                    "Subject", "Internal", "External", "Total");
        } else {
            System.out.printf("%-15s %-10s\n", "Subject", "Marks");
        }

        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < subjectCount; i++) {
            if (hasInternal) {
                System.out.printf("%-15s %-10d %-10d %-10d\n",
                        subjects[i], internalMarks[i], externalMarks[i], totalMarks[i]);
            } else {
                System.out.printf("%-15s %-10d\n",
                        subjects[i], externalMarks[i]);
            }
        }

        System.out.println("--------------------------------------------------------");
        System.out.println(" TOTAL MARKS OBTAINED : " + totalObtained());
        System.out.println("========================================================");
    }
}

public class StudentReportCardAdvanced {

    static final String FILE_NAME = "students.dat";
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        if (!adminLogin()) return;

        loadStudents();

        System.out.print("Is internal & external pattern followed? (yes/no): ");
        boolean hasInternal = sc.next().equalsIgnoreCase("yes");

        while (true) {
            System.out.println("\n===== MENU =====");
System.out.println("1. Add Student");
System.out.println("2. Generate Report by Roll No");
System.out.println("3. Modify Report by Roll No");
System.out.println("4. Search Student by Name");
System.out.println("5. Class-wise Student List");
System.out.println("6. Exit");
System.out.print("Choose option: ");


            int choice = sc.nextInt();

            switch (choice) {
    case 1 -> addStudent(hasInternal);
    case 2 -> generateReport();
    case 3 -> modifyReport();
    case 4 -> searchByName();
    case 5 -> classWiseList();
    case 6 -> {
        saveStudents();
        System.out.println("Data saved. Exiting...");
        return;
    }
    default -> System.out.println("Invalid choice.");
}

        }
    }

    static boolean adminLogin() {
        System.out.print("Admin Username: ");
        String u = sc.next();
        System.out.print("Admin Password: ");
        String p = sc.next();

        if (u.equals(Admin.USERNAME) && p.equals(Admin.PASSWORD)) {
            System.out.println("Login successful.\n");
            return true;
        }
        System.out.println("Invalid login.");
        return false;
    }

    static void addStudent(boolean hasInternal) {

        System.out.print("Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.println("Roll No already exists.");
                return;
            }
        }

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Class (1-12): ");
        int cls = sc.nextInt();

        System.out.print("Number of subjects: ");
        int subCount = sc.nextInt();
        sc.nextLine();

        Student s = new Student(subCount, hasInternal);
        s.rollNo = roll;
        s.name = name;
        s.studentClass = cls;

        for (int i = 0; i < subCount; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            s.subjects[i] = sc.nextLine();
        }

        if (hasInternal) {
            for (int i = 0; i < subCount; i++) {
                System.out.print("Internal marks (" + s.subjects[i] + "): ");
                s.internalMarks[i] = sc.nextInt();
            }
        }

        for (int i = 0; i < subCount; i++) {
            System.out.print("Marks (" + s.subjects[i] + "): ");
            s.externalMarks[i] = sc.nextInt();
        }

        students.add(s);
        System.out.println("Student added.");
    }

    static void generateReport() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.rollNo == roll) {
                s.displayReport();
                return;
            }
        }
        System.out.println("Student not found.");
    }

static void searchByName() {
    sc.nextLine();
    System.out.print("Enter student name: ");
    String searchName = sc.nextLine();

    boolean found = false;
    for (Student s : students) {
        if (s.name.equalsIgnoreCase(searchName)) {
            s.displayReport();
            found = true;
        }
    }

    if (!found) {
        System.out.println("Student not found.");
    }
}

static void classWiseList() {
    System.out.print("Enter class (1-12): ");
    int cls = sc.nextInt();

    boolean found = false;

    System.out.println("\nROLL NO   NAME                    TOTAL MARKS");
    System.out.println("------------------------------------------------");

    for (Student s : students) {
        if (s.studentClass == cls) {
            s.calculateTotals();  // ensure totals are updated

            System.out.printf("%-9d %-22s %-10d\n",
                    s.rollNo,
                    s.name,
                    s.totalObtained());

            found = true;
        }
    }

    if (!found) {
        System.out.println("No students found for this class.");
    }
}



    static void modifyReport() {
    System.out.print("Enter Roll No: ");
    int roll = sc.nextInt();

    for (Student s : students) {
        if (s.rollNo == roll) {

            while (true) {
                System.out.println("\n--- MODIFY MENU ---");
                System.out.println("1. Change Name");
                System.out.println("2. Change Roll No");
                System.out.println("3. Change Subject Name");
                System.out.println("4. Change Internal Marks");
                System.out.println("5. Change External Marks");
                System.out.println("6. Back");
                System.out.print("Choose: ");

                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> {
                        sc.nextLine();
                        System.out.print("New Name: ");
                        s.name = sc.nextLine();
                        System.out.println("Name updated.");
                    }

                    case 2 -> {
                        System.out.print("New Roll No: ");
                        s.rollNo = sc.nextInt();
                        System.out.println("Roll No updated.");
                    }

                    case 3 -> {
                        for (int i = 0; i < s.subjectCount; i++) {
                            System.out.println((i + 1) + ". " + s.subjects[i]);
                        }
                        System.out.print("Select subject: ");
                        int idx = sc.nextInt() - 1;
                        sc.nextLine();
                        System.out.print("New Subject Name: ");
                        s.subjects[idx] = sc.nextLine();
                        System.out.println("Subject updated.");
                    }

                    case 4 -> {
                        if (!s.hasInternal) {
                            System.out.println("Internal marks not applicable.");
                            break;
                        }
                        for (int i = 0; i < s.subjectCount; i++) {
                            System.out.println((i + 1) + ". " + s.subjects[i]);
                        }
                        System.out.print("Select subject: ");
                        int idx = sc.nextInt() - 1;
                        System.out.print("New Internal Marks: ");
                        s.internalMarks[idx] = sc.nextInt();
                        System.out.println("Internal marks updated.");
                    }

                    case 5 -> {
                        for (int i = 0; i < s.subjectCount; i++) {
                            System.out.println((i + 1) + ". " + s.subjects[i]);
                        }
                        System.out.print("Select subject: ");
                        int idx = sc.nextInt() - 1;
                        System.out.print("New External Marks: ");
                        s.externalMarks[idx] = sc.nextInt();
                        System.out.println("External marks updated.");
                    }

                    case 6 -> {
                        return;
                    }

                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
    System.out.println("Student not found.");
}


    static void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    static void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("Students loaded from previous session.");
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}
