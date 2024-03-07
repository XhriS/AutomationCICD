package christianacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//Below will read Json file and convert it to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//christianacademy//data//PurchaseOrder.json"), 
				StandardCharsets.UTF_8);
	
	//Convert String to HashMap (Jackson Databind - DOWNLOADED FROM MAVEN REPOSITORY - UTILITY)
		ObjectMapper mapper = new ObjectMapper();
		//All HashMaps from the JSON file (Indexes) will be put in a List
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>() {	
		});
		//{map, map}
		return data;
	}
}
