package com.example.seohyun.ohohoh;

import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by SeoHyun on 15. 11. 24..
 */
interface CompleteRequest{
    public void complete(String a, String b);
}
public class SHRequest extends AsyncTask<CompleteRequest, Void, Void> {
    public String weather;
    public String temperature;
    @Override
    protected Void doInBackground(CompleteRequest... params) {
        String response = this.GET("http://weather.yahooapis.com/forecastrss?w=1132599");
        InputStream is = new ByteArrayInputStream(response.getBytes());
        Pair data = getData(is);

        for( CompleteRequest comp: params){
            if( comp != null && comp.getClass() == GameActivity.class) {
                comp.complete(data.first.toString(), data.second.toString());
            }
        }
        return null;
    }
    public String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public Pair<String, String> getData(InputStream in){

        //저장 객체를 생성


        try {
            //XmlPullParser를 사용하기 위해서
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            //네임스페이스 사용여부
            factory.setNamespaceAware(true);
            //xml문서를 이벤트를 이용해서 데이터를 추출해주는 객체
            XmlPullParser xpp = factory.newPullParser();

            //XmlPullParser xml데이터를 저장
            xpp.setInput(in, "euc-kr");

            //이벤트 저장할 변수선언
            int eventType = xpp.getEventType();

            boolean isItemTag = false; // <item> .영역에 인지 여부 체크
            String tagName = "";
            String title = "";
            //xml의 데이터의 끝까지 돌면서 원하는 데이터를  얻어옴
            while(eventType != XmlPullParser.END_DOCUMENT){
                if( eventType == XmlPullParser.START_TAG ){
                    tagName = xpp.getName();
                    if(tagName.equals("condition")){
                        weather = xpp.getAttributeValue(0);
                        temperature = xpp.getAttributeValue(2);
                        return new Pair(weather, temperature);
                    }
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {

            Log.e("ALARMAPP", "예외발생 :"+e.getMessage());
        }

        return null;
        //return newsTitleVector;
    }




}
