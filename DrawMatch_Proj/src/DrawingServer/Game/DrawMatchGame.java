package DrawingServer.Game;

import java.util.ArrayList;
// 게임 구현 클래스
public class DrawMatchGame {
	private ArrayList<String> List; //정답 리스트
	private String answer; //현재 정답
	
	private ReadAnswerSheet read; //AnswerSheet.txt를 읽는 객체 
	public void start() {
		read();
		saveAnswer();
	}
	private void read() {
		read = new ReadAnswerSheet();
		read.read();
	}
	private void saveAnswer() {
		List = read.getList();
	}
	public boolean nextAnswer() {
		if(List.size() != 0) {
			int index = (int) (Math.random() * List.size());
			answer = List.get(index);
			List.remove(index);
			return true;
		}else {
			return false;
		}
	}
	public String getAnswer() {
		return this.answer;
	}
}
