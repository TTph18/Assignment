package com.example.mulitchoicequiz.Common;

import androidx.appcompat.view.menu.MenuBuilder;

import com.example.mulitchoicequiz.Models.Category;
import com.example.mulitchoicequiz.Models.CurrentQuestion;
import com.example.mulitchoicequiz.Models.Question;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public  static Category selectedCategory = new Category();
    public static List<Question> questionList = new ArrayList<>();
    public static List<CurrentQuestion> answeSheetList = new ArrayList<>();
    public static int right_count =0 ;
    public static int wrong_count =0;

    public enum ANSWER_TYPE {
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }

}
