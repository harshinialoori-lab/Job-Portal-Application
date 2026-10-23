import java.util.ArrayList;
import java.util.Scanner;

class Job {
    int id;
    String title;
    String company;
    String location;
    String salary;

    Job(int id, String title, String company,
        String location, String salary) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
    }

    void display() {
        System.out.println("\nJob ID: " + id);
        System.out.println("Job Title: " + title);
        System.out.println("Company: " + company);
        System.out.println("Location: " + location);
        System.out.println("Salary: " + salary);
        System.out.println("--------------------------------");
    }
}

public class JobPortal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Job> jobs = new ArrayList<>();
    static ArrayList<String> applications = new ArrayList<>();
    static int nextId = 4;

    public static void main(String[] args) {

        jobs.add(new Job(1, "Java Developer",
                "TCS", "Hyderabad", "4 LPA"));
        jobs.add(new Job(2, "Software Engineer",
                "Infosys", "Bangalore", "5 LPA"));
        jobs.add(new Job(3, "Web Developer",
                "Wipro", "Hyderabad", "3.5 LPA"));

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("       JOB PORTAL SYSTEM");
            System.out.println("================================");
            System.out.println("1. View All Jobs");
            System.out.println("2. Search Jobs");
            System.out.println("3. Add New Job");
            System.out.println("4. Apply for a Job");
            System.out.println("5. View Applications");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    viewJobs();
                    break;
                case 2:
                    searchJobs();
                    break;
                case 3:
                    addJob();
                    break;
                case 4:
                    applyForJob();
                    break;
                case 5:
                    viewApplications();
                    break;
                case 6:
                    System.out.println(
                        "Thank you for using Job Portal!");
                    break;
                default:
                    System.out.println(
                        "Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.nextLine();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    static void viewJobs() {
        if (jobs.isEmpty()) {
            System.out.println("No jobs available.");
            return;
        }

        System.out.println("\n--- AVAILABLE JOBS ---");
        for (Job job : jobs) {
            job.display();
        }
    }

    static void searchJobs() {
        System.out.print(
            "Enter job title or location to search: ");
        String keyword = sc.nextLine().trim();

        boolean found = false;

        for (Job job : jobs) {
            if (job.title.toLowerCase().contains(
                    keyword.toLowerCase())
                || job.location.toLowerCase().contains(
                    keyword.toLowerCase())) {
                job.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching jobs found.");
        }
    }

    static void addJob() {
        System.out.print("Enter job title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter company name: ");
        String company = sc.nextLine().trim();

        System.out.print("Enter location: ");
        String location = sc.nextLine().trim();

        System.out.print("Enter salary: ");
        String salary = sc.nextLine().trim();

        if (title.isEmpty() || company.isEmpty()
                || location.isEmpty() || salary.isEmpty()) {
            System.out.println(
                "All fields are required. Job not added.");
            return;
        }

        jobs.add(new Job(nextId++, title, company,
                location, salary));

        System.out.println("Job added successfully!");
    }

    static void applyForJob() {
        viewJobs();

        System.out.print("Enter Job ID to apply: ");
        int id = readInt();

        Job selectedJob = null;

        for (Job job : jobs) {
            if (job.id == id) {
                selectedJob = job;
                break;
            }
        }

        if (selectedJob == null) {
            System.out.println("Job ID not found.");
            return;
        }

        System.out.print("Enter your full name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter your email: ");
        String email = sc.nextLine().trim();

        if (name.isEmpty() || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println(
                "Enter a valid name and email address.");
            return;
        }

        applications.add(
            "Applicant: " + name
            + " | Email: " + email
            + " | Job: " + selectedJob.title
            + " | Company: " + selectedJob.company
        );

        System.out.println(
            "Application submitted successfully!");
    }

    static void viewApplications() {
        System.out.println("\n--- JOB APPLICATIONS ---");

        if (applications.isEmpty()) {
            System.out.println("No applications submitted yet.");
            return;
        }

        for (int i = 0; i < applications.size(); i++) {
            System.out.println((i + 1) + ". "
                    + applications.get(i));
        }
    }
}