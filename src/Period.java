import java.util.ArrayList;

public class Period {

	private int[] mCommonTime = new int[2]; //first index for beat per phrase, second index for total beat for per measure
	private int mBPM; //beats per minute
	private float mMSPB; //miliseconds per beat (defines how long a single beat takes)
	private ArrayList<Phrase> mPhrases = new ArrayList<>();
	
	public Period(int phraseBeatCount, int totalBeatCount, int bpm, ArrayList<Phrase> phrases){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = bpm;
		mMSPB = bpm / (60 * 60);
		mPhrases = phrases;
	}
	
	public Period(int phraseBeatCount, int totalBeatCount, float mspb, ArrayList<Phrase> phrases){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = (int)mspb * 60 * 60;
		mMSPB = mspb;
		mPhrases = phrases;
	}
	
	public Period(int phraseBeatCount, int totalBeatCount, int bpm, Phrase phrase){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = bpm;
		mMSPB = bpm / (60 * 60);
		mPhrases.add(phrase);
	}
	
	public Period(int phraseBeatCount, int totalBeatCount, float mspb, Phrase phrase){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = (int)mspb * 60 * 60;
		mMSPB = mspb;
		mPhrases.add(phrase);
	}
	
	public Period(int phraseBeatCount, int totalBeatCount, int bpm){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = bpm;
		mMSPB = bpm / (60 * 60);
	}
	
	public Period(int phraseBeatCount, int totalBeatCount, float mspb){
		mCommonTime[0] = phraseBeatCount;
		mCommonTime[1] = totalBeatCount;
		mBPM = (int)mspb * 60 * 60;
		mMSPB = mspb;
	}
	
	public void setPhrases(ArrayList<Phrase> phrases){
		mPhrases = phrases;
	}
	
	public void addPhrase(Phrase phrase){
		mPhrases.add(phrase);
	}
	
	public ArrayList<Phrase> getPhrases(){
		return mPhrases;
	}
	
	public ArrayList<Phrase> getLastPhrases(int indexFromLast){
		ArrayList<Phrase> phrases = new ArrayList<>();
		for(int i = mPhrases.size() - indexFromLast; i < mPhrases.size() ; i++){
			phrases.add(mPhrases.get(i));
		}
		return phrases;
	}
	
	public int[] getCommonTime(){
		return mCommonTime;
	}
	
	public int getBPM(){
		return mBPM;
	}
	
	public float getMSPB(){
		return mMSPB;
	}
	
}
