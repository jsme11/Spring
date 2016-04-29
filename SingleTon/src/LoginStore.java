import java.util.ArrayList;
import java.util.List;

public class LoginStore {

	private static volatile LoginStore loginStore;
	private List<String> loginSessions;
	
	// private으로 설정
	// 생성자를 호출하지 못하도록 막는다.
	private LoginStore() {
		loginSessions = new ArrayList<String>();
	}
	
	public static synchronized LoginStore getInstance() {
		
		if ( loginStore == null ) {
			loginStore = new LoginStore();
		}
		
		return loginStore;
	}
	
	public void add(String sessionId) {
		loginSessions.add(sessionId);
	}
	
	public String get(int index) {
		return loginSessions.get(index);
	}
}
