package vn.sefviapp.sdksefvi;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SefviLogin {
    private Dialog dialog;
    private Context context;
    private static String sefviUserEmail;
    private static String sefviUserUsername;
    private static String sefviUserName;
    private static String sefviUserFirstName;
    private static String sefviUserLastName;
    private static String sefviUserGender;
    private static String sefviUserAvatar;
    private static String sefviUserCover;
    private static String sefviUserIsPro;
    private static String sefviUserLanguage;
    private static String sefviUserVerified;
    private static String sefviUserLastseen;
    private static String sefviUserAddress;
    private static String sefviUserAbout;

    public SefviLogin(Context context) {
        this.context = context;
    }

    public void sefviAppView(final String appSefviApi, final String urlAppWebsite, final String urlApploginOrRe ){
        WindowManager.LayoutParams sefviLayout = new WindowManager.LayoutParams();
        sefviLayout.width = 300;
        sefviLayout.height = 200;
        final WebView webView = new WebView(context);
        webView.setLayoutParams(sefviLayout);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(context.getCacheDir().getPath());
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if(url.contains(urlAppWebsite)){
                    if (url.contains(urlApploginOrRe)){
                        getJSON(url);
                        dialog.dismiss();
                    }else {
                        dialog.dismiss();
                    }
                }else if (url.equals("https://sefvi.com/")){
                    webView.loadUrl("https://sefvi.com/oauth?app_id=" + appSefviApi);
                }
                return true;
            }

            public void onPageFinished(WebView view1, String url){
                super.onPageFinished(view1, url);
                if (url.contains("https://sefvi.com/oauth?app_id=")){
                    webView.loadUrl(
                            "javascript:(function() { "
                                    + "var mainWap = document.getElementsByClassName('container')[0];"
                                    + "mainWap.style.display = 'block';" +
                                    "})()"
                    );
                }else {
                    webView.loadUrl(
                            "javascript:(function() { "
                                    + "var navLogin = document.getElementsByClassName('container')[0];"
                                    + "var footerLogin = document.getElementsByClassName('container')[2];"
                                    + "var welcomeLogin = document.getElementsByClassName('sun_welcome_heading')[0];"
                                    + "var t = document.getElementsByClassName('cc-color-override-1874709998')[0];"
                                    + "t.style.display = 'none';"
                                    + "navLogin.style.display = 'none';"
                                    + "footerLogin.style.display = 'none';"
                                    + "welcomeLogin.style.display = 'none';" +
                                    "})()"
                    );
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://sefvi.com/oauth?app_id=" + appSefviApi);
        dialog = new Dialog(context);
        dialog.getWindow().setAttributes(sefviLayout);
        dialog.setContentView(webView);
        dialog.show();
    }
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadViewApp(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadViewApp(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.getInt("status") == 200){
            JSONObject jsonUser = (JSONObject) jsonObject.get("user_data");
            sefviUserUsername = jsonUser.getString("username");
            sefviUserEmail = jsonUser.getString("email");
            sefviUserName = jsonUser.getString("name");
            sefviUserFirstName = jsonUser.getString("first_name");
            sefviUserLastName = jsonUser.getString("last_name");
            sefviUserGender = jsonUser.getString("gender");
            sefviUserAvatar = jsonUser.getString("avatar");
            sefviUserCover = jsonUser.getString("cover");
            sefviUserIsPro = jsonUser.getString("is_pro");
            sefviUserLanguage = jsonUser.getString("language");
            sefviUserVerified = jsonUser.getString("verified");
            sefviUserLastseen = jsonUser.getString("lastseen");
            sefviUserAddress = jsonUser.getString("address");
            sefviUserAbout = jsonUser.getString("about");
        }else {
            Log.d("Err", "Lỗi");
        }
    }

    public static String getSefviUserEmail() {
        return sefviUserEmail;
    }

    public static String getSefviUserUsername() {
        return sefviUserUsername;
    }

    public static String getSefviUserName() {
        return sefviUserName;
    }

    public static String getSefviUserFirstName() {
        return sefviUserFirstName;
    }

    public static String getSefviUserLastName() {
        return sefviUserLastName;
    }

    public static String getSefviUserGender() {
        return sefviUserGender;
    }

    public static String getSefviUserAvatar() {
        return sefviUserAvatar;
    }

    public static String getSefviUserCover() {
        return sefviUserCover;
    }

    public static String getSefviUserIsPro() {
        return sefviUserIsPro;
    }

    public static String getSefviUserLanguage() {
        return sefviUserLanguage;
    }

    public static String getSefviUserVerified() {
        return sefviUserVerified;
    }

    public static String getSefviUserLastseen() {
        return sefviUserLastseen;
    }

    public static String getSefviUserAddress() {
        return sefviUserAddress;
    }

    public static String getSefviUserAbout() {
        return sefviUserAbout;
    }
}
