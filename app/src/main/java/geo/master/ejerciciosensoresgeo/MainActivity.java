package geo.master.ejerciciosensoresgeo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private SensorManager sensorManager;
    private List<Sensor> sensors;
    String selectedSensor;
    int selectedSensorType = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> sensorList = new ArrayList<>();
        final ArrayList<Integer> sensorListTypes = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.sensors_spinner);
        Button button = (Button) findViewById(R.id.btn_detail);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: sensors){
            sensorList.add(sensor.getName());
            sensorListTypes.add(sensor.getType());
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,sensorList);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSensor = adapterView.getItemAtPosition(i).toString();
                selectedSensorType = sensorListTypes.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( selectedSensorType != -1) {
                    Intent i = new Intent(getBaseContext(), SensorDetail.class);
                    i.putExtra("type", selectedSensorType);
                    startActivity(i);
                }
            }
        });


    }


}
