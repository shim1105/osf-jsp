package com.osf.test.vo;

public class Food {
	private Integer foodNum;
	private Integer foodPrice;
	private String foodName;
	

	public Integer getFoodNum() {
		return foodNum;
	}


	public void setFoodNum(Integer foodNum) {
		this.foodNum = foodNum;
	}


	public Integer getFoodPrice() {
		return foodPrice;
	}


	public void setFoodPrice(Integer foodPrice) {
		this.foodPrice = foodPrice;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	@Override
	public String toString() {
		return "Food [foodNum=" + foodNum + ", foodPrice=" + foodPrice + ", foodName=" + foodName + "]";
	}
	
}
