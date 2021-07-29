package com.example.demo;

import com.example.demo.listeners.PingListener;
import com.example.demo.listeners.RaceListener;
import com.example.demo.listeners.RateListener;
import com.example.demo.listeners.UserListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DiscordBotApplication {

	@Autowired
	private Environment env;


	@Autowired
	private PingListener pingListener;

	@Autowired
	private RateListener rateListener;

	@Autowired
	private RaceListener raceListener;

	@Autowired
	private UserListener userListener;

	public static void main(String[] args) {
		SpringApplication.run(DiscordBotApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi() {
		String token = env.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllIntents()
				.login()
				.join();

		api.addMessageCreateListener(pingListener);
		api.addMessageCreateListener(rateListener);
		api.addMessageCreateListener(raceListener);
		api.addMessageCreateListener(userListener);
		return api;
	}
}
