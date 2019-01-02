package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class SessionManager extends HttpServlet{
private Map<String,HttpSession> map;
public SessionManager() {
    map = new HashMap<String,HttpSession>();
}

public void addSession(HttpSession session){
	map.put(session.getId(), session);
}
public HttpSession getSession(String sessionId) {
    if (sessionId == null) {
        return null;
    }
    return map.get(sessionId);

}
}
