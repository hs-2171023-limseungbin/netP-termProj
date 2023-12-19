package DrawingServer.Game;

import java.util.ArrayList;
// 게임 구현 클래스
public class DrawMatchGame {
	private ReadAnswerSheet readfile; //AnswerSheet.txt를 읽는 객체 
	private ArrayList<String> answerList; //정답 리스트
	private String answer; //현재 정답
	
	public void start() {
		readFile();
		saveAnswer();
	}
	private void readFile() {
		readfile = new ReadAnswerSheet();
		readfile.read();
	}
	private void saveAnswer() {
		answerList = readfile.getList();
	}
	public boolean moreAnswer() {
		if(answerList.size() != 0) {
			int index = (int) (Math.random() * answerList.size());
			answer = answerList.get(index);
			answerList.remove(index);
			return true;
		}else {
			return false;
		}
	}
	public String getAnswer() {
		return this.answer;
	}
}
