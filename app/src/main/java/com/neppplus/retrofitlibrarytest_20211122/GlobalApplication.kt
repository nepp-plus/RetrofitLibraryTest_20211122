package com.neppplus.retrofitlibrarytest_20211122

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "23bd4652c744e3f82017a4110f0040a2")

    }

}