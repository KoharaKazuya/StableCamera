package net.sourceforge.opencamera;

import java.text.DecimalFormat;

import android.app.Activity;
import android.widget.TextView;

public class GyroSensorViewUpdater extends GyroSensor {

	private Activity parentActivity;
	private DecimalFormat decimalFormat = new DecimalFormat("#0.0");


	public GyroSensorViewUpdater(Activity parent) {
		super(parent.getApplicationContext());
		this.parentActivity = parent;
	}

	@Override
	public void performWhenSensorChanged() {
		this.updateGyroSensorValues();
	}


	private void updateGyroSensorValues() {

		TextView gyroX = (TextView) this.parentActivity.findViewById(R.id.gyroX);
		TextView gyroY = (TextView) this.parentActivity.findViewById(R.id.gyroY);
		TextView gyroZ = (TextView) this.parentActivity.findViewById(R.id.gyroZ);

		double x = this.getGyroX();
		double y = this.getGyroY();
		double z = this.getGyroZ();

		String xValue = "GX\r\n" + decimalFormat.format(x);
		String yValue = "GY\r\n" + decimalFormat.format(y);
		String zValue = "GZ\r\n" + decimalFormat.format(z);

		gyroX.setText(xValue);
		gyroY.setText(yValue);
		gyroZ.setText(zValue);
	}

}
