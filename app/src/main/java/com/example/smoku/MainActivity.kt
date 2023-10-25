package com.example.smoku

import android.R.attr.key
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.FusedLocationSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private var mAlertDialog: AlertDialog? = null
    private var mBuilder: AlertDialog.Builder? = null
    private var mDialogView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDialogView = LayoutInflater.from(this).inflate(R.layout.navigation_dialog,null)
        mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view, it).commit()
            }


        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)


        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        mapFragment.getMapAsync(this)


    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        //안내시작 창에 대한 정보 불러오기

        this.naverMap = naverMap
        naverMap.mapType = NaverMap.MapType.Basic
        naverMap.isIndoorEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow


        //현재위치확인에 대한 버튼 활성화
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        //사용자의 위치에 대한 오버레이
        val locationOverlay = naverMap.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.position = LatLng(37.539734,127.077744)
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.user_state_ic)

        //카메라의 초기위치 설정
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.539734,127.077744))
        naverMap.moveCamera(cameraUpdate)


        var front_hall_latitude = 37.539314
        var front_hall_longitude = 127.078426

        var innovation_hall_latitude = 37.539734
        var innovation_hall_longitude = 127.077744

        var cooperative_hall_latitude = 37.539324
        var cooperative_hall_longitude = 127.073454

        var library_latitude = 37.542006
        var library_longitude = 127.074458

        var artDesign_college_latitude = 37.542586
        var artDesign_college_longitude = 127.072790

        var liberalArts_college_latitude = 37.543091
        var liberalArts_college_longitude = 127.078600

        var science_college_latitude = 37.541781
        var science_college_longitude = 127.080444

        var bull_statue_latitude = 37.542749
        var bull_statue_longitude = 127.076197


        conMapMarker(naverMap,"프론트홀",front_hall_latitude,front_hall_longitude)
        conMapMarker(naverMap,"혁신관",innovation_hall_latitude,innovation_hall_longitude)
        conMapMarker(naverMap,"협동관",cooperative_hall_latitude,cooperative_hall_longitude)
        conMapMarker(naverMap,"도서관",library_latitude,library_longitude)
        conMapMarker(naverMap,"예술디자인대학",artDesign_college_latitude,artDesign_college_longitude)
        conMapMarker(naverMap,"문과대학",liberalArts_college_latitude,liberalArts_college_longitude)
        conMapMarker(naverMap,"이과대학",science_college_latitude,science_college_longitude)
        conMapMarker(naverMap,"황소동상",bull_statue_latitude,bull_statue_longitude)





    }



    fun conMapMarker(naverMap: NaverMap, captionText: String, latitude: Double, longitude : Double){
        val APIKEY_ID = "l0z6ze1vzh"
        val APIKEY = "fdbh98Cx8SlZcvWJOVCoBxvgSjpC8HE867DlaPRh"
        //레트로핏 객체 생성
        val retrofit = Retrofit.Builder().
        baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/").
        addConverterFactory(GsonConverterFactory.create()).
        build()
        val api = retrofit.create(NaverAPI::class.java)

        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(baseContext) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "열기"
            }
        }


        val marker = Marker()
        marker.position = LatLng(latitude,longitude)
        marker.map = naverMap
        marker.icon = OverlayImage.fromResource(R.drawable.default_smoking_area_ic)
        marker.captionText = captionText
        marker.setOnClickListener {

            if (marker.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow.open(marker)
                infoWindow.setOnClickListener {
                    val ad = mBuilder?.create()
                    //ad?.window?.setDimAmount(0F)

                    var guideBtn = mDialogView?.findViewById<Button>(R.id.guideBtn)

                    guideBtn?.setOnClickListener {
                        val callgetPath = api.getPath(APIKEY_ID, APIKEY,"127.078426,37.539314", "$longitude,$latitude")

                        callgetPath.enqueue(object : Callback<ResultPath> {
                            override fun onResponse(
                                call: Call<ResultPath>,
                                response: Response<ResultPath>
                            ) {
                                val path_cords_list = response.body()?.route?.traoptimal
                                //경로 그리기 응답바디가 List<List<Double>> 이라서 2중 for문 썼음
                                val path = PathOverlay()
                                val fArray = floatArrayOf(122F,56.6F,68.6F)
                                path.outlineWidth = 0
                                path.width = 5
                                path.color = Color.HSVToColor(fArray)
                                //MutableList에 add 기능 쓰기 위해 더미 원소 하나 넣어둠
                                val path_container: MutableList<LatLng>? = mutableListOf(LatLng(0.1, 0.1))
                                for (path_cords in path_cords_list!!) {
                                    for (path_cords_xy in path_cords.path) {
                                        //구한 경로를 하나씩 path_container에 추가해줌
                                        path_container?.add(LatLng(path_cords_xy[1], path_cords_xy[0]))
                                    }
                                }
                                //더미원소 드랍후 path.coords에 path들을 넣어줌.
                                path.coords = path_container?.drop(1)!!
                                path.map = naverMap
                                //경로 시작점으로 화면 이동
                                if (path.coords != null) {
                                    val cameraUpdate = CameraUpdate.scrollTo(path.coords[0]!!)
                                        .animate(CameraAnimation.Fly, 3000)
                                    naverMap.moveCamera(cameraUpdate)
                                    Toast.makeText(baseContext, "경로 안내가 시작됩니다.", Toast.LENGTH_SHORT).show()
                                }

                            }

                            override fun onFailure(call: Call<ResultPath>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                        ad?.dismiss()

                    }
                    if (mDialogView?.parent != null) {
                        (mDialogView?.parent as ViewGroup).removeView(mDialogView)
                    }
                    val smokingZone = mDialogView?.findViewById<TextView>(R.id.smokingAreaLabel)
                    smokingZone?.setText(captionText)
                    mBuilder?.setView(mDialogView)
                    ad?.show()
                    true
                }

            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow.close()
            }
            true
        }
    }




    fun setDestinationMarker(marker: Marker){
        marker.captionColor = Color.BLUE
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


}
