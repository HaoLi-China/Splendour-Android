package android.game.gameView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.game.butterfly.R;
import android.game.mainView.Gamemain;
import android.game.thread.DecSpeedThread;
import android.game.thread.IncRateThread;
import android.game.thread.setDoubleThread;
import android.game.util.DataUtil;
import android.game.util.MyButton;
import android.game.util.SoundPlay;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class GameView extends Activity {
	private Butterflying butterflying = null;
	private WidgetView widgetView = null;
	private AnimView animView = null;

	private ImageView load = null;

	private AlertDialog myDialog;// 对话框

	private AbsoluteLayout.LayoutParams params = null;

	// AbsoluteLayout对象
	private AbsoluteLayout al_window1 = null;
	private AbsoluteLayout al_wondow2 = null;
	private AbsoluteLayout al_wondow3 = null;
	public AbsoluteLayout al_wondow4 = null;

	// 屏幕宽度和高度
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	private ImageView loading = null;

	// 逐帧动画AnimationDrawable对象
	protected AnimationDrawable wait_ani = new AnimationDrawable();

	// 标志
	private int sign = 0;

	// 声音资源
	private MediaPlayer backSound = null;
	private MediaPlayer beeSound = null;
	private MediaPlayer butterSound = null;
	private MediaPlayer catchSound = null;
	private MediaPlayer lightingSound = null;

	// 播放声音对象
	protected SoundPlay soundPlay = null;

	private Context context = null;

	// 配置文件对象
	private DataUtil datautil = null;

	// 声音标志
	protected boolean isMusic = true;
	protected boolean isSound = true;

	// 声控标志
	protected boolean isSoundControl = true;

	// 场景
	protected int scene = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// SysApplication.getInstance().addActivity(this);
		// 无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 设置屏幕恒为横向
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// 设置屏幕常亮
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.game_view);

		context = this;
		datautil = new DataUtil(this);

		scene = datautil.getSceneNum();
		int musicState = datautil.getYY();
		if (musicState == 0) {
			isMusic = false;
		}
		int soundState = datautil.getYX();
		if (soundState == 0) {
			isSound = false;
		}
		int soundControlState = datautil.getSK();
		if (soundControlState == 0) {
			isSoundControl = false;
		}

		al_window1 = ((AbsoluteLayout) findViewById(R.id.load));
		al_wondow2 = ((AbsoluteLayout) findViewById(R.id.game));
		al_wondow3 = ((AbsoluteLayout) findViewById(R.id.button));
		al_wondow4 = ((AbsoluteLayout) findViewById(R.id.anim));

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();

		loading = new ImageView(this);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 3,
				screenHeight / 3, screenWidth / 2 - screenHeight / 6,
				screenHeight / 4);
		al_window1.addView(loading, params);

		load = new ImageView(this);
		load.setBackgroundResource(R.drawable.load);
		params = new AbsoluteLayout.LayoutParams(screenHeight / 3,
				screenHeight / 12, screenWidth / 2 - screenHeight / 6,
				screenHeight * 2 / 3);
		al_window1.addView(load, params);

		animView = new AnimView(this);
		al_wondow4.addView(animView.getView(), animView.getParams());

		soundPlay = new SoundPlay(this);

		// 加载游戏View
		butterflying = new Butterflying(this);
		new Thread(butterflying).start();

		// 加载按钮View
		widgetView = new WidgetView(this);
		new Thread(widgetView).start();
	}

	// 显示游戏界面
	public void showView() {
		if (sign == 2) {
			soundPlay.palyBackMusic(isMusic);
			wait_ani.stop();
			al_wondow2.addView(butterflying);

			al_wondow3
					.addView(widgetView.getTool1Bg(), widgetView.getParams1());
			al_wondow3
					.addView(widgetView.getTool2Bg(), widgetView.getParams2());
			al_wondow3
					.addView(widgetView.getTool3Bg(), widgetView.getParams3());
			al_wondow3
					.addView(widgetView.getTool4Bg(), widgetView.getParams4());
			al_wondow3
					.addView(widgetView.getTool5Bg(), widgetView.getParams5());

			al_wondow3.addView(widgetView.getTool1(), widgetView.getParams1());
			al_wondow3.addView(widgetView.getTool2(), widgetView.getParams2());
			al_wondow3.addView(widgetView.getTool3(), widgetView.getParams3());
			al_wondow3.addView(widgetView.getTool4(), widgetView.getParams4());
			al_wondow3.addView(widgetView.getTool5(), widgetView.getParams5());

			al_wondow3.addView(widgetView.getCatchButton(), widgetView
					.getParams7());
			al_wondow3.addView(widgetView.getMusicButton(), widgetView
					.getParams8());
			if (isMusic) {
				widgetView.getMusicButton().setBackgroundResource(
						R.drawable.music_on);
			} else {
				widgetView.getMusicButton().setBackgroundResource(
						R.drawable.music_off);
			}
			al_wondow3.addView(widgetView.getSoundButton(), widgetView
					.getParams9());
			if (isSound) {
				widgetView.getSoundButton().setBackgroundResource(
						R.drawable.sound_on);
			} else {
				widgetView.getSoundButton().setBackgroundResource(
						R.drawable.sound_off);
			}
			al_wondow3.addView(widgetView.getPauseButton(), widgetView
					.getParams10());
			al_wondow3.addView(widgetView.getExperienceBar(), widgetView
					.getParams6());
			al_wondow3.addView(widgetView.getTextView(), widgetView
					.getParams11());
			widgetView.getTextView().setText(butterflying.level + "");
			double expProcess = (double) (butterflying.experience)
					/ butterflying.lp.getGetMaxExperience(butterflying.level)
					* (widgetView.getExpE() - widgetView.getExpS())
					+ widgetView.getExpS();
			params = new AbsoluteLayout.LayoutParams(screenHeight * 2 / 45,
					screenHeight * 2 / 45, (int) expProcess, screenHeight / 12);
			al_wondow3.addView(widgetView.getExperienceTool(), params);
			// buttonView.getExperienceTool().setLayoutParams(params);

			al_wondow3.addView(widgetView.getTool1NumBg(), widgetView
					.getParams17());
			al_wondow3.addView(widgetView.getTool2NumBg(), widgetView
					.getParams18());
			al_wondow3.addView(widgetView.getTool3NumBg(), widgetView
					.getParams19());
			al_wondow3.addView(widgetView.getTool4NumBg(), widgetView
					.getParams20());
			al_wondow3.addView(widgetView.getTool5NumBg(), widgetView
					.getParams21());
			al_wondow3.addView(widgetView.getTool1NumView(), widgetView
					.getParams12());
			al_wondow3.addView(widgetView.getTool2NumView(), widgetView
					.getParams13());
			al_wondow3.addView(widgetView.getTool3NumView(), widgetView
					.getParams14());
			al_wondow3.addView(widgetView.getTool4NumView(), widgetView
					.getParams15());
			al_wondow3.addView(widgetView.getTool5NumView(), widgetView
					.getParams16());

			widgetView.getTool1NumView().setText(butterflying.tool1num + "");
			widgetView.getTool2NumView().setText(butterflying.tool2num + "");
			widgetView.getTool3NumView().setText(butterflying.tool3num + "");
			widgetView.getTool4NumView().setText(butterflying.tool4num + "");
			widgetView.getTool5NumView().setText(butterflying.tool5num + "");

		}
	}

	Handler myHandler = new Handler() {// 用来更新UI线程中的控件
		public void handleMessage(Message msg) {
			if (msg.what == 0) {// 游戏界面加载完传来的消息
				sign++;
				showView();
			}
			if (msg.what == 1) {// 按钮界面加载完传来的消息
				sign++;
				showView();
			}
			if (msg.what == 2) {// 点击道具一发来的消息
				if (butterflying.tool1num > 0) {
					animView.setAniamals(1);
					butterflying.tool1num -= 1;
					widgetView.getTool1NumView().setText(
							butterflying.tool1num + "");
					butterflying.rws.write(context, "tool1num",
							butterflying.tool1num + "");
					butterflying.killBee();
				}
			}
			if (msg.what == 3) {// 点击道具二发来的消息
				if (butterflying.tool2num > 0) {
					animView.setAniamals(2);
					butterflying.tool2num -= 1;
					widgetView.getTool2NumView().setText(
							butterflying.tool2num + "");
					butterflying.rws.write(context, "tool2num",
							butterflying.tool2num + "");
					butterflying.catchAll();
				}
			}
			if (msg.what == 4) {// 点击道具三发来的消息
				if (butterflying.tool3num > 0) {
					animView.setAniamals(3);
					butterflying.tool3num -= 1;
					widgetView.getTool3NumView().setText(
							butterflying.tool3num + "");
					butterflying.rws.write(context, "tool3num",
							butterflying.tool3num + "");
					new DecSpeedThread(butterflying).start();
				}
			}
			if (msg.what == 5) {// 点击道具四发来的消息
				if (butterflying.tool4num > 0) {
					animView.setAniamals(4);
					butterflying.tool4num -= 1;
					widgetView.getTool4NumView().setText(
							butterflying.tool4num + "");
					butterflying.rws.write(context, "tool4num",
							butterflying.tool4num + "");
					new IncRateThread(butterflying).start();
				}
			}
			if (msg.what == 6) {// 点击道具五发来的消息
				if (butterflying.tool5num > 0) {
					animView.setAniamals(5);
					butterflying.tool5num -= 1;
					widgetView.getTool5NumView().setText(
							butterflying.tool5num + "");
					butterflying.rws.write(context, "tool5num",
							butterflying.tool5num + "");
					new setDoubleThread(butterflying).start();
				}
			}
			if (msg.what == 7) {// 游戏升级发来的消息
				widgetView.getTextView().setText(butterflying.level + "");
			}
			if (msg.what == 8) {// 游戏经验值提升发来的消息
				double expProcess = (double) (butterflying.experience)
						/ butterflying.lp
								.getGetMaxExperience(butterflying.level)
						* (widgetView.getExpE() - widgetView.getExpS())
						+ widgetView.getExpS();
				params = new AbsoluteLayout.LayoutParams(screenHeight * 2 / 45,
						screenHeight * 2 / 45, (int) expProcess,
						screenHeight / 12);
				widgetView.getExperienceTool().setLayoutParams(params);
			}
			if (msg.what == 9) {// 点击捕捉按钮发来的消息
				butterflying.catchButter(butterflying.getWebX()
						+ butterflying.getWebWidth() / 2, butterflying
						.getWebY()
						+ butterflying.getWebHeight() / 2);
			}
			if (msg.what == 10) {// 点击暂停按钮发来的消息
				butterflying.setPauseState(true);
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.pause_dialog,
						(ViewGroup) findViewById(R.id.layout_root));
				myDialog = new AlertDialog.Builder(GameView.this).create();
				myDialog.show();
				myDialog.getWindow().setContentView(layout);

				Integer[] main_button = { R.drawable.main1, R.drawable.main2,
						R.drawable.main2 };
				Integer[] go_on_button = { R.drawable.go_on1,
						R.drawable.go_on2, R.drawable.go_on2 };

				Button main = (Button) myDialog.findViewById(R.id.main);
				Button go_on = (Button) myDialog.findViewById(R.id.go_on);

				MyButton myMainButton = new MyButton(context);
				main.setBackgroundDrawable(myMainButton.setbg(main_button));

				MyButton myGoOnButton = new MyButton(context);
				go_on.setBackgroundDrawable(myGoOnButton.setbg(go_on_button));

				main.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(GameView.this, Gamemain.class);
						GameView.this.startActivity(intent);
						System.exit(0);
					}
				});

				go_on.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						myDialog.dismiss();
						butterflying.setPauseState(false);
					}
				});
			}
			if (msg.what == 11) {// 点击音乐按钮发来的消息
				if (isMusic) {
					isMusic = false;
					datautil.setYY(0);
					widgetView.getMusicButton().setBackgroundResource(
							R.drawable.music_off);
					soundPlay.stopBackMusic();
				} else {
					isMusic = true;
					datautil.setYY(1);
					widgetView.getMusicButton().setBackgroundResource(
							R.drawable.music_on);
					soundPlay.palyBackMusic(isMusic);
				}
			}
			if (msg.what == 12) {// 点击音效按钮发来的消息
				if (isSound) {
					isSound = false;
					datautil.setYX(0);
					widgetView.getSoundButton().setBackgroundResource(
							R.drawable.sound_off);
				} else {
					isSound = true;
					datautil.setYX(1);
					widgetView.getSoundButton().setBackgroundResource(
							R.drawable.sound_on);
				}
			}
		}
	};

	// 设置背景音乐
	public void setBackSound() {
		backSound = MediaPlayer.create(context, R.raw.backmusic);
	}

	// 设置抓到蝴蝶的音效
	public void setButterSound() {
		butterSound = MediaPlayer.create(context, R.raw.buttercaught);
	}

	// 设置抓到蜜蜂的音效
	public void setBeeSound() {
		beeSound = MediaPlayer.create(context, R.raw.beecaught);
	}

	// 设置抓捕的音效
	public void setCatchSound() {
		catchSound = MediaPlayer.create(context, R.raw.caught);
	}

	// 设置闪电音效
	public void setLightingSound() {
		lightingSound = MediaPlayer.create(context, R.raw.lightning);
	}

	// 获得背景音乐
	public MediaPlayer getBackSound() {
		return backSound;
	}

	// 获得抓到蝴蝶的音效
	public MediaPlayer getButterSound() {
		return butterSound;
	}

	// 获得抓到蜜蜂的音效
	public MediaPlayer getBeeSound() {
		return beeSound;
	}

	// 获得抓捕的音效
	public MediaPlayer getCatchSound() {
		return catchSound;
	}

	// 获得闪电的音效
	public MediaPlayer getLightingSound() {
		return lightingSound;
	}

	// 进入场景后播放动画并加载
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// 圆环旋转动画
		loading.setBackgroundResource(R.anim.wait_ani);
		wait_ani = (AnimationDrawable) loading.getBackground();
		System.out.println(123);
		wait_ani.start();
		super.onWindowFocusChanged(hasFocus);
	}

	// 捕获手机的返回键的按下事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			butterflying.setPauseState(false);
		}
		return false;
	}
}
