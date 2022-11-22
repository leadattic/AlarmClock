import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.*;
public class Main {
    static boolean stop;
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        /*TimeUnit.SECONDS.sleep(1L);*/

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");

        //System.out.println(dtf.format(LocalDateTime.now()));
        stop = false; //default of


        System.out.println("When do you want the alarm to go off? (hh:mm:ss)");
        Scanner scanner = new Scanner(System.in);
        String chosenTime = scanner.nextLine();
        System.out.println("Chosen time: " + chosenTime);
        chosenTime = chosenTime.replaceAll(":", "");

        while (!stop){

            if(dtf.format(LocalDateTime.now()).equals(chosenTime)) {

                alarmGoOff();


            }

            System.out.println("Current time: " + dtf.format ( LocalDateTime.now( ) ) );

            TimeUnit.SECONDS.sleep(1L);


        }

    }

    public static void alarmGoOff() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File("beep.wav");
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();

        clip.open(stream);
        clip.start();

        Scanner sc = new Scanner(System.in);

        stop = true;
        TimeUnit.SECONDS.sleep(1L);

        while (stop){
            clip.setMicrosecondPosition(0);
            clip.start();
            TimeUnit.SECONDS.sleep(1L);
        }


        //play sound
    }
}