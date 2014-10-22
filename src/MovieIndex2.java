
import java.io.IOException;

public class MovieIndex2{

	public static void main(String[] args)  throws IOException {
		//test_subs,Iron,sourcecode,spider,nowuseemee
		String filePath="E:\\Study Materials\\Compiler Project\\Movie_Indexing\\src\\test_subs.srt";
		String Script_Elements[];
		int c_Script_Elements=0,index;
	    String Script_Segment[]=new String[3000];
	    int c_Script_Segment;
	    int lib_score[][]=new int[3000][3000];
	    String stop[]={"a","about","above","badge","big","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","attack","capsule","name","dick",
	    		"if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","janet","disappointment","moment",
	    		"was","wasn't","we","we'd","we'll","we're","we've","were","truth","weren't","said","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"};
		try{
			ReadFile file=new ReadFile(filePath);
			String file_content[]= file.openFile();
			
			System.out.println(file_content.length);
			
			String content="";
			for(int i=0;i<file_content.length;i++)
			{
				content=content+file_content[i]+" ";
			}
			
			
			System.out.println("THE CONTENTS OF SRT FILE\n");
			System.out.println("\n"+content+"\n");
			ScriptExtract se=new ScriptExtract(content);
			c_Script_Elements=se.nSE;
			Script_Elements=new String[c_Script_Elements+1];
			Script_Elements=se.match_Script();
			content=null;
			System.gc();
			index=0;
			System.out.println("SPILTTED INTO SCRIPT ELEMENTS\n");
	        while(index<c_Script_Elements)
	        {
	        	System.out.println(Script_Elements[index]);
	        	index++;
	        }
	        System.out.println(c_Script_Elements);
	        int i=0;
	        String start1="",start2="",end1="",end2="";
	        String temp1="",temp2="";
	       
	        String start[]=new String[c_Script_Elements+1];
	        String end[]=new String[c_Script_Elements+1];
	        String subs[]=new String[c_Script_Elements+1];
	        for(int j=0;j<c_Script_Elements;j++)
	        {
		        for(String retval : Script_Elements[j].split(" ", 5) )
		        {
		        	if(i==1)
		        		start[j]=retval;
		        	if(i==3)
		        		end[j]=retval;
		        	if(i==4)
		        		subs[j]=retval;
		        	i++;
		        }
		        i=0;
	        }
	        System.out.println("SPLITTING INTO SEGMENTS\n");
	        for(int j=0;j<c_Script_Elements;j++)
	        {
	        	System.out.println(start[j]);
	        	System.out.println(end[j]);
	        	System.out.println(subs[j]);
	        }
	        
	        int k=-1;
	        System.out.println("COMIBINING INTO SCRIPT SEGMENTS\n");
	        for(int j=0;j<c_Script_Elements;j++)
	        {
	        	if(j%9==0)
	        	{
	        		k++;
	        		Script_Segment[k]="";
	        	}
	        	Script_Segment[k]+=subs[j];
	        	System.out.println(subs[j]);
	        }
	        System.out.println(k);
	        System.out.println("COMIBINING INTO SCRIPT SEGMENTS\n");
	        for(int j=0;j<k;j++)
	        	System.out.println("\n"+Script_Segment[j]+"\n");
	        System.out.println("STRIPPING OF STOP WORDS AND UNIMPORTANT SYMBOLS\n");
	        for(int j=0;j<k;j++)
	        {
	        	Script_Segment[j]=Script_Segment[j].replaceAll("\\s+", " ");
	        	Script_Segment[j]=Script_Segment[j].replaceAll("[,.'?-]", "");
	        	Script_Segment[j]=Script_Segment[j].replaceAll("</*.>", "");
	        	Script_Segment[j]=Script_Segment[j].replaceAll("  ", " ");
	        	Script_Segment[j]=Script_Segment[j].toLowerCase();
	        	for(int l=0;l<stop.length;l++ )
	        	{
	        		String regex=" "+stop[l]+" ";
	        		Script_Segment[j]=Script_Segment[j].replaceAll(regex, " ");
	        	}
	        	System.out.println(Script_Segment[j]);
	        	System.out.println();
	        }
	        //System.out.println("Out of script segment");
	        String lib[][]=new String[3000][3000];
	        Stemmer s=new Stemmer();
	        int lib_len[]=new int[3000];
	        for(i=0;i<k;i++)
	        {
	        	String word[];
	        	word=Script_Segment[i].split(" ");
	        	lib_len[i]=word.length;
	        	for(int j=0;j<word.length;j++)
	        	{	        		
	        		lib[i][j]=s.get_stem(word[j]);
	        		//lib[i][j]=word[j];
	        		//System.out.println(lib[i][j]);
	        	}
	        }
	        int count=0;
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		String check=lib[i][j];
	        		count=0;
	        		for(int l=0;l<lib_len[i];l++)
	        		{
	        			if(check.equals(lib[i][l]))
	        			{
	        				count++;
	        			}
	        		}
	        		lib_score[i][j]=count;
	        	}
	        		//System.out.println(lib[i][j]);
	        	
	        }
	        int lib_global_score[][]=new int [3000][3000];
	        int lib_global_score1[][]=new int [3000][3000];
	        int flag=0;
	        int count2;
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		String check=lib[i][j];
	        		count=0;
	        		count2=0;
	        		for(int m=0;m<k;m++)
	        		{
		        		for(int l=0;l<lib_len[m];l++)
		        		{
		        			if(check.equals(lib[m][l]))
		        			{
		        				count2++;
		        				flag=1;
		        				if(flag==1)
		        				{
		        					break;
		        				}
		        			}
		        			
		        	
		        	
		        		}
		        	
		        		
	        		}
	        		lib_global_score1[i][j]=count2;
	        	}
	        		//System.out.println(lib[i][j]);
	        	//System.out.println();
	        }
	        double tftid1[][]=new double [3000][3000];
	        double tftid[][]=new double [3000][3000];
	        double vect[][]=new double [3000][3000];
	       double w[][]=new double [3000][3000];
	       double max=0;
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		vect[i][j]=k/lib_global_score1[i][j];
	        	}
	        }
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		vect[i][j]=(double) Math.log(vect[i][j]);
	        	}
	        }
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		tftid[i][j]=((lib_score[i][j])*(vect[i][j]));
	        	}
	        }
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        	
	        		tftid1[i][j]=0;	        		
	        	}
	        }
	        double sum56=0;
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        		tftid1[i][j]=((tftid[i][j])*(tftid[i][j]));
	        		sum56=sum56+tftid1[i][j];
	        	}
	        }
	        double sq=0;
	        		sq=(double) Math.sqrt(sum56);
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        	
					w[i][j]=((tftid[i][j])/sq);
	        	}
	        }
	        
			max=w[0][0];
			for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        	
	        	  if(w[i][j]>max)
	        	  {
	        		  max=w[i][j];
	        	  }
	        	}
	        }
	        int cou10[][]=new int[2000][2];
	        int l=0;
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        	{
	        	
	        	  if(w[i][j]==max)
	        	  {
	        		  cou10[l][0]=i;
	        		  cou10[l][1]=j;
	        		  l++;
	        	  }
	        	}
	        }
	        System.out.println("LIBRARY OF WORDS \n");
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        		System.out.print(lib[i][j]+" ");
	        	System.out.println();
	        }
	        System.out.println("\nLOCAL SCORE FOR WORDS");
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        		System.out.print(lib_score[i][j]+" ");
	        	//System.out.println();
	        }
	        System.out.println("\n\nGLOBAL SCORE FOR WORDS");
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        		System.out.print(lib_global_score1[i][j]+ " ");
	        	//System.out.println();
	        }
	        /*	        
	        System.out.println("\nThe tftid vector for the words");
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        		System.out.print(tftid[i][j]+" ");
	        	//System.out.println();
	        }*/
	        System.out.println();
	        
	        //System.out.print(sq + "  "+sum56);
	        System.out.print("\nTHE WEIGHTAGE FOR WORDS\n");
	        for(i=0;i<k;i++)
	        {
	        	for(int j=0;j<lib_len[i];j++)
	        		if(w[i][j]!=' ')
	        			System.out.print(w[i][j]+"   ");
	        	System.out.println();
	        }
	        System.out.println("\nTHE MAXIMUM WEIGHTAGE GIVEN: "+max+"\n");
	        int m=0;
	        String final_str[]=new String[2000];
	        for(i=0;i<l;i++)
	        {
	           final_str[i]=lib[cou10[i][0]][cou10[i][1]];
	           //cmpr2[i][m]=cmpr[i][m];
//	           System.out.println(final_str[i]);
	        }
	        
	        System.out.println("\nTHE MOST COMMON WORDS USED IN THE MOVIE ARE \n");
	        int y=0;
	        flag=0;
	        for(i=0;i<l;i++)
	        {
	        	flag=0;
	        	y=0;
	        	String check=final_str[i];
	        	while(y<i)
	        	{
	        		if(final_str[y].equals(check))
	        			flag=1;
	        		y++;
	        	}
	        	if(flag==0)
	        		System.out.println(check);
	        }
		}
		
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
