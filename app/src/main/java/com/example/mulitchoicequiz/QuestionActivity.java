package com.example.mulitchoicequiz;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.mulitchoicequiz.Adapter.AnswerSheetAdapter;
import com.example.mulitchoicequiz.Common.Common;
import com.example.mulitchoicequiz.Models.CurrentQuestion;
import com.example.mulitchoicequiz.Models.DBHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    boolean isAnswerModeView = false;
    TextView txt_Right;

    RecyclerView answer_sheet_view;
    AnswerSheetAdapter answerSheetAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        takeQuestion();
        if (Common.questionList.size() > 0) {
            txt_Right = (TextView) findViewById(R.id.txt_question_right);
            txt_Right.setVisibility(View.VISIBLE);
            txt_Right.setText(new StringBuilder((String.format("%d/%d", Common.right_count,Common.wrong_count))));
            answer_sheet_view = (RecyclerView) findViewById(R.id.grid_answer);
            answer_sheet_view.setHasFixedSize(true);
            if(Common.questionList.size()>5){
                answer_sheet_view.setLayoutManager(new GridLayoutManager(this,Common.questionList.size()/2));
            }
            answerSheetAdapter = new AnswerSheetAdapter(this, Common.answeSheetList);
            answer_sheet_view.setAdapter(answerSheetAdapter);
        }
    }
    private void takeQuestion(){
        Common.questionList= DBHelper.getInstance(this).getQuestionByCategory(Common.selectedCategory.getId());
        if(Common.questionList.size()==0)
        {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Error");
        }
        else
        {
            if (Common.answeSheetList.size()>0){
                Common.answeSheetList.clear();
            }
            for(int y= 0; y<Common.questionList.size();y++){
                Common.answeSheetList.add(new CurrentQuestion(y, Common.ANSWER_TYPE.NO_ANSWER));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}