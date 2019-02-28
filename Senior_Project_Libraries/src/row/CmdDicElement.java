package row;

import java.util.ArrayList;

public class CmdDicElement {
		
	int cmdId;
	ArrayList<Integer> bytes;
	int nextCmdId;
	
	private Boolean hasNextId = false;
		
	public CmdDicElement(int Id) {
	
		cmdId = Id;
		bytes = new ArrayList<Integer>();
			
	}
	
	public CmdDicElement(int Id, ArrayList<Integer> bytesList) {
		
		cmdId = Id;
		bytes = bytesList;
	
	}
	
	public CmdDicElement(int Id, int nextId) {
		
		cmdId = Id;
		bytes = new ArrayList<Integer>();
		nextCmdId = nextId;
	
	}
	
	public CmdDicElement(int Id, ArrayList<Integer> bytesList, int nextId) {
		
		cmdId = Id;
		bytes = bytesList;
		nextCmdId = nextId;
		hasNextId = true;
		
	}

	public int getCmdId() {
		
		return cmdId;
		
	}
	
	public ArrayList<Integer> getBytes() {
		
		return bytes;
		
	}
	
	public int getNextCmdId() {
		
		return nextCmdId;
	
	}
	
	public Boolean hasNextCmdId() {
		
		return hasNextId;
	
	}
	
}
