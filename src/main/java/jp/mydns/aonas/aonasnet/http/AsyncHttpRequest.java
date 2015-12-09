package jp.mydns.aonas.aonasnet.http;

import android.os.AsyncTask;

/**
 * GETリクエストとPOSTリクエストに共通する部分を管理するクラス。
 * ・HttpRequestBuilderから値の取得
 * ・Callbackの処理
 * ・パラメータの作成
 */
abstract public class AsyncHttpRequest extends AsyncTask<String, Integer, String>
{
    private final AsyncCallback asyncCallback;
    private final String url;
    protected final String encoding;
    protected final String param;

    abstract protected String doAccess(String url);

    @Override
    protected String doInBackground(String... strs)
    {
        return doAccess(url);
    }

    @Override
    protected void onPreExecute()
    {
        if(asyncCallback != null)
        {
            asyncCallback.onPreExecute();
        }
    }
    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        if(asyncCallback != null)
        {
            asyncCallback.onProgressUpdate(progress);
        }
    }
    @Override
    protected void onPostExecute(String res)
    {
        if(asyncCallback != null)
        {
            asyncCallback.onPostExecute(res);
        }
    }
    @Override
    protected void onCancelled()
    {
        if(asyncCallback != null)
        {
            asyncCallback.onCancelled();
        }
    }

    protected String makeParams(HttpParameter[] params){
        String p = "";
        if(params != null){
            for(int i = 0;i < params.length;i++){
                p += params[i].key + "=" + params[i].value;
                if(i+1 != params.length){
                    p += "&";
                }
            }
        }
        return p;
    }

    AsyncHttpRequest(HttpRequestBuilder builder)
    {
        this.url = builder.getUrl();
        this.asyncCallback = builder.getAsyncCallBack();
        this.encoding = builder.getEncoding();
        this.param = makeParams(builder.getParams());
    }

}

