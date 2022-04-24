package com.example.texteditor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView disp;
    Button btn;
    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar=Calendar.getInstance();
    DatabaseReference Root= FirebaseDatabase.getInstance().getReference();
    DatabaseReference container=Root.child("container");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText) findViewById(R.id.editTextTextMultiLine);
        disp=(TextView) findViewById(R.id.display);
        btn=(Button) findViewById(R.id.button8);



    }

    @Override
    protected void onStart() {
        super.onStart();
        container.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                disp.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setValue("hello!!!");
            }
        });
    }

    public void buttonBold(View view){
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                editText.getSelectionStart(),
                editText.getSelectionEnd(),
                0);

        editText.setText(spannableString);
    }
    public void buttonItalics(View view){
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                editText.getSelectionStart(),
                editText.getSelectionEnd(),
                0);

        editText.setText(spannableString);

    }
    public void buttonUnderline(View view){
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        spannableString.setSpan(new UnderlineSpan(),
                editText.getSelectionStart(),
                editText.getSelectionEnd(),
                0);

        editText.setText(spannableString);
    }

    public void buttonNoFormat(View view){
        String stringText = editText.getText().toString();
        editText.setText(stringText);
    }


    public void buttonAlignmentLeft(View view){
        editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        editText.setText(spannableString);
    }

    public void buttonAlignmentCenter(View view){
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        editText.setText(spannableString);
    }

    public void buttonAlignmentRight(View view){
        editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString = new SpannableStringBuilder(editText.getText());
        editText.setText(spannableString);
    }



    @SuppressLint("SetTextI18n")
    public void curruntDateTime(View view) {

        try{
            Date dateAndTime=Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            SimpleDateFormat timeFormat =new SimpleDateFormat("hhmmss", Locale.getDefault());
            String d=dateFormat.format(dateAndTime);
            String t=timeFormat.format(dateAndTime);
          // String postedOn= DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
           //String f1= DateFormat.getTimeInstance(DateFormat.MINUTE_FIELD).format(calendar.getTime());
          // String dateAndTime=simpleDateFormat.format(calendar.getTime());
          //  String f3= DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
            disp.setText(d+t);
        }catch (Exception e){
            Toast.makeText(this, "Error:"+e, Toast.LENGTH_SHORT)
                    .show();
        }


    }
}