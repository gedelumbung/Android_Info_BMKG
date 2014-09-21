package dlmbg.pckg.info.bmkg;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class PetaGempaActivity extends MapActivity {

	Double lat = -6.29436;
	Double lng = 106.8859;
	String tanggal,jam,kekuatan,kedalaman,wilayah;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peta);
        MapView mapView = (MapView) findViewById(R.id.mapView);
        
        try {

            Bundle b = getIntent().getExtras();

            String koordinatLintang = b.getString("lintang");
            String[] koorlin = koordinatLintang.split(" : ");
            String koordinatBujur = b.getString("bujur");
            String[] koorbuj = koordinatBujur.split(" : ");
            
            lat = Double.parseDouble(koorlin[1]);
            lng = Double.parseDouble(koorbuj[1]);

            tanggal = b.getString("tanggal");
            jam = b.getString("jam");
            kekuatan = b.getString("kekuatan");
            kedalaman = b.getString("kedalaman");
            wilayah = b.getString("wilayah");
			
		} catch (Exception e) {}
        
        GeoPoint posisi = new GeoPoint((int) (lat * 1E6),(int) (lng * 1E6));
		MapController mapController = mapView.getController();
		mapController.setCenter(posisi);
		mapController.setZoom(8);
		
        Drawable icon = getResources().getDrawable(R.drawable.marker);
		icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon
				.getIntrinsicHeight());
		
		MyItemizedOverlay overlay = new MyItemizedOverlay(icon, this);
		OverlayItem item = new OverlayItem(posisi, "Posisi Gempa", "Tanggal : "+tanggal+" \nJam : "+jam+" \n"+kekuatan+"" +
				"\n"+kedalaman+" \n"+wilayah);
		overlay.addItem(item);
		mapView.getOverlays().add(overlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
