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
					// int intValue = ; Ask for help on this step, original library doesn't make sense
				}
			}
		}
		
		
		return message;
	}
}
