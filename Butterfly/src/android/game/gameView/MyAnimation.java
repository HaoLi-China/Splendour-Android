package android.game.gameView;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

public abstract class MyAnimation extends AnimationDrawable {
	Handler finishHandler; // �жϽ�����Handler

	public MyAnimation(AnimationDrawable ad) {
		// ������Լ���ÿһ֡�ӽ�ȥ
		for (int i = 0; i < ad.getNumberOfFrames(); i++) {
			this.addFrame(ad.getFrame(i), ad.getDuration(i));
		}
	}

	@Override
	public void start() {
		super.start();
		// �����ø����start() Ȼ�������̣߳�������onAnimationEnd()
		finishHandler = new Handler();
		finishHandler.postDelayed(new Runnable() {
			public void run() {
				onAnimationEnd();
			}
		}, getTotalDuration());
	}

	// ���������ö����ĳ���ʱ�䣨֮�����onAnimationEnd()��
	public int getTotalDuration() {
		int durationTime = 0;
		for (int i = 0; i < this.getNumberOfFrames(); i++) {
			durationTime += this.getDuration(i);
		}
		return durationTime;
	}

	// ����ʱ���õķ�����һ��Ҫʵ��
	abstract void onAnimationEnd();
}