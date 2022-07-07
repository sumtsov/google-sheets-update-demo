package com.dsumtsov.google.sheets.update.demo.util;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.security.GeneralSecurityException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleSheetsUtils {

    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        var credentials = getCredentials();
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("wms-delivery-service")
                .build();
    }

    public static GoogleCredentials getCredentials() throws IOException {
        GoogleCredentials googleCredentials;
        try (var inputStream =
                     GoogleSheetsUtils.class.getResourceAsStream(
                             "credentials/google-credentials.json")) {
            googleCredentials =
                    GoogleCredentials.fromStream(inputStream).createScoped(SheetsScopes.SPREADSHEETS);
        }
        return googleCredentials;
    }
}
