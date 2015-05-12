package android.game.mainView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.gameView.GameView;
import android.game.util.DataUtil;
import android.game.util.SysApplication;
import android.os.Bundle;
import android.view.Gravity;
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
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SceneActivity extends Activity {

	private int scenenum = 1;

	private TextView tv_scene;
	private ImageButton imgbt_pre_scene;
	private ImageView scenekk;
	private ImageView scene;
	private ImageButton imgbt_next_scene;

	private ImageButton imgbt_unlock_scene;
	private ImageButton imgbt_begin_game;

	private ImageButton imgbt_sback;

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

		setContentView(R.layout.scene);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		datautil = new DataUtil(this);

		scenekk = (ImageView) findViewById(R.id.bg_scenekk);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 2,
				screenHeight * 2 / 3, screenWidth / 4, screenHeight / 6);
		scenekk.setLayoutParams(params);
		scenekk.setBackgroundResource(R.drawable.bg_scenekk);

		scenenum = datautil.getSceneNum();
		tv_scene = (TextView) findViewById(R.id.tv_scene);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 6,
				screenHeight / 9, screenWidth * 5 / 12, screenHeight / 12);
		tv_scene.setLayoutParams(params);
		scene = (ImageView) findViewById(R.id.bg_scene);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 2 - screenHeight
				/ 12, screenHeight * 7 / 12, screenWidth / 4 + screenHeight
				/ 24, screenHeight / 6 + screenHeight / 24);
		scene.setLayoutParams(params);
		switch (scenenum) {
		case 1:
			scene.setBackgroundResource(R.drawable.s1);
			tv_scene.setText("s1,请选择");
			break;
		case 2:
			scene.setBackgroundResource(R.drawable.s2);
			tv_scene.setText("s2,请选择");
			break;
		case 3:
			scene.setBackgroundResource(R.drawable.s3);
			tv_scene.setText("s3,请选择");
			break;
		case 4:
			scene.setBackgroundResource(R.drawable.s4);
			tv_scene.setText("s4,请选择");
			break;
		case 5:
			scene.setBackgroundResource(R.drawable.s5);
			tv_scene.setText("s5,请选择");
			break;
		}

		imgbt_pre_scene = (ImageButton) findViewById(R.id.pre_scene);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth / 8, screenHeight * 4 / 9);
		imgbt_pre_scene.setLayoutParams(params);
		imgbt_pre_scene.setBackgroundResource(R.drawable.bt_pre);
		imgbt_pre_scene.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_pre_scene.setBackgroundResource(R.drawable.bt_pre_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_pre_scene.setBackgroundResource(R.drawable.bt_pre);
					break;
				}
				return false;
			}
		});
		imgbt_pre_scene.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				scenenum = scenenum - 1;
				if (scenenum == 0)
					scenenum = 5;
				switch (scenenum) {
				case 1:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s1);
						tv_scene.setText("s1,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s1_2);
						tv_scene.setText("s1,未解锁" + "  1000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 2:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s2);
						tv_scene.setText("s2,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s2_2);
						tv_scene.setText("s2,未解锁" + "  2000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 3:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s3);
						tv_scene.setText("s3,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s3_2);
						tv_scene.setText("s3,未解锁" + "  5000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 4:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s4);
						tv_scene.setText("s4,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s4_2);
						tv_scene.setText("s4,未解锁" + "  10000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 5:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s5);
						tv_scene.setText("s5,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s5_2);
						tv_scene.setText("s5,未解锁" + "  20000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				}

			}
		});

		imgbt_next_scene = (ImageButton) findViewById(R.id.next_scene);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth * 7 / 8 - screenHeight / 9,
				screenHeight * 4 / 9);
		imgbt_next_scene.setLayoutParams(params);
		imgbt_next_scene.setBackgroundResource(R.drawable.bt_next);
		imgbt_next_scene.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_next_scene
							.setBackgroundResource(R.drawable.bt_next_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_next_scene.setBackgroundResource(R.drawable.bt_next);
					break;
				}
				return false;
			}
		});
		imgbt_next_scene.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				scenenum = scenenum + 1;
				if (scenenum == 6)
					scenenum = 1;
				switch (scenenum) {
				case 1:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s1);
						tv_scene.setText("s1,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s1_2);
						tv_scene.setText("s1,未解锁" + "  1000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 2:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s2);
						tv_scene.setText("s2,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s2_2);
						tv_scene.setText("s2,未解锁" + "  2000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 3:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s3);
						tv_scene.setText("s3,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s3_2);
						tv_scene.setText("s3,未解锁" + "  5000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 4:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s4);
						tv_scene.setText("s4,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s4_2);
						tv_scene.setText("s4,未解锁" + "  10000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				case 5:
					if (datautil.sceneUnlocked(scenenum) == 1) {
						scene.setBackgroundResource(R.drawable.s5);
						tv_scene.setText("s5,请选择");
						imgbt_unlock_scene.setVisibility(View.GONE);
						imgbt_begin_game.setVisibility(View.VISIBLE);
					} else {
						scene.setBackgroundResource(R.drawable.s5_2);
						tv_scene.setText("s5,未解锁" + "  20000金币");
						imgbt_unlock_scene.setVisibility(View.VISIBLE);
						imgbt_begin_game.setVisibility(View.GONE);
					}
					break;
				}

			}
		});

		imgbt_unlock_scene = (ImageButton) findViewById(R.id.unlock_scene);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 6,
				screenHeight / 9, screenWidth * 5 / 12, screenHeight * 2 / 3);
		imgbt_unlock_scene.setLayoutParams(params);
		imgbt_unlock_scene.setBackgroundResource(R.drawable.bt_unlockscene);
		imgbt_unlock_scene.setVisibility(View.GONE);
		imgbt_unlock_scene.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_unlock_scene
							.setBackgroundResource(R.drawable.bt_unlockscene_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_unlock_scene
							.setBackgroundResource(R.drawable.bt_unlockscene);
					break;
				}
				return false;
			}
		});
		imgbt_unlock_scene.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int rt = datautil.buyScene(scenenum);
				if (rt == 1) {
					String show = "恭喜你，购买成功，场景s" + scenenum + "已经解锁！\n剩余金币"
							+ datautil.getMoney() + "!";
					Toast toast = Toast.makeText(getApplicationContext(), show,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					imgbt_unlock_scene.setVisibility(View.GONE);
					imgbt_begin_game.setVisibility(View.VISIBLE);
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
		// 进入游戏,设置用户选择的场景
		imgbt_begin_game = (ImageButton) findViewById(R.id.begin_game);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 6,
				screenHeight / 9, screenWidth * 5 / 12, screenHeight * 2 / 3);
		imgbt_begin_game.setLayoutParams(params);
		imgbt_begin_game.setBackgroundResource(R.drawable.bt_gogame);
		imgbt_begin_game.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_begin_game
							.setBackgroundResource(R.drawable.bt_gogame_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_begin_game
							.setBackgroundResource(R.drawable.bt_gogame);
					break;
				}
				return false;
			}
		});
		imgbt_begin_game.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datautil.setSceneNum(scenenum);
				Intent intent = new Intent();
				intent.setClass(SceneActivity.this, GameView.class);
				SceneActivity.this.startActivity(intent);
				System.exit(0);
			}
		});

		imgbt_sback = (ImageButton) findViewById(R.id.bt_sback);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 6,
				screenHeight / 6, screenWidth - screenHeight / 5, screenHeight
						- screenWidth / 5);
		imgbt_sback.setLayoutParams(params);
		imgbt_sback.setBackgroundResource(R.drawable.bt_back);
		imgbt_sback.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_sback.setBackgroundResource(R.drawable.bt_back_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_sback.setBackgroundResource(R.drawable.bt_back);
					break;
				}
				return false;
			}
		});
		imgbt_sback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SceneActivity.this, Gamemain.class);
				SceneActivity.this.startActivity(intent);
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
