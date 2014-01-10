package au.uq.dke.comon_rcp2.data.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InternalAudit extends Audit {

	public InternalAudit(String name) {
		super(name);
	}

	public InternalAudit() {

	}

}
