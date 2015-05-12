package android.game.mainView;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.game.butterfly.R;
import android.game.util.BookPageFactory;
import android.game.util.PageWidget;
import android.game.util.SetScaleImage;
import android.game.util.SysApplication;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class HelpView extends Activity {

	private int window_width;// 屏幕的宽
	private int window_height;// 屏幕的高

	/** Called when the activity is first created. */
	private PageWidget mPageWidget;
	Bitmap mCurPageBitmap, mNextPageBitmap;
	Bitmap backBitmap[] = new Bitmap[4];
	Canvas mCurPageCanvas, mNextPageCanvas;
	BookPageFactory pagefactory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		// 无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		window_height = getWindowManager().getDefaultDisplay().getHeight();
		window_width = getWindowManager().getDefaultDisplay().getWidth();

		mPageWidget = new PageWidget(this, window_width, window_height);
		setContentView(mPageWidget);

		mCurPageBitmap = Bitmap.createBitmap(window_width, window_height,
				Bitmap.Config.ARGB_8888);
		mNextPageBitmap = Bitmap.createBitmap(window_width, window_height,
				Bitmap.Config.ARGB_8888);
		backBitmap[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help1);
		backBitmap[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help2);
		backBitmap[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help3);
		backBitmap[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.help4);

		SetScaleImage ssi = new SetScaleImage();
		for (int i = 0; i < 4; i++) {
			backBitmap[i] = ssi.getScaleImg(backBitmap[i], window_width,
					window_height);
		}

		mCurPageCanvas = new Canvas(mCurPageBitmap);
		mNextPageCanvas = new Canvas(mNextPageBitmap);
		pagefactory = new BookPageFactory(window_width, window_height);

		pagefactory.setBgBitmap(backBitmap);
		pagefactory.onDraw(mCurPageCanvas);
		mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);

		mPageWidget.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				// TODO Auto-generated method stub

				boolean ret = false;
				if (v == mPageWidget) {
					if (e.getAction() == MotionEvent.ACTION_DOWN) {
						mPageWidget.abortAnimation();
						mPageWidget.calcCornerXY(e.getX(), e.getY());

						pagefactory.onDraw(mCurPageCanvas);
						if (mPageWidget.DragToRight()) {
							try {
								pagefactory.prePage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (pagefactory.isfirstPage())
								return false;
							pagefactory.onDraw(mNextPageCanvas);
						} else {
							try {
								pagefactory.nextPage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (pagefactory.islastPage())
								return false;
							pagefactory.onDraw(mNextPageCanvas);
						}
						mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
					}

					ret = mPageWidget.doTouchEvent(e);
					return ret;
				}
				return false;
			}
		});
	}

	// 捕获手机的返回键的按下事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(HelpView.this, Gamemain.class);
			HelpView.this.startActivity(intent);
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}
}