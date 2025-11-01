package com.example.controller;

import com.example.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Обработка ошибок не предусмотрена, данный код предназначен исключительно для демонстрации алгоритма из сервиса.
 */
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService service;

    @Operation(summary = "Read Excel file and get minimum integers",
            description = "Reads the Excel file and returns a list of minimum {n} integers")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @PostMapping(value = "/{n}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Integer> readExcel(
            @Parameter(description = "Excel file", required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestParam("file") MultipartFile file, @PathVariable Integer n) {
        return service.getMinIntegers(file, n);
    }
}