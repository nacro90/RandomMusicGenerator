
public class PlayableNote {
	
	public enum NoteMeasure {
		WHOLE_NOTE  (4.0f),
		HALF_NOTE  (2.0f),
		DOTED_QUARTER_NOTE  (1.5f),
		QUARTER_NOTE  (1.0f),
		EIGHTH_NOTE  (0.5f);
		
		private float mNoteMeasure;
		
		NoteMeasure(float noteMeasure) {
			mNoteMeasure = noteMeasure;
		}
		
		public float getMeasure(){
			return mNoteMeasure;
		}
	}
	
	public enum NoteType {
		RHYTHM_NOTE  (0),
		MELODY_NOTE  (1),
		REST_NOTE  (2);
		
		private int mNoteType; //RHYTHM_NOTE or MELODY_NOTE
		
		NoteType(int noteType) {
			mNoteType = noteType;
		}
		
		public int getNoteType(){
			return mNoteType;
		}
	}
	
	private Note mNote;
	private NoteType mNoteType;
	private NoteMeasure mNoteMeasure;
	
	
	
	public PlayableNote(Note note, NoteType noteType, NoteMeasure noteMeasure){
		mNote = note;
		mNoteType = noteType;
		mNoteMeasure = noteMeasure;
		
	}

	public Note getNote(){
		return mNote;
	}
	
	public NoteType getNoteType(){
		return mNoteType;
	}
	
	public NoteMeasure getNoteMeasure(){
		return mNoteMeasure;
	}
}
