import java.util.ArrayList;

public class Phrase {
	
	private ArrayList<PlayableNote> mNotes = new ArrayList<>();
	
	public Phrase(ArrayList<PlayableNote> notes){
		mNotes = notes;
	}
	
	public Phrase(PlayableNote note){
		mNotes .add(note);
	}
	
	public ArrayList<PlayableNote> getNotes(){
		return mNotes;
	}
	
	public float getPhraseMeasure(){
		float totalMeasure = 0;
		for(PlayableNote note : mNotes){
			totalMeasure += note.getNoteMeasure().getMeasure();
		}
		return totalMeasure;
	}
	
	public boolean isNoteFrequencyIncreasing(){
		if(mNotes.get(0).getNote().getFrequency() > mNotes.get(mNotes.size()).getNote().getFrequency()){
			return true;
		}else{
			return false;
		}	
	}
	
	public PlayableNote.NoteType getNoteTypes(){
		return mNotes.get(0).getNoteType();
	}
	
}
