package jp.mydns.aonas.aonasnet.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Getリクエストを送信する処理。
 * execute()を実行したタイミングで通信が行われる。
 */
public class AsyncGet extends AsyncHttpRequest
{
    AsyncGet(HttpRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected String doAccess(String urlString) {
        HttpURLConnection connection = null;
        String response= "";
        try{
            URL url = new URL(urlString + "?" + param);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);

            connection.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            connection.connect();
            Scanner inStream = new Scanner(connection.getInputStream());

            while(inStream.hasNextLine()) {
                response += (inStream.nextLine());
                if(inStream.hasNextLine())
                    response += System.getProperty("line.separator");
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
            return "MalformedURLException[url:"+urlString+"]";
        }catch(IOException e){
            e.printStackTrace();
            return "IOException";
        }catch (Exception e){
            e.printStackTrace();
            return "Exception";
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }

        return response;
    }

}
