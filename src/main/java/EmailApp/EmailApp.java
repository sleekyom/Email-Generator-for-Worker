package EmailApp;

public class EmailApp {

    public static void main(String[] args) {
        Email email = new Email("Emmanuel", "Kolawole");
        System.out.println(email.showInfo());

        email.writeFile("C:\\Users\\olamide\\Documents\\EmailApp\\Generated Email.txt",email.showInfo());

    }
}
