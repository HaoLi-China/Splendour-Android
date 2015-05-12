package android.game.gameView;

import android.game.butterfly.R;
import android.game.util.MyButton;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class WidgetView implements Runnable {
	private GameView gameView = null;

	// 道具按钮
	private Button tool1 = null;
	private Button tool2 = null;
	private Button tool3 = null;
	private Button tool4 = null;
	private Button tool5 = null;

	private Button pauseButton = null;// 暂停按钮
	private Button musicButton = null;// 音乐按钮
	private Button soundButton = null;// 音效按钮
	private Button catchButton = null;// 捕捉按钮

	// 经验栏
	private ImageView experienceBar = null;
	private ImageView experienceTool = null;

	// 道具数量背景
	private ImageView tool1NumBg = null;
	private ImageView tool2NumBg = null;
	private ImageView tool3NumBg = null;
	private ImageView tool4NumBg = null;
	private ImageView tool5NumBg = null;

	// 道具背景
	private ImageView tool1Bg = null;
	private ImageView tool2Bg = null;
	private ImageView tool3Bg = null;
	private ImageView tool4Bg = null;
	private ImageView tool5Bg = null;

	// 经验值
	private TextView textView = null;

	// 道具数
	private TextView tool1NumView = null;
	private TextView tool2NumView = null;
	private TextView tool3NumView = null;
	private TextView tool4NumView = null;
	private TextView tool5NumView = null;

	// 绝对布局参数
	private AbsoluteLayout.LayoutParams params1 = null;
	private AbsoluteLayout.LayoutParams params2 = null;
	private AbsoluteLayout.LayoutParams params3 = null;
	private AbsoluteLayout.LayoutParams params4 = null;
	private AbsoluteLayout.LayoutParams params5 = null;
	private AbsoluteLayout.LayoutParams params6 = null;
	private AbsoluteLayout.LayoutParams params7 = null;
	private AbsoluteLayout.LayoutParams params8 = null;
	private AbsoluteLayout.LayoutParams params9 = null;
	private AbsoluteLayout.LayoutParams params10 = null;
	private AbsoluteLayout.LayoutParams params11 = null;
	private AbsoluteLayout.LayoutParams params12 = null;
	private AbsoluteLayout.LayoutParams params13 = null;
	private AbsoluteLayout.LayoutParams params14 = null;
	private AbsoluteLayout.LayoutParams params15 = null;
	private AbsoluteLayout.LayoutParams params16 = null;
	private AbsoluteLayout.LayoutParams params17 = null;
	private AbsoluteLayout.LayoutParams params18 = null;
	private AbsoluteLayout.LayoutParams params19 = null;
	private AbsoluteLayout.LayoutParams params20 = null;
	private AbsoluteLayout.LayoutParams params21 = null;
	private int screenWidth;
	private int screenHeight;

	// 经验值进度条初始和最终位置
	private int expProcessS = 0;
	private int expProcessE = 0;

	// 构造器
	public WidgetView(GameView gameView) {
		this.gameView = gameView;
		this.screenWidth = gameView.screenWidth;
		this.screenHeight = gameView.screenHeight;
	}

	// 线程方法
	public void run() {
		init();

		gameView.myHandler.sendEmptyMessage(1);// 向Activity的Handler发送消息
	}

	// 按钮初始化
	public void init() {
		expProcessS = screenWidth - screenHeight * 39 / 64;
		expProcessE = screenWidth - screenHeight * 21 / 64;

		// 按钮图片数组
		Integer[] tool1_button = { R.drawable.tool1_button1,
				R.drawable.tool1_button2, R.drawable.tool1_button2 };
		Integer[] tool2_button = { R.drawable.tool2_button1,
				R.drawable.tool2_button2, R.drawable.tool2_button2 };
		Integer[] tool3_button = { R.drawable.tool3_button1,
				R.drawable.tool3_button2, R.drawable.tool3_button2 };
		Integer[] tool4_button = { R.drawable.tool4_button1,
				R.drawable.tool4_button2, R.drawable.tool4_button2 };
		Integer[] tool5_button = { R.drawable.tool5_button1,
				R.drawable.tool5_button2, R.drawable.tool5_button2 };

		Integer[] catch_button = { R.drawable.catch1, R.drawable.catch2,
				R.drawable.catch2 };

		Integer[] pause_button = { R.drawable.pause1, R.drawable.pause2,
				R.drawable.pause2 };

		tool1 = new Button(gameView);
		tool2 = new Button(gameView);
		tool3 = new Button(gameView);
		tool4 = new Button(gameView);
		tool5 = new Button(gameView);

		pauseButton = new Button(gameView);
		musicButton = new Button(gameView);
		soundButton = new Button(gameView);
		catchButton = new Button(gameView);

		textView = new TextView(gameView);
		tool1NumView = new TextView(gameView);
		tool2NumView = new TextView(gameView);
		tool3NumView = new TextView(gameView);
		tool4NumView = new TextView(gameView);
		tool5NumView = new TextView(gameView);

		MyButton myTool1 = new MyButton(gameView);
		tool1.setBackgroundDrawable(myTool1.setbg(tool1_button));
		MyButton myTool2 = new MyButton(gameView);
		tool2.setBackgroundDrawable(myTool2.setbg(tool2_button));
		MyButton myTool3 = new MyButton(gameView);
		tool3.setBackgroundDrawable(myTool3.setbg(tool3_button));
		MyButton myTool4 = new MyButton(gameView);
		tool4.setBackgroundDrawable(myTool4.setbg(tool4_button));
		MyButton myTool5 = new MyButton(gameView);
		tool5.setBackgroundDrawable(myTool5.setbg(tool5_button));

		MyButton myCatchButton = new MyButton(gameView);
		catchButton.setBackgroundDrawable(myCatchButton.setbg(catch_button));

		MyButton myPauseButton = new MyButton(gameView);
		pauseButton.setBackgroundDrawable(myPauseButton.setbg(pause_button));

		// 事件监听
		tool1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(2);// 向Activity的Handler发送消息
			}
		});
		tool2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(3);// 向Activity的Handler发送消息
			}
		});
		tool3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(4);// 向Activity的Handler发送消息
			}
		});
		tool4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(5);// 向Activity的Handler发送消息
			}
		});
		tool5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(6);// 向Activity的Handler发送消息
			}
		});
		catchButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(9);// 向Activity的Handler发送消息
			}
		});
		pauseButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(10);// 向Activity的Handler发送消息
			}
		});
		musicButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(11);// 向Activity的Handler发送消息
			}
		});
		soundButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				gameView.myHandler.sendEmptyMessage(12);// 向Activity的Handler发送消息
			}
		});

		experienceBar = new ImageView(gameView);
		experienceBar.setBackgroundResource(R.drawable.experiencebar);

		experienceTool = new ImageView(gameView);
		experienceTool.setBackgroundResource(R.drawable.experiencetool);

		tool1NumBg = new ImageView(gameView);
		tool1NumBg.setBackgroundResource(R.drawable.toolnumbg);

		tool2NumBg = new ImageView(gameView);
		tool2NumBg.setBackgroundResource(R.drawable.toolnumbg);

		tool3NumBg = new ImageView(gameView);
		tool3NumBg.setBackgroundResource(R.drawable.toolnumbg);

		tool4NumBg = new ImageView(gameView);
		tool4NumBg.setBackgroundResource(R.drawable.toolnumbg);

		tool5NumBg = new ImageView(gameView);
		tool5NumBg.setBackgroundResource(R.drawable.toolnumbg);

		tool1Bg = new ImageView(gameView);
		tool1Bg.setBackgroundResource(R.drawable.toolback);

		tool2Bg = new ImageView(gameView);
		tool2Bg.setBackgroundResource(R.drawable.toolback);

		tool3Bg = new ImageView(gameView);
		tool3Bg.setBackgroundResource(R.drawable.toolback);

		tool4Bg = new ImageView(gameView);
		tool4Bg.setBackgroundResource(R.drawable.toolback);

		tool5Bg = new ImageView(gameView);
		tool5Bg.setBackgroundResource(R.drawable.toolback);

		params1 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight / 9,
				screenHeight * 3 / 9);
		params2 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight / 9,
				screenHeight * 4 / 9);
		params3 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight / 9,
				screenHeight * 5 / 9);
		params4 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight / 9,
				screenHeight * 6 / 9);
		params5 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight / 9,
				screenHeight * 7 / 9);
		params6 = new AbsoluteLayout.LayoutParams(screenHeight * 2 / 3,
				screenHeight * 2 / 9, screenWidth - screenHeight * 2 / 3, 0);
		params7 = new AbsoluteLayout.LayoutParams(screenHeight / 5,
				screenHeight / 5, 0, screenHeight * 4 / 5);
		params8 = new AbsoluteLayout.LayoutParams(screenHeight / 8,
				screenHeight / 8, screenHeight / 6, screenHeight / 48);
		params9 = new AbsoluteLayout.LayoutParams(screenHeight / 8,
				screenHeight / 8, screenHeight / 3, screenHeight / 48);
		params10 = new AbsoluteLayout.LayoutParams(screenHeight / 8,
				screenHeight / 8, screenHeight / 2, screenHeight / 48);
		params11 = new AbsoluteLayout.LayoutParams(screenHeight / 18,
				screenHeight / 18, screenWidth - screenHeight * 31 / 128,
				screenHeight / 8);

		params12 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight * 15 / 72,
				screenHeight * 25 / 72);
		params13 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight * 15 / 72,
				screenHeight * 33 / 72);
		params14 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight * 15 / 72,
				screenHeight * 41 / 72);
		params15 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight * 15 / 72,
				screenHeight * 49 / 72);
		params16 = new AbsoluteLayout.LayoutParams(screenHeight / 9,
				screenHeight / 9, screenWidth - screenHeight * 15 / 72,
				screenHeight * 57 / 72);

		params17 = new AbsoluteLayout.LayoutParams(screenHeight / 12,
				screenHeight / 12, screenWidth - screenHeight * 2 / 9,
				screenHeight * 3 / 9);
		params18 = new AbsoluteLayout.LayoutParams(screenHeight / 12,
				screenHeight / 12, screenWidth - screenHeight * 2 / 9,
				screenHeight * 4 / 9);
		params19 = new AbsoluteLayout.LayoutParams(screenHeight / 12,
				screenHeight / 12, screenWidth - screenHeight * 2 / 9,
				screenHeight * 5 / 9);
		params20 = new AbsoluteLayout.LayoutParams(screenHeight / 12,
				screenHeight / 12, screenWidth - screenHeight * 2 / 9,
				screenHeight * 6 / 9);
		params21 = new AbsoluteLayout.LayoutParams(screenHeight / 12,
				screenHeight / 12, screenWidth - screenHeight * 2 / 9,
				screenHeight * 7 / 9);

		/*
		 * params7 = new AbsoluteLayout.LayoutParams(screenHeight*2/45,
		 * screenHeight*2/45, screenWidth-screenHeight*21/64, screenHeight/12);
		 */
	}

	public int getExpS() {
		return expProcessS;
	}

	public int getExpE() {
		return expProcessE;
	}

	public Button getTool1() {
		return tool1;
	}

	public Button getTool2() {
		return tool2;
	}

	public Button getTool3() {
		return tool3;
	}

	public Button getTool4() {
		return tool4;
	}

	public Button getTool5() {
		return tool5;
	}

	public Button getCatchButton() {
		return catchButton;
	}

	public Button getMusicButton() {
		return musicButton;
	}

	public Button getSoundButton() {
		return soundButton;
	}

	public Button getPauseButton() {
		return pauseButton;
	}

	public ImageView getExperienceBar() {
		return experienceBar;
	}

	public ImageView getExperienceTool() {
		return experienceTool;
	}

	public ImageView getTool1NumBg() {
		return tool1NumBg;
	}

	public ImageView getTool2NumBg() {
		return tool2NumBg;
	}

	public ImageView getTool3NumBg() {
		return tool3NumBg;
	}

	public ImageView getTool4NumBg() {
		return tool4NumBg;
	}

	public ImageView getTool5NumBg() {
		return tool5NumBg;
	}

	public ImageView getTool1Bg() {
		return tool1Bg;
	}

	public ImageView getTool2Bg() {
		return tool2Bg;
	}

	public ImageView getTool3Bg() {
		return tool3Bg;
	}

	public ImageView getTool4Bg() {
		return tool4Bg;
	}

	public ImageView getTool5Bg() {
		return tool5Bg;
	}

	public TextView getTextView() {
		return textView;
	}

	public TextView getTool1NumView() {
		return tool1NumView;
	}

	public TextView getTool2NumView() {
		return tool2NumView;
	}

	public TextView getTool3NumView() {
		return tool3NumView;
	}

	public TextView getTool4NumView() {
		return tool4NumView;
	}

	public TextView getTool5NumView() {
		return tool5NumView;
	}

	public AbsoluteLayout.LayoutParams getParams1() {
		return params1;
	}

	public AbsoluteLayout.LayoutParams getParams2() {
		return params2;
	}

	public AbsoluteLayout.LayoutParams getParams3() {
		return params3;
	}

	public AbsoluteLayout.LayoutParams getParams4() {
		return params4;
	}

	public AbsoluteLayout.LayoutParams getParams5() {
		return params5;
	}

	public AbsoluteLayout.LayoutParams getParams6() {
		return params6;
	}

	public AbsoluteLayout.LayoutParams getParams7() {
		return params7;
	}

	public AbsoluteLayout.LayoutParams getParams8() {
		return params8;
	}

	public AbsoluteLayout.LayoutParams getParams9() {
		return params9;
	}

	public AbsoluteLayout.LayoutParams getParams10() {
		return params10;
	}

	public AbsoluteLayout.LayoutParams getParams11() {
		return params11;
	}

	public AbsoluteLayout.LayoutParams getParams12() {
		return params12;
	}

	public AbsoluteLayout.LayoutParams getParams13() {
		return params13;
	}

	public AbsoluteLayout.LayoutParams getParams14() {
		return params14;
	}

	public AbsoluteLayout.LayoutParams getParams15() {
		return params15;
	}

	public AbsoluteLayout.LayoutParams getParams16() {
		return params16;
	}

	public AbsoluteLayout.LayoutParams getParams17() {
		return params17;
	}

	public AbsoluteLayout.LayoutParams getParams18() {
		return params18;
	}

	public AbsoluteLayout.LayoutParams getParams19() {
		return params19;
	}

	public AbsoluteLayout.LayoutParams getParams20() {
		return params20;
	}

	public AbsoluteLayout.LayoutParams getParams21() {
		return params21;
	}
}
