package javeCorePractice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class readFiles {

	private static List<String> blackList = new ArrayList<>();
    
    public static List<String> getBlackList(){
        if(blackList.isEmpty()){
            String fileName = new File("").getAbsolutePath() + "/lines.txt";

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                blackList = stream.collect(Collectors.toList());

            } catch (IOException e) {
                 e.printStackTrace();
            }
            
        }
        return blackList;
    }
    
	public static void main(String[] args) {
		getBlackList();
		 


	}

}
