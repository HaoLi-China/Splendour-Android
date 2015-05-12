package android.game.param;

public class ButterflyParam {

	// 蝴蝶大小
	private int beeWidth = 66;
	private int beeHeight = 650 / 6;
	private int butterWidth1 = 80;
	private int butterHeight1 = 1360 / 17;
	private int butterWidth2 = 60;
	private int butterHeight2 = 1020 / 17;
	private int butterWidth3 = 70;
	private int butterHeight3 = 1190 / 17;
	private int butterWidth4 = 70;
	private int butterHeight4 = 1190 / 17;
	private int butterWidth5 = 90;
	private int butterHeight5 = 1530 / 17;
	private int butterWidth6 = 50;
	private int butterHeight6 = 1120 / 14;
	private int butterWidth7 = 66;
	private int butterHeight7 = 1620 / 18;
	private int butterWidth8 = 98;
	private int butterHeight8 = 1800 / 18;
	private int butterWidth9 = 150;
	private int butterHeight9 = 3600 / 18;
	private int butterWidth10 = 120;
	private int butterHeight10 = 2160 / 18;

	// 蝴蝶飞行速度
	private int beeSpeed = 3;
	private int butter1Speed = 3;
	private int butter2Speed = 4;
	private int butter3Speed = 5;
	private int butter4Speed = 6;
	private int butter5Speed = 7;
	private int butter6Speed = 8;
	private int butter7Speed = 9;
	private int butter8Speed = 10;
	private int butter9Speed = 11;
	private int butter10Speed = 12;

	// 蝴蝶捕获概率（百分之几）
	private int beeRate = 80;
	private int butter1Rate = 70;
	private int butter2Rate = 60;
	private int butter3Rate = 30;
	private int butter4Rate = 22;
	private int butter5Rate = 18;
	private int butter6Rate = 15;
	private int butter7Rate = 12;
	private int butter8Rate = 9;
	private int butter9Rate = 6;
	private int butter10Rate = 3;

	// 蝴蝶出现概率（百分之几）
	private int beeShowRate = 10;
	private int butter1ShowRate = 19;
	private int butter2ShowRate = 14;
	private int butter3ShowRate = 11;
	private int butter4ShowRate = 10;
	private int butter5ShowRate = 9;
	private int butter6ShowRate = 8;
	private int butter7ShowRate = 7;
	private int butter8ShowRate = 6;
	private int butter9ShowRate = 4;
	private int butter10ShowRate = 2;

	// 捕获相应蝴蝶获得的分数
	private int beePoints = -10;
	private int butter1Points = 1;
	private int butter2Points = 3;
	private int butter3Points = 5;
	private int butter4Points = 10;
	private int butter5Points = 15;
	private int butter6Points = 20;
	private int butter7Points = 25;
	private int butter8Points = 30;
	private int butter9Points = 40;
	private int butter10Points = 50;

	// 捕获相应蝴蝶获得的经验值
	private int beeExp = 0;
	private int butter1Exp = 3;
	private int butter2Exp = 5;
	private int butter3Exp = 7;
	private int butter4Exp = 9;
	private int butter5Exp = 10;
	private int butter6Exp = 11;
	private int butter7Exp = 14;
	private int butter8Exp = 15;
	private int butter9Exp = 19;
	private int butter10Exp = 20;

	// 得到蝴蝶宽度
	public int getButterWidth(int butterId) {
		switch (butterId) {
		case 0:
			return beeWidth;
		case 1:
			return butterWidth1;
		case 2:
			return butterWidth2;
		case 3:
			return butterWidth3;
		case 4:
			return butterWidth4;
		case 5:
			return butterWidth5;
		case 6:
			return butterWidth6;
		case 7:
			return butterWidth7;
		case 8:
			return butterWidth8;
		case 9:
			return butterWidth9;
		case 10:
			return butterWidth10;
		}
		return 0;
	}

	// 得到蝴蝶高度
	public int getButterHeight(int butterId) {
		switch (butterId) {
		case 0:
			return beeHeight;
		case 1:
			return butterHeight1;
		case 2:
			return butterHeight2;
		case 3:
			return butterHeight3;
		case 4:
			return butterHeight4;
		case 5:
			return butterHeight5;
		case 6:
			return butterHeight6;
		case 7:
			return butterHeight7;
		case 8:
			return butterHeight8;
		case 9:
			return butterHeight9;
		case 10:
			return butterHeight10;
		}
		return 0;
	}

	// 得到蝴蝶价值
	public int getPoint(int butterId) {
		switch (butterId) {
		case 0:
			return beePoints;
		case 1:
			return butter1Points;
		case 2:
			return butter2Points;
		case 3:
			return butter3Points;
		case 4:
			return butter4Points;
		case 5:
			return butter5Points;
		case 6:
			return butter6Points;
		case 7:
			return butter7Points;
		case 8:
			return butter8Points;
		case 9:
			return butter9Points;
		case 10:
			return butter10Points;
		}
		return 0;
	}

	// 设置蝴蝶价值
	public void setButterPoints(int butterId, int points) {
		switch (butterId) {
		case 0:
			beePoints = points;
			break;
		case 1:
			butter1Points = points;
			break;
		case 2:
			butter2Points = points;
			break;
		case 3:
			butter3Points = points;
			break;
		case 4:
			butter4Points = points;
			break;
		case 5:
			butter5Points = points;
			break;
		case 6:
			butter6Points = points;
			break;
		case 7:
			butter7Points = points;
			break;
		case 8:
			butter8Points = points;
			break;
		case 9:
			butter9Points = points;
			break;
		case 10:
			butter10Points = points;
			break;
		}
	}

	// 得到蝴蝶经验值
	public int getExp(int butterId) {
		switch (butterId) {
		case 0:
			return beeExp;
		case 1:
			return butter1Exp;
		case 2:
			return butter2Exp;
		case 3:
			return butter3Exp;
		case 4:
			return butter4Exp;
		case 5:
			return butter5Exp;
		case 6:
			return butter6Exp;
		case 7:
			return butter7Exp;
		case 8:
			return butter8Exp;
		case 9:
			return butter9Exp;
		case 10:
			return butter10Exp;
		}
		return 0;
	}

	// 设置蝴蝶经验值
	public void setButterExp(int butterId, int exp) {
		switch (butterId) {
		case 0:
			beeExp = exp;
			break;
		case 1:
			butter1Exp = exp;
			break;
		case 2:
			butter2Exp = exp;
			break;
		case 3:
			butter3Exp = exp;
			break;
		case 4:
			butter4Exp = exp;
			break;
		case 5:
			butter5Exp = exp;
			break;
		case 6:
			butter6Exp = exp;
			break;
		case 7:
			butter7Exp = exp;
			break;
		case 8:
			butter8Exp = exp;
			break;
		case 9:
			butter9Exp = exp;
			break;
		case 10:
			butter10Exp = exp;
			break;
		}
	}

	// 初始化蝴蝶捕捉概率
	public void initButterRate() {
		beeRate = 80;
		butter1Rate = 80;
		butter2Rate = 75;
		butter3Rate = 65;
		butter4Rate = 60;
		butter5Rate = 55;
		butter6Rate = 45;
		butter7Rate = 40;
		butter8Rate = 35;
		butter9Rate = 25;
		butter10Rate = 20;
	}

	// 设置蝴蝶捕捉概率
	public void setButterRate(int butterId, int rate) {
		switch (butterId) {
		case 0:
			beeRate = rate;
			break;
		case 1:
			butter1Rate = rate;
			break;
		case 2:
			butter2Rate = rate;
			break;
		case 3:
			butter3Rate = rate;
			break;
		case 4:
			butter4Rate = rate;
			break;
		case 5:
			butter5Rate = rate;
			break;
		case 6:
			butter6Rate = rate;
			break;
		case 7:
			butter7Rate = rate;
			break;
		case 8:
			butter8Rate = rate;
			break;
		case 9:
			butter9Rate = rate;
			break;
		case 10:
			butter10Rate = rate;
			break;
		}
	}

	// 得到蝴蝶捕获概率
	public int getButterRate(int butterId) {
		switch (butterId) {
		case 0:
			return beeRate;
		case 1:
			return butter1Rate;
		case 2:
			return butter2Rate;
		case 3:
			return butter3Rate;
		case 4:
			return butter4Rate;
		case 5:
			return butter5Rate;
		case 6:
			return butter6Rate;
		case 7:
			return butter7Rate;
		case 8:
			return butter8Rate;
		case 9:
			return butter9Rate;
		case 10:
			return butter10Rate;
		}
		return 0;
	}

	// 设置蝴蝶出现概率
	public void setButterShowRate(int butterId, int rate) {
		switch (butterId) {
		case 0:
			beeShowRate = rate;
			break;
		case 1:
			butter1ShowRate = rate;
			break;
		case 2:
			butter2ShowRate = rate;
			break;
		case 3:
			butter3ShowRate = rate;
			break;
		case 4:
			butter4ShowRate = rate;
			break;
		case 5:
			butter5ShowRate = rate;
			break;
		case 6:
			butter6ShowRate = rate;
			break;
		case 7:
			butter7ShowRate = rate;
			break;
		case 8:
			butter8ShowRate = rate;
			break;
		case 9:
			butter9ShowRate = rate;
			break;
		case 10:
			butter10ShowRate = rate;
			break;
		}
	}

	// 得到蝴蝶出现概率
	public int getButterShowRate(int butterId) {
		switch (butterId) {
		case 0:
			return beeShowRate;
		case 1:
			return butter1ShowRate;
		case 2:
			return butter2ShowRate;
		case 3:
			return butter3ShowRate;
		case 4:
			return butter4ShowRate;
		case 5:
			return butter5ShowRate;
		case 6:
			return butter6ShowRate;
		case 7:
			return butter7ShowRate;
		case 8:
			return butter8ShowRate;
		case 9:
			return butter9ShowRate;
		case 10:
			return butter10ShowRate;
		}
		return 0;
	}

	// 设置蝴蝶速度
	public void setButterSpeed(int butterId, int speed) {
		switch (butterId) {
		case 0:
			beeSpeed = speed;
			break;
		case 1:
			butter1Speed = speed;
			break;
		case 2:
			butter2Speed = speed;
			break;
		case 3:
			butter3Speed = speed;
			break;
		case 4:
			butter4Speed = speed;
			break;
		case 5:
			butter5Speed = speed;
			break;
		case 6:
			butter6Speed = speed;
			break;
		case 7:
			butter7Speed = speed;
			break;
		case 8:
			butter8Speed = speed;
			break;
		case 9:
			butter9Speed = speed;
			break;
		case 10:
			butter10Speed = speed;
			break;
		}
	}

	// 初始化蝴蝶速度
	public void initButterSpeed() {
		beeSpeed = 3;
		butter1Speed = 3;
		butter2Speed = 4;
		butter3Speed = 5;
		butter4Speed = 6;
		butter5Speed = 7;
		butter6Speed = 8;
		butter7Speed = 9;
		butter8Speed = 10;
		butter9Speed = 11;
		butter10Speed = 12;
	}

	// 蝴蝶横坐标路径
	public float getXPath(float xLocation, int butterId) {
		float xpath = 0;
		switch (butterId) {
		case 0:
			xpath = xLocation + beeSpeed;
			break;
		case 1:
			xpath = xLocation + butter1Speed;
			break;
		case 2:
			xpath = xLocation + butter2Speed;
			break;
		case 3:
			xpath = xLocation + butter3Speed;
			break;
		case 4:
			xpath = xLocation + butter4Speed;
			break;
		case 5:
			xpath = xLocation + butter5Speed;
			break;
		case 6:
			xpath = xLocation + butter6Speed;
			break;
		case 7:
			xpath = xLocation + butter7Speed;
			break;
		case 8:
			xpath = xLocation + butter8Speed;
			break;
		case 9:
			xpath = xLocation + butter9Speed;
			break;
		case 10:
			xpath = xLocation + butter10Speed;
			break;
		}
		return xpath;
	}

	// 蝴蝶纵坐标路径
	public float getYPath(float xLocation, float width, float height, int pathId) {
		double ypath = 0;
		switch (pathId) {
		case 1:
			ypath = (height / 6) * Math.sin(2 * Math.PI * xLocation / width)
					+ height / 2;
			break;
		case 2:
			ypath = (height / 6) * Math.sin(4 * Math.PI * xLocation / width)
					+ height / 2;
			break;
		case 3:
			ypath = (height / 3) * Math.sin(Math.PI * xLocation / width)
					+ height / 2;
			break;
		case 4:
			ypath = (height / 6) * Math.cos(Math.PI * xLocation / width)
					+ height / 2;
			break;
		case 5:
			ypath = (height / 6) * Math.cos(2 * Math.PI * xLocation / width)
					+ height / 3;
			break;
		case 6:
			ypath = (height / 6) * Math.cos(2 * Math.PI * xLocation / width)
					+ 2 * height / 3;
			break;
		case 7:
			ypath = (height / 4) * Math.cos(Math.PI * xLocation / width)
					+ height / 4;
			break;
		case 8:
			ypath = (height / 4) * Math.sin(2 * Math.PI * xLocation / width)
					+ 5 * height / 6;
			break;
		case 9:
			ypath = (height / 3) * Math.cos(Math.PI * xLocation / width)
					+ height / 2;
			break;
		case 10:
			ypath = (height / 6) * Math.cos(4 * Math.PI * xLocation / width)
					+ height / 2;
			break;
		}
		return (float) ypath;
	}
}
