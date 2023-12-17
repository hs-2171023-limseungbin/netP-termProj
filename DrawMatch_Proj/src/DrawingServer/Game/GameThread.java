package DrawingServer.Game;

import javax.swing.JTextArea;

import DrawingServer.ServerSocket.ServerSocketProtocol;

public class GameThread extends Thread{
	private JTextArea chatTextArea;
	
	public void run() {
		super.run();
		DrawMatchGame game = new DrawMatchGame();
		String answer;
		int index = 0;
		game.start();
		GameManager.startGame();
		while(game.moreAnswer()) {
			answer = game.getAnswer();
			GameManager.answer = answer;
			System.out.println(answer);
			GameManager.turnToGame = true;
			GameManager.turnToAnswer = true;
			GameManager.Id = ServerSocketProtocol.List.get(index).getUserId();
			GameManager.ChatOnAllUser("CHAT[알림]"+GameManager.Id+"님의 차례입니다.");
			chatTextArea.append("CHAT[알림]"+ GameManager.Id+ "님의 차례입니다.");
			ServerSocketProtocol.List.get(index).sendMsg("SET:TRUE");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[알림]" + "당신차례입니다.");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[알림]" + "정답은 "+ answer + "입니다.");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[알림]" +" 정답을 잘 설명해보세요");
			ServerSocketProtocol.List.get(index).sendMsg("ANSWER:"+ answer);
			while(true) {
				if(GameManager.turnToAnswer == true) {break;}
				else {
					try {
						Thread.sleep(3000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
			ServerSocketProtocol.List.get(index).sendMsg("ANSWER:"+ " ");
			GameManager.allFailed();
			++index;
			if(index == ServerSocketProtocol.List.size()) {index = 0;}
		}
	}
	public void setChatTextArea(JTextArea chatTextArea) {
		this.chatTextArea = chatTextArea;
	}
}
