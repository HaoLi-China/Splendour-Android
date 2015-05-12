package android.game.gameView;

import android.graphics.drawable.AnimationDrawable;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class AnimView {
	private GameView gameView = null;
	private ImageView view = null;
	private AbsoluteLayout.LayoutParams params = null;

	// 屏幕宽和高
	private int screenWidth;
	private int screenHeight;

	// 构造器
	public AnimView(GameView gameView) {
		this.gameView = gameView;
		this.screenWidth = gameView.screenWidth;
		this.screenHeight = gameView.screenHeight;
		init();
	}

	// 初始化
	public void init() {
		view = new ImageView(gameView);
		int width = screenHeight * 18 / 25;
		int height = screenHeight * 3 / 5;
		params = new AbsoluteLayout.LayoutParams(width, height, screenWidth / 2
				- width / 2, screenHeight / 2 - height / 2);
	}

	// 设置道具动画
	public void setAniamals(int toolId) {
		AnimationDrawable anim = new AnimationDrawable();
		switch (toolId) {
		case 1:
			for (int i = 0; i < 3; i++) {
				// 第一个 就是我们的资源名称(图片名)
				// 第二个 就是我们存放图片的文件夹drawable
				// 第三个 包名也可以用Context的getPackageName返回应用程序的包名
				int id = gameView.getResources().getIdentifier("lighting",
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 2:
			for (int i = 0; i < 3; i++) {
				// 第一个 就是我们的资源名称(图片名)
				// 第二个 就是我们存放图片的文件夹drawable
				// 第三个 包名也可以用Context的getPackageName返回应用程序的包名
				int id = gameView.getResources().getIdentifier("nettool" + i,
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				// 第一个 就是我们的资源名称(图片名)
				// 第二个 就是我们存放图片的文件夹drawable
				// 第三个 包名也可以用Context的getPackageName返回应用程序的包名
				int id = gameView.getResources().getIdentifier("clocktool",
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 4:
			for (int i = 0; i < 3; i++) {
				// 第一个 就是我们的资源名称(图片名)
				// 第二个 就是我们存放图片的文件夹drawable
				// 第三个 包名也可以用Context的getPackageName返回应用程序的包名
				int id = gameView.getResources().getIdentifier(
						"butterflygodtool", "drawable",
						"android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++) {
				// 第一个 就是我们的资源名称(图片名)
				// 第二个 就是我们存放图片的文件夹drawable
				// 第三个 包名也可以用Context的getPackageName返回应用程序的包名
				int id = gameView.getResources().getIdentifier("lure" + i,
						"drawable", "android.game.butterfly");
				anim.addFrame(gameView.getResources().getDrawable(id), 200);
			}
			break;
		}
		// 设置手否重复播放，true为不重复
		anim.setOneShot(true);
		MyAnimation mad = new MyAnimation(anim) {
			@Override
			void onAnimationEnd() {
				// 实现这个方法，结束后会调用
				view.setBackgroundResource(0);
			}
		};
		// 把这个动画“赐福”给某个ImageView
		view.setBackgroundDrawable(mad);
		// 开始
		mad.start();
	}

	public ImageView getView() {
		return view;
	}

	public AbsoluteLayout.LayoutParams getParams() {
		return params;
	}
}
