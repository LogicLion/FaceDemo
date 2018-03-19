package com.example.tufei.facedemo.mvp.face;

import android.content.Context;

import com.example.tufei.facedemo.base.BasePresenter;
import com.example.tufei.facedemo.bean.User;
import com.example.tufei.facedemo.utils.FileUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author wzh
 * @date 2018/1/6
 */
public class FacePresenter extends BasePresenter implements FaceContract.Presenter {

    private FaceContract.View mView;
    private Context mContext;
    private FaceTask mFaceTask;


    private final static String ID_GUTIANLE = "1";
    private final static String NAME_GUTIANLE = "古天乐";
    private final static String EVALUATION_GUTIANLE = "只有太阳才能黑的男人。";
    private final static String FACE_FILE_GUTIANLE1 = "gutianle.jpg";
    private final static String FACE_FILE_GUTIANLE2 = "gutianle2.jpg";

    private final static String ID_WUYANZU = "2";
    private final static String NAME_WUYANZU = "吴彦祖";
    private final static String EVALUATION_WUYANZU = "明明可以靠脸吃饭，却偏偏靠演技";
    private final static String FACE_FILE_WUYANZU1 = "wuyanzu.jpg";
    private final static String FACE_FILE_WUYANZU2 = "wuyanzu2.jpg";

    private final static String NO_FACE_FILE_NAME = "noface.png";


    @Inject
    FacePresenter(Context context,FaceTask faceTask) {
        mFaceTask = faceTask;
        mContext=context;
    }

    @Override
    public void onAttachView(FaceContract.View view) {
        mView = view;
    }

    @Override
    public void onDetachView() {

        clearDisposite();
        mView = null;
    }

    @Override
    public void getAccessToken() {

        Disposable disposable = mFaceTask.getAccessToken().subscribe(accessToken -> {

        }, throwable -> mView.showToast(throwable.getMessage()));

        addDisposable(disposable);
    }

    @Override
    public void saveFace(int id) {

        byte[] picture;
        User user;
        if (id==1) {
            user = new User(ID_GUTIANLE, NAME_GUTIANLE, EVALUATION_GUTIANLE);
            picture = FileUtil.getBytesFromAssets(mContext, FACE_FILE_GUTIANLE1);
        } else {
            user = new User(ID_WUYANZU, NAME_WUYANZU, EVALUATION_WUYANZU);
            picture = FileUtil.getBytesFromAssets(mContext, FACE_FILE_WUYANZU1);
        }
        Disposable disposable = mFaceTask.saveFace(picture, user).subscribe(faceHttpResult -> mView.showToast("保存人脸成功")
                , throwable -> mView.showToast("保存人脸失败，失败原因：" + throwable.getMessage()));

        addDisposable(disposable);
    }

    @Override
    public void recognizeFace(int id) {

        byte[] picture=null;
        switch (id) {
            case 1:
                picture = FileUtil.getBytesFromAssets(mContext, FACE_FILE_GUTIANLE2);
                break;
            case 2:
                picture = FileUtil.getBytesFromAssets(mContext, FACE_FILE_WUYANZU2);
                break;
            case 3:
                picture = FileUtil.getBytesFromAssets(mContext, NO_FACE_FILE_NAME);
                break;
                default:
        }

        Disposable disposable = mFaceTask.recognizeFace(picture).subscribe(
                user ->
                        mView.showToast("识别成功，姓名：" + user.getName() + "评价：" + user.getEvaluate()),
                throwable ->
                        mView.showToast("识别失败,原因：" + throwable.getMessage()));

        addDisposable(disposable);
    }

    @Override
    public void deleteFace(int id) {

        Disposable disposable = mFaceTask.deleteFace(id+"").subscribe(faceHttpResult -> mView.showToast("删除成功")
                , throwable -> mView.showToast("删除失败，失败原因:" + throwable.getMessage()));

        addDisposable(disposable);
    }

    @Override
    public void getFace(int id) {
        Disposable disposable = mFaceTask.getFace(id+"").subscribe(user
                        -> mView.showToast("获取成功，姓名:" + user.getName() + "评价：" + user.getEvaluate()),
                throwable ->
                        mView.showToast("获取失败，失败原因：" + throwable.getMessage())
        );

        addDisposable(disposable);
    }


}
