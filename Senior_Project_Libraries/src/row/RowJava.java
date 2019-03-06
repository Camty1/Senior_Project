package row;

import org.usb4java.*;

public class RowJava {
	Context context = new Context();
	int result = LibUsb.init(context);
	{
	
		if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.", result);
	
	}
	
	int C2_VENDOR_ID = 0x17A4;
	double MIN_FRAME_GAP = .05;
	int INTERFACE = 0;
	
	Device erg = findDevice(C2_VENDOR_ID);
	
	public Device findDevice(int id) {
		
		DeviceList list = new DeviceList();
	    int result = LibUsb.getDeviceList(null, list);
	    if (result < 0) throw new LibUsbException("Unable to get device list", result);

	    try
	    {
	        // Iterate over all devices and scan for the right one
	        for (Device device: list)
	        {
	            DeviceDescriptor descriptor = new DeviceDescriptor();
	            result = LibUsb.getDeviceDescriptor(device, descriptor);
	            if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
	            if (descriptor.idVendor() == id) return device;
	        }
	    }
	    finally
	    {
	        // Ensure the allocated device list is freed
	        LibUsb.freeDeviceList(list, true);
	    }

	    // Device not found
	    return null;
	}
	
	public void init(Device erg) {
		
		String os = System.getProperty("os.name").toLowerCase();
		DeviceHandle handle = new DeviceHandle();
		int result = LibUsb.open(erg, handle);
		
		
		boolean detach = LibUsb.hasCapability(LibUsb.CAP_SUPPORTS_DETACH_KERNEL_DRIVER) 
			    && LibUsb.kernelDriverActive(handle, INTERFACE) != 0;

			// Detach the kernel driver
			if (detach)
			{
			    int result2 = LibUsb.detachKernelDriver(handle,  INTERFACE);
			    if (result2 != LibUsb.SUCCESS) throw new LibUsbException("Unable to detach kernel driver", result);
			}
		
	}
	
}