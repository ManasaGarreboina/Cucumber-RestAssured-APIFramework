package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(int Accuracy, String name, String language, String address, String Phone_number, String Website){
		AddPlace place =new AddPlace();
		place.setAccuracy(Accuracy);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number(Phone_number);
		place.setWebsite(Website);
		place.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("she spark");
		myList.add("ship");

		place.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		place.setLocation(l);

		return place;
	}
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
