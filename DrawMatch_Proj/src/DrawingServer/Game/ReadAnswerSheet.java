package DrawingServer.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//AnswerSheet.txt 를 읽는 클래스
public class ReadAnswerSheet {

	private File file;
	private final String directory="src"+File.separator+"DrawingServer"+File.separator+"Game"+File.separator+"AnswerSheet.txt";
	private ArrayList<String> list;
	
	public void read() {
		createList();
		Read();
	}
	private void createList() {
		list = new ArrayList<String>();
	}
	private void Read() {
		try {
			file = new File(directory);
			FileReader read = new FileReader(file);
			BufferedReader in = new BufferedReader(read);
			String line = "";
			while((line = in.readLine()) != null) {
				list.add(line);
			}
			in.close();
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e) {
		}
	}
	//정답 리스트 반환
	public ArrayList<String> getList() {
		return this.list;
	}
	
}
