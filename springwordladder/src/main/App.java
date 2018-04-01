package wordLadder.wzy;
import java.util.*;
import java.io.File;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam; 

public class App {
	@RequestMapping(value = "/wordladder") 
	public static void main(@RequestParam(value = "beginWord") String beginWord, @RequestParam(value = "endWord") String endWord, @RequestParam(value = "fileAddr") String fileName) throws Exception{
		File file = new File(fileName);
		Scanner dicscanner = new Scanner(file);//scanner of file
		Set<String> dic = new HashSet<String>();//使用set作为dictionary
		while(dicscanner.hasNextLine()){
			dic.add(dicscanner.nextLine());//读取文件中单词放入dictionary
		}
		dicscanner.close();
		
		App wl = new App();//实例wl以调用非静态函数
		wl.ladder(start,end,dic);
		s.close();
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

