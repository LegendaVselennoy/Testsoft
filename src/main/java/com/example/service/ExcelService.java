package com.example.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public List<Integer> getMinIntegers(MultipartFile file, Integer n) {
        List<Integer> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int columnIndex = 0;
            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    list.add((int) cell.getNumericCellValue());
                }
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sortIntegers(list);
        return list.stream().limit(n).toList();
    }

    private void sortIntegers(List<Integer> list) {
        sort(list, list.get(0), list.size() - 1);
    }

    private void sort(List<Integer> list, int start, int end) {
        if (end <= start) return;
        Integer j = partition(list, start, end);
        sort(list, start, j - 1);
        sort(list, j + 1, end);
    }

    private Integer partition(List<Integer> a, int start, int end) {
        int i = start, j = end + 1;
        Integer v = a.get(start);
        while (true) {
            while (a.get(++i).compareTo(v) < 0) if (i == end) break;
            while (v.compareTo(a.get(--j)) < 0) if (j == start) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, start, j);
        return j;
    }

    private void exch(List<Integer> a, int i, int j) {
        Integer t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }
}