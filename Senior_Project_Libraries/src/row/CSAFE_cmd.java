package row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
			
			for (int j : CSAFE_dic.getResp().get(cmdId).getBytes()) {
				sum += j;
			}
			
			maxResponse += Math.abs(sum) * 2 + 1;
			
			message.addAll(command);
			
			i++;
		}
		
		if (wrapped.size() > 0) {
			
			wrapped.add(0, wrapped.size());
			wrapped.add(0, wrapper);
			message.addAll(wrapped);
		}
		
		int checkSum = 0x0;
		int j = 0;
		
		while (j < message.size()) {
			
			checkSum = (int) Math.pow(checkSum, message.get(j));
			
			if (0xF0 <= message.get(j) && message.get(j) <= 0xF3) {
				message.add(j, CSAFE_dic.getByteStuffingFlag());
				j++;
				message.set(j, message.get(j) & 0x3);
			}
			
			j++;
			
		}
		
		message.add(checkSum);
		
		message.add(0, CSAFE_dic.getStandardStartFlag());
		message.add(CSAFE_dic.getStopFlag());
		
		if (message.size() > 96) {
			
			System.out.println("The message is too long with " + message.size() + " bytes.");
			
		}
		
		int maxMessage = Math.max(message.size(), maxResponse);
		
		if (maxMessage <= 21) {
			
			message.add(0, 0x01);
			message.add(message.get(0) * (21-message.size()));
			
		}
		
		else if (maxMessage <= 63) {
			
			message.add(0, 0x04);
			message.add(message.get(0) * (63 - message.size()));
		}
		
		else if (maxMessage <= 121) {
			
			message.add(0, 0x02);
			message.add(message.get(0) * (121 - message.size()));
			
			if (maxResponse > 121) {
				
				System.out.println("Response may be too long to recieve.  Max possible length of " + message.size() + " bytes");
				
			}
			
		}
		
		else {
			
			System.out.println("Message too long.  Message length of " + message.size() + " bytes");
			message.clear();
			
		}
		
		return message;
		
	}
	
	public ArrayList<Integer> checkMessage(ArrayList<Integer> message) {
		
		int i = 0;
		int checkSum = 0;
		
		while (i < message.size()) {
			
			if (message.get(i) == CSAFE_dic.getByteStuffingFlag()) {
				int stuffValue = message.get(i + 1);
				message.remove(i + 1);
				message.set(i, 0xF0 | stuffValue);
			}
			
			checkSum = (int) Math.pow(checkSum, message.get(i));
			
			i++;
			
		}
		
		if (checkSum != 0) {
			
			System.out.println("Error with Checksum");
			return new ArrayList<Integer>();
		}
		
		message.remove(message.size() - 1);
		
		return message;
		
	}
	
	public HashMap<String, ArrayList> read(ArrayList<Integer> transmission) {
		
		ArrayList<Integer> message = new ArrayList<Integer>();
		boolean stopFound = false;
		
		int j = 0;
		
		int startFlag = transmission.get(1);
		
		if (startFlag == CSAFE_dic.getExtendedStartFlag()) {
			
			j = 4;
			
		}
		
		else if (startFlag == CSAFE_dic.getStandardStartFlag()) {
			
			j = 2;
			
		}
		
		else {
			
			System.out.println("No Start Flag found");
			return new HashMap<String, ArrayList>();
			
		}
		
		while (j < transmission.size()) {
			
			if (transmission.get(j) == CSAFE_dic.getStopFlag()) {
				
				stopFound = true;
				break;
				
			}
			
			message.add(transmission.get(j));
			j++;
			
		}
		
		if (!stopFound) {
			
			System.out.println("No Stop Flag found");
			return new HashMap<String, ArrayList>();
			
		}
		
		message = checkMessage(message);
		int status = message.get(0);
		message.remove(0);
		
		HashMap<String, ArrayList> response = new HashMap<String, ArrayList>();
		response.put("CSAFE_GETSTATUS_CMD", new ArrayList(Arrays.asList(status)));
		
		int k = 0;
		int wrapend = -1;
		int wrapper = 0x0;
		
		while (k < message.size()) {
			
			ArrayList result = new ArrayList<>();
			
			int msgCmd = message.get(k);
			
			if (k <= wrapend) {
				
				msgCmd = wrapper | msgCmd;
				
			}
			
			RespDicElement msgProp = CSAFE_dic.getResp().get(msgCmd);
			
			k++;
			
			int byteCount = message.get(k);
			
			k++;
			
			if (msgProp.getCmdName() == "CSAFE_SETUSERCFG1_CMD") {
				
				wrapper = message.get(k - 2) << 8;
				wrapend = k + byteCount - 1;
				
				if (byteCount != 0) {
					
					msgCmd = wrapper | message.get(k);
					msgProp = CSAFE_dic.getResp().get(msgCmd);
					k++;
					
					byteCount = message.get(k);
					k++;
					
				}
				
			}
			
			if (msgProp.getCmdName() == "CSAFE_GETCAPS_CMD") {
				
				msgProp.setBytes(new ArrayList<Integer>(Arrays.asList(byteCount)));
				
			}
			
			if (msgProp.getCmdName() == "CSAFE_GETID_CMD") {
				
				msgProp.setBytes(new ArrayList<Integer>(Arrays.asList((-byteCount))));
				
			}
			
			int tempSum = 0;
			
			for (int i : msgProp.getBytes()) {
				
				tempSum += i;
				
			}
			
			if (Math.abs(tempSum) != 0 && byteCount != Math.abs(tempSum)) {
				
				System.out.println("Warning! Byte Count is an unexpected length");
				
			}
			
			for (int numBytes : msgProp.getBytes()) {
				
				ArrayList<Integer> rawBytes = new ArrayList<Integer>();
				
				for (int i = k; i < k + Math.abs(tempSum) + k; i++) {
					
					rawBytes.set(i, msgProp.getBytes().get(i));
					
				}
				
				
				
				if (numBytes >= 0) {
					int value = 0;
					value = bytesToInteger(rawBytes);
					result.add(value);
				}
				
				else {
					String value = "";
					value = bytesToAscii(rawBytes);
					result.add(value);
				}
				k += Math.abs(numBytes);
			}
			response.put(msgProp.getCmdName(), result);
		}
		
		return response;
		
	}
}
