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

	// ��ʼ������
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

	// �õ��Ƿ��һ��������Ϸ
	public int getInitSt() {
		int ininst = share.getInt("initst", 0);
		return ininst;
	}

	// ����������Ϸ״̬
	private void setInitSt(int st) {
		editor.putInt("initst", st);
		editor.commit();
	}

	// �õ������Ŀ
	public int getMoney() {
		int money = share.getInt("money", 0);
		return money;
	}

	// ���ý����Ŀ
	private void setMoney(int money) {
		editor.putInt("money", money);
		editor.commit();
	}

	// �õ�����ֵ
	private int getExp() {
		int exp = share.getInt("exp", 0);
		return exp;
	}

	// ���þ���ֵ
	private void setExp(int exp) {
		editor.putInt("exp", exp);
		editor.commit();
	}

	// �õ��ȼ�ֵ
	private int getLevel() {
		int level = share.getInt("level", 0);
		return level;
	}

	// ���õȼ�ֵ
	private void setLevel(int level) {
		editor.putInt("level", level);
		editor.commit();
	}

	// �õ����߸�����cate�ǵڼ�������
	public int getToolNum(int cate) {
		int toolnum = share.getInt("tool" + cate, 0);
		return toolnum;
	}

	// �õ����߸���,cate�ǵڼ�������
	private void setToolNum(int cate, int num) {
		editor.putInt("tool" + cate, num);
		editor.commit();
	}

	// �жϳ����Ƿ��Ѿ�����,cate�ǵڼ�������
	private int getSceneStatic(int cate) {
		int scenesc = share.getInt("scene" + cate, 0);
		return scenesc;
	}

	// ���ó�����״̬
	private void setSceneStatic(int cate, int sc) {
		editor.putInt("scene" + cate, sc);
		editor.commit();
	}

	// �õ��û��ϴ���Ϸѡ��ĳ���
	public int getSceneNum() {
		int scenenum = share.getInt("scenenum", 0);
		return scenenum;
	}

	// �����û�ѡ��ĳ���
	public void setSceneNum(int scnum) {
		editor.putInt("scenenum", scnum);
		editor.commit();
	}

	// �����������ȡ���,cate����ѡ�����ַ�ʽ,һ������
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

	// ��ҹ�����ߣ�cate����ڼ������ߣ�һ��5������
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
			return 1;// ����ɹ�
		} else {
			return -1;// �����Ŀ����,��ʾ���Խ�Ǯ������
		}
	}

	// ʹ�õ��ߣ�������1
	public int useTools(int cate) {
		if (getToolNum(cate) != 0) {
			setToolNum(cate, getToolNum(cate) - 1);
			return 1;// ʹ�óɹ�
		} else {
			return -1;// �����������㣬�빺��
		}
	}

	// ���Ӿ���ֵ
	public void addExp(int addexp) {
		if (getExp() + addexp > (getLevel() + 1) * 100) {// ��������
			setLevel((getLevel() + addexp) - ((getLevel() + 1) * 100));
			addLevel();
		} else {// ����������ֻ�ǼӾ���
			setExp(getExp() + addexp);
		}
	}

	// ���Ӿ���ֵ
	public void addLevel() {
		setLevel(getLevel() + 1);
	}

	// �жϴ˳����Ƿ��Ѿ������������˾Ͳ��ڹ���δ�������жϽ���Ƿ��㹻
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
			return 1;// ����ɹ�
		} else {
			return -1;// �����Ŀ����,��ʾ���Խ�Ǯ������
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

	/* �������� */

	// ����
	public int getSK() {
		int sk = share.getInt("sk", 0);
		return sk;
	}

	public void setSK(int OnorOff) {
		editor.putInt("sk", OnorOff);
		editor.commit();
	}

	// ��Ч
	public int getYX() {
		int yx = share.getInt("yx", 0);
		return yx;
	}

	public void setYX(int OnorOff) {
		editor.putInt("yx", OnorOff);
		editor.commit();
	}

	// ����
	public int getYY() {
		int yy = share.getInt("yy", 0);
		return yy;
	}

	public void setYY(int OnorOff) {
		editor.putInt("yy", OnorOff);
		editor.commit();
	}

}
