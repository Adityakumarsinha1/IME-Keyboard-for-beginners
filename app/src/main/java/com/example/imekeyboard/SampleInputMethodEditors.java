//package com.example.imekeyboard;
//
//import android.content.Context;
//import android.graphics.PixelFormat;
//import android.inputmethodservice.InputMethodService;
//import android.inputmethodservice.Keyboard;
//import android.inputmethodservice.KeyboardView;
//import android.media.AudioManager;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputConnection;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.inline.InlineContentView;
//
//public class SampleInputMethodEditors extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
//
//
//    /**
//     * 1.onCreateInputView
//     * setInputView
//     * onCreateCandidateView
//     * onStartInputView
//     * onFinishInputView
//     */
//    private KeyboardView kv;
//    private Keyboard keyboard;
//    private boolean caps = false;
////    private InlineContentClipView mScrollableSuggestionsClip;
////
////    private final Runnable mMoveScrollableSuggestionsToFg = () -> {
////        mScrollableSuggestionStrip.setZOrderedOnTop(true);
////        Toast.makeText(AutofillImeService.this, "Chips moved to fg - clickable",
////                Toast.LENGTH_SHORT).show();
////    };
//
//    @Override
//    public void setCandidatesView(View view) {
//
//        Log.d("@@@", "set candidate view");
//        super.setCandidatesView(view);
//    }
//
//    @Override
//    public void setInputView(View view) {
//        Log.d("@@@", "set input view");
//        super.setInputView(view);
//    }
//
//
//
//    @Override
//    public View onCreateCandidatesView() {
//
//        View candidateView = getLayoutInflater().inflate(R.layout.candidate_view, null);
//
//        LinearLayout candidateViewContainer = candidateView.findViewById(R.id.candidate_view);
//        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) candidateViewContainer.getLayoutParams();
//        layoutParams.topMargin = 10;
//
//        return candidateView;
//
////        LayoutInflater li = (LayoutInflater) getApplicationContext()
////                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View wordBar = li.inflate(R.layout.candidate_view, null);
////
////        LinearLayout ll = (LinearLayout) wordBar.findViewById(R.id.candidate_view);
////
////        mCandidateView = new CandidateView(this);
////        mCandidateView.setService(this);
////        setCandidatesViewShown(true);
////        mCandidateView.setLayoutParams(new LayoutParams(
////                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
////
////        ll.addView(mCandidateView);
////
////        return getLayoutInflater().inflate(R.layout.candidate_view, null);
//    }
//
//    @Override
//    public void onStartInputView(EditorInfo editorInfo, boolean restarting) {
//        Log.d("@@@", "on start input view");
//        super.onStartInputView(editorInfo, restarting);
//    }
//
//    @Override
//    public void onFinishInputView(boolean finishingInput) {
//        Log.d("@@@", "on finish input view");
//        super.onFinishInputView(finishingInput);
//    }
//
//    @Override
//    public void onStartCandidatesView(EditorInfo editorInfo, boolean restarting) {
//        Log.d("@@@", "on start candidate view");
//        super.onStartCandidatesView(editorInfo, restarting);
//    }
//
//    @Override
//    public void onFinishCandidatesView(boolean finishingInput) {
//
//        Log.d("@@@", "on finish candidate view");
//        super.onFinishCandidatesView(finishingInput);
//    }
//
//    @Override
//    public View onCreateInputView() {
//        Log.d("@@@", "on create input view");
//
////        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = layoutInflater.inflate(R.layout.input_view_1,  );
//        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.input_view, null);
//        keyboard = new Keyboard(this, R.xml.input_view_0);
////
//        kv.setKeyboard(keyboard);
//        kv.setOnKeyboardActionListener(this);
//        return kv;
//    }
//
//    @Override
//    public View onCreateExtractTextView() {
//        return super.onCreateExtractTextView();
//    }
//
//    @Override
//    public void onPress(int primaryCode) {
//
//    }
//
//    @Override
//    public void onRelease(int primaryCode) {
//
//    }
//
//    @Override
//    public void onKey(int primaryCode, int[] keyCodes) {
//        InputConnection ic = getCurrentInputConnection();
//        playClick(primaryCode);
//        switch (primaryCode) {
//            case Keyboard.KEYCODE_DELETE:
//                ic.deleteSurroundingText(1, 0);
//                break;
//            case Keyboard.KEYCODE_SHIFT:
//                caps = !caps;
//                keyboard.setShifted(caps);
//                kv.invalidateAllKeys();
//                break;
//            case Keyboard.KEYCODE_DONE:
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
//                break;
//            default:
//                char code = (char) primaryCode;
//                if (Character.isLetter(code) && caps) {
//                    code = Character.toUpperCase(code);
//                }
//                ic.commitText(String.valueOf(code), 1);
//        }
//
//    }
//
//    @Override
//    public void onText(CharSequence text) {
//
//    }
//
//    @Override
//    public void swipeLeft() {
//
//    }
//
//    @Override
//    public void swipeRight() {
//
//    }
//
//    @Override
//    public void swipeDown() {
//
//    }
//
//    @Override
//    public void swipeUp() {
//
//    }
//
//    private void playClick(int keyCode) {
//        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
//        switch (keyCode) {
//            case 32:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
//                break;
//            case Keyboard.KEYCODE_DONE:
//            case 10:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
//                break;
//            case Keyboard.KEYCODE_DELETE:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
//                break;
//            default:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
//        }
//    }
//}
