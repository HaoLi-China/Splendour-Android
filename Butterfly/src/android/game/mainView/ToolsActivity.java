package android.game.mainView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.util.DataUtil;
import android.game.util.SysApplication;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ToolsActivity extends Activity {

	private ImageButton imgbt_toolprice1;
	private ImageButton imgbt_toolprice2;
	private ImageButton imgbt_toolprice3;
	private ImageButton imgbt_toolprice4;
	private ImageButton imgbt_toolprice5;

	private ImageButton imgbt_tback;

	// 屏幕宽度和高度
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	private AbsoluteLayout.LayoutParams params = null;

	private DataUtil datautil;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.tools);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		datautil = new DataUtil(this);

		imgbt_toolprice1 = (ImageButton) findViewById(R.id.bt_toolprice1);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 3, screenWidth / 10, screenHeight / 9);
		imgbt_toolprice1.setLayoutParams(params);
		imgbt_toolprice1.setBackgroundResource(R.drawable.toolprice1);
		imgbt_toolprice1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_toolprice1
							.setBackgroundResource(R.drawable.toolprice1_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_toolprice1
							.setBackgroundResource(R.drawable.toolprice1);
					break;
				}
				return false;
			}
		});
		imgbt_toolprice1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyTools(1);
				if (rt == 1) {
					String show = "恭喜你，购买成功，‘雷击’道具现在还有"
							+ datautil.getToolNum(1) + "件！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else if (rt == -1) {
					String show = "对不起，您的金币只有" + datautil.getMoney()
							+ "，不足，请购买金币！";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				return;
			}
		});

		imgbt_toolprice2 = (ImageButton) findViewById(R.id.bt_toolprice2);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 3, screenWidth * 2 / 5, screenHeight / 9);
		imgbt_toolprice2.setLayoutParams(params);
		imgbt_toolprice2.setBackgroundResource(R.drawable.toolprice2);
		imgbt_toolprice2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_toolprice2
							.setBackgroundResource(R.drawable.toolprice2_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_toolprice2
							.setBackgroundResource(R.drawable.toolprice2);
					break;
				}
				return false;
			}
		});
		imgbt_toolprice2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyTools(2);
				if (rt == 1) {
					String show = "恭喜你，购买成功，‘时光机’道具现在还有"
							+ datautil.getToolNum(2) + "件！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else if (rt == -1) {
					String show = "对不起，您的金币只有" + datautil.getMoney()
							+ "，不足，请购买金币！";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				return;
			}
		});

		imgbt_toolprice3 = (ImageButton) findViewById(R.id.bt_toolprice3);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 3, screenWidth * 7 / 10, screenHeight / 9);
		imgbt_toolprice3.setLayoutParams(params);
		imgbt_toolprice3.setBackgroundResource(R.drawable.toolprice3);
		imgbt_toolprice3.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_toolprice3
							.setBackgroundResource(R.drawable.toolprice3_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_toolprice3
							.setBackgroundResource(R.drawable.toolprice3);
					break;
				}
				return false;
			}
		});
		imgbt_toolprice3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyTools(3);
				if (rt == 1) {
					String show = "恭喜你，购买成功，‘捕蝶网’道具现在还有"
							+ datautil.getToolNum(3) + "件！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else if (rt == -1) {
					String show = "对不起，您的金币只有" + datautil.getMoney()
							+ "，不足，请购买金币！";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				return;
			}
		});

		imgbt_toolprice4 = (ImageButton) findViewById(R.id.bt_toolprice4);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 3, screenWidth / 10, screenHeight * 5 / 9);
		imgbt_toolprice4.setLayoutParams(params);
		imgbt_toolprice4.setBackgroundResource(R.drawable.toolprice4);
		imgbt_toolprice4.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_toolprice4
							.setBackgroundResource(R.drawable.toolprice4_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_toolprice4
							.setBackgroundResource(R.drawable.toolprice4);
					break;
				}
				return false;
			}
		});
		imgbt_toolprice4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyTools(4);
				if (rt == 1) {
					String show = "恭喜你，购买成功，‘蝶神’道具现在还有"
							+ datautil.getToolNum(4) + "件！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else if (rt == -1) {
					String show = "对不起，您的金币只有" + datautil.getMoney()
							+ "，不足，请购买金币！";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				return;
			}
		});

		imgbt_toolprice5 = (ImageButton) findViewById(R.id.bt_toolprice5);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 3, screenWidth * 2 / 5, screenHeight * 5 / 9);
		imgbt_toolprice5.setLayoutParams(params);
		imgbt_toolprice5.setBackgroundResource(R.drawable.toolprice5);
		imgbt_toolprice5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_toolprice5
							.setBackgroundResource(R.drawable.toolprice5_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_toolprice5
							.setBackgroundResource(R.drawable.toolprice5);
					break;
				}
				return false;
			}
		});
		imgbt_toolprice5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyTools(5);
				if (rt == 1) {
					String show = "恭喜你，购买成功，‘蓝色妖姬’道具现在还有"
							+ datautil.getToolNum(5) + "件！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else if (rt == -1) {
					String show = "对不起，您的金币只有" + datautil.getMoney()
							+ "，不足，请购买金币！";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				return;
			}
		});

		imgbt_tback = (ImageButton) findViewById(R.id.bt_tback);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight / 5, screenHeight
						- screenWidth / 5);
		imgbt_tback.setLayoutParams(params);
		imgbt_tback.setBackgroundResource(R.drawable.bt_back);
		imgbt_tback.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_tback.setBackgroundResource(R.drawable.bt_back_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_tback.setBackgroundResource(R.drawable.bt_back);
					break;
				}
				return false;
			}
		});
		imgbt_tback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ToolsActivity.this, Gamemain.class);
				ToolsActivity.this.startActivity(intent);
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
