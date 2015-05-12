package android.game.gameView;

import android.graphics.drawable.AnimationDrawable;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class AnimView {
	private GameView gameView = null;
	private ImageView view = null;
	private AbsoluteLayout.LayoutParams params = null;

	// ��Ļ��͸�
	private int screenWidth;
	private int screenHeight;

	// ������
	public AnimView(GameView gameView) {
		this.gameView = gameView;
		this.screenWidth = gameView.screenWidth;
		this.screenHeight = gameView.screenHeight;
		init();
	}

	// ��ʼ��
	public void init() {
		view = new ImageView(gameView);
		int width = screenHeight * 18 / 25;
		int height = screenHeight * 3 / 5;
		params = new AbsoluteLayout.LayoutParams(width, height, screenWidth / 2
				- width / 2, screenHeight / 2 - height / 2);
	}

	// ���õ��߶���
	public void setAniamals(int toolId) {
		AnimationDrawable anim = new AnimationDrawable();
		switch (toolId) {
		case 1:
			for (int i = 0; i < 3; i++) {
				// ��һ�� �������ǵ���Դ����(ͼƬ��)
				// �ڶ��� �������Ǵ��ͼƬ���ļ���drawable
				// ������ ����Ҳ������Context��getPackageName����Ӧ�ó���İ���
				int id = gameView.getResources().getIdentifier("lighting",
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 2:
			for (int i = 0; i < 3; i++) {
				// ��һ�� �������ǵ���Դ����(ͼƬ��)
				// �ڶ��� �������Ǵ��ͼƬ���ļ���drawable
				// ������ ����Ҳ������Context��getPackageName����Ӧ�ó���İ���
				int id = gameView.getResources().getIdentifier("nettool" + i,
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				// ��һ�� �������ǵ���Դ����(ͼƬ��)
				// �ڶ��� �������Ǵ��ͼƬ���ļ���drawable
				// ������ ����Ҳ������Context��getPackageName����Ӧ�ó���İ���
				int id = gameView.getResources().getIdentifier("clocktool",
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 4:
			for (int i = 0; i < 3; i++) {
				// ��һ�� �������ǵ���Դ����(ͼƬ��)
				// �ڶ��� �������Ǵ��ͼƬ���ļ���drawable
				// ������ ����Ҳ������Context��getPackageName����Ӧ�ó���İ���
				int id = gameView.getResources().getIdentifier(
						"butterflygodtool", "drawable",
						"android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++) {
				// ��һ�� �������ǵ���Դ����(ͼƬ��)
				// �ڶ��� �������Ǵ��ͼƬ���ļ���drawable
				// ������ ����Ҳ������Context��getPackageName����Ӧ�ó���İ���
				int id = gameView.getResources().getIdentifier("lure" + i,
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		}
		// �����ַ��ظ����ţ�trueΪ���ظ�
		anim.setOneShot(true);
		MyAnimation mad = new MyAnimation(anim) {
			@Override
			void onAnimationEnd() {
				// ʵ���������������������
				view.setBackgroundResource(0);
			}
		};
		// ������������͸�����ĳ��ImageView
		view.setBackgroundDrawable(mad);
		// ��ʼ
		mad.start();
	}

	public ImageView getView() {
		return view;
	}

	public AbsoluteLayout.LayoutParams getParams() {
		return params;
	}
}
