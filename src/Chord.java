import java.util.ArrayList;

public class Chord {
	
	//Members for measure musical measurement
	private static final int ONE_MEASURE = 2; // one note
	private static final int ONE_AND_HALF_MEASURE = 3; // one and half notes
	private static final int TWO_MEASURE = 4; // two notes
	private static final int TWO_AND_HALF_MEASURE = 5; // two and half notes
	private static final int THREE_MEASURE = 6; // three notes
	private static final int FOUR_MEASURE = 8; // four notes
	private static final int SIX_MEASURE = 12; // six notes

	public enum ChordType{
		MAJOR_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE}),
		MAJOR_6_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,ONE_MEASURE}),
		MAJOR_7_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,TWO_MEASURE}),
		MAJOR_9_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE}),
		MAJOR_11_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE}),
		MAJOR_13_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,TWO_MEASURE}),
		MAJOR_add9_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,FOUR_MEASURE}),
		MAJOR_7_add13_CHORD  (new int[]{TWO_MEASURE,TWO_MEASURE,TWO_MEASURE,SIX_MEASURE}),
		
		MINOR_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE}),
		MINOR_6_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,ONE_MEASURE}),
		MINOR_7_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,ONE_AND_HALF_MEASURE}),
		MINOR_9_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE}),
		MINOR_11_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,TWO_MEASURE}),
		MINOR_13_CHORD  (new int[]{ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,ONE_AND_HALF_MEASURE,TWO_AND_HALF_MEASURE,TWO_MEASURE,TWO_MEASURE}),
		
		SUSPENDED_2_CHORD  (new int[]{ONE_MEASURE,THREE_MEASURE}),
		SUSPENDED_4_CHORD  (new int[]{THREE_MEASURE,ONE_MEASURE}),
		SUSPENDED_4_WITH_7_CHORD  (new int[]{THREE_MEASURE,ONE_MEASURE,TWO_MEASURE});

		private int[] mPattern;
		
		ChordType(int[] pattern){
			mPattern = pattern;
		}
		
		public int[] getPattern(){
			return mPattern;
		}
		
		public int getMeasureAtIndex(int index){
			return mPattern[index];
		}
		
		public int getPatternLength(){
			return mPattern.length;
		}
	}
	
	private Note mReferenceNote;
	private ChordType mChordType;
	
	public Chord(Note referenceNote, ChordType chordType){
		mReferenceNote = referenceNote;
		mChordType = chordType;
	}
	
	public ArrayList<Note> getChordNotes(){
		ArrayList<Note> chordNotes = new ArrayList<>();
		
		chordNotes.add(mReferenceNote);
		Note templateNote = mReferenceNote;
		for(int i = 0; i < mChordType.getPatternLength(); i++){
			templateNote = new Note(templateNote);
			templateNote.increaseMeasure(mChordType.getMeasureAtIndex(i));
			chordNotes.add(templateNote);
		}
		
		return chordNotes;
	}

	public ChordType getChordType() {
		return mChordType;
	}

	public void setChordType(ChordType mChordType) {
		this.mChordType = mChordType;
	}

	public Note getReferenceNote() {
		return mReferenceNote;
	}

	public void setReferenceNote(Note mReferenceNote) {
		this.mReferenceNote = mReferenceNote;
	}
}
