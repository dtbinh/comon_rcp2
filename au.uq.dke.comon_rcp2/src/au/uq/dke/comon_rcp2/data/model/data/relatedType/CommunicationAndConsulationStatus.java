package au.uq.dke.comon_rcp2.data.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CommunicationAndConsulationStatus extends Status{
	public CommunicationAndConsulationStatus() {

	}
	
}
