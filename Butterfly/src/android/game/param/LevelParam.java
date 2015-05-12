package android.game.param;

public class LevelParam {
	private int butterNum = 0;
	private int butterTypeNum = 0;

	// �����Ӧ�ȼ��ĺ�����Ŀ
	public int getButterNum(int level) {
		if (level > 0 && level <= 10) {
			butterNum = 10;
		} else if (level > 10 && level <= 20) {
			butterNum = 12;
		} else if (level > 20 && level <= 30) {
			butterNum = 14;
		} else if (level > 30 && level <= 40) {
			butterNum = 16;
		} else {
			butterNum = 18;
		}
		return butterNum;
	}

	// �����Ӧ�ȼ��ĺ�������
	public int getButterTypeNum(int level) {
		if (level > 0 && level <= 10) {
			butterTypeNum = 7;
		} else if (level > 10 && level <= 20) {
			butterTypeNum = 8;
		} else if (level > 20 && level <= 30) {
			butterTypeNum = 9;
		} else if (level > 30 && level <= 40) {
			butterTypeNum = 10;
		} else {
			butterTypeNum = 11;
		}
		return butterTypeNum;
	}

	// ��������ֵ
	public int getGetMaxExperience(int level) {
		int maxExperience = 1000 + 100 * level;
		return maxExperience;
	}
}
