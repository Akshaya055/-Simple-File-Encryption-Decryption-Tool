import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;

public class FileEncryption {

    public static void encryptFile(String inputFile, String outputFile, String password) throws Exception {
        // Generate a secret key from the password
        SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");

        // Initialize the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) new File(inputFile).length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();
    }

    public static void decryptFile(String inputFile, String outputFile, String password) throws Exception {
        // Generate a secret key from the password
        SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");

        // Initialize the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) new File(inputFile).length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) {
        try {
            String inputFile = "input.txt";
            String encryptedFile = "encrypted.txt";
            String decryptedFile = "decrypted.txt";
            String password = "SecretPassword";

            encryptFile(inputFile, encryptedFile, password);
            System.out.println("File encrypted successfully.");

            decryptFile(encryptedFile, decryptedFile, password);
            System.out.println("File decrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
