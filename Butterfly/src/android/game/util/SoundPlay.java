package android.game.util;

import android.game.gameView.GameView;

public class SoundPlay {
	private GameView gameView = null;

	public SoundPlay(GameView gameView) {
		this.gameView = gameView;
	}

	// ���ű�������
	public void palyBackMusic(boolean bool) {
		if (bool) {
			gameView.setBackSound();
			gameView.getBackSound().setLooping(true);
			gameView.getBackSound().start();
		}
	}

	// ֹͣ���ű�������
	public void stopBackMusic() {
		gameView.getBackSound().stop();
	}

	// ����ץ���۷�ʱ����Ч
	public void palyBeeSound(boolean bool) {
		if (bool) {
			gameView.setBeeSound();
			gameView.getBeeSound().setLooping(false);
			gameView.getBeeSound().start();
		}
	}

	// ����ץ������ʱ����Ч
	public void palyButterSound(boolean bool) {
		if (bool) {
			gameView.setButterSound();
			gameView.getButterSound().setLooping(false);
			gameView.getButterSound().start();
		}
	}

	// ����ץ��ʱ����Ч
	public void palyCatchSound(boolean bool) {
		if (bool) {
			gameView.setCatchSound();
			gameView.getCatchSound().setLooping(false);
			gameView.getCatchSound().start();
		}
	}

	// �����������Ч
	public void palyLightingSound(boolean bool) {
		if (bool) {
			gameView.setLightingSound();
			gameView.getLightingSound().setLooping(false);
			gameView.getLightingSound().start();
		}
	}
}
