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

	// ����ͼƬ
	private Bitmap bee = null;// �۷�
	private Bitmap butterfly1 = null;// ����һ
	private Bitmap butterfly2 = null;// ������
	private Bitmap butterfly3 = null;// ������
	private Bitmap butterfly4 = null;// ������
	private Bitmap butterfly5 = null;// ������
	private Bitmap butterfly6 = null;// ������
	private Bitmap butterfly7 = null;// ������
	private Bitmap butterfly8 = null;// ������
	private Bitmap butterfly9 = null;// ������
	private Bitmap butterfly10 = null;// ����ʮ

	// ֡����
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

	// ����ͼƬ
	private Bitmap backPic = null;

	// ���������
	private Bitmap coinBar = null;

	// ������
	private Bitmap web = null;
	private int webWidth = 0;
	private int webHeight = 0;

	// �����ͼƬ
	private Bitmap numPic = null;
	protected int numPicWidth = 0;
	protected int numPicHeight = 0;

	private SurfaceHolder sh = null;

	private ButterflyParam bp = null;

	protected LevelParam lp = null;

	// ��Ϸ����
	private Paint paint = null;

	// �ֻ���Ļ���
	private int screenWidth = 0;
	private int screenHeight = 0;

	// ��Ϸ����
	protected Canvas canvas = null;

	// SensorManager������
	private SensorManager mSensorMgr = null;
	private Sensor mSensor = null;

	// ��Խ������
	private int overX = 0;
	private int overY = 0;

	// ��������λ��
	private float xLocation = 200;
	private float yLocation = 0;

	// ������ӦX�� Y�� Z�������ֵ
	private float gX = 0;
	private float gY = 0;
	// private float gZ = 0;

	private DrawThread drawThread = null;
	private RecordThread recordThread = null;

	// ����λ��
	private float xL[] = new float[18];
	private float yL[] = new float[18];
	private int pathId[] = new int[18];
	private int butterId[] = new int[18];

	// �ȼ�
	protected int level = 1;

	// ����ֵ
	protected int experience = 0;

	// �����
	private int goldNum = 0;

	// ��������
	protected int tool1num = 0;
	protected int tool2num = 0;
	protected int tool3num = 0;
	protected int tool4num = 0;
	protected int tool5num = 0;

	private boolean isPause = false;// �Ƿ���ͣ��Ϸ

	// �Ƿ�����
	private boolean isSoundCtl = true;

	// ��д�����ļ�����
	protected RWSharedperferences rws = null;

	private SetScaleImage ssi = null;

	private GameView gameView = null;

	public Butterflying(GameView gameView) {
		super(gameView);
		this.gameView = gameView;
	}

	public void run() {
		// ���õ�ǰViewӵ�п��ƽ���
		this.setFocusable(true);
		// ���õ�ǰViewӵ�д����¼�
		this.setFocusableInTouchMode(true);
		// �õ�SurfaceHolder����
		sh = this.getHolder();
		// ��mSurfaceHolder��ӵ�Callback�ص�������
		sh.addCallback(this);

		this.screenWidth = gameView.screenWidth;
		this.screenHeight = gameView.screenHeight;

		bp = new ButterflyParam();
		lp = new LevelParam();

		// ��ʼ��
		init();
		initLocation();

		drawThread = new DrawThread(this, sh);
		recordThread = new RecordThread(this);

		/** �õ�SensorManager���� **/
		mSensorMgr = (SensorManager) gameView
				.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// ע��listener�������������Ǽ��ľ�ȷ��
		// SENSOR_DELAY_FASTEST ������ ��Ϊ̫����û��Ҫʹ��
		// SENSOR_DELAY_GAME ��Ϸ������ʹ��
		// SENSOR_DELAY_NORMAL �����ٶ�
		// SENSOR_DELAY_UI �������ٶ�
		mSensorMgr.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_GAME);

		gameView.myHandler.sendEmptyMessage(0);// ��Activity��Handler������Ϣ
		System.out.println("sfgsdgdfghdghgfj");
	}

	// ��ʼ����Դ
	public void init() {
		ssi = new SetScaleImage();
		rws = new RWSharedperferences();// ��ʼ����д�����ļ�����
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
					R.drawable.s1);// ����ͼƬ1
			break;
		case 2:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s2);// ����ͼƬ2
			break;
		case 3:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s3);// ����ͼƬ3
			break;
		case 4:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s4);// ����ͼƬ4
			break;
		case 5:
			backPic = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.s5);// ����ͼƬ5
			break;
		}
		backPic = ssi.getScaleImg(backPic, screenWidth, screenHeight);

		bee = BitmapFactory.decodeResource(this.getResources(), R.drawable.bee);// �۷�
		butterfly1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly1);// ����һ
		butterfly2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly2);// ������
		butterfly3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly3);// ������
		butterfly4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly4);// ������
		butterfly5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly5);// ������
		butterfly6 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly6);// ������
		butterfly7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly7);// ������
		butterfly8 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly8);// ������
		butterfly9 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly9);// ������
		butterfly10 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.butterfly10);// ����ʮ

		web = BitmapFactory.decodeResource(this.getResources(), R.drawable.web);// ������
		numPic = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.num);// �����
		coinBar = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.coinback);// �����

		webWidth = web.getWidth();
		webHeight = web.getHeight();

		numPicWidth = numPic.getWidth() / 12;
		numPicHeight = numPic.getHeight();

		coinBar = ssi.getScaleImg(coinBar, numPicWidth * 11, numPicHeight * 2);

		// ������Ϸ����
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(screenWidth / 30);
		// ��������
		canvas = new Canvas();
	}

	// ������Ϸ����
	public void DrawBack() {
		// ������Ϸ����
		canvas.drawBitmap(backPic, 0, 0, paint);
		// ������
		canvas.drawBitmap(web, xLocation, yLocation, paint);
	}

	// ���Ʋ�����
	public void DrawWeb() {
		// ������
		canvas.drawBitmap(web, xLocation, yLocation, paint);
	}

	// ��������
	public void DrawFigure(int figure, int location) {
		Bitmap fig = Bitmap.createBitmap(numPic, numPicWidth * figure, 0,
				numPicWidth, numPicHeight);
		int startX = screenWidth / 2 - coinBar.getWidth() / 6;
		canvas.drawBitmap(fig, startX + numPicWidth * location, screenHeight
				- numPicHeight * 3 / 2, paint);
	}

	// ���ػ�õĽ����
	public int getGold(int id) {
		return bp.getPoint(id);
	}

	// ���ƻ�õĽ����
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

	// ���ƽ����
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

	// ���ƺ���
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

	// ���ƺ���(������д)
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

	// ����
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

	// ��ú�������
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

	// ��ʼ������λ��,·��������
	public void initLocation() {
		for (int i = 0; i < 18; i++) {
			xL[i] = -100 - 100 * i;
			Random rand = new Random();
			pathId[i] = rand.nextInt(10) + 1;
			butterId[i] = getButterId();
		}
	}

	// ������ʱ�ĺ���
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

	// ���¾���ֵ
	public void updateExp(int butterId) {
		experience += bp.getExp(butterId);
		if (experience >= lp.getGetMaxExperience(level)) {
			experience = experience - lp.getGetMaxExperience(level);
			level++;
			rws.write(gameView, "level", level + "");
			gameView.myHandler.sendEmptyMessage(7);// ��Activity��Handler������Ϣ
		}
		rws.write(gameView, "experience", experience + "");
		gameView.myHandler.sendEmptyMessage(8);// ��Activity��Handler������Ϣ
	}

	// ץ����
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

	// ɱ�������۷�
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

	// ץ����Ļ�����з�����
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

	// ���ӷ�����Ĳ�׽����
	public void incRate() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 0; i < 11; i++) {
			bp.setButterRate(i, 90);
		}
	}

	// �ָ�������Ĳ�׽����
	public void initRate() {
		bp.initButterRate();
	}

	// ���ͷ�����ķ����ٶ�
	public void decSpeed() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 0; i < 11; i++) {
			bp.setButterSpeed(i, 2);
		}
	}

	// �ָ�������ķ����ٶ�
	public void initSpeed() {
		bp.initButterSpeed();
	}

	// ����˫������ֵ�ͺ�����ֵ
	public void setDouble() {
		gameView.soundPlay.palyButterSound(gameView.isSound);
		for (int i = 1; i < 11; i++) {
			bp.setButterExp(i, 2 * bp.getExp(i));
			bp.setButterPoints(i, 2 * bp.getPoint(i));
		}
	}

	// �ָ�����ֵ�ͺ�����ֵ
	public void setSingle() {
		for (int i = 1; i < 11; i++) {
			bp.setButterExp(i, bp.getExp(i) / 2);
			bp.setButterPoints(i, bp.getPoint(i) / 2);
		}
	}

	// �жϺ����Ƿ�ץס
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

	// ������Ŀ��
	public int getWebWidth() {
		return webWidth;
	}

	// ������ĸ߶�
	public int getWebHeight() {
		return webHeight;
	}

	// ������ĺ�����
	public float getWebX() {
		return xLocation;
	}

	// �������������
	public float getWebY() {
		return yLocation;
	}

	// �����ͣ״̬
	public boolean getPauseState() {
		return isPause;
	}

	// ������ͣ״̬
	public void setPauseState(boolean bool) {
		isPause = bool;
	}

	// ��û���
	public Canvas getCanvas() {
		return canvas;
	}

	// ���û���
	public void setCanvas(Canvas can) {
		canvas = can;
	}

	// �������ر�־
	public boolean getIsSoundCtl() {
		return isSoundCtl;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// ��Ϸ��ѭ���߳�
		drawThread.start();
		recordThread.start();

		// �õ���Խ������
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

		// �������2��Ϊ���ò������ƶ��ĸ���
		xLocation += gY * 2;
		yLocation += gX * 2;

		// ��Ⲷ�����Ƿ񳬳��߽�
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
