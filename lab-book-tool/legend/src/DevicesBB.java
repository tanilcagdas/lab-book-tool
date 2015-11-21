
import java.util.LinkedList;
import java.util.List;

import com.lab.controller.IndexBean;
import com.lab.model.Device;
import com.lab.server.AdminIF;
import com.lab.server.ItemAdminImpl;

public class DevicesBB {
	private List<Device> dataForDevices = new LinkedList();
	AdminIF admin = new ItemAdminImpl();

	public List<Device> getDataForDevices() {
		if(dataForDevices.size()==0){
			IndexBean indexBB = new IndexBean();
			
			dataForDevices= admin.search();
			//DBsearch.search("devices",indexBB.getLoginUserName(), "");
		}
		return dataForDevices;
	}

	public void setDataForDevices(List<Device> dataForDevices) {
		dataForDevices = dataForDevices;
	}

}
