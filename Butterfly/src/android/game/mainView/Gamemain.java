package android.game.mainView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.util.DataUtil;
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
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Gamemain extends Activity {

	private ImageButton imgbt_begin;
	private ImageButton imgbt_set;
	private ImageButton imgbt_help;
	private ImageButton imgbt_about;
	private ImageView img_title;
	private ImageButton imgbt_money;
	private TextView tv_money;
	private ImageButton imgbt_tools;
	private DataUtil datautil;

	private AbsoluteLayout.LayoutParams params = null;

	// 屏幕宽度和高度
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.gamemain);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		datautil = new DataUtil(this);
		datautil.initData();

		// 开始图片按钮，进入开始游戏
		imgbt_begin = (ImageButton) findViewById(R.id.bt_begin);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 5,
				screenHeight / 5, screenHeight / 4, screenHeight / 5);
		imgbt_begin.setLayoutParams(params);
		imgbt_begin.setBackgroundResource(R.drawable.bt_begin);
		imgbt_begin.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_begin.setBackgroundResource(R.drawable.bt_begin_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_begin.setBackgroundResource(R.drawable.bt_begin);
					break;
				}
				return false;
			}
		});
		imgbt_begin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, SceneActivity.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 设置图片按钮，进入设置游戏相关属性
		imgbt_set = (ImageButton) findViewById(R.id.bt_set);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 5,
				screenHeight / 5, screenWidth / 4 + screenHeight / 8,
				screenHeight * 7 / 20);
		imgbt_set.setLayoutParams(params);
		imgbt_set.setBackgroundResource(R.drawable.bt_set);
		imgbt_set.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_set.setBackgroundResource(R.drawable.bt_set_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_set.setBackgroundResource(R.drawable.bt_set);
					break;
				}
				return false;
			}
		});
		imgbt_set.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, SetActivity.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 帮助图片按钮，进入游戏帮助界面
		imgbt_help = (ImageButton) findViewById(R.id.bt_help);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 5,
				screenHeight / 5, screenWidth / 2, screenHeight / 5);
		imgbt_help.setLayoutParams(params);
		imgbt_help.setBackgroundResource(R.drawable.bt_help);
		imgbt_help.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_help.setBackgroundResource(R.drawable.bt_help_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_help.setBackgroundResource(R.drawable.bt_help);
					break;
				}
				return false;
			}
		});
		imgbt_help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, HelpView.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 关于我们
		imgbt_about = (ImageButton) findViewById(R.id.bt_about);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 5,
				screenHeight / 5, screenHeight / 4, screenHeight / 2);
		imgbt_about.setLayoutParams(params);
		imgbt_about.setBackgroundResource(R.drawable.bt_about);
		imgbt_about.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_about.setBackgroundResource(R.drawable.bt_about_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_about.setBackgroundResource(R.drawable.bt_about);
					break;
				}
				return false;
			}
		});
		imgbt_about.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, AboutActivity.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 游戏的名称和logo
		img_title = (ImageView) findViewById(R.id.title);
		params = new AbsoluteLayout.LayoutParams(screenWidth * 3 / 4,
				screenHeight * 3 / 4, screenWidth / 4, 0);
		img_title.setLayoutParams(params);
		img_title.setBackgroundResource(R.drawable.title);

		// 兑换金币
		imgbt_money = (ImageButton) findViewById(R.id.bt_money);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenHeight / 4, screenHeight * 4 / 5);
		imgbt_money.setLayoutParams(params);
		imgbt_money.setBackgroundResource(R.drawable.bt_money);
		imgbt_money.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_money.setBackgroundResource(R.drawable.bt_money_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_money.setBackgroundResource(R.drawable.bt_money);
					break;
				}
				return false;
			}
		});
		imgbt_money.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, MoneyActivity.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 剩余金币
		tv_money = (TextView) findViewById(R.id.tv_money);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 5,
				screenHeight / 6, screenWidth * 2 / 5, screenHeight * 4 / 5);
		tv_money.setLayoutParams(params);
		tv_money.setText(R.string.nowmoney);
		int nowmoney = datautil.getMoney();
		tv_money.append("\n" + nowmoney);

		// 购买道具
		imgbt_tools = (ImageButton) findViewById(R.id.bt_tools);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight * 5 / 12,
				screenHeight * 4 / 5);
		imgbt_tools.setLayoutParams(params);
		imgbt_tools.setBackgroundResource(R.drawable.bt_tools);
		imgbt_tools.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_tools.setBackgroundResource(R.drawable.bt_tools_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_tools.setBackgroundResource(R.drawable.bt_tools);
					break;
				}
				return false;
			}
		});
		imgbt_tools.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Gamemain.this, ToolsActivity.class);
				Gamemain.this.startActivity(intent);
				System.exit(0);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			new AlertDialog.Builder(this).setMessage("你确定要退出游戏吗？")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).setPositiveButton("退出",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									SysApplication.getInstance().exit();
								}
							}).show();
			return true;
		}
		return true;
	}

}
