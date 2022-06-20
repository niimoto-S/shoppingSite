package jp.co.aforce.mySQL;

public class MySQLError {

	public static String getMessage(int errorCore) {

		String message = "";

		switch (errorCore) {
		case ErrorCode.ERROR_1044:
			message = ErrorCode.ERROR_MESSAGE_1044;
			break;
		case ErrorCode.ERROR_1045:
			message = ErrorCode.ERROR_MESSAGE_1045;
			break;
		case ErrorCode.ERROR_1046:
			message = ErrorCode.ERROR_MESSAGE_1046;
			break;
		case ErrorCode.ERROR_1054:
			message = ErrorCode.ERROR_MESSAGE_1054;
			break;
		case ErrorCode.ERROR_1062:
			message = ErrorCode.ERROR_MESSAGE_1062;
			break;
		case ErrorCode.ERROR_1064:
			message = ErrorCode.ERROR_MESSAGE_1064;
			break;
		case ErrorCode.ERROR_1067:
			message = ErrorCode.ERROR_MESSAGE_1067;
			break;
		case ErrorCode.ERROR_1146:
			message = ErrorCode.ERROR_MESSAGE_1146;
			break;
		case ErrorCode.ERROR_1918:
			message = ErrorCode.ERROR_MESSAGE_1918;
			break;
		case ErrorCode.ERROR_2002:
			message = ErrorCode.ERROR_MESSAGE_2002;
			break;
		case ErrorCode.ERROR_2003:
			message = ErrorCode.ERROR_MESSAGE_2003;
			break;
		case ErrorCode.ERROR_2006:
			message = ErrorCode.ERROR_MESSAGE_2006;
			break;
		default:
			message = "エラーコードが登録されていません。「MySQLErrorCode : " + errorCore + "」で、検索してください";
			break;
		}


		return message;
	}

}
