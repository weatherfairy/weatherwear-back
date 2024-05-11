package com.weatherfairy.weatherwearback.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credentials.path}")
    private String keyPath;

    private String key ="{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"weather-wear-a7674\",\n" +
            "  \"private_key_id\": \"d57266459696eb73825337e4c94d841c0f092d60\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCSOo0UWn6pyy5d\\nHvaKDYpdRtOFV9OEWoOISbcSVe9aGH+qWwZvyTtuM59JyoYh0umUkix19Me8btYY\\npbetYxGbScR6kboMK3JsdEqhNatKAhh8Swco54EYHbw64VBN269Wy59MvrbXQzxO\\nS63uBAqxChJ5CDCa5yYjFnOObO3z0MspZNNjZ8eV4sB7IhcUFMP4C567m/WXNNFf\\n+Xu7hin4BB7xxIbTKGdMftUYGxDjKNmpVpIns/8Pqrpa4xQsQIi4rW7AsLuREfPw\\nEUS0F4VlohtAEbeT8jEkOnP9KoxG9HexgnwwoG0mDOE3yoVAcNVmnVbGovAzeEpl\\nj1CFv9sfAgMBAAECggEAB6E1dj/ohvx6fVJdw8NEGmzVIYp9F1VPsp9CBZtlQzqv\\nApkAQ3no922LHKMMD5y9IkrkNf1hJJRV6HBRHqNiKkuVjF2pKvRjYoRI1pOBzflB\\nBM83XuuBYVw/04SpF37kba9vLuZujNnNvjoVp6sfUbB4WUdTnel8pG0J9374hXWl\\nyPt+RnXcqb4+o/NbGv89SPrEgnMOA2eqYKraKtg+RfuyMwO8SI04V21c+VinzSym\\n+B2wC3E+a4CBLJHL2rU9jWJcygOIaRbf7b/VoRtWQ4bHCUyBx4WRzkNU7jpYmxrr\\nmm6yqGty+gtgQXoHJwcvnkynL4JVBSP/jEQkA9D64QKBgQDHVUyEZ0l3zd3V1DyE\\nvSNmkCpozhia6Vvi3ZRFoK4v6lf2q9SrmjhbQhi9iSyDaqdL4Fc04u8aJdNm69uD\\nVJXm9XvvA0UbubZFafOGYBVf3hBcgEaImwGlf+JadF+LTj0ueDJX/5HcCCCtv85s\\nhx5uSB3QAZ2cHXP6WT2IId6T4QKBgQC7zINFXkF9JDjYEiQKxUSEuxaD4hdm7Cit\\nzSFvBlZghrsnbxR69lhhCdwC6tsmznRKwtjMBXMKNxHbYe4xPWaPct6vg00fZtCo\\nE4QXuJd5WdLpAS9PKYAvuHjzbHkwZuo3RHICywuxO/3r6LW2y/AYLOOovv4tO7W6\\nX6rQN3xO/wKBgFI6Ws1HumPklb1uxuRPDbpygDwpwLauy0vj8hVi+lgjyNOmAJ4l\\niVgEBSay3pz7szW2vuK/NrlHn1SWuUAM9aWH4O8xHyIX0xwBYAcvvcGFXqR5+h7C\\nKsS1449C8GMMDGsOBF6OvAHTjH+AX0wf1SrTLxEuiXDsKXq9s4djyWihAoGAY22y\\nCqYOsjzdQ7jHEFTFJCkRkJ3BmiJqnyo+C1Qlo1FyATzUcQ3AWRApRfomMXOZKoPi\\n2JICCGSDtc0xmSxTApvkvrQbvwZmbrZ2d0CqIfumqOqRq63jSmC22NqTh0bVtpEs\\nc8Ig3KNQmvhITRHIemQ53HvSgwxb+OLxwu+9hu0CgYApToOB7Y0pKe68W4UpmcJK\\n2Wj18wAznNyEVe8P4paHtxFyDn42I0pPZ+9lBZHjcU2Z7ZrngeAM+ws6pMy8FZ5W\\npc5IjbBkg9U3N1jDofdRzPQPmJWJKAGELluVWkfyAt8uJyU59YzscqXCiCBRNh/P\\ntKhzlE9HkETErPGKSLPGgA==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"firebase-adminsdk-2batn@weather-wear-a7674.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"100953604256679082382\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-2batn%40weather-wear-a7674.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}\n";

    @PostConstruct
    public void init() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                ByteArrayInputStream serviceAccount = new ByteArrayInputStream(key.getBytes(StandardCharsets.UTF_8));

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase 초기화 성공");

            } else {
                System.out.println("Firebase 앱이 이미 초기화되어 있습니다.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("firebase config 오류");
        }
    }
}
