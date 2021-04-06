package com.entity;

import java.util.Date;
import java.util.List;

public class Orders {
	
	/** Order status-unpaid */
	public static final byte STATUS_UNPAY = 1;
	/** Order status-paid */
	public static final byte STATUS_PAYED = 2;
	/** Order status-shipping */
	public static final byte STATUS_SEND = 3;
	/** Order status-completed*/
	public static final byte STATUS_FINISH = 4;
	
	/** Payment method-PayPal */
	public static final byte PAYTYPE_WECHAT = 1;
	/** Payment method-Apple pay */
	public static final byte PAYTYPE_ALIPAY = 2;
	/** Payment method-Credit card*/
	public static final byte PAYTYPE_OFFLINE = 3;
	
    private Integer id;

    private Integer total;

    private Integer amount;

    private Byte status;

    private Byte paytype;

    private String name;

    private String phone;

    private String address;

    private Date systime;

    private Integer userId;
    
    
    private Users user;

	private List<Items> itemList;

	
	
    public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPaytype() {
        return paytype;
    }

    public void setPaytype(Byte paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getSystime() {
        return systime;
    }

    public void setSystime(Date systime) {
        this.systime = systime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}