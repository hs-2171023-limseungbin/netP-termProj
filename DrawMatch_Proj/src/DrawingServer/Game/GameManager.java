package DrawingServer.Game;

import DrawingServer.ServerSocket.ServerSocketProtocol;
//게임 진행 관리 클래스
public class GameManager {
	static public String answer;
	static public boolean turnToAnswer = false; //정답을 맞추는 차례 판단
	static public boolean turnToGame = false; //정답을 내는 차례 판단
	static public String Id;
	

	static public void startGame() {
		for (int i = 0; i < ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("CHAT:[SERVER] " + "게임을 시작합니다.");
			ServerSocketProtocol.List.get(i).sendMsg("CHAT:[SERVER] " + "제한시간은 30초입니다!");
			ServerSocketProtocol.List.get(i).sendMsg("SET:FALSE");
			ServerSocketProtocol.List.get(i).sendMsg("MODE:CLEAR");
		}
	}
	
	//모든 사용자가 시간내에 정답을 맞추지 못했을 때 화면을 지우고 다음 차례로 넘김
	static public void allFailed() {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("SET:FALSE");
			ServerSocketProtocol.List.get(i).sendMsg("MODE:CLEAR");
		}
	}
	
	//정답을 맞췄을 때
	static public void gotAnswerRight(String id) {
		turnToAnswer = true;
		answer = "";
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg("CHAT:[SERVER] " + id + "님 정답");
		}
	}
	
	//입장한 모든 사람에게 메시지를 전송하는 메서드
	static public void ChatOnAllUser(String msg) {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).sendMsg(msg);
		}
	}
}
