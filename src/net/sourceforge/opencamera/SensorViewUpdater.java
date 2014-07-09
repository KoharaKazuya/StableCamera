package net.sourceforge.opencamera;

//onResumeでインスタンス化すること

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SensorViewUpdater extends Accelerometer {

	private View parent = null;

	//コンストラクタ
	//ここでセンサ値をセットしたいビューを引数で渡す
	SensorViewUpdater(Context context, View parent) {
		super(context);
		this.parent = parent;
	}

	@Override
	public void performWhenSensorChanged() {
		this.updateSensorValues();
	}

	//Sensor値の更新
	private void updateSensorValues() {

		TextView acceleratorX = (TextView) parent.findViewById(R.id.acceleratorX);
		TextView acceleratorY = (TextView) parent.findViewById(R.id.acceleratorY);
		TextView acceleratorZ = (TextView) parent.findViewById(R.id.acceleratorZ);

		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();

		acceleratorX.setText(Double.toString(x));
		acceleratorY.setText(Double.toString(y));
		acceleratorZ.setText(Double.toString(z));
	}

}
