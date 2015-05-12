package android.game.thread;

import android.game.gameView.Butterflying;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
	private final int sleep = 80;// 睡眠的毫秒数
	private boolean flag = true;// 循环标记位
	private Butterflying butterfly;
	private SurfaceHolder surfaceHolder = null;

	public DrawThread(Butterflying butterflying, SurfaceHolder surfaceHolder) {// 构造器
		this.butterfly = butterflying;
		this.surfaceHolder = surfaceHolder;
	}

	@Override
	public void run() {
		while (flag) {
			if (!butterfly.getPauseState()) {
				// 在这里加上线程安全锁
				synchronized (surfaceHolder) {
					// 拿到当前画布 然后锁定
					butterfly.setCanvas(surfaceHolder.lockCanvas());
					butterfly.DrawBack();
					butterfly.drawFlying();
					butterfly.DrawWeb();
					butterfly.DrawCoinNum();
					// 绘制结束后解锁显示在屏幕上
					surfaceHolder.unlockCanvasAndPost(butterfly.getCanvas());
				}
			}
			try {
				sleep(sleep);// 睡眠sleep毫秒
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setFlag(boolean flag) {// 设置循环标记
		this.flag = flag;
	}
}
