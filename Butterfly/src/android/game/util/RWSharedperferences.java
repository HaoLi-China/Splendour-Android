package android.game.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class RWSharedperferences {
	private String strValue = null;
	private String name[] = { "level", "experience", "goldNum", "tool1num",
			"tool2num", "tool3num", "tool4num", "tool5num" };
	private String value[] = { "1", "0", "1500", "1", "1", "1", "1", "1" };

	// �������ļ��ж�ȡ����
	public String read(Context context, String keyName) {
		SharedPreferences share = context.getSharedPreferences("Butterfly",
				Context.MODE_PRIVATE);
		strValue = share.getString(keyName, "");// ����keyѰ��ֵ ����1 key ����2
		// ���û��value��ʾ������
		if (strValue.contentEquals("")) {
			for (int i = 0; i < name.length; i++) {
				write(context, name[i], value[i]);
			}
			strValue = share.getString(keyName, "");
		}

		return strValue;
	}

	// ������д�������ļ���
	public void write(Context context, String keyName, String keyValue) {
		SharedPreferences share = context.getSharedPreferences("Butterfly",
				Context.MODE_PRIVATE);
		Editor editor = share.edit();// ȡ�ñ༭��
		editor.putString(keyName, keyValue);// �洢���� ����1 ��key ����2 ��ֵ
		editor.commit();// �ύˢ������
	}
}
