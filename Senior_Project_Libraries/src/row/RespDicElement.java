package row;

import java.util.ArrayList;

public class RespDicElement {
	String cmdName;
	ArrayList<Integer> bytes;

	public RespDicElement(String name, ArrayList<Integer> bytesList) {
	
		cmdName = name;
		bytes = bytesList;
	
	}
	
	public String getCmdName() {
		
		return cmdName;
		
	}
	
	public ArrayList<Integer> getBytes() {
		
		return bytes;
		
	}
}
