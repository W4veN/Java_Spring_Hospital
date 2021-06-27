package pl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.air.hospital.init.DataLoader;
import pl.air.hospital.init.DataLoaderImpl;

@SpringBootApplication
public class HospitalApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

   @Autowired
   DataLoader dataLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.insertData();
    }
}
