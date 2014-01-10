package au.uq.dke.comon_rcp2.data.model.data.resource;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class MonetaryResource extends Resource	{
	private double amount;
	
	public MonetaryResource(String name){
		super(name);
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public MonetaryResource() {

	}
	

}
