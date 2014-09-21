package dlmbg.pckg.info.bmkg;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay {

	private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
	private Drawable marker;
	private Context mContext;

	public MyItemizedOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		marker = defaultMarker;
	}

	public MyItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int index) {
		return (OverlayItem) items.get(index);
	}

	@Override
	public int size() {
		return items.size();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.android.maps.ItemizedOverlay#draw(android.graphics.Canvas,
	 * com.google.android.maps.MapView, boolean)
	 */
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);

	}

	public void addItem(OverlayItem item) {
		items.add(item);
		populate();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = items.get(0);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		dialog.show();
		return true;
	}

}
