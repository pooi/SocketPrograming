import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WebBrowser {

    private String url;
    private String host;
    private int port;
    private String userAgent;
    private String header;
    private String content;

    public void init(){
        url = "/";
        host = "";
        port = 80;
        userAgent = "Chrome/7.0.517.44";
        header = "";
        content = "";
    }

    public WebBrowser(RequestBuilder builder){
        init();
        this.url = builder.url;
        this.host = builder.host;
        this.port = builder.port;
        this.userAgent = builder.userAgent;
    }

    public boolean connect(){

        try{

            Socket clientSocket = new Socket(host, port);
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
            pw.print("GET " + url + " HTTP/1.1\r\n"+
                    "Host: " + host + "\r\n" +
                    "User-Agent: " + userAgent + "\r\n" +
                    "Accept: text/html,application/xhtml+xml\r\n" +
                    "Accept-Charset: ISO-8859-1,utf-8;q=0.7\r\n" +
                    "Keep-Alive: 115\r\n" +
                    "Connection: keep-alive\r\n" +
                    "\r\n");
            pw.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String t;
            String readLine = "";
            while((t = in.readLine()) != null){
                readLine += t + "\n";
            }
            in.close();

            clientSocket.close();

            int index = readLine.indexOf("\n\n");

            header = readLine.substring(0, index);
            content = readLine.substring(index+2);

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public String getHeader(){
        return header;
    }
    public String getContent(){
        return content;
    }

//	pw.print("GET / HTTP/1.1\r\n"+
//			"Host: www.bugs.co.kr\r\n" +
//			"User-Agent: Firefox/3.6.10\r\n" +
//			"Accept: text/html,application/xhtml+xml\r\n" +
//			"Accept-Charset: ISO-8859-1,utf-8;q=0.7\r\n" +
//			"Keep-Alive: 115\r\n" +
//			"Connection: keep-alive\r\n" +
//			"\r\n");

    public static class RequestBuilder{


        private String url;
        private String host;
        private int port;
        private String userAgent;

        public void init(){
            url = "/";
            host = "";
            port = 80;
            userAgent = "Chrome/7.0.517.44";
        }

        public RequestBuilder(String host){
            init();
            this.host = host;
//            if(!url.startsWith("/")){
//                url = "/" + url;
//            }
            int index = host.indexOf("/");
            if(index > 0){
                String hostTemp = host.substring(0, index);
                String urlTemp = host.substring(index);
                this.host = hostTemp;
                this.url = urlTemp;
            }

        }

        public RequestBuilder setUrl(String url){
            this.url = url;
            return this;
        }

        public RequestBuilder setPort(int port){
            this.port = port;
            return this;
        }

        public RequestBuilder setUserAgent(String userAgent){
            this.userAgent = userAgent;
            return this;
        }

        public WebBrowser build(){
            return new WebBrowser(this);
        }

    }

}
