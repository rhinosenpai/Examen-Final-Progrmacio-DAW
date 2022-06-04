package daw.programacio.thenewmisterquestion;

import daw.programacio.thenewmisterquestion.data.DBFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionModel {
    private int id;
    private String question;
    private String[] answers;
    private int value;
    private int category;
    private int correctAnswer;

    public QuestionModel(int id, String question, String[] answers, int value, int category, int correctAnswer) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.value = value;
        this.category = category;
        this.correctAnswer = correctAnswer;
    }

    public QuestionModel() {
    }

    public ArrayList<QuestionModel> getQuestionsModels(){
        ArrayList<QuestionModel> ret = new ArrayList();
        try {
            ResultSet rs = DBFacade.select("question");
            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question");
                String[] answers = {rs.getString("answer_A"), rs.getString("answer_B"), rs.getString("answer_C")};
                int value = rs.getInt("value");
                int category = rs.getInt("category");
                int correctAnswer = rs.getInt("correct_answer");
                QuestionModel qm = new QuestionModel(id,question,answers,value,category,correctAnswer);
                ret.add(qm);
            }
            return ret;
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
