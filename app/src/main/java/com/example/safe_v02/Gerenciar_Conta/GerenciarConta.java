package com.example.safe_v02.Gerenciar_Conta;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.safe_v02.Main.MainActivity;
import com.example.safe_v02.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;

public class GerenciarConta extends AppCompatActivity {

Toolbar toolbar;
TextView txtNome,txtemail,txtCurso,txtInstituicao;
ListView listaInformacoesUsuario;
CircleImageView fotoDePerfil;
public static final int GET_FROM_GALLERY  = 1;
public static String nome="Nome de usuário",email="Email",curso="O que está cursando",instituicao="Nome da instituição de ensino";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_conta);
        toolbar = (Toolbar) findViewById(R.id.toolbarGerConta);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gerenciar conta");

        fotoDePerfil = (CircleImageView)findViewById(R.id.fotoDePerfil);
        txtNome = (TextView)findViewById(R.id.txtNomeUsuario);
        txtemail = (TextView)findViewById(R.id.txtEmail);
        txtCurso = (TextView)findViewById(R.id.txtCurso);
        txtInstituicao = (TextView)findViewById(R.id.txtInstituicao);
        carregarPerfil();


        fotoDePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });

    }

    public void editarInfo(final View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GerenciarConta.this);
        alertDialog.setTitle("Editar");
        final EditText input = new EditText(GerenciarConta.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(100,50,100,0);
        input.setLayoutParams(lp);
        input.setSelectAllOnFocus(true);
        alertDialog.setView(input);

        if (txtNome.equals(view)) {
            input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            input.setText(nome);
        } else if (txtemail.equals(view)) {
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            input.setText(email);
        } else if (txtCurso.equals(view)) {
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            input.setText(curso);
        } else if (txtInstituicao.equals(view)) {
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            input.setText(instituicao);
        }

        alertDialog.setPositiveButton("Salvar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if((input.getText().toString().length()>0)){
                            if (txtNome.equals(view)) {
                                nome=input.getText().toString();
                                txtNome.setText(nome);
                            } else if (txtemail.equals(view)) {
                                email=input.getText().toString();
                                txtemail.setText(email);
                            } else if (txtCurso.equals(view)) {
                                curso=input.getText().toString();
                                txtCurso.setText(curso);
                            } else if (txtInstituicao.equals(view)) {
                                instituicao=input.getText().toString();
                                txtInstituicao.setText(instituicao);
                            }
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("nome",nome).apply();
                            sharedPreferences.edit().putString("email",email).apply();
                            sharedPreferences.edit().putString("curso",curso).apply();
                            sharedPreferences.edit().putString("instituicao",instituicao).apply();

                        }
                    }
                });

        alertDialog.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                fotoDePerfil.setImageBitmap(bitmap);
                MainActivity.foto_usuario=bitmap;
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("foto_perfil", codificarParaBase64(bitmap)).apply();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // Faz com que o botão voltar da toolbar funcione igual ao do celular
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    public void carregarPerfil(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
        HashSet<String> setDeInformacoes = (HashSet<String>) sharedPreferences.getStringSet("informacoesPerfil", null);
        String SpNome = sharedPreferences.getString("nome",null);
        String SpEmail = sharedPreferences.getString("email",null);
        String SpCurso = sharedPreferences.getString("curso",null);
        String  SpInstituicao = sharedPreferences.getString("instituicao",null);

        if((SpNome!=null)&&(SpNome.length()>0)){
            nome=SpNome;
        }
        if((SpEmail!=null)&&(SpEmail.length()>0)){
            email=SpEmail;
        }
        if((SpCurso!=null)&&(SpCurso.length()>0)){
            curso=SpCurso;
        }
        if((SpInstituicao!=null)&&(SpInstituicao.length()>0)){
            instituicao=SpInstituicao;
        }
        if(MainActivity.foto_usuario!=null){
            fotoDePerfil.setImageBitmap(MainActivity.foto_usuario);
        }

        txtNome.setText(nome);
        txtemail.setText(email);
        txtCurso.setText(curso);
        txtInstituicao.setText(instituicao);

    }

    // Bitmap para base64
    public static String codificarParaBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    }

