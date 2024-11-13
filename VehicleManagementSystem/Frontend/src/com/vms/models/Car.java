package com.vms.models;

public class Car extends Vehicle {
	private int numberOfDoors;
	private boolean isConvertible;
	
	public Car(String VIN,String licensePlate,String make,String model,int year,int numberOfDoors,boolean isConvertible)
	{
		super(VIN,licensePlate,make,model,year);
		this.isConvertible = isConvertible;
		this.numberOfDoors = numberOfDoors;
	}

	public int getNumberOfDoors()
	{
		return this.numberOfDoors;
	}
	public void setNumberOfDoors(int numberOfDoors)
	{
		if(numberOfDoors > 0 && numberOfDoors < 5)
		{
			this.numberOfDoors = numberOfDoors;
		}
		else
		{
			throw new IllegalArgumentException("Number of doors should be between 1 and 5");
		}
	}
	public boolean isConvertible()
	{
		return this.isConvertible;
	}
	public void setConvertible(boolean convertible)
	{
		this.isConvertible = convertible;
	}
	@Override
	public void register()
	{
		super.register();
		System.out.println("Car registered with VIN:"+getVIN());
	}
	
	@Override
	public void deregister() {
		super.deregister();
		System.out.println("Car deregistered with VIN:"+getVIN());
		
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ",Number of Doors:" +numberOfDoors +",Convertible:" +isConvertible;
	}
	
}
