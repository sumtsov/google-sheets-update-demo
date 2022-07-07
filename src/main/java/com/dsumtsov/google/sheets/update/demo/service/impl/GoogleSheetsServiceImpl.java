package com.dsumtsov.google.sheets.update.demo.service.impl;

import com.dsumtsov.google.sheets.update.demo.config.properties.GoogleSheetsProperties;
import com.dsumtsov.google.sheets.update.demo.service.GoogleSheetsService;
import com.dsumtsov.google.sheets.update.demo.util.GoogleSheetsUtils;
import com.google.api.services.sheets.v4.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final GoogleSheetsProperties properties;

    @Override
    public void updateSpreadsheet() {
        var batchRequest = createBatchUpdateRequest();
        try {
            var batchResponse = GoogleSheetsUtils.getSheetsService()
                    .spreadsheets()
                    .batchUpdate(properties.getId(), batchRequest)
                    .execute();

            if (Objects.nonNull(batchResponse)
                    && Objects.nonNull(batchResponse.getUpdatedSpreadsheet())) {
                log.info("Spreadsheet updated successfully");
            } else {
                log.error("Failed to update spreadsheet, received empty response");
            }
        } catch (Exception e) {
            log.error("Failed to update spreadsheet: {}", e.getMessage());
        }
    }

    private BatchUpdateSpreadsheetRequest createBatchUpdateRequest() {
        return new BatchUpdateSpreadsheetRequest()
                .setIncludeSpreadsheetInResponse(true)
                .setResponseRanges(properties.getSheetsNames())
                .setIncludeSpreadsheetInResponse(true)
                .setRequests(createRequests());
    }

    private List<Request> createRequests() {
        return properties.getSheetsIds().stream()
                .map(id -> new Request().setAppendCells(
                        createAppendCellRequest(id)))
                .collect(Collectors.toList());
    }

    private AppendCellsRequest createAppendCellRequest(Integer sheetId) {
        var appendCellsRequest = new AppendCellsRequest();
        appendCellsRequest.setSheetId(sheetId);
        appendCellsRequest.setRows(createRowData());
        appendCellsRequest.setFields("userEnteredValue");
        return appendCellsRequest;
    }

    private List<RowData> createRowData() {
        return List.of(
                new RowData().setValues(
                        List.of(
                                new CellData().setUserEnteredValue(
                                        new ExtendedValue().setStringValue("Value 1")),
                                new CellData().setUserEnteredValue(
                                        new ExtendedValue().setStringValue("Value 2")),
                                new CellData().setUserEnteredValue(
                                        new ExtendedValue().setStringValue("Value 3")))));
    }
}
