package com.example.admin.bugproject.Fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.bugproject.Activities.DetailActivity;
import com.example.admin.bugproject.Adapters.BillListAdapter;
import com.example.admin.bugproject.Objects.FListPackagesResponse;
import com.example.admin.bugproject.Objects.FPackage;
import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Utils.ApiUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/19/2017.
 */

public class DrawImageFragment extends Fragment {

    public static DrawImageFragment drawImageFragment;

    public static DrawImageFragment instance(Activity activity) {
        if (drawImageFragment == null) {
            drawImageFragment = new DrawImageFragment();
            drawImageFragment.parentActivity = activity;
        }
        return drawImageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawimage, container, false);
        setupViews(rootView);
        return rootView;
    }

    private ListView listBill;
    private List<FPackage> listPkgs;
    private BillListAdapter adapter;

    private void getData() {
        String token = "";
        SharedPreferences editor = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE);
        token = editor.getString("token_user", "");
        ApiUtils.getAnotherService().getListPackage("PHPSESSID=" + token, "lab2.giaohangtietkiem.vn", "/", "1").enqueue(new Callback<FListPackagesResponse>() {
            @Override
            public void onResponse(Call<FListPackagesResponse> call, Response<FListPackagesResponse> response) {
                FListPackagesResponse flistPkgs = response.body();
                listPkgs = flistPkgs.getPkgs();
                System.out.println("====== Package =============");
                for (FPackage pkg : listPkgs) {
                    //System.out.println("Pkg " + pkg.alias + ": " + pkg.customer_fullname);
                    adapter.add(createBitmap(pkg));
                }
            }

            @Override
            public void onFailure(Call<FListPackagesResponse> call, Throwable t) {
                System.out.println("Error: " + call.toString());
            }
        });
    }

    private void setupViews(View view) {
        listBill = view.findViewById(R.id.fragment_drawimage_list);
        Button btn = view.findViewById(R.id.fragment_drawimage_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bitmap = createBitmap(listPkgs.get(0));
                    adapter = new BillListAdapter(getContext(), R.layout.item_listbill);
                    listBill.setAdapter(adapter);

                    getData();
            }
        });
    }

    private void drawBarCode(Paint paint, Canvas canvas, String barCode, int y) {
        try {
            Bitmap bitmapBarCode = this.encodeAsBitmap(barCode, BarcodeFormat.CODE_128, 400, 100);
            float center = (canvas.getWidth() / 2) - bitmapBarCode.getWidth() / 2;
            canvas.drawBitmap(bitmapBarCode, center, y, paint);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    public Activity parentActivity = null;

    public Activity getParentActivity() {
        return parentActivity;
    }

    private void addBlackBorder(Bitmap bitmap, Paint paint, Canvas canvas) {

        final int borderSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 3, getResources().getDisplayMetrics());
        final int cornerSizePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 5, getResources().getDisplayMetrics());

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        // prepare canvas for transfer
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);

        // draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        // draw border
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) borderSizePx);
        canvas.drawRoundRect(rectF, cornerSizePx, cornerSizePx, paint);
    }

    private void drawText(Paint paint, Canvas canvas, String message, int x, int y, boolean isAlignmentCentral, boolean isBold, boolean isBigSize, boolean isSmailSize, boolean isTinny) {

        // text size in pixels


        // text shadow
//        paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);


        if (isBold) {
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        } else {
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        }

        if (isBigSize) {
            paint.setTextSize((int) (60));
        } else {
            paint.setTextSize((int) (30));
        }
        if (isSmailSize) {
            paint.setTextSize((int) (20));
        } else if (isTinny) {
            paint.setTextSize((int) (25));
        }
        Rect bounds = new Rect();
        paint.getTextBounds(message, 0, message.length(), bounds);
        if (isAlignmentCentral) {
            x = (canvas.getWidth() / 2);
            x = x - bounds.width() / 2;

        }


        canvas.drawText(message, x, y, paint);
//        message = "Mạng lưới chuyển phát hàng nhanh nhất";
//
//        bounds = new Rect();
//        y= 60;
//        paint.getTextBounds(message, 0, message.length(), bounds);
//        x  = (canvas.getWidth() / 2);
//        x = x - bounds.width()/2;
//        canvas.drawText(message, x , y , paint);

    }

    private Bitmap createBitmap(FPackage order) {
        Bitmap bitmap = null;
        bitmap = Bitmap.createBitmap(595, 400, Bitmap.Config.ARGB_8888);
        String mText = "";
        int currentY = 5;
        try {
            Canvas canvas = new Canvas(bitmap);
            // new antialised Paint
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            addBlackBorder(bitmap, paint, canvas);
            paint.setStyle(Paint.Style.FILL);

            paint.setColor(Color.BLACK);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo_app_login_update);
            Bitmap appLogo = Bitmap.createScaledBitmap(largeIcon, 276, 40, false);
            float center = (canvas.getWidth() / 2) - appLogo.getWidth() / 2;
            canvas.drawBitmap(appLogo, center, currentY, paint);

            currentY += 45;

            drawBarCode(paint, canvas, order.order, currentY);

            currentY += 130;

            mText = String.valueOf(order.alias);
            drawText(paint, canvas, mText, 0, currentY, true, false, false, false, false);

            currentY += 5;

            paint.setStrokeWidth(1);

            canvas.drawLine(0, 185, bitmap.getWidth(), currentY, paint);

            currentY += 35;

            Rect bounds = new Rect();
            mText = "PHIẾU GIAO HÀNG ";

            paint.getTextBounds(mText, 0, mText.length(), bounds);
            drawText(paint, canvas, mText, 10, currentY, false, true, false, false, false);
            currentY += 25;

            mText = "TUYẾN ";

            paint.getTextBounds(mText, 0, mText.length(), bounds);

            drawText(paint, canvas, mText, (((canvas.getWidth() - 400) / 2 - bounds.width() / 2) + 400), 210, false, false, false, false, true);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mText = order.deliver_cart_alias;

            paint.setTextSize((int) (30));

            paint.getTextBounds(mText, 0, mText.length(), bounds);
            canvas.drawText(mText, (((canvas.getWidth() - 400) / 2 - bounds.width() / 2) + 400), 240, paint);
            mText = "(Kí nhận)";
            drawText(paint, canvas, mText, 10, currentY, false, false, false, true, false);

            canvas.drawLine(400, 185, 400, 260, paint);

            canvas.drawLine(0, 260, bitmap.getWidth(), 260, paint);

            mText = order.pick_fullname + "/" + order.created_username;
            String sub = "";
            String remainder = "";

            //drawText(paint, canvas, mText, 10, 270, false, false, false, true, false);
            //mText = order.getPickTel()+"/" + order.getPickFullName();
            //drawText(paint, canvas, mText, 10, 295, false, false, false, true, false);

            String shopName = order.pick_fullname;
            int lengthShopName = shopName.length();
            String shopCode = order.client_id;
            shopCode = (shopCode != null && shopCode.equals("") == false ? (" / " + shopCode) : "");
            int endShopName = (58 - shopCode.length()) > shopName.length() ? shopName.length() : (58 - shopCode.length());
            shopName = shopName.substring(0, endShopName);
            if (lengthShopName > shopName.length())
                shopName += "..";
            String zText = shopName + shopCode;
            paint.getTextBounds(zText, 0, zText.length(), bounds);
            drawText(paint, canvas, zText, 10, 282, false, false, false, true, false);

            mText = order.customer_fullname + ", " + order.customer_tel + ", ";
            paint.getTextBounds(mText, 0, mText.length(), bounds);

            boolean isBold = false;

            if (mText.length() > 58) {
                isBold = true;
                sub = mText.substring(0, 58);
                remainder = mText.substring(58);
                paint.getTextBounds(remainder, 0, remainder.length(), bounds);
                drawText(paint, canvas, sub, 10, 305, false, true, false, true, false);

            } else {
                sub = "";
                remainder = "";
                drawText(paint, canvas, mText, 10, 305, false, true, false, true, false);

                int checklengMtext = mText.length();
                paint.getTextBounds(mText, 0, mText.length(), bounds);

                mText = order.customer_fullname + ", " + order.customer_tel + ", " + order.customer_first_address + ", " + order.customer_last_address;
//                int endStr = (checklengMtext + 58) > mText.length() ? (mText.length() - ((checklengMtext + 58) - mText.length())) : 58;
                int endStr = 60  > mText.length() ? mText.length() : 60;
                sub = mText.substring(checklengMtext, endStr);
                remainder = mText.substring(endStr);
                drawText(paint, canvas, sub, 14 + bounds.width(), 305, false, false, false, true, false);
            }

            int lengRemaind = 0;
            if (isBold) {
                lengRemaind = remainder.length();
                drawText(paint, canvas, remainder, 10, 310, false, true, false, true, false);
                remainder = remainder + " " + order.customer_first_address + ", " + order.customer_last_address;
            }

            if (remainder.length() > 63) {
                sub = remainder.substring(lengRemaind, 60);
                remainder = remainder.substring(60);
                if (isBold) {
                    drawText(paint, canvas, sub, 10 + bounds.width(), 328, false, false, false, true, false);
                } else {
                    drawText(paint, canvas, sub, 10, 328, false, false, false, true, false);
                }

            } else {
                if (isBold) {
                    drawText(paint, canvas, remainder, 10 + bounds.width(), 328, false, false, false, true, false);
                } else {
                    drawText(paint, canvas, remainder, 10, 328, false, false, false, true, false);
                }

                sub = "";
                remainder = "";
            }
            currentY = 335;
            if (!remainder.equals("")) {
                if (remainder.length() > 63) {
                    sub = remainder.substring(0, 60) + "...";
                    currentY += 15;
                    drawText(paint, canvas, sub, 10, currentY, false, false, false, true, false);
                } else {
                    currentY += 15;
                    drawText(paint, canvas, remainder, 10, currentY, false, false, false, true, false);
                }
                currentY += 22;
            } else {
                currentY += 22;
            }

            order.desc = "Sample sample sample sample sample sample sample";
            mText = order.desc != null ? order.desc : "";

            if (mText.length() > 0) {
//                    currentY += 25;
                if (mText.length() >= 60) {
                    sub = mText.substring(0, 60);
                    remainder = mText.substring(60);
//                        drawText(paint, canvas, sub, 10, 360, false, false, false, true, false);
//                        drawText(paint, canvas, remainder, 10, 385, false, false, false, true, false);
                    drawText(paint, canvas, sub, 10, currentY, false, false, false, true, false);
                    currentY += 20;
                    drawText(paint, canvas, remainder, 10, currentY, false, false, false, true, false);
//                        currentY = 385;
                } else {
                    drawText(paint, canvas, mText, 10, currentY, false, false, false, true, false);
                }
                currentY += 20;
            } else {
                currentY += 20;
            }

            order.message = "Message message message message message message";
            mText = order.message;

            if (order.message.length() > 0) {
                String[] msArr = mText.split("<br/>");
                mText = msArr[0];
                for (int i = 1; i < msArr.length; i++) {
                    mText += ", " + msArr[i];
                }
                if (mText.length() >= 60) {
                    sub = mText.substring(0, 60);
                    remainder = mText.substring(60);
                    drawText(paint, canvas, sub, 10, currentY, false, false, false, true, false);
                    drawText(paint, canvas, remainder, 10, currentY + 25, false, false, false, true, false);
                } else {
                    drawText(paint, canvas, mText, 10, currentY, false, false, false, true, false);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Message error: " + e.getMessage());
        } finally {
            return bitmap;
        }
    }
}
