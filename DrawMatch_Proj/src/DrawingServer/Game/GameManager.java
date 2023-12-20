package DrawingServer.Game;

import DrawingServer.ServerSocket.ServerSocketProtocol;
//게임 진행 관리 클래스
public class GameManager {
	static public String answer;
	static public String Id;
	static public boolean turnToAnswer = false; //정답 차례 판단
	static public boolean turnToGame = false; //게임 시작 판단
	
	
	static public void startGame() {
		for (int i = 0; i < ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).outputMessage("Chatting:게임 시작");
			ServerSocketProtocol.List.get(i).outputMessage("Chatting:제한시간은 30초입니다!");
			ServerSocketProtocol.List.get(i).outputMessage("Setting:False");
			ServerSocketProtocol.List.get(i).outputMessage("Mode:Repaint");
		}
	}
	
	//모든 사용자가 시간내에 정답을 맞추지 못했을 때 화면을 지우고 다음 차례로 넘김
	static public void userFailed() {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).outputMessage("Setting:False");
			ServerSocketProtocol.List.get(i).outputMessage("Mode:Repaint");
		}
	}
	
	//정답을 맞췄을 때
	static public void userCorrect(String id) {
		turnToAnswer = true;
		answer = "";
	}
	
	//입장한 모든 사람에게 메시지를 전송하는 메서드
	static public void userAnnouncement(String out) {
		for(int i=0; i<ServerSocketProtocol.List.size(); i++) {
			ServerSocketProtocol.List.get(i).outputMessage(out);
		}
	}
}
