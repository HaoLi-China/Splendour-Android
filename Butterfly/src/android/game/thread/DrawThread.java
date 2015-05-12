package android.game.thread;

import android.game.gameView.Butterflying;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
	private final int sleep = 80;// ˯�ߵĺ�����
	private boolean flag = true;// ѭ�����λ
	private Butterflying butterfly;
	private SurfaceHolder surfaceHolder = null;

	public DrawThread(Butterflying butterflying, SurfaceHolder surfaceHolder) {// ������
		this.butterfly = butterflying;
		this.surfaceHolder = surfaceHolder;
	}

	@Override
	public void run() {
		while (flag) {
			if (!butterfly.getPauseState()) {
				// ����������̰߳�ȫ��
				synchronized (surfaceHolder) {
					// �õ���ǰ���� Ȼ������
					butterfly.setCanvas(surfaceHolder.lockCanvas());
					butterfly.DrawBack();
					butterfly.drawFlying();
					butterfly.DrawWeb();
					butterfly.DrawCoinNum();
					// ���ƽ����������ʾ����Ļ��
					surfaceHolder.unlockCanvasAndPost(butterfly.getCanvas());
				}
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
