package com.acadgild.webservicedemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ssm2349 on 6/11/16.
 */
public class WebServiceCall {
    URI getUri;

    public String getListOfTopTagsInJSONFormat() {
        StringBuffer dataFromServer = new StringBuffer();
        try {
            getUri = new URI("http://ws.audioscrobbler.com/2.0/?method=album.gettoptags&artist=radiohead&album=the%20bends&api_key=4ba745c463400faf6171c2378a96aeaa&format=json");
            HttpGet httpGet = new HttpGet(getUri);
            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                byte data[] = new byte[256];
                while (inputStream.read(data) > 0) {
                    dataFromServer.append(new String(data));
                }
                inputStream.close();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return processJSONData(dataFromServer.toString());
    }


    private String processJSONData(String data) {
        StringBuffer playListBuilder = new StringBuffer();
        try {
            JSONObject jsonData = new JSONObject(data);
            JSONObject topTags = jsonData.getJSONObject("toptags");
            JSONArray playList = topTags.getJSONArray("tag");

            for (int i=0; i < playList.length(); i++) {
                JSONObject item=playList.getJSONObject(i);
                playListBuilder.append("\n\nName: "+item.getString("name")+"  Count:"+item.getString("count"));
                playListBuilder.append("\nURI: "+item.getString("url"));
            }
            return playListBuilder.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Invalid data";
    }


    public String getListOfTopTagsInXMLFormat() {
        StringBuffer dataFromServer = new StringBuffer();
        try {
            getUri = new URI("http://ws.audioscrobbler.com/2.0/?method=album.gettoptags&artist=radiohead&album=the%20bends&api_key=4ba745c463400faf6171c2378a96aeaa&format=xml");
            HttpGet httpGet = new HttpGet(getUri);
            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                XmlPullParser pullParser= XmlPullParserFactory.newInstance().newPullParser();
                pullParser.setInput(inputStream,null);
                int eventType=pullParser.getEventType();
                while (eventType!=XmlPullParser.END_DOCUMENT){
                    String tagName=pullParser.getName();
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            dataFromServer.append("\n\n[ "+tagName+" ");
                        break;
                    case XmlPullParser.END_TAG:
                        dataFromServer.append("  "+tagName+" ]");
                        break;
                    case XmlPullParser.TEXT:
                        dataFromServer.append(pullParser.getText());
                        break;

                    }
                    eventType=pullParser.next();
                }

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return dataFromServer.toString();
    }
}
