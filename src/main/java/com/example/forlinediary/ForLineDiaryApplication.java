package com.example.forlinediary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        parseMdFile(inputStream);

    }

    private void parseMdFile(InputStream inputStream) {
        List<FourLineDiary> fourLineDiaryArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] parced = str.split("\\|");
                if(parced.length == 6) {
                    fourLineDiaryArrayList.add(new FourLineDiary(parced[1], parced[2], parced[3], parced[4], parced[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
