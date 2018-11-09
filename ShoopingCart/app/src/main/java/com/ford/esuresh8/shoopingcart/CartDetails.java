package com.ford.esuresh8.shoopingcart;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class CartDetails implements Parcelable {

    String productName;
    String category;
    String importFlag;
    Integer quantity;
    double unitPrice;
    double subTotal;
    double subTotalWithTax;

    public CartDetails() {
    }

    protected CartDetails(Parcel in) {
        productName = in.readString();
        category = in.readString();
        importFlag = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        unitPrice = in.readDouble();
        subTotal = in.readDouble();
        subTotalWithTax = in.readDouble();
    }

    public static final Creator<CartDetails> CREATOR = new Creator<CartDetails>() {
        @Override
        public CartDetails createFromParcel(Parcel in) {
            return new CartDetails(in);
        }

        @Override
        public CartDetails[] newArray(int size) {
            return new CartDetails[size];
        }
    };


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    public double getSubTotal() {
        return quantity * unitPrice;
    }

    public double getSubTotalWithTax() {
        if (importFlag.equals("imported")) {
            if (category.equals("Foods") || category.equals("Medicine") || category.equals("Books")) {
                subTotalWithTax = getSubTotal() * (0.05);
                return subTotalWithTax;
            } else {
                subTotalWithTax = getSubTotal() * (0.15);
                return subTotalWithTax;
            }
        } else if (importFlag.equals("others")) {
            if (category.equals("Foods") || category.equals("Medicine") || category.equals("Books")) {
                subTotalWithTax = 0.00;
                return subTotalWithTax;
            } else {
                subTotalWithTax = getSubTotal() * (0.10);
                return subTotalWithTax;
            }
        } else
            return subTotalWithTax = 0.00;
    }

    public void setSubTotalWithTax(double subTotalWithTax) {
        this.subTotalWithTax = subTotalWithTax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(productName);
        dest.writeString(category);
        dest.writeString(importFlag);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeDouble(unitPrice);
        dest.writeDouble(subTotal);
        dest.writeDouble(subTotalWithTax);
    }


}
