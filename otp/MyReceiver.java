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
