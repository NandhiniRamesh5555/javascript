


About this Activity:
One of the Basic activity of an Android application is OTP (One Time Password) verification, which is required in signing up process.
Nowadays, this step becomes more efficient, where a message is sent by the service-provider and it is automatically read by the application, verifying it. This flow helps user to save a lot of app switching from application to messenger app and then entering the authentication text to app again.
Broadcast Receiver:
Broadcast receiver is an Android component that  listens to system-wide broadcast events or intents. All the registered application are notified by the Android runtime once event happens. (When any of these events occur it brings the application into action by either creating a status bar notification or performing a task).
So, in this activity, we use Broadcast Receiver to detect the incoming SMS to that device and retrieve OTP from that message.

Steps to do this Auto_Detect_Verify OTP Activity:
Step1: Create a New Project in Android Studio.
            In Android Studio, File ⇒ New ⇒ New Projects 
Step 2: Creating Myreceiver.java class:
To perform the broadcast Receiver opertaion, we need to create a new class (here it is MyReceiver.java) 
Onclicking & mouse over the Java folder in the left side Project Pallete, 
    Java=>Right click=>New=>Java class
Step 3: Configuring MyReceiver.java:










package com.example.otp_v2;
import android.app.Service;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.provider.Telephony;
        import android.telephony.SmsMessage;
        import android.telephony.TelephonyManager;
        import android.widget.EditText;
        import android.widget.Toast;
        import static androidx.core.content.ContextCompat.startActivity;
public class MyReceiver extends BroadcastReceiver {
    private static EditText editText;
    public void setEditText(EditText editText) {
        MyReceiver.editText = editText;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            int state = tm.getCallState();
            String num = intent.getStringExtra("incoming_number");
            Toast.makeText(context,"Ringing number:"+num, Toast.LENGTH_LONG).show();
            if(state == TelephonyManager.CALL_STATE_OFFHOOK)
            {
                Toast.makeText(context,"Call Active",Toast.LENGTH_LONG).show();
            }
            if(state == TelephonyManager.CALL_STATE_IDLE)
            {
                Toast.makeText(context,"NO Call",Toast.LENGTH_LONG).show();
            }
            if(state == TelephonyManager.CALL_STATE_RINGING)
            {
                Toast.makeText(context,"Ringing State",Toast.LENGTH_LONG).show();
            }
        }
        else{
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for(SmsMessage sms:msgs) {
                String message = sms.getMessageBody();
                data.OTP = message.split(":")[1];
                editText.setText(data.OTP);
                String number  = sms.getOriginatingAddress();
                Toast.makeText(context,"message:"+ message,Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"number:"+ number,Toast.LENGTH_SHORT).show();
                if((data.OTP).equals(editText.getText().toString()))
                {
                    Toast.makeText(context,"OTP Verification success",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, SecondActivity.class);
                        context.startActivity(i);
                    }
                }
            }
        }
    }








Step 3: Configuring MainActivity.java file:
There will be two components in the OTP screen,They are:
A EditText (to Enter OTP Received)
A Button (to Verify OTP & Take us into next page if OTP is entered correct)

