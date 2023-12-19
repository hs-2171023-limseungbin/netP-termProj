package DrawingServer.Game;

import DrawingServer.ServerSocket.ServerSocketProtocol;

public class GameManager {
	static public String answer;
	static public boolean turnToAnswer = false;
	static public boolean turnToGame = false;
	static public String Id;
	
	static public void startGame() {
		for (int i = 0; i < ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("CHAT:[SERVER] " + "게임을 시작합니다.");
			ServerSocketProtocol.List.get(i).sendMsg("SET:FALSE");
			ServerSocketProtocol.List.get(i).sendMsg("MODE:CLEAR");
		}
	}
	static public void allFailed() {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("SET:FALSE");
			ServerSocketProtocol.List.get(i).sendMsg("MODE:CLEAR");
		}
	}
	
	static public void gotAnswerRight(String id) {
		turnToAnswer = true;
		answer = "";
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("CHAT:[SERVER] " + id + "님 정답");
		}
	}
	
	static public void ChatOnAllUser(String msg) {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg(msg);
		}
	}
}
