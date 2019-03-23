package EmailApp;

import javax.persistence.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


@Entity
@Table(name = "UserEmail")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private String firstname;
    private String lastname;
    private String password;
    int defaultPasswordLength = 10;
    private String department;
    String email;
    private int mailboxCapacity = 500;
    private String alternateEmail;

    // Constructor to receive first name and last name
    public Email(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        //System.out.println("Email created : " + this.firstname + " " + this.lastname);

        // Call a method asking for department and return the department
        this.department = setDepartment();
        //System.out.println("Department : " + this.department);

        // Call a method that returns a random password

        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Your password is : " + this.password + " Write it in safe place.");

        //Combine elements to generate email
        String companySuffix = "xyzcompany.com";
        email = firstname.toLowerCase() + "." + lastname.toLowerCase() + "@" + department + "." + companySuffix;
        //System.out.println("Your email : " + email);
    }

    // Ask for department
    private String setDepartment() {
        System.out.print("New employee : " + firstname + ". Department codes : \n1 for Sales\n2 for Development\n3 for Accounting\n0 for none\n\nEnter Department Code : ");

        Scanner scanner = new Scanner(System.in);
        int depChoice = scanner.nextInt();

        if (depChoice == 1) {
            System.out.println();
            return "Sales";
        } else if (depChoice == 2) {
            System.out.println();
            return "Development";
        } else if (depChoice == 3) {
            System.out.println();
            return "Accounting";
        } else {
            System.out.println();
            return "";
        }
    }

    // Generate a random password
    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUWVXYZ0123456789!$#@%";
        char[] password = new char[length];
        for (int i = 0; i < password.length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    // Set mailbox capacity
    public void setMailBoxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Set the alternate email
    public void setAlternateEmail(String altEmail) {
        this.alternateEmail = altEmail;
    }

    // Change the password
    public void changePassword(String password) {
        this.password = password;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getPassword() {
        return password;
    }

    public String showInfo() {
        return "DISPLAY NAME : " + firstname + " " + lastname +
                "\nCOMPANY EMAIL : " + email +
                "\nMAILBOX CAPACITY : " + mailboxCapacity + "mb";
    }

    public void writeFile(String filepath, String generatedMail) {
        try {
            File file = new File(filepath);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(generatedMail);
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
