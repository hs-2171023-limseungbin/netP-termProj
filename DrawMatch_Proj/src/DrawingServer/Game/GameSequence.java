package DrawingServer.Game;

import DrawingServer.ServerSocket.ServerSocketProtocol;

public class GameSequence extends Thread{
	//private JTextArea chatTextArea;
	
	@Override
	public void run() {
		super.run();
		DrawMatchGame game = new DrawMatchGame();
		String answer;
		int index = 0; //유저의 차례를 판단하는 index
		
		game.start();
		GameManager.startGame();
		
		while(game.nextAnswer()) {
			answer = game.getAnswer();
			
			GameManager.answer = answer;
			
			GameManager.turnToGame = true;
			GameManager.turnToAnswer = false;
			
			GameManager.Id = ServerSocketProtocol.List.get(index).getUserId();
			GameManager.userAnnouncement(GameManager.Id+"님의 차례입니다.");
			
			ServerSocketProtocol.List.get(index).outputMessage("Setting:True");
			ServerSocketProtocol.List.get(index).outputMessage("Chatting:당신의 차례입니다.");
			ServerSocketProtocol.List.get(index).outputMessage("Chatting:정답은 "+ answer + "입니다.");
			ServerSocketProtocol.List.get(index).outputMessage("Correct:"+ answer);
			while(true) {
				if(GameManager.turnToAnswer == true) {break;}
				else {
					try {
						Thread.sleep(300);
					}catch(InterruptedException e){
						System.out.println("[에러] 발생");
					}
				}
			}
			ServerSocketProtocol.List.get(index).outputMessage("Correct:"+ " ");
			GameManager.userFailed();
			++index;
			if(index == ServerSocketProtocol.List.size()) {index = 0;}
		}
	}
}
