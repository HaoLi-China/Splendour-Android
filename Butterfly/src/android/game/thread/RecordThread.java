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
		// ���ڶ�ȡ��
		byte[] buffer = new byte[bs];
		isRun = true;
		if (butterflying.getIsSoundCtl()) {
			while (isRun&&isSupport) {
				if (!butterflying.getPauseState()) {
					int r = ar.read(buffer, 0, bs);
					int v = 0;
					// �� buffer ����ȡ��������ƽ��������
					for (int i = 0; i < buffer.length; i++) {
						// ����û����������Ż���Ϊ�˸���������չʾ����
						v += buffer[i] * buffer[i];
					}
					// ƽ���ͳ��������ܳ��ȣ��õ�������С�����Ի�ȡ������ֵ��Ȼ���ʵ�ʲ������б�׼����
					// ��������������ֵ���в����������� sendMessage �����׳����� Handler ����д���
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
		// �ڵ��ñ��̵߳� Activity �� onPause ����ã��Ա� Activity ��ͣʱ�ͷ���˷�
		isRun = false;
	}

	public void start() {
		// �ڵ��ñ��̵߳� Activity �� onResume ����ã��Ա� Activity �ָ��������ȡ��˷���������
		if (!isRun) {
			super.start();
		}
	}

}
