package row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CSAFE_dic {
	
	public static void main(String[] args) {
		
		// Unique Flags
		int Extended_Frame_Start_Flag = 0xF0;
		int Standard_FrameStart_Flag = 0xF1;
		int Stop_Frame_Flag = 0xF2;
		int Byte_Stuffing_Flag = 0xF3;
		
		// Initializing of Command and Response HashMaps
		HashMap<String, CmdDicElement> cmds = new HashMap<String, CmdDicElement>();
		HashMap<Integer, RespDicElement> resp = new HashMap<Integer, RespDicElement>();
		
		// Commands
		
		// Short
		cmds.put("CSAFE_GETSTATUS_CMD", new CmdDicElement(0x80));
		cmds.put("CSAFE_RESET_CMD", new CmdDicElement(0x81));
		cmds.put("CSAFE_GOIDLE_CMD", new CmdDicElement(0x82));
		cmds.put("CSAFE_GOHAVEID_CMD", new CmdDicElement(0x83));
		cmds.put("CSAFE_GOINUSE_CMD", new CmdDicElement(0x85));
		cmds.put("CSAFE_GOFINISHED_CMD", new CmdDicElement(0x86));
		cmds.put("CSAFE_GOREADY_CMD", new CmdDicElement(0x87));
		cmds.put("CSAFE_BADID_CMD", new CmdDicElement(0x88));
		cmds.put("CSAFE_GETVERSION_CMD", new CmdDicElement(0x91));
		cmds.put("CSAFE_GETID_CMD", new CmdDicElement(0x92));
		cmds.put("CSAFE_GETUNITS_CMD", new CmdDicElement(0x93));
		cmds.put("CSAFE_GETSERIAL_CMD", new CmdDicElement(0x94));
		cmds.put("CSAFE_GETODOMETER_CMD", new CmdDicElement(0x9B));
		cmds.put("CSAFE_GETERRORCODE_CMD", new CmdDicElement(0x9C));
		cmds.put("CSAFE_GETWORK_CMD", new CmdDicElement(0xA0));
		cmds.put("CSAFE_GETHORIZONTAL_CMD", new CmdDicElement(0xA1));
		cmds.put("CSAFE_GETCALORIES_CMD", new CmdDicElement(0xA3));
		cmds.put("CSAFE_GETPROGRAM_CMD", new CmdDicElement(0xA4));
		cmds.put("CSAFE_GETPACE_CMD", new CmdDicElement(0xA6));
		cmds.put("CSAFE_GETCADENCE_CMD", new CmdDicElement(0xA7));
		cmds.put("CSAFE_GETUSERINFO_CMD", new CmdDicElement(0xAB));
		cmds.put("CSAFE_GETHRCUR_CMD", new CmdDicElement(0xB0));
		cmds.put("CSAFE_GETPOWER_CMD", new CmdDicElement(0xB4));
		// PM3 Specific
		cmds.put("CSAFE_PM_GET_WORKOUTTYPE", new CmdDicElement(0x89, 0x1A));
		cmds.put("CSAFE_PM_GET_DRAGFACTOR", new CmdDicElement(0xC1, 0x1A));
		cmds.put("CSAFE_PM_GET_STROKESTATE", new CmdDicElement(0xBF, 0x1A));
		cmds.put("CSAFE_PM_GET_WORKTIME", new CmdDicElement(0xA0, 0x1A));
		cmds.put("CSAFE_PM_GET_WORKDISTANCE", new CmdDicElement(0xA3, 0x1A));
		cmds.put("CSAFE_PM_GET_ERRORVALUE", new CmdDicElement(0xC9, 0x1A));
		cmds.put("CSAFE_PM_GET_WORKOUTSTATE", new CmdDicElement(0x8D, 0x1A));
		cmds.put("CSAFE_PM_GET_WORKOUTINTERVALCOUNT", new CmdDicElement(0x9F, 0x1A));
		cmds.put("CSAFE_PM_GET_INTERVALTYPE", new CmdDicElement(0x8E, 0x1A));
		cmds.put("CSAFE_PM_GET_RESTTIME", new CmdDicElement(0xCF, 0x1A));
		
		// Long
		cmds.put("CSAFE_AUTOUPLOAD_CMD", new CmdDicElement(0x01, new ArrayList<Integer>(Arrays.asList(1))));
		cmds.put("CSAFE_IDDIGITS_CMD", new CmdDicElement(0x10, new ArrayList<Integer>(Arrays.asList(1))));
		cmds.put("CSAFE_SETTIME_CMD", new CmdDicElement(0x11, new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		cmds.put("CSAFE_SETDATE_CMD", new CmdDicElement(0x12, new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		cmds.put("CSAFE_SETTIMEOUT_CMD", new CmdDicElement(0x13, new ArrayList<Integer>(Arrays.asList(1))));
		cmds.put("CSAFE_SETUSERCFG1_CMD", new CmdDicElement(0x1A, new ArrayList<Integer>(Arrays.asList(0))));
		cmds.put("CSAFE_SETTWORK_CMD", new CmdDicElement(0x20, new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		cmds.put("CSAFE_SETWORK_CMD", new CmdDicElement(0x21, new ArrayList<Integer>(Arrays.asList(2, 1))));
		cmds.put("CSAFE_SETCALORIES_CMD", new CmdDicElement(0x23, new ArrayList<Integer>(Arrays.asList(2))));
		cmds.put("CSAFE_SETPROGRAM_CMD", new CmdDicElement(0x24, new ArrayList<Integer>(Arrays.asList(1, 1))));
		cmds.put("CSAFE_SETPOWER_CMD", new CmdDicElement(0x34, new ArrayList<Integer>(Arrays.asList(2, 1))));
		cmds.put("CSAFE_GETCAPS_CMD", new CmdDicElement(0x70, new ArrayList<Integer>(Arrays.asList(1))));
		// PM3 Specific
		cmds.put("CSAFE_PM_SET_SPLITDURATION", new CmdDicElement(0x05, new ArrayList<Integer>(Arrays.asList(1, 4)), 0x1A));
		cmds.put("CSAFE_PM_SET_FORCEPLOTDATA", new CmdDicElement(0x6B, new ArrayList<Integer>(Arrays.asList(1)), 0x1A));
		cmds.put("CSAFE_PM_SET_SCREENERRORMODE", new CmdDicElement(0x27, new ArrayList<Integer>(Arrays.asList(1)), 0x1A));
		cmds.put("CSAFE_PM_SET_HEARTBEATDATA", new CmdDicElement(0x6C, new ArrayList<Integer>(Arrays.asList(1)), 0x1A));
	
		
		// Responses to Commands
		
		// Short
		resp.put(0x80, new RespDicElement("CSAFE_GETSTATUS_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x81, new RespDicElement("CSAFE_RESET_CMD", new ArrayList<Integer>(Arrays.asList(0))));
	}
	
}

