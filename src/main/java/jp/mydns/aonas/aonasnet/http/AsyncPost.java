package jp.mydns.aonas.aonasnet.http;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * POSTリクエストを送信する処理。
 * execute()を実行したタイミングで通信が行われる。
 */
public class AsyncPost extends AsyncHttpRequest
{
    AsyncPost(HttpRequestBuilder builder)
    {
        super(builder);
    }

    @Override
    protected String doAccess(String urlString) {
        HttpURLConnection connection = null;
        String response= "";
        try{
            URL url=new URL(urlString);

            connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");


            connection.setFixedLengthStreamingMode(param.getBytes().length);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.close();

            Scanner inStream = new Scanner(connection.getInputStream());

            while(inStream.hasNextLine()) {
                response += (inStream.nextLine());
                if(inStream.hasNextLine())
                    response += System.getProperty("line.separator");
            }

            return response;

        }catch(MalformedURLException e){
            e.printStackTrace();
            return "MalformedURLException[url:"+urlString+"]";
        }catch(IOException e){
            e.printStackTrace();
            return "IOException";
        }catch (Exception e){
            e.printStackTrace();
            return "Exception";
        }finally{
            if(connection != null){
                connection.disconnect();
            }
        }
    }

}