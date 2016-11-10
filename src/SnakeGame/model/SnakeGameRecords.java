package SnakeGame.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
 * game records
 */
public class SnakeGameRecords implements Serializable {

	private static final long serialVersionUID = 4513299590910220441L;
	
	private static final int TOP_TEN = 10;
	
	private Record[] records = null;
	
	//exist record number
	private int numberInRecord = 0;
	
	public SnakeGameRecords() {
		records = new Record[TOP_TEN];
	}
	
	@SuppressWarnings("unchecked")
	public void sortRecords() {
		Collections.sort(Arrays.asList(getAvailableRecords()), new RecordComparator());
	}
	
	private Record[] getAvailableRecords() {
		Record[] availableRecords = new Record[numberInRecord];
		for(int i = 0; i < numberInRecord; i++){
			availableRecords[i] = new Record(records[i].getPlayer(), records[i].getScore());
		}
		return availableRecords;
	}
	
	public Record[] getSortedAvailableRecords() {
		Record[] availableRecords = new Record[numberInRecord];
		for(int i = 0; i < numberInRecord; i++){
			availableRecords[i] = new Record(records[i].getPlayer(), records[i].getScore());
		}
		Collections.sort(Arrays.asList(availableRecords), new RecordComparator());
		
		return availableRecords;
	}
	
	/**
	 * @return last available record
	 */
	public Record getLastAvailableRecord() {
		return isEmpty() ? null : records[numberInRecord - 1];
	}
	
	/**
	 * @param record
	 */
	public void addRecordToTopTen(Record record) {
		if (isEmpty()) {
			records[0] = record;
			numberInRecord++;
			return;
		}

		if (isFull()) {
			if (records[TOP_TEN - 1].getScore() < record.getScore()) {
				records[TOP_TEN - 1] = record;
				sortRecords();
				return;
			}
		}
		
		records[numberInRecord] = record;
		numberInRecord++;
		sortRecords();
	}
	
	public boolean isEmpty() {
		return 0 == numberInRecord;
	}
	
	public boolean isFull() {
		return TOP_TEN == numberInRecord;
	}
	
	/**
	 * @return numberInRecord
	 */
	public int getNumberInRecord() {
		return numberInRecord;
	}
	
	/**
	 * @param numberInRecord
	 * set record number
	 */
	public void setNumberInRecord(int numberInRecord) {
		this.numberInRecord = numberInRecord;
	}
	
	/**
	 * @return records
	 */
	public Record[] getRecords() {
		return records;
	}
	
	/**
	 * @param records
	 * set records
	 */
	public void setRecords(Record[] records) {
		this.records = records;
	}
	
	private class RecordComparator implements Comparator {
		
		public int compare(Object o1, Object o2) {
			Record r1 = (Record) o1;
			Record r2 = (Record) o2;
			
			return (0 == compareScore(r1, r2)) ? compareName(r1, r2) : compareScore(r1, r2);
		}
		
		private int compareScore(Record r1, Record r2) {
			return r2.getScore() - r1.getScore();
		}
		
		private int compareName(Record r1, Record r2) {
			return r1.getPlayer().compareTo(r2.getPlayer());
		}
	}
}
