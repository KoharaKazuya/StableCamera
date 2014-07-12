package net.sourceforge.opencamera.temp;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class GyroSensor {

	protected SensorManager sensor = null;
	protected List<Sensor> sensorList =null;
	protected SensorEventListener sensorListener = null;

	//各方向への加速度
	protected double gyroX;
	protected double gyroY;
	protected double gyroZ;

	public GyroSensor(Context context) {
		this.sensor = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		this.sensorList = this.sensor.getSensorList(Sensor.TYPE_GYROSCOPE);
		this.sensorListener = new SensorEventListener() {

			//センサ値が変化した際の処理
			public void onSensorChanged(SensorEvent event) {
				gyroX = event.values[0];
				gyroY = event.values[1];
				gyroZ = event.values[2];
				performWhenSensorChanged();
			}

			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		};
	}

	public double getGyroX() {
		return this.gyroX;
	}

	public double getGyroY() {
		return this.gyroY;
	}

	public double getGyroZ() {
		return this.gyroZ;
	}

	public void setListener() {
		if (this.sensorList.size() > 0) {
			this.sensor.registerListener(this.sensorListener, this.sensorList.get(0), SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	public void unsetListener() {
		if (this.sensorList.size() > 0) {
			this.sensor.unregisterListener(this.sensorListener, this.sensorList.get(0));
		}
	}

	//基本的にオーバーライドして使う
	public void performWhenSensorChanged(){}


}
