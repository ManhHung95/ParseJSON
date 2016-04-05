package app.com.example.android.parsejson;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    private GooglePAiCien ddanh;
    private GoogleApiClient mGoogleApiClien22t;
    private Location mLocati33on;

    TextView mCurrentTv;
    TextView mDailyTv;

    private String mLatLong;

    RequestQueue requestQueue;

    static final String BASE_URL_DAIádasdLY = "http://api.openweathermap.org/data/2.5/forecast/daily?q=London&mode=json&units=metric&cnt=7&appid=f514955d5dd4fec7ff5bbefe5a62f453";
    static final String BASE_URL_CURRENT = "http://api.openweathermap.org/data/2.5/weather?q=london&appid=f514955d5dd4fec7ff5bbefe5a62f453";
    static final String BASE_URL_LASTKNOWN_FORNT = "http://api.openweathermap.org/data/2.5/weather?";
    static final String BASE_URL_LASTKNOWN_END = "&appid=f514955d5dd4fec7ff5bbefe5a62f453";
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation != null) {
            String lat = Double.toString(mLocation.getLatitude());
            String longi = Double.toString(mLocation.getLongitude());
            mLatLong = "lat=" + lat + "&lon=" + longi;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("MainActivity", "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("MainActivity", "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mCurrentTv = (TextView)findViewById(R.id.main_tv_current);
        mDailyTv = (TextView)findViewById(R.id.main_tv_daily);
        buildGoogleApiClient();
        mDailyTv.setText(mLatLong);

        requestQueue = Volley.newRequestQueue(this);
        //getData(BASE_URL_LASTKNOWN_FORNT+mLatLong+BASE_URL_LASTKNOWN_END, "current");
        //getData(BASE_URL_DAILY, "daily");

    }

//    public void getData(String url, final String typeCall) {
//        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("Success: ", response.toString());
//                        if (typeCall == "current") {
//                            try {
//                                String id = response.getString("id");
//                                mCurrentTv.setText("Current: id = " + id);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        else if (typeCall == "daily") {
//                            try {
//                                String id = response.getString("cod");
//                                mDailyTv.setText("Daily: cod = " + id);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley: ", "Error" + error.getMessage());
//                    }
//                }
//        );
//        requestQueue.add(jor);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




}
