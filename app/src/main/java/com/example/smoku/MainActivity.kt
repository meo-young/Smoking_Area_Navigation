package com.example.smoku

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource

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

        val coord = LatLng(37.54045835,127.06932036)

        Toast.makeText(this,
            "위도: ${coord.latitude}, 경도: ${coord.longitude}",
            Toast.LENGTH_SHORT).show()

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
        locationOverlay.position = LatLng(37.54045835,127.06932036)
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.user_state_ic)

        //카메라의 초기위치 설정
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.54045835,127.06932036))
        naverMap.moveCamera(cameraUpdate)

        //각각의 흡연구역에 대한 마커 표시
        val front_hall_smoking_area = Marker()
        val innovation_hall_smoking_area = Marker()
        val cooperative_hall_smoking_area = Marker()
        val library_smoking_area = Marker()
        val artDesign_college_smoking_area = Marker()
        val liberalArts_college_smoking_area = Marker()
        val science_college_smoking_area = Marker()
        val bull_statue_smoking_area = Marker()

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


        conMapMarker(front_hall_smoking_area,naverMap,"프론트홀",front_hall_latitude,front_hall_longitude)
        conMapMarker(innovation_hall_smoking_area,naverMap,"혁신관",innovation_hall_latitude,innovation_hall_longitude)
        conMapMarker(cooperative_hall_smoking_area,naverMap,"협동관",cooperative_hall_latitude,cooperative_hall_longitude)
        conMapMarker(library_smoking_area,naverMap,"도서관",library_latitude,library_longitude)
        conMapMarker(artDesign_college_smoking_area,naverMap,"예술디자인대학",artDesign_college_latitude,artDesign_college_longitude)
        conMapMarker(liberalArts_college_smoking_area,naverMap,"문과대학",liberalArts_college_latitude,liberalArts_college_longitude)
        conMapMarker(science_college_smoking_area,naverMap,"이과대학",science_college_latitude,science_college_longitude)
        conMapMarker(bull_statue_smoking_area,naverMap,"황소동상",bull_statue_latitude,bull_statue_longitude)






    }

    fun conMapMarker(marker: Marker, naverMap: NaverMap, captionText: String, latitude: Double, longitude : Double){
        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(baseContext) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "열기"
            }
        }



        marker.position = LatLng(latitude,longitude)
        marker.map = naverMap
        marker.icon = OverlayImage.fromResource(R.drawable.default_smoking_area_ic)
        marker.captionText = captionText
        marker.setOnClickListener {

            if (marker.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow.open(marker)
                infoWindow.setOnClickListener {
                    if (mDialogView?.parent != null) {
                        (mDialogView?.parent as ViewGroup).removeView(mDialogView)
                    }
                    val smokingZone = mDialogView?.findViewById<TextView>(R.id.smokingAreaLabel)
                    smokingZone?.setText(captionText)
                    mBuilder?.setView(mDialogView)
                    mBuilder?.show()
                    true
                }

            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow.close()
            }
            true
        }


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
