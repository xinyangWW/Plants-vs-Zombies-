package tp1.p2.logic;

import java.io.*;

import tp1.p2.control.Level;

import tp1.p2.control.exceptions.RecordException;

public class Record {
	private Level level;
	private String[] record;
	private int record_score = 0;
	
	public Record(Level level) throws RecordException {
		this.level = level;
		try {
			FileReader reader = new FileReader("record.txt");
	 
	        StringBuffer sb = new StringBuffer();
	        while (reader.ready()) {
	            sb.append((char) reader.read());
	        }
	        record = sb.toString().split("\n");
	        
	        try {
		        for(String str : record) {
		        	if(level.name().equalsIgnoreCase(str.split(":")[0])) record_score = Integer.parseInt(str.split(":")[1]);
		        }
	        } catch(NumberFormatException nfe) {
	        	throw new RecordException(nfe);
	        } finally {
		        reader.close();     	
	        }
	        
		} catch(IOException e) {
			throw new RecordException(e);
		}
	}
	
	public void save(int score) throws RecordException{
		try {
		    FileWriter writer = new FileWriter("record.txt");
	        boolean found = false;
	        for(String str : record) {
			    if(!found && level.name().equalsIgnoreCase(str.split(":")[0])) {
			    	found = true;
			    	writer.append(level.name() + ":" + score + "\n");
			    }
			    else writer.append(str + "\n");
	        }
	        if(!found) writer.append(level.name() + ":" + score + "\n");
		    writer.close();
		}catch(IOException e) {
			throw new RecordException(e);
		}
	}
	
	public int getRecordScore() {
		return record_score;
	}
}
