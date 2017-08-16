import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Attend {
	public static void main(String[] args) throws Exception{
		sendPost();
	}
	
	public static void sendPost() throws Exception{
		URL url = new URL("http://attend.yancyparedes.net/check/");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		String parameters = "api_key=a49fd004d58aeebf7b9a3c1185ab9ac0a71e5f26262cff17039c7871a99c2920&student_id=121789";
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
		
		Scanner in = new Scanner(con.getInputStream());
		StringBuffer sb = new StringBuffer();
		
		while(in.hasNext()){
			sb.append(in.nextLine());
		}
		in.close();
		System.out.println(sb.toString());
		
	}
}
