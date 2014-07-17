package net.sourceforge.opencamera.temp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ArrowCanvasForSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable  {
	public ArrowCanvasForSurface(Context context, AttributeSet attributes, int defStyle) {
		super(context, attributes, defStyle);
		this.init();
	}

	//コンストラクタ
	public ArrowCanvasForSurface(Context context) {
		super(context);
		this.init();
	}

	//コンストラクタ
	public ArrowCanvasForSurface(Context context, AttributeSet attributes) {
		super(context, attributes);
		this.init();
	}


//	//forView
//	@Override
//	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
//
//		Paint paint = new Paint();
//		paint.setColor(Color.GREEN);
//		paint.setStrokeWidth(1.0f);
//
//		int centerX = canvas.getWidth() / 2;
//		int centerY = canvas.getHeight() / 2;
//		canvas.drawLine(110, centerY, canvas.getWidth() - 110, centerY, paint);
//		canvas.drawLine(centerX, 110, centerX, canvas.getHeight() - 110, paint);
//	}
//
	//コールバックを登録//
	private void init() {
		this.getHolder().addCallback(this);
	}


	//中心に十字を描画
	private void drawCenter() {

		SurfaceHolder holder = this.getHolder();
		Canvas canvas = holder.lockCanvas();

		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(1.0f);

		int length = 20;

		//中心の座標を取得
		int centerX = canvas.getWidth() / 2;
		int centerY = canvas.getHeight() / 2;

		//中心をマーキング
		canvas.drawLine(centerX, centerY - length / 2, centerX, centerY + length / 2, paint);
		canvas.drawLine(centerX - length / 2, centerY, centerX + length / 2, centerY, paint);

		//描画
		holder.unlockCanvasAndPost(canvas);
	}

	//矢印の描画
	public void drawArrow(float x, float y) {

		//中心の描画
		this.drawCenter();

		Paint style = new Paint();
		style.setColor(Color.GREEN);
		style.setStrokeWidth(3.0f);
		style.setStyle(Paint.Style.FILL_AND_STROKE);

		if ((int)x == 0 && (int)y == 0) {
			return;
		}

		//SurfaceHolderの取得
		SurfaceHolder holder = this.getHolder();

		//Surfaceのキャンバスをロックして取得
		Canvas canvas = holder.lockCanvas();

		//キャンバスの大きさを取得
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		//キャンバスの中心の座標
		int centerX = width / 2;
		int centerY = height / 2;

		//描画する内容をここで定義する．
		Path path = new Path();

		//中心へ移動
		path.moveTo((float)centerX, (float)centerY);

		//矢印の先端の座標を決定し線を描画
		int lineToX = (int)((1 + x) * centerX);
		if (lineToX < 10) {
			lineToX = 10;
		} else if (lineToX > width - 10) {
			lineToX = width - 10;
		}

		int lineToY = (int)((1 + y) * centerY);
		if (lineToY < 10) {
			lineToY = 10;
		} else if (lineToY > height - 10) {
			lineToY = height - 10;
		}

		path.lineTo(lineToX, lineToY);


		//先端に正三角形を足す
		//一辺の長さ
		int sideLength = 5;

		//キャンバスの中心を原点とした時の線の先端の相対座標
		int relativeX = lineToX - centerX;
		int relativeY = lineToY - centerY;

		//矢印のx軸となす角の大きさ
		double arrowAngle = Math.atan(relativeY / relativeX);

		//三角系の頂点残り2点の座標を計算
		int vertex1X = lineToX - (int)(sideLength * (float)Math.cos(arrowAngle - Math.PI / 6.0) );
		int vertex1Y = lineToY - (int)(sideLength * (float)Math.sin(arrowAngle - Math.PI / 6.0) );
		int vertex2X = lineToX + (int)(sideLength * (float)Math.sin(arrowAngle - Math.PI / 3.0) );
		int vertex2Y = lineToY - (int)(sideLength * (float)Math.cos(arrowAngle - Math.PI / 3.0) );

		path.moveTo(lineToX, lineToY);
		path.lineTo(vertex1X, vertex1Y);

		path.moveTo(vertex1X, vertex1Y);
		path.lineTo(vertex2X, vertex2Y);

		path.moveTo(lineToX, lineToY);
		path.close();

		//canvasに描画
		canvas.drawPath(path, style);

		//ビューの更新
		holder.unlockCanvasAndPost(canvas);
	}
//for SurfaceVieww///	@Override///	public void run() {///		// TODO 自動生成されたメソッド・スタブ//////	}//////	@Override///	public void surfaceCreated(SurfaceHolder holder) {//////		//レイアウトの初期化///		ArrowCanvas.this.setRotation(270.0f);///		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)ArrowCanvas.this.getLayoutParams();///		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);///		ArrowCanvas.this.setLayoutParams(params);//////		Canvas canvas = holde.lockCanvas();;///		holder.unlockCanvasAndPost(canvas);//////	}//////	@Override//	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//		System.out.println("a");
//		}//////	@Override///	public void surfaceDestroyed(SurfaceHolder holder) {}///

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ

	}


}
