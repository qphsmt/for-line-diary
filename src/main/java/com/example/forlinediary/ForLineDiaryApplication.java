package com.example.forlinediary;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForLineDiaryApplication implements CommandLineRunner {
    @Value("${dropbox.access.token}")
	private String ACCESS_TOKEN;

	public static void main(String[] args) {
		SpringApplication.run(ForLineDiaryApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        // Get current account info
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        System.out.println(account.getName().getDisplayName());
    }
}
