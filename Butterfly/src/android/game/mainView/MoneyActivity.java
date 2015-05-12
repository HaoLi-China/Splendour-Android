package android.game.mainView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.util.SysApplication;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class MoneyActivity extends Activity {

	private ImageButton imgbt_price1;
	private ImageButton imgbt_price2;
	private ImageButton imgbt_price3;
	private ImageButton imgbt_price4;
	private ImageButton imgbt_price5;
	private ImageButton imgbt_price6;

	private ImageButton imgbt_mback;

	private ImageView bg = null;

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

		setContentView(R.layout.money);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		bg = (ImageView) findViewById(R.id.money_bg);
		params = new AbsoluteLayout.LayoutParams(screenWidth * 3 / 5,
				screenHeight * 2 / 3, screenWidth / 5, screenHeight / 6);
		bg.setLayoutParams(params);

		imgbt_price1 = (ImageButton) findViewById(R.id.bt_price1);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 3 / 10, screenHeight * 17 / 40);
		imgbt_price1.setLayoutParams(params);
		imgbt_price1.setBackgroundResource(R.drawable.price1_1);
		imgbt_price1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price1.setBackgroundResource(R.drawable.price1_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price1.setBackgroundResource(R.drawable.price1_1);
					break;
				}
				return false;
			}
		});

		imgbt_price2 = (ImageButton) findViewById(R.id.bt_price2);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 35 / 80,
				screenHeight * 17 / 40);
		imgbt_price2.setLayoutParams(params);
		imgbt_price2.setBackgroundResource(R.drawable.price2_1);
		imgbt_price2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price2.setBackgroundResource(R.drawable.price2_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price2.setBackgroundResource(R.drawable.price2_1);
					break;
				}
				return false;
			}
		});

		imgbt_price3 = (ImageButton) findViewById(R.id.bt_price3);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 23 / 40,
				screenHeight * 17 / 40);
		imgbt_price3.setLayoutParams(params);
		imgbt_price3.setBackgroundResource(R.drawable.price3_1);
		imgbt_price3.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price3.setBackgroundResource(R.drawable.price3_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price3.setBackgroundResource(R.drawable.price3_1);
					break;
				}
				return false;
			}
		});

		imgbt_price4 = (ImageButton) findViewById(R.id.bt_price4);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 3 / 10, screenHeight * 13 / 20);
		imgbt_price4.setLayoutParams(params);
		imgbt_price4.setBackgroundResource(R.drawable.price4_1);
		imgbt_price4.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price4.setBackgroundResource(R.drawable.price4_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price4.setBackgroundResource(R.drawable.price4_1);
					break;
				}
				return false;
			}
		});

		imgbt_price5 = (ImageButton) findViewById(R.id.bt_price5);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 35 / 80,
				screenHeight * 13 / 20);
		imgbt_price5.setLayoutParams(params);
		imgbt_price5.setBackgroundResource(R.drawable.price5_1);
		imgbt_price5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price5.setBackgroundResource(R.drawable.price5_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price5.setBackgroundResource(R.drawable.price5_1);
					break;
				}
				return false;
			}
		});

		imgbt_price6 = (ImageButton) findViewById(R.id.bt_price6);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 10,
				screenHeight / 12, screenWidth * 23 / 40,
				screenHeight * 13 / 20);
		imgbt_price6.setLayoutParams(params);
		imgbt_price6.setBackgroundResource(R.drawable.price6_1);
		imgbt_price6.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_price6.setBackgroundResource(R.drawable.price6_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_price6.setBackgroundResource(R.drawable.price6_1);
					break;
				}
				return false;
			}
		});

		imgbt_mback = (ImageButton) findViewById(R.id.bt_mback);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight / 5, screenHeight
						- screenWidth / 5);
		imgbt_mback.setLayoutParams(params);
		imgbt_mback.setBackgroundResource(R.drawable.bt_back);
		imgbt_mback.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_mback.setBackgroundResource(R.drawable.bt_back_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_mback.setBackgroundResource(R.drawable.bt_back);
					break;
				}
				return false;
			}
		});
		imgbt_mback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MoneyActivity.this, Gamemain.class);
				MoneyActivity.this.startActivity(intent);
				System.exit(0);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			System.out.println("返回键执行了！！！");
		}
		return false;
	}

}
