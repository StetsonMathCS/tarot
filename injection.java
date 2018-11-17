import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class injection {

    public static void main(String[] args) {
        System.out.println("SQL-Injection \n");
        System.out.println("Enter your link of the website : ");
        String link = new Scanner(System.in).next();

        HttpURLConnection httpURLConnection = null;

        try {
            new URL(link);

            link += "%27";

            if (!link.contains("?")) {
                System.out.println("The website isn’t vulnerable");
            }
            else {
                URL url = new URL(link);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String input;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (input = bufferedReader.readLine()) != null) {
                    stringBuilder.append(input);
                }

                String content = stringBuilder.toString();

                if (content.contains("mysql_fetch_array()") || content.contains("Exception") || content.contains("missing") || content.length() <=0) {
                    System.out.println("The website is vulnerable");
                }else {
                    System.out.println("The website isn’t vulnerable");
                }
                bufferedReader.close();
            }
        } catch (MalformedURLException e) {
            System.out.println("Enter a valid link");
        } catch (IOException e) {
            assert httpURLConnection != null;

            try {
                int res_code =httpURLConnection.getResponseCode();

                if (res_code == 500) {
                    System.out.println("The website is vulnerable");
                }else {
                    System.out.println("The website isn’t vulnerable");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}