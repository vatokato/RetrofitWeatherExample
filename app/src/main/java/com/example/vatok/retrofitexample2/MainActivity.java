package com.example.vatok.retrofitexample2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vatok.retrofitexample2.WeatherItem.WeatherItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//http://api.openweathermap.org/data/2.5/weather?q=Moscow&APPID=fdac0291ad48feaa00664fe96728a7ec&lang=ru&units=metric
//http://www.jsonschema2pojo.org/

//{
// "coord":{"lon":37.62,"lat":55.75},
// "weather":[{"id":800,"main":"Clear","description":"ясно","icon":"01d"}],
// "base":"stations",
// "main":{"temp":27.75,"pressure":1023,"humidity":34,"temp_min":27,"temp_max":28},
// "visibility":10000,"wind":{"speed":2,"deg":40},
// "clouds":{"all":0},"dt":1533132000,
// "sys":{"type":1,"id":7325,"message":0.0276,"country":"RU","sunrise":1533087324,"sunset":1533144898},
// "id":524901,
// "name":
// "Moscow",
// "cod":200
// }

public class MainActivity extends AppCompatActivity {
    final String APPID = "fdac0291ad48feaa00664fe96728a7ec";
    private String units;
    private String lang;

    LinearLayout listLL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.units="metric";
        this.lang="ru";

        listLL = findViewById(R.id.ll_btns);
        for (int i=0; i<listLL.getChildCount(); i++) {
            TextView btn = (TextView) listLL.getChildAt(i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) v;

                    OpenWeather.getApi().getData(tv.getText().toString().trim(), APPID, lang, units).enqueue(new Callback<WeatherItem>() {
                        @Override
                        public void onResponse(Call<WeatherItem> call, Response<WeatherItem> response) {
                            if(response.body()!=null) {
                                Toast.makeText(MainActivity.this, String.format(
                                        "Температура в %s: %s",
                                        response.body().getName(),
                                        response.body().getMain().getTemp()) ,
                                        Toast.LENGTH_SHORT
                                ).show();
                                return;
                            }
                            System.err.println(response.code()+": "+response.message());

                        }
                        @Override
                        public void onFailure(Call<WeatherItem> call, Throwable t) {
                            System.err.println(t);
                        }
                    });

                }
            });
        }




    }
}
