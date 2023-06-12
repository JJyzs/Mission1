package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class OpenApi {

	public static List<Info> depoint() throws IOException, ParseException {

		List<Info> list = new ArrayList<>();
		
	      Totalcount totalco = new Totalcount(); // 파싱하여 총 23304개의 카운트를 가져오기 위한 코드

	      int count = totalco.total();

	      int start = 1;
	      int end = 999;

	      for (int i = 0; i <= count / 1000; i++) {
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
			urlBuilder.append("/" + URLEncoder.encode("77576f48446475713738596e696a61", "UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
			urlBuilder.append("/" + start);
			urlBuilder.append("/" + (start + end));
									
			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
			JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
			JSONArray row = (JSONArray) TbPublicWifiInfo.get("row");
		

			Gson gson = new Gson();

			for (Object o : row) {
				list.add(gson.fromJson(o.toString(), Info.class));
			}
			
			start += 1000;
             
             if(i == count / 1000) {
                end = (count % 1000) -1;
             }   
	     }
	      return list;
	}
}
