import java.util.Arrays;
import java.util.List;
import java.util.*;

import com.google.gson.annotations.SerializedName;

public class analytics {

	private String manufacturer;
	private session_infos usage_statistics;

	public String getManf() {
		return manufacturer;
	}
	public session_infos getsis() {
		return usage_statistics;
	}
}