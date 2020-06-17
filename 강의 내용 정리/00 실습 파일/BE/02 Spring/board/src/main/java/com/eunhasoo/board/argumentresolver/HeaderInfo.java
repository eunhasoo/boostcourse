package com.eunhasoo.board.argumentresolver;

import java.util.HashMap;
import java.util.Map;

// 컨트롤러 메소드의 인자값에서 resolve 될 객체로,
// 다른 리소스에서 참조될 수 있는 하나 이상의 필드를 가졌으며
// 폼 객체가 아니어야하고, request body로 부터 읽히지 않아야 하는 충족사항을 가진다.
public class HeaderInfo {
	private Map<String, String> map;

	public HeaderInfo() {
		map = new HashMap<>();
	}

	public void put(String name, String value) {
		map.put(name, value);
	}

	public String get(String name) {
		return map.get(name);
	}
}
