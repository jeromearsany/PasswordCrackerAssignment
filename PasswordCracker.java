import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordCracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String correctPassword = "PaSsW";
        String dictionaryFilePath = "/path/to/your/dictionary.txt";

        if (dictionaryAttack(correctPassword, dictionaryFilePath)) {
            System.out.println("Login successful using dictionary attack!");
        } else {
            System.out.println("Login failed using dictionary attack. Trying brute force...");
            bruteForceAttack(correctPassword);
        }

        scanner.close();
    }

    public static boolean dictionaryAttack(String correctPassword, String dictionaryFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String password;
            while ((password = reader.readLine()) != null) {
                if (password.equals(correctPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
        }
        return false;
    }

    public static void bruteForceAttack(String correctPassword) {
        char[] charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int passwordLength = 5;
        char[] attempt = new char[passwordLength];

        if (bruteForceRecursive(charset, passwordLength, attempt, 0, correctPassword)) {
            System.out.println("Password cracked using brute force!");
        } else {
            System.out.println("Brute force failed.");
        }
    }

    public static boolean bruteForceRecursive(char[] charset, int passwordLength, char[] attempt, int index, String correctPassword) {
        if (index == passwordLength) {
            if (new String(attempt).equals(correctPassword)) {
                return true;
            }
            return false;
        }

        for (char c : charset) {
            attempt[index] = c;
            if (bruteForceRecursive(charset, passwordLength, attempt, index + 1, correctPassword)) {
                return true;
            }
        }
        return false;
    }
}
