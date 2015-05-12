package android.game.thread;

import android.game.gameView.Butterflying;

public class IncRateThread extends Thread {

	// 30s计时
	private final int TIME_IN_FRAME = 30000;
	private Butterflying butterflying = null;

	public IncRateThread(Butterflying butterflying) {
		this.butterflying = butterflying;
	}

	@Override
	public void run() {
		butterflying.incRate();
		// 开始计时的时间
		long startTime = System.currentTimeMillis();

		// 当前时间
		long endTime = System.currentTimeMillis();

		// 时间差
		int diffTime = (int) (endTime - startTime);

		while (diffTime <= TIME_IN_FRAME) {
			diffTime = (int) (System.currentTimeMillis() - startTime);
			// 线程等待
			Thread.yield();
		}
		butterflying.initRate();
	}
}
