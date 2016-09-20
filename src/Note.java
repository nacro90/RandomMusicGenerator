import java.util.Random;

/**
 * This class is created for store notes and their frequencies.
 * With calculations every notes in every pitch class can be created by this class with the help of {@link ChromaticScaleTone}
 *
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * Frequency calculation:
 *
 * In the {@link ChromaticScaleTone}, there are notes with zero pitch class frequencies.
 * The pitch class calculation for same note is:
 *
 * X -> Note
 * n -> Target pitch class
 *
 * Xn = X0 * 2ⁿ
 *
 *
 * Example for C5:
 *
 * C5 = C0 * 2⁵
 *
 * float C0 = ChromaticScaleTone.C.getZeroFrequency();
 *
 * float C5 = C0 * Math.pow(2, 5);
 *
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * Semitone offset calculation:
 *
 * n  -> Semitone count
 * f0 -> Original frequency
 * f' -> Calculated frequency
 *
 * f' = f0 * 2^(n / 12)
 *
 *
 * Example +2 Semitone for A4:
 *
 * A + 2 semitone. A4 = 440 Hz. So:
 *
 * A + 2 = 440 * 2^(+2 / 12)
 *
 * A + 2 SemiTone = B = 493.88
 *
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * Semitone offset change does not affect mChromaticScaleTone.
 * Notes letter notation is always same until directly changed.
 *
 * So Semitone Offset feature should be used for sound transitions, not the new note creations.
 *
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * Created by Orcan on 9/10/2016.
 */

public class Note {

    public static final int MAX_PITCH_CLASS = 9;

    /**
     *  This enum stores 12-Tone Chromatic Scale notes and their frequencies at zero pitch class.
     */
    public enum ChromaticScaleTone {
        C   (16.35f),
        Db  (17.32f),
        D   (18.35f),
        Eb  (19.45f),
        E   (20.60f),
        F   (21.83f),
        Gb  (23.12f),
        G   (24.50f),
        Ab  (25.96f),
        A   (27.50f),
        Bb  (29.14f),
        B   (30.87f);

        private float mZeroFrequency;

        ChromaticScaleTone(float zeroFrequency) {
            mZeroFrequency = zeroFrequency;
        }

        public float getZeroFrequency() {
            return mZeroFrequency;
        }
    }

    private ChromaticScaleTone mChromaticScaleTone;
    private int mPitchClass;

    public Note(ChromaticScaleTone chromaticScaleTone, int pitchClass) {
        mChromaticScaleTone = chromaticScaleTone;
        mPitchClass = pitchClass;
    }
    
    public Note(Note note) {
        mChromaticScaleTone = note.getChromaticScaleTone();
        mPitchClass = note.getPitchClass();
    }

    /**
     * Calculate Notes last frequency with properties. All calculations explained at the beginning of this class.
     */
    public float getFrequency() {

        float frequencyRaw = (float) (mChromaticScaleTone.mZeroFrequency * Math.pow(2, mPitchClass));

        return frequencyRaw;
    }

    public ChromaticScaleTone getChromaticScaleTone() {
        return mChromaticScaleTone;
    }

    public void setChromaticScaleTone(ChromaticScaleTone chromaticScaleTone) {
        mChromaticScaleTone = chromaticScaleTone;
    }

    public int getPitchClass() {
        return mPitchClass;
    }

    public void setPitchClass(int pitchClass) {
        mPitchClass = pitchClass;
    }

    public void increaseMeasure(int measure) {
    	if(measure > 0){
    		int index = 0;
        	while(mChromaticScaleTone.getZeroFrequency() != ChromaticScaleTone.values()[index].getZeroFrequency()){
        		index++;
        	}
        	mPitchClass = ((mPitchClass *12) + index + measure) / ChromaticScaleTone.values().length;
        	
        	mChromaticScaleTone = ChromaticScaleTone.values()[(index + measure) % ChromaticScaleTone.values().length];
    	}
    }
    
    public static Note getRandomNote(int pitchClassStartThreshold, int pitchClassEndThreshold){
    Random random = new Random();
    int randomNoteIndex = random.nextInt(ChromaticScaleTone.values().length);
    int randomNotePitchClass = random.nextInt(pitchClassEndThreshold - pitchClassStartThreshold) + pitchClassStartThreshold; 
    Note randomNote = new Note(ChromaticScaleTone.values()[randomNoteIndex],randomNotePitchClass);
    return randomNote;
    }

    @Override
    public String toString() {
        String s = "Note: " + mChromaticScaleTone.name() + mPitchClass + " frequency: " + getFrequency();
        return s;
    }
}