package com.example.bbs_llsif;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by lijun on 2017/11/21.
 */

public class Poster {
    public static String post(String url, String body, Map<String,String> header) {
        System.out.println("HTTP Request: "+ body);
        String result = "";
        try {
            URL url_ = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) url_.openConnection();
            con.setReadTimeout(6000);
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> entry:header.entrySet()){
                System.out.println("HTTP Add Header: "+ entry.getKey() + " - " + entry.getValue());
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
            OutputStream out = con.getOutputStream();
            out.write(body.getBytes());
            out.flush();
            out.close();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null) {
                result = line;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("HTTP Response: " + result);
        return result;
    }
}