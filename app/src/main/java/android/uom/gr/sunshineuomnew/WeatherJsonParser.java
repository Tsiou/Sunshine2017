package android.uom.gr.sunshineuomnew;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class WeatherJsonParser {

    public static List<String> getWeatherFromJson(String jsonString, int numDays){
        List<String> results = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray weatherList = jsonObject.getJSONArray("list");

            for(int i =0; i< weatherList.length(); i++){
                String day = "day "+i;
                String condition, low, high;

                JSONObject dayForecast = weatherList.getJSONObject(i);
                JSONArray weather = dayForecast.getJSONArray("weather");
                condition = weather.getJSONObject(0).getString("main");

                JSONObject tempObject = dayForecast.getJSONObject("temp");
                low = tempObject.getString("min");
                high = tempObject.getString("max");

                String res = day + " - "+condition+" - "+ low+" / "+high;
                results.add(res);
            }

        } catch (JSONException e) {
            Log.e("WeatherJsonParser", e.getMessage());
        }

        return results;
    }

}
