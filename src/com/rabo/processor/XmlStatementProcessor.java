package com.rabo.processor;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.rabobank.model.Record;
import com.rabobank.model.Records;

public class XmlStatementProcessor {

	public List<Record> xmlReportValidation(){
			
		List<Record> invalidList = new ArrayList<Record>();
		try{
			//Used JAXB Parser to convert xml to pojo
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			List refList = new ArrayList();
			Records records = (Records) jaxbUnmarshaller.unmarshal(new File("records.xml"));
			
			//Iterating the pojo records to validate unique reference and end balance
			for(Record record : records.getRecord()){
				
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
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return invalidList;
	}


}
