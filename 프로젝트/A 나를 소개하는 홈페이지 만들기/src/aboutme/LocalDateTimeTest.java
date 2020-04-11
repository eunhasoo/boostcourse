package aboutme;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

	public static void main(String[] args) {
		ZoneId id = ZoneId.of("Asia/Seoul"); // default는 UTC이므로 서울의 timezone을 구한다
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/M/d H:mm"); // 출력할 패턴을 지정한다
		System.out.println(LocalDateTime.now(id).format(formatter)); // 출력할 timezone의 날짜와 패턴으로 만든 String을 리턴
	}

}
