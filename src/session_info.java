import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class session_info {
	private int id;
	private List<purchase> purchases;

	public int getbid() {
		return id;
	}
	public List<purchase> getPurchase() {
		return purchases;
	}
}