package com.javawebspringboot.education.exception;

public class ReadFileException extends Exception {

	private static final long serialVersionUID = 1L;
	private static String msgError = "";

    @Override
    public String getMessage() {
        return "File bi loi tai dong " + ReadFileException.msgError;
    }

    public static void messageError(String strError) {
        ReadFileException.msgError = strError;
    }

    public static String getMsgError() {
        return msgError;
    }

    public static void setMsgError(String msgError) {
        ReadFileException.msgError = msgError;
    }

}
