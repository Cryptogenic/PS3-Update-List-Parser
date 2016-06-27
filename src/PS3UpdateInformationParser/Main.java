/* This package is distributed under the Apache 2.0 License. */
package PS3UpdateInformationParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Parses the latest ps3-updatelist.txt
 *
 * @author  Specter
 * @version 1.0
 * @since   2016-06-26
 */
public class Main {
    /**
     * The main entry point
     * <p>
     * @author Specter
     * @param  args  Contains arguments passed to the program. None are used.
     */
    public static void main(String[] args) {
        try
        {
            // Stores the contents fetched from the site to parse
            String contents;
            
            contents = getWebContents("http://fus01.ps3.update.playstation.net/update/ps3/list/us/ps3-updatelist.txt");
            contents = contents.replace("# US", ""); // We don't need this comment.
            
            // A quick hack to make the first CDN= tag to CDNPATCH for easier parsing
            contents = contents.replace("IncrementalUpdateVersion=0000feda-0000feda;ImageVersion=0001036c;SystemSoftwareVersion=4.8000;CDN=",
"IncrementalUpdateVersion=0000feda-0000feda;ImageVersion=0001036c;SystemSoftwareVersion=4.8000;CDNPATCH=");
            
            HashMap updateListInfo = parseUpdateList(contents);
        }
        catch(Exception E)
        {
            System.out.println("An exception occured while trying to parse the ps3-updatelist.txt file.");
        }
    }
    
    /**
     * Parses the given string into a hashmap
     * <p>
     * @author Specter
     * @param  buffer  Contains the sanatized string from playstation.net of ps3-updatelist.txt
     * @return updateInfo Contains a hashmap of the parsed information
     */
    public static HashMap<String, String> parseUpdateList(String buffer)
    {
        // Create an empty hashmap for later usage
        HashMap updateInfo = new HashMap();
        
        String[] elements = buffer.split(";");
        
        // For every element we get in the file, iterate
        for(String element: elements)
        {
            // Split by '=', on the left is the key, right is value
            String[] components = element.split("=");
            
            updateInfo.put(components[0], components[1]);
        }
        
        // Finally return
        return updateInfo;
    }
    
    /**
     * Fetches the content of the given webpage via the 'url' parameter.
     * <p>
     * @author Specter
     * @param url Contains the URL to navigate to for contents.
     * @return contents Contains the contents from the webpage specified via url parameter
     * @throws Exception If the web-server could not be reached.
     */
    public static String getWebContents(String url) throws Exception
    {
        URL server = new URL(url);
        
        URLConnection serverConnection = server.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
        
        StringBuilder contents = new StringBuilder();
        String inputLine;
        
        while((inputLine = in.readLine()) != null)
        {
            contents.append(inputLine);
        }
        
        in.close();
        
        return contents.toString();
    }
}
