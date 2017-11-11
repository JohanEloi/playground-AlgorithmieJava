package com.egaetan;

public class Communication {

	public void msg(String channel, String msg) {
		java.lang.System.out.println(String.format("TECHIO> message --channel \"%s\" \"%s\"", channel, msg));
	}

	public void success(boolean success) {
		java.lang.System.out.println(String.format("TECHIO> success %s", success));
	}
	
}
