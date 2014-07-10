package net.sourceforge.opencamera;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//加速度センサの値を扱うためのクラス
class Accelerometer {

	protected SensorManager sensor = null;
	protected List<Sensor> sensorList = null;
	protected SensorEventListener sensorListener = null;

	protected float ALPHA = 0.8f;

	//重力x, y , z
	protected float[] gravity = new float[3];

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
			public void onSensorChanged(SensorEvent event) {
				updateGravity(event);
				accelerationX = event.values[0] - gravity[0];
				accelerationY = event.values[1] - gravity[1];
				accelerationZ = event.values[2] - gravity[2];
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

	//リスナーを登録
	public void setListener() {
		if (this.sensorList.size() > 0) {
			this.sensor.registerListener(this.sensorListener, this.sensorList.get(0), SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	//リスナーを解除
	public void unsetListener() {
		if (this.sensorList.size() > 0) {
			this.sensor.unregisterListener(this.sensorListener, this.sensorList.get(0));
		}
	}

	//センサ値が変化した際に呼ばれるメソッド
	//基本的にはオーバーライドして使う
	public void performWhenSensorChanged(){}


	//重力値の更新
	private void updateGravity(SensorEvent event) {
		for (int i = 0; i < this.gravity.length; i++) {
			this.gravity[i] = ALPHA * this.gravity[i] + (1.0f - ALPHA) * event.values[i];
		}
	}
}
