package DrawingServer.Game;

import javax.swing.JTextArea;

import DrawingServer.ServerSocket.ServerSocketProtocol;

public class GameThread extends Thread{
	private JTextArea chatTextArea;
	
	@Override
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
			GameManager.turnToAnswer = false;
			GameManager.Id = ServerSocketProtocol.List.get(index).getUserId();
			GameManager.ChatOnAllUser("[운영자]>"+GameManager.Id+"님의 차례입니다.");
			chatTextArea.append("[운영자]>"+ GameManager.Id+ "님의 차례입니다.\n");
			ServerSocketProtocol.List.get(index).sendMsg("SET:TRUE");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[운영자]>" + "당신의 차례입니다.");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[운영자]>" + "정답은 "+ answer + "입니다.");
			ServerSocketProtocol.List.get(index).sendMsg("CHAT:[운영자]>" + "정답을 잘 설명해보세요");
			ServerSocketProtocol.List.get(index).sendMsg("ANSWER:"+ answer);
			while(true) {
				if(GameManager.turnToAnswer == true) {break;}
				else {
					try {
						Thread.sleep(300);
					}catch(InterruptedException e){}
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
