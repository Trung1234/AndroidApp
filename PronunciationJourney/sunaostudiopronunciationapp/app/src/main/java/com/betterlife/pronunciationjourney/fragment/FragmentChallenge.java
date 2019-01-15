package com.betterlife.pronunciationjourney.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PracticeQuestion;
import com.betterlife.pronunciationjourney.utils.SoundEffect;
import com.betterlife.pronunciationjourney.utils.SpeechRecognizerSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/03/2018.
 */

public class FragmentChallenge extends Fragment implements View.OnClickListener,RecognitionListener {
    public static final String TAG = "FragmentChallenge";
    private Button mButtonQuestionOne;
    private Button mButtonQuestionTwo;
    private Button mButtonQuestionThree;
    private Button mButtonQuestionFour;
    private Button mButtonQuestionFive;
    private Button mButtonQuestionSix;
    private Button mButtonQuestionSeven;
    private Button mButtonQuestionEight;
    private Button mButtonQuestionNine;
    private Button mButtonQuestionTen;
    private ImageView mImageDoneQuestionOne;
    private ImageView mImageDoneQuestionTwo;
    private ImageView mImageDoneQuestionThree;
    private ImageView mImageDoneQuestionFour;
    private ImageView mImageDoneQuestionFive;
    private ImageView mImageDoneQuestionSix;
    private ImageView mImageDoneQuestionSeven;
    private ImageView mImageDoneQuestionEight;
    private ImageView mImageDoneQuestionNine;
    private ImageView mImageDoneQuestionTen;
    private TextView mTextviewTotalTime;
    private TextView mTextviewQuestion;
    private TextView mTextviewDescription;
    private TextView mTextviewHint;
    private TextView mTextviewIsChecking;
    private ImageView mImageRecord;
    private TextView mTextviewResult;
    private TextView mTextviewTryAgainOrContine;

    private SpeechRecognizer mSpeechRecognizer;
    private List<PracticeQuestion> mListQuestion;
    private List<Boolean> listCheckQuestionDone;

    private int mNumberQuestion = 0;
    private int mCurrentQuestion = 1;
    private boolean isRecording =false;
    private int mTotalTime;
    private PracticeQuestion mPracticeQuestion;
    private Intent recognizerIntent;
    private MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenge_get_gift,container,false);
        setupView(rootView);
        registerEvent();
        return rootView;
    }

    private void registerEvent() {
        mButtonQuestionOne.setOnClickListener(this);
        mButtonQuestionTwo.setOnClickListener(this);
        mButtonQuestionThree.setOnClickListener(this);
        mButtonQuestionFour.setOnClickListener(this);
        mButtonQuestionFive.setOnClickListener(this);
        mButtonQuestionSix.setOnClickListener(this);
        mButtonQuestionSeven.setOnClickListener(this);
        mButtonQuestionEight.setOnClickListener(this);
        mButtonQuestionNine.setOnClickListener(this);
        mButtonQuestionTen.setOnClickListener(this);
        mImageRecord.setOnClickListener(this);

    }

    private void setupView(View rootView) {
        mButtonQuestionOne = rootView.findViewById(R.id.challenge_button_question_one);
        mButtonQuestionTwo = rootView.findViewById(R.id.challenge_button_question_two);
        mButtonQuestionThree = rootView.findViewById(R.id.challenge_button_question_three);
        mButtonQuestionFour = rootView.findViewById(R.id.challenge_button_question_four);
        mButtonQuestionFive = rootView.findViewById(R.id.challenge_button_question_five);
        mButtonQuestionSix = rootView.findViewById(R.id.challenge_button_question_six);
        mButtonQuestionSeven = rootView.findViewById(R.id.challenge_button_question_seven);
        mButtonQuestionEight = rootView.findViewById(R.id.challenge_button_question_eight);
        mButtonQuestionNine = rootView.findViewById(R.id.challenge_button_question_nine);
        mButtonQuestionTen = rootView.findViewById(R.id.challenge_button_question_ten);
        mImageDoneQuestionOne = rootView.findViewById(R.id.challenge_done_question_one);
        mImageDoneQuestionTwo = rootView.findViewById(R.id.challenge_done_question_two);
        mImageDoneQuestionThree = rootView.findViewById(R.id.challenge_done_question_three);
        mImageDoneQuestionFour = rootView.findViewById(R.id.challenge_done_question_four);
        mImageDoneQuestionFive = rootView.findViewById(R.id.challenge_done_question_five);
        mImageDoneQuestionSix = rootView.findViewById(R.id.challenge_done_question_six);
        mImageDoneQuestionSeven = rootView.findViewById(R.id.challenge_done_question_seven);
        mImageDoneQuestionEight = rootView.findViewById(R.id.challenge_done_question_eight);
        mImageDoneQuestionNine = rootView.findViewById(R.id.challenge_done_question_nine);
        mImageDoneQuestionTen = rootView.findViewById(R.id.challenge_done_question_ten);
        mTextviewTotalTime = rootView.findViewById(R.id.challenge_tv_time_need_complete);
        mTextviewQuestion = rootView.findViewById(R.id.challenge_textview_question);
        mTextviewDescription = rootView.findViewById(R.id.challenge_textview_description);
        mTextviewHint = rootView.findViewById(R.id.challenge_textview_hint);
        mImageRecord = rootView.findViewById(R.id.challenge_img_record);
        mTextviewResult = rootView.findViewById(R.id.challenge_textview_result);
        mTextviewIsChecking = rootView.findViewById(R.id.challenge_tv_checking);
        mTextviewTryAgainOrContine = rootView.findViewById(R.id.challenge_textview_try_again_or_continue);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intiliaze();
        generateQuestion();
        mPracticeQuestion = mListQuestion.get(0);
        showQuestion(mPracticeQuestion, 1);
    }

    private void generateQuestion() {
        mListQuestion = new ArrayList<>();
        PracticeQuestion practiceQuestionOne = new PracticeQuestion();
        practiceQuestionOne.setType(1);
        practiceQuestionOne.setQuestion("");
        practiceQuestionOne.setDescription("Speak the sentence have all the word above");
        practiceQuestionOne.setExplaination("");
        practiceQuestionOne.setResult("");
        practiceQuestionOne.setmCombineWordNeedPronounce("usually-go-friend");
        mListQuestion.add(practiceQuestionOne);

        PracticeQuestion practiceQuestionTwo = new PracticeQuestion();
        practiceQuestionTwo.setType(1);
        practiceQuestionTwo.setQuestion("");
        practiceQuestionTwo.setDescription("Speak the sentence have all the word above");
        practiceQuestionTwo.setExplaination("");
        practiceQuestionTwo.setResult("");
        practiceQuestionTwo.setmCombineWordNeedPronounce("when-you-dinner");
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

    private void intiliaze() {
        listCheckQuestionDone = new ArrayList<>();
        for(int i=0;i<10;i++){
            listCheckQuestionDone.add(false);
        }
        mSpeechRecognizer = SpeechRecognizerSingleton.getInstance(getActivity());
        mSpeechRecognizer.setRecognitionListener(this);
        mListQuestion = new ArrayList<>();
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en-US");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                getActivity().getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void onClick(View view) {
        if(isRecording){
            return;
        }
        switch (view.getId()){
            case R.id.challenge_button_question_one:
                mPracticeQuestion = mListQuestion.get(0);
                mCurrentQuestion = 1;
                showQuestion(mPracticeQuestion,1);
                break;
            case R.id.challenge_button_question_two:
                mPracticeQuestion = mListQuestion.get(1);
                mCurrentQuestion = 2;
                showQuestion(mPracticeQuestion, 2);
                break;
            case R.id.challenge_button_question_three:
                mPracticeQuestion = mListQuestion.get(2);
                mCurrentQuestion = 3;
                showQuestion(mPracticeQuestion, 3);
                break;
            case R.id.challenge_button_question_four:
                mPracticeQuestion = mListQuestion.get(3);
                mCurrentQuestion = 4;
                showQuestion(mPracticeQuestion, 4);
                break;
            case R.id.challenge_button_question_five:
                mPracticeQuestion = mListQuestion.get(4);
                mCurrentQuestion = 5;
                showQuestion(mPracticeQuestion, 5);
                break;
            case R.id.challenge_button_question_six:
                mPracticeQuestion = mListQuestion.get(5);
                mCurrentQuestion = 6;
                showQuestion(mPracticeQuestion, 6);
                break;
            case R.id.challenge_button_question_seven:
                mPracticeQuestion = mListQuestion.get(6);
                mCurrentQuestion = 7;
                showQuestion(mPracticeQuestion, 7);
                break;
            case R.id.challenge_button_question_eight:
                mPracticeQuestion = mListQuestion.get(7);
                mCurrentQuestion = 8;
                showQuestion(mPracticeQuestion, 8);
                break;
            case R.id.challenge_button_question_nine:
                mPracticeQuestion = mListQuestion.get(8);
                mCurrentQuestion = 9;
                showQuestion(mPracticeQuestion, 9);
                break;
            case R.id.challenge_button_question_ten:
                mPracticeQuestion = mListQuestion.get(9);
                mCurrentQuestion = 10;
                showQuestion(mPracticeQuestion, 10);
                break;
            case R.id.challenge_img_record:
                if(isRecording){
                    return;
                }
                mSpeechRecognizer.startListening(recognizerIntent);
                isRecording = true;
                mImageRecord.setImageResource(R.drawable.microphone_red);
                mTextviewResult.setVisibility(View.INVISIBLE);
                mTextviewTryAgainOrContine.setVisibility(View.INVISIBLE);
                break;

        }
    }

    private void showQuestion(PracticeQuestion mPracticeQuestion, int positionQuestion) {
        if(listCheckQuestionDone.get(positionQuestion-1)){
            mTextviewQuestion.setText(mPracticeQuestion.getQuestion());
            mTextviewDescription.setText(mPracticeQuestion.getDescription());
            mTextviewResult.setText(mPracticeQuestion.getResult());
            mTextviewTryAgainOrContine.setText(getString(R.string.done_question));
            mTextviewTryAgainOrContine.setTextColor(Color.GREEN);
            mImageRecord.setEnabled(false);
            mImageRecord.setClickable(false);
        }else {
            mTextviewQuestion.setText(mPracticeQuestion.getQuestion());
            mTextviewDescription.setText(mPracticeQuestion.getDescription());
            mTextviewHint.setText("");
            mTextviewResult.setText("");
            mTextviewTryAgainOrContine.setText("");
            mImageRecord.setEnabled(true);
            mImageRecord.setClickable(true);
        }
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.d("SpeechRecognize","onReady");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d("SpeechRecognize","onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float v) {
        Log.d("SpeechRecognize","onRmsChanged");
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.d("SpeechRecognize","onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d("SpeechRecognize","onEndOfSpeech");
        mTextviewIsChecking.setVisibility(View.VISIBLE);
        isRecording = false;
        mSpeechRecognizer.stopListening();
        mImageRecord.setImageResource(R.drawable.microphone_blue);
    }

    @Override
    public void onError(int errorCode) {
        isRecording = false;
        mSpeechRecognizer.stopListening();
        mImageRecord.setImageResource(R.drawable.microphone_blue);
        String errorMessage = getErrorText(errorCode);
        Log.d("SpeechRecognize","onError");
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
        Log.d("SpeechRecognize","onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for(int i=0;i<matches.size();i++){
            Log.e("SpeechResult",matches.get(i));
        }
        if(checkAnswer(matches)){
            //1.update variable for next question
            mNumberQuestion++;
            listCheckQuestionDone.set(mCurrentQuestion-1,true);
            //2.update layout
            updateLayout(true,matches.get(0));
        }else {
            updateLayout(false,matches.get(0));
        }
    }

    private void updateLayout(boolean isAnswerRight, String fistUserSpeak) {
        mTextviewIsChecking.setVisibility(View.INVISIBLE);
        if(isAnswerRight){
            SoundEffect.getInstance().playRightAnswerSound(getActivity(),mMediaPlayer);
            mTextviewTryAgainOrContine.setVisibility(View.VISIBLE);
            mTextviewTryAgainOrContine.setText(getString(R.string.choose_other_question_for_continue));
            mImageRecord.setEnabled(false);
            mTextviewResult.setTextColor(Color.GREEN);
            if(mPracticeQuestion.getType() != 1){
                mTextviewResult.setText(mPracticeQuestion.getResult());
            }
            mTextviewResult.setVisibility(View.VISIBLE);
        }else {
            SoundEffect.getInstance().playWrongAnswerSound(getActivity(),mMediaPlayer);
            mTextviewResult.setText(getString(R.string.you_have_pronounce)+" '"+fistUserSpeak+"'");
            mTextviewResult.setTextColor(Color.RED);
            mTextviewResult.setVisibility(View.VISIBLE);
            mTextviewTryAgainOrContine.setVisibility(View.VISIBLE);
            mTextviewTryAgainOrContine.setText(getString(R.string.please_try_again));
        }

    }

    private boolean checkAnswer(ArrayList<String> matches) {
        boolean isAnswerRight = true;
        if(mPracticeQuestion.getType() == 1){
            String[] needPronunceWord = mPracticeQuestion.getmCombineWordNeedPronounce().split("-");

            for(String answer : matches){
                boolean isContainAllWord = true;
                for(String wordNeedPronuce : needPronunceWord){
                    if(!answer.contains(wordNeedPronuce)){
                        isContainAllWord = false;
                    }
                }
                if(isContainAllWord){
                    isAnswerRight = true;
                    mTextviewResult.setText(answer);
                    mTextviewResult.setVisibility(View.VISIBLE);
                    break;
                }
            }
            return isAnswerRight;
        }else {
            boolean isSpeechRight = false;
            String[] importantWordArr = mPracticeQuestion.getCombineImportantWord().split("-");
            for(String result : matches){
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

    }

    @Override
    public void onPartialResults(Bundle bundle) {
        Log.d("SpeechRecognize","onPartialResults");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.d("SpeechRecognize","onEvent");
    }
}
