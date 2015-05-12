package android.game.gameView;

import java.util.Random;

import android.content.Context;
import android.game.butterfly.R;
import android.game.param.ButterflyParam;
import android.game.param.LevelParam;
import android.game.thread.DrawButterCaught;
import android.game.thread.DrawThread;
import android.game.thread.RecordThread;
import android.game.util.RWSharedperferences;
import android.game.util.SetScaleImage;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Butterflying extends SurfaceView implements
		SurfaceHolder.Callback, SensorEventListener, Runnable {

	// 蝴蝶图片
	private Bitmap bee = null;// 蜜蜂
	private Bitmap butterfly1 = null;// 蝴蝶一
	private Bitmap butterfly2 = null;// 蝴蝶二
	private Bitmap butterfly3 = null;// 蝴蝶三
	private Bitmap butterfly4 = null;// 蝴蝶四
	private Bitmap butterfly5 = null;// 蝴蝶五
	private Bitmap butterfly6 = null;// 蝴蝶六
	private Bitmap butterfly7 = null;// 蝴蝶七
	private Bitmap butterfly8 = null;// 蝴蝶八
	private Bitmap butterfly9 = null;// 蝴蝶九
	private Bitmap butterfly10 = null;// 蝴蝶十

	// 帧计数
	private int count0 = 0;
	private int count1 = 1;
	private int count2 = 2;
	private int count3 = 3;
	private int count4 = 4;
	private int count5 = 5;
	private int count6 = 6;
	private int count7 = 7;
	private int count8 = 8;
	private int count9 = 9;
	private int count10 = 10;

	// 背景图片
	private Bitmap backPic = null;

	// 金币数量栏
	private Bitmap coinBar = null;

	// 捕蝶网
	private Bitmap web = null;
	private int webWidth = 0;
	private int webHeight = 0;

	// 金币数图片
	private Bitmap numPic = null;
	protected int numPicWidth = 0;
	protected int numPicHeight = 0;

	private SurfaceHolder sh = null;

	private ButterflyParam bp = null;

	protected LevelParam lp = null;

	// 游戏画笔
	private Paint paint = null;

	// 手机屏幕宽高
	private int screenWidth = 0;
	private int screenHeight = 0;

	// 游戏画布
	protected Canvas canvas = null;

	// SensorManager管理器
	private SensorManager mSensorMgr = null;
	private Sensor mSensor = null;

	// 网越界区域
	private int overX = 0;
	private int overY = 0;

	// 网的坐标位置
	private float xLocation = 200;
	private float yLocation = 0;

	// 重力感应X轴 Y轴 Z轴的重力值
	private float gX = 0;
	private float gY = 0;
	// private float gZ = 0;

	private DrawThread drawThread = null;
	private RecordThread recordThread = null;

	// 蝴蝶位置
	private float xL[] = new float[18];
	private float yL[] = new float[18];
	private int pathId[] = new int[18];
	private int butterId[] = new int[18];

	// 等级
	protected int level = 1;

	// 经验值
	protected int experience = 0;

	// 金币数
	private int goldNum = 0;

	// 道具数量
	protected int tool1num = 0;
	protected int tool2num = 0;
	protected int tool3num = 0;
	protected int tool4num = 0;
	protected int tool5num = 0;

	private boolean isPause = false;// 是否暂停游戏

	// 是否声控
	private boolean isSoundCtl = true;

	// 读写配置文件对象
	protected RWSharedperferences rws = null;

	private SetScaleImage ssi = null;

	private GameView gameView = null;

	public Butterflying(GameView gameView) {
		super(gameView);
		this.gameView = gameView;
	}

	public void run() {
		// 设置当前View拥有控制焦点
		this.setFocusable(true);
		// 设置当前View拥有触摸事件
		this.setFocusableInTouchMode(true);
		// 拿到SurfaceHolder对象
		sh = this.getHolder();
		// 将mSurfaceHolder添加到Callback回调函数中
		sh.addCallback(this);

		this.screenWidth = gameView.screenWidth;
		this.screenHeight = gameView.screenHeight;

		bp = new ButterflyParam();
		lp = new LevelParam();

		// 初始化
		init();
		initLocation();

		drawThread = new DrawThread(this, sh);
		recordThread = new RecordThread(this);

		/** 得到SensorManager对象 **/
		mSensorMgr = (SensorManager) gameView
				.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// 注册listener，第三个参数是检测的精确度
		// SENSOR_DELAY_FASTEST 最灵敏 因为太快了没必要使用
		// SENSOR_DELAY_GAME 游戏开发中使用
		// SENSOR_DELAY_NORMAL 正常速度
		// SENSOR_DELAY_UI 最慢的速度
		mSensorMgr.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_GAME);

		gameView.myHandler.sendEmptyMessage(0);// 向Activity的Handler发送消息
		System.out.println("sfgsdgdfghdghgfj");
	}

	// 初始化资源
	public void init() {
		ssi = new SetScaleImage();
		rws = new RWSharedperferences();// 初始化读写配置文件对象
		level = Integer.parseInt(rws.read(gameView, "level"));
		experience = Integer.parseInt(rws.read(gameView, "experience"));
		goldNum = Integer.parseInt(rws.read(gameView, "goldNum"));
		tool1num = Integer.parseInt(rws.read(gameView, "tool1num"));
		tool2num = Integer.parseInt(rws.read(gameView, "tool2num"));
		tool3num = Integer.parseInt(rws.read(gameView, "tool3num"));
		tool4num = Integer.parseInt(rws.read(gameView, "tool4num"));
		tool5num = Integer.parseInt(rws.read(gameView, "tool5num"));
		// scene = Integer.parseInt(rws.read(gameView, "scene"));
		isSoundCtl = gameView.isSoundControl;

		switch (gameView.scene) {
		case 1:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s1);// 背景图片1
			break;
		case 2:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s2);// 背景图片2
			break;
		case 3:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s3);// 背景图片3
			break;
		case 4:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s4);// 背景图片4
			break;
		case 5:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s5);// 背景图片5
			break;
		}
		backPic = ssi.getScaleImg(backPic, screenWidth, screenHeight);

		bee = BitmapFactory.decodeResource(this.getResources(), R.drawable.bee);// 蜜蜂
		butterfly1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly1);// 蝴蝶一
		butterfly2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly2);// 蝴蝶二
		butterfly3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly3);// 蝴蝶三
		butterfly4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly4);// 蝴蝶四
		butterfly5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly5);// 蝴蝶五
		butterfly6 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly6);// 蝴蝶六
		butterfly7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly7);// 蝴蝶七
		butterfly8 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly8);// 蝴蝶八
		butterfly9 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly9);// 蝴蝶九
		butterfly10 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly10);// 蝴蝶十

		web = BitmapFactory.decodeResource(this.getResources(), R.drawable.web);// 蝴蝶网
		numPic = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.num);// 金币数
		coinBar = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.coinback);// 金币栏

		webWidth = web.getWidth();
		webHeight = web.getHeight();

		numPicWidth = numPic.getWidth() / 12;
		numPicHeight = numPic.getHeight();

		coinBar = ssi.getScaleImg(coinBar, numPicWidth * 11, numPicHeight * 2);

		// 创建游戏画笔
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(screenWidth / 30);
		// 创建画布
		canvas = new Canvas();
	}

	// 绘制游戏背景
	public void DrawBack() {
		// 绘制游戏背景
		canvas.drawBitmap(backPic, 0, 0, paint);
		// 绘制网
		canvas.drawBitmap(web, xLocation, yLocation, paint);
	}

	// 绘制捕蝶网
	public void DrawWeb() {
		// 绘制网
		canvas.drawBitmap(web, xLocation, yLocation, paint);
	}

	// 绘制数字
	public void DrawFigure(int figure, int location) {
		Bitmap fig = Bitmap.createBitmap(numPic, numPicWidth * figure, 0,
				numPicWidth, numPicHeight);
		int startX = screenWidth / 2 - coinBar.getWidth() / 6;
		canvas.drawBitmap(fig, startX + numPicWidth * location, screenHeight
				- numPicHeight * 3 / 2, paint);
	}

	// 返回获得的金币数
	public int getGold(int id) {
		return bp.getPoint(id);
	}

	// 绘制获得的金币数
	public void DrawGoldGot(int id, float x, float y, int num) {
		float xL = x + bp.getButterWidth(id) / 2;
		if (num > 0) {
			Bitmap fig = Bitmap.createBitmap(numPic, numPicWidth * 10, 0,
					numPicWidth, numPicHeight);
			canvas.drawBitmap(fig, xL, y, paint);
			if (num == 100) {
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth, 0,
						numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
				Bitmap bit2 = Bitmap.createBitmap(numPic, 0, 0, numPicWidth,
						numPicHeight);
				canvas.drawBitmap(bit2, xL + numPicWidth * 2, y, paint);
				Bitmap bit3 = Bitmap.createBitmap(numPic, 0, 0, numPicWidth,
						numPicHeight);
				canvas.drawBitmap(bit3, xL + numPicWidth * 3, y, paint);
			} else if (num >= 10) {
				int num1 = num % 10;
				int num2 = num / 10;
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth * num2,
						0, numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
				Bitmap bit2 = Bitmap.createBitmap(numPic, numPicWidth * num1,
						0, numPicWidth, numPicHeight);
				canvas.drawBitmap(bit2, xL + numPicWidth * 2, y, paint);
			} else {
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth * num, 0,
						numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
			}
		} else {
			Bitmap fig = Bitmap.createBitmap(numPic, numPicWidth * 11, 0,
					numPicWidth, numPicHeight);
			canvas.drawBitmap(fig, xL, y, paint);
			if (num == -100) {
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth, 0,
						numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
				Bitmap bit2 = Bitmap.createBitmap(numPic, 0, 0, numPicWidth,
						numPicHeight);
				canvas.drawBitmap(bit2, xL + numPicWidth * 2, y, paint);
				Bitmap bit3 = Bitmap.createBitmap(numPic, 0, 0, numPicWidth,
						numPicHeight);
				canvas.drawBitmap(bit3, xL + numPicWidth * 3, y, paint);
			} else if (num <= -10) {
				int num1 = (-num) % 10;
				int num2 = (-num) / 10;
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth * num2,
						0, numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
				Bitmap bit2 = Bitmap.createBitmap(numPic, numPicWidth * num1,
						0, numPicWidth, numPicHeight);
				canvas.drawBitmap(bit2, xL + numPicWidth * 2, y, paint);
			} else {
				Bitmap bit1 = Bitmap.createBitmap(numPic, numPicWidth * (-num),
						0, numPicWidth, numPicHeight);
				canvas.drawBitmap(bit1, xL + numPicWidth, y, paint);
			}
		}
	}

	// 绘制金币数
	public void DrawCoinNum() {
		int no = goldNum;
		int figure[] = new int[6];
		for (int i = 0; i < 6; i++) {
			figure[i] = no % 10;
			no = no / 10;
		}
		canvas.drawBitmap(coinBar, screenWidth / 2 - coinBar.getWidth() / 2,
				screenHeight - coinBar.getHeight(), paint);
		for (int i = 0; i < 6; i++) {
			DrawFigure(figure[i], 5 - i);
		}
	}

	// 绘制蝴蝶
	public void DrawButterfly(float x, float y, int butterId) {
		Bitmap butterfly = null;
		switch (butterId) {
		case 0:
			int beeWidth = bp.getButterWidth(0);
			int beeHeight = bp.getButterHeight(0);
			butterfly = Bitmap.createBitmap(bee, 0, beeHeight * count0,
					beeWidth, beeHeight);
			canvas.drawBitmap(butterfly, x - beeWidth / 2, y - beeHeight / 2,
					paint);
			break;
		case 1:
			int butterWidth1 = bp.getButterWidth(1);
			int butterHeight1 = bp.getButterHeight(1);
			butterfly = Bitmap.createBitmap(butterfly1, 0, butterHeight1
					* count1, butterWidth1, butterHeight1);
			canvas.drawBitmap(butterfly, x - butterWidth1 / 2, y
					- butterHeight1 / 2, paint);
			break;
		case 2:
			int butterWidth2 = bp.getButterWidth(2);
			int butterHeight2 = bp.getButterHeight(2);
			butterfly = Bitmap.createBitmap(butterfly2, 0, butterHeight2
					* count2, butterWidth2, butterHeight2);
			canvas.drawBitmap(butterfly, x - butterWidth2 / 2, y
					- butterHeight2 / 2, paint);
			break;
		case 3:
			int butterWidth3 = bp.getButterWidth(3);
			int butterHeight3 = bp.getButterHeight(3);
			butterfly = Bitmap.createBitmap(butterfly3, 0, butterHeight3
					* count3, butterWidth3, butterHeight3);
			canvas.drawBitmap(butterfly, x - butterWidth3 / 2, y
					- butterHeight3 / 2, paint);
			break;
		case 4:
			int butterWidth4 = bp.getButterWidth(4);
			int butterHeight4 = bp.getButterHeight(4);
			butterfly = Bitmap.createBitmap(butterfly4, 0, butterHeight4
					* count4, butterWidth4, butterHeight4);
			canvas.drawBitmap(butterfly, x - butterWidth4 / 2, y
					- butterHeight4 / 2, paint);
			break;
		case 5:
			int butterWidth5 = bp.getButterWidth(5);
			int butterHeight5 = bp.getButterHeight(5);
			butterfly = Bitmap.createBitmap(butterfly5, 0, butterHeight5
					* count5, butterWidth5, butterHeight5);
			canvas.drawBitmap(butterfly, x - butterWidth5 / 2, y
					- butterHeight5 / 2, paint);
			break;
		case 6:
			int butterWidth6 = bp.getButterWidth(6);
			int butterHeight6 = bp.getButterHeight(6);
			butterfly = Bitmap.createBitmap(butterfly6, 0, butterHeight6
					* count6, butterWidth6, butterHeight6);
			canvas.drawBitmap(butterfly, x - butterWidth6 / 2, y
					- butterHeight6 / 2, paint);
			break;
		case 7:
			int butterWidth7 = bp.getButterWidth(7);
			int butterHeight7 = bp.getButterHeight(7);
			butterfly = Bitmap.createBitmap(butterfly7, 0, butterHeight7
					* count7, butterWidth7, butterHeight7);
			canvas.drawBitmap(butterfly, x - butterWidth7 / 2, y
					- butterHeight7 / 2, paint);
			break;
		case 8:
			int butterWidth8 = bp.getButterWidth(8);
			int butterHeight8 = bp.getButterHeight(8);
			butterfly = Bitmap.createBitmap(butterfly8, 0, butterHeight8
					* count8, butterWidth8, butterHeight8);
			canvas.drawBitmap(butterfly, x - butterWidth8 / 2, y
					- butterHeight8 / 2, paint);
			break;
		case 9:
			int butterWidth9 = bp.getButterWidth(9);
			int butterHeight9 = bp.getButterHeight(9);
			butterfly = Bitmap.createBitmap(butterfly9, 0, butterHeight9
					* count9, butterWidth9, butterHeight9);
			canvas.drawBitmap(butterfly, x - butterWidth9 / 2, y
					- butterHeight9 / 2, paint);
			break;
		case 10:
			int butterWidth10 = bp.getButterWidth(10);
			int butterHeight10 = bp.getButterHeight(10);
			butterfly = Bitmap.createBitmap(butterfly10, 0, butterHeight10
					* count10, butterWidth10, butterHeight10);
			canvas.drawBitmap(butterfly, x - butterWidth10 / 2, y
					- butterHeight10 / 2, paint);
			break;
		}
	}

	// 绘制蝴蝶(方法重写)
	public void DrawButterfly(float x, float y, int butterId, int num) {
		Bitmap butterfly = null;
		switch (butterId) {
		case 0:
			int beeWidth = bp.getButterWidth(0);
			int beeHeight = bp.getButterHeight(0);
			butterfly = Bitmap.createBitmap(bee, 0, beeHeight * num, beeWidth,
					beeHeight);
			canvas.drawBitmap(butterfly, x - beeWidth / 2, y - beeHeight / 2,
					paint);
			break;
		case 1:
			int butterWidth1 = bp.getButterWidth(1);
			int butterHeight1 = bp.getButterHeight(1);
			butterfly = Bitmap.createBitmap(butterfly1, 0, butterHeight1 * num,
					butterWidth1, butterHeight1);
			canvas.drawBitmap(butterfly, x - butterWidth1 / 2, y
					- butterHeight1 / 2, paint);
			break;
		case 2:
			int butterWidth2 = bp.getButterWidth(2);
			int butterHeight2 = bp.getButterHeight(2);
			butterfly = Bitmap.createBitmap(butterfly2, 0, butterHeight2 * num,
					butterWidth2, butterHeight2);
			canvas.drawBitmap(butterfly, x - butterWidth2 / 2, y
					- butterHeight2 / 2, paint);
			break;
		case 3:
			int butterWidth3 = bp.getButterWidth(3);
			int butterHeight3 = bp.getButterHeight(3);
			butterfly = Bitmap.createBitmap(butterfly3, 0, butterHeight3 * num,
					butterWidth3, butterHeight3);
			canvas.drawBitmap(butterfly, x - butterWidth3 / 2, y
					- butterHeight3 / 2, paint);
			break;
		case 4:
			int butterWidth4 = bp.getButterWidth(4);
			int butterHeight4 = bp.getButterHeight(4);
			butterfly = Bitmap.createBitmap(butterfly4, 0, butterHeight4 * num,
					butterWidth4, butterHeight4);
			canvas.drawBitmap(butterfly, x - butterWidth4 / 2, y
					- butterHeight4 / 2, paint);
			break;
		case 5:
			int butterWidth5 = bp.getButterWidth(5);
			int butterHeight5 = bp.getButterHeight(5);
			butterfly = Bitmap.createBitmap(butterfly5, 0, butterHeight5 * num,
					butterWidth5, butterHeight5);
			canvas.drawBitmap(butterfly, x - butterWidth5 / 2, y
					- butterHeight5 / 2, paint);
			break;
		case 6:
			int butterWidth6 = bp.getButterWidth(6);
			int butterHeight6 = bp.getButterHeight(6);
			butterfly = Bitmap.createBitmap(butterfly6, 0, butterHeight6 * num,
					butterWidth6, butterHeight6);
			canvas.drawBitmap(butterfly, x - butterWidth6 / 2, y
					- butterHeight6 / 2, paint);
			break;
		case 7:
			int butterWidth7 = bp.getButterWidth(7);
			int butterHeight7 = bp.getButterHeight(7);
			butterfly = Bitmap.createBitmap(butterfly7, 0, butterHeight7 * num,
					butterWidth7, butterHeight7);
			canvas.drawBitmap(butterfly, x - butterWidth7 / 2, y
					- butterHeight7 / 2, paint);
			break;
		case 8:
			int butterWidth8 = bp.getButterWidth(8);
			int butterHeight8 = bp.getButterHeight(8);
			butterfly = Bitmap.createBitmap(butterfly8, 0, butterHeight8 * num,
					butterWidth8, butterHeight8);
			canvas.drawBitmap(butterfly, x - butterWidth8 / 2, y
					- butterHeight8 / 2, paint);
			break;
		case 9:
			int butterWidth9 = bp.getButterWidth(9);
			int butterHeight9 = bp.getButterHeight(9);
			butterfly = Bitmap.createBitmap(butterfly9, 0, butterHeight9 * num,
					butterWidth9, butterHeight9);
			canvas.drawBitmap(butterfly, x - butterWidth9 / 2, y
					- butterHeight9 / 2, paint);
			break;
		case 10:
			int butterWidth10 = bp.getButterWidth(10);
			int butterHeight10 = bp.getButterHeight(10);
			butterfly = Bitmap.createBitmap(butterfly10, 0, butterHeight10
					* num, butterWidth10, butterHeight10);
			canvas.drawBitmap(butterfly, x - butterWidth10 / 2, y
					- butterHeight10 / 2, paint);
			break;
		}
	}

	// 计数
	public void count() {
		if (count0 < 3) {
			count0++;
		} else {
			count0 = 0;
		}
		if (count1 < 11) {
			count1++;
		} else {
			count1 = 0;
		}

		if (count2 < 11) {
			count2++;
		} else {
			count2 = 0;
		}

		if (count3 < 11) {
			count3++;
		} else {
			count3 = 0;
		}

		if (count4 < 11) {
			count4++;
		} else {
			count4 = 0;
		}

		if (count5 < 11) {
			count5++;
		} else {
			count5 = 0;
		}

		if (count6 < 9) {
			count6++;
		} else {
			count6 = 0;
		}

		if (count7 < 11) {
			count7++;
		} else {
			count7 = 0;
		}

		if (count8 < 11) {
			count8++;
		} else {
			count8 = 0;
		}

		if (count9 < 11) {
			count9++;
		} else {
			count9 = 0;
		}

		if (count10 < 11) {
			count10++;
		} else {
			count10 = 0;
		}
	}

	// 获得蝴蝶种类
	public int getButterId() {
		int butterTypeNum = lp.getButterTypeNum(level);
		int butterShowRate[] = new int[butterTypeNum];
		int sum = 0;
		int rangeS = 0;
		int rangeE = 0;
		for (int i = 0; i < butterTypeNum; i++) {
			butterShowRate[i] = bp.getButterShowRate(i);
			sum += butterShowRate[i];
		}
		Random rand = new Random();
		int random = rand.nextInt(sum) + 1;
		for (int i = 0; i < butterTypeNum; i++) {
			rangeS = rangeE;
			rangeE += butterShowRate[i];
			if (random > rangeS && random <= rangeE) {
				return i;
			}
		}
		return 0;
	}

	// 初始化蝴蝶位置,路径及种类
	public void initLocation() {
		for (int i = 0; i < 18; i++) {
			xL[i] = -100 - 100 * i;
			Random rand = new Random();
			pathId[i] = rand.nextInt(10) + 1;
			butterId[i] = getButterId();
		}
	}

	// 画飞行时的蝴蝶
	public void drawFlying() {
		count();
		for (int i = 0; i < lp.getButterNum(level); i++) {
			if (xL[i] < screenWidth + 100) {
				xL[i] = bp.getXPath(xL[i], butterId[i]);
				yL[i] = bp
						.getYPath(xL[i], screenWidth, screenHeight, pathId[i]);
				DrawButterfly(xL[i], yL[i], butterId[i]);
			} else {
				xL[i] = -100;
				yL[i] = bp
						.getYPath(xL[i], screenWidth, screenHeight, pathId[i]);
				butterId[i] = getButterId();
				DrawButterfly(xL[i], yL[i], butterId[i]);
			}
		}
	}

	// 更新经验值
	public void updateExp(int butterId) {
		experience += bp.getExp(butterId);
		if (experience >= lp.getGetMaxExperience(level)) {
			experience = experience - lp.getGetMaxExperience(level);
			level++;
			rws.write(gameView, "level", level + "");
			gameView.myHandler.sendEmptyMessage(7);// 向Activity的Handler发送消息
		}
		rws.write(gameView, "experience", experience + "");
		gameView.myHandler.sendEmptyMessage(8);// 向Activity的Handler发送消息
	}

	// 抓蝴蝶
	public void catchButter(float x, float y) {
		gameView.soundPlay.palyCatchSound(gameView.isSound);
		if (goldNum > 1) {
			goldNum -= 2;
			for (int i = 0; i < lp.getButterNum(level); i++) {
				if (x > xL[i] - bp.getButterWidth(butterId[i]) / 2
						&& x < xL[i] + bp.getButterWidth(butterId[i]) / 2
						&& y > yL[i] - bp.getButterHeight(butterId[i]) / 2
						&& y < yL[i] + bp.getButterHeight(butterId[i]) / 2) {
					if (isCaught(butterId[i])) {
						new DrawButterCaught(this, sh, xL[i], yL[i],
								butterId[i]).start();
						if (bp.getPoint(butterId[i]) > 0) {
							gameView.soundPlay
									.palyButterSound(gameView.isSound);
						} else {
							gameView.soundPlay.palyBeeSound(gameView.isSound);
						}
						goldNum += bp.getPoint(butterId[i]);
						if (goldNum < 0) {
							goldNum = 0;
						}
						if (goldNum > 999999) {
							goldNum = 999999;
						}
						updateExp(butterId[i]);
						xL[i] = -100;
						yL[i] = bp.getYPath(xL[i], screenWidth, screenHeight,
								pathId[i]);
						butterId[i] = getButterId();
					}
				}
			}
			rws.write(gameView, "goldNum", goldNum + "");
		}
	}

	// 杀死所有蜜蜂
	public void killBee() {
		gameView.soundPlay.palyLightingSound(gameView.isSound);
		bp.setButterPoints(0, 5);
		// goldNum-=2;
		for (int i = 0; i < lp.getButterNum(level); i++) {
			if (butterId[i] == 0 && xL[i] > 0 && xL[i] < screenWidth) {
				new DrawButterCaught(this, sh, xL[i], yL[i], butterId[i])
						.start();
				goldNum += 5;
				xL[i] = -100;
				yL[i] = bp
						.getYPath(xL[i], screenWidth, screenHeight, pathId[i]);
				butterId[i] = getButterId();
			}
		}
		bp.setButterPoints(0, -10);
		rws.write(gameView, "goldNum", goldNum + "");
	}

	// 抓捕屏幕上所有飞行物
	public void catchAll() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		// goldNum-=2;
		for (int i = 0; i < lp.getButterNum(level); i++) {
			if (xL[i] > 0 && xL[i] < screenWidth) {
				new DrawButterCaught(this, sh, xL[i], yL[i], butterId[i])
						.start();
				goldNum += bp.getPoint(butterId[i]);
				if (goldNum < 0) {
					goldNum = 0;
				}
				if (goldNum > 999999) {
					goldNum = 999999;
				}
				updateExp(butterId[i]);
				xL[i] = -100 - 100 * i;
				yL[i] = bp
						.getYPath(xL[i], screenWidth, screenHeight, pathId[i]);
				butterId[i] = getButterId();
			}
		}
		rws.write(gameView, "goldNum", goldNum + "");
	}

	// 增加飞行物的捕捉概率
	public void incRate() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 0; i < 11; i++) {
			bp.setButterRate(i, 90);
		}
	}

	// 恢复飞行物的捕捉概率
	public void initRate() {
		bp.initButterRate();
	}

	// 降低飞行物的飞行速度
	public void decSpeed() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 0; i < 11; i++) {
			bp.setButterSpeed(i, 2);
		}
	}

	// 恢复飞行物的飞行速度
	public void initSpeed() {
		bp.initButterSpeed();
	}

	// 设置双倍经验值和蝴蝶价值
	public void setDouble() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 1; i < 11; i++) {
			bp.setButterExp(i, 2 * bp.getExp(i));
			bp.setButterPoints(i, 2 * bp.getPoint(i));
		}
	}

	// 恢复经验值和蝴蝶价值
	public void setSingle() {
		for (int i = 1; i < 11; i++) {
			bp.setButterExp(i, bp.getExp(i) / 2);
			bp.setButterPoints(i, bp.getPoint(i) / 2);
		}
	}

	// 判断蝴蝶是否被抓住
	public boolean isCaught(int id) {
		Random rand = new Random();
		int random = rand.nextInt(100) + 1;
		System.out.println("random:" + random);
		boolean bool = false;
		switch (id) {
		case 0:
			if (random <= bp.getButterRate(0)) {
				bool = true;
			}
			break;
		case 1:
			if (random <= bp.getButterRate(1)) {
				bool = true;
			}
			break;
		case 2:
			if (random <= bp.getButterRate(2)) {
				bool = true;
			}
			break;
		case 3:
			if (random <= bp.getButterRate(3)) {
				bool = true;
			}
			break;
		case 4:
			if (random <= bp.getButterRate(4)) {
				bool = true;
			}
			break;
		case 5:
			if (random <= bp.getButterRate(5)) {
				bool = true;
			}
			break;
		case 6:
			if (random <= bp.getButterRate(6)) {
				bool = true;
			}
			break;
		case 7:
			if (random <= bp.getButterRate(7)) {
				bool = true;
			}
			break;
		case 8:
			if (random <= bp.getButterRate(8)) {
				bool = true;
			}
			break;
		case 9:
			if (random <= bp.getButterRate(9)) {
				bool = true;
			}
			break;
		case 10:
			if (random <= bp.getButterRate(10)) {
				bool = true;
			}
			break;
		}
		return bool;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	// 获得网的宽度
	public int getWebWidth() {
		return webWidth;
	}

	// 获得网的高度
	public int getWebHeight() {
		return webHeight;
	}

	// 获得网的横坐标
	public float getWebX() {
		return xLocation;
	}

	// 获得网的纵坐标
	public float getWebY() {
		return yLocation;
	}

	// 获得暂停状态
	public boolean getPauseState() {
		return isPause;
	}

	// 设置暂停状态
	public void setPauseState(boolean bool) {
		isPause = bool;
	}

	// 获得画布
	public Canvas getCanvas() {
		return canvas;
	}

	// 设置画布
	public void setCanvas(Canvas can) {
		canvas = can;
	}

	// 返回声控标志
	public boolean getIsSoundCtl() {
		return isSoundCtl;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 游戏主循环线程
		drawThread.start();
		recordThread.start();

		// 得到网越界区域
		overX = screenWidth - webWidth;
		overY = screenHeight - webHeight;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawThread.setFlag(false);
		recordThread.pause();
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		gX = event.values[SensorManager.DATA_X];
		gY = event.values[SensorManager.DATA_Y];
		// gZ = event.values[SensorManager.DATA_Z];

		// 这里乘以2是为了让捕蝶网移动的更快
		xLocation += gY * 2;
		yLocation += gX * 2;

		// 检测捕蝶网是否超出边界
		if (xLocation < 0) {
			xLocation = 0;
		} else if (xLocation > overX) {
			xLocation = overX;
		}
		if (yLocation < 0) {
			yLocation = 0;
		} else if (yLocation > overY) {
			yLocation = overY;
		}
	}
}
