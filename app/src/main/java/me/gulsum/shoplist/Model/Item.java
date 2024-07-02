package me.gulsum.shoplist.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String itemName;
    private boolean itemPriority;

    public Item(String itemName, boolean itemPriority) {
        this.itemName = itemName;
        this.itemPriority = itemPriority;
    }

    protected Item(Parcel in) {
        itemName = in.readString();
        itemPriority = in.readByte() != 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemPriority() {
        return itemPriority;
    }

    public void setItemPriority(boolean itemPriority) {
        this.itemPriority = itemPriority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeByte((byte) (itemPriority ? 1 : 0));
    }

    @Override
    public String toString() {
        return itemName + (itemPriority ? " (öncelikli)" : "");
    }

    public void setCompleted(boolean b) {
    }

    public boolean isCompleted() {
        return false;
    }
}