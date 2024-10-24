package mk.ukim.finki.aud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class AudApplication {

    public static void main(String[] args) {
        SpringApplication.run(AudApplication.class, args);
    }

}
