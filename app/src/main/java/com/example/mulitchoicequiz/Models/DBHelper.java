package com.example.mulitchoicequiz.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteAssetHelper {

    private static final String DB_name = "Quiz.db";
    private static final int DB_ver = 1;

    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context){
        if(instance==null) {
            instance = new DBHelper(context);
        }
            return instance;
    }
    public DBHelper(Context context) {
        super(context, DB_name, null, DB_ver);
    }

    public List<Category> getAllCategories(){
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Category;",null);
        List<Category> categories=new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Category category = new Category(cursor.getInt(cursor.getColumnIndex("ID")),
                         cursor.getString(cursor.getColumnIndex("Name")),
                         cursor.getString(cursor.getColumnIndex("Image")));
                categories.add(category);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return  categories;
    }
    public List<Question> getQuestionByCategory(int category){
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM Question WHERE CategoryId = %d ORDER BY RANDOM() LIMIT 30",category),null);
        List<Question> questions=new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String questionText = cursor.getString(cursor.getColumnIndex("QuestionText"));
                String questionImage =  cursor.getString(cursor.getColumnIndex("QuestionImage"));
                String answerA = cursor.getString(cursor.getColumnIndex("AnswerA"));
                String answerB = cursor.getString(cursor.getColumnIndex("AnswerB"));
                String answerC = cursor.getString(cursor.getColumnIndex("AnswerC"));
                String answerD = cursor.getString(cursor.getColumnIndex("AnswerD"));
                String correctAnswer = cursor.getString(cursor.getColumnIndex("CorrectAnswer"));
                int isImageQuestion =  cursor.getInt(cursor.getColumnIndex("IsImageQuestion"));
                int categoryID = cursor.getInt(cursor.getColumnIndex("CategoryID"));
                Question question = new Question(id,
                        questionText,
                        questionImage,
                        answerA,
                        answerB,
                        answerC,
                        answerD,
                        correctAnswer,
                        isImageQuestion,
                        categoryID);
                questions.add(question);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return  questions;
    }
}
