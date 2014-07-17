package net.sourceforge.opencamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


public class ArrowCanvas extends View {

	private float x = 0.0f;
	private float y = 0.0f;

	//コンストラクタ
	public ArrowCanvas(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setFocusable(true);
	}

	//コンストラクタ
	public ArrowCanvas(Context context, AttributeSet attributes) {
		super(context, attributes);
		this.setFocusable(true);
	}

	//コンストラクタ
	public ArrowCanvas(Context context) {
		super(context);
		this.setFocusable(true);
	}


	//矢印の更新
	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
		this.invalidate();
	}

	//描画メソッド
	@Override
	protected void onDraw(Canvas canvas) {

		Log.i("OpenCamera", "onDraw");
		try {
			this.drawCenter(canvas);
			//this.drawArrow(canvas);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("OpenCamera", "onDraw - failed");
		}

		super.onDraw(canvas);
	}

	//レイアウトの初期化
	public void initLayout() {
		this.setRotation(270.0f);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.getLayoutParams();
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		this.setLayoutParams(params);
	}

	private void drawCenter(Canvas canvas) {

		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(2.0f);

		this.x = -this.x;

		//絶対値0.13までは許容
		this.x = (Math.abs(this.x) < 0.13) ? 0.0f : this.x;
		this.y = (Math.abs(this.y) < 0.13) ? 0.0f : this.y;


		if (Math.abs(this.x) + Math.abs(this.y) > 0.1) {
			paint.setColor(Color.RED);
		}

		//クロスの辺の長さ
		int length = 40;

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		int centerX = width / 2;
		int centerY = height / 2;

		int drawX = (int)((1 + x) * centerX);
		int drawY = (int)((1 + y) * centerY);

		if (drawX < 10) {
			drawX = 10;
		} else if (drawX > width - 10) {
			drawX = width - 10;
		}

		if (drawY < 10) {
			drawY = 10;
		} else if (drawY > height - 10) {
			drawY = height - 10;
		}

		//クロスの描画
		canvas.drawLine(drawX, drawY - length / 2, drawX, drawY + length / 2, paint);
		canvas.drawLine(drawX - length / 2, drawY, drawX + length / 2, drawY, paint);

	}

}
