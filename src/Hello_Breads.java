import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;



public class Hello_Breads {
	
	private static final float TIME = 350.0f;
	
	private static ArrayList<Note> mNotes = new ArrayList<>();
	
	private static int lastRandom = -1;

	
	public static void main(String[] args) {
		// create an AudioContext
	    AudioContext ac = new AudioContext();
	    
	    // create a sine generator
	    WavePlayer kickSine = new WavePlayer(ac, 110.0f, Buffer.NOISE);
	    
	    WavePlayer snareSine = new WavePlayer(ac, 110.0f, Buffer.NOISE);
	    
	    Envelope frequencyEnvelope = new Envelope(ac, 440.0f);
	    
	    WavePlayer melodySine = new WavePlayer(ac, frequencyEnvelope, Buffer.TRIANGLE);
	    WavePlayer melodySine2 = new WavePlayer(ac, frequencyEnvelope, Buffer.SAW);
	    WavePlayer melodySine3 = new WavePlayer(ac, frequencyEnvelope, Buffer.SINE);
	    
	    // create an Envelope to control the gain
	    Envelope kickGainEnvelope = new Envelope(ac, 0.0f);
	    
	    Envelope snareGainEnvelope = new Envelope(ac ,0.0f);
	    
	    Envelope melodyGainEnvelope = new Envelope(ac ,0.0f);
	    
	    // create a Gain to control the sine volume
	    Gain kickSineGain = new Gain(ac, 1, kickGainEnvelope);
	    
	    Gain snareSineGain = new Gain(ac , 1 ,snareGainEnvelope);
	    
	    Gain melodySineGain = new Gain(ac , 1 ,melodyGainEnvelope);
	    
	    // add the sine generator as an input to the Gain
	    kickSineGain.addInput(kickSine);
	    
	    snareSineGain.addInput(snareSine);
	    
	    melodySineGain.addInput(melodySine);
	    melodySineGain.addInput(melodySine2);
	    melodySineGain.addInput(melodySine3);
	    
	    
	    // add the Gain as an input to the master output, ac.out
	    ac.out.addInput(kickSineGain);
	    
	    ac.out.addInput(snareSineGain);
	    
	    ac.out.addInput(melodySineGain);
	    
	    Chord chord = new Chord(Note.getRandomNote(3, 5), Chord.ChordType.MINOR_CHORD);
	    
	    ArrayList<Note> chordNotes = chord.getChordNotes();
	    
	    WavePlayer chordSine = new WavePlayer(ac, 110.0f, Buffer.TRIANGLE);
	    Envelope chordGainEnvelope = new Envelope(ac, 0.0f);
	    Gain chordSineGain = new Gain(ac , 1 ,chordGainEnvelope);
	    for(Note note : chordNotes){
	    	chordSine = new WavePlayer(ac,note.getFrequency(), Buffer.TRIANGLE);
	    	chordSineGain.addInput(chordSine);
	    }
	    
	    ac.out.addInput(chordSineGain);
	    
	    Chord chord2 = new Chord(Note.getRandomNote(3, 5), Chord.ChordType.MINOR_CHORD);
	    
	    ArrayList<Note> chordNotes2 = chord2.getChordNotes();
	    
	    WavePlayer chordSine2 = new WavePlayer(ac, 110.0f, Buffer.TRIANGLE);
	    Envelope chordGainEnvelope2 = new Envelope(ac, 0.0f);
	    Gain chordSineGain2 = new Gain(ac , 1 ,chordGainEnvelope2);
	    for(Note note : chordNotes2){
	    	chordSine2 = new WavePlayer(ac,note.getFrequency(), Buffer.TRIANGLE);
	    	chordSineGain2.addInput(chordSine2);
	    }
	    
	    ac.out.addInput(chordSineGain2);
	    
	    // begin audio processing
	    ac.start();
	    
	    
	    
	    for(int i = 0; i< 10 ; i++){
	    	snareSoundMaker(snareGainEnvelope, TIME);
	    	chordSoundMaker(chordGainEnvelope, TIME/2);
		    
			snareSoundMaker(snareGainEnvelope, TIME);
			chordSoundMaker(chordGainEnvelope, TIME);
			
			chordGainEnvelope2.addSegment(0.0f, TIME*2);
			chordGainEnvelope.addSegment(0.0f, TIME/2);
			chordSoundMaker(chordGainEnvelope2, TIME);
		    
		    kickGainEnvelope.addSegment(0.0f, TIME*2);
		    
		    kickSoundMaker(kickGainEnvelope, TIME);
		    
		    snareSoundMaker(snareGainEnvelope, TIME);
		    
		    snareSoundMaker(snareGainEnvelope, TIME);
		    
		    kickGainEnvelope.addSegment(0.0f, TIME);
		    
		    chordGainEnvelope2.addSegment(0.0f, TIME);
			chordGainEnvelope.addSegment(0.0f, TIME*2);

	    }
	    
	    Scale scale = new Scale(Note.getRandomNote(3, 5), Scale.ScaleType.MAJOR_SCALE);
	    mNotes = scale.getScale();
	    
	    melodyMaker(frequencyEnvelope, melodyGainEnvelope, TIME * 10 * 4, 0);	    
		
	}
	
	private static void snareSoundMaker(Envelope envelope,Float time){
		envelope.addSegment(0.4f, 0.0f);
		envelope.addSegment(0.1f, time/3);
		envelope.addSegment(0.0f, time*2/3);
	}
	
	private static void kickSoundMaker(Envelope envelope,Float time){
		envelope.addSegment(0.4f, 0.0f);
		envelope.addSegment(0.2f, time/3);
		envelope.addSegment(0.0f, time*2/3);
	}
	
	private static void chordSoundMaker(Envelope envelope,Float time){
		envelope.addSegment(0.15f, time /5);
		envelope.addSegment(0.0f, time *4/5);
	}
	
	private static void melodyMaker(Envelope frequencyEnvelope, Envelope gainEnvelope, float totalTime, float usedTime){
		Random random = new Random();
		
		
		Timer timer = new Timer();
		
		
		if(totalTime > usedTime){
				if(lastRandom < 0){
					lastRandom = random.nextInt(16);
				}else{
					if (random.nextBoolean()) {
						frequencyEnvelope.addSegment(mNotes.get(lastRandom - (random.nextInt(2)+1)).getFrequency(), 0.1f);
					}else{
						frequencyEnvelope.addSegment(mNotes.get(lastRandom + (random.nextInt(2)+1)).getFrequency(), 0.1f);
					}
				}
				

				
				float increaseTime = random.nextInt(100) + 50;
				float decreaseTime = random.nextInt(500) + 50;
				float waitTime = random.nextInt(350) + 50 ;
				final float usedTimeTemp= usedTime + increaseTime + decreaseTime + waitTime;
				
				gainEnvelope.addSegment(0.7f, increaseTime);
				gainEnvelope.addSegment(0.2f, decreaseTime);
				gainEnvelope.addSegment(0.0f, waitTime);
				
				
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						melodyMaker(frequencyEnvelope,gainEnvelope,totalTime,usedTimeTemp);
					}
				}, (long) (increaseTime + decreaseTime+ waitTime)-5);
			
		}
		
	}

}
