package util;

public class JudgmentIsEmpty {
	public static boolean judgmentIsEmpty(String value) {
		if (value != null && !(value.trim().isEmpty())) {
			return true;
		} else {
			return false;
		}

	}
}
