package android.game.mainView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.util.DataUtil;
import android.game.util.SysApplication;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class SetActivity extends Activity {

	private ImageView bg_set1;
	private ImageView bg_set2;
	private ImageView bg_set3;
	private ImageView img_sk;
	private ImageButton imgbt_sk;
	private ImageView img_yx;
	private ImageButton imgbt_yx;
	private ImageView img_yy;
	private ImageButton imgbt_yy;

	private int sk_onoroff;
	private int yx_onoroff;
	private int yy_onoroff;

	private ImageButton imgbt_setback;

	private DataUtil datautil;

	// 屏幕宽度和高度
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	private AbsoluteLayout.LayoutParams params = null;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.set);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		datautil = new DataUtil(this);

		bg_set1 = (ImageView) findViewById(R.id.bg_set1);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 2,
				screenHeight / 4, screenWidth / 4, screenHeight / 16);
		bg_set1.setLayoutParams(params);
		bg_set1.setBackgroundResource(R.drawable.bg_scenekk);

		bg_set2 = (ImageView) findViewById(R.id.bg_set2);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 2,
				screenHeight / 4, screenWidth / 4, screenHeight * 3 / 8);
		bg_set2.setLayoutParams(params);
		bg_set2.setBackgroundResource(R.drawable.bg_scenekk);

		bg_set3 = (ImageView) findViewById(R.id.bg_set3);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 2,
				screenHeight / 4, screenWidth / 4, screenHeight * 11 / 16);
		bg_set3.setLayoutParams(params);
		bg_set3.setBackgroundResource(R.drawable.bg_scenekk);

		img_sk = (ImageView) findViewById(R.id.img_sk);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth / 4 + screenHeight / 8,
				screenHeight / 8);
		img_sk.setLayoutParams(params);
		img_sk.setBackgroundResource(R.drawable.bt_sk);

		imgbt_sk = (ImageButton) findViewById(R.id.bt_sk);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth * 3 / 4 - screenHeight * 3 / 8,
				screenHeight / 8);
		imgbt_sk.setLayoutParams(params);
		sk_onoroff = datautil.getSK();
		if (sk_onoroff == 1) {
			imgbt_sk.setBackgroundResource(R.drawable.bt_on);
		} else {
			imgbt_sk.setBackgroundResource(R.drawable.bt_off);
		}
		imgbt_sk.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (sk_onoroff == 1) {
					imgbt_sk.setBackgroundResource(R.drawable.bt_off);
					sk_onoroff = 0;
					datautil.setSK(sk_onoroff);
				} else {
					imgbt_sk.setBackgroundResource(R.drawable.bt_on);
					sk_onoroff = 1;
					datautil.setSK(sk_onoroff);
				}
			}
		});
		/*
		 * imgbt_sk.setOnTouchListener(new OnTouchListener() { public boolean
		 * onTouch(View arg0, MotionEvent arg1) { switch (arg1.getAction()) {
		 * case MotionEvent.ACTION_DOWN:
		 * System.out.println(px2dip(getApplication(),arg1.getX()));
		 * System.out.println(px2dip(getApplication(),arg1.getY())); break; case
		 * MotionEvent.ACTION_UP: break; } return false; } });
		 */
		img_yx = (ImageView) findViewById(R.id.img_yx);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth / 4 + screenHeight / 8,
				screenHeight * 7 / 16);
		img_yx.setLayoutParams(params);
		img_yx.setBackgroundResource(R.drawable.bt_yx);

		imgbt_yx = (ImageButton) findViewById(R.id.bt_yx);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth * 3 / 4 - screenHeight * 3 / 8,
				screenHeight * 7 / 16);
		imgbt_yx.setLayoutParams(params);
		yx_onoroff = datautil.getYX();
		if (yx_onoroff == 1) {
			imgbt_yx.setBackgroundResource(R.drawable.bt_on);
		} else {
			imgbt_yx.setBackgroundResource(R.drawable.bt_off);
		}
		imgbt_yx.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (yx_onoroff == 1) {
					imgbt_yx.setBackgroundResource(R.drawable.bt_off);
					yx_onoroff = 0;
					datautil.setYX(yx_onoroff);
				} else {
					imgbt_yx.setBackgroundResource(R.drawable.bt_on);
					yx_onoroff = 1;
					datautil.setYX(yx_onoroff);
				}
			}
		});

		img_yy = (ImageView) findViewById(R.id.img_yy);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth / 4 + screenHeight / 8,
				screenHeight * 3 / 4);
		img_yy.setLayoutParams(params);
		img_yy.setBackgroundResource(R.drawable.bt_yy);

		imgbt_yy = (ImageButton) findViewById(R.id.bt_yy);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 4,
				screenHeight / 8, screenWidth * 3 / 4 - screenHeight * 3 / 8,
				screenHeight * 3 / 4);
		imgbt_yy.setLayoutParams(params);
		yy_onoroff = datautil.getYY();
		if (yy_onoroff == 1) {
			imgbt_yy.setBackgroundResource(R.drawable.bt_on);
		} else {
			imgbt_yy.setBackgroundResource(R.drawable.bt_off);
		}
		imgbt_yy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (yy_onoroff == 1) {
					imgbt_yy.setBackgroundResource(R.drawable.bt_off);
					yy_onoroff = 0;
					datautil.setYY(yy_onoroff);
				} else {
					imgbt_yy.setBackgroundResource(R.drawable.bt_on);
					yy_onoroff = 1;
					datautil.setYY(yy_onoroff);
				}
			}
		});

		imgbt_setback = (ImageButton) findViewById(R.id.bt_setback);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight / 5, screenHeight
						- screenWidth / 5);
		imgbt_setback.setLayoutParams(params);
		imgbt_setback.setBackgroundResource(R.drawable.bt_back);
		imgbt_setback.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_setback.setBackgroundResource(R.drawable.bt_back_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_setback.setBackgroundResource(R.drawable.bt_back);
					break;
				}
				return false;
			}
		});
		imgbt_setback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SetActivity.this, Gamemain.class);
				SetActivity.this.startActivity(intent);
				System.exit(0);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		}
		return false;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

}
