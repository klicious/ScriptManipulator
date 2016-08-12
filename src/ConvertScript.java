import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConvertScript {

	static Map<Integer,Script> subtitle;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		String outputPath, inputPath;
		
		for(int i = 0; i < 16; i++) {
			int a = i + 1;
			String episode;
			if(a < 10) {
				episode = "0" + a;
			}else {
				episode = String.valueOf(a);
			}

			outputPath = "Friends_S01_E" + episode + ".txt";
			/*
			 * Write OUTPUT to OUTPUT_FILE
			 */
			PrintWriter writer = null;
			try{
				writer = new PrintWriter(outputPath, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/*
			 * Read INPUT from INFPUT_FILE
			 */
			
/*			
			for ( Map.Entry<Integer, Script> entry : subtitle.entrySet()) {
				System.out.println("Value of Key :" + String.valueOf(entry.getKey()));
				System.out.println("Korean saved :" + entry.getValue().getKorean());
				System.out.println("English saved : " + entry.getValue().getEnglish());
				System.out.println(" ============================ ");
				
			}
			*/
			SortedSet<Integer> keys = new TreeSet<Integer>(subtitle.keySet());
			//writer.println("HERE IT STARTS for Love_Actually");
			for ( Integer ret : keys) {
				Script entry = subtitle.get(ret);
				writer.println(entry.getEnglish());
				writer.println(entry.getKorean());
			}
			
			inputPath = "C:\\Users\\Kwon\\workspace\\PronounceBetter\\ScriptManipulator\\Friends_S01_E" + episode + "_orignial.txt";
			
			try (BufferedReader br = new BufferedReader(new FileReader(inputPath))){
				String line;
				while( (line = br.readLine()) != null) {
					//!line.matches(".*[*-].*") && line.length() > 1
					if(line.length() < 2) continue;
					if (!(line.substring(0,1).equals("*") || line.substring(0,1).equals("-"))) {
						writer.println(line.replaceAll("\\[.*\\]", "").replaceAll("\\(.*\\)", "").trim());
					} else {
						//System.out.println(line);
					}
					
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer.close();
		}
		
		
/*		
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
*/
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
	
	private static void init() {
		subtitle = new HashMap<>();
	}
	
	private void readLoveActually(){
		String line = "";
		
		subtitle.clear();
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
