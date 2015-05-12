package android.game.thread;

import android.game.gameView.Butterflying;

public class DecSpeedThread extends Thread {

	// 30s��ʱ
	private final int TIME_IN_FRAME = 30000;
	private Butterflying butterflying = null;

	public DecSpeedThread(Butterflying butterflying) {
		this.butterflying = butterflying;
	}

	@Override
	public void run() {
		butterflying.decSpeed();
		// ��ʼ��ʱ��ʱ��
		long startTime = System.currentTimeMillis();

		// ��ǰʱ��
		long endTime = System.currentTimeMillis();

		// ʱ���
		int diffTime = (int) (endTime - startTime);

		while (diffTime <= TIME_IN_FRAME) {
			diffTime = (int) (System.currentTimeMillis() - startTime);
			// �̵߳ȴ�
			Thread.yield();
		}
		butterflying.initSpeed();
	}
}
