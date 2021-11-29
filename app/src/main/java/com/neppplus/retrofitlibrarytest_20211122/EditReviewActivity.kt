package com.neppplus.retrofitlibrarytest_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofitlibrarytest_20211122.databinding.ActivityEditReviewBinding
import com.neppplus.retrofitlibrarytest_20211122.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20211122.datas.ProductData
import com.neppplus.retrofitlibrarytest_20211122.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding

    lateinit var mProductData : ProductData

    val mInputTagList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_review)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        binding.img1.setOnClickListener {

            val tempCategoryList = arrayListOf("아우터", "셔츠", "신발", "치마")

            binding.smallCategoryListLayout.removeAllViews()

            for (category in tempCategoryList) {
                val tagBox = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item, null)
                val txtTag = tagBox.findViewById<TextView>(R.id.txtTag)
                txtTag.text = "#${category}"

                binding.smallCategoryListLayout.addView(tagBox)
            }

        }
        
        binding.img2.setOnClickListener {

            val tempCategoryList = arrayListOf("과자", "과일", "가공식품", "곡류")

            binding.smallCategoryListLayout.removeAllViews()

            for (category in tempCategoryList) {
                val tagBox = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item, null)
                val txtTag = tagBox.findViewById<TextView>(R.id.txtTag)
                txtTag.text = "#${category}"

                binding.smallCategoryListLayout.addView(tagBox)
            }
        }


//        한글자 입력할때마다 -> 스페이스를 넣었는지 검사.

        binding.edtTag.addTextChangedListener {

            val nowText = it.toString()

            if (nowText == "") {
//                빈칸일때는 밑의 코드 실행 X
                return@addTextChangedListener
            }

            Log.d("입력값", nowText)

//            지금 입력된 내용의 마지막 글자(Char)가 ' ' 글자인가?

            if ( nowText.last() == ' ' )  {
                Log.d("입력값", "스페이스가 들어옴")

//                입력된값을 태그로 등록

//                태그로 등록될문구 => " "공백 제거
                val tag = nowText.replace(" ", "")

//                태그목록으로 추가해보자.
                mInputTagList.add( tag )

//                태그 목록 보여줄 레이아웃에 xml을 끌어오기 -> 그 내부의 텍스트뷰에 문구 변경

                val tagBox = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item, null)
                val txtTag = tagBox.findViewById<TextView>(R.id.txtTag)
                txtTag.text = "#${tag}"

                binding.tagListLayout.addView(tagBox)

//                입력값 초기화
                binding.edtTag.setText("")

            }


        }


        binding.btnWrite.setOnClickListener {

            for (tag in mInputTagList) {
                Log.d("입력태그", tag)
            }

            return@setOnClickListener

            val inputTitle = binding.edtReviewTitle.text.toString()
            val inputContent = binding.edtContent.text.toString()

//            몇점 입력?
            val rating = binding.ratingBar.rating.toInt()
            Log.d("평점 점수", rating.toString())

            apiService.postRequestReview(mProductData.id, inputTitle, inputContent, rating).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            } )

        }

    }

    override fun setValues() {

        mProductData = intent.getSerializableExtra("product") as ProductData

        binding.txtProductName.text =  mProductData.name
        binding.txtUserNickname.text =  GlobalData.loginUser!!.nickname

//        오늘 날짜 -> 2021.5.5 형태로 가공 -> 텍스트뷰에 반영

//        1. 오늘 날짜?
        val now = Calendar.getInstance()  // 현재 일시 자동 기록

//        원하는 형태로 가공 (String 생성)
        val sdf = SimpleDateFormat( "yyyy.M.d" )
        val nowString = sdf.format( now.time )

        binding.txtToday.text = nowString

    }
}