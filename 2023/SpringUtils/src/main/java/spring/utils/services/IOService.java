package spring.utils.services;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@Service
public class IOService {
    private final Scanner scanner;
    private final PrintWriter writer;

    public IOService(InputStream is, OutputStream os) {
        scanner = new Scanner(is);
        writer = new PrintWriter(os);
    }

    public void print(Object o) {
        writer.print(o);
        writer.flush();
    }

    public void println(Object o) {
        writer.println(o);
        writer.flush();
    }

    public int nextInt() {
        return scanner.nextInt();
    }
}
