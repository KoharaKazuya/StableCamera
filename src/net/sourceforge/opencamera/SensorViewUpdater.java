package net.sourceforge.opencamera;

//onResumeでインスタンス化すること

import java.text.DecimalFormat;

import android.app.Activity;
import android.widget.TextView;

public class SensorViewUpdater extends Accelerometer {

	private Activity parentActivity;
	private DecimalFormat decimalFormat = new DecimalFormat("#0.0");

	//コンストラクタ
	//ここでセンサ値をセットしたいビューを引数で渡す
	SensorViewUpdater(Activity parent) {
		super(parent.getApplicationContext());
		this.parentActivity = parent;
	}

	@Override
	public void performWhenSensorChanged() {
		this.updateSensorValues();
	}

	//Sensor値の更新
	private void updateSensorValues() {

		TextView acceleratorX = (TextView) this.parentActivity.findViewById(R.id.acceleratorX);
		TextView acceleratorY = (TextView) this.parentActivity.findViewById(R.id.acceleratorY);
		TextView acceleratorZ = (TextView) this.parentActivity.findViewById(R.id.acceleratorZ);

		double x = Math.abs(this.getX());
		double y = Math.abs(this.getY());
		double z = Math.abs(this.getZ());

		String xValue = "X\r\n" + decimalFormat.format(x);
		String yValue = "Y\r\n" + decimalFormat.format(y);
		String zValue = "Z\r\n" + decimalFormat.format(z);

		acceleratorX.setText(xValue);
		acceleratorY.setText(yValue);
		acceleratorZ.setText(zValue);
	}

}
