import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ScriptExtract {

	String content;
	int nSE=0,index=0;
	String scriptElements[];
	String regex="(\\d)+ ((\\d){2}:*){3},(\\d){3} --> ((\\d){2}:*){3},(\\d){3} (([a-zA-Z_.<>/,'?-])+(\\s)*)+ ";
	
	public ScriptExtract(String Content)
	{
		content=Content;
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(content);
        while (m.find()) {
        	nSE++;
        }
	}
	
	public String[] match_Script()
	{
	
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(content);
        while (m.find()) {
        	nSE++;
        }
        m = r.matcher(content);
        scriptElements=new String[nSE+1];
        
        while (m.find()) {
        	scriptElements[index]=m.group();
        	index++;
        }
       /* index=0;
        while(index<nSE)
        {
        	System.out.println(scriptElements[index]);
        	index++;
        }
        System.out.println(nSE);*/
        return scriptElements;
        
	
	}
	
	public int scriptElementCount()
	{
		return nSE;
	}
}
