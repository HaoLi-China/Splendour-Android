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

	private AlertDialog myDialog;// �Ի���

	private AbsoluteLayout.LayoutParams params = null;

	// AbsoluteLayout����
	private AbsoluteLayout al_window1 = null;
	private AbsoluteLayout al_wondow2 = null;
	private AbsoluteLayout al_wondow3 = null;
	public AbsoluteLayout al_wondow4 = null;

	// ��Ļ��Ⱥ͸߶�
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	private ImageView loading = null;

	// ��֡����AnimationDrawable����
	protected AnimationDrawable wait_ani = new AnimationDrawable();

	// ��־
	private int sign = 0;

	// ������Դ
	private MediaPlayer backSound = null;
	private MediaPlayer beeSound = null;
	private MediaPlayer butterSound = null;
	private MediaPlayer catchSound = null;
	private MediaPlayer lightingSound = null;

	// ������������
	protected SoundPlay soundPlay = null;

	private Context context = null;

	// �����ļ�����
	private DataUtil datautil = null;

	// ������־
	protected boolean isMusic = true;
	protected boolean isSound = true;

	// ���ر�־
	protected boolean isSoundControl = true;

	// ����
	protected int scene = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// SysApplication.getInstance().addActivity(this);
		// ��title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// ������Ļ��Ϊ����
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// ������Ļ����
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

		// ������ϷView
		butterflying = new Butterflying(this);
		new Thread(butterflying).start();

		// ���ذ�ťView
		widgetView = new WidgetView(this);
		new Thread(widgetView).start();
	}

	// ��ʾ��Ϸ����
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

	Handler myHandler = new Handler() {// ��������UI�߳��еĿؼ�
		public void handleMessage(Message msg) {
			if (msg.what == 0) {// ��Ϸ��������괫������Ϣ
				sign++;
				showView();
			}
			if (msg.what == 1) {// ��ť��������괫������Ϣ
				sign++;
				showView();
			}
			if (msg.what == 2) {// �������һ��������Ϣ
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
			if (msg.what == 3) {// ������߶���������Ϣ
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
			if (msg.what == 4) {// �����������������Ϣ
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
			if (msg.what == 5) {// ��������ķ�������Ϣ
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
			if (msg.what == 6) {// ��������巢������Ϣ
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
			if (msg.what == 7) {// ��Ϸ������������Ϣ
				widgetView.getTextView().setText(butterflying.level + "");
			}
			if (msg.what == 8) {// ��Ϸ����ֵ������������Ϣ
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
			if (msg.what == 9) {// �����׽��ť��������Ϣ
				butterflying.catchButter(butterflying.getWebX()
						+ butterflying.getWebWidth() / 2, butterflying
						.getWebY()
						+ butterflying.getWebHeight() / 2);
			}
			if (msg.what == 10) {// �����ͣ��ť��������Ϣ
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
			if (msg.what == 11) {// ������ְ�ť��������Ϣ
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
			if (msg.what == 12) {// �����Ч��ť��������Ϣ
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

	// ���ñ�������
	public void setBackSound() {
		backSound = MediaPlayer.create(context, R.raw.backmusic);
	}

	// ����ץ����������Ч
	public void setButterSound() {
		butterSound = MediaPlayer.create(context, R.raw.buttercaught);
	}

	// ����ץ���۷����Ч
	public void setBeeSound() {
		beeSound = MediaPlayer.create(context, R.raw.beecaught);
	}

	// ����ץ������Ч
	public void setCatchSound() {
		catchSound = MediaPlayer.create(context, R.raw.caught);
	}

	// ����������Ч
	public void setLightingSound() {
		lightingSound = MediaPlayer.create(context, R.raw.lightning);
	}

	// ��ñ�������
	public MediaPlayer getBackSound() {
		return backSound;
	}

	// ���ץ����������Ч
	public MediaPlayer getButterSound() {
		return butterSound;
	}

	// ���ץ���۷����Ч
	public MediaPlayer getBeeSound() {
		return beeSound;
	}

	// ���ץ������Ч
	public MediaPlayer getCatchSound() {
		return catchSound;
	}

	// ����������Ч
	public MediaPlayer getLightingSound() {
		return lightingSound;
	}

	// ���볡���󲥷Ŷ���������
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// Բ����ת����
		loading.setBackgroundResource(R.anim.wait_ani);
		wait_ani = (AnimationDrawable) loading.getBackground();
		System.out.println(123);
		wait_ani.start();
		super.onWindowFocusChanged(hasFocus);
	}

	// �����ֻ��ķ��ؼ��İ����¼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			butterflying.setPauseState(false);
		}
		return false;
	}
}
