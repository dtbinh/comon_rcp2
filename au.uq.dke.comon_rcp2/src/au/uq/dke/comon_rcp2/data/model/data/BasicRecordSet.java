package au.uq.dke.comon_rcp2.data.model.data;

import java.util.HashSet;

public class BasicRecordSet<T extends BasicRecord> extends HashSet<T>{

	@Override
	public boolean add(T e) {
		//find if exists already
		for(BasicRecord record : this){
			if(record.getId() == e.getId()){
				return false;
			}
		}
		//find no result
		return super.add(e);
	}

	@Override
	public boolean remove(Object o) {
		//find the object first
		BasicRecord recordInSet = null;
		for(BasicRecord record : this){
			if(record.getId() == ((BasicRecord)o).getId()){
				recordInSet = record;
				return super.remove(recordInSet);
			}
		}
		//found no match
		return false;
	}
	
	

}
