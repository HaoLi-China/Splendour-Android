package android.game.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataUtil {

	private Editor editor;
	private SharedPreferences share;
	private RWSharedperferences rws;
	private Context context;

	public DataUtil(Context context) {
		share = context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
		editor = share.edit();
		rws = new RWSharedperferences();
		this.context = context;
	}

	// 初始化数据
	public void initData() {
		if (getInitSt() == 0) {
			setSceneNum(1);
			setSK(1);
			setYX(1);
			setYY(1);
			for (int i = 1; i <= 5; i++) {
				setToolNum(i, 0);
				setSceneStatic(i, 0);
			}
			setSceneStatic(1, 1);
			setInitSt(1);
		}
		setMoney(Integer.parseInt(rws.read(context, "goldNum")));
		setLevel(Integer.parseInt(rws.read(context, "level")));
		setExp(Integer.parseInt(rws.read(context, "experience")));
		for (int i = 1; i <= 5; i++) {
			setToolNum(i, Integer.parseInt(rws
					.read(context, "tool" + i + "num")));
		}
	}

	// 得到是否第一次启动游戏
	public int getInitSt() {
		int ininst = share.getInt("initst", 0);
		return ininst;
	}

	// 设置启动游戏状态
	private void setInitSt(int st) {
		editor.putInt("initst", st);
		editor.commit();
	}

	// 得到金币数目
	public int getMoney() {
		int money = share.getInt("money", 0);
		return money;
	}

	// 设置金币数目
	private void setMoney(int money) {
		editor.putInt("money", money);
		editor.commit();
	}

	// 得到经验值
	private int getExp() {
		int exp = share.getInt("exp", 0);
		return exp;
	}

	// 设置经验值
	private void setExp(int exp) {
		editor.putInt("exp", exp);
		editor.commit();
	}

	// 得到等级值
	private int getLevel() {
		int level = share.getInt("level", 0);
		return level;
	}

	// 设置等级值
	private void setLevel(int level) {
		editor.putInt("level", level);
		editor.commit();
	}

	// 得到道具个数，cate是第几个道具
	public int getToolNum(int cate) {
		int toolnum = share.getInt("tool" + cate, 0);
		return toolnum;
	}

	// 得到道具个数,cate是第几个道具
	private void setToolNum(int cate, int num) {
		editor.putInt("tool" + cate, num);
		editor.commit();
	}

	// 判断场景是否已经解锁,cate是第几个场景
	private int getSceneStatic(int cate) {
		int scenesc = share.getInt("scene" + cate, 0);
		return scenesc;
	}

	// 设置场景的状态
	private void setSceneStatic(int cate, int sc) {
		editor.putInt("scene" + cate, sc);
		editor.commit();
	}

	// 得到用户上次游戏选择的场景
	public int getSceneNum() {
		int scenenum = share.getInt("scenenum", 0);
		return scenenum;
	}

	// 设置用户选择的场景
	public void setSceneNum(int scnum) {
		editor.putInt("scenenum", scnum);
		editor.commit();
	}

	// 用人民币来换取金币,cate代表选择哪种方式,一共六种
	public void buyMoney(int cate) {
		int addmoney = 0;
		switch (cate) {
		case 1:
			addmoney = 400;
			break;
		case 2:
			addmoney = 700;
			break;
		case 3:
			addmoney = 1600;
			break;
		case 4:
			addmoney = 4000;
			break;
		case 5:
			addmoney = 12000;
			break;
		case 6:
			addmoney = 30000;
			break;
		}
		setMoney(getMoney() + addmoney);
	}

	// 金币购买道具，cate代表第几个道具，一共5个道具
	public int buyTools(int cate) {
		int reducemoney = 0;
		switch (cate) {
		case 1:
			reducemoney = 50;
			break;
		case 2:
			reducemoney = 60;
			break;
		case 3:
			reducemoney = 100;
			break;
		case 4:
			reducemoney = 120;
			break;
		case 5:
			reducemoney = 140;
			break;
		}
		if (getMoney() >= reducemoney) {
			setMoney(getMoney() - reducemoney);
			setToolNum(cate, getToolNum(cate) + 1);
			rws.write(context, "goldNum", "" + getMoney());
			rws.write(context, "tool" + cate + "num", "" + getToolNum(cate));
			return 1;// 购买成功
		} else {
			return -1;// 金币数目不足,提示可以金钱购买金币
		}
	}

	// 使用道具，数量减1
	public int useTools(int cate) {
		if (getToolNum(cate) != 0) {
			setToolNum(cate, getToolNum(cate) - 1);
			return 1;// 使用成功
		} else {
			return -1;// 道具数量不足，请购买
		}
	}

	// 增加经验值
	public void addExp(int addexp) {
		if (getExp() + addexp > (getLevel() + 1) * 100) {// 可以升级
			setLevel((getLevel() + addexp) - ((getLevel() + 1) * 100));
			addLevel();
		} else {// 不用升级，只是加经验
			setExp(getExp() + addexp);
		}
	}

	// 增加经验值
	public void addLevel() {
		setLevel(getLevel() + 1);
	}

	// 判断此场景是否已经解锁，解锁了就不在购买，未解锁则判断金币是否足够
	public int buyScene(int cate) {
		int reducemoney = 0;
		switch (cate) {
		case 1:
			reducemoney = 1000;
			break;
		case 2:
			reducemoney = 2000;
			break;
		case 3:
			reducemoney = 5000;
			break;
		case 4:
			reducemoney = 10000;
			break;
		case 5:
			reducemoney = 20000;
			break;
		}
		if (getMoney() >= reducemoney) {
			setMoney(getMoney() - reducemoney);
			setSceneStatic(cate, 1);
			rws.write(context, "goldNum", "" + getMoney());
			return 1;// 购买成功
		} else {
			return -1;// 金币数目不足,提示可以金钱购买金币
		}
	}

	public void changeMoney(int coin) {
		int money = getMoney() + coin;
		if (money > 999999) {
			money = 999999;
		}
		if (money < 0) {
			money = 0;
		}
		setMoney(money);
	}

	public int sceneUnlocked(int cate) {
		return getSceneStatic(cate);
	}

	/* 声音设置 */

	// 声控
	public int getSK() {
		int sk = share.getInt("sk", 0);
		return sk;
	}

	public void setSK(int OnorOff) {
		editor.putInt("sk", OnorOff);
		editor.commit();
	}

	// 音效
	public int getYX() {
		int yx = share.getInt("yx", 0);
		return yx;
	}

	public void setYX(int OnorOff) {
		editor.putInt("yx", OnorOff);
		editor.commit();
	}

	// 音乐
	public int getYY() {
		int yy = share.getInt("yy", 0);
		return yy;
	}

	public void setYY(int OnorOff) {
		editor.putInt("yy", OnorOff);
		editor.commit();
	}

}
