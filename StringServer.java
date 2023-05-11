import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    List<String> words = new ArrayList<>();
    String val = "";
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            val = "";
            for(String s : words){
                val += s;
                val += "\n";
            }
            return val;
        }
        else if(url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                val = "";
                words.add(parameters[1]);
                for(String s : words){
                    val += s;
                    val += "\n";
                }
                return val;
            }
            else{
                return "404 Not Found";
            }
        }
        else if(url.getPath().equals("/clear")){
            val = "";
            words.removeAll(words);
            return "Words Cleared.";
        }
        else{
            return "404 Not Found";
        }
    }
}    

class StringServer{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}       