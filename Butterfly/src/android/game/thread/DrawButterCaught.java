package android.game.thread;

import android.game.gameView.Butterflying;
import android.view.SurfaceHolder;

public class DrawButterCaught extends Thread {
	private final int sleep = 5;// ˯�ߵĺ�����
	private boolean flag = true;// ѭ�����λ
	private Butterflying butterfly = null;
	private SurfaceHolder surfaceHolder = null;
	private float xL = 0;
	private float yL = 0;
	private int butterId = 0;
	private int num0 = 3;
	private int num1 = 11;
	private int num2 = 11;
	private int num3 = 11;
	private int num4 = 11;
	private int num5 = 11;
	private int num6 = 9;
	private int num7 = 11;
	private int num8 = 11;
	private int num9 = 11;
	private int num10 = 11;
	private int gold = 0;

	public DrawButterCaught(Butterflying butterflying,
			SurfaceHolder surfaceHolder, float x, float y, int id) {// ������
		this.butterfly = butterflying;
		this.surfaceHolder = surfaceHolder;
		this.xL = x;
		this.yL = y;
		this.butterId = id;
		gold = butterfly.getGold(butterId);
	}

	@Override
	public void run() {
		while (flag) {
			synchronized (surfaceHolder) {
				// �õ���ǰ���� Ȼ������
				butterfly.setCanvas(surfaceHolder.lockCanvas());
				butterfly.DrawGoldGot(butterId, xL, yL, gold);
				switch (butterId) {
				case 0:
					butterfly.DrawButterfly(xL, yL, 0, num0);
					num0++;
					if (num0 > 5) {
						setFlag(false);
					}
					break;
				case 1:
					butterfly.DrawButterfly(xL, yL, 1, num1);
					num1++;
					if (num1 > 16) {
						setFlag(false);
					}
					break;
				case 2:
					butterfly.DrawButterfly(xL, yL, 2, num2);
					num2++;
					if (num2 > 16) {
						setFlag(false);
					}
					break;
				case 3:
					butterfly.DrawButterfly(xL, yL, 3, num3);
					num3++;
					if (num3 > 16) {
						setFlag(false);
					}
					break;
				case 4:
					butterfly.DrawButterfly(xL, yL, 4, num4);
					num4++;
					if (num4 > 16) {
						setFlag(false);
					}
					break;
				case 5:
					butterfly.DrawButterfly(xL, yL, 5, num5);
					num5++;
					if (num5 > 16) {
						setFlag(false);
					}
					break;
				case 6:
					butterfly.DrawButterfly(xL, yL, 6, num6);
					num6++;
					if (num6 > 13) {
						setFlag(false);
					}
					break;
				case 7:
					butterfly.DrawButterfly(xL, yL, 7, num7);
					num7++;
					if (num7 > 17) {
						setFlag(false);
					}
					break;
				case 8:
					butterfly.DrawButterfly(xL, yL, 8, num8);
					num8++;
					if (num8 > 17) {
						setFlag(false);
					}
					break;
				case 9:
					butterfly.DrawButterfly(xL, yL, 9, num9);
					num9++;
					if (num9 > 17) {
						setFlag(false);
					}
					break;
				case 10:
					butterfly.DrawButterfly(xL, yL, 10, num10);
					num10++;
					if (num10 > 17) {
						setFlag(false);
					}
					break;
				}
				// ���ƽ����������ʾ����Ļ��
				surfaceHolder.unlockCanvasAndPost(butterfly.getCanvas());
			}
			try {
				sleep(sleep);// ˯��sleep����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setFlag(boolean flag) {// ����ѭ�����
		this.flag = flag;
	}
}
