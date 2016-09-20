import java.util.ArrayList;
import java.util.Random;

public class RandomPatternGenerator {
	
	public static ArrayList<Phrase> generateKickPattern(int[] commonTime, int totalPhraseCount){
		
		ArrayList<Phrase> pattern = new ArrayList<>();
		
		if(commonTime.length != 2){
			System.out.println("Common Time is not valid");
			return pattern;
		}
		
		Random random = new Random();
		
		int repeatPhraseCount;
		if(totalPhraseCount < 2){
			repeatPhraseCount = 1;
		}else if(totalPhraseCount < 4){
			repeatPhraseCount = (random.nextInt(2) * 2) + 1;
			
			while(repeatPhraseCount%2 != 0){
				repeatPhraseCount = (random.nextInt(2) * 2) + 1;
			}
		}else{
			repeatPhraseCount = (random.nextInt(4) * 2) + 1;
			
			while(repeatPhraseCount%2 != 0 && repeatPhraseCount%3 == 0){
				repeatPhraseCount = (random.nextInt(4) * 2) + 1;
			}
		}
		
		ArrayList<Phrase> subPattern = new ArrayList<>();
		
		return pattern;
	}
}
