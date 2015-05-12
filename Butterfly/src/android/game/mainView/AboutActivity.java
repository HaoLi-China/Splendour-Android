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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class AboutActivity extends Activity {

	private ImageView bg_about;
	private ImageButton imgbt_aback;

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

		setContentView(R.layout.about);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		bg_about = (ImageView) findViewById(R.id.bg_about);
		params = new AbsoluteLayout.LayoutParams(screenWidth * 2 / 3,
				screenHeight * 4 / 5, screenWidth / 2 - screenWidth / 3,
				screenHeight / 2 - screenHeight * 2 / 5);
		bg_about.setLayoutParams(params);
		bg_about.setBackgroundResource(R.drawable.bg_about);

		imgbt_aback = (ImageButton) findViewById(R.id.bt_aback);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight / 5, screenHeight
						- screenWidth / 5);
		imgbt_aback.setLayoutParams(params);
		imgbt_aback.setBackgroundResource(R.drawable.bt_back);
		imgbt_aback.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_aback.setBackgroundResource(R.drawable.bt_back_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_aback.setBackgroundResource(R.drawable.bt_back);
					break;
				}
				return false;
			}
		});
		imgbt_aback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AboutActivity.this, Gamemain.class);
				AboutActivity.this.startActivity(intent);
				System.exit(0);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		}
		return false;
	}

}
