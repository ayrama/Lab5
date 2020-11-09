import java.io.*;
import java.util.*;

//////////////
/*
class Song
 */
class Song {
    protected String songTitle;
    protected int streamsCount;
    protected int days;
    protected String artistName;
    protected Song leftChild;
    protected Song rightChild;
    // -------------------------------------------------------------
    /* constructor
    param@ s name of the song
    param@ a name of the artist
    param@ streams number of streams
    */
    public Song(String s, String a, int streams) {
        songTitle = s;
        artistName = a;
        streamsCount = streams;
        days = 7;
    }
    // -------------------------------------------------------------
    public void setStreamsCount(int number){
        streamsCount += number;
        days += 7;
    }
    // -------------------------------------------------------------
    public int calcAveragePerDay(){
        return streamsCount/days;
    }
    // -------------------------------------------------------------
    public int calcWeeks(){
        return days/7;
    }
    // -------------------------------------------------------------
    /* displaySong() method returns name of the song followed by artist name,
    weeks on Air and average streams per day
    return@ name of the track followed by artist name
    */
    public String displaySong() {
        return "\"" + songTitle + "\"" +" by " + artistName +
                ". Weeks on air: " + calcWeeks() +
                ". Average streams per day: " + calcAveragePerDay();
    }
}  //end class Song
///////////////////////
/* class Playlist  */
class Playlist {
    private Song root;
    private static int pos;
    // -------------------------------------------------------------
    public Playlist(){
        root = null;
        pos = 0;
    }
    // -------------------------------------------------------------

    public Song find(String song)
    {
        Song current = root;
        while(!current.songTitle.equals(song))
        {
            if(song.indexOf('(') != -1)
                if(sub(song).compareToIgnoreCase(sub(current.songTitle)) < 0)
                    current = current.leftChild;
                else
                    current = current.rightChild;
                if(current == null)
                    return null;
            else
                if(song.compareToIgnoreCase(current.songTitle) < 0)
                    current = current.leftChild;
                else
                    current = current.rightChild;
                if(current == null)
                    return null;
        }
        return current;
    }
    // -------------------------------------------------------------
    public String sub(String s){
        if(s.indexOf('(') == -1)
            return s;
        else
            return s.substring(0, s.indexOf('('));
    }
    // -------------------------------------------------------------
    public void insert(String song, String artist, int streams)
    {
        Song newSong = new Song(song, artist, streams);
        if(root==null)
            root = newSong;
        else
        {
            Song current = root;
            Song parent;
            while(true) {
                parent = current;
                if (song.indexOf('(') != -1) {
                    if (sub(song).equalsIgnoreCase(sub(current.songTitle))) {
                        current.setStreamsCount(streams);
                            return;
                    } else if (sub(song).compareToIgnoreCase(sub(current.songTitle)) < 0) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = newSong;
                            return;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = newSong;
                            return;
                        }
                    }
                } else {
                    if (song.equalsIgnoreCase(current.songTitle)) {
                        current.setStreamsCount(streams);
                        return;
                    } else if (song.compareToIgnoreCase(current.songTitle) < 0) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = newSong;
                            return;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = newSong;
                            return;
                        }
                    }
                }
            }
        }
    } // end insert()
    // -------------------------------------------------------------
    public void Traverse(){
        String s = "====================================";
        s += s;
        String d = "------------------------------------";
        d += d;
        System.out.println(s + "\nSongs in Alphabet order:\n" + d);
        inOrder(root);
    }
    // -------------------------------------------------------------
    public void inOrder(Song localRoot){
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.println(++pos + ". " + localRoot.displaySong());
            inOrder(localRoot.rightChild);
        }
    }
} //end of Playlist class
/////////////////////////////////////////////////
class Artist{
    protected int streamsCount;
    protected int days;
    protected String artistName;
    protected Artist leftChild;
    protected Artist rightChild;
    // -------------------------------------------------------------
    public Artist(String a, int streams) {
        artistName = a;
        streamsCount = streams;
        days = 7;
    }
    // -------------------------------------------------------------
    public Artist(String a, int streams, int days){
        artistName = a;
        streamsCount = streams;
        this.days = days;
    }
    // -------------------------------------------------------------
    public void setStreamsCount(int number){
        streamsCount += number;
        days += 7;
    }
    // -------------------------------------------------------------
    public int calcAveragePerDay(){
        return streamsCount/days;
    }
    // -------------------------------------------------------------
    public int calcWeeks(){
        return days/7;
    }
    // -------------------------------------------------------------
    public String displayArtist() {
        return artistName + ". Average streams per day: " + calcAveragePerDay();
    }
}
/////////////////////////////////////////////////
class ArtistList {
    private Artist root;
    private static int pos;
    // -------------------------------------------------------------
    public ArtistList(){
        root = null;
        pos = 0;
    }
    // -------------------------------------------------------------
    public void insert(String artist, int streams)
    {
        Artist newArtist = new Artist(artist, streams);
        if(root==null)
            root = newArtist;
        else
        {
            Artist current = root;
            Artist parent;
            while(true) {
                parent = current;
                if (newArtist.artistName.equalsIgnoreCase(current.artistName)) {
                    current.setStreamsCount(streams);
                    return;
                } else if (newArtist.artistName.compareToIgnoreCase(current.artistName) < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newArtist;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newArtist;
                        return;
                    }

                }
            }
        }
    } // end insert()
    // -------------------------------------------------------------
    public void Traverse(){
        String s = "====================================";
        s += s;
        String d = "------------------------------------";
        d += d;
        System.out.println(s + "\nArtists in Alphabet order:\n" + d);

        inOrder(root);
    }
    // -------------------------------------------------------------
    public void inOrder(Artist localRoot){
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.println(++pos + ". " + localRoot.displayArtist());
            inOrder(localRoot.rightChild);
        }
    }
}//end of ArtistTop class
/////////////////////////////////////////////////
/* Lab5 class is the class with main method
 */
public class Lab5 {
    /* main method */
    public static void main(String[] args) throws IOException {
        // creating of "Australia top 200" binary tree for last 4 weeks
        // will be arranged in ascending order in printed output file
        Playlist AustraliaTop = new Playlist();
        ArtistList AustArtistList = new ArtistList();
        String path = "C:\\Users\\ayram\\Desktop\\Fall 2020 HomeWorks\\Data Structure\\HomeWorks\\Lab5\\data\\";
        String line = "";
        // 4 last weeks
        // 4 .csv files will be read.
        for (int i = 1; i <= 4; i++) {
            try {
                String s = i + ".csv";
                BufferedReader reader = new BufferedReader(new FileReader(path + s));
                // first 2 lines are description before the table
                //that's why I've read them and have not stored in an array
                reader.readLine();
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] array = line.split(",");
                    if(array[1].charAt(0) == '"') {
                        array[1] = array[1].substring(1, array[1].length()-1);
                    }
                    if(array[2].charAt(0) == '"') {
                        array[2] = array[2].substring(1, array[2].length()-1);
                    }
                    AustraliaTop.insert(array[1], array[2].trim(), Integer.parseInt(array[array.length-2]));
                    AustArtistList.insert(array[2].trim(), Integer.parseInt(array[array.length-2]));
                }
                reader.close();
            }
            catch (IOException ep) {
                ep.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("No such file exists.");
            }
        }
        AustraliaTop.Traverse();

        AustArtistList.Traverse();
    }
} // end of Lab5 class

