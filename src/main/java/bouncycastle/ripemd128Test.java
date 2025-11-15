package bouncycastle;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class RIPEMD128Test {
    public static void main(String[] args) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter text to hash:  ");
            String input = scanner.nextLine();

            MessageDigest digest = MessageDigest.getInstance("RIPEMD128", "BC");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }

            System.out.println("RIPEMD-128 Hash: " + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
