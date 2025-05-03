package API;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class API_Functions {
	
	
	 public static List<CountryCode> loadFromJsonToCountryList(String filePath) {
	        Gson gson = new Gson();
	        try (Reader reader = new FileReader(filePath)) {
	            java.lang.reflect.Type listType = new TypeToken<List<CountryCode>>() {}.getType();
	            return gson.fromJson(reader, listType);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return new ArrayList<>();
	    }
}
