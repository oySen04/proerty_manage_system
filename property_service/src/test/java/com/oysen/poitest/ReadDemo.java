package com.oysen.poitest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用poi读取Excel表格
 */
public class ReadDemo {
    public static void main(String[] args) {
        try {
            List<Product> products = read("E:\\WebProjects\\proerty_manage_system\\test.xlsx");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> read(String path) throws IOException {
        List<Product> products = new ArrayList<>();
        //1. 创建输入流
        FileInputStream fis = new FileInputStream(path);
        //2. 在输入流中获取Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //3. 在工作簿中获取目标工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取数据对应行数(有数据的)
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            //获取除了第一行以外的所有行
            Row row = sheet.getRow(i);
            if (row != null) {
                //用于保存数据集合
                List<String> list = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell != null) {
                        //把单元格中所有的数据设置为String
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        //获取所有单元格数据
                        String value = cell.getStringCellValue();
                        if (value != null && !value.equals("")) {
                            list.add(value);
                        }
                    }
                }
                //把获取到的数据封装成Product
                if (list.size() > 0) {
                    Product product = new Product(Integer.parseInt(list.get(0)),list.get(1),Double.parseDouble(list.get(2)),Integer.parseInt(list.get(3)));
                    //把product实体类封装到List集合中
                    products.add(product);
                }
            }
        }
        return products;
    }
}
