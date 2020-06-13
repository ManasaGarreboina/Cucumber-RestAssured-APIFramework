package Resources;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqSpe;
	public RequestSpecification requestSpecification() throws IOException

	{
		if(reqSpe==null){

			PrintStream log= new PrintStream(new FileOutputStream("LogFile.txt"));
			reqSpe =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqSpe;
		}
		return reqSpe;
	}
	public static String getGlobalValue(String key) throws IOException{
		Properties pro = new Properties();
		FileInputStream fin= new FileInputStream("C:\\Users\\user\\workspace\\API_Framework\\src\\test\\java\\Resources\\global.properties");
		pro.load(fin);

		return pro.getProperty(key);

	}
	public String getJsonPath(Response response,String key)
	{
		String status_resp=response.asString();
		JsonPath js = new JsonPath(status_resp);
		
		return js.get(key).toString();
	}
}
