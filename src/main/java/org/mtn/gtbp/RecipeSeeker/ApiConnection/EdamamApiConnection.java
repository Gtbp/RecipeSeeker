package org.mtn.gtbp.RecipeSeeker.ApiConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class EdamamApiConnection {
	
//	Tester de sauver en base
//	Git ignore
//	Push
//	Si temps, regarder pour delete apiKey et appId des archives

	private static String apiKey;
	private static String appId;

	private final static String apiUrlTemplate = "https://api.edamam.com/search?q={query}&app_id={appId}&app_key={apiKey}";

	public static Map<String, String> getDataConfigProperties() {
		Properties properties = new Properties();
		FileInputStream input;
        Map<String, String> apiCredentials = new HashMap<>();

		try {
			input = new FileInputStream("src/main/resources/config.properties");
			try {
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			apiKey = properties.getProperty("api_key");
			appId = properties.getProperty("app_id");
			
			System.out.println("api_key=" + apiKey);
			System.out.println("app_id=" + appId);
			
			apiCredentials.put("apiKey", apiKey);
			apiCredentials.put("appId", appId);
			
		} catch (FileNotFoundException e) {
			System.out.println("Config.properties doesn't exist");
			e.printStackTrace();
		}
		return apiCredentials; 
	}

	public String fetchApiEdamam(String query) {
        Map<String, String> credentials = getDataConfigProperties();
        
        apiKey = credentials.get("apiKey");
        appId = credentials.get("appId");

		// URL creation with dynamic parameter (query)
		String apiUrl = apiUrlTemplate.replace("{query}", query)
					.replace("{appId}", appId)
					.replace("{apiKey}", apiKey);

		// Call to Api
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", apiKey);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

		// Response Api treatment
		String responseBody = response.getBody();
		return responseBody;
	}

}
