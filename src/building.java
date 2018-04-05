import java.util.Arrays;
import java.util.List;
import java.util.*;

import com.google.gson.annotations.SerializedName;

public class building {

	private int building_id;
	private String building_name;
	private String city;
	private String state;
	private String country;

	public int getbid() {
		return building_id;
	}
	public String getbn() {
		return building_name;
	}
	public String getcity() {
		return city;
	}
	public String getstate() {
		return state;
	}
	public String getcountry() {
		return country;
	}
}