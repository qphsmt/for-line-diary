package com.example.forlinediary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ForLineDiaryApplication implements CommandLineRunner {
    @Value("${dropbox.access.token}")
    private String ACCESS_TOKEN;

    @Value("${dropbox.api.args}")
    private String API_ARGS;

    public static void main(String[] args) {
        SpringApplication.run(ForLineDiaryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = PaperDaoKt.httpDonwload("https://api.dropboxapi.com/2/paper/docs/download", ACCESS_TOKEN, API_ARGS);
        Files.copy(inputStream, Paths.get("test1.txt"));
    }
}
