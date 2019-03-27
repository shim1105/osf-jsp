package com.osf.test.test;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;

// 네이버 기계번역 (Papago SMT) API 예제
public class Transper {

    public static void main(String[] args) {
        String clientId = "ATFesbCdyEM6Vc8LcL7f";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "JCWbfDyTxK";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("이게 사실은 아웃풋에 기본이다 ,", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            Gson gson=new Gson();
            Map<String,Map<String,Map<String,String>>>result = gson.fromJson(response.toString(), Map.class);
          
            System.out.println(result.get("message").get("result").get("translatedText"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}