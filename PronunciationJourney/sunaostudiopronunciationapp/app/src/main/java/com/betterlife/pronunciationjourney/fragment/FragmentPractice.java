package com.betterlife.pronunciationjourney.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PracticeQuestion;
import com.betterlife.pronunciationjourney.utils.Constants;
import com.betterlife.pronunciationjourney.utils.MyPreferences;
import com.betterlife.pronunciationjourney.utils.SoundEffect;
import com.betterlife.pronunciationjourney.utils.SpeechRecognizerSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 03/03/2018.
 */

public class FragmentPractice extends Fragment implements RecognitionListener,View.OnClickListener{
    public static final String TAG = "FragmentPractice";
    private ProgressBar mProgressComplete;
    private TextView mTextviewScore;
    private TextView mTextviewQuestion;
    private TextView mTextviewHint;
    private TextView mTextviewDescription;
    private TextView mTextviewResult;
    private TextView mTextviewTryAgain;
    private TextView mTextviewAnswerChecking;
    private ImageView mImageViewRecord;
    private ImageView mImageRatingOne;
    private ImageView mImageRatingTwo;
    private ImageView mImageRatingThree;
    private ImageView mImageRatingFour;
    private ImageView mImageRatingFive;
    private LinearLayout mLayoutRating;
    private Button mButtonNextQuiz;
    private Button mButtonDonePractice;

    private SpeechRecognizer mSpeechRecognizer;
    private List<PracticeQuestion> mListQuestion;

    private int mPositionQuestion = 0;
    private boolean isRecording =false;
    private int mCurrentScore;
    private PracticeQuestion mPracticeQuestion;
    private Intent recognizerIntent;
    private MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_practice,container,false);
        setupView(rootView);
        registerEvent();
        return  rootView;
    }

    private void registerEvent() {
        mImageViewRecord.setOnClickListener(this);
        mButtonNextQuiz.setOnClickListener(this);
        mButtonDonePractice.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSpeechRecognizer = SpeechRecognizerSingleton.getInstance(getActivity());
        mSpeechRecognizer.setRecognitionListener(this);
        intiliaze();
        showQuestion(mPositionQuestion);
    }

    private void intiliaze() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en-US");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                getActivity().getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        mMediaPlayer = new MediaPlayer();
        mCurrentScore = MyPreferences.getInstance(getActivity()).getIntObject(Constants.KEY_CURRENT_SCORE);
        mTextviewScore.setText(getString(R.string.your_score)+" "+mCurrentScore);
        intiliazeSamplePracticeQuestion();
    }

    private void showQuestion(int positionQuestion) {
        if(positionQuestion == mListQuestion.size()){
            return;
        }
        mPracticeQuestion = mListQuestion.get(positionQuestion);
        mTextviewQuestion.setText(mPracticeQuestion.getQuestion());
        mTextviewDescription.setText(mPracticeQuestion.getDescription());
        mTextviewHint.setText(mPracticeQuestion.getHint());
    }

    private void intiliazeSamplePracticeQuestion() {
        mListQuestion = new ArrayList<>();
        PracticeQuestion practiceQuestionOne = new PracticeQuestion();
        practiceQuestionOne.setType(2);
        practiceQuestionOne.setQuestion("Chúng tôi quyên góp cho quỹ từ thiện");
        practiceQuestionOne.setDescription("Speak the sentence have the meaning above");
        practiceQuestionOne.setExplaination("");
        practiceQuestionOne.setResult("We subscribe to the charity fund");
        mListQuestion.add(practiceQuestionOne);

        PracticeQuestion practiceQuestionTwo = new PracticeQuestion();
        practiceQuestionTwo.setType(3);
        practiceQuestionTwo.setQuestion("I am from Hanoi VietNam");
        practiceQuestionTwo.setDescription("Speak the question is suitable with answer above");
        practiceQuestionTwo.setExplaination("");
        practiceQuestionTwo.setResult("Where are you from");
        mListQuestion.add(practiceQuestionTwo);

        PracticeQuestion practiceQuestionThree = new PracticeQuestion();
        practiceQuestionThree.setType(2);
        practiceQuestionThree.setQuestion("Họ là khách du lịch trong nước");
        practiceQuestionThree.setDescription("Speak the sentence have the meaning above");
        practiceQuestionThree.setExplaination("");
        practiceQuestionThree.setResult("They are domestic tourists");
        mListQuestion.add(practiceQuestionThree);

        PracticeQuestion practiceQuestionFour = new PracticeQuestion();
        practiceQuestionFour.setType(2);
        practiceQuestionFour.setQuestion("Anh ấy gặp người vợ tương lai của mình tại trường luật");
        practiceQuestionFour.setDescription("Speak the sentence have the meaning above");
        practiceQuestionFour.setExplaination("");
        practiceQuestionFour.setResult("He met his future wife at law school");
        mListQuestion.add(practiceQuestionFour);

        PracticeQuestion practiceQuestionFive = new PracticeQuestion();
        practiceQuestionFive.setType(2);
        practiceQuestionFive.setQuestion("Chúng tôi rất hạnh phúc thông báo lễ đính hôn của con gái chúng tôi");
        practiceQuestionFive.setDescription("Speak the sentence have the meaning above");
        practiceQuestionFive.setExplaination("");
        practiceQuestionFive.setResult("We are happy to announce the engagement of our daughter");
        mListQuestion.add(practiceQuestionFive);

        PracticeQuestion practiceQuestionSix = new PracticeQuestion();
        practiceQuestionSix.setType(2);
        practiceQuestionSix.setQuestion("Cô ấy phải rất tự hào về bản thân");
        practiceQuestionSix.setDescription("Speak the sentence have the meaning above");
        practiceQuestionSix.setExplaination("");
        practiceQuestionSix.setResult("She must be very proud of herself");
        mListQuestion.add(practiceQuestionSix);

        PracticeQuestion practiceQuestionSeven = new PracticeQuestion();
        practiceQuestionSeven.setType(2);
        practiceQuestionSeven.setQuestion("Không còn nơi nào cho tôi ngồi");
        practiceQuestionSeven.setDescription("Speak the sentence have the meaning above");
        practiceQuestionSeven.setExplaination("");
        practiceQuestionSeven.setResult("There was nowhere for me to sit");
        mListQuestion.add(practiceQuestionSeven);

        PracticeQuestion practiceQuestionEight = new PracticeQuestion();
        practiceQuestionEight.setType(2);
        practiceQuestionEight.setQuestion("Đôi này vừa khít");
        practiceQuestionEight.setDescription("Speak the sentence have the meaning above");
        practiceQuestionEight.setExplaination("");
        practiceQuestionEight.setResult("This pair fits perfectly");
        mListQuestion.add(practiceQuestionEight);

        PracticeQuestion practiceQuestionNine = new PracticeQuestion();
        practiceQuestionNine.setType(2);
        practiceQuestionNine.setQuestion("Tôi không thể tập trung, có lẽ bởi vì tôi đã quá mệt mỏi");
        practiceQuestionNine.setDescription("Speak the sentence have the meaning above");
        practiceQuestionNine.setExplaination("");
        practiceQuestionNine.setResult("I couldn't concentrate, because I was so tired");
        mListQuestion.add(practiceQuestionNine);

        PracticeQuestion practiceQuestionTen = new PracticeQuestion();
        practiceQuestionTen.setType(2);
        practiceQuestionTen.setQuestion("Người đọc đã rút ra kết luận của riêng mình");
        practiceQuestionTen.setDescription("Speak the sentence have the meaning above");
        practiceQuestionTen.setExplaination("");
        practiceQuestionTen.setResult("The reader is left to draw his or her own conclusions");
        mListQuestion.add(practiceQuestionTen);
    }

    private void setupView(View rootView) {
        mProgressComplete = rootView.findViewById(R.id.practice_progress_complete);
        mTextviewScore = rootView.findViewById(R.id.practice_tv_practice_score);
        mTextviewQuestion = rootView.findViewById(R.id.practice_textview_question);
        mTextviewHint = rootView.findViewById(R.id.practice_textview_hint);
        mTextviewDescription = rootView.findViewById(R.id.practice_textview_description);
        mTextviewResult = rootView.findViewById(R.id.practice_textview_result);
        mImageViewRecord = rootView.findViewById(R.id.practice_img_record);
        mTextviewTryAgain = rootView.findViewById(R.id.practice_textview_try_again);
        mTextviewAnswerChecking = rootView.findViewById(R.id.practice_tv_checking);
        mImageRatingOne = rootView.findViewById(R.id.practice_star_rating_one);
        mImageRatingTwo = rootView.findViewById(R.id.practice_star_rating_two);
        mImageRatingThree = rootView.findViewById(R.id.practice_star_rating_three);
        mImageRatingFour = rootView.findViewById(R.id.practice_star_rating_four);
        mImageRatingFive = rootView.findViewById(R.id.practice_star_rating_five);
        mLayoutRating = rootView.findViewById(R.id.practice_layout_rating);
        mButtonNextQuiz = rootView.findViewById(R.id.practice_button_next_question);
        mButtonDonePractice = rootView.findViewById(R.id.practice_button_done_practice);
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        mTextviewAnswerChecking.setVisibility(View.VISIBLE);
        isRecording = false;
        mSpeechRecognizer.stopListening();
        mImageViewRecord.setImageResource(R.drawable.microphone_blue);
    }

    @Override
    public void onError(int errorCode) {
        isRecording = false;
        mSpeechRecognizer.stopListening();
        mImageViewRecord.setImageResource(R.drawable.microphone_blue);
        String errorMessage = getErrorText(errorCode);
        Log.d("SpeechError", "FAILED " + errorMessage);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for(int i=0;i<matches.size();i++){
            Log.e("SpeechResult",matches.get(i));
        }
        if(checkAnswer(matches)){
            //1.update variable for next question
            mCurrentScore=mCurrentScore+10;
            mPositionQuestion++;
            //2.update layout
            updateLayout(true,matches.get(0));
        }else {
            updateLayout(false,matches.get(0));
        }

    }

    private void updateLayout(boolean isRightAnswer,String firstAnswerOfUser) {
        mTextviewAnswerChecking.setVisibility(View.INVISIBLE);
        if(isRightAnswer){
            mProgressComplete.setProgress(mPositionQuestion*10);
            SoundEffect.getInstance().playRightAnswerSound(getActivity(),mMediaPlayer);
            mLayoutRating.setVisibility(View.VISIBLE);
            mTextviewTryAgain.setVisibility(View.INVISIBLE);
            mImageViewRecord.setEnabled(false);
            mTextviewResult.setTextColor(Color.GREEN);
            mTextviewResult.setText(mPracticeQuestion.getResult());
            mTextviewResult.setVisibility(View.VISIBLE);
            mTextviewScore.setText(getString(R.string.your_score)+" "+mCurrentScore);
            if(mPositionQuestion == 10){
                mButtonDonePractice.setVisibility(View.VISIBLE);
            }else {
                mButtonNextQuiz.setVisibility(View.VISIBLE);
            }
        }else {
            SoundEffect.getInstance().playWrongAnswerSound(getActivity(),mMediaPlayer);
            mTextviewResult.setText(getString(R.string.you_have_pronounce)+" '"+firstAnswerOfUser+"'");
            mTextviewResult.setTextColor(Color.RED);
            mTextviewResult.setVisibility(View.VISIBLE);
            mLayoutRating.setVisibility(View.INVISIBLE);
            mButtonNextQuiz.setVisibility(View.INVISIBLE);
            mTextviewTryAgain.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkAnswer(ArrayList<String> results) {
        boolean isSpeechRight = false;
        String[] importantWordArr = mPracticeQuestion.getCombineImportantWord().split("-");
        for(String result : results){
            int numberWordCorrect = 0;
            for(String importantWord : importantWordArr){
                Log.d("ImportantWord",importantWord+"");
                if(result.toLowerCase().contains(importantWord.toLowerCase())){
                    numberWordCorrect++;
                }
            }
            if((mPracticeQuestion.getResult().length()<=6 && numberWordCorrect >=2) ||
                    mPracticeQuestion.getResult().length()>6 && numberWordCorrect >=3){
                isSpeechRight = true;
                break;
            }
        }
        return isSpeechRight;
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.practice_img_record:
                if(isRecording){
                    return;
                }
                mSpeechRecognizer.startListening(recognizerIntent);
                isRecording = true;
                mImageViewRecord.setImageResource(R.drawable.microphone_red);
                mTextviewResult.setVisibility(View.INVISIBLE);
                mTextviewTryAgain.setVisibility(View.INVISIBLE);
                break;
            case R.id.practice_button_next_question:
                resetLayout();
                showQuestion(mPositionQuestion);
                break;
            case R.id.practice_button_done_practice:
                //1.save score of user
                MyPreferences.getInstance(getActivity()).saveIntObject(Constants.KEY_CURRENT_SCORE,mCurrentScore);
                //2.back to home screen
                getActivity().onBackPressed();
                break;

        }
    }

    private void resetLayout() {
        mLayoutRating.setVisibility(View.INVISIBLE);
        mTextviewResult.setText("");
        mButtonNextQuiz.setVisibility(View.INVISIBLE);
        mImageViewRecord.setEnabled(true);
    }

    public void saveScore() {
        if(getActivity() != null){
            MyPreferences.getInstance(getActivity()).saveIntObject(Constants.KEY_CURRENT_SCORE,mCurrentScore);
        }
    }
}
