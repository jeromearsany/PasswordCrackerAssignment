import java.io.*;

public class DictionaryAttack {
    private String dictionaryFile;

    public DictionaryAttack(String dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
    }

    public boolean attemptLogin(String correctPassword) {
        System.out.println("Starting Dictionary Attack...");

        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile))) {
            String passwordAttempt;
            while ((passwordAttempt = reader.readLine()) != null) {
                passwordAttempt = passwordAttempt.trim();
                System.out.println("Trying: " + passwordAttempt);
                if (passwordAttempt.equals(correctPassword)) {
                    System.out.println("Success! Password found: " + passwordAttempt);
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dictionary file.");
        }

        System.out.println("Dictionary Attack failed.");
        return false;
    }
}

