package ripemd128;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RIPEMD128 {

    private int h0, h1, h2, h3;

    private static final int[] RL = {
            11, 14, 15, 12, 5, 8, 7, 9, 11, 13, 14, 15, 6, 7, 9, 8,
            7, 6, 8, 13, 11, 9, 7, 15, 7, 12, 15, 9, 11, 7, 13, 12,
            11, 13, 6, 7, 14, 9, 13, 15, 14, 8, 13, 6, 5, 12, 7, 5,
            11, 12, 14, 15, 14, 15, 9, 8, 9, 14, 5, 6, 8, 6, 5, 12
    };

    private static final int[] RR = {
            8, 9, 9, 11, 13, 15, 15, 5, 7, 7, 8, 11, 14, 14, 12, 6,
            9, 13, 15, 7, 12, 8, 9, 11, 7, 7, 12, 7, 6, 15, 13, 11,
            9, 7, 15, 11, 8, 6, 6, 14, 12, 13, 5, 14, 13, 13, 7, 5,
            15, 5, 8, 11, 14, 14, 6, 14, 6, 9, 12, 9, 12, 5, 15, 8
    };

    private static final int[] ML = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            7, 4, 13, 1, 10, 6, 15, 3, 12, 0, 9, 5, 2, 14, 11, 8,
            3, 10, 14, 4, 9, 15, 8, 1, 2, 7, 0, 6, 13, 11, 5, 12,
            1, 9, 11, 10, 0, 8, 12, 4, 13, 3, 7, 15, 14, 5, 6, 2
    };

    private static final int[] MR = {
            5, 14, 7, 0, 9, 2, 11, 4, 13, 6, 15, 8, 1, 10, 3, 12,
            6, 11, 3, 7, 0, 13, 5, 10, 14, 15, 8, 12, 4, 9, 1, 2,
            15, 5, 1, 3, 7, 14, 6, 9, 11, 8, 12, 2, 10, 0, 13, 4,
            8, 6, 4, 1, 3, 11, 15, 0, 5, 12, 2, 13, 9, 7, 10, 14
    };

    private static final int[] KL = { 0x00000000, 0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC };
    private static final int[] KR = { 0x50A28BE6, 0x5C4DD124, 0x6D703EF3, 0x00000000 };

    private int f1(int x, int y, int z) { return x ^ y ^ z; }
    private int f2(int x, int y, int z) { return (x & y) | (~x & z); }
    private int f3(int x, int y, int z) { return (x | ~y) ^ z; }
    private int f4(int x, int y, int z) { return (x & z) | (y & ~z); }

    private static int rotl(int x, int n) { return (x << n) | (x >>> (32 - n)); }

    public RIPEMD128() { reset(); }

    private void reset() {
        h0 = 0x67452301;
        h1 = 0xEFCDAB89;
        h2 = 0x98BADCFE;
        h3 = 0x10325476;
    }

    private byte[] padMessage(byte[] message) {
        long messageLength = message.length * 8L;
        int paddingLength = 64 - ((message.length + 9) % 64);
        if (paddingLength == 64) paddingLength = 0;

        int totalLength = message.length + 1 + paddingLength + 8;
        byte[] padded = new byte[totalLength];
        System.arraycopy(message, 0, padded, 0, message.length);
        padded[message.length] = (byte) 0x80;

        for (int i = 0; i < 8; i++) {
            padded[padded.length - 8 + i] = (byte) (messageLength >>> (i * 8));
        }
        return padded;
    }

    private void processBlock(byte[] block) {
        int[] X = new int[16];
        ByteBuffer buffer = ByteBuffer.wrap(block).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < 16; i++) X[i] = buffer.getInt(i * 4);

        int al = h0, bl = h1, cl = h2, dl = h3;
        int ar = h0, br = h1, cr = h2, dr = h3;

        for (int i = 0; i < 64; i++) {
            int round = i / 16;
            int func = 0, t;
            switch (round) {
                case 0 -> func = f1(bl, cl, dl);
                case 1 -> func = f2(bl, cl, dl);
                case 2 -> func = f3(bl, cl, dl);
                case 3 -> func = f4(bl, cl, dl);
            }
            t = (al + func + X[ML[i]] + KL[round]) & 0xFFFFFFFF;
            t = rotl(t, RL[i]);
            al = dl; dl = cl; cl = bl; bl = (bl + t) & 0xFFFFFFFF;
        }

        for (int i = 0; i < 64; i++) {
            int round = i / 16;
            int func = switch (round) {
                case 0 -> f4(br, cr, dr);
                case 1 -> f3(br, cr, dr);
                case 2 -> f2(br, cr, dr);
                case 3 -> f1(br, cr, dr);
                default -> 0;
            };
            int t = (ar + func + X[MR[i]] + KR[round]) & 0xFFFFFFFF;
            t = rotl(t, RR[i]);
            ar = dr; dr = cr; cr = br; br = (br + t) & 0xFFFFFFFF;
        }

        int t = (h1 + cl + dr) & 0xFFFFFFFF;
        h1 = (h2 + dl + ar) & 0xFFFFFFFF;
        h2 = (h3 + al + br) & 0xFFFFFFFF;
        h3 = (h0 + bl + cr) & 0xFFFFFFFF;
        h0 = t;
    }

    public String hash(byte[] message) {
        reset();
        byte[] padded = padMessage(message);
        for (int i = 0; i < padded.length; i += 64) {
            byte[] block = new byte[64];
            System.arraycopy(padded, i, block, 0, 64);
            processBlock(block);
        }
        return String.format("%08x%08x%08x%08x",
                Integer.reverseBytes(h0),
                Integer.reverseBytes(h1),
                Integer.reverseBytes(h2),
                Integer.reverseBytes(h3));
    }

    public String hash(String message) {
        return hash(message.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        RIPEMD128 ripemd128 = new RIPEMD128();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=== RIPEMD-128 Hash Generator ===");
        System.out.print("Enter text to hash: ");
        String input = scanner.nextLine();

        String hashed = ripemd128.hash(input);
        System.out.println("\nInput:  " + input);
        System.out.println("RIPEMD-128 Hash: " + hashed);

        scanner.close();
    }
}
