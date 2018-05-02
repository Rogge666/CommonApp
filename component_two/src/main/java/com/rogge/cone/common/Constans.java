package com.rogge.cone.common;

import com.rogge.utils.Utils;

import java.io.File;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/22.
 * @since 1.0.0
 */
public class Constans {

    public static final String DATA = "DATA";
    public static final String DATA_ONE = "data_one";
    public static final String DATA_TWO = "data_two";
    public static final String DATA_THREE = "data_three";
    public static final String DATA_BEAN = "DATA_BEAN";

    //件的保存路径  草稿箱   进件箱  草稿箱详情   通过人脸识别的件
    public static final String DRAFT_ACCESS_PATH = "access/accesssun";
    public static final String ALREADY_ACCESS_PATH = "access/accessin";
    public static final String DRAFT_ACCESS_DETAIL = "access/detail";
    public static final String FACE_STATE = "access/facestate";
    //人脸识别图像保存目录
    private static final String FACE_IMAGE_PATH = "faceapp";

    //二维码保存路径
    public final static String CACHE = "/css";
    //二维码保存名字
    public final static String QR_NAME = "QRCODE.jpg";
    //微信appID
    public static final String APP_ID = "wx5a836044d7cdb37f";

    public static final String gotoFragmentAccess = "FragmentAccess";

    public static final int gotoFragmentWaitAccess = 0;
    public static final int gotoFragmentDraftAccess = 1;
    public static final int gotoFragmentAlreadyAccess = 2;

    //获取主页数据TAG
    public static final String GET_MAIN_TAG = "getMainTag";
    //获取卡片信息
    public static final int GET_CARD_DATA = 1;
    //获取服务信息
    public static final int GET_SERVICE_DATA = 2;

    public static final String cameraReturnData = "CameraReturnData";
    public static final String deletePic = "DeletePic";
    public static final String qrReturn = "QrReturn";

    public static final String refreshAccessOfTag = "REFRESH_ACCESS_OF_TAG";
    public static final int refreshWait = 0;
    public static final int refreshDraft = 1;
    public static final int refreshAlready = 2;

    public static final String ChannelResult = "channel_result";

    public static final String DraftFilesPath = Utils.getContext().getExternalFilesDir(null) + File.separator + DRAFT_ACCESS_PATH;
    public static final String DraftDetailFilesPath = Utils.getContext().getExternalFilesDir(null) + File.separator + DRAFT_ACCESS_DETAIL;
    public static final String AlreadyFilesPath = Utils.getContext().getExternalFilesDir(null) + File.separator + ALREADY_ACCESS_PATH;
    public static final String FaceStateFilePath = Utils.getContext().getExternalFilesDir(null) + File.separator + FACE_STATE + "/" + "face_state.txt";
    public static final String FaceImageFilesPath = Utils.getContext().getExternalFilesDir(null) + File.separator + FACE_IMAGE_PATH;
}
