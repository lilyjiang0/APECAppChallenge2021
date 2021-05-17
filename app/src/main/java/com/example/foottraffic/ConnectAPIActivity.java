package com.example.foottraffic;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class ConnectAPIActivity {
    private static FileWriter file;
    public static String result(String venueName, String venueAddress) {
        // interface url
        String requestUrl = "https://besttime.app/api/v1/forecasts/live";
        //params
        Map params = new HashMap();
        // pri_e435dec0a2aa4b8e8b4ef42bc990f596
        // private api key
        params.put("api_key_private", "pri_e435dec0a2aa4b8e8b4ef42bc990f596");
        // venue name
        params.put("venue_name", venueName);
        // venue address
        params.put("venue_address", venueAddress);

        String s = httpRequest(requestUrl, params);
        return s;
    }

    private static String httpRequest(String requestUrl, Map params) {
        // buffer is used to receive the returned json data
        StringBuffer buffer = new StringBuffer();
        try {
            //create URL, complete the request address
            // urlencoded() is used to extract the parameters from the params
            URL url = new URL(requestUrl+"?"+ urlencoded(params));
            //open http connection
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();// connect
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.connect();

            // get the input
            InputStream inputStream = httpUrlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");// coding
            BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);


            // put the value of bufferReader inside the str
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }


            // close the bufferReader and input stream
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            // inputStream = null;

            // disconnect
            httpUrlConnection.disconnect();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        // return string
        return buffer.toString();
    }

    public static String urlencoded(Map<String, Object>data) {

        // change the parameters in map to the pattern of api_key_private=###&venue_name=###&
        StringBuilder sb = new StringBuilder();
        for(Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.json.JSONObject;
//public class ConnectAPIActivity {
//    public static void main(String venueName, String venueAddress) {
//        try {
//            ConnectAPIActivity.call_me(venueName, venueAddress);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void call_me(String venueName, String venueAddress) throws Exception {
//        URL url = new URL("https://besttime.app/api/v1/forecasts/live");
//        Map params = new LinkedHashMap<>();
//        // 'api_key_private': 'pri_50990bf1f8828f6abbf6152013113c6b',
//        //  'venue_name': 'McDonalds',
//        //  'venue_address': 'Ocean Ave, San Fransisco'
//        params.put("api_key_private", "pri_50990bf1f8828f6abbf6152013113c6b");
//        params.put("venue_name", venueName);
//        params.put("venue_address", venueAddress);
//        StringBuilder postData = new StringBuilder();
//        Map<String, Object>data = params;
//        for (Map.Entry param : data.entrySet()) {
//            if (postData.length() != 0) postData.append('&');
//            postData.append(URLEncoder.encode((String) param.getKey(), "UTF-8"));
//            postData.append('=');
//            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//        }
//        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//        conn.setDoOutput(true);
//        conn.getOutputStream().write(postDataBytes);
//        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        for (int c; (c = in.read()) >= 0;)
//            sb.append((char)c);
//        String response = sb.toString();
//        System.out.println(response);
//        JSONObject myResponse = new JSONObject(response.toString());
//        System.out.println("result after Reading JSON Response");
//        System.out.println("Analysis- "+myResponse.getString("analysis"));
//        System.out.println("Status- "+myResponse.getString("status"));
//        JSONObject form_data = myResponse.getJSONObject("form");
//        System.out.println("Venue info- "+form_data.getString("venue_info"));
//    }
//}