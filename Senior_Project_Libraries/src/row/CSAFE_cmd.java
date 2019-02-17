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
}
