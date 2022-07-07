package com.dsumtsov.google.sheets.update.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(
        value = "app.google.spreadsheet",
        ignoreInvalidFields = true
)
public class GoogleSheetsProperties {

    private String id;
    private List<String> sheetsNames;
    private List<Integer> sheetsIds;
}
