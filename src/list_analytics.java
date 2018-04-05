// Class that stores a list of objects of type analytics
import java.util.Arrays;
import java.util.List;
import java.util.*;

import com.google.gson.annotations.SerializedName;

public class list_analytics {

	private List<analytics> list_analytics;

	public List<analytics> getAn() {
		return list_analytics;
	}
}

/*
[{"manufacturer":"Motorola",
"market_name":"Moto G4 Play",
"codename":"harpia",
"model":"Moto G Play",
"usage_statistics": {
   "session_infos":[{"building_id":17,"purchases":[{"item_id":197,"item_category_id":2,"cost":3.75}]},
                    {"building_id":18,"purchases":[{"item_id":94,"item_category_id":4,"cost":148.93}]},
                    {"building_id":17,"purchases":[{"item_id":139,"item_category_id":4,"cost":132.47}]
                     }]}},
{"manufacturer":"Samsung","market_name":"Galaxy S7 
..
}*/