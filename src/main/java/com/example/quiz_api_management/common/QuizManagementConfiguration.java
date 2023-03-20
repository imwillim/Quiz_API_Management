
package com.example.quiz_api_management.common;

import com.example.quiz_api_management.answer.Answer;
import com.example.quiz_api_management.answer.AnswerRepository;
import com.example.quiz_api_management.question.Question;
import com.example.quiz_api_management.question.QuestionRepository;
import com.example.quiz_api_management.quiz.Quiz;
import com.example.quiz_api_management.quiz.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class QuizManagementConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(QuestionRepository questionRepository, AnswerRepository answerRepository, QuizRepository quizRepository) {
        return args -> {

            Quiz first_quiz = new Quiz("Math 1");
            Question first_question = new Question("Equation(s) to get result of 4 ?", "multiple", first_quiz);
            Answer first_answer = new Answer("2 * 2", true, first_question);

            Answer second_answer = new Answer("2 + 2", true, first_question);
            Answer third_answer = new Answer("2 / 2", false, first_question);
            Answer fourth_answer = new Answer("2 - 2", false, first_question);

            Question second_question = new Question("2 + 6 is ?", "short",first_quiz);
            Answer fifth_answer = new Answer("8", true, second_question);

            Question third_question = new Question("Given any number, what is the result after multiplying by 1 ?", "long", first_quiz);
            Answer sixth_answer = new Answer("Itself", true, third_question);

            Question fourth_question = new Question("Is zero an even number ?", "long", first_quiz);
            Answer seventh_answer = new Answer("Yesa", true, fourth_question);

            Question fifth_question = new Question("Is zero a natural number ?", "long", first_quiz);
            Answer eighth_answer = new Answer("Yes", true, fifth_question);
            // second_question.setAnswers(List.of(fifth_answer));

            List<Question> questions = new ArrayList<>(List.of(first_question, second_question, third_question, fourth_question, fifth_question));
            List<Answer> answers = new ArrayList<>(List.of(first_answer, second_answer, third_answer, fourth_answer, fifth_answer, sixth_answer, seventh_answer, eighth_answer));



            Quiz second_quiz = new Quiz("Math 2");
            Question sixth_question = new Question("a*a ?", "multiple", second_quiz);
            Answer ninth_answer = new Answer("a^2", true, sixth_question);

            Question seventh_question = new Question("2 to the power of 3 ?", "short", second_quiz);
            Answer tenth_answer = new Answer("8", true, seventh_question);

            Question eighth_question = new Question("Square root of 1 ?", "short", second_quiz);
            Answer eleventh_answer = new Answer("1", true, eighth_question);

            Question ninth_question = new Question("Given any number, the 1st power of that number is ?", "long", second_quiz);
            Answer twelfth_answer = new Answer("Itself", true, ninth_question);

            Question tenth_question = new Question("2^2 equals to ?", "multiple", second_quiz);
            Answer thirteenth_answer = new Answer("4", true, tenth_question);
            Answer fourteenth_answer = new Answer("8", false, tenth_question);
            Answer fifteenth_answer = new Answer("16", false, tenth_question);
            Answer sixteenth_answer = new Answer("2 * 2", true, tenth_question);
            questions.addAll(List.of(sixth_question, seventh_question, eighth_question, ninth_question, tenth_question));
            answers.addAll(List.of(ninth_answer, tenth_answer, eleventh_answer, twelfth_answer, thirteenth_answer, fourteenth_answer, fifteenth_answer, sixteenth_answer));



            Quiz third_quiz = new Quiz("Basic Java 1");
            Question eleventh_question = new Question("WORA is ?", "long", third_quiz);
            Answer seventeenth_answer = new Answer("Write Once, Run Anywhere", true, eleventh_question);

            Question twelfth_question = new Question("Is Java object-oriented programming language ?", "long", third_quiz);
            Answer eighteenth_answer = new Answer("Yes", true, twelfth_question);

            Question thirteenth_question = new Question("How many primitive(s) are there ?", "multiple", third_quiz);
            Answer nineteenth_answer = new Answer("5", false, thirteenth_question);
            Answer twentieth_answer = new Answer("6", true, thirteenth_question);
            Answer twenty_first_answer = new Answer("7", true, thirteenth_question);
            Answer twenty_second_answer = new Answer("8", true, thirteenth_question);

            Question fourthteen_question = new Question("Is Java object-oriented programming language ?", "long", third_quiz);
            Answer twenty_third_answer = new Answer("Yes, it is", true, fourthteen_question);

            Question fifteenth_question = new Question("What is a class ?","long", third_quiz);
            Answer twenty_fourth_answer = new Answer("A blueprint of object", true, fifteenth_question);

            questions.addAll(List.of(eleventh_question, twelfth_question, thirteenth_question, fourthteen_question, fifteenth_question));
            answers.addAll(List.of(seventeenth_answer, eighteenth_answer, nineteenth_answer, twentieth_answer, twenty_first_answer, twenty_second_answer, twenty_third_answer, twenty_fourth_answer));



            Quiz fourth_quiz = new Quiz("Basic Java 2");
            Question sixteenth_question = new Question("How to define main function in Java ?", "long", fourth_quiz);
            Answer twenty_fifth_answer = new Answer("public static void main(String []args)", true, sixteenth_question);

            Question seventeenth_question = new Question("How many byte(s) does long type have?", "multiple", fourth_quiz);
            Answer twenty_sixth_answer = new Answer("2", false, seventeenth_question);
            Answer twenty_seventh_answer = new Answer("4", false, seventeenth_question);
            Answer twenty_eight_answer = new Answer("6", false, seventeenth_question);
            Answer twenty_ninth_answer = new Answer("8", true, seventeenth_question);

            Question eighteenth_question = new Question("Most keyword to use interface ?", "long", fourth_quiz);
            Answer thirdtieth_answer = new Answer("implements",true, eighteenth_question);

            Question nineteenth_question = new Question("What is JVM ?", "long", fourth_quiz);
            Answer thirty_first_answer = new Answer("Java Virtual Machine", true, nineteenth_question);

            Question twentieth_question = new Question("Keyword to use abstract class ?", "long", fourth_quiz);
            Answer thirdty_second_answer = new Answer("extends", true, twentieth_question);

            questions.addAll(List.of(sixteenth_question, seventeenth_question, eighteenth_question, nineteenth_question, twentieth_question));
            answers.addAll(List.of(twenty_fifth_answer, twenty_sixth_answer, twenty_seventh_answer, twenty_eight_answer, twenty_ninth_answer, thirdtieth_answer, thirty_first_answer, thirdty_second_answer));



            Quiz fifth_quiz = new Quiz("Java OOP");
            Question twenty_first_question = new Question("How to provoke superclass ?", "long", fifth_quiz);
            Answer thirty_third_answer = new Answer("super()", true, twenty_first_question);

            Question twenty_second_question = new Question("How many principle(s) does OOP have?", "multiple", fifth_quiz);
            Answer thirty_fourth_answer = new Answer("2", false, twenty_second_question);
            Answer thirty_fifth_answer = new Answer("3", false, twenty_second_question);
            Answer thirty_sixth_answer = new Answer("4", true, twenty_second_question);
            Answer thirty_seventh_answer = new Answer("5", false, twenty_second_question);

            Question twenty_third_question = new Question("What is encapsulation ?", "long", fifth_quiz);
            Answer thirty_eight_answer = new Answer("A process of wrapping code and data together into a single unit", true, twenty_third_question);

            Question twenty_fourth_question = new Question("What is abstraction ?", "long", fifth_quiz);
            Answer thirty_ninth_answer= new Answer("A process of hiding the implementation detail", true, twenty_fourth_question);

            Question twenty_fifth_question = new Question("How many ways to achieve abstraction ?", "short", fifth_quiz);
            Answer fourtieth_answer = new Answer("2", true, twenty_fifth_question);

            questions.addAll(List.of(twenty_first_question, twenty_second_question, twenty_third_question, twenty_fourth_question, twenty_fifth_question));
            answers.addAll(List.of(thirty_third_answer,thirty_fourth_answer, thirty_fifth_answer, thirty_sixth_answer, thirty_seventh_answer, thirty_eight_answer, thirty_ninth_answer, fourtieth_answer));


            quizRepository.saveAll(List.of(first_quiz, second_quiz, third_quiz, fourth_quiz, fifth_quiz));
            questionRepository.saveAll(questions);
            answerRepository.saveAll(answers);





            // quizRepository.saveAll(List.of(first_quiz));
            // questionRepository.saveAll(List.of(first_question));
            // answerRepository.saveAll(List.of(first_answer, second_answer, third_answer, fourth_answer));


        };

    }
}


