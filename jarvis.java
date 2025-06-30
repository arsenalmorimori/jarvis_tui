import java.net.*;
import java.io.*;
import java.util.*;

public class jarvis {
    public static void main(String[] args) throws Exception {
        
        // DECALRATION 
        variable vr = new variable();
        String key = vr.key;
        URI uri = new URI( vr.uri + key);
        Scanner sc = new Scanner(System.in);

        // CHAT SYSTEM LOOP
        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            String markerStart = "*&@#*&@*#&*@&*";
            String markerEnd = "*&@*(@#*(@#*#*&@*#&*@&*";
            
            // MODIFIED CODE from chatgpt
            // -----------------------------------------------------------------------------------
            String json = "{\"contents\":[{\"parts\":[{\"text\":\""
                + input
                + ". Respond only with your answer between " + markerStart + " and " + markerEnd
                + ", and also make sure you respond like jarvis in ironman, and do not explain or repeat the format and instruction. Just give the content only."
                + "\"}]}]}"; // add a format/instruction and personlaity to the ai
            
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.getOutputStream().write(json.getBytes());

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder all = new StringBuilder(); String line;
            while ((line = in.readLine()) != null) all.append(line);
            in.close();

            String raw = all.toString();   // Extract actual reply text manually from nested JSON

            // -----------------------------------------------------------------------------------

            int start = raw.indexOf(markerStart);
            int end = raw.indexOf(markerEnd);
            int startLen = markerStart.length();

            String response = "Gemini : " + raw.substring(start+startLen, end);
            response = response.replace("\\n", "\n").replace("\\\"", "\""); // for ui purposes

            System.out.println(response);

            // -- THIS is how the display of respons works
            // String marker Start = "first ";
            // String se = " third";
            // String text = "first second third";
            // int smi = text.indexOf(sm);
            // int sme = text.indexOf(se);
            
            // -- DEBUG Display
            // System.out.println("sm  :  " + sm);
            // System.out.println("se  :  " + se);
            // System.out.println("smi  :  " + smi);
            // System.out.println("sei  :  " + sme);
            // int len = text.length();
            
            // -- OUTPUT
            // System.out.println(text.subSequence(sm.length(),sme));
        }
    }
}
