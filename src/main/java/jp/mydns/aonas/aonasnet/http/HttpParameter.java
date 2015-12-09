package jp.mydns.aonas.aonasnet.http;

/**
 * パラメータ格納用のクラス
 * そのうちなくなるかもしれない
 */
final class HttpParameter {
    public final String key;
    public final String value;
    public HttpParameter(String key, String value){
        this.key = key;
        this.value = value;
    }
}
