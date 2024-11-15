package songpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyDataReader {
    
    /**
     * Process a line from the TSV file and returns the corresponding song object
     * @param inputLine TSV line
     * @return songpack.Song object
     */
    private static Song lineToReport(String inputLine)
    {
        String[] items = inputLine.split("\t");
        String title= items[0];
        String tag= items[1];
        String artist= items[2];
        int year= Integer.parseInt(items[3]);
        int views= Integer.parseInt(items[4]);
        String lyrics= items[5];
        
        Song s = new Song(title, tag, artist, year, views, lyrics);
        return s;
    }
    
    /**
     * This method takes in the tsv file path and returns the binary search tree of songs with the given tag
     * @param tsvFilePath tsv file path
     * @param tag one of the six tags: rap, rb, pop, rock, misc, and country
     * @return binary search tree of songs
     * @throws IOException
     */
    public static BinarySearchTree readFileToBST(String tsvFilePath, String tag) throws IOException {
        BinarySearchTree songsBST = new BinarySearchTree();
        BufferedReader TSVReader = new BufferedReader(new FileReader(tsvFilePath));
        String line = TSVReader.readLine();
        while ((line = TSVReader.readLine()) != null) {
            Song song = MyDataReader.lineToReport(line);
                songsBST.insert(song);
        }
        return songsBST;
    }

    /**
     * This method takes in the tsv file path and returns the binary search tree of songs with the given tag
     * @param tsvFilePath tsv file path
     * @param tag one of the six tags: rap, rb, pop, rock, misc, and country
     * @return binary search tree of songs
     * @throws IOException
     */
    public static AVLTree readFileToAVL(String tsvFilePath, String tag) throws IOException
    {
        AVLTree songsAVL = new AVLTree();
        BufferedReader TSVReader = new BufferedReader(new FileReader(tsvFilePath));
        String line = TSVReader.readLine();
        while ((line = TSVReader.readLine()) != null) {
            Song song = MyDataReader.lineToReport(line);
                songsAVL.insert(song);
        }

        return songsAVL;
    }

}
