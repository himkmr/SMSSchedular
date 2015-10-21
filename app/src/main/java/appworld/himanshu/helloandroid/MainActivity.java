package appworld.himanshu.helloandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    static int info_entered = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("App Create Method");
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showInputForm(View view)
    {
        ((EditText) findViewById(R.id.phone_number)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.message)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.phone_number)).setText("");
        ((EditText) findViewById(R.id.message)).setText("");
        ((TextView)findViewById(R.id.message_label)).setText(R.string.message_label);
        ((TextView)findViewById(R.id.phone_label)).setText(R.string.phone_label);
        findViewById(R.id.scrollView).setEnabled(true);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.back_button).setVisibility(View.GONE);
    }
    public void ShowInfoEntered(View view)
    {
            long num = Long.parseLong(((EditText) findViewById(R.id.phone_number)).getText().toString());
            String phone_num = "Recipient:\n"+((EditText)findViewById(R.id.phone_number)).getText().toString();
            String message = "Message:\n"+((EditText)findViewById(R.id.message)).getText().toString()+"\n\n";
            ((EditText) findViewById(R.id.phone_number)).setVisibility(View.GONE);
            ((EditText) findViewById(R.id.message)).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.message_label)).setText(message);
            ((TextView)findViewById(R.id.phone_label)).setText(phone_num);
            findViewById(R.id.scrollView).setEnabled(true);
            findViewById(R.id.button).setVisibility(View.GONE);
            findViewById(R.id.back_button).setVisibility(View.VISIBLE);
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("sms:" + num) );
        intent.putExtra("sms_body", message);
      //  startActivity(intent);
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phone_num, null, message, null, null);
    }
}
