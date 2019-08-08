package com.rabo.processor;


import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rabobank.model.Record;
import com.opencsv.bean.CsvToBean;

public class CSVProcessor {

	public List<Record> csvValidation() throws IOException {
		String SAMPLE_CSV_FILE_PATH = "records.csv";
		List<Record> invalidList = new ArrayList<Record>();
		List refList = new ArrayList();
		 try (
		            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
		        ) {
		            CsvToBean<Record> csvToBean = new CsvToBeanBuilder(reader)
		                    .withType(Record.class)
		                    .withIgnoreLeadingWhiteSpace(true)
		                    .build();

		            Iterator<Record> recordIter = csvToBean.iterator();
		            
		            while (recordIter.hasNext()) {
		                Record record = recordIter.next();
		                
		                if(refList.contains(record.getReference())){
		                	invalidList.add(record);
		                	break;
		                }else{
		                	refList.add(record.getReference());
		                }

		                BigDecimal start = new BigDecimal(String.valueOf(record.getStartBalance()));
		                BigDecimal mutation = new BigDecimal(String.valueOf(record.getMutation()));
		                BigDecimal sum = start.add(mutation);
		                double bal = sum.doubleValue();
		                //validation for end balance 
		                if(record.getEndBalance() != bal){
		                	invalidList.add(record);
		                }
		            }
		        }
		 return invalidList;
	}

}
