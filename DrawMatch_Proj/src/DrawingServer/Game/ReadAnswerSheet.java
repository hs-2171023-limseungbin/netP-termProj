package DrawingServer.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadAnswerSheet {

	private File file;
	private final String directory="src"+File.separator+"DrawingServer"+File.separator+"Game"+File.separator+"AnswerSheet.txt";
	private ArrayList<String> list;
	
	public void read() {
		createList();
		fileRead();
	}
	private void createList() {
		list = new ArrayList<String>();
	}
	private void fileRead() {
		try {
			file = new File(directory);
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}
			bufferedReader.close();
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e) {
		}
	}
	public ArrayList<String> getList() {
		return this.list;
	}
	
}
