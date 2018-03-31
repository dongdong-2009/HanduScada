package main.com.handu.scada.switch101.json;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class Value {

    private int InfoAddress;
    private boolean IsPlusMinusAmbit;
    private boolean IsValidValue;
    private int ItemBitPosition;
    private int ItemByteOrder;
    private int ItemDecimalBits;
    private String ItemName;
    private int ItemPlusMinusAmbit;
    private int ItemPosition;
    private int ItemShowType;
    private int ItemSize;
    private int ItemType;
    private String ItemUnit;
    private int ItemValue;
    private String SmsCode;
    private boolean SmsSendFilter;

    public void setInfoAddress(int InfoAddress) {
        this.InfoAddress = InfoAddress;
    }

    public int getInfoAddress() {
        return InfoAddress;
    }

    public void setIsPlusMinusAmbit(boolean IsPlusMinusAmbit) {
        this.IsPlusMinusAmbit = IsPlusMinusAmbit;
    }

    public boolean getIsPlusMinusAmbit() {
        return IsPlusMinusAmbit;
    }

    public void setIsValidValue(boolean IsValidValue) {
        this.IsValidValue = IsValidValue;
    }

    public boolean getIsValidValue() {
        return IsValidValue;
    }

    public void setItemBitPosition(int ItemBitPosition) {
        this.ItemBitPosition = ItemBitPosition;
    }

    public int getItemBitPosition() {
        return ItemBitPosition;
    }

    public void setItemByteOrder(int ItemByteOrder) {
        this.ItemByteOrder = ItemByteOrder;
    }

    public int getItemByteOrder() {
        return ItemByteOrder;
    }

    public void setItemDecimalBits(int ItemDecimalBits) {
        this.ItemDecimalBits = ItemDecimalBits;
    }

    public int getItemDecimalBits() {
        return ItemDecimalBits;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemPlusMinusAmbit(int ItemPlusMinusAmbit) {
        this.ItemPlusMinusAmbit = ItemPlusMinusAmbit;
    }

    public int getItemPlusMinusAmbit() {
        return ItemPlusMinusAmbit;
    }

    public void setItemPosition(int ItemPosition) {
        this.ItemPosition = ItemPosition;
    }

    public int getItemPosition() {
        return ItemPosition;
    }

    public void setItemShowType(int ItemShowType) {
        this.ItemShowType = ItemShowType;
    }

    public int getItemShowType() {
        return ItemShowType;
    }

    public void setItemSize(int ItemSize) {
        this.ItemSize = ItemSize;
    }

    public int getItemSize() {
        return ItemSize;
    }

    public void setItemType(int ItemType) {
        this.ItemType = ItemType;
    }

    public int getItemType() {
        return ItemType;
    }

    public void setItemUnit(String ItemUnit) {
        this.ItemUnit = ItemUnit;
    }

    public String getItemUnit() {
        return ItemUnit;
    }

    public void setItemValue(int ItemValue) {
        this.ItemValue = ItemValue;
    }

    public int getItemValue() {
        return ItemValue;
    }

    public void setSmsCode(String SmsCode) {
        this.SmsCode = SmsCode;
    }

    public String getSmsCode() {
        return SmsCode;
    }

    public void setSmsSendFilter(boolean SmsSendFilter) {
        this.SmsSendFilter = SmsSendFilter;
    }

    public boolean getSmsSendFilter() {
        return SmsSendFilter;
    }
}