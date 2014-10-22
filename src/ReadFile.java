import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile {
	
	public String path;
	
	public ReadFile(String file_path)
	{
		path=file_path;
	}
	
	public String[] openFile() throws IOException{
		
		FileReader fr=new FileReader(path);
		BufferedReader textReader=new BufferedReader(fr);
		
		int numberOfLines=readLine();
		String textData[]=new String[numberOfLines];
		
		for(int i=0;i<numberOfLines;i++)
		{
			textData[i]=textReader.readLine();
		}
		
		textReader.close();
		return textData;
		
	}
	
	int readLine() throws IOException{
		
		FileReader fr=new FileReader(path);
		BufferedReader bf=new BufferedReader(fr);
		
		int numberOfLines=0;
		
		while((bf.readLine())!=null)
			numberOfLines++;
		
		bf.close();
		return numberOfLines;
	}

}
