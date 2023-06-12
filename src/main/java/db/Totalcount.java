package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class Totalcount {
		public int total() throws IOException, ParseException  {
			
			int start = 1;
			int end = 5;
			
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
			urlBuilder.append("/" + URLEncoder.encode("77576f48446475713738596e696a61","UTF-8") );
			urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
			urlBuilder.append("/" + start);
			urlBuilder.append("/" + end);
			
			URL url = new URL(urlBuilder.toString()); 
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
			sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
		
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
			JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
			JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
			
			int totalc = ((Long)TbPublicWifiInfo.get("list_total_count")).intValue();
			
			return totalc;
		}
	}
