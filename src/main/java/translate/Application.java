package translate;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.io.InputStreamReader;

public class Application { 
	private static int i = 0;
	public static void main(String[] args) throws IOException {
		String text = "";
		for (int i = 0; i < args.length; i++){
			text += args[i] + " ";
		}
		System.out.println(translate("en-ru", text));
	}

	private static String translate(String lang, String input) throws IOException {
		String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170303T184009Z.1358732cf6f0ec68.87b4e8a5c3f2622dc43ea8befd09a098665b0c2b";
		URL urlObj = new URL(urlStr);
		HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
		dataOutputStream.writeBytes("text=" + input + "&lang=" + lang);

		InputStreamReader response = new InputStreamReader(connection.getInputStream(), "UTF-8");
		Scanner sc = new Scanner(response);
		String json = sc.nextLine();
		String translation = json.substring(json.indexOf("[") + 2, json.indexOf("]") - 1);

		return translation;
	}
}
