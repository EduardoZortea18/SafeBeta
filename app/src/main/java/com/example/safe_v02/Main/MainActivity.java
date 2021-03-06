package com.example.safe_v02.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.safe_v02.Agenda_de_eventos.MeusEventos;
import com.example.safe_v02.Bloco_de_notas.TelaAnotacoes;
import com.example.safe_v02.Ferramentas_de_calculo.InicioCalculadora;
import com.example.safe_v02.GoogleTradutor.GoogleTradutor;
import com.example.safe_v02.Materias_e_notas.TelaMaterias;
import com.example.safe_v02.Gerenciar_Conta.GerenciarConta;
import com.example.safe_v02.Estatisticas.Estatisticas;
import com.example.safe_v02.Horarios.Horarios;
import com.example.safe_v02.R;
import com.example.safe_v02.Suporte.Suporte;
import com.example.safe_v02.Tutorial.MainTutorial;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ViewPager pagerInicio;
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    public static String horario;
    String nome_usuario="Nome de usuário",email_usuario="Email",curso_usuario="O que está cursando",instituicao_usuario="Nome da instituição de ensino";
    public static Bitmap foto_usuario;
    TextView nav_user,nav_email,nav_course,nav_institution;
    CircleImageView nav_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setando a toolbar que sera utilizada no layout
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Configura o pagerAdapter e o tabLayout para trabalharem juntos
        pagerInicio = (ViewPager)findViewById(R.id.pagerInicio);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Horarios());
        fragmentList.add(new Estatisticas());
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),fragmentList);
        pagerInicio.setAdapter(pagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tabsInicio);
        tabLayout.setupWithViewPager(pagerInicio);
        //Configura um icone para a posição 0 e 1, no caso horários e estatísticas respectivamente
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_horarios);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_estatisticas);


        //Setando a navigationView,drawer e gestos de abrir e fechar drawer
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();


        //Setando as informações no drawer
        View hView =  navigationView.getHeaderView(0);
        nav_user = (TextView)hView.findViewById(R.id.txtUserId);
        nav_email = (TextView)hView.findViewById(R.id.txtUserMail);
        nav_course = (TextView)hView.findViewById(R.id.txtUserCourse);
        nav_institution = (TextView)hView.findViewById(R.id.txtUserInstitution);
        nav_photo = (CircleImageView)hView.findViewById(R.id.profilePic);

        //Verifica se o usuário está abrindo o app pela primeira vez
        verificaPrimeiroUso();
        //Carrega as informações do usuário no drawer
        carregarPerfil();


    }

    public void selectTabItem(View v){
        if(tabLayout.getSelectedTabPosition()==0){
            pagerInicio.setCurrentItem(1);
        }
        else{
            pagerInicio.setCurrentItem(2);
        }
    }

    //Fecha o drawer ao clicar no botão voltar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    // Verifica se um item do drawer foi selecionado e executa uma ação
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.agenda_de_eventos:
                Intent Agenda = new Intent(MainActivity.this, MeusEventos.class);
                startActivity(Agenda);
                break;
            case R.id.anotacoes:
                Intent telaAnotacoes = new Intent(MainActivity.this, TelaAnotacoes.class);
                startActivity(telaAnotacoes);
                break;
            case R.id.frrramentas_de_calculo:
               Intent frrramentas_de_calculo = new Intent(this, InicioCalculadora.class);
               startActivity(frrramentas_de_calculo);
                break;
            case R.id.GoogleTradutor:
                Intent google_tradutor = new Intent(this, GoogleTradutor.class);
                startActivity(google_tradutor);
                break;
            case R.id.materias:
                Intent materias = new Intent(this, TelaMaterias.class);
                startActivity(materias);
                break;
            case R.id.Suporte:
                Intent suporte = new Intent(MainActivity.this, Suporte.class);
                startActivity(suporte);
                break;
            case R.id.Conta:
                Intent ger_conta = new Intent(MainActivity.this, GerenciarConta.class);
                startActivity(ger_conta);
            break;

        }
        menuItem.setCheckable(false);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Atualiza as infromações do usuario após ele voltar a home
    @Override
    protected void onResume() {
        atualizarPerfil();
        super.onResume();
    }

    //carrega as informações dousuário
    public void carregarPerfil(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
        String SpNome = sharedPreferences.getString("nome", null);
        String SpEmail = sharedPreferences.getString("email", null);
        String SpCurso = sharedPreferences.getString("curso", null);
        String SpInstituicao = sharedPreferences.getString("instituicao", null);
        String imgString = (sharedPreferences.getString("foto_perfil",null));

        if(imgString!=null){
            Bitmap bitmap = decodificarBase64(imgString);
            foto_usuario=bitmap;
        }
        if(SpNome!=null){
            nome_usuario= SpNome;
        }
        if(SpEmail!=null){
            email_usuario=SpEmail;
        }
        if(SpNome!=null){
            curso_usuario=SpCurso;
        }
        if(SpNome!=null){
            instituicao_usuario=SpInstituicao;
        }
    }

    //Atualiza as informações do usuários e as atualiza no drawer
    public void atualizarPerfil(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
        String SpNome = sharedPreferences.getString("nome", null);
        String SpEmail = sharedPreferences.getString("email", null);
        String SpCurso = sharedPreferences.getString("curso", null);
        String SpInstituicao = sharedPreferences.getString("instituicao", null);

        if(SpNome!=null){
            nome_usuario= SpNome;
        }
        if(SpEmail!=null){
            email_usuario=SpEmail;
        }
        if(SpNome!=null){
            curso_usuario=SpCurso;
        }
        if(SpNome!=null){
            instituicao_usuario=SpInstituicao;
        }
        nav_user.setText(nome_usuario);
        nav_email.setText(email_usuario);
        nav_course.setText(curso_usuario);
        nav_institution.setText(instituicao_usuario);
        if(foto_usuario!=null){
            nav_photo.setImageBitmap(foto_usuario);
        }
    }

    // Converte a imagem de usuário que é salva em integer de base 64 para bitmap
    public static Bitmap decodificarBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    //Verifica se é o primeirouso do app e mostra uma mensagem para o usuario cadastrar suas informações
    public void verificaPrimeiroUso(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
        boolean primeiroUso = sharedPreferences.getBoolean("primeiroUso",true);

        if(primeiroUso==true){
            Intent ger_conta = new Intent(MainActivity.this, MainTutorial.class);
            startActivity(ger_conta);
        }
    }

}
