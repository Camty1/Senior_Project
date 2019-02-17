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
		resp.put(0x82, new RespDicElement("CSAFE_GOIDLE_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x83, new RespDicElement("CSAFE_GOHAVEID_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x85, new RespDicElement("CSAFE_GOINUSE_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x86, new RespDicElement("CSAFE_GOFINISHED_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x87, new RespDicElement("CSAFE_GOREADY_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x88, new RespDicElement("CSAFE_BADID_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x91, new RespDicElement("CSAFE_GETVERSION_CMD", new ArrayList<Integer>(Arrays.asList(1, 1, 1, 2, 2))));
		resp.put(0x92, new RespDicElement("CSAFE_GETID_CMD", new ArrayList<Integer>(Arrays.asList(-5))));
		resp.put(0x93, new RespDicElement("CSAFE_GETUNITS_CMD", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x94, new RespDicElement("CSAFE_GETSERIAL_CMD", new ArrayList<Integer>(Arrays.asList(-9))));
		resp.put(0x9B, new RespDicElement("CSAFE_GETODOMETER_CMD", new ArrayList<Integer>(Arrays.asList(4, 1))));
		resp.put(0x9C, new RespDicElement("CSAFE_GETERRORCODE_CMD", new ArrayList<Integer>(Arrays.asList(3))));
		resp.put(0xA0, new RespDicElement("CSAFE_GETWORK_CMD", new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		resp.put(0xA1, new RespDicElement("CSAFE_GETHORIZONTAL_CMD", new ArrayList<Integer>(Arrays.asList(2, 1))));
		resp.put(0xA3, new RespDicElement("CSAFE_GETCALORIES_CMD", new ArrayList<Integer>(Arrays.asList(2))));
		resp.put(0xA4, new RespDicElement("CSAFE_GETPROGRAM_CMD", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0xA6, new RespDicElement("CSAFE_GETPACE_CMD", new ArrayList<Integer>(Arrays.asList(2, 1))));
		resp.put(0xA7, new RespDicElement("CSAFE_GETCADANCE_CMD", new ArrayList<Integer>(Arrays.asList(2, 1))));
		resp.put(0xAB, new RespDicElement("CSAFE_GETUSERINFO_CMD", new ArrayList<Integer>(Arrays.asList(2, 1, 1, 1))));
		resp.put(0xB0, new RespDicElement("CSAFE_GETHRCUR_CMD", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0xB4, new RespDicElement("CSAFE_GETPOWER_CMD", new ArrayList<Integer>(Arrays.asList(2, 1))));
		// PM3 Specific
		resp.put(0x1A89, new RespDicElement("CSAFE_PM_GET_WORKOUTTYPE", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1AC1, new RespDicElement("CSAFE_PM_GET_DRAGFACTOR", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1ABF, new RespDicElement("CSAFE_PM_GET_STROKESTATE", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1AA0, new RespDicElement("CSAFE_PM_GET_WORKTIME", new ArrayList<Integer>(Arrays.asList(4, 1))));
		resp.put(0x1AA3, new RespDicElement("CSAFE_PM_GET_WORKDISTANCE", new ArrayList<Integer>(Arrays.asList(4, 1))));
		resp.put(0x1AC9, new RespDicElement("CSAFE_PM_GET_ERRORVALUE", new ArrayList<Integer>(Arrays.asList(2))));
		resp.put(0x1A8D, new RespDicElement("CSAFE_PM_GET_WORKOUTSTATE", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1A9F, new RespDicElement("CSAFE_PM_GET_WORKOUTINTERVALCOUNT", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1A8E, new RespDicElement("CSAFE_PM_GET_INTERVALTYPE", new ArrayList<Integer>(Arrays.asList(1))));
		resp.put(0x1ACF, new RespDicElement("CSAFE_PM_GET_RESTTIME", new ArrayList<Integer>(Arrays.asList(2))));
		
		// Long
		resp.put(0x01, new RespDicElement("CSAFE_AUTOUPLOAD_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x10, new RespDicElement("CSAFE_IDDIGITS_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x11, new RespDicElement("CSAFE_SETTIME_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x12, new RespDicElement("CSAFE_SETDATE_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x13, new RespDicElement("CSAFE_SETTIMEOUT_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x1A, new RespDicElement("CSAFE_SETUSERCFG1_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x20, new RespDicElement("CSAFE_SETTWORK_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x21, new RespDicElement("CSAFE_SETHORIZONTAL_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x23, new RespDicElement("CSAFE_SETCALORIES_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x24, new RespDicElement("CSAFE_SETPROGRAM_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x34, new RespDicElement("CSAFE_SETPOWER_CMD", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x70, new RespDicElement("CSAFE_GETCAPS_CMD", new ArrayList<Integer>(Arrays.asList(11))));
		// PM3 Specific
		resp.put(0x1A05, new RespDicElement("CSAFE_PM_SET_SPLITDURATION", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x1A6B, new RespDicElement("CSAFE_PM_GET_FORCEPLOTDATA", new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
		resp.put(0x1A27, new RespDicElement("CSAFE_PM_SET_SCREENERRORMODE", new ArrayList<Integer>(Arrays.asList(0))));
		resp.put(0x1A6C, new RespDicElement("CSAFE_PM_GET_HEARBEATDATA", new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
		
	}
	
}

