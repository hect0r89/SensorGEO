package geo.master.ejerciciosensoresgeo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

public class SensorDetail extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor sensor;
    TextView txtNombre;
    TextView txtVersion;
    TextView txtFabricante;
    TextView txtValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_detail);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(type);

        txtNombre = (TextView) findViewById(R.id.txt_nombre);
        txtVersion = (TextView) findViewById(R.id.txt_version);
        txtFabricante = (TextView) findViewById(R.id.txt_fabricante);
        txtValor = (TextView) findViewById(R.id.txt_valor);

        txtNombre.setText(sensor.getName());
        txtVersion.setText(String.valueOf(sensor.getVersion()));
        txtFabricante.setText(sensor.getVendor());

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        txtValor.setText(Arrays.toString(sensorEvent.values));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
