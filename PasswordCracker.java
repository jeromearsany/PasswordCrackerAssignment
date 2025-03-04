import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordCracker {

    public static void main(String[] args) { // Fixed 'Stringargs' to 'String[] args'
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String correctPassword = "PaSsW"; // Hardcoded correct password (as per task)
        String dictionaryFilePath = "/path/to/your/dictionary.txt"; // Replace with the actual path

        if (dictionaryAttack(correctPassword, dictionaryFilePath)) { // Removed unnecessary 'username' parameter
            System.out.println("Login successful using dictionary attack!");
        } else {
            System.out.println("Login failed using dictionary attack. Trying brute force...");
            bruteForceAttack(correctPassword); // Removed unnecessary 'username' parameter
        }

        scanner.close();
    }

    public static boolean dictionaryAttack(String correctPassword, String dictionaryFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String password;
            while ((password = reader.readLine()) != null) {
                if (password.equals(correctPassword)) {
                    return true; // Password found in dictionary
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
        }
        return false; // Password not found in dictionary
    }

    public static void bruteForceAttack(String correctPassword) {
        char[] charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // Fixed 'charcharset'
        int passwordLength = 5;
        char[] attempt = new char[passwordLength]; // Fixed 'charattempt'

        if (bruteForceRecursive(charset, passwordLength, attempt, 0, correctPassword)) {
            System.out.println("Password cracked using brute force!");
        } else {
            System.out.println("Brute force failed.");
        }
    }

    public static boolean bruteForceRecursive(char[] charset, int passwordLength, char[] attempt, int index, String correctPassword) {
        if (index == passwordLength) {
            if (new String(attempt).equals(correctPassword)) {
                return true; // Password found
            }
            return false; // Password not found at this combination
        }

        for (char c : charset) {
            attempt[index] = c;
            if (bruteForceRecursive(charset, passwordLength, attempt, index + 1, correctPassword)) {
                return true;
            }
        }
        return false; // Password not found in this branch
    }
}
