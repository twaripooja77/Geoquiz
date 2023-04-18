package com.example.geoquiz;

public class Questions {
    private int mQusTestResId;
    private boolean mIsAnsTrue;
 public Questions(int qusTestResId, boolean isAnsTrue){
     mQusTestResId= qusTestResId;
     mIsAnsTrue= isAnsTrue;
 }

    public int getQusTestResId() {
        return mQusTestResId;
    }

    public void setQusTestResId(int qusTestResId) {
        mQusTestResId = qusTestResId;
    }

    public boolean isAnsTrue() {
        return mIsAnsTrue;
    }

    public void setIsAnsTrue(boolean isAnsTrue) {
        mIsAnsTrue = isAnsTrue;
    }
}
