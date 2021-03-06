package com.example.ammach.formproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import dao.Personne;
import dao.PersonneDao;
import utils.ImageUtil;

public class AddActivity extends AppCompatActivity {

    ImageView profil;
    EditText editNom;
    EditText editPrenom;
    EditText editAge;
    EditText editTravail;

    TextInputLayout editNomLayout;
    TextInputLayout editPrenomLayout;
    TextInputLayout editAgeLayout;
    TextInputLayout editTravailLayout;

    String nom="";
    String prenom="";
    String age="";
    String travail="";
    Uri uri=Uri.parse("null");

    PersonneDao personneDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,PersonnesActivity.class));
            }
        });

        initviews();
    }

    ///////////////////////////////////////////////////////////////////////////
    // initiate views
    ///////////////////////////////////////////////////////////////////////////
    private void initviews() {
        profil=(ImageView) findViewById(R.id.profil);
        editNom=(EditText) findViewById(R.id.editNom);
        editPrenom=(EditText) findViewById(R.id.editPrenom);
        editAge=(EditText) findViewById(R.id.editAge);
        editTravail=(EditText) findViewById(R.id.editTravail);

        editNomLayout=(TextInputLayout) findViewById(R.id.editNomLayout);
        editPrenomLayout=(TextInputLayout) findViewById(R.id.editPrenomLayout);
        editAgeLayout=(TextInputLayout) findViewById(R.id.editAgeLayout);
        editTravailLayout=(TextInputLayout) findViewById(R.id.editTravailLayout);
    }


    ///////////////////////////////////////////////////////////////////////////
    // PICK IMAGE
    ///////////////////////////////////////////////////////////////////////////
    public void pickImage(View v) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    ///////////////////////////////////////////////////////////////////////////
    // IMAGE PICKED
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1) {
            if (resultCode==RESULT_OK) {
                this.uri=data.getData();
                profil.setImageURI(this.uri);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // AJOUTER LA PERSONNE
    ///////////////////////////////////////////////////////////////////////////
    public void ajouter(View v) {
        nom=editNom.getText().toString();
        prenom=editPrenom.getText().toString();
        age=editAge.getText().toString();
        travail=editTravail.getText().toString();
        if (nom.equals("") || prenom.equals("")|| editAge.getText().toString().equals("") || travail.equals("")) {
            if (nom.equals("")){
                editNomLayout.setError("veuillez remplir votre nom");
            }
            else {
                editNomLayout.setErrorEnabled(false);
            }
            if (prenom.equals("")){
                editPrenomLayout.setError("veuillez remplir votre prenom");
            }
            else {
                editPrenomLayout.setErrorEnabled(false);
            }
            if (editAge.getText().toString().equals("")){
                editAgeLayout.setError("veuillez remplir votre age");
            }
            else {
                editAgeLayout.setErrorEnabled(false);
            }
            if (travail.equals("")){
                editTravailLayout.setError("veuillez remplir votre travail");
            }
            else {
                editTravailLayout.setErrorEnabled(false);
            }
        } else {
            profil.setDrawingCacheEnabled(true);
            Bitmap bitmap=profil.getDrawingCache();
            byte[] imagebyte= ImageUtil.getBytes(bitmap);
            Personne personne=new Personne(nom,prenom,Integer.parseInt(age),travail,imagebyte);
            personneDao=new PersonneDao(this);
            personneDao.addPersonne(personne);
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    // SAVE INSTANCES
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        nom=editNom.getText().toString();
        prenom=editPrenom.getText().toString();
        travail=editTravail.getText().toString();
        String uri=this.uri.toString();

        outState.putString("nom", nom);
        outState.putString("prenom", prenom);
        outState.putString("age", editAge.getText().toString());
        outState.putString("travail", travail);
        outState.putString("uri", uri);
    }

    ///////////////////////////////////////////////////////////////////////////
    // RESTORE INSTANCES
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String nom=savedInstanceState.getString("nom");
        String prenom=savedInstanceState.getString("prenom");
        String age=savedInstanceState.getString("age");
        String travail=savedInstanceState.getString("travail");
        Uri usiSaved=Uri.parse(savedInstanceState.getString("uri"));

        editNom.setText(nom);
        editPrenom.setText(prenom);
        editAge.setText(age);
        editTravail.setText(travail);

        if (!(this.uri.equals("null"))) {
            profil.setImageURI(usiSaved);
        }
        else {
            profil.setImageResource(R.drawable.profil);
        }
    }

}
