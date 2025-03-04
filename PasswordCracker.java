import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordCracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String correctPassword = "PaSsW"; // Hardcoded correct password (as per task)
        String dictionaryFilePath = "/path/to/your/dictionary.txt"; // Replace with the actual path

        if (dictionaryAttack(username, correctPassword, dictionaryFilePath)) {
            System.out.println("Login successful using dictionary attack!");
        } else {
            System.out.println("Login failed using dictionary attack. Trying brute force...");
            bruteForceAttack(username, correctPassword);
        }

        scanner.close();
    }

    public static boolean dictionaryAttack(String username, String correctPassword, String dictionaryFilePath) {
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

    public static void bruteForceAttack(String username, String correctPassword) {
        charcharset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int passwordLength = 5;
        charattempt = new char[passwordLength];

        if (bruteForceRecursive(charset, passwordLength, attempt, 0, correctPassword)) {
            System.out.println("Password cracked using brute force!");
        } else {
            System.out.println("Brute force failed.");
        }
    }

    public static boolean bruteForceRecursive(charcharset, int passwordLength, charattempt, int index, String correctPassword) {
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