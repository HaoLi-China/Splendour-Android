package android.game.thread;

import android.game.gameView.Butterflying;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class RecordThread extends Thread {
	private AudioRecord ar;
	private int bs;
	private static int SAMPLE_RATE_IN_HZ = 8000;
	private boolean isRun = false;
	private boolean isSupport = true;

	private Butterflying butterflying = null;

	public RecordThread(Butterflying butterflying) {
		super();
		this.butterflying = butterflying;
		try{
		bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, bs);
		}
		catch(Exception e){
			isSupport=false;
		}

	}

	public void run() {
		super.run();
		ar.startRecording();
		// 用于读取的
		byte[] buffer = new byte[bs];
		isRun = true;
		if (butterflying.getIsSoundCtl()) {
			while (isRun&&isSupport) {
				if (!butterflying.getPauseState()) {
					int r = ar.read(buffer, 0, bs);
					int v = 0;
					// 将 buffer 内容取出，进行平方和运算
					for (int i = 0; i < buffer.length; i++) {
						// 这里没有做运算的优化，为了更加清晰的展示代码
						v += buffer[i] * buffer[i];
					}
					// 平方和除以数据总长度，得到音量大小。可以获取白噪声值，然后对实际采样进行标准化。
					// 如果想利用这个数值进行操作，建议用 sendMessage 将其抛出，在 Handler 里进行处理。
					double d = v / (float) r;
					System.out.println(d);
					if (d > 3000) {
						butterflying.catchButter(butterflying.getWebX()
								+ butterflying.getWebWidth() / 2, butterflying
								.getWebY()
								+ butterflying.getWebHeight() / 2);
					}
				}
			}
		}
		ar.stop();
	}

	public void pause() {
		// 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风
		isRun = false;
	}

	public void start() {
		// 在调用本线程的 Activity 的 onResume 里调用，以便 Activity 恢复后继续获取麦克风输入音量
		if (!isRun) {
			super.start();
		}
	}

}
