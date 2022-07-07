package com.dsumtsov.google.sheets.update.demo.controller;

import com.dsumtsov.google.sheets.update.demo.service.GoogleSheetsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/google-sheets")
@Tag(name = "google sheets update", description = "Google Sheets Update API")
public class GoogleSheetsController {

    private final GoogleSheetsService googleSheetsService;

    @PostMapping("/update")
    @Operation(summary = "Update Google spreadsheet")
    public void updateSpreadsheet() {
        log.info("Request: update google spreadsheet");
        googleSheetsService.updateSpreadsheet();
    }
}
