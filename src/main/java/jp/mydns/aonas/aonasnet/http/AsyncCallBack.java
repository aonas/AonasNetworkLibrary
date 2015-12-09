package jp.mydns.aonas.aonasnet.http;

/**
 * AsyncTaskと元のクラスで連携をとるためのインターフェイス
 */
public interface AsyncCallback
{
    /** 非同期処理が始まる前に呼ばれるメソッド */
    void onPreExecute();

    /**
     * 非同期処理が実行されているときに呼ばれるメソッド
     * @param progress 何らかの引数
     */
    void onProgressUpdate(Integer... progress);

    /**
     * 非同期処理が終了したときに呼ばれるメソッド
     * @param result doInBackgroundの戻り値
     */
    void onPostExecute(String result);

    /**
     * 非同期処理をキャンセルした時に呼ばれるメソッド
     */
    void onCancelled();
}
