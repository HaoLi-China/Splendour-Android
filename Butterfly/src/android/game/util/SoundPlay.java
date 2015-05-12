package android.game.util;

import android.game.gameView.GameView;

public class SoundPlay {
	private GameView gameView = null;

	public SoundPlay(GameView gameView) {
		this.gameView = gameView;
	}

	// 播放背景音乐
	public void palyBackMusic(boolean bool) {
		if (bool) {
			gameView.setBackSound();
			gameView.getBackSound().setLooping(true);
			gameView.getBackSound().start();
		}
	}

	// 停止播放背景音乐
	public void stopBackMusic() {
		gameView.getBackSound().stop();
	}

	// 播放抓到蜜蜂时的音效
	public void palyBeeSound(boolean bool) {
		if (bool) {
			gameView.setBeeSound();
			gameView.getBeeSound().setLooping(false);
			gameView.getBeeSound().start();
		}
	}

	// 播放抓到蝴蝶时的音效
	public void palyButterSound(boolean bool) {
		if (bool) {
			gameView.setButterSound();
			gameView.getButterSound().setLooping(false);
			gameView.getButterSound().start();
		}
	}

	// 播放抓捕时的音效
	public void palyCatchSound(boolean bool) {
		if (bool) {
			gameView.setCatchSound();
			gameView.getCatchSound().setLooping(false);
			gameView.getCatchSound().start();
		}
	}

	// 播放闪电的音效
	public void palyLightingSound(boolean bool) {
		if (bool) {
			gameView.setLightingSound();
			gameView.getLightingSound().setLooping(false);
			gameView.getLightingSound().start();
		}
	}
}
