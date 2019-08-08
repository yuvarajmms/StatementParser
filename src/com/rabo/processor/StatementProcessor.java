package com.rabo.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.rabobank.model.Record;

public class StatementProcessor {

	public static void main(String args[])
    {
		try{
			Path path = Paths.get("issues.csv");
			XmlStatementProcessor xmlProcessor = new XmlStatementProcessor();
			List<Record> xmlList = xmlProcessor.xmlReportValidation();
			
			CSVProcessor csvProcessor = new CSVProcessor();
			List<Record> csvList = csvProcessor.csvValidation();
			
			try (BufferedWriter writer = Files.newBufferedWriter(path))
			{
				for(Record record:xmlList){
					writer.write(record.getReference()+","+record.getDescription()+"\n");
				}
				
				for(Record record:csvList){
					writer.append(record.getReference()+","+record.getDescription()+"\n");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
