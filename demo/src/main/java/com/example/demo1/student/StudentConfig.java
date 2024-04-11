package com.example.demo1.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.JANUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student theogene = new Student("theogene", "hahimwimanatheogene34@gmail.com",
                    LocalDate.of(2000,Month.JANUARY, 1));

            Student peter = new Student("peter", "peter@gmail.com",
                    LocalDate.of(2007,Month.JANUARY, 5));

            repository.saveAll(List.of(theogene, peter));
        };
    }
}
