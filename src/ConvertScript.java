import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConvertScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Write OUTPUT to OUTPUT_FILE
		 */
		PrintWriter writer = null;
		try{
			writer = new PrintWriter("Movies_LoveActually.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Read INPUT from INFPUT_FILE
		 */
		String line = "";
		Map<Integer, Script> subtitle = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kwon\\workspace\\PronounceBetter\\ScriptManipulator\\Love_Actually.txt"))){
			
			while( (line = br.readLine()) != null) {
				//!line.matches(".*[*-].*") && line.length() > 1
				if(line.length() < 2) continue;
				
				if(line.contains("SYNC")) {
					if(line.contains("&nbsp")) {
						continue;
					}
					line = line.substring(line.indexOf('=') + 1, line.indexOf('>'));
					//System.out.println(line);
					
					int key = Integer.valueOf(line);
					Script value;
					if ((line = br.readLine()) != null) {
						line = line.replaceAll("<.*>", " ").replaceAll("-", " ").replaceAll("\\(.*\\)", "").trim();
						if (line.length() < 2) continue;
						if (subtitle.containsKey(key)) {
							value = subtitle.get(key);
							value.setEnglish(line);
							subtitle.put(key, value);
						} else {
							value = new Script(line, line);
							subtitle.put(key, value);							
							//System.out.println(line);
						}
					}
										
				}
					
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for ( Map.Entry<Integer, Script> entry : subtitle.entrySet()) {
			System.out.println("Value of Key :" + String.valueOf(entry.getKey()));
			System.out.println("Korean saved :" + entry.getValue().getKorean());
			System.out.println("English saved : " + entry.getValue().getEnglish());
			System.out.println(" ============================ ");
			
		}
		
		SortedSet<Integer> keys = new TreeSet<Integer>(subtitle.keySet());
		writer.println("HERE IT STARTS for Love_Actually");
		for ( Integer ret : keys) {
			Script entry = subtitle.get(ret);
			writer.println(entry.getEnglish());
			writer.println(entry.getKorean());
		}
/*		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kwon\\workspace\\PronounceBetter\\ScriptManipulator\\Friends_S01_E01_orignial.txt"))){
			
			while( (line = br.readLine()) != null) {
				//!line.matches(".*[*-].*") && line.length() > 1
				if(line.length() < 2) continue;
				if (!(line.substring(0,1).equals("*") || line.substring(0,1).equals("-"))) {
					writer.println(line.replaceAll("\\[.*\\]", "").replaceAll("\\(.*\\)", "").trim());
				} else {
					System.out.println(line);
				}
				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
		
		ArrayList<String> fullScript = new ArrayList<String>();
        ArrayList<Script> theScript = new ArrayList<Script>();
		
try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kwon\\workspace\\PronounceBetter\\ScriptManipulator\\Friends_S01_E01.txt"))){
			
			while( (line = br.readLine()) != null) {
				fullScript.add(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
writer.close();
/*		
		String english = "", korean = "";
		boolean flag = true;
        for (String ret : fullScript) {
            if(flag) {
            	flag = false;
            	continue;
            }
        	if (english == "") {
                english = ret;
            } else if(korean == "") {
                korean = ret;
                theScript.add(new Script(english, korean));
                english = "";
                korean = "";
            } else {
                System.out.println(english + " :: " + korean + " :: " + ret);
            }
        }
		
        int index = 0;
		for (Script ret : theScript) {
			index++;
			System.out.println("index = " + index);
			System.out.println("English = " + ret.getEnglish());
			System.out.println("Korean = " + ret.getKorean());
			System.out.println();
		}
		
		int scriptSize = theScript.size();
        List<Integer> shuffle = new ArrayList<Integer>();
        for(int i = 0; i < scriptSize; i++) {
        	shuffle.add(i);
        }
        
        for(int ret : shuffle) {
        	System.out.println(ret);
        }
        
        Collections.shuffle(shuffle);
        
        for (int ret : shuffle) {
        	System.out.println(ret);
        }
		System.out.println("너 한글 읽을 줄 아니 ??");
	}*/
	}
}

class Script {
    private String English;
    private String Korean;

    public Script(String english, String korean) {
        English = english;
        Korean = korean;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getKorean() {
        return Korean;
    }

    public void setKorean(String korean) {
        Korean = korean;
    }
}
