package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddAddress;
import pojo.Loc;

public class TestDataBuild {
	
	public AddAddress addPlacePayload(String name, String language, String address) {
		AddAddress p = new AddAddress();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setName(name);
		p.setLanguage(language);
		p.setPhone_number("09867384324");
		p.setWebsite("google.com");

		List<String> mylist = new ArrayList<String>();
		mylist.add("Shoes");
		mylist.add("store");
		p.setTypes(mylist);

		Loc l = new Loc();
		l.setLat("50.89");
		l.setLng("90.89");

		p.setLocation(l);
		
		return p;
	}
	
	
	public String deletePayload (String placeId) {
		return "{\r\n" + 
				"    \"place_id\" : \"" + placeId + "\"\r\n" + 
				"}";
	}

}
