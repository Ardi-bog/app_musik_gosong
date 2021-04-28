package com.example.boss.go_song;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class profil extends Fragment {
    Button btn_logout;
    TextView txt_id, txt_username;
    SharedPreferences sharedpreferences;

    String id, email;

    public static final String TAG_ID = "id";
    public static final String TAG_EMAIL = "email";

    public profil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);

        txt_username = (TextView) v.findViewById(R.id.email);
        txt_id = (TextView) v.findViewById(R.id.password);
        btn_logout = (Button) v.findViewById(R.id.logout);

        sharedpreferences = this.getActivity().getSharedPreferences(login.my_shared_preferences, Context.MODE_PRIVATE);

        id = this.getActivity().getIntent().getStringExtra(TAG_ID);
        email = this.getActivity().getIntent().getStringExtra(TAG_EMAIL);

        txt_id.setText("ID : " + id);
        txt_username.setText("EMAIL : " + email);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_EMAIL, null);
                editor.commit();

                Intent intent = new Intent(getActivity(), login.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return v;
    }

}
