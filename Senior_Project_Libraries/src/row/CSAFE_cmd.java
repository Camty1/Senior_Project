package row;

import java.util.ArrayList;

public class CSAFE_cmd {
	
	public ArrayList<Integer> intToBytes(int numBytes, int num) {
		if (!(num >= 0 && num <= Math.pow(2, 8 * numBytes))) {
			System.out.println("The number is not within the valid range for conversion to bytes");
		}
		
		ArrayList<Integer> conByte = new ArrayList<Integer>();
		for (int i = 0; i < numBytes; i++) {
			int calcByte = (num >> (8 * i)) & 0xFF;
			conByte.add(calcByte);
		}
		return conByte;
		
	}
	
	public Integer bytesToInteger(ArrayList<Integer> rawBytes) {
		
		int numBytes = rawBytes.size();
		int num = 0;
		
		for (int i = 0; i < numBytes; i++) {
			num = (rawBytes.get(i) << (8 * i)) & num;
		}
		
		return num;
		
	}
	
	public String bytesToAscii (ArrayList<Integer> rawBytes) {

		String word = "";
		
		for (int i : rawBytes) {
			char ch = (char) i;
			word += ch;
		}
		
		return word;
	}
	
	public ArrayList<Integer> write(ArrayList<String> arguments) {
		
		int i = 0;
		int wrapper = 0;
		int maxResponse = 3;
		
		ArrayList<Integer> message = new ArrayList<Integer>();
		ArrayList<Integer> wrapped = new ArrayList<Integer>();
		
		while (i < arguments.size()) {
			
			String arg = arguments.get(i);
			CmdDicElement cmdProp = CSAFE_dic.getCmds().get(arg);
			ArrayList<Integer> command = new ArrayList<Integer>();
			
			if (cmdProp.getBytes().size() != 0) {
				for (int byteValue : cmdProp.getBytes()) {
					int intValue = cmdProp.getNextCmdId();
					ArrayList<Integer> value = intToBytes(byteValue, intValue);
					command.addAll(value);
				}
				
				int cmdLength = command.size();
				command.add(0, cmdLength);
			}
			
			command.add(0, cmdProp.getCmdId());
			
			if (wrapped.size() > 0 && (!cmdProp.hasNextCmdId() || cmdProp.getNextCmdId() != wrapper)) {
				wrapped.add(0, wrapped.size());
				wrapped.add(0, wrapper);
				message.addAll(wrapped);
				wrapped.clear();
				wrapper = 0;
			}
			
			if (cmdProp.hasNextCmdId()) {
				
				if (wrapper == cmdProp.getNextCmdId()) {
					
					wrapped.addAll(command);
				
				}
				
				else {
					
					wrapped = command;
					wrapper = cmdProp.getNextCmdId();
					maxResponse += 2;
					
				}
				
				command.clear();
				
			}
			
			int cmdId = cmdProp.getCmdId() | (wrapper << 8);
			
			int sum = 0;
			
			for (int j : cmdProp.getBytes()) {
				sum += j;
			}
			
			maxResponse += Math.abs(sum) * 2 + 1;
		}
		
		
		return message;
	}
}
