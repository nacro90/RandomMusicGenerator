import java.util.ArrayList;

public class Scale {
	
	//Members for measure musical measurement
	private static final int HALF_STEP = 1; // half note
	private static final int WHOLE_STEP = 2; // one note
	private static final int ONE_AND_HALF_STEP = 3; // one and half notes
	private static final int TWO_STEPS = 4; // two notes
	
	/**
	 * Type for each scale patterns based on measure between two notes.
	 * 
	 * Caution: 		
	 * 		Make sure you controled scale pattern's lenght before use.
	 * 		Every new octave note you need to reset loop because each octave have 8 notes 
	 * this pattern shows measure between two notes. Every new reference note begin with pattern's first.
	 * 		
	 * @author doruk
	 */
	public enum ScaleType{
		MAJOR_SCALE  (new int[]{WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP}),
		NATUREL_MINOR_SCALE  (new int[]{WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP}),
		HARMONIC_MINOR_SCALE  (new int[]{WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,ONE_AND_HALF_STEP,HALF_STEP}),
		MELODIC_MINOR_SCALE  (new int[]{WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP}),
		MINOR_PENTATONIC_SCALE  (new int[]{ONE_AND_HALF_STEP,WHOLE_STEP,WHOLE_STEP,ONE_AND_HALF_STEP}),
		MAJOR_PENTATONIC_SCALE  (new int[]{HALF_STEP,WHOLE_STEP,TWO_STEPS,HALF_STEP}),
		IONIAN_SCALE  (new int[]{WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP}),
		DORIAN_SCALE  (new int[]{WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP}),
		FRIGIAN_SCALE  (new int[]{HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP}),
		LIDIAN_SCALE  (new int[]{WHOLE_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP}),
		MIXOLIDIAN_SCALE  (new int[]{WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP}),
		EOLIAN_SCALE  (new int[]{WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP}),
		LOKRIAN_SCALE  (new int[]{HALF_STEP,WHOLE_STEP,WHOLE_STEP,HALF_STEP,WHOLE_STEP,WHOLE_STEP,WHOLE_STEP});

		private int[] mPattern;
		
		ScaleType(int[] pattern){
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
	private ScaleType mScaleType;
	
	public Scale(Note referenceNote, ScaleType scaleType){
		mReferenceNote = referenceNote;
		mScaleType = scaleType;
	}
	
	public ArrayList<Note> getScale(){
		ArrayList<Note> scaledNotes = new ArrayList<>();
		scaledNotes.add(mReferenceNote);
		Note templateNote = mReferenceNote;
		for(int i = 0; i < mScaleType.getPatternLength() * (Note.MAX_PITCH_CLASS - mReferenceNote.getPitchClass()) ; i++){
			templateNote = new Note(templateNote);
			templateNote.increaseMeasure(mScaleType.getMeasureAtIndex(i% mScaleType.getPatternLength()));
			scaledNotes.add(templateNote);
		}
		return scaledNotes;
	}
}
