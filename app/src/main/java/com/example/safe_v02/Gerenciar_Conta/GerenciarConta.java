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
import android.icu.lang.UCharacter;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.safe_v02.MainActivity;
import com.example.safe_v02.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;

public class GerenciarConta extends AppCompatActivity {

Toolbar toolbar;
ListView listaInformacoesUsuario;
CircleImageView fotoDePerfil;
public static final int GET_FROM_GALLERY  = 1;
ArrayList<String> informacoesPerfil = new ArrayList<String>();
public static String nome="Nome de usuário",email="Email",curso="O que está cursando",instituicao="Nome da instituição de ensino";
ArrayAdapter<String> adapter_informacoes_perfil = null;
String informacoesArray[] = new String[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_conta);
        toolbar = (Toolbar) findViewById(R.id.toolbarGerConta);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gerenciar conta");

        fotoDePerfil = (CircleImageView)findViewById(R.id.fotoDePerfil);

        carregarPerfil();
        listaInformacoesUsuario = (ListView)findViewById(R.id.listaInformacoesUsuario);
        listaInformacoesUsuario.setAdapter(adapter_informacoes_perfil);


        listaInformacoesUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
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

                switch(position){
                    case 0:
                        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                        input.setText(informacoesArray[0]);
                        break;
                    case 1:
                        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        input.setText(informacoesArray[1]);
                        break;
                    case 2:
                        input.setText(informacoesArray[2]);
                        break;
                    case 3:
                        input.setText(informacoesArray[3]);
                        break;
                }

                alertDialog.setPositiveButton("Salvar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if((input.getText().toString().length()>0)){
                                    switch (position){
                                        case 0:
                                            nome=(input.getText().toString());
                                            informacoesArray[0]=nome;
                                            break;
                                        case 1:
                                            email=(input.getText().toString());
                                            informacoesArray[1]=email;
                                            break;
                                        case 2:
                                            curso=(input.getText().toString());
                                            informacoesArray[2]=curso;
                                            break;
                                        case 3:
                                            instituicao=(input.getText().toString());
                                            informacoesArray[3]=instituicao;
                                            break;

                                    }
                                    adapter_informacoes_perfil.notifyDataSetChanged();
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
        });


        fotoDePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });

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

        informacoesArray[0]=nome;
        informacoesArray[1]=email;
        informacoesArray[2]=curso;
        informacoesArray[3]=instituicao;
        adapter_informacoes_perfil = new ArrayAdapter<String>(this, R.layout.txt_custom, informacoesArray);

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

