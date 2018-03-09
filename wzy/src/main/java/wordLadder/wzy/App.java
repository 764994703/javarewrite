package wordLadder.wzy;
import java.util.*;
import java.io.File;
public class App {
	public static void main() throws Exception{
		Scanner s = new Scanner(System.in);//input file name and start&end word
		System.out.println("dictionary address:\n");
		String fl = s.nextLine();//filename
		File file = new File(fl);
		Scanner dicscanner = new Scanner(file);//scanner of file
		Set<String> dic = new HashSet<String>();//使用set作为dictionary
		while(dicscanner.hasNextLine()){
			dic.add(dicscanner.nextLine());//读取文件中单词放入dictionary
		}
		dicscanner.close();
		System.out.println("start word:\n");
		String start = s.nextLine();//start word
		System.out.println("end word\n");
		String end = s.nextLine();//end word
		
		App wl = new App();//实例wl以调用非静态函数
		wl.ladder(start,end,dic);
		s.close();
	}
	int maintest(String beginWord, String endWord, String filepath) throws Exception{
		String fl = filepath;//filename
		File file = new File(fl);
		Scanner dicscanner = new Scanner(file);//scanner of file
		Set<String> dic = new HashSet<String>();//使用set作为dictionary
		while(dicscanner.hasNextLine()){
			dic.add(dicscanner.nextLine());//读取文件中单词放入dictionary
		}
		dicscanner.close();
		App wl = new App();//实例wl以调用非静态函数
		if(wl.ladder(beginWord,endWord,dic)==0)
			return 0;
		else return 1;
	}
	public int ladder(String beginWord, String endWord, Set<String> dic) throws Exception {  
        if (beginWord.length() == 0 || endWord.length() == 0  || beginWord.length() != endWord.length()){
        	System.out.println("there is something wrong!\n");
        	return 0; 
        }
        Map<String,String> path = new HashMap<String,String>();
        Set<String> dictionary = new HashSet<String>(dic);  
        if (dictionary.contains(beginWord))  
            dictionary.remove(beginWord);  
        Queue<String> queue = new LinkedList<String>();  
        queue.add(beginWord);
  
        while (!queue.isEmpty()) {  
            String word = queue.remove();  //删除第一个
            for (int i = 0; i < word.length(); i++) {  
                char[] wordd = word.toCharArray();  
                for (char j = 'a'; j <= 'z'; j++) {  
                    wordd[i] = j;  
                    String newword = new String(wordd);  
                    
                    if (dictionary.contains(newword)) {  
                    	
                    	path.put(newword,word);
                        if (newword.equals(endWord)) {
                        	String noww = newword;
                        	System.out.println(noww);
                        	while(path.get(noww)!= null) {
                        		String prenext = path.get(noww);
                        		System.out.println("->" + prenext );
                        		noww = prenext;
                        	}
                        	return 1;
                        }
                        queue.add(newword);  
                        dictionary.remove(newword);  
                        
                    }  
                }  
            }  
            
        }  
        System.out.println("no such path");
        App.main(); 
        return 0;
    }  
}

