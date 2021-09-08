package com.example.littlebossapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    private Marker marker;
    public String miubicación = "Mi Ubicación";
    public String seleccionarubicación = "Seleccionar Ubicación";
    private int REQUEST_CODE;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            /**
             * esta parte marca los puntos de las viviendas
             */
            SharedPreferences preferences = getActivity().getSharedPreferences("map", Context.MODE_PRIVATE);

            if (preferences.getInt("x", 0) != 0){
                Double latidude = Double.valueOf(preferences.getString("lat", ""));
                Double longitude = Double.valueOf(preferences.getString("lng", ""));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(latidude, longitude));


                mMap.addMarker(markerOptions).setTitle(preferences.getString("sector", ""));
                SharedPreferences.Editor editor =  getActivity().getSharedPreferences("map", Context.MODE_PRIVATE).edit();
                editor.clear().apply();
            }



            if (ActivityCompat.checkSelfPermission(getLayoutInflater().getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getLayoutInflater().getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            }
            mMap.setMyLocationEnabled(true);

            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {

                    //remove previously placed Marker
                    if (marker != null) {
                        marker.remove();
                    }

                    marker = mMap.addMarker(new MarkerOptions().position(point).title(seleccionarubicación)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                }


            });

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker arg0) {


                    Snackbar.make(getView().findViewById(R.id.map), "Lat: "+ arg0.getPosition().latitude + "  Lng: " +arg0.getPosition().longitude,
                            Snackbar.LENGTH_LONG)
                            .show();

                    if(!arg0.getTitle().equals(miubicación)){
                        Snackbar.make(getView().findViewById(R.id.map), "Lat: "+ arg0.getPosition().latitude + "\nLng: " +arg0.getPosition().longitude,
                                Snackbar.LENGTH_LONG)
                                .show();
                        SharedPreferences preferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
                        int rol = preferences.getInt("rol", 0);
                        if(rol !=1){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage("¿Desea agregar estas coordenadas?\nLatitud:"+arg0.getPosition().latitude + "\nLongitud: " + (arg0.getPosition().longitude))
                                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Intent intent = new Intent(getActivity(), registrar_cliente_fragment.class);
                                            Fragment nuevoFragmento = new registrar_cliente_fragment();
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            /**transaction.putExtra("lat", String.valueOf(arg0.getPosition().latitude));
                                            transaction.putExtra("Lng", String.valueOf(arg0.getPosition().longitude));
                                            transaction.putExtra("agregar", 1);**/
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Lng",String.valueOf(arg0.getPosition().longitude));
                                            bundle.putString("Lat",String.valueOf(arg0.getPosition().latitude));
                                            getParentFragmentManager().setFragmentResult("key", bundle);
                                            /**---------------------------**/
                                            transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                                            transaction.addToBackStack(null);
                                            transaction.commit();

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Snackbar.make(getView().findViewById(R.id.map), "Lat: "+ arg0.getPosition().latitude + "\nLng: " +arg0.getPosition().longitude,
                                                    Snackbar.LENGTH_LONG)
                                                    .show();
                                        }
                                    });
                            builder.show();
                        }

                    }
                }
            });

            LocationManager locationManager = (LocationManager) getLayoutInflater().getContext().getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());

                    //remove previously placed Marker
                    if (marker != null) {
                        marker.remove();
                    }
                    marker = mMap.addMarker(new MarkerOptions().position(miUbicacion).title(miubicación));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(miUbicacion)
                            .zoom(14)
                            .bearing(90)
                            .tilt(45)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}