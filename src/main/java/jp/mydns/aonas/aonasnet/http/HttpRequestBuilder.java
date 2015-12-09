package jp.mydns.aonas.aonasnet.http;

import java.util.ArrayList;
import java.util.List;

/**
 * HttpRequestを使用するためのインスタンスを作成するビルダークラス
 * ・コンストラクタで接続先のURLを指定する。
 * ・build(int type)を実行すると、typeがHttpRequestBuilder.HTTP_GETならAsyncGet、
 * 　HttpRequestBuilder.HTTP_POSTならAsyncPOSTを取得することができる。
 * ・取得したインスタンスでexecute()を実行したタイミングで通信が行われる。
 * また、メソッドチェーンで以下の要素を追加することができる。
 * ・asyncCallback(AsyncCallback ac)
 *   - コールバッククラスの追加
 * ・addParam(String key, String value)
 *   - パラメータの追加（nullはダメ、keyに関してはemptyでもダメ）
 * ・encoding(String enc)
 *   - まだ作ってないです。
 */
public class HttpRequestBuilder
{
    private List<HttpParameter> params = new ArrayList<>();

    private final String url;
    private AsyncCallback asyncCallback;
    private String encoding = "UTF-8";

    public static final int HTTP_GET = 0;
    public static final int HTTP_POST = 1;
    public AsyncHttpRequest build(int requestType){
        switch (requestType){
            case HTTP_GET:
                return new AsyncGet(this);
            case HTTP_POST:
                return new AsyncPost(this);
            default:
                throw new IllegalArgumentException();
        }
    }

    public HttpRequestBuilder(String url)
    {
        if(url == null || url.isEmpty())
        {
            throw new NullPointerException();
        }
        this.url = url;
    }

    /** 今後実装予定 */
    HttpRequestBuilder encoding(String encoding)
    {
        if(encoding == null || encoding.isEmpty())
        {
            throw new NullPointerException();
        }
        this.encoding = encoding;
        return this;
    }

    public HttpRequestBuilder asyncCallback(AsyncCallback asyncCallback)
    {
        this.asyncCallback = asyncCallback;
        return this;
    }

    public final String getUrl()
    {
        return url;
    }

    public final String getEncoding()
    {
        return encoding;
    }

    public final AsyncCallback getAsyncCallBack()
    {
        return asyncCallback;
    }

    public HttpRequestBuilder addParam(String name, String value)
    {
        if(name == null || name.isEmpty() || value == null)
        {
            throw new NullPointerException();
        }
        params.add(new HttpParameter(name, value));
        return this;
    }

    final HttpParameter[] getParams()
    {
        if(params != null && !params.isEmpty())
        {
            return params.toArray(new HttpParameter[]{});
        }
        return null;
    }
}
