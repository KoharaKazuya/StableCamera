package net.sourceforge.opencamera;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//加速度センサの値を扱うためのクラス
abstract class Accelerometer {

	protected SensorManager sensor = null;
	protected List<Sensor> sensorList = null;
	protected SensorEventListener sensorListener = null;

	//各方向への加速度 (m / s^2)
	protected double accelerationX;
	protected double accelerationY;
	protected double accelerationZ;

	//コンストラクタ
	public Accelerometer(Context context){
		this.sensor = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		this.sensorList = this.sensor.getSensorList(Sensor.TYPE_ACCELEROMETER);

		this.sensorListener = new SensorEventListener() {

			//センサが変化した際の処理
			@SuppressWarnings("deprecation")
			public void onSensorChanged(SensorEvent event) {
				accelerationX = event.values[0];
				accelerationY = event.values[1];
				accelerationZ = event.values[2];
				performWhenSensorChanged();
			}

			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		};
	}



	/**
	 * X軸方向の加速度を取得
	 */
	public double getX() {
		return this.accelerationX;
	}

	/**
	 * y軸方向の加速度を取得
	 */
	public double getY() {
		return this.accelerationY;
	}

	/**
	 * z軸方向の加速度を取得
	 */
	public double getZ() {
		return this.accelerationZ;
	}

	//リスナーを返す
	public SensorEventListener getSensorEventListener() {
		return this.sensorListener;
	}

	//センサ値が変化した際に呼ばれるメソッド
	//基本的にはオーバーライドして使う
	public void performWhenSensorChanged() {

	}
}
